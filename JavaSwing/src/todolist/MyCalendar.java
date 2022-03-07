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
	DbConnect db = new DbConnect(); //����Ŭ DB��ü ����
	Container cp;
	JLabel lblID,lblPW; //�� ID,PW
	JTextField tfID; //�ؽ�Ʈ�ʵ� ID
	JPasswordField tfPW; //�н������ʵ� PW
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
		
		Font a=new Font("ī��24 �����º�", Font.BOLD, 30);
		
		//�� ����
		lblID=new JLabel("ID");
		lblID.setFont(a);
		lblPW=new JLabel("PW");
		lblPW.setFont(a);
		//�ؽ�Ʈ�ʵ� ����
		tfID=new JTextField(8);
		tfPW=new JPasswordField(8);
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
		signup.btnSubmit.addActionListener(this);
	}
	
	@Override //�̺�Ʈ �޼���
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
			//����Ŭ DB����
			Connection conn = db.getOracle();
			PreparedStatement pstmt = null;
			try {
				pstmt = conn.prepareStatement(sql);
				//���ε�
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.setString(3, name);
				pstmt.setString(4, birth);
				//sql�� ����
				pstmt.execute();
				
				JOptionPane.showMessageDialog(this, "ȸ�������� �Ϸ�Ǿ����ϴ�!");
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
		new MyCalendar("My Ķ����");
	}
}
