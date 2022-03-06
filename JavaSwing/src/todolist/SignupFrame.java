package todolist;

import java.awt.Color;
import java.awt.Container;

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
		this.setBounds(450, 100, 250, 300);
		cp.setBackground(new Color(255, 200, 200));
		initDesign();
//		this.setVisible(true);
	}
	
	public void initDesign() {
		this.setLayout(null);
		
		//�� ����
		lblId=new JLabel("ID");
		lblPw=new JLabel("PW");
		lblName=new JLabel("�̸�");
		lblBirth=new JLabel("�������");
		//�ؽ�Ʈ�ʵ� ����
		tfId=new JTextField();
		tfPw=new JPasswordField();
		tfName=new JTextField();
		tfBirth=new JTextField();
		//��ư ����
		btnSubmit=new JButton("Ȯ��");
		
		//������Ʈ ��ġ ����(��,�ؽ�Ʈ�ʵ�,��ư)
		lblId.setBounds(30, 30, 50, 30);
		this.add(lblId);
		tfId.setBounds(90, 30, 100, 30);
		this.add(tfId);
		
		lblPw.setBounds(30, 70, 50, 30);
		this.add(lblPw);
		tfPw.setBounds(90, 70, 100, 30);
		this.add(tfPw);
		
		lblName.setBounds(30, 110, 50, 30);
		this.add(lblName);
		tfName.setBounds(90, 110, 100, 30);
		this.add(tfName);
		
		lblBirth.setBounds(30, 150, 50, 30);
		this.add(lblBirth);
		tfBirth.setBounds(90, 150, 100, 30);
		this.add(tfBirth);
		
		btnSubmit.setBounds(70, 200, 80, 30);
		this.add(btnSubmit);
	}
	
}
