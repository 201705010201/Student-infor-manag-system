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

	JLabel jLabel_userName = new JLabel("�û���:");
	JLabel jLabel_pwd = new JLabel("���� :");
	
	JTextField jTextField_userName = new JTextField(15);
	JPasswordField jPasswordField_pwd = new JPasswordField(15);

	JButton button_login = new JButton("��¼");
	JButton button_register = new JButton("ע��");
	
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jp3 = new JPanel();
	JPanel jp4 = new JPanel();

	//����һ���������Сͼ��
	Image icon1 = Toolkit.getDefaultToolkit().getImage("img\\���Ͻǵ�ͼ��.jpg");
	//�û�ע������Сͼ��
	Image icon2 = Toolkit.getDefaultToolkit().getImage("img\\ע������Сͼ��.jpg");
	//����ͼƬ
	ImageIcon bg = new ImageIcon("img\\a.jpg");
	JLabel background = new JLabel(bg);
	
	JOptionPane jOptionPane = new JOptionPane();
	
	static String name, level;
	//�������ݿ����ӵķ���
	DbC conn = new DbC();
	
	/**
	 * �����������͸��
	 */
	public void touming() {
		
		//���ñ���ͼƬ
		background.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
		
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);

		//���������͸��
		button_login.setOpaque(false);
		button_register.setOpaque(false);
		
		jTextField_userName.setOpaque(false);
		jPasswordField_pwd.setOpaque(false);
		
		jTextField_userName.setFont(new Font("����", Font.PLAIN, 20));
		//jTextField_userName.setForeground(Color.green);
		
		jPasswordField_pwd.setFont(new Font("����", Font.PLAIN, 20));
		//jPasswordField_pwd.setForeground(Color.green);
		
		jLabel_userName.setOpaque(false);
		jLabel_pwd.setOpaque(false);
		
		jLabel_userName.setFont(new Font("����", Font.PLAIN, 20));
		//jLabel_userName.setForeground(Color.BLUE);
		
		jLabel_pwd.setFont(new Font("����", Font.PLAIN, 20));
		//jLabel_pwd.setForeground(Color.BLUE);
		
		//�ô����еĲ��͸��
		JPanel jpo=(JPanel) this.getContentPane();
		jpo.setOpaque(false);
		
		//�ڴ��������ͼƬԪ��
		this.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));
		
	}
	
	
	public LoginFrame() {
		this.setSize(520, 420);
		this.setTitle("��ӭ��½ѧ������ϵͳ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//�������Ͻǵ�ͼ��
		this.setIconImage(icon1);
		addComponent();
		touming();
		addListener1();
		this.setVisible(true);
	}
	
	/**
	 * �������ķ���
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
	 * ��ť�ļ����¼�
	 */
	public void addListener1() {
		//��¼��ť�ļ���
		button_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean cheng = false;
				//�쳣�ж�
				if(jTextField_userName.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "�û�������Ϊ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
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
							JOptionPane.showMessageDialog(null, "�û������������", "�� ʾ", JOptionPane.INFORMATION_MESSAGE);		
						}
						rs.close();
					} catch (Exception ce) {
						System.out.println(ce);
					}
				}	
			}
		});
		
		//ע�ᰴť�ļ���
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