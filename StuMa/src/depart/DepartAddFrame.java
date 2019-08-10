package depart;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.DataBindingException;

import dataconnection.DbC;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

/**
 * Title:院系信息录入
 * Description:可实现对院系和专业的录入
 * @author moon
 *
 */

public class DepartAddFrame extends JFrame {

	private JPanel contentPane;
	JComboBox comboBox = new JComboBox();
	JComboBox comboBox_1 = new JComboBox();
	
	
	DbC dbC = new DbC();
	
	String zhuanye, xueyuan;
	
	
	public DepartAddFrame() {
		
		init();
	}
	
	
	public void init() {
		setTitle("院系录入");
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("院   系   信   息   录   入");
		label.setFont(new Font("宋体", Font.BOLD, 22));
		label.setBounds(47, 20, 342, 26);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("院系名称：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(60, 71, 109, 28);
		contentPane.add(label_1);
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				zhuanye();
				comboBox_1.setEnabled(true);
				
				
			}
		});
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setBounds(175, 70, 183, 33);
		comboBox.setEditable(true);
		contentPane.add(comboBox);
		
		JLabel label_2 = new JLabel("专业名称：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(57, 145, 102, 22);
		contentPane.add(label_2);
		
		
		comboBox_1.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox_1.setBounds(175, 136, 183, 35);
		comboBox_1.setEditable(true);
		contentPane.add(comboBox_1);
		
		JButton button = new JButton("提交");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// 异常判断
				if (comboBox_1.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请添加学院！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else if (comboBox_1.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "请添加专业！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					xueYuan();
					zhuanYe();
					try {
						// 对学院、专业分别存在与否的四种情况做判断处理
						boolean departExist = false, specExist = false;

						ResultSet rs_departExist = dbC.getRS("select * from tb_spec where departName= '"
								+ String.valueOf(comboBox_1.getSelectedItem()) + "' ");
						while (rs_departExist.next()) {
							departExist = true;
						}

						ResultSet rs_specExist = dbC.getRS("select * from tb_spec where specName= '"
								+ String.valueOf(comboBox_1.getSelectedItem()) + "' ");
						while (rs_specExist.next()) {
							specExist = true;
						}

						if (departExist && specExist) {
							JOptionPane.showMessageDialog(null, "该学院与专业已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
						} else if (!departExist && specExist) {
							JOptionPane.showMessageDialog(null, "该专业在其他学院已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
						} else if (departExist && !specExist) {
							dbC.getUpdate("insert into tb_spec (departName,specName) values ('"
									+ String.valueOf(comboBox.getSelectedItem()) + "','"
									+ String.valueOf(comboBox_1.getSelectedItem()) + "')");
							JOptionPane.showMessageDialog(null, "恭喜您院系录入成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
						} else if (!departExist && !specExist) {
									dbC.getUpdate("insert into tb_spec (departName,specName) values ('"
									+ String.valueOf(comboBox.getSelectedItem()) + "','"
									+ String.valueOf(comboBox_1.getSelectedItem()) + "')");
							dbC.getUpdate("insert into tb_depart (departName) values ('"
									+ String.valueOf(comboBox.getSelectedItem()) + "') ");
							JOptionPane.showMessageDialog(null, "恭喜您院系录入成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
						}

					} catch (Exception ce) {
						System.out.println(ce);
					}
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(70, 206, 93, 29);
		contentPane.add(button);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(269, 207, 93, 29);
		contentPane.add(button_1);
		
		comboBox.addItem("已有学院");
		
		//让comboBox中显示已有的学院
		try {
			
			ResultSet rSet = dbC.getRS("select * from tb_depart");
			while(rSet.next()) {
				String depart = rSet.getString("departName");
				comboBox.addItem(depart);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		comboBox_1.setEnabled(false);
		
	}
	
	
	//comboBox的触发，让在comboBox_1上显示comboBox对应的专业
	public void zhuanye() {
		comboBox_1.removeAllItems();
		comboBox_1.addItem("已有专业");
		
		try {

			ResultSet rs = dbC.getRS(
					"select * from tb_spec where departName='" + String.valueOf(comboBox.getSelectedItem()) + "' ");
			while (rs.next()) {
				String zhy = rs.getString("specName");
				comboBox_1.addItem(zhy);
			}

		} catch (Exception ce) {
			System.out.println("++++++++" + ce);
		}
	}
	
	// 存下选定学院到xueyuan
		public void xueYuan() {
			String sel = String.valueOf(comboBox.getSelectedItem());

			try {

				ResultSet rs = dbC.getRS("select * from tb_depart where departName='" + sel + "'");
				while (rs.next()) {
					xueyuan = rs.getString("departName");

				}

			} catch (Exception ce) {
				System.out.println("++++++++" + ce);
			}

		}

		// 存下选定专业到zhuanye,在提交按钮的监听中会用到
		public void zhuanYe() {
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
