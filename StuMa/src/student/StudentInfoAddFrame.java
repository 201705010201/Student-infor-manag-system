package student;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataconnection.DbC;

/**
 * Title: ѧ����Ϣ¼��
 * @author ������
 *
 */

public class StudentInfoAddFrame extends JFrame {
	
	JPanel contentPane;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	JLabel jLabel6 = new JLabel();
	
	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	
	//�����б�
	JComboBox jComboBox1 = new JComboBox();
	JComboBox jComboBox2 = new JComboBox();
	JComboBox jComboBox3 = new JComboBox();
	
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	
	DbC conn = new DbC();//�������ݿ�
	
	JOptionPane jOptionPane1 = new JOptionPane();
	JOptionPane jOptionPane2 = new JOptionPane();
	JOptionPane jOptionPane_enter = new JOptionPane();
	
	String number, name, depart, sspec, sclass;
	
	public StudentInfoAddFrame() {
		Init();
		addActionListener2();
		addActionListener3();
	}

	private void Init() {
		contentPane = (JPanel) getContentPane();
		//contentPane = new JPanel();
		contentPane.setLayout(null);
		setSize(new Dimension(562,500));
		setVisible(true);
		setLocation(400, 200);
		//setResizable(false);
		setTitle("ѧ����Ϣ¼��");
		
		jLabel1.setText("ѧ  ��  ��  Ϣ  ¼  ��");
		jLabel1.setFont(new Font("Dialog", Font.BOLD, 23));
		jLabel1.setBounds(new Rectangle(196, 16, 232, 25));
		
		jLabel2.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel2.setText("ѧ       ��:");
		jLabel2.setBounds(new Rectangle(50, 74, 90, 22));
		
		jLabel3.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("��       ��:");
		jLabel3.setBounds(new Rectangle(50, 124, 90, 22));
		
		jLabel4.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("����ѧԺ:");
		jLabel4.setBounds(new Rectangle(50, 174, 90, 22));
		
		jLabel5.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel5.setText("����רҵ:");
		jLabel5.setBounds(new Rectangle(297, 174, 90, 22));
		
		jLabel6.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel6.setText("���ڰ༶��");
		jLabel6.setBounds(new Rectangle(50, 224, 93, 29));
		
		jTextField1.setFont(new Font("Dialog", Font.PLAIN, 16));
		jTextField1.setText("");
		jTextField1.setBounds(new Rectangle(150, 74, 195, 23));
		
		jTextField2.setFont(new Font("Dialog", Font.PLAIN, 16));
		jTextField2.setText("");
		jTextField2.setBounds(new Rectangle(150, 124, 195, 23));
		
		jComboBox1.setFont(new Font("Dialog", Font.PLAIN, 16));
		jComboBox1.setBounds(new Rectangle(150, 174, 125, 25));
		
		jComboBox2.setEnabled(false);//���ÿؼ�������
		jComboBox2.setFont(new Font("Dialog", Font.PLAIN, 16));
		jComboBox2.setBounds(new Rectangle(390, 174, 125, 25));
		
		jComboBox3.setEnabled(false);//���ÿؼ�������
		jComboBox3.setFont(new Font("Dialog", Font.PLAIN, 16));
		jComboBox3.setBounds(new Rectangle(150, 224, 125, 25));
		
		jButton1.setText("��  ��");
		jButton1.setFont(new Font("Dialog", Font.PLAIN, 16));
		jButton1.setBounds(new Rectangle(147, 274, 90, 27));
		jButton2.setText("��  ��");
		jButton2.setFont(new Font("Dialog", Font.PLAIN, 16));
		jButton2.setBounds(new Rectangle(375, 274, 90, 27));
		
		contentPane.add(jLabel1);
		contentPane.add(jLabel2);
		contentPane.add(jLabel3);
		contentPane.add(jLabel4);
		contentPane.add(jLabel5);
		contentPane.add(jLabel6);
		
		contentPane.add(jTextField1);
		contentPane.add(jTextField2);
		
		contentPane.add(jComboBox1);
		contentPane.add(jComboBox2);
		contentPane.add(jComboBox3);
		
		contentPane.add(jButton1);
		contentPane.add(jButton2);
		contentPane.add(jOptionPane_enter);
		jComboBox1.addItem("��ѡ��ѧԺ");
		
		//ѡ��ѧԺ
		try {
			ResultSet rs = conn.getRS("select * from tb_depart");
			while(rs.next()) {
				String xueyuan = rs.getString("departName");
				jComboBox1.addItem(xueyuan);
			} 
		} catch(Exception ce) {
			//System.out.println(ce);
			ce.printStackTrace();
		}
		jComboBox2.setEnabled(false);
		jComboBox3.setEnabled(false);
	}
	
	public void addActionListener2() {
		jComboBox1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jComboBox2.setEnabled(true);
				jcb();
			}
		});
		
		jComboBox2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jComboBox3.setEnabled(true);
				classAdd();
			}
		});
	}
	
	//ѡ��ѧԺ����ѡ��רҵ
	public void jcb() {
		jComboBox2.removeAllItems();
		jComboBox2.addItem("��ѡ��רҵ");
		try {
			ResultSet rs = conn.getRS("select * from tb_spec where departName='" + String.valueOf(jComboBox1.getSelectedItem()) + "' ");
			while(rs.next()) {
				String zhuan = rs.getString("specName");
				jComboBox2.addItem(zhuan);
			}
			rs.close();
		} catch(Exception e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
		
	// ѡ��רҵ����ѡ��༶
	public void classAdd() {
		jComboBox3.removeAllItems();
		jComboBox3.addItem("��ѡ��༶");
		try {
			ResultSet rs = conn.getRS("select * from tb_class where specName='" + String.valueOf(jComboBox2.getSelectedItem()) + "' ");
			while (rs.next()) {
				String banji = rs.getString("className");
				jComboBox3.addItem(banji);
			}
			rs.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	
	public void addActionListener3() {
		//�ύ��ť�ļ���
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �쳣�ж�
				if (jTextField1.getText().trim().equals("")) {
					jOptionPane1.showMessageDialog(null, "������ѧ��ѧ�ţ�", "�� ʾ", jOptionPane1.INFORMATION_MESSAGE);
				} else if (jTextField2.getText().trim().equals("")) {
					jOptionPane1.showMessageDialog(null, "������ѧ��������", "�� ʾ", jOptionPane1.INFORMATION_MESSAGE);
				} else if (jComboBox1.getSelectedIndex() == 0) {
					jOptionPane1.showMessageDialog(null, "��ѡ��ѧ������ѧԺ��", "�� ʾ", jOptionPane1.INFORMATION_MESSAGE);
				} else if (jComboBox2.getSelectedIndex() == 0) {
					jOptionPane1.showMessageDialog(null, "��ѡ��ѧ������רҵ��", "�� ʾ", jOptionPane1.INFORMATION_MESSAGE);
				} else if (jComboBox3.getSelectedIndex() == 0) {
					jOptionPane1.showMessageDialog(null, "��ѡ��ѧ�����ڰ༶��", "�� ʾ", jOptionPane1.INFORMATION_MESSAGE);
				} else {
					try {
						//��ѯ��ѡ��ѧԺ�Ƿ����
						findDepart();
						//��ѯ��ѡ��רҵ�Ƿ����
						findSpec();
						//��ѯ��ѡ�İ༶�Ƿ����
						findClass();
						boolean Num = false;
						ResultSet rs = conn.getRS("select * from tb_student");
						while (rs.next()) {
							if (jTextField1.getText().trim().equals(rs.getString("stuNumber").trim())) {
								Num = true;
							}
						}
						rs.close();
						if (Num) {
							JOptionPane.showMessageDialog(null, "ѧ��ѧ���Ѵ��ڣ����������룡", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
						} else {
							number = jTextField1.getText().trim();
							name = jTextField2.getText().trim();
							instu();
						}
					} catch (Exception ce) {
						System.out.println(ce.getMessage());
					}
				}
				
			}
		});
		
		//�˳���ť�ļ���
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}
	
	
	
	//��ѯѧԺ
	public void findDepart() {
		try {
			ResultSet rs = conn.getRS(
					"select * from tb_depart where departName='" + String.valueOf(jComboBox1.getSelectedItem()) + "' ");
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
					"select * from tb_spec where specName='" + String.valueOf(jComboBox2.getSelectedItem()) + "' ");
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
					"select * from tb_class where className='" + String.valueOf(jComboBox3.getSelectedItem()) + "' ");
			while (rs.next()) {
				sclass = rs.getString("className");
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	// ѧ����Ϣ¼��
	public void instu() {
		try {
			if (0 < conn.getUpdate("insert into tb_student (stuNumber,stuName,stuDepart,stuSpec,stuClass) values ('"
					+ number + "','" + name + "','" + depart + "','" + sspec + "','" + sclass + "')")) {
				jOptionPane_enter.showMessageDialog(this, "ѧ����Ϣ¼��ɹ���", "�� ʾ",
						jOptionPane_enter.INFORMATION_MESSAGE);
			} else {
				//System.err.printf("�޸� tb_student ���� stuNumber = %d �ļ�¼ʧ��\n", number);
				jOptionPane_enter.showMessageDialog(this, "ѧ����Ϣ¼��ʧ�ܣ�", "�� ʾ",
						jOptionPane_enter.INFORMATION_MESSAGE);
			}
		} catch (Exception ce) {
			System.out.println(ce.getMessage());
		}
	}
	
}