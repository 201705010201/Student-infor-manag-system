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
 * Title:成绩管理模块
 * @author moon
 *
 */
public class ScoreManage extends JFrame {

	private JPanel contentPane;

	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	
	String [] columnName = {"成绩编号", "学生学号", "姓名", "课程", "分数"};
	Object[][] rowdata = {};
	
	DbC dbC = new DbC();
	
	String sql;
	
	int intRow;
	static int find;
	
	

	public ScoreManage() {
		Init();
	}
	
	
	public void Init() {
		setTitle("成绩管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("成  绩  管  理");
		label.setFont(new Font("宋体", Font.BOLD, 18));
		label.setBounds(200, 10, 136, 26);
		contentPane.add(label);
		
		JButton button = new JButton("排序");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//依据习惯，通常是对成绩进行降序排序
				sql = "select * from tb_score order by score desc, stuNumber";
				UpdateRecord();			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		button.setBounds(38, 60, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("平均");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float f_average = 0;
				int averageBig = 0, averageSmall = 0;
				try {
					// 获得平均分
					ResultSet rs_average = dbC.getRS("select avg(score) as scoreAverage from tb_score");
					while (rs_average.next()) {
						f_average = Float.valueOf(rs_average.getString("scoreAverage"));
					}

					// 获得大于等于平均分的人数
					ResultSet rs_averageBig = dbC.getRS("select * from tb_score where score>='" + f_average + "'");
					while (rs_averageBig.next()) {
						averageBig++;
					}

					// 获得小于平均分的人数
					ResultSet rs_averageSmall = dbC.getRS("select * from tb_score where score<'" + f_average + "'");
					while (rs_averageSmall.next()) {
						averageSmall++;
					}

				} catch (SQLException e1) {
					System.out.println(e1);
				}

				// 显示平均分
				JOptionPane.showMessageDialog(null,
						"平均分  = " + f_average + "\n大于等于平均分有 " + averageBig + "人\n小于平均分有 " + averageSmall + "人", "提示",
						JOptionPane.INFORMATION_MESSAGE, null);
				
				
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 18));
		button_1.setBounds(233, 60, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("不及格");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int totalNumber = 0, failureNumber = 0;
				try {
					// 获取总人数
					ResultSet rs_total = dbC.getRS("select * from tb_score");
					while (rs_total.next()) {
						totalNumber++;
					}

					// 获取低于60分（不及格）人数
					ResultSet rs_failure = dbC.getRS("select * from tb_score where score<'" + 60 + "'");
					while (rs_failure.next()) {
						failureNumber++;
					}
				} catch (SQLException e1) {
					System.out.println(e1);
				}

				// 显示不及格率
				JOptionPane.showMessageDialog(null, "不及格率  = " + (float) failureNumber / totalNumber * 100 + "%\n不及格人数有 "
						+ failureNumber + "人\n总人数有 " + totalNumber + "人", "提示", JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 18));
		button_2.setBounds(415, 60, 93, 23);
		contentPane.add(button_2);
		
		
		JButton button_3 = new JButton("修改");
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
        button_3.setFont(new Font("宋体", Font.PLAIN, 18));
        button_3.setBounds(38, 370, 93, 23);
        contentPane.add(button_3);
        
        JButton button_4 = new JButton("删除");
        button_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		getM();
        		if (intRow == -1) {
        			JOptionPane.showMessageDialog(null, "请选择要删除的行！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        		}
        		try {
        			dbC.getUpdate("delete from tb_score where scoreId='" + Integer.valueOf(find) + "'");
        			JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
        		} catch (Exception ce) {
        			System.out.println(ce.getMessage());
        		}
        		
        		UpdateRecord();
        		
        	}
        });
        button_4.setFont(new Font("宋体", Font.PLAIN, 18));
        button_4.setBounds(233, 372, 93, 23);
        contentPane.add(button_4);
        
        JButton button_5 = new JButton("退出");
        button_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		dispose();
        	}
        });
        button_5.setFont(new Font("宋体", Font.PLAIN, 18));
        button_5.setBounds(415, 372, 93, 23);
        contentPane.add(button_5);
	       
        // 用户等级判断
		// 1为管理员
		if (manage.MainFrame.level.equals("1")) {
			// 不隐藏任何功能
		}
		// 2为普通用户
		else if (manage.MainFrame.level.equals("2")) {
			// 隐藏部分功能
			button_3.setVisible(false);
			button_4.setVisible(false);
		}

		
		// 设置默认查找所有成绩
		sql = "select * from tb_score ";
		
		//更新表格
		UpdateRecord();
		
	}
	
	//更新表格
	public void UpdateRecord() {
		
		Vector vec = new Vector(1,1);
		
		model = new DefaultTableModel(rowdata, columnName);
		table = new JTable(model);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(38, 93, 476, 267);
		table.setRowHeight(30);
	    table.setFont(new Font("宋体", Font.PLAIN, 12));
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
		
	// 选定所选的行
	public void getM() {
		intRow = table.getSelectedRow();
		if (intRow == -1) {
			JOptionPane.showMessageDialog(null, "请选择要修改的行！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
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
