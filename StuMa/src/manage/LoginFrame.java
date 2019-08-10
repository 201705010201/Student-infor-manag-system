package manage;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dataconnection.DbC;
import user.UserAddFrame;


public class LoginFrame extends JFrame {

	JLabel jLabel_userName = new JLabel("用户名:");
	JLabel jLabel_pwd = new JLabel("密码 :");
	
	JTextField jTextField_userName = new JTextField(15);
	JPasswordField jPasswordField_pwd = new JPasswordField(15);

	JButton button_login = new JButton("登录");
	JButton button_register = new JButton("注册");
	
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jp3 = new JPanel();
	JPanel jp4 = new JPanel();

	//定义一个主界面的小图标
	Image icon1 = Toolkit.getDefaultToolkit().getImage("img\\左上角的图标.jpg");
	//用户注册界面的小图标
	Image icon2 = Toolkit.getDefaultToolkit().getImage("img\\注册界面的小图标.jpg");
	//背景图片
	ImageIcon bg = new ImageIcon("img\\a.jpg");
	JLabel background = new JLabel(bg);
	
	JOptionPane jOptionPane = new JOptionPane();
	
	static String name, level;
	//调用数据库连接的方法
	DbC conn = new DbC();
	
	/**
	 * 设置其他组件透明
	 */
	public void touming() {
		
		//设置背景图片
		background.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);

		//让其他组件透明
		button_login.setOpaque(false);
		button_register.setOpaque(false);
		
		jTextField_userName.setOpaque(false);
		jPasswordField_pwd.setOpaque(false);
		
		jTextField_userName.setFont(new Font("黑体", Font.PLAIN, 20));
		//jTextField_userName.setForeground(Color.green);
		
		jPasswordField_pwd.setFont(new Font("黑体", Font.PLAIN, 20));
		//jPasswordField_pwd.setForeground(Color.green);
		
		jLabel_userName.setOpaque(false);
		jLabel_pwd.setOpaque(false);
		
		jLabel_userName.setFont(new Font("黑体", Font.PLAIN, 20));
		//jLabel_userName.setForeground(Color.BLUE);
		
		jLabel_pwd.setFont(new Font("黑体", Font.PLAIN, 20));
		//jLabel_pwd.setForeground(Color.BLUE);
		
		//让窗口中的层次透明
		JPanel jpo=(JPanel) this.getContentPane();
		jpo.setOpaque(false);
		
		//在窗体中添加图片元素
		this.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));
		
	}
	
	
	public LoginFrame() {
		this.setSize(520, 420);
		this.setTitle("欢迎登陆学生管理系统");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//设置左上角的图标
		this.setIconImage(icon1);
		addComponent();
		touming();
		addListener1();
		this.setVisible(true);
	}
	
	/**
	 * 添加组件的方法
	 */
	public void addComponent() {
		
		this.setLayout(new GridLayout(4, 1));
		
		jp2.add(jLabel_userName);
		jp2.add(jTextField_userName);
		jp3.add(jLabel_pwd);
		jp3.add(jPasswordField_pwd);
		
		jp4.add(button_login);
		jp4.add(button_register);

		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
	}
	
	/**
	 * 按钮的监听事件
	 */
	public void addListener1() {
		//登录按钮的监听
		button_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean cheng = false;
				//异常判断
				if(jTextField_userName.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "用户名不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						ResultSet rs = conn.getRS("select * from tb_user where userName='" + jTextField_userName.getText() + "'");
						while(rs.next()) {
							if(rs.getString("userPwd").trim().equals(jPasswordField_pwd.getText())) {
								level = rs.getString("userType").trim();
								name = jTextField_userName.getText().trim();
								MainFrame main = new MainFrame(level,jTextField_userName.getText());
								main.setVisible(true);
								main.setResizable(false);
								//main.validate();
								dispose();
								cheng = false;
								break;
							} else {
								cheng = true;
							}
						}
						if(cheng) {
							JOptionPane.showMessageDialog(null, "用户名或密码错误！", "提 示", JOptionPane.INFORMATION_MESSAGE);		
						}
						rs.close();
					} catch (Exception ce) {
						System.out.println(ce);
					}
				}	
			}
		});
		
		//注册按钮的监听
		button_register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserAddFrame useradf = new UserAddFrame();
				useradf.setLocation(400, 200);
				useradf.setSize(469, 315);
				useradf.setVisible(true);
				useradf.setResizable(false);
				useradf.setIconImage(icon2);
				useradf.validate();
			}
		});
	}
}	