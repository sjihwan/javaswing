package todolist;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyCalendar extends JFrame implements ActionListener{
	DbConnect db = new DbConnect(); //����Ŭ DB�� ����
	Container cp;
	JLabel lblID,lblPW; //�� ID,PW
	JTextField tfID,tfPW; //�ؽ�Ʈ�ʵ� ID,PW
	JButton btnSignin,btnSignup; //�α��ι�ư,���Թ�ư
	
	SignupFrame signup=new SignupFrame("ȸ������");
	
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
		
		//�� ����
		lblID=new JLabel("ID");
		lblPW=new JLabel("PW");
		//�ؽ�Ʈ�ʵ� ����
		tfID=new JTextField(8);
		tfPW=new JTextField(8);
		//��ư ����
		btnSignin=new JButton("�α���");
		btnSignup=new JButton("ȸ������");
		
		//������Ʈ���� ��ġ����(��,�ؽ�Ʈ�ʵ�,��ư)
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
		
		//�̺�Ʈ ����
		btnSignin.addActionListener(this);
		btnSignup.addActionListener(this);
	}
	
	@Override //�̺�Ʈ �޼���
	public void actionPerformed(ActionEvent e) {
		Object ob=e.getSource();
		if(ob==btnSignin) {
			
		}
		else if(ob==btnSignup) {
			signup.setVisible(true);
		}
	}

	public static void main(String[] args) {
		new MyCalendar("My Ķ����");
	}
}
