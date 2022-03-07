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
	JComboBox<String> cbDateM,cbDateW; //콤보박스Month,Week
	JLabel lblM,lblW; //라벨Month,Week
	JButton btnCheck; //확인버튼
	
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
		
		Font a=new Font("나눔손글씨 장미체", Font.BOLD, 15);
		
		//라벨 생성&폰트 적용
		lblM=new JLabel("월");
		lblM.setFont(a);
		lblW=new JLabel("주차");
		lblW.setFont(a);
		
		//컴포넌트들의 위치 지정(라벨,콤보박스)
		this.add(lblM);
		this.add(lblW);
	}
}
