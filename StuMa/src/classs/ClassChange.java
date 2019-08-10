package classs;

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
import javax.swing.DefaultComboBoxModel;

/**
 * Title:�༶��Ϣ�޸�
 * @author moon
 *
 */

public class ClassChange extends JFrame {

	private JPanel contentPane;
	private JTextField textField = new JTextField();;
	private JComboBox comboBox = new JComboBox();
	private JComboBox comboBox_1 = new JComboBox();
	
	DbC dbC = new DbC();
	
	String find;
	String zhuanye,xueyuan;
	
	
	public ClassChange(String find) {
		this.find = find;
		init();
	}

	//��ʼ��
	public void init() {
		setTitle("�༶��Ϣ�޸�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("��   ��   ��   Ϣ   ��   ��");
		label.setFont(new Font("����", Font.BOLD, 22));
		label.setBounds(87, 10, 341, 32);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("�༶���ƣ�");
		label_1.setFont(new Font("����", Font.PLAIN, 20));
		label_1.setBounds(87, 71, 110, 24);
		contentPane.add(label_1);
		
		
		textField.setFont(new Font("����", Font.PLAIN, 20));
		textField.setBounds(213, 66, 162, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("����ѧԺ��");
		label_2.setFont(new Font("����", Font.PLAIN, 20));
		label_2.setBounds(87, 114, 100, 24);
		contentPane.add(label_2);
		comboBox.setFont(new Font("����", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u8BF7\u9009\u62E9\u5B66\u9662"}));
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zhuanye();
				comboBox_1.setEnabled(true);
			}
		});
		
		comboBox.setBounds(212, 112, 165, 32);
		contentPane.add(comboBox);
		
		JLabel label_3 = new JLabel("��ѡרҵ��");
		label_3.setFont(new Font("����", Font.PLAIN, 20));
		label_3.setBounds(84, 162, 104, 22);
		contentPane.add(label_3);
		comboBox_1.setFont(new Font("����", Font.PLAIN, 20));
		
		comboBox_1.setBounds(213, 156, 165, 30);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("�޸�");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// �쳣�ж�
				if (textField.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "�༶���Ʋ���Ϊ�ա�", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				} else if (comboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "��ѡ������ѧԺ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				} else if (comboBox_1.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "��ѡ������רҵ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					xueYuan();
					zhuanYe();
					try {
						// ������ͬһ��רҵ������һ���İ༶
						boolean classname = false;
						ResultSet rs = dbC.getRS("select className from tb_class where specName='"
								+ String.valueOf(comboBox_1.getSelectedItem()) + "'");
						while (rs.next()) {
							if (textField.getText().trim().equals(rs.getString("className").trim())) {
								classname = true;
							}
						}
						
						if (classname) {
							JOptionPane.showMessageDialog(null, "�ð༶�����Ѿ����ڣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
						} else {
							dbC.getUpdate(
									"update tb_class set className='" + textField.getText().trim() + "',specName='" + zhuanye
											+ "', departName='" + xueyuan + "' where classId='" + Integer.valueOf(find) + "'");
							JOptionPane.showMessageDialog(null, "��ϲ���༶��Ϣ�޸ĳɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
						}
					} catch (Exception ce) {
						System.out.println(ce);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton.setBounds(87, 221, 93, 31);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("�˳�");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBounds(282, 221, 93, 31);
		contentPane.add(button);
		
		comboBox.addItem("��ѡ��ѧԺ");
		// ѡ��ѧԺ
		try {
			ResultSet rs = dbC.getRS("select * from tb_depart");
			while (rs.next()) {
				String xi = rs.getString("departName");
				comboBox.addItem(xi);
			}
		} catch (Exception ce) {
			System.out.println("++++++++" + ce);
		}
		
		comboBox_1.setEnabled(false);
		
		xianshi();
		
	}


	// ѡ���к����޸Ľ�����ʾѡ��Ĭ��ֵ
	private void xianshi() {
		if (find == null)
			return;
		else {
			comboBox_1.setEnabled(true);
			try {
				ResultSet rs = dbC.getRS("select * from tb_class where classId='" + Integer.valueOf(find) + "'");
				while (rs.next()) {
					comboBox.setSelectedItem(String.valueOf(rs.getString("departName")));
					comboBox_1.setSelectedItem(String.valueOf(rs.getString("specName")));
					textField.setText(rs.getString("className").trim());
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
		comboBox_1.addItem("��ѡ��רҵ");
		try {

			ResultSet rs = dbC.getRS(
					"select * from tb_spec where departName='" + String.valueOf(comboBox.getSelectedItem()) + "' ");
			while (rs.next()) {
				String zhuan = rs.getString("specName");
				comboBox_1.addItem(zhuan);
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
		String sel = String.valueOf(comboBox_1.getSelectedItem());

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
