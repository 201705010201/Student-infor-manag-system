package score;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dataconnection.DbC;

/**
 * Title:成绩修改
 * @author moon
 *
 */
public class ScoreChange extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	
	DbC dbC = new DbC();
	
	
	
	
	int nfind;
	
	public ScoreChange(int find) {
		nfind = find;
		Init();
	}
	
	
	public void Init() {
		setTitle("成绩修改");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);  
		setContentPane(contentPane);
		
		JLabel label = new JLabel("成  绩  修  改");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(94, 10, 239, 21);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("学生学号：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(64, 48, 91, 25);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 18));
		textField.setBounds(171, 50, 185, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("课程名称：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(64, 106, 101, 21);
		contentPane.add(label_2);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setBounds(170, 101, 188, 30);
		contentPane.add(comboBox);
		
		JLabel label_3 = new JLabel("分  数：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(75, 152, 80, 21);
		contentPane.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setBounds(170, 148, 186, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String keCName, zyName, tName, xueFen;
				keCName = textField.getText().trim();
				zyName = String.valueOf(comboBox.getSelectedItem());
				xueFen = textField_1.getText().trim();
				try {
					dbC.getUpdate("update tb_score set courceName='" + zyName + "', stuNumber='" + keCName + "',score='"
							+ Float.valueOf(xueFen) + "' where scoreId='" + nfind + "'");
					JOptionPane.showMessageDialog(null, "课程修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} catch (Exception a) {
					System.out.println(a.getMessage());
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(65, 198, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(246, 198, 93, 23);
		contentPane.add(button_1);
		
		comboBox.addItem("请选择");
		
		// 选择课程
		try {
			ResultSet rs = dbC.getRS("select * from tb_cource");
			while (rs.next()) {
				String ke = rs.getString("courceName");
				comboBox.addItem(ke);
			}
			rs.close();
		} catch (Exception ce) {
			System.out.println("++++++++" + ce);
		}
		
		set();
		
		
	}

	// 选定行后在修改界面显示选定默认值
	public void set() {
		if (nfind < 0)
			return;
		else {
			try {
				ResultSet rs = dbC.getRS("select * from tb_score where scoreId='" + Integer.valueOf(nfind) + "'");
				while (rs.next()) {
					comboBox.setSelectedItem(String.valueOf(rs.getString("courceName")));
					textField.setText(rs.getString("stuNumber").trim());
					textField_1.setText(rs.getString("score").trim());
				}
				rs.close();
			} catch (Exception ce) {
				System.out.println("++++++++" + ce);
			}
		}
	}

}
