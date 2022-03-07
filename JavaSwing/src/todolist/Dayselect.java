package todolist;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Dayselect extends JFrame implements ItemListener,ActionListener{

	DbConnect dbcon=new DbConnect();
	
	Container cp;
	
	DefaultTableModel model;
	JTable table;
	JButton btnAdd,btnDel,btnUpdate;
	JRadioButton [] rb=new JRadioButton[8];
	JCheckBox [] cb=new JCheckBox[5];

	ImageIcon icon0=new ImageIcon("C:\\SIST\\swingimage\\img7.gif");
	ImageIcon icon1=new ImageIcon("C:\\SIST\\picture\\1.png");
	ImageIcon icon2=new ImageIcon("C:\\SIST\\picture\\2.png");
	ImageIcon icon3=new ImageIcon("C:\\SIST\\picture\\3.png");
	ImageIcon icon4=new ImageIcon("C:\\SIST\\picture\\4.png");
	ImageIcon icon5=new ImageIcon("C:\\SIST\\picture\\5.png");
	ImageIcon icon6=new ImageIcon("C:\\SIST\\picture\\6.png");
	ImageIcon icon7=new ImageIcon("C:\\SIST\\picture\\7.png");
	ImageIcon iconbtn=new ImageIcon("C:\\SIST\\picture\\8.png");
	
	ImageIcon [] icon={icon0,icon1,icon2,icon3,icon4,icon5,icon6,icon7};
	
	
	public Dayselect(String title)
	{
		super(title);
		cp=this.getContentPane();
		this.setBounds(800, 200, 605, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp.setBackground(new Color(255, 200, 100));
		
		setDesign();
		this.scheduleTableWrite(1);
		
//		this.setVisible(true);
	}

//////////////////////////////////////////////////////////////////
	//������ư,üũ�ڽ�,��ư 3��
	
	public void setDesign()
	{
		this.setLayout(null);
		
		//��Ʈ
		Font f=new Font("�����ձ۾� ���ü",Font.BOLD,20);
		Font b=new Font("ī��24 �����º�",Font.BOLD,13);
		
		//���̺�
		String [] title= {"��¥","����","ī�װ�","��������","�����ð�"}; //���̺� ���� �� ��
		model=new DefaultTableModel(title,0); 
		table=new JTable(model);
		JScrollPane js=new JScrollPane(table);
		js.setBounds(10, 200, 570, 300);
		js.setFont(b);
		this.add(js);
		
		//����
		//������ư �߰�
		ButtonGroup bg=new ButtonGroup();
			
		int xpos=30;
		rb[0]=new JRadioButton(icon0);
		rb[0].setBounds(7, 20, 60, 60);
		rb[0].setOpaque(false);
		rb[0].addItemListener(this);
		this.add(rb[0]);
		
		
		for(int i=1;i<rb.length;i++)
		{
			//rb[i]=new JRadioButton(rb_title[i],i==0?true:false);
			rb[i]=new JRadioButton(icon[i]);
			rb[i].setBounds(xpos, 20, 90, 60);
			rb[i].setOpaque(false);
			
			rb[i].setPressedIcon(iconbtn);
			
			//�̺�Ʈ �߰�
			rb[i].addItemListener(this);
			bg.add(rb[i]);
			this.add(rb[i]);
			xpos+=75;
		}
		
		
		//ī�װ�
		//üũ�ڽ� �߰�
		String [] str= {"�","����","����","���","��Ÿ"};
		int a=45;
		for(int i=0;i<cb.length;i++)
		{
			cb[i]=new JCheckBox(str[i]);
			cb[i].setBounds(a, 100, 80, 30);
			
			cb[i].setFont(f);
			a+=110;
			cb[i].setOpaque(false);
			this.add(cb[i]);
			
			cb[i].addItemListener(this);
		}
		
		//��ư
		btnAdd=new JButton("�߰�");
		btnAdd.setBounds(200, 150, 60, 30);
		btnAdd.setFont(b);
		this.add(btnAdd);
		btnAdd.addActionListener(this);
				
		btnUpdate=new JButton("����");
		btnUpdate.setBounds(275, 150, 60, 30);
		btnUpdate.setFont(b);
		this.add(btnUpdate);
		btnUpdate.addActionListener(this);
			
		btnDel=new JButton("����");
		btnDel.setBounds(350, 150, 60, 30);
		btnDel.setFont(b);
		this.add(btnDel);
		btnDel.addActionListener(this);
	}

	//���Ϻ�
	//���̺� ���
	public void scheduleTableWrite(int select)
	{
		String sql="";
		if(select==1)
			sql="select sday,weekday,category,cont,schetime from schedule order by sday";
		else if(select==2)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='������' order by sday";
		else if(select==3)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='ȭ����' order by sday";
		else if(select==4)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='������' order by sday";
		else if(select==5)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='�����' order by sday";
		else if(select==6)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='�ݿ���' order by sday";
		else if(select==7)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='�����' order by sday";
		else if(select==8)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='�Ͽ���' order by sday";
		
		Connection conn=dbcon.getOracle();
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			model.setRowCount(0);
			
			while(rs.next())
			{
				Vector<String> data=new Vector<String>();
				
				data.add(rs.getString("sday"));
				data.add(rs.getString("weekday"));
				data.add(rs.getString("category"));
				data.add(rs.getString("cont"));
				data.add(rs.getString("schetime"));
				
				model.addRow(data);
			}
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.dbClose(rs, stmt, conn);
		}
	}
	
	//ī�װ���
	//���̺� ���
	public void scheduleTableWrite2(int select)
	{
		String sql="";
		if(select==1)
			sql="select sday,weekday,category,cont,schetime from schedule where category='�' order by sday";
		else if(select==2)
			sql="select sday,weekday,category,cont,schetime from schedule where category='����' order by sday";
		else if(select==3)
			sql="select sday,weekday,category,cont,schetime from schedule where category='����' order by sday";
		else if(select==4)
			sql="select sday,weekday,category,cont,schetime from schedule where category='���' order by sday";
		else if(select==5)
			sql="select sday,weekday,category,cont,schetime from schedule where category='��Ÿ' order by sday";
		
		Connection conn=dbcon.getOracle();
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			model.setRowCount(0);
			
			while(rs.next())
			{
				Vector<String> data=new Vector<String>();
				
				data.add(rs.getString("sday"));
				data.add(rs.getString("weekday"));
				data.add(rs.getString("category"));
				data.add(rs.getString("cont"));
				data.add(rs.getString("schetime"));
				
				model.addRow(data);
			}
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.dbClose(rs, stmt, conn);
		}
		
	}
	
///////////////////////////////////////////////////////////////////////
	//������ư �̺�Ʈ
	//����
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		Object ob=e.getItem();
		
		for(int i=0;i<rb.length;i++)
		{
			if(ob==rb[i])
			{
				this.scheduleTableWrite(i+1);
			}
		}
		
		//üũ�ڽ� �̺�Ʈ
		//ī�װ�
		int select=0;
		for(int i=0;i<cb.length;i++)
		{
			select++;
			if(cb[i].isSelected()) {
				this.scheduleTableWrite2(i+1);
			}
		}if(select==0)
			this.scheduleTableWrite(1);
		
		/*if(ob==cb[0] && ob==cb[1])
		{
			String sql="";
			sql="select sday,weekday,category,cont,schetime from schedule where category='����' and category='�' order by sday";
			
			
			Connection conn=dbcon.getOracle();
			Statement stmt=null;
			ResultSet rs=null;
			
			try {
				stmt=conn.createStatement();
				rs=stmt.executeQuery(sql);
				
				model.setRowCount(0);
				
				while(rs.next())
				{
					Vector<String> data=new Vector<String>();
					
					data.add(rs.getString("sday"));
					data.add(rs.getString("weekday"));
					data.add(rs.getString("category"));
					data.add(rs.getString("cont"));
					data.add(rs.getString("schetime"));
					
					
				
					model.addRow(data);
					
			} 
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				dbcon.dbClose(rs, stmt, conn);
			}
			
			
			
			
		}
		*/
		
		
		/*int select=0;
		 * for(int i=0;i<cb.length;i++)
		{
			select++;
			
			if(cb[i].isSelected()) {
				this.scheduleTableWrite2(i+1);
			}
		}
		if(select==0)
			this.scheduleTableWrite(1);*/
	
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {

//		new Dayselect("");
		
	}

}
