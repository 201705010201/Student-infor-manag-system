package depart;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dataconnection.DbC;
import manage.MainFrame;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

/**
 * Title��Ժϵ��Ϣ����
 * @author moon
 *
 */

public class DepartManage extends JFrame {

	private JPanel contentPane;
	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	
	String [] columnName = {"רҵ���", "ѧԺ����" ,"רҵ����"};
	Object[][] rowdata = {};
	
	DbC dbC = new DbC();
	
	int row;
	String sql, find;
	
	
	public DepartManage() {
		
		init();
	}
	
	
	public void init() {
		setTitle("Ժϵ��Ϣ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		JLabel label = new JLabel("Ժ   ϵ   ��   Ϣ   ��   ��");
		label.setFont(new Font("����", Font.BOLD, 22));
		label.setBounds(74, 10, 346, 38);
		contentPane.add(label);
		
		JButton button = new JButton("�޸�");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				getM();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ�Ժϵ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					DepartChange dChange = new DepartChange(find);
					dChange.setVisible(true);					
					dChange.setResizable(false);
					dChange.setLocation(400,  200);
				}
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setBounds(24, 394, 93, 24);
		contentPane.add(button);
		
		
		
		JButton button_1 = new JButton("ɾ��");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				getM();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ����Ժϵ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				}
				try {
					dbC .getUpdate("delete from tb_spec where specId='" + Integer.valueOf(find) + "'");
					JOptionPane.showMessageDialog(null, "ɾ���ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				} catch (Exception ce) {
					System.out.println(ce.getMessage());
				}
				
				UpdateRecord();
				
			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 20));
		button_1.setBounds(199, 394, 93, 25);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("�˳�");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_2.setFont(new Font("����", Font.PLAIN, 20));
		button_2.setBounds(364, 394, 93, 25);
		contentPane.add(button_2);
		
		//�û��ȼ��ж�
		//1Ϊ����Ա,2Ϊ��ͨ�û�
		if(MainFrame.level.equals("1")) {
			//�������κι���
		} else if (MainFrame.level.equals("2")) {
			//�����޸ĺ�ɾ������
			button.setVisible(false);
			button_1.setVisible(false);
		}
		
		// ѡ��רҵ��
		sql = "select * from tb_spec";
		
		//���±��
		UpdateRecord();
		
	}


	//���±��
	public void UpdateRecord() {
		
		Vector vector = new Vector(1,1);
		
		model = new DefaultTableModel(rowdata, columnName);
		table = new JTable(model);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 58, 435, 312);
		table.setRowHeight(30);
	    table.setFont(new Font("����", Font.PLAIN, 12));
	    scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN));
		
        try {
			ResultSet rs = dbC.getRS(sql);
			while (rs.next()) {
				vector = new Vector();
				vector.add(String.valueOf(rs.getInt("specId")));
				vector.add(rs.getString("departName"));
				vector.add(rs.getString("specName"));
				model.addRow(vector);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
        table.setGridColor(Color.blue);
        table.setDragEnabled(true);
        table.setSelectionForeground(Color.red);
        table.setSelectionBackground(Color.green);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setShowVerticalLines(true);
        
        contentPane.add(scrollPane);
	}
	
	
	// ��ȡ��ѡ����
	public void getM() {
		
		row = table.getSelectedRow();

		if (row == -1) {
			return;
		}
		try {
			find = model.getValueAt(row, 0).toString().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
