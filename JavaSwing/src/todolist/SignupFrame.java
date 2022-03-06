package todolist;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SignupFrame extends JFrame{
	Container cp;
	JLabel lblId,lblPw,lblName,lblBirth;
	JTextField tfId,tfPw,tfName,tfBirth;
	JButton btnSubmit;
	
	public SignupFrame(String title) {
		super(title);
		cp = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(450, 100, 250, 300);
		cp.setBackground(new Color(255, 200, 200));
		initDesign();
//		this.setVisible(true);
	}
	
	public void initDesign() {
		this.setLayout(null);
		
		//라벨 생성
		lblId=new JLabel("ID");
		lblPw=new JLabel("PW");
		lblName=new JLabel("이름");
		lblBirth=new JLabel("생년월일");
		//텍스트필드 생성
		tfId=new JTextField();
		tfPw=new JTextField();
		tfName=new JTextField();
		tfBirth=new JTextField();
		//버튼 생성
		btnSubmit=new JButton("확인");
		
		//컴포넌트 위치 지정(라벨,텍스트필드,버튼)
		lblId.setBounds(30, 30, 50, 30);
		this.add(lblId);
		tfId.setBounds(80, 30, 100, 30);
		this.add(tfId);
		
		lblPw.setBounds(30, 70, 50, 30);
		this.add(lblPw);
		tfPw.setBounds(80, 70, 100, 30);
		this.add(tfPw);
		
		lblName.setBounds(30, 110, 50, 30);
		this.add(lblName);
		tfName.setBounds(80, 110, 100, 30);
		this.add(tfName);
		
		lblBirth.setBounds(30, 150, 50, 30);
		this.add(lblBirth);
		tfBirth.setBounds(80, 150, 100, 30);
		this.add(tfBirth);
		
		btnSubmit.setBounds(70, 200, 80, 30);
		this.add(btnSubmit);
	}
	
}
