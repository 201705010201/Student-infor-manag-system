package user;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataconnection.DbC;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
/**
 * Title:用户密码修改模块
 * Description: 用户密码修改模块，管理员和普通用户都可见，
 * 管理员可修改所有用户密码，普通用户只能修改本用户密码。
 * 
 * @author moon
 *
 */
public class UserPassword extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	
	DbC dbC = new DbC();
	String level,name;
	
	
	 
	public UserPassword(String level, String name) {
		this.level = level;
		this.name = name;
		Init();
	}
	
	public void Init() {
		setTitle("用户密码修改");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 404, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("修  改  密  码");
		label.setFont(new Font("宋体", Font.BOLD, 18));
		label.setBounds(119, 11, 165, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("用户名：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(52, 42, 94, 15);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setFont(new Font("宋体", Font.PLAIN, 18));
		textField.setBounds(155, 40, 158, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("新密码：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(48, 140, 75, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("确认新密码;");
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		label_3.setBounds(35, 196, 109, 15);
		contentPane.add(label_3);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 18));
		passwordField.setBounds(153, 134, 159, 28);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("宋体", Font.PLAIN, 18));
		passwordField_1.setBounds(155, 190, 157, 27);
		contentPane.add(passwordField_1);
		
		JLabel label_4 = new JLabel("旧密码：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 18));
		label_4.setBounds(51, 90, 80, 15);
		contentPane.add(label_4);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("宋体", Font.PLAIN, 18));
		passwordField_2.setBounds(151, 84, 162, 27);
		contentPane.add(passwordField_2);
		
		
		JButton button = new JButton("确认");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean a = false;
				boolean b = true;
				// 异常判断
				if (textField.getText().trim() == null || textField.getText().trim().length() == 0
						|| textField.getText().trim().length() > 20) {
					JOptionPane.showMessageDialog(null, "用户名不能为空且最大长度为20个字符！", "提  示", JOptionPane.INFORMATION_MESSAGE, null);
				} else if (passwordField_1.getText().trim().compareTo(passwordField.getText().trim()) != 0) {
					JOptionPane.showMessageDialog(null, "新密码确认错误！", "提  示", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					try {
						// 对原密码进行验证
						ResultSet rs = dbC.getRS("select userName,userPwd from tb_user");
						while (rs.next()) {
							if (textField.getText().trim().equals(rs.getString("userName").trim())
									&& passwordField_2.getText().trim().equals(rs.getString("userPwd").trim())) {
								a = true;
								b = false;
								break;
							}
						}
						if (b) {
							JOptionPane.showMessageDialog(null, "用户名或密码错误！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
						}
						if (a) {
							// 修改密码
							dbC.getUpdate("update tb_user set userPwd= '" + passwordField.getText().trim()
									+ "' where userName=('" + textField.getText().trim() + "') ");
							JOptionPane.showMessageDialog(null, "恭喜您修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
							textField.setText("");
							passwordField.setText("");
							passwordField_1.setText("");
							passwordField_2.setText("");
						}
						rs.close();
					} catch (java.sql.SQLException ce) {
						System.out.println(ce);
					}
				}
				
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 18));
		button.setBounds(35, 255, 93, 23);
		contentPane.add(button);
		
		
		
		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 18));
		button_1.setBounds(235, 255, 93, 23);
		contentPane.add(button_1);
		
		
		textField.setText(name);
		
		if(level.equals("1")) {
			textField.setEnabled(true);
		}
	}	
	
}
