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
	JComboBox<String> cbDateM,cbDateW; //콤보박스Month,Week
	JLabel lblM,lblW; //라벨Month,Week
	JButton btnCheck; //확인버튼
	
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
		
		Font a=new Font("나눔손글씨 장미체", Font.BOLD, 30);
		Font b=new Font("카페24 빛나는별", Font.PLAIN, 20);
		
		//콤보박스 생성
		String[] year= {"2022"};
		cbDateM=new JComboBox<String>(year);
		String[] month={"1","2","3","4","5","6","7","8","9","10","11","12"};
		cbDateW=new JComboBox<String>(month);
		
		//라벨 생성&폰트 적용
		lblM=new JLabel("년");
		lblM.setFont(a);
		lblW=new JLabel("월");
		lblW.setFont(a);
		
		//버튼 생성
		btnCheck=new JButton("확인");
		btnCheck.setFont(b);
		
		//컴포넌트들의 위치 지정(콤보박스,라벨,버튼)
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
