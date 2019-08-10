package score;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import score.ScoreChange;
import dataconnection.DbC;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Title:�ɼ�����ģ��
 * @author moon
 *
 */
public class ScoreManage extends JFrame {

	private JPanel contentPane;

	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	
	String [] columnName = {"�ɼ����", "ѧ��ѧ��", "����", "�γ�", "����"};
	Object[][] rowdata = {};
	
	DbC dbC = new DbC();
	
	String sql;
	
	int intRow;
	static int find;
	
	

	public ScoreManage() {
		Init();
	}
	
	
	public void Init() {
		setTitle("�ɼ�����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("��  ��  ��  ��");
		label.setFont(new Font("����", Font.BOLD, 18));
		label.setBounds(200, 10, 136, 26);
		contentPane.add(label);
		
		JButton button = new JButton("����");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//����ϰ�ߣ�ͨ���ǶԳɼ����н�������
				sql = "select * from tb_score order by score desc, stuNumber";
				UpdateRecord();			}
		});
		button.setFont(new Font("����", Font.PLAIN, 18));
		button.setBounds(38, 60, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("ƽ��");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float f_average = 0;
				int averageBig = 0, averageSmall = 0;
				try {
					// ���ƽ����
					ResultSet rs_average = dbC.getRS("select avg(score) as scoreAverage from tb_score");
					while (rs_average.next()) {
						f_average = Float.valueOf(rs_average.getString("scoreAverage"));
					}

					// ��ô��ڵ���ƽ���ֵ�����
					ResultSet rs_averageBig = dbC.getRS("select * from tb_score where score>='" + f_average + "'");
					while (rs_averageBig.next()) {
						averageBig++;
					}

					// ���С��ƽ���ֵ�����
					ResultSet rs_averageSmall = dbC.getRS("select * from tb_score where score<'" + f_average + "'");
					while (rs_averageSmall.next()) {
						averageSmall++;
					}

				} catch (SQLException e1) {
					System.out.println(e1);
				}

				// ��ʾƽ����
				JOptionPane.showMessageDialog(null,
						"ƽ����  = " + f_average + "\n���ڵ���ƽ������ " + averageBig + "��\nС��ƽ������ " + averageSmall + "��", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE, null);
				
				
			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 18));
		button_1.setBounds(233, 60, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("������");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int totalNumber = 0, failureNumber = 0;
				try {
					// ��ȡ������
					ResultSet rs_total = dbC.getRS("select * from tb_score");
					while (rs_total.next()) {
						totalNumber++;
					}

					// ��ȡ����60�֣�����������
					ResultSet rs_failure = dbC.getRS("select * from tb_score where score<'" + 60 + "'");
					while (rs_failure.next()) {
						failureNumber++;
					}
				} catch (SQLException e1) {
					System.out.println(e1);
				}

				// ��ʾ��������
				JOptionPane.showMessageDialog(null, "��������  = " + (float) failureNumber / totalNumber * 100 + "%\n������������ "
						+ failureNumber + "��\n�������� " + totalNumber + "��", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		button_2.setFont(new Font("����", Font.PLAIN, 18));
		button_2.setBounds(415, 60, 93, 23);
		contentPane.add(button_2);
		
		
		JButton button_3 = new JButton("�޸�");
        button_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		getM();
        		if (find > 0) {
        			ScoreChange sChange = new ScoreChange(find);
        			sChange.setVisible(true);
        			sChange.setResizable(false);
        		}
        	}
        });
        button_3.setFont(new Font("����", Font.PLAIN, 18));
        button_3.setBounds(38, 370, 93, 23);
        contentPane.add(button_3);
        
        JButton button_4 = new JButton("ɾ��");
        button_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		getM();
        		if (intRow == -1) {
        			JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�����У�", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
        		}
        		try {
        			dbC.getUpdate("delete from tb_score where scoreId='" + Integer.valueOf(find) + "'");
        			JOptionPane.showMessageDialog(null, "ɾ���ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
        		} catch (Exception ce) {
        			System.out.println(ce.getMessage());
        		}
        		
        		UpdateRecord();
        		
        	}
        });
        button_4.setFont(new Font("����", Font.PLAIN, 18));
        button_4.setBounds(233, 372, 93, 23);
        contentPane.add(button_4);
        
        JButton button_5 = new JButton("�˳�");
        button_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		dispose();
        	}
        });
        button_5.setFont(new Font("����", Font.PLAIN, 18));
        button_5.setBounds(415, 372, 93, 23);
        contentPane.add(button_5);
	       
        // �û��ȼ��ж�
		// 1Ϊ����Ա
		if (manage.MainFrame.level.equals("1")) {
			// �������κι���
		}
		// 2Ϊ��ͨ�û�
		else if (manage.MainFrame.level.equals("2")) {
			// ���ز��ֹ���
			button_3.setVisible(false);
			button_4.setVisible(false);
		}

		
		// ����Ĭ�ϲ������гɼ�
		sql = "select * from tb_score ";
		
		//���±��
		UpdateRecord();
		
	}
	
	//���±��
	public void UpdateRecord() {
		
		Vector vec = new Vector(1,1);
		
		model = new DefaultTableModel(rowdata, columnName);
		table = new JTable(model);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(38, 93, 476, 267);
		table.setRowHeight(30);
	    table.setFont(new Font("����", Font.PLAIN, 12));
	    scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN));
		
        try {
			ResultSet rs = dbC.getRS(sql);
			while (rs.next()) {
				vec = new Vector();
				vec.add(String.valueOf(rs.getString("scoreId")));
				vec.add(String.valueOf(rs.getString("stuNumber")));
				ResultSet rsTemp = dbC.getRS("select stuName from tb_student where stuNumber='"
						+ String.valueOf(rs.getString("stuNumber")) + "'");
				while (rsTemp.next()) {
					vec.add(String.valueOf(rsTemp.getString("stuName")));
				}
				vec.add(String.valueOf(rs.getString("courceName")));
				vec.add(String.valueOf(rs.getString("score")));
				model.addRow(vec);
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
		
	// ѡ����ѡ����
	public void getM() {
		intRow = table.getSelectedRow();
		if (intRow == -1) {
			JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ��У�", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
			return;
		}
		try {
			find = Integer.parseInt(model.getValueAt(intRow, 0).toString());
			System.out.println(find);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
