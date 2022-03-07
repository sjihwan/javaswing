package todolist;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyCalendar extends JFrame implements ActionListener{
	DbConnect db = new DbConnect(); //오라클 DB객체 생성
	Container cp;
	JLabel lblID,lblPW; //라벨 ID,PW
	JTextField tfID; //텍스트필드 ID
	JPasswordField tfPW; //패스워드필드 PW
	JButton btnSignin,btnSignup; //로그인버튼,가입버튼
	
	SignupFrame signup=new SignupFrame("회원가입");
	
	public MyCalendar(String title) {
		super(title);
		cp=this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 100, 250, 280);
		cp.setBackground(new Color(255, 200, 200));
		initDesign();
		this.setVisible(true);
	}
	
	public void initDesign() {
		this.setLayout(null);
		
		Font a=new Font("카페24 빛나는별", Font.BOLD, 30);
		
		//라벨 생성
		lblID=new JLabel("ID");
		lblID.setFont(a);
		lblPW=new JLabel("PW");
		lblPW.setFont(a);
		//텍스트필드 생성
		tfID=new JTextField(8);
		tfPW=new JPasswordField(8);
		//버튼 생성
		btnSignin=new JButton("로그인");
		btnSignup=new JButton("회원가입");
		
		//컴포넌트들의 위치지정(라벨,텍스트필드,버튼)
		lblID.setBounds(30, 30, 50, 30);
		this.add(lblID);
		tfID.setBounds(80, 30, 100, 30);
		this.add(tfID);
		
		lblPW.setBounds(30, 70, 50, 30);
		this.add(lblPW);
		tfPW.setBounds(80, 70, 100, 30);
		this.add(tfPW);
		
		btnSignin.setBounds(70, 130, 90, 30);
		this.add(btnSignin);
		btnSignup.setBounds(70, 170, 90, 30);
		this.add(btnSignup);
		
		//이벤트 생성
		btnSignin.addActionListener(this);
		btnSignup.addActionListener(this);
		signup.btnSubmit.addActionListener(this);
	}
	
	@Override //이벤트 메서드
	public void actionPerformed(ActionEvent e) {
		Object ob=e.getSource();
		if(ob==btnSignin) {
			
		}
		else if(ob==btnSignup) {
			signup.setVisible(true);
		}
		else if(ob==signup.btnSubmit) {
			String id=signup.tfId.getText();
			String pw=signup.tfPw.getText();
			String name=signup.tfName.getText();
			String birth=signup.tfBirth.getText();
			
			String sql="insert into todo_member values(seq_todo.nextval,?,?,?,?)";	
			//오라클 DB연결
			Connection conn = db.getOracle();
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
				//바인딩
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.setString(3, name);
				pstmt.setString(4, birth);
				//sql문 실행
				pstmt.execute();
				
				JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다!");
				signup.tfId.setText("");
				signup.tfPw.setText("");
				signup.tfName.setText("");
				signup.tfBirth.setText("");
				signup.setVisible(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				db.dbClose(pstmt, conn);
			}
		}
	}

	public static void main(String[] args) {
		new MyCalendar("My 캘린더");
	}
}
