package student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.RowData;

import dataconnection.DbC;
import manage.MainFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;


import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class StudentManager extends JFrame {

	JPanel contentPane;
	JTable table = new JTable();
	JScrollPane jScrollPane = new JScrollPane();
	String[] arrField = {"ѧ��", "����", "ѧԺ", "רҵ", "�༶"};
	Object[][] arrData = {};
	DefaultTableModel model = new DefaultTableModel();
	int intRow;
	static int find;
	String sql;
	
	JOptionPane jOptionPane = new JOptionPane();
	JTextField textField1 = new JTextField();
	JTextField textField2 = new JTextField();
	
	JButton jButton3 = new JButton();
	JButton jButton4 = new JButton();
	JButton button = new JButton();//�޸İ�ť
	JButton button_1 = new JButton();//ɾ����ť
	
	DbC conn = new DbC();
	
	public StudentManager() {
			Init();
	}
	 
	public void Init() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setName("ѧ����Ϣ����");
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("ѧ   ��   ��   Ϣ   ��   ��");
		label.setBounds(175, 10, 420, 69);
		label.setFont(new Font("����", Font.BOLD, 28));
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("��ѡ���ѯ��ʽ��");
		label_1.setBounds(194, 81, 188, 38);
		label_1.setFont(new Font("����", Font.PLAIN, 22));
		contentPane.add(label_1);
		
		JPanel jPanel1 = new JPanel();
		jPanel1.setBounds(23, 117, 716, 48);
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel jLabel3 = new JLabel("������ѧ�ţ� ");
		jLabel3.setBounds(166, 131, 155, 24);
		jLabel3.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JTextField textField1 = new JTextField();
		textField1.setFont(new Font("����", Font.PLAIN, 20));
		textField1.setBounds(329, 133, 140, 22);
		textField1.setColumns(10);
		
		//ѧ�ŵĲ�ѯ��ť
		jButton3 = new JButton("��ѯ");
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton3_addActionListener();
			}
			
			private void jButton3_addActionListener() {
				if (!textField1.getText().trim().equals("")) {
					String sql = "select * from tb_student where stuNumber='" + textField1.getText().trim() + "'";
					update(sql);
				} else
					JOptionPane.showMessageDialog(null, "������Ҫ��ѯ��ѧ��ѧ�ţ�", "�� ʾ",JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		jButton3.setBounds(529, 134, 93, 23);
		jButton3.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		jPanel1.add(jLabel3);
		jPanel1.add(textField1);
		jPanel1.add(jButton3);
		contentPane.add(jPanel1);
		
		jPanel1.setVisible(false);
		
		JPanel jPanel2 = new JPanel();
		jPanel2.setBounds(23, 117, 716, 48);
		jPanel2.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel jLabel4 = new JLabel("������������ ");
		jLabel4.setBounds(166, 131, 155, 24);
		jLabel4.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		textField2 = new JTextField();
		textField2.setFont(new Font("����", Font.PLAIN, 20));
		textField2.setBounds(329, 133, 140, 22);
		textField2.setColumns(10);
		
		//�����Ĳ�ѯ��ť
		jButton4 = new JButton("��ѯ");
		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton4_addActionListener();
			}
			
			private void jButton4_addActionListener() {
				if (!textField2.getText().trim().equals("")) {
					String sql = "select * from tb_student where stuName='" + textField2.getText().trim() + "'";
					update(sql);
				} else
					JOptionPane.showMessageDialog(null, "������Ҫ��ѯ��ѧ��������", "�� ʾ",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		jButton4.setBounds(529, 134, 93, 23);
		jButton4.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		jPanel2.add(jLabel4);
		jPanel2.add(textField2);
		jPanel2.add(jButton4);
		contentPane.add(jPanel2);
		
		jPanel2.setVisible(false);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(427, 87, 137, 29);
		comboBox.setFont(new Font("����", Font.PLAIN, 22));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"��ѡ��", "��ѧ�Ų�ѯ", "��������ѯ"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Show();
			}
			private void Show() {
				jPanel1.setVisible(false);
				jPanel2.setVisible(false);
				if (comboBox.getSelectedIndex() == 1)
					jPanel1.setVisible(true);
				else if (comboBox.getSelectedIndex() == 2)
					jPanel2.setVisible(true);
			}
		});
		contentPane.add(comboBox);
		
		button = new JButton("�޸�");
		button.setBounds(163, 459, 93, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getM();
				if(intRow != -1) {
					StudentChange stuCh = new StudentChange(find);
					stuCh.setTitle("ѧ����Ϣ�޸�");
					stuCh.setBounds(100, 100, 568, 355);
					stuCh.setVisible(true);
					stuCh.setResizable(false);
				} else 
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���Ϣ��", "�� ʾ",JOptionPane.INFORMATION_MESSAGE);
			}
			//��ȡѡ������
			public void getM() {
				intRow = table.getSelectedRow();
				if (intRow == -1)
					return;
				try {
					find = Integer.parseInt(model.getValueAt(intRow, 0).toString());
					//System.err.println(find);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 22));
		contentPane.add(button);
		
		button_1 = new JButton("ɾ��");
		button_1.setBounds(345, 462, 93, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getM();
				if (intRow != -1) {
					delstu();
				} else
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������Ϣ��", "�� ʾ",
							JOptionPane.INFORMATION_MESSAGE);
			}

			private void getM() {
				intRow = table.getSelectedRow();
				if (intRow == -1)
					return;
				try {
					find = Integer.parseInt(model.getValueAt(intRow, 0).toString());
					//System.err.println(find);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}

			public void delstu() {
				try {
					if (0 < conn.getUpdate("delete from tb_student where stuNumber='" + find + "'")) {
						JOptionPane.showMessageDialog(null, "ѧ����Ϣɾ���ɹ���", "�� ʾ",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						System.err.printf("ɾ�� tb_student ���� stuNumber = %d �ļ�¼ʧ��\n", find);
						JOptionPane.showMessageDialog(null, "ѧ����Ϣɾ��ʧ�ܣ�", "�� ʾ",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception ce) {
					System.out.println(ce.getMessage());
				}
			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 22));
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("����");
		button_2.setBounds(511, 462, 93, 23);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_2.setFont(new Font("����", Font.PLAIN, 22));
		contentPane.add(button_2);
        
		//�û��ȼ��жϣ�1Ϊ����Ա��2Ϊ��ͨ�û�
		if(MainFrame.level.equals("1")) {
			//�������κι���
		}
		else if(MainFrame.level.equals("2")) {
			//��ͨ�û�û���޸��Լ�ɾ������
			button.setVisible(false);
			button_1.setVisible(false);
		}
		
		sql = "select * from tb_student";
		//������ʾ���е�����
		update(sql);
	}
	

	public void update(String sql) {
		//�趨�����ֶ�
		Object[][] arrTmp = {};
		//��1��ʼ,��ֵΪ1
		Vector vec = new Vector(1, 1);
		model = new DefaultTableModel(arrTmp, arrField);
		table = new JTable();
		table.setModel(model);
		jScrollPane = new JScrollPane(table);
		table.setRowHeight(30);
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 20));// ���ñ�ͷ����������ʽ
        table.getTableHeader().setForeground(Color.BLACK);// ���ñ�ͷ����������ɫ
        table.getTableHeader().setResizingAllowed(false);// ���ò������ֶ��ı��п�
        table.getTableHeader().setReorderingAllowed(false); // ���ò������϶������������*/
		
		try {
			ResultSet rs = conn.getRS(sql);
			while(rs.next()) {
				vec = new Vector();
				vec.add(rs.getString("stuNumber").trim());
				vec.add(rs.getString("stuName").trim());
				vec.add(rs.getString("stuDepart").trim());
				vec.add(rs.getString("stuSpec").trim());
				vec.add(rs.getString("stuClass").trim());
				model.addRow(vec);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
        jScrollPane.setBounds(23, 198, 716, 226);
        jScrollPane.setBorder(BorderFactory.createEtchedBorder());
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        table.setGridColor(Color.blue);
        table.setDragEnabled(true);
        table.setSelectionForeground(Color.red);
        table.setSelectionBackground(Color.green);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setShowVerticalLines(true);
		contentPane.add(jScrollPane);
	}
}
