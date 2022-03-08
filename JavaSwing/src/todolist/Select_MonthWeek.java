package todolist;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Select_MonthWeek extends JFrame{
	Container cp;
	JComboBox<String> cbDateM,cbDateW; //�޺��ڽ�Month,Week
	JLabel lblM,lblW; //��Month,Week
	JButton btnCheck; //Ȯ�ι�ư
	
	public Select_MonthWeek(String title) {
		super(title);
		cp=this.getContentPane();
		this.setBounds(100, 100, 400, 200);
		cp.setBackground(new Color(255, 200, 200));
		initDesign();
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setVisible(true);
	}
	
	public void initDesign() {
		this.setLayout(null);
		
		Font a=new Font("�����ձ۾� ���ü", Font.BOLD, 30);
		Font b=new Font("ī��24 �����º�", Font.PLAIN, 20);
		
		//�޺��ڽ� ����
		String[] year= {"2022"};
		cbDateM=new JComboBox<String>(year);
		String[] month={"1","2","3","4","5","6","7","8","9","10","11","12"};
		cbDateW=new JComboBox<String>(month);
		
		//�� ����&��Ʈ ����
		lblM=new JLabel("��");
		lblM.setFont(a);
		lblW=new JLabel("��");
		lblW.setFont(a);
		
		//��ư ����
		btnCheck=new JButton("Ȯ��");
		btnCheck.setFont(b);
		
		//������Ʈ���� ��ġ ����(�޺��ڽ�,��,��ư)
		cbDateM.setBounds(60, 30, 60, 30);
		this.add(cbDateM);
		cbDateW.setBounds(200, 30, 60, 30);
		this.add(cbDateW);
		
		lblM.setBounds(130, 30, 70, 30);
		this.add(lblM);
		lblW.setBounds(270, 30, 70, 30);
		this.add(lblW);
		
		btnCheck.setBounds(150, 100, 70, 30);
		this.add(btnCheck);
	}
}
