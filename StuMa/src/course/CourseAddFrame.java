package course;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataconnection.DbC;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

/**
 * Title:课程录入模块
 * @author moon
 *
 */
public class CourseAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	DbC dbC = new DbC();
	

	public CourseAddFrame() {
		
		init();
	}
	
	
	public void init() {
		setFont(new Font("Dialog", Font.BOLD, 20));
		setTitle("课程信息录入");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("课  程  信  息  录  入");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(94, 10, 239, 21);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("课程名称：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(64, 48, 91, 25);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 18));
		textField.setBounds(171, 50, 185, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("所属专业：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(64, 106, 101, 21);
		contentPane.add(label_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setBounds(170, 101, 188, 30);
		contentPane.add(comboBox);
		
		JLabel label_3 = new JLabel("学  分：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(75, 152, 80, 21);
		contentPane.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setBounds(170, 148, 186, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("提交");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)  {
				// 异常判断
				if (textField.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "课程名称不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else if (comboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择课程所属专业！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else if (textField_1.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "课程学分不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					try {
						boolean name = false;
						ResultSet rs = dbC.getRS("select courceName from tb_cource");
						while (rs.next()) {
							if (textField.getText().trim().equals(rs.getString("courceName").trim())) {
								name = true;
							}
						}
						if (name) {
							JOptionPane.showMessageDialog(null, "课程名称已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
						} else {
							dbC.getUpdate("insert into tb_cource (courceName,courceHour,courceSpecName) values ('"
									+ textField.getText().trim() + "','" + Float.valueOf(textField_1.getText().trim()) + "','"
									+ String.valueOf(comboBox.getSelectedItem()) + "')");
							JOptionPane.showMessageDialog(null, "课程信息提交成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
						}
						rs.close();
					} catch (Exception ce) {
						System.out.println("--------" + ce);
					}
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
	}
	
}
