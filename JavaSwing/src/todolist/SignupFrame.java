package todolist;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignupFrame extends JFrame {
	Container cp;
	JLabel lblId,lblPw,lblName,lblBirth;
	JTextField tfId,tfName,tfBirth;
	JPasswordField tfPw;
	JButton btnSubmit;
	
	public SignupFrame(String title) {
		super(title);
		cp = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(450, 100, 260, 300);
		cp.setBackground(new Color(255, 200, 200));
		initDesign();
//		this.setVisible(true);
	}
	
	public void initDesign() {
		this.setLayout(null);
		
		Font a=new Font("나눔손글씨 장미체", Font.BOLD, 20);
		
		//라벨 생성
		lblId=new JLabel("ID");
		lblId.setFont(a);
		lblPw=new JLabel("PW");
		lblPw.setFont(a);
		lblName=new JLabel("이름");
		lblName.setFont(a);
		lblBirth=new JLabel("생년월일");
		lblBirth.setFont(a);
		//텍스트필드 생성
		tfId=new JTextField();
		tfPw=new JPasswordField();
		tfName=new JTextField();
		tfBirth=new JTextField();
		//버튼 생성
		btnSubmit=new JButton("가입하기");
		
		//컴포넌트 위치 지정(라벨,텍스트필드,버튼)
		lblId.setBounds(30, 30, 70, 30);
		this.add(lblId);
		tfId.setBounds(120, 30, 100, 30);
		this.add(tfId);
		
		lblPw.setBounds(30, 70, 70, 30);
		this.add(lblPw);
		tfPw.setBounds(120, 70, 100, 30);
		this.add(tfPw);
		
		lblName.setBounds(30, 110, 70, 30);
		this.add(lblName);
		tfName.setBounds(120, 110, 100, 30);
		this.add(tfName);
		
		lblBirth.setBounds(30, 150, 70, 30);
		this.add(lblBirth);
		tfBirth.setBounds(120, 150, 100, 30);
		this.add(tfBirth);
		
		btnSubmit.setBounds(80, 200, 90, 30);
		this.add(btnSubmit);
	}
	
}
