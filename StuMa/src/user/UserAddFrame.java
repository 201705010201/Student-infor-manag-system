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
 * Title: �û����
 * Description:�û����ģ�壬ֻ�Թ���Ա��ʾ
 * @author ������
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

	//����ͼƬ
	ImageIcon bg2 = new ImageIcon("img\\ע�ᱳ��ͼƬ.jpg");
	JLabel jLabel_bagd = new JLabel(bg2);
	
	
	public void ming() {
		
		//���ñ���ͼƬ
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
		
		//�ô����еĲ��͸��
		JPanel jPanel =(JPanel) this.getContentPane();
		jPanel.setOpaque(false);
		
		//�ڴ��������ͼƬ
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
		setTitle("����û�");
		
		jlabel1.setFont(new Font("Dialog", Font.BOLD, 23));
		jlabel1.setText("��  ��   ��   ��");
		jlabel1.setBounds(new Rectangle(134, 12, 198, 27));
		
		jLabel2.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel2.setText("�û���:");
		jLabel2.setBounds(new Rectangle(90, 66, 68, 22));
		
		jLabel3.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("��    ��:");
		jLabel3.setBounds(new Rectangle(90, 118, 75, 27));
		
		jLabel4.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("ȷ������:");
		jLabel4.setBounds(new Rectangle(84, 167, 89, 29));
		
		jTextField1.setFont(new Font("Dialog", Font.PLAIN, 13));
		jTextField1.setBounds(new Rectangle(191, 65, 155, 24));
		
		jPasswordField1.setBounds(new Rectangle(191, 119, 155, 24));
		jPasswordField2.setText("");
		jPasswordField2.setBounds(new Rectangle(191, 169, 155, 24));
		
		jButton1.setBounds(new Rectangle(99, 225, 89, 25));
		jButton1.setFont(new Font("Dialog", Font.PLAIN, 13));
		jButton1.setText("��    ��");
		
		jButton2.setBounds(new Rectangle(267, 225, 89, 25));
		jButton2.setFont(new Font("Dialog", Font.PLAIN, 13));
		jButton2.setText("��    ��");
		
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
		//�˳���ť�ļ����¼�
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		//�ύ��ť�ļ����¼�
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean zhi = false;
				//�쳣�ж�
				if(jTextField1.getText().length() == 0) {
					//����null������ǵ�ǰ��Frame����Ϊ��һ������ʹ�ã�������ƥ��ġ�
					//�������this������Ϊ��һ�������ͽ�ȥ���ǵ�ǰListener���������ǲ�ƥ��ġ�
					JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				} else if (jPasswordField2.getText().trim().equals(jPasswordField1.getText().trim())) {
					zhi = true;
				} else {
					JOptionPane.showMessageDialog(null, "����ȷ�ϴ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
				}
				if(zhi) {//������û�����������Ϣ��û�д��󣬼�ע��ɹ�
					try {
						//��ѯ�û����Ƿ����
						boolean name = false;
						DbC conn = new DbC();
						ResultSet rs = conn.getRS("select userName from tb_user");
						while(rs.next()) {//trim()��������ȥ���ַ������˵Ķ���Ŀո�
							if(jTextField1.getText().trim().equals(rs.getString("userName").trim())) {
								name = true;
							}
						}
						if(name) {
							JOptionPane.showMessageDialog(null, "���û����Ѿ����ڣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
						} else {
							//�û��������������
							conn.getUpdate("insert into tb_user (userName,userPwd,userType) values ('"
									+ jTextField1.getText().trim() + "','" + jPasswordField2.getText().trim() + "','2')");
							JOptionPane.showMessageDialog(null, "��ϲ������û��ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
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