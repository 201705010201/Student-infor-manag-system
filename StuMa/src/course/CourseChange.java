package course;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dataconnection.DbC;

/**
 * Title:课程修改
 * @author moon
 *
 */
public class CourseChange extends JFrame {

	private JPanel contentPane;
	private JTextField textField  = new JTextField();
	private JTextField textField_1 = new JTextField();
	private JComboBox comboBox = new JComboBox();

	DbC dbC = new DbC();
	
	int nfind;

	public CourseChange(int find) {
		nfind = find;
		init();
	}
	
	
	public void init() {
		setFont(new Font("Dialog", Font.BOLD, 20));
		setTitle("课程信息修改");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("课  程  信  息  修  改");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(94, 10, 239, 21);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("课程名称：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(64, 48, 91, 25);
		contentPane.add(label_1);
		
		
		textField.setFont(new Font("宋体", Font.PLAIN, 18));
		textField.setBounds(171, 50, 185, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("所属专业：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(64, 106, 101, 21);
		contentPane.add(label_2);
		
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setBounds(170, 101, 188, 30);
		contentPane.add(comboBox);
		
		JLabel label_3 = new JLabel("学  分：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(75, 152, 80, 21);
		contentPane.add(label_3);
		
		
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setBounds(170, 148, 186, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)  {
				String keCName, zyName, tName, xueFen;
				keCName = textField.getText().trim();
				zyName = String.valueOf(comboBox.getSelectedItem());
				xueFen = textField_1.getText().trim();
				try {
					dbC.getUpdate("update tb_cource set courceSpecName='" + zyName + "', courceName='" + keCName
							+ "',courceHour='" + Float.valueOf(xueFen) + "' where courceId='" + nfind + "'");
					JOptionPane.showMessageDialog(null, "课程修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} catch (Exception a) {
					System.out.println(a.getMessage());
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(65, 198, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(246, 198, 93, 23);
		contentPane.add(button_1);
		
		comboBox.addItem("已有专业");
		// 选择专业
		try {
			ResultSet rs = dbC.getRS("select * from tb_spec");
			while (rs.next()) {
				String xi = rs.getString("specName");
				comboBox.addItem(xi);
			}
			rs.close();
		} catch (Exception ce) {
			System.out.println("++++++++" + ce);
		}
		
		
		//选定修改行之后在修改界面显示信息
		set();
		
		
	}

	//选定修改行之后在修改界面显示信息
	public void set() {
		if(nfind<0) 
			return;
		else {
			try {
				ResultSet rSet = dbC.getRS("select * from tb_cource where courceId='" + Integer.valueOf(nfind) + "'");
				
				while(rSet.next()) {
					comboBox.setSelectedItem(String.valueOf(rSet.getString("courceSpecName")));
					textField.setText(rSet.getString("courceName").trim());
					textField_1.setText(rSet.getString("courceHour").trim());
				}
				rSet.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
