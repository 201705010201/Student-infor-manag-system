package depart;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
 * Title��ѧԺ�޸�
 * @author moon
 *
 */
public class DepartChange extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox = new JComboBox();;
	private JComboBox comboBox_1 = new JComboBox();;
	
	
	DbC dbC = new DbC();
	
	String find;
	String zhuanye, xueyuan;
	
	

	public DepartChange(String find) {
		this.find = find;
		init();
	}
	
	public void init() {
		setTitle("Ժϵ�޸�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Ժ  ϵ  ��  ��");
		label.setFont(new Font("����", Font.BOLD, 18));
		label.setBounds(139, 10, 163, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("ѧԺ���ƣ�");
		label_1.setFont(new Font("����", Font.PLAIN, 18));
		label_1.setBounds(65, 46, 95, 25);
		contentPane.add(label_1);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zhuanye();
				comboBox_1.setEnabled(true);
			}
		});
		
		
		comboBox.setEditable(true);
		comboBox.setFont(new Font("����", Font.PLAIN, 18));
		comboBox.setBounds(172, 42, 167, 33);
		contentPane.add(comboBox);
		
		JLabel label_2 = new JLabel("רҵ���ƣ�");
		label_2.setFont(new Font("����", Font.PLAIN, 18));
		label_2.setBounds(61, 104, 102, 24);
		contentPane.add(label_2);
		
	
		comboBox_1.setEditable(true);
		comboBox_1.setFont(new Font("����", Font.PLAIN, 18));
		comboBox_1.setBounds(172, 104, 170, 30);
		contentPane.add(comboBox_1);
		
		JButton button = new JButton("�޸�");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// �쳣�ж�
				if (comboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "��ѡ������ѧԺ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				} else if (comboBox_1.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "��ѡ������רҵ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					xueYuan();
					zhuanYe();
					try {
						// ��ѧԺ��רҵ�ֱ������������������жϴ���
						boolean departExist = false, specExist = false;

						ResultSet rs_departExist = dbC.getRS("select * from tb_spec where departName= '"
								+ String.valueOf(comboBox.getSelectedItem()) + "' ");
						while (rs_departExist.next()) {
							departExist = true;
						}

						ResultSet rs_specExist = dbC.getRS("select * from tb_spec where specName= '"
								+ String.valueOf(comboBox_1.getSelectedItem()) + "' ");
						while (rs_specExist.next()) {
							specExist = true;
						}

						if (departExist && specExist) {
							JOptionPane.showMessageDialog(null, "��ѧԺ��רҵ�Ѿ����ڣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
						} else if (!departExist && specExist) {
							JOptionPane.showMessageDialog(null, "��רҵ������ѧԺ�Ѿ����ڣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
						} else if (departExist && !specExist) {
							dbC.getUpdate("delete from tb_spec where specId='" + Integer.valueOf(find) + "'");
							dbC.getUpdate("insert into tb_spec (departName,specName) values ('"
									+ String.valueOf(comboBox.getSelectedItem()) + "','"
									+ String.valueOf(comboBox_1.getSelectedItem()) + "')");
							JOptionPane.showMessageDialog(null, "��ϲ��Ժϵ��Ϣ�޸ĳɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
						} else if (!departExist && !specExist) {
							dbC.getUpdate("delete from tb_spec where specId='" + Integer.valueOf(find) + "'");
							dbC.getUpdate("insert into tb_spec (departName,specName) values ('"
									+ String.valueOf(comboBox.getSelectedItem()) + "','"
									+ String.valueOf(comboBox_1.getSelectedItem()) + "')");
							dbC.getUpdate("insert into tb_depart (departName) values ('"
									+ String.valueOf(comboBox.getSelectedItem()) + "') ");
							JOptionPane.showMessageDialog(null, "��ϲ��Ժϵ��Ϣ�޸ĳɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
						}
					} catch (Exception ce) {
						System.out.println(ce);
					}
				}
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBounds(71, 172, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("�˳�");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 20));
		button_1.setBounds(250, 171, 93, 23);
		contentPane.add(button_1);
		
		comboBox.addItem("����ѧԺ");
		
		// ѡ��ѧԺ
		try {
			ResultSet rs = dbC.getRS("select * from tb_depart ");
			while (rs.next()) {
				String xibu = rs.getString("departName");
				comboBox.addItem(xibu);
			}

		} catch (Exception ce) {
			System.out.println("++++++++" + ce);
		}
		comboBox_1.setEnabled(false);
		xianshi();
		
	}
	
	// ѡ���к����޸Ľ�����ʾѡ��Ĭ��ֵ
	public void xianshi() {
		if (find == null)
			return;
		else {
			comboBox_1.setEnabled(true);
			try {
				ResultSet rs = dbC.getRS("select * from tb_spec where specId='" + Integer.valueOf(find) + "'");
				while (rs.next()) {
					comboBox.setSelectedItem(String.valueOf(rs.getString("departName")));
					comboBox_1.setSelectedItem(String.valueOf(rs.getString("specName")));
				}
				rs.close();
			} catch (Exception ce) {
				System.out.println("++++++++" + ce);
			}
		}
	}

	// ѡ��רҵ
	public void zhuanye() {
		comboBox_1.removeAllItems();
		comboBox_1.addItem("����רҵ");
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

	// ����ѡ��ѧԺ��xueyuan
	public void xueYuan() {
		String sel = String.valueOf(comboBox.getSelectedItem());
		try {
			ResultSet rs = dbC.getRS("select * from tb_depart where departName='" + sel + "'");
			while (rs.next()) {
				xueyuan = rs.getString("departName");
			}
			rs.close();
		} catch (Exception ce) {
			System.out.println("++++++++" + ce);
		}
	}

	// ����ѡ��רҵ��zhuanye
	public void zhuanYe() {
		String sel = String.valueOf(comboBox_1	.getSelectedItem());
		try {
			ResultSet rs = dbC.getRS("select * from tb_spec where specName='" + sel + "'");
			while (rs.next()) {
				zhuanye = rs.getString("specName");
			}
			rs.close();
		} catch (Exception ce) {
			System.out.println("++++++++" + ce);
		}
	}

	
	
	
	
	
	
	

}
