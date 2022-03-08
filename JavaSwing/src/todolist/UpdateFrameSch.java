package todolist;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UpdateFrameSch extends JFrame{

	Container cp; 
	JLabel titleSday,titleCategory,titleCon,titleTime,titleWeekday,timeBet;
	JTextField tfSday,tfCon,tfTime1,tfTime2;
	JComboBox<String> cbCategory,cbweekday;
	JButton btnMod;
	String num;
	
	public UpdateFrameSch(String title)
	{
		super(title);
		cp=this.getContentPane();
		this.setBounds(200, 100, 290, 310);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp.setBackground(new Color(255, 200, 255));

		initDesign();
		
		//this.setVisible(true);
	}

	public void initDesign()
	{
		this.setLayout(null);
		//label
		titleSday=new JLabel("��¥");
		titleCategory=new JLabel("ī�װ�");
		titleCon=new JLabel("����");
		titleTime=new JLabel("�ð�");
		titleWeekday=new JLabel("����");
		timeBet=new JLabel(":");
		
		//tf
		tfSday=new JTextField(20);
		tfCon=new JTextField(20);
		tfTime1=new JTextField(20);
		tfTime2=new JTextField(20);
		
		//cb
		String [] category= {"�","����","����","���","��Ÿ"};
		cbCategory=new JComboBox<String>(category);  
		String [] weekday= {"������","ȭ����","������","�����","�ݿ���","�����","�Ͽ���"};
		cbweekday=new JComboBox<String>(weekday);  
		
		//btn
		btnMod=new JButton("����");
		
		//������Ʈ���� ��ġ����
		titleSday.setBounds(55, 20, 60, 30);
		this.add(titleSday);
		titleCategory.setBounds(55, 60, 60, 30);
		this.add(titleCategory);
		titleCon.setBounds(55, 100, 60, 30);
		this.add(titleCon);
		titleTime.setBounds(55, 140, 60, 30);
		this.add(titleTime);
		titleWeekday.setBounds(55, 180, 60, 30);
		this.add(titleWeekday);
		
		tfSday.setBounds(115, 25, 70, 20);
		this.add(tfSday);
		cbCategory.setBounds(115, 65, 70, 20);
		this.add(cbCategory);
		tfCon.setBounds(115, 105, 70, 20);
		this.add(tfCon);
		tfTime1.setBounds(115, 145, 70, 20);
		this.add(tfTime1);
		//timeBet.setBounds(148, 145, 15, 20);
		//this.add(timeBet);
		//tfTime2.setBounds(155, 145, 30, 20);
		//this.add(tfTime2);
		cbweekday.setBounds(115, 185, 70, 20);
		this.add(cbweekday);
		
		btnMod.setBounds(95, 220, 70, 25);
		this.add(btnMod);
		
		
		
	}
	
	
	
	//public static void main(String[] args) {
		

		//new UpdateFrameSch("�����߰� ������");
	

	//}
}