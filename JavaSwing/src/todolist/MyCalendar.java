package todolist;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyCalendar extends JFrame implements ActionListener{
	DbConnect db=new DbConnect(); //오라클 DB객체 생성
	Container cp;
	JLabel lblID,lblPW; //라벨 ID,PW
	JTextField tfID; //텍스트필드 ID
	JPasswordField tfPW; //패스워드필드 PW
	JButton btnSignin,btnSignup; //로그인버튼,가입버튼
	
	//회원가입&날짜선택 프레임
	SignupFrame signup=new SignupFrame("회원가입");
	Select_MonthWeek mw=new Select_MonthWeek("날짜입력");
	
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
		
		//폰트
		Font a=new Font("나눔손글씨 장미체", Font.BOLD, 30);
		Font b=new Font("카페24 빛나는별", Font.PLAIN, 20);
		
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
		btnSignin.setFont(b);
		btnSignup=new JButton("회원가입");
		btnSignup.setFont(b);
		
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
///////////////////////////////////////////////////
	//로그인 메서드
	public void login(String inID, String inPW) {
		String sql="select pw from todo_member where id='"+inID+"'";
		//오라클DB 연결
		Connection conn=db.getOracle();
		//PreparedStatement pstmt=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			//pstmt=conn.prepareStatement(sql);
			//바인딩
			//pstmt.setString(1, inID);
			//SQL문 실행
			//rs=pstmt.executeQuery(sql);
			rs=stmt.executeQuery(sql);
			if(rs.next()) { //ID가 일치하는 것이 있으면
				//비밀번호 비교
				if(rs.getString(1).contentEquals(inPW)) {
					JOptionPane.showMessageDialog(this, "로그인 성공!!");
					this.setVisible(false);
					mw.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
				}
			}
			else { //ID가 일치하는 것이 없으면
				JOptionPane.showMessageDialog(this, "해당 아이디가 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
///////////////////////////////////////////////////	
	@Override //이벤트 메서드
	public void actionPerformed(ActionEvent e) {
		Object ob=e.getSource();
		if(ob==btnSignin) { //로그인 버튼 클릭
			String id=tfID.getText();
			String pw=tfPW.getText();
			
			if(id.equals("")||pw.equals("")) { //아이디,비번 공백 입력일 경우
				JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 정확히 입력해 주세요.");
			}
			else {
				login(id, pw); //login() 호출
			}
		}
		else if(ob==btnSignup) { //회원가입 버튼 클릭
			signup.setVisible(true);
		}
		else if(ob==signup.btnSubmit) { //"가입하기" 버튼 클릭
			String id=signup.tfId.getText();
			String pw=signup.tfPw.getText();
			String name=signup.tfName.getText();
			String birth=signup.tfBirth.getText();
			
			String sql="insert into todo_member values(seq_todo.nextval,?,?,?,?)";	
			//오라클DB 연결
			Connection conn=db.getOracle();
			PreparedStatement pstmt=null;
			
			//입력을 어느 하나라도 안할 경우
			if(id.equals("")||pw.equals("")||name.equals("")||birth.equals("")) {
				JOptionPane.showMessageDialog(this, "정확히 입력해 주세요.");
				//... 메시지창 위치 지정 ...
				//... 메시지창 글꼴 지정 ...
			}
			//입력이 바르게 되었을 경우
			else {
				try {
					pstmt=conn.prepareStatement(sql);
					//바인딩
					pstmt.setString(1, id);
					pstmt.setString(2, pw);
					pstmt.setString(3, name);
					pstmt.setString(4, birth);
					//SQL문 실행
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
		else if(ob==mw.btnCheck) { //날짜선택후 "확인"버튼 클릭
			String m=(String)mw.cbDateM.getSelectedItem();
			String w=(String)mw.cbDateW.getSelectedItem();
		}
	}
///////////////////////////////////////////////////
	public static void main(String[] args) {
		new MyCalendar("My 캘린더");
	}
}
