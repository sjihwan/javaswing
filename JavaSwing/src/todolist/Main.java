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

public class Main extends JFrame implements ActionListener{
	DbConnect db=new DbConnect(); //오라클 DB객체 생성
	Container cp;
	JLabel lblID,lblPW; //라벨 ID,PW
	JTextField tfID; //텍스트필드 ID
	JPasswordField tfPW; //패스워드필드 PW
	JButton btnSignin,btnSignup; //로그인버튼,가입버튼
	
	//외부 Frame
	//회원가입&날짜선택&일정표
	SignupFrame signup=new SignupFrame("회원가입");
	Select_Date sd=new Select_Date("날짜입력");
	Dayselect sche=new Dayselect("일정표");
	
	public Main(String title) {
		super(title);
		cp=this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 100, 250, 280);
		cp.setBackground(new Color(255, 200, 200));
		initDesign();
		this.setVisible(true);
	}
	
	//1.디자인 매서드
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
		sd.btnCheck.addActionListener(this);
	}
	
///////////////////////////////////////////////////
	//2.로그인 메서드
	/*
	 * 입력한 ID,PW를 인수로 가져와
	 * -> 
	 * ID와 일치하는 PW를 찾아 로그인 성공!!
	 * ->
	 * monthweek 창을 부른다
	 * ->
	 * 입력한 아이디를 인수로 가져가
	 * ->
	 * 일정표 테이블을 출력...Write()
	 */
	public void login(String inID, String inPW) {
		String sql="select pw from todo_member where id='"+inID+"'";
		Connection conn=db.getOracle();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			//ID가 일치하는 것이 있으면
			if(rs.next()) { 
				//비밀번호 비교
				if(rs.getString(1).contentEquals(inPW)) {
					JOptionPane.showMessageDialog(this, "로그인 성공!!");
					this.setVisible(false);
					sd.setVisible(true);
					//inID를 인수로 가져가 조건절로 조회문 출력
					//write(inID);
				}
				else {
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
				}
			}
			//ID가 일치하는 것이 없으면
			else { 
				JOptionPane.showMessageDialog(this, "해당 아이디가 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//3.출력 메서드
	
///////////////////////////////////////////////////	
	//이벤트 메서드
	@Override 
	public void actionPerformed(ActionEvent e) {
		
		Object ob=e.getSource();
		
		//로그인 버튼 클릭
		if(ob==btnSignin) { 
			String id=tfID.getText();
			String pw=tfPW.getText();			
			if(id.equals("")||pw.equals("")) { //아이디,비번 공백 입력 시
				JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 정확히 입력해 주세요.");
			}
			else { //아이디,비번 모두 입력 시
				login(id, pw); //login() 호출
			}
		}
		
		
		//회원가입 버튼 클릭
		else if(ob==btnSignup) { 
			signup.setVisible(true);
		}
		
		
		//"가입하기" 버튼 클릭
		else if(ob==signup.btnSubmit) { 
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
				ResultSet rs =null;
				String sql0="select * from todo_member where id=?";
				
				try {
					pstmt=conn.prepareStatement(sql0);
					pstmt.setString(1, id);
					rs=pstmt.executeQuery();
					//아이디 중복확인
					if(rs.next()) { 
						JOptionPane.showMessageDialog(this, "중복된 아이디가 있습니다","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
					}
					else {
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
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					db.dbClose(rs, pstmt, conn);
				}
			}
		}
		
		
		//월&주차 선택후 "확인"버튼 클릭
		else if(ob==sd.btnCheck) { 
			
			//년도&월 선택후
			//(String) m,w 변수에 저장
			String y=(String)sd.cbDateM.getSelectedItem();
			String m=(String)sd.cbDateW.getSelectedItem();
			
			if(y=="2022"&&m=="2") {
				//스케줄Frame 열기
				sche.setVisible(true);
			}
			//월주차Frame 닫기
			sd.setVisible(false); 
		}
	}
	
	public void check(String id) {
		Connection conn=db.getOracle();
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		String sql="select * from todo_member where id=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(this, "중복된 아이디가 있습니다","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		
	
///////////////////////////////////////////////////
	public static void main(String[] args) {
		new Main("My 캘린더");
	}
}

/*
 * 각자 ID에 해당하는 테이블 불러오기 필요
 * 회원테이블, ID가 포함된 테이블, 일정테이블 서로 간의 연동 필요
 */
 