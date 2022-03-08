package todolist;


import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	
	//Frame �߰�
	AddFrameSch addFrame=new AddFrameSch("���� �߰�");
	UpdateFrameSch updateFrame=new UpdateFrameSch("���� ����");

	ImageIcon icon0=new ImageIcon("C:\\SIST\\swingimage\\img7.gif");
	ImageIcon icon1=new ImageIcon("C:\\SIST\\picture\\1.png");
	ImageIcon icon2=new ImageIcon("C:\\SIST\\picture\\2.png");
	ImageIcon icon3=new ImageIcon("C:\\SIST\\picture\\3.png");
	ImageIcon icon4=new ImageIcon("C:\\SIST\\picture\\4.png");
	ImageIcon icon5=new ImageIcon("C:\\SIST\\picture\\5.png");
	ImageIcon icon6=new ImageIcon("C:\\SIST\\picture\\6.png");
	ImageIcon icon7=new ImageIcon("C:\\SIST\\picture\\7.png");
	ImageIcon iconbtn=new ImageIcon("C:\\SIST\\picture\\8.png");
	ImageIcon iconadd=new ImageIcon("C:\\SIST\\picture\\aaa.png");
	ImageIcon iconupd=new ImageIcon("C:\\SIST\\picture\\uu.png");
	ImageIcon icondel=new ImageIcon("C:\\SIST\\picture\\del.png");
	
	
	ImageIcon [] icon={icon0,icon1,icon2,icon3,icon4,icon5,icon6,icon7};
	
	
	public Dayselect(String title)
	{
		super(title);
		cp=this.getContentPane();
		this.setBounds(800, 200, 600, 550);
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
		
		Font f=new Font("�����ձ۾� ���ü",Font.BOLD,20);
		Font b=new Font("�����ձ۾� ���ü",Font.BOLD,13);
		
		
		//���̺�
		String [] title= {"No.","��¥","����","ī�װ�","��������","�����ð�"}; //���̺� ���� �� ��
		model=new DefaultTableModel(title,0); 
		table=new JTable(model);
		JScrollPane js=new JScrollPane(table);
		js.setBounds(10, 200, 570, 300);
		js.setFont(b);
		this.add(js);
		
		//������ư �߰�
		ButtonGroup bg=new ButtonGroup();
		
		//����
		
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
			rb[i].addItemListener(this);
			bg.add(rb[i]);
			this.add(rb[i]);
			xpos+=75;
			
			
		}
		
		
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
		btnAdd=new JButton(iconadd);
		btnAdd.setBounds(110, 140, 60, 60);
		btnAdd.setOpaque(false);
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(null);
		//btnAdd.setFont(b);
		this.add(btnAdd);
		btnAdd.addActionListener(this);
				
		btnUpdate=new JButton(iconupd);
		btnUpdate.setBounds(260, 140, 60, 60);
		btnUpdate.setOpaque(false);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(null);
		//btnUpdate.setFont(b);
		this.add(btnUpdate);
		btnUpdate.addActionListener(this);
			
		btnDel=new JButton(icondel);
		btnDel.setBounds(410, 140, 60, 60);
		btnDel.setOpaque(false);
		btnDel.setBorderPainted(false);
		btnDel.setBackground(null);
		//btnDel.setFont(b);
		this.add(btnDel);
		btnDel.addActionListener(this);
		
		addFrame.btnAdd.addActionListener(this);
		updateFrame.btnMod.addActionListener(this);
	}

	public void scheduleTableWrite(int select)
	{
		String sql="";
		if(select==1)
			sql="select num,sday,weekday,category,cont,schetime from schedule order by sday";
		else if(select==2)
			sql="select num,sday,weekday,category,cont,schetime from schedule where weekday='������' order by sday";
		else if(select==3)
			sql="select num,sday,weekday,category,cont,schetime from schedule where weekday='ȭ����' order by sday";
		else if(select==4)
			sql="select num,sday,weekday,category,cont,schetime from schedule where weekday='������' order by sday";
		else if(select==5)
			sql="select num,sday,weekday,category,cont,schetime from schedule where weekday='�����' order by sday";
		else if(select==6)
			sql="select num,sday,weekday,category,cont,schetime from schedule where weekday='�ݿ���' order by sday";
		else if(select==7)
			sql="select num,sday,weekday,category,cont,schetime from schedule where weekday='�����' order by sday";
		else if(select==8)
			sql="select num,sday,weekday,category,cont,schetime from schedule where weekday='�Ͽ���' order by sday";
		
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
				
				data.add(rs.getString("num"));
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
	
	//üũ�ڽ� ���� ��
	public void scheduleTableWrite2(int select)
	{
		String sql="";
		if(select==1)
			sql="select num,sday,weekday,category,cont,schetime from schedule where category='�' order by sday";
		
		else if(select==1&&select==2)
			sql="select num,sday,weekday,category,cont,schetime from schedule where category='�' and category='����' order by sday ";
		
		else if(select==2)
			sql="select num,sday,weekday,category,cont,schetime from schedule where category='����' order by sday";
		else if(select==3)
			sql="select num,sday,weekday,category,cont,schetime from schedule where category='����' order by sday";
		else if(select==4)
			sql="select num,sday,weekday,category,cont,schetime from schedule where category='���' order by sday";
		else if(select==5)
			sql="select num,sday,weekday,category,cont,schetime from schedule where category='��Ÿ' order by sday";
		
		
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
				
				data.add(rs.getString("num"));
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
	
	
	public void insertData()
	{
		String sday=addFrame.tfSday.getText();
		String cont=addFrame.tfCon.getText();
		String schetime=addFrame.tfTime1.getText();
		//String schetime2=addFrame.tfTime2.getText();
		String category=(String)addFrame.cbCategory.getSelectedItem();
		String weekday=(String)addFrame.cbweekday.getSelectedItem();
		int day=Integer.parseInt(sday);
		int week=((day+1)/7)+1;
		
		String sql="insert into schedule(num,sday,weekday,category,cont,schetime,week) values(seq_sch.nextval,?,?,?,?,?,?)";
		
		Connection conn=dbcon.getOracle();
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, sday);
			pstmt.setString(2, weekday);
			pstmt.setString(3, category);
			pstmt.setString(4, cont);
			pstmt.setString(5, schetime);
			pstmt.setInt(6, week);
			
			pstmt.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbcon.dbClose(pstmt, conn);
		}
		
	}
	
	
		public void updateData()
		{
			String num=updateFrame.num;
			String sday=updateFrame.tfSday.getText();
			String cont=updateFrame.tfCon.getText();
			String schetime=updateFrame.tfTime1.getText();
			//String schetime2=updateFrame.tfTime2.getText();
			String category=(String)updateFrame.cbCategory.getSelectedItem();
			String weekday=(String)updateFrame.cbweekday.getSelectedItem();
			int day=Integer.parseInt(sday);
			int week=((day+1)/7)+1;
			
			
			String sql="update schedule set sday=?,weekday=?,category=?,cont=?,schetime=?,week=? where num=?";
			
			Connection conn=dbcon.getOracle();
			PreparedStatement pstmt=null;
			
			try {
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, sday);
				pstmt.setString(2, weekday);
				pstmt.setString(3, category);
				pstmt.setString(4, cont);
				pstmt.setString(5, schetime);
				pstmt.setInt(6, week);
				pstmt.setString(7, num);
				
				pstmt.execute();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				dbcon.dbClose(pstmt, conn);
			}
			
		}
	
///////////////////////////////////////////////////////////////////////
	//������ư �̺�Ʈ
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object ob=e.getItem();
		for(int i=0;i<rb.length;i++)
		{
			if(ob==rb[i])
			{
				this.scheduleTableWrite(i+1);
			}
		}
		
		//üũ�ڽ� �̺�Ʈ
		int select=0;
		for(int i=0;i<cb.length;i++)
		{
			select++;
			if(cb[i].isSelected()) {
				this.scheduleTableWrite2(i+1);
			}
		}if(select==0)
			this.scheduleTableWrite(1);
	
	}
///////////////////////////////////////////////////////////////////////////////////
	//��ư �̺�Ʈ(�߰�,����,����)
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob=e.getSource();
		
		Connection conn=dbcon.getOracle();
		Statement stmt=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		
		if(ob==btnAdd)
		{
			
			System.out.println("add");
			addFrame.setVisible(true);
			
		}
		else if(ob==btnUpdate)
		{
			
			//System.out.println("update");
			int row=table.getSelectedRow();
			System.out.println(row);
			
			if(row==-1)
			{
				JOptionPane.showMessageDialog(this, "������ ���� �������ּ���");
				return; //�޼��� ����
			}
			
			
			String num=(String)model.getValueAt(row, 0);
			System.out.println(num);
			
			
			sql="select sday,weekday,category,cont,schetime from schedule where num="+num;
			
			try {
				stmt=conn.createStatement();
				rs=stmt.executeQuery(sql);
				
				if(rs.next())
				{
					updateFrame.num=num; 
					updateFrame.tfSday.setText(rs.getString("sday"));
					updateFrame.tfCon.setText(rs.getString("cont"));
					updateFrame.tfTime1.setText(rs.getString("schetime"));
					//updateFrame.tfTime2.setText(rs.getString("schetime"));
					updateFrame.cbCategory.setSelectedItem(rs.getString("category"));
					updateFrame.cbweekday.setSelectedItem(rs.getString("weekday"));
					updateFrame.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(this, "�ش��ϴ� ���� �����ϴ�");
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				dbcon.dbClose(rs, pstmt, conn);
			}
		}
			
			
		else if(ob==btnDel)
		{
			
			int row=table.getSelectedRow();
			System.out.println(row);
			
			if(row==-1)
			{
				JOptionPane.showMessageDialog(this, "������ ���� �������ּ���");
				return; 
			}
			
			
			String num=(String)model.getValueAt(row, 0);
			System.out.println(num);
			
			sql="delete from schedule where num=?";
			
			try {
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, num);
				pstmt.execute();
				
				JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�");
				this.scheduleTableWrite(1);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				dbcon.dbClose(pstmt, conn);
			}
			
		
		}
		else if(ob==addFrame.btnAdd)
		{
			
			insertData();
			
			scheduleTableWrite(1);
			
			
			addFrame.tfSday.setText("");
			addFrame.tfCon.setText("");
			addFrame.tfTime1.setText("");
			//addFrame.tfTime2.setText("");
			addFrame.cbweekday.setSelectedIndex(0); 
			addFrame.cbCategory.setSelectedIndex(0);
			addFrame.tfSday.requestFocus(); 
			
			
			addFrame.setVisible(false);
			
		}
		else if(ob==updateFrame.btnMod)
		{
			
			updateData();
			
			scheduleTableWrite(1);
			
			
			updateFrame.tfSday.setText("");
			updateFrame.tfCon.setText("");
			updateFrame.tfTime1.setText("");
			//updateFrame.tfTime2.setText("");
			updateFrame.cbweekday.setSelectedIndex(0); 
			updateFrame.cbCategory.setSelectedIndex(0);
			updateFrame.tfSday.requestFocus(); 
			
			
			updateFrame.setVisible(false);
			
		}
		
		
	}
	
	public static void main(String[] args) {
	
//		new Dayselect("2�� ����ǥ");
		
	}

}