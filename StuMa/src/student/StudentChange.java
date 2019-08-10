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
		//JFrame.setTitle("ѧ����Ϣ�޸�");
		//JFrame.getContentPane().setFont(new Font("����", Font.PLAIN, 20));
		//JFrame.setBounds(100, 100, 568, 355);
		//JFrame.setVisible(true);
		//JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ѧ   ��   ��   Ϣ   ��   ��");
		label.setFont(new Font("����", Font.BOLD, 24));
		label.setBounds(110, 21, 372, 26);
		contentPane.add(label);
		
		
		JLabel label_1 = new JLabel("ѧ��:");
		label_1.setFont(new Font("����", Font.PLAIN, 22));
		label_1.setBounds(43, 72, 66, 33);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("������");
		label_2.setFont(new Font("����", Font.PLAIN, 20));
		label_2.setBounds(43, 123, 66, 26);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setFont(new Font("����", Font.PLAIN, 20));
		textField.setBounds(110, 76, 184, 26);
		textField.setColumns(10);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("����", Font.PLAIN, 20));
		textField_1.setBounds(110, 120, 184, 26);
		textField_1.setColumns(10);
		contentPane.add(textField_1);
		
		JLabel label_3 = new JLabel("����ѧԺ��");
		label_3.setFont(new Font("����", Font.PLAIN, 20));
		label_3.setBounds(43, 169, 100, 26);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("����רҵ��");
		label_4.setFont(new Font("����", Font.PLAIN, 20));
		label_4.setBounds(282, 169, 100, 26);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("�����༶��");
		label_5.setFont(new Font("����", Font.PLAIN, 20));
		label_5.setBounds(43, 216, 100, 24);
		contentPane.add(label_5);
		
		button = new JButton("�ύ");
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBounds(88, 264, 93, 23);
		contentPane.add(button);
		
		button_1 = new JButton("�˳�");
		button_1.setFont(new Font("����", Font.PLAIN, 20));
		button_1.setBounds(327, 264, 93, 23);
		contentPane.add(button_1);
		
		comboBox.setFont(new Font("����", Font.PLAIN, 20));
		comboBox.setBounds(139, 169, 127, 26);
		contentPane.add(comboBox);
		
		comboBox_1.setEnabled(false);
		comboBox_1.setFont(new Font("����", Font.PLAIN, 20));
		comboBox_1.setBounds(375, 169, 125, 26);
		contentPane.add(comboBox_1);
		
		comboBox_2 .setEnabled(false);
		comboBox_2.setFont(new Font("����", Font.PLAIN, 20));
		comboBox_2.setBounds(139, 215, 127, 26);
		contentPane.add(comboBox_2);
		
		this.comboBox.addItem("��ѡ��ѧԺ");
		
		//ѡ��ѧԺ
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
		
		
		//�ύ��ť�ļ���
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �쳣�ж�
				if (textField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "������ѧ��ѧ�ţ�", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else if (textField_1.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "������ѧ��������", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else if (comboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��ѧ������ѧԺ��", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else if (comboBox_1.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��ѧ������רҵ��", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else if (comboBox_2.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��ѧ�����ڰ༶��", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						
						findDepart();
						findSpec();
						findClass();
						
						if (find == Integer.parseInt(textField.getText().trim())) {
							// ���޸�ѧ�ŵ����
							number = textField.getText().trim();
							name = textField_1.getText().trim();
							updatestu();
						} 
						else{
							//�޸�ѧ�ŵ����
							boolean Num = false;
							ResultSet rs = conn.getRS("select * from tb_student");
							while (rs.next()) {
								if (textField.getText().trim().equals(rs.getString("stuNumber").trim())) {
									Num = true;
								}
							}
							rs.close();
							if (Num) {
								//�ж�ѧ���Ƿ����
									JOptionPane.showMessageDialog(null, "ѧ��ѧ���Ѵ��ڣ����������룡", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
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
	
		//�˳���ť�ļ���
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	//ѡ��ѧԺ����ѡ��רҵ
	public void jcb() {
		comboBox_1.removeAllItems();
		comboBox_1.addItem("��ѡ��רҵ");
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
		
	//ѡ��רҵ����ѡ��༶
	public void classAdd() {
		comboBox_2.removeAllItems();
		comboBox_2.addItem("��ѡ��༶");
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
	
	
	
	//��ѯѧԺ
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

	// ��ѯרҵ
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

	// ��ѯ�༶
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

	//�޸�
	public void updatestu() {
		try {
			if (0 < conn.getUpdate("update tb_student set stuNumber = '" + number + "', stuName = '" + name
					+ "', stuDepart = '" + depart + "', stuSpec = '" + sspec + "', stuClass = '" + sclass
					+ "' where stuNumber = '" + find + "'")) {
				JOptionPane.showMessageDialog(null, "ѧ����Ϣ�޸ĳɹ���", "�� ʾ",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				//System.err.printf("�޸� tb_student ���� stuNumber = %d �ļ�¼ʧ��\n", number);
				JOptionPane.showMessageDialog(null, "ѧ����Ϣ�޸�ʧ�ܣ�", "�� ʾ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception ce) {
			System.out.println(ce.getMessage());
		}
	}
	
	// ��ʾ
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
