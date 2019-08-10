package course;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import dataconnection.DbC;
import depart.DepartChange;
import manage.MainFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Title:�γ̹���ģ��
 * @author moon
 *
 */
public class CourseManage extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	
	String [] columnName = { "�γ̱��", "רҵ����", "�γ�����", "�γ�ѧ��"};
	Object[][] rowdata = {};
	
	DbC dbC = new DbC();
	
	int row;
	String sql;
	
	static int find;
	
	
	public CourseManage() {
		init();
	}
	
	
	public void init() {
		setTitle("�γ̹���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("��  ��  ��  ��");
		label.setFont(new Font("����", Font.BOLD, 18));
		label.setBounds(146, 10, 136, 25);
		contentPane.add(label);
		
		button = new JButton("�޸�");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		getM();
        		if (find > 0) {
        			CourseChange courseChange = new CourseChange(find);
        			courseChange.setVisible(true);
        			courseChange.setResizable(false);
        		}
        		
        	}
        });
        button.setFont(new Font("����", Font.PLAIN, 18));
        button.setBounds(41, 393, 93, 23);
        contentPane.add(button);
        
        button_1 = new JButton("ɾ��");
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		getM();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���Ŀγ̣�", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				}
				try {
					dbC .getUpdate("delete from tb_cource where courceId='\" + Integer.valueOf(find) + \"'");
					JOptionPane.showMessageDialog(null, "ɾ���ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				} catch (Exception ce) {
					System.out.println(ce.getMessage());
				}
				
				UpdateRecord();
        		
        		
        	}
        });
        button_1.setFont(new Font("����", Font.PLAIN, 18));
        button_1.setBounds(205, 393, 93, 23);
        contentPane.add(button_1);
        
        button_2 = new JButton("�˳�");
        button_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		dispose();
        	}
        });
        button_2.setFont(new Font("����", Font.PLAIN, 18));
        button_2.setBounds(347, 393, 93, 23);
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
		
		sql = "select * from tb_cource ";
		
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
				vector.add(String.valueOf(rs.getString("courceId")));
				vector.add(String.valueOf(rs.getString("courceSpecName")));
				vector.add(String.valueOf(rs.getString("courceName")));
				vector.add(String.valueOf(rs.getString("courceHour")));
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

	// ��ȡѡ������
	public void getM() {

		row = table.getSelectedRow();
		
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�޸ĵĿγ̣�", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		try {
			find = Integer.parseInt(model.getValueAt(row, 0).toString());
			System.out.println(find);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
