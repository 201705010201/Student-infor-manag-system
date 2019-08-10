package user;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dataconnection.DbC;

/**
 * Title: 用户添加
 * Description:用户添加模板，只对管理员显示
 * @author 程梦月
 * 
 */

public class UserAddFrame extends JFrame {
	
	JPanel contentPane;
	JLabel jlabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JTextField jTextField1 = new JTextField();
	JPasswordField jPasswordField1 = new JPasswordField();
	JPasswordField jPasswordField2 = new JPasswordField();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	
	JOptionPane jOptionPane1 = new JOptionPane();

	//背景图片
	ImageIcon bg2 = new ImageIcon("img\\注册背景图片.jpg");
	JLabel jLabel_bagd = new JLabel(bg2);
	
	
	public void ming() {
		
		//设置背景图片
		jLabel_bagd.setBounds(0, 0, bg2.getIconWidth(), bg2.getIconHeight());
		
		jlabel1.setOpaque(false);
		jLabel2.setOpaque(false);
		jLabel3.setOpaque(false);
		jLabel4.setOpaque(false);
		jTextField1.setOpaque(false);
		jPasswordField1.setOpaque(false);
		jPasswordField2.setOpaque(false);
		jButton1.setOpaque(false);
		jButton1.setOpaque(false);
		
		//让窗口中的层次透明
		JPanel jPanel =(JPanel) this.getContentPane();
		jPanel.setOpaque(false);
		
		//在窗体中添加图片
		this.getLayeredPane().add(jLabel_bagd, new Integer(Integer.MIN_VALUE));
		
	}
	
	public UserAddFrame() {
		
		Init();
		
		ming();
		
		addListener();
		
	}

	private void Init() {
		
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(469, 315));
		setTitle("添加用户");
		
		jlabel1.setFont(new Font("Dialog", Font.BOLD, 23));
		jlabel1.setText("添  加   用   户");
		jlabel1.setBounds(new Rectangle(134, 12, 198, 27));
		
		jLabel2.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel2.setText("用户名:");
		jLabel2.setBounds(new Rectangle(90, 66, 68, 22));
		
		jLabel3.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("密    码:");
		jLabel3.setBounds(new Rectangle(90, 118, 75, 27));
		
		jLabel4.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("确认密码:");
		jLabel4.setBounds(new Rectangle(84, 167, 89, 29));
		
		jTextField1.setFont(new Font("Dialog", Font.PLAIN, 13));
		jTextField1.setBounds(new Rectangle(191, 65, 155, 24));
		
		jPasswordField1.setBounds(new Rectangle(191, 119, 155, 24));
		jPasswordField2.setText("");
		jPasswordField2.setBounds(new Rectangle(191, 169, 155, 24));
		
		jButton1.setBounds(new Rectangle(99, 225, 89, 25));
		jButton1.setFont(new Font("Dialog", Font.PLAIN, 13));
		jButton1.setText("提    交");
		
		jButton2.setBounds(new Rectangle(267, 225, 89, 25));
		jButton2.setFont(new Font("Dialog", Font.PLAIN, 13));
		jButton2.setText("退    出");
		
		jOptionPane1.setBounds(new Rectangle(0, 233, 262, 90));
		jOptionPane1.setLayout(null);
		
		contentPane.add(jLabel2);
		contentPane.add(jLabel3);
		contentPane.add(jLabel4);
		contentPane.add(jTextField1);
		contentPane.add(jPasswordField2);
		contentPane.add(jPasswordField1);
		contentPane.add(jButton1);
		contentPane.add(jButton2);
		contentPane.add(jlabel1);
		contentPane.add(jOptionPane1);
	}
	
	
	public void addListener() {
		//退出按钮的监听事件
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		//提交按钮的监听事件
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean zhi = false;
				//异常判断
				if(jTextField1.getText().length() == 0) {
					//这里null顶替的是当前的Frame，作为第一个参数使用，类型是匹配的。
					//如果你用this，则作为第一个参数送进去的是当前Listener对象，类型是不匹配的。
					JOptionPane.showMessageDialog(null, "用户名不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else if (jPasswordField2.getText().trim().equals(jPasswordField1.getText().trim())) {
					zhi = true;
				} else {
					JOptionPane.showMessageDialog(null, "密码确认错误！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				}
				if(zhi) {//输入的用户名与密码信息都没有错误，即注册成功
					try {
						//查询用户名是否存在
						boolean name = false;
						DbC conn = new DbC();
						ResultSet rs = conn.getRS("select userName from tb_user");
						while(rs.next()) {//trim()的作用是去掉字符串两端的多余的空格
							if(jTextField1.getText().trim().equals(rs.getString("userName").trim())) {
								name = true;
							}
						}
						if(name) {
							JOptionPane.showMessageDialog(null, "该用户名已经存在！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
						} else {
							//用户名不存在则添加
							conn.getUpdate("insert into tb_user (userName,userPwd,userType) values ('"
									+ jTextField1.getText().trim() + "','" + jPasswordField2.getText().trim() + "','2')");
							JOptionPane.showMessageDialog(null, "恭喜您添加用户成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
						}
						rs.close();
					} catch (Exception ce) {
						System.out.println(ce);
					}
				}
			}
		});
	}
}