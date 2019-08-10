package classs;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import java.awt.Color;


import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import dataconnection.DbC;
import manage.MainFrame;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.IllegalFormatCodePointException;
import java.util.Vector;
import java.awt.event.ActionEvent;

/**
 * Title:�༶��Ϣ����
 * Description: ʵ�ְ༶����ģ�飬�����༶��ѯ�Ͱ༶ɾ����ͬʱҲ�ǰ༶�����޸ĵ���ڡ�
 * @author moon
 *
 */
public class ClassManager extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	
	String[] arrField = {"�༶���", "ѧԺ����", "רҵ����", "�༶����"};
	Object[][] arrData = {};
	
	
	DbC dbC = new DbC();
	
	String sql,find;
	int row;
	
	ButtonGroup buttonGroup = new ButtonGroup();
	
	
	public ClassManager() {
		
		init();
		
	}

	/*
	 * ��ʼ������
	 */

	private void init() {
		setTitle("�༶��Ϣ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("��   ��   ��   Ϣ   ��   ��");
		label.setFont(new Font("����", Font.BOLD, 24));
		label.setBounds(125, 25, 379, 26);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("��ѧԺ��ѯ��");
		label_1.setFont(new Font("����", Font.PLAIN, 20));
		label_1.setBounds(56, 66, 120, 34);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("��ѡ��ѧԺ��");
		label_2.setFont(new Font("����", Font.PLAIN, 20));
		label_2.setBounds(184, 68, 125, 31);
		contentPane.add(label_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("����", Font.PLAIN, 20));
		comboBox.setBounds(319, 66, 185, 34);
		contentPane.add(comboBox);
		
		//��ѧԺ��ѯ�İ�ť
		JButton button = new JButton("��ѯ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				sql = "select * from tb_class "
						+ "where departName='" + String.valueOf(comboBox.getSelectedItem()) + "'";
				UpdateRecord();
				
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBounds(535, 66, 93, 34);
		contentPane.add(button);
		
		JLabel label_3 = new JLabel("���༶��ѯ��");
		label_3.setFont(new Font("����", Font.PLAIN, 20));
		label_3.setBounds(54, 118, 120, 27);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("������༶��");
		label_4.setFont(new Font("����", Font.PLAIN, 20));
		label_4.setBounds(184, 118, 124, 26);
		contentPane.add(label_4);
		
		textField = new JTextField();
		textField.setFont(new Font("����", Font.PLAIN, 20));
		textField.setBounds(319, 114, 185, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//���༶��ѯ�İ�ť
		JButton button_1 = new JButton("��ѯ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				sql = "select * from tb_class "
						+ "where className='" + textField.getText() + "'";
				UpdateRecord();
				
			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 20));
		button_1.setBounds(535, 114, 93, 35);
		contentPane.add(button_1);
		
		//��ѧԺ��ѯ�ĵ�ѡ��ť
		JRadioButton radioButton = new JRadioButton();
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				comboBox.setEnabled(true);
				button.setEnabled(true);
				
			}
		});
		radioButton.setBounds(29, 66, 21, 23);
		contentPane.add(radioButton);
		
		//���༶��ѯ�ĵ�ѡ��ť
		JRadioButton radioButton_1 = new JRadioButton();
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textField.setEnabled(true);
				button_1.setEnabled(true);
				
			}
		});
		radioButton_1.setBounds(27, 118, 21, 23);
		contentPane.add(radioButton_1);
		
		//���뵽ButtonGroup�е�JRdioButtonֻ��һ�����Ա�ѡ��
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton);
		buttonGroup.add(radioButton_1);
		
		
		JButton button_2 = new JButton("�޸�");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getM();
				//System.out.println(row);
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵİ༶��", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					ClassChange classChange = new ClassChange(find);
					classChange.setVisible(true);
					classChange.setResizable(false);
				}
			}
		});
		button_2.setFont(new Font("����", Font.PLAIN, 20));
		button_2.setBounds(41, 496, 93, 26);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("ɾ��");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				getM();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���İ༶��", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				} 
				
				try {
					dbC.getUpdate("delete from tb_class "
							+ "where classId='" + Integer.valueOf(find) + "'");
					JOptionPane.showMessageDialog(null, "ɾ���ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				
				UpdateRecord();
		
			}
		});
		button_3.setFont(new Font("����", Font.PLAIN, 20));
		button_3.setBounds(287, 496, 93, 26);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("�˳�");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_4.setFont(new Font("����", Font.PLAIN, 20));
		button_4.setBounds(514, 496, 93, 26);
		contentPane.add(button_4);
		
		comboBox.addItem("��ѡ��ѧԺ");
		
		
		//�û��ȼ��ж�
		//1Ϊ����Ա,2Ϊ��ͨ�û�
		if(MainFrame.level.equals("1")) {
			//�������κι���
		} else if (MainFrame.level.equals("2")) {
			//�����޸ĺ�ɾ������
			button_2.setVisible(false);
			button_3.setVisible(false);
		}
		
		//ѡ��ѧԺ
		try {
			
			ResultSet rSet = dbC.getRS("select * from tb_depart");
			
			while(rSet.next()) {
				String xi = rSet.getString("departName");
				comboBox.addItem(xi);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("++++++++" + e);
		}
		
		comboBox.setEnabled(false);
		button.setEnabled(false);
		textField.setEnabled(false);
		button_1.setEnabled(false);
		
		sql = "select * from tb_class";
		UpdateRecord();
		
		
	}
	

	//��ȡѡ������
	protected void getM() {
		row = table.getSelectedRow();
		if(row == -1) {
			return;
		}
		try {
			
			/*
			 * getValueAt(int row, int column) 
			 *  ���� row �� column λ�õĵ�Ԫ��ֵ��
			 */
			
			find = model.getValueAt(row, 0).toString().trim();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//���±��
	public void UpdateRecord() {
		
		//��֪�������м��У�����������Vector
		//��1��ʼ,��ֵΪ1
		Vector vec = new Vector(1, 1);
		model = new DefaultTableModel(arrData, arrField);
		table = new JTable();
		table.setModel(model);
		scrollPane = new JScrollPane(table);
		
        scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 255, 255), new Color(0, 255, 255), new Color(0, 255, 255), new Color(0, 255, 255)));
        scrollPane.setBounds(22, 165, 606, 311);
        
		table.setRowHeight(30);
		table.setFont(new Font("SimSun", Font.PLAIN, 12));
		
		 // ���ñ��������ɫ
        table.setForeground(Color.BLACK);                   // ������ɫ
        table.setSelectionForeground(Color.DARK_GRAY);      // ѡ�к�������ɫ
        table.setSelectionBackground(Color.LIGHT_GRAY);     // ѡ�к����屳��

		 // ���ñ�ͷ
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // ���ñ�ͷ����������ʽ
        table.getTableHeader().setForeground(Color.RED);                // ���ñ�ͷ����������ɫ
        table.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
        table.getTableHeader().setReorderingAllowed(false);             // ���ò������϶������������
		
        try {
			
        	ResultSet resultSet = dbC.getRS(sql);
        	while(resultSet.next()) {
        		vec = new Vector();
        		vec.add(String.valueOf(resultSet.getInt("classId")));
        		vec.add(resultSet.getString("departName").trim());
        		vec.add(resultSet.getString("specName"));
        		vec.add(resultSet.getString("className"));
        		model.addRow(vec);
        	}
        	resultSet.close();	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
        //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        table.setGridColor(Color.blue);
        table.setDragEnabled(true);
        table.setSelectionForeground(Color.red);
        table.setSelectionBackground(Color.green);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setShowVerticalLines(true);
		contentPane.add(scrollPane);
		
	}
	
}
