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
	//라디오버튼,체크박스,버튼 3개
	
	public void setDesign()
	{
		this.setLayout(null);
		
		//폰트
		Font f=new Font("나눔손글씨 장미체",Font.BOLD,20);
		Font b=new Font("카페24 빛나는별",Font.BOLD,13);
		
		//테이블
		String [] title= {"날짜","요일","카테고리","일정내용","일정시간"}; //테이블 열에 들어갈 값
		model=new DefaultTableModel(title,0); 
		table=new JTable(model);
		JScrollPane js=new JScrollPane(table);
		js.setBounds(10, 200, 570, 300);
		js.setFont(b);
		this.add(js);
		
		//요일
		//라디오버튼 추가
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
			
			//이벤트 추가
			rb[i].addItemListener(this);
			bg.add(rb[i]);
			this.add(rb[i]);
			xpos+=75;
		}
		
		
		//카테고리
		//체크박스 추가
		String [] str= {"운동","업무","병원","행사","기타"};
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
		
		//버튼
		btnAdd=new JButton("추가");
		btnAdd.setBounds(200, 150, 60, 30);
		btnAdd.setFont(b);
		this.add(btnAdd);
		btnAdd.addActionListener(this);
				
		btnUpdate=new JButton("수정");
		btnUpdate.setBounds(275, 150, 60, 30);
		btnUpdate.setFont(b);
		this.add(btnUpdate);
		btnUpdate.addActionListener(this);
			
		btnDel=new JButton("삭제");
		btnDel.setBounds(350, 150, 60, 30);
		btnDel.setFont(b);
		this.add(btnDel);
		btnDel.addActionListener(this);
	}

	//요일별
	//테이블 출력
	public void scheduleTableWrite(int select)
	{
		String sql="";
		if(select==1)
			sql="select sday,weekday,category,cont,schetime from schedule order by sday";
		else if(select==2)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='월요일' order by sday";
		else if(select==3)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='화요일' order by sday";
		else if(select==4)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='수요일' order by sday";
		else if(select==5)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='목요일' order by sday";
		else if(select==6)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='금요일' order by sday";
		else if(select==7)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='토요일' order by sday";
		else if(select==8)
			sql="select sday,weekday,category,cont,schetime from schedule where weekday='일요일' order by sday";
		
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
	
	//카테고리별
	//테이블 출력
	public void scheduleTableWrite2(int select)
	{
		String sql="";
		if(select==1)
			sql="select sday,weekday,category,cont,schetime from schedule where category='운동' order by sday";
		else if(select==2)
			sql="select sday,weekday,category,cont,schetime from schedule where category='업무' order by sday";
		else if(select==3)
			sql="select sday,weekday,category,cont,schetime from schedule where category='병원' order by sday";
		else if(select==4)
			sql="select sday,weekday,category,cont,schetime from schedule where category='행사' order by sday";
		else if(select==5)
			sql="select sday,weekday,category,cont,schetime from schedule where category='기타' order by sday";
		
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
	//라디오버튼 이벤트
	//요일
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
		
		//체크박스 이벤트
		//카테고리
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
			sql="select sday,weekday,category,cont,schetime from schedule where category='업무' and category='운동' order by sday";
			
			
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
