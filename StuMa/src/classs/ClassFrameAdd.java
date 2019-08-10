package classs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataconnection.DbC;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Description: 添加班级的模块
 * @author moon
 *
 */
public class ClassFrameAdd extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	private JComboBox comboBox = new JComboBox();
	private JComboBox comboBox_1 = new JComboBox();
	
	DbC dbC = new DbC();
	String xueyuan,zhuanye;
	
	public ClassFrameAdd() {
		setTitle("班级信息录入");
		try {
			init();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/*
	 * 在窗口中添加面板以及所需组件，对按钮实现监听
	 */
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("班   级   信   息   录   入");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(121, 10, 342, 34);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("班级名称：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(51, 48, 100, 28);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("所属学院：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(54, 98, 115, 28);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("所选专业：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(53, 147, 113, 22);
		contentPane.add(label_3);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBounds(154, 51, 178, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//选择学院之后可触发选择所选学院的相应专业
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			zhuanye();
			comboBox_1.setEnabled(true);
				
			}
		});
		
		
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setBounds(156, 99, 180, 29);
		contentPane.add(comboBox);
		
		
		comboBox_1.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox_1.setBounds(157, 144, 182, 29);
		contentPane.add(comboBox_1);
		
		JButton button = new JButton("提交");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				//异常判断
				if(textField.getText().trim().length() == 0 ) {
					JOptionPane.showMessageDialog(null,"班级名称不能为空! ", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else if (comboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择所属学院！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else if (comboBox_1.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请选择所属专业！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					xue();
					zhuan();
					
					try {
						boolean className = false;
						ResultSet rSet = dbC.getRS("select className from tb_class where specName= '"
								+ String.valueOf(comboBox_1.getSelectedItem()) + "' ");
						while(rSet.next()) {
							if(textField.getText().trim().equals(rSet.getString("className").trim())) {
								className = true;
							}
						}
						
						
						if(className == true) {
							//添加的班级已存在
							JOptionPane.showMessageDialog(null, "该班级名称已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
						} else {
							dbC.getUpdate("insert into tb_class (className,specName, departName) values ('"
									+ textField.getText().trim() + "','" + zhuanye + "', '" + xueyuan + "')");
							JOptionPane.showMessageDialog(null, "恭喜您班级信息录入成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e);
					}
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(53, 209, 93, 30);
		contentPane.add(button);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(278, 211, 93, 30);
		contentPane.add(button_1);
		
		comboBox.addItem("请选择学院");
		
		//选择数据库中有的学院
		try {
			//调用DbC中的getRs方法查询数据库中的学院
			ResultSet rSet = dbC.getRS("select * from tb_depart");
			while(rSet.next()) {
				String xi = rSet.getString("departName");
				comboBox.addItem(xi);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("++++++++" + e);
		}
		comboBox_1.setEnabled(false);
		
	}

	//根据所选的学院来选择相应的专业
	public void zhuanye() {
		
		comboBox_1.removeAllItems();
		comboBox_1.addItem("请选择专业");
		try {
		
			ResultSet rSet = dbC.getRS("select * from tb_spec"
					+ " where departName='" + String.valueOf(comboBox.getSelectedItem()) + "'");
			while(rSet.next()) {
				String zhuan = rSet.getString("specName");
				comboBox_1.addItem(zhuan);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("++++++++" + e);
		}
	}

	//存下选定的学院
	public void xue() {
		String sel = String.valueOf(comboBox.getSelectedItem());
		try {
			ResultSet resultSet = dbC.getRS("select * from tb_depart where departName='" + sel + "'");
			while (resultSet.next()) {
				xueyuan = resultSet.getString("departName");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("++++++++" + e);
		}
	}
	
	
	//存下选定的专业
	public void zhuan() {
		String sel = String.valueOf(comboBox_1.getSelectedItem());
		try {
			ResultSet rs = dbC.getRS("select * from tb_spec where specName='" + sel + "'");
			while (rs.next()) {
				zhuanye = rs.getString("specName");
			}
		} catch (Exception ce) {
			System.out.println("++++++++" + ce);
		}
	}
	
}
