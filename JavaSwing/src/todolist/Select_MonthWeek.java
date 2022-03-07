package todolist;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

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
		this.setBounds(100, 100, 400, 250);
		cp.setBackground(new Color(255, 200, 200));
		initDesign();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void initDesign() {
		this.setLayout(null);
		
		Font a=new Font("�����ձ۾� ���ü", Font.BOLD, 15);
		
		//�� ����&��Ʈ ����
		lblM=new JLabel("��");
		lblM.setFont(a);
		lblW=new JLabel("����");
		lblW.setFont(a);
		
		//������Ʈ���� ��ġ ����(��,�޺��ڽ�)
		this.add(lblM);
		this.add(lblW);
	}
}
