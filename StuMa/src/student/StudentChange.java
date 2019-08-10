package student;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;

import dataconnection.DbC;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class StudentChange extends JFrame {

	//private JFrame JFrame;
	JPanel contentPane;
	
	private JTextField textField;
	private JTextField textField_1;
	JButton button = new JButton();
	JButton button_1 = new JButton();
	JComboBox comboBox = new JComboBox();
	JComboBox comboBox_1 = new JComboBox();
	JComboBox comboBox_2 = new JComboBox();
	
	JOptionPane jOptionPane1 = new JOptionPane();
	JOptionPane jOptionPane2 = new JOptionPane();
	JOptionPane jOptionPane_enter = new JOptionPane();
	
	String number, name, depart, sspec, sclass;
	int find;
	DbC conn = new DbC();
	
	public StudentChange(int find) {
		this.find = find;
		init();
		addActionListener2();
	}
	
	private void init() {
		//JFrame = new JFrame();
		//JFrame.setTitle("学生信息修改");
		//JFrame.getContentPane().setFont(new Font("宋体", Font.PLAIN, 20));
		//JFrame.setBounds(100, 100, 568, 355);
		//JFrame.setVisible(true);
		//JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("学   生   信   息   修   改");
		label.setFont(new Font("宋体", Font.BOLD, 24));
		label.setBounds(110, 21, 372, 26);
		contentPane.add(label);
		
		
		JLabel label_1 = new JLabel("学号:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 22));
		label_1.setBounds(43, 72, 66, 33);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("姓名：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(43, 123, 66, 26);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBounds(110, 76, 184, 26);
		textField.setColumns(10);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setBounds(110, 120, 184, 26);
		textField_1.setColumns(10);
		contentPane.add(textField_1);
		
		JLabel label_3 = new JLabel("所属学院：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(43, 169, 100, 26);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("所属专业：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(282, 169, 100, 26);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("所属班级：");
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(43, 216, 100, 24);
		contentPane.add(label_5);
		
		button = new JButton("提交");
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(88, 264, 93, 23);
		contentPane.add(button);
		
		button_1 = new JButton("退出");
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(327, 264, 93, 23);
		contentPane.add(button_1);
		
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setBounds(139, 169, 127, 26);
		contentPane.add(comboBox);
		
		comboBox_1.setEnabled(false);
		comboBox_1.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox_1.setBounds(375, 169, 125, 26);
		contentPane.add(comboBox_1);
		
		comboBox_2 .setEnabled(false);
		comboBox_2.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox_2.setBounds(139, 215, 127, 26);
		contentPane.add(comboBox_2);
		
		this.comboBox.addItem("请选择学院");
		
		//选择学院
		try {
			ResultSet rs = conn.getRS("select * from tb_depart");
			while(rs.next()) {
				String xueyuan = rs.getString("departName");
				comboBox.addItem(xueyuan);
			} 
		} catch(Exception ce) {
			//System.out.println(ce);
			ce.printStackTrace();
		}
		showstu();
		comboBox_1.setEnabled(false);
		comboBox_2.setEnabled(false);
	}
	
	
	public void addActionListener2() {
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				jcb();
				comboBox_1.setEnabled(true);
			}
		});
		
		comboBox_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				classAdd();
				comboBox_2.setEnabled(true);
			}
		});
		
		
		//提交按钮的监听
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 异常判断
				if (textField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入学生学号！", "提 示", JOptionPane.INFORMATION_MESSAGE);
				} else if (textField_1.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入学生姓名！", "提 示", JOptionPane.INFORMATION_MESSAGE);
				} else if (comboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择学生所在学院！", "提 示", JOptionPane.INFORMATION_MESSAGE);
				} else if (comboBox_1.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择学生所属专业！", "提 示", JOptionPane.INFORMATION_MESSAGE);
				} else if (comboBox_2.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择学生所在班级！", "提 示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						
						findDepart();
						findSpec();
						findClass();
						
						if (find == Integer.parseInt(textField.getText().trim())) {
							// 不修改学号的情况
							number = textField.getText().trim();
							name = textField_1.getText().trim();
							updatestu();
						} 
						else{
							//修改学号的情况
							boolean Num = false;
							ResultSet rs = conn.getRS("select * from tb_student");
							while (rs.next()) {
								if (textField.getText().trim().equals(rs.getString("stuNumber").trim())) {
									Num = true;
								}
							}
							rs.close();
							if (Num) {
								//判断学号是否存在
									JOptionPane.showMessageDialog(null, "学生学号已存在，请重新输入！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
								} else {
									number = textField.getText().trim();
									name = textField_1.getText().trim();
									updatestu();
								}
							} 	
						} catch(Exception ce) {
								System.out.println(ce.getMessage());
						}
				}
			}
		});
	
		//退出按钮的监听
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	//选择学院触发选择专业
	public void jcb() {
		comboBox_1.removeAllItems();
		comboBox_1.addItem("请选择专业");
		try {
			ResultSet rs = conn.getRS(
					"select * from tb_spec where departName='" + String.valueOf(comboBox.getSelectedItem()) + "' ");
			while(rs.next()) {
				String zhuan = rs.getString("specName");
				comboBox_1.addItem(zhuan);
			}
			rs.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
	//选择专业触发选择班级
	public void classAdd() {
		comboBox_2.removeAllItems();
		comboBox_2.addItem("请选择班级");
		try {
			ResultSet rs = conn.getRS(
					"select * from tb_class where specName='" + String.valueOf(comboBox_1.getSelectedItem()) + "' ");
			while (rs.next()) {
				String banji = rs.getString("className");
				comboBox_2.addItem(banji);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	//查询学院
	public void findDepart() {
		try {
			ResultSet rs = conn.getRS(
					"select * from tb_depart where departName='" + String.valueOf(comboBox.getSelectedItem()) + "' ");
			while (rs.next()) {
				depart = rs.getString("departName");
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 查询专业
	public void findSpec() {
		try {
			ResultSet rs = conn.getRS(
					"select * from tb_spec where specName='" + String.valueOf(comboBox_1.getSelectedItem()) + "' ");
			while (rs.next()) {
				sspec = rs.getString("specName");
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 查询班级
	public void findClass() {
		try {
			ResultSet rs = conn.getRS(
					"select * from tb_class where className='" + String.valueOf(comboBox_2.getSelectedItem()) + "' ");
			while (rs.next()) {
				sclass = rs.getString("className");
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//修改
	public void updatestu() {
		try {
			if (0 < conn.getUpdate("update tb_student set stuNumber = '" + number + "', stuName = '" + name
					+ "', stuDepart = '" + depart + "', stuSpec = '" + sspec + "', stuClass = '" + sclass
					+ "' where stuNumber = '" + find + "'")) {
				JOptionPane.showMessageDialog(null, "学生信息修改成功！", "提 示",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				//System.err.printf("修改 tb_student 表中 stuNumber = %d 的记录失败\n", number);
				JOptionPane.showMessageDialog(null, "学生信息修改失败！", "提 示",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception ce) {
			System.out.println(ce.getMessage());
		}
	}
	
	// 显示
	public void showstu() {
		if (find < 0) {
			return;
		} else {
			try {
				ResultSet rs = conn.getRS("select * from tb_student where stuNumber='" + find + "' ");
				while (rs.next()) {
					number = rs.getString(2);
					name = rs.getString(3);
					depart = rs.getString(4);
					sspec = rs.getString(5);
					sclass = rs.getString(6);
				}
			} catch (Exception ce) {
				System.out.println(ce);
			}
			textField.setText(number);
			textField_1.setText(name);
			comboBox.setSelectedItem(depart);
			comboBox_1.addItem(sspec);
			comboBox_2.addItem(sclass);
		}
	}
	
}
