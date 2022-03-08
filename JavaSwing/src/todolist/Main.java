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
	DbConnect db=new DbConnect(); //����Ŭ DB��ü ����
	Container cp;
	JLabel lblID,lblPW; //�� ID,PW
	JTextField tfID; //�ؽ�Ʈ�ʵ� ID
	JPasswordField tfPW; //�н������ʵ� PW
	JButton btnSignin,btnSignup; //�α��ι�ư,���Թ�ư
	
	//�ܺ� Frame
	//ȸ������&��¥����&����ǥ
	SignupFrame signup=new SignupFrame("ȸ������");
	Select_Date sd=new Select_Date("��¥�Է�");
	Dayselect sche=new Dayselect("����ǥ");
	
	public Main(String title) {
		super(title);
		cp=this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 100, 250, 280);
		cp.setBackground(new Color(255, 200, 200));
		initDesign();
		this.setVisible(true);
	}
	
	//1.������ �ż���
	public void initDesign() {
		this.setLayout(null);
		
		//��Ʈ
		Font a=new Font("�����ձ۾� ���ü", Font.BOLD, 30);
		Font b=new Font("ī��24 �����º�", Font.PLAIN, 20);
		
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
		btnSignin.setFont(b);
		btnSignup=new JButton("ȸ������");
		btnSignup.setFont(b);
		
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
		sd.btnCheck.addActionListener(this);
	}
	
///////////////////////////////////////////////////
	//2.�α��� �޼���
	/*
	 * �Է��� ID,PW�� �μ��� ������
	 * -> 
	 * ID�� ��ġ�ϴ� PW�� ã�� �α��� ����!!
	 * ->
	 * monthweek â�� �θ���
	 * ->
	 * �Է��� ���̵� �μ��� ������
	 * ->
	 * ����ǥ ���̺��� ���...Write()
	 */
	public void login(String inID, String inPW) {
		String sql="select pw from todo_member where id='"+inID+"'";
		Connection conn=db.getOracle();
		Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			//ID�� ��ġ�ϴ� ���� ������
			if(rs.next()) { 
				//��й�ȣ ��
				if(rs.getString(1).contentEquals(inPW)) {
					JOptionPane.showMessageDialog(this, "�α��� ����!!");
					this.setVisible(false);
					sd.setVisible(true);
					//inID�� �μ��� ������ �������� ��ȸ�� ���
					//write(inID);
				}
				else {
					JOptionPane.showMessageDialog(this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
			}
			//ID�� ��ġ�ϴ� ���� ������
			else { 
				JOptionPane.showMessageDialog(this, "�ش� ���̵� �������� �ʽ��ϴ�.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//3.��� �޼���
	
///////////////////////////////////////////////////	
	//�̺�Ʈ �޼���
	@Override 
	public void actionPerformed(ActionEvent e) {
		
		Object ob=e.getSource();
		
		//�α��� ��ư Ŭ��
		if(ob==btnSignin) { 
			String id=tfID.getText();
			String pw=tfPW.getText();			
			if(id.equals("")||pw.equals("")) { //���̵�,��� ���� �Է� ��
				JOptionPane.showMessageDialog(this, "���̵�� ��й�ȣ�� ��Ȯ�� �Է��� �ּ���.");
			}
			else { //���̵�,��� ��� �Է� ��
				login(id, pw); //login() ȣ��
			}
		}
		
		
		//ȸ������ ��ư Ŭ��
		else if(ob==btnSignup) { 
			signup.setVisible(true);
		}
		
		
		//"�����ϱ�" ��ư Ŭ��
		else if(ob==signup.btnSubmit) { 
			String id=signup.tfId.getText();
			String pw=signup.tfPw.getText();
			String name=signup.tfName.getText();
			String birth=signup.tfBirth.getText();
			
			String sql="insert into todo_member values(seq_todo.nextval,?,?,?,?)";	
			//����ŬDB ����
			Connection conn=db.getOracle();
			PreparedStatement pstmt=null;
			
			//�Է��� ��� �ϳ��� ���� ���
			if(id.equals("")||pw.equals("")||name.equals("")||birth.equals("")) {
				JOptionPane.showMessageDialog(this, "��Ȯ�� �Է��� �ּ���.");
				//... �޽���â ��ġ ���� ...
				//... �޽���â �۲� ���� ...
			}
			//�Է��� �ٸ��� �Ǿ��� ���
			else {
				ResultSet rs =null;
				String sql0="select * from todo_member where id=?";
				
				try {
					pstmt=conn.prepareStatement(sql0);
					pstmt.setString(1, id);
					rs=pstmt.executeQuery();
					//���̵� �ߺ�Ȯ��
					if(rs.next()) { 
						JOptionPane.showMessageDialog(this, "�ߺ��� ���̵� �ֽ��ϴ�","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
					}
					else {
						pstmt=conn.prepareStatement(sql);
						//���ε�
						pstmt.setString(1, id);
						pstmt.setString(2, pw);
						pstmt.setString(3, name);
						pstmt.setString(4, birth);
						//SQL�� ����
						pstmt.execute();
						
						JOptionPane.showMessageDialog(this, "ȸ�������� �Ϸ�Ǿ����ϴ�!");
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
		
		
		//��&���� ������ "Ȯ��"��ư Ŭ��
		else if(ob==sd.btnCheck) { 
			
			//�⵵&�� ������
			//(String) m,w ������ ����
			String y=(String)sd.cbDateM.getSelectedItem();
			String m=(String)sd.cbDateW.getSelectedItem();
			
			if(y=="2022"&&m=="2") {
				//������Frame ����
				sche.setVisible(true);
			}
			//������Frame �ݱ�
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
				JOptionPane.showMessageDialog(this, "�ߺ��� ���̵� �ֽ��ϴ�","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		
	
///////////////////////////////////////////////////
	public static void main(String[] args) {
		new Main("My Ķ����");
	}
}

/*
 * ���� ID�� �ش��ϴ� ���̺� �ҷ����� �ʿ�
 * ȸ�����̺�, ID�� ���Ե� ���̺�, �������̺� ���� ���� ���� �ʿ�
 */
 