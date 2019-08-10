package user;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataconnection.DbC;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;


/**
 * 
 * Title: �û�ɾ�� 
 * Description: �û�ɾ��ģ�飬ֻ�Թ���Ա��ʾ��
 * 
 * @author moon
 */
public class UserDelete extends JFrame {

	private JPanel contentPane;
		
	DbC dbC = new DbC();
	
	public UserDelete() {
		Init();
	}
	
	public void Init() {
		setTitle("�û�ɾ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("��  ��  ɾ  ��");
		label.setFont(new Font("����", Font.BOLD, 18));
		label.setBounds(92, 14, 146, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("�û���");
		label_1.setFont(new Font("����", Font.PLAIN, 18));
		label_1.setBounds(53, 75, 76, 15);
		contentPane.add(label_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("����", Font.PLAIN, 18));
		comboBox.setBounds(140, 73, 145, 23);
		contentPane.add(comboBox);
		
		JButton button = new JButton("ɾ��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isAdmin = false, noUser = true;
				// �쳣�ж�
				try {
					ResultSet rs = dbC
							.getRS("select * from tb_user where userName='" + comboBox.getSelectedItem() + "'");
					while (rs.next()) {
						// �ж�ɾ�����û��Ƿ����Ա���޷�ɾ������Ա�û���
						if (1 == Integer.valueOf(rs.getString("userType").trim())) {
							isAdmin = true;
						}
					}
					
					if (isAdmin == true) {
						JOptionPane.showMessageDialog(null, "�޷�ɾ������Ա�˺ţ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
					} else {
						dbC.getUpdate("delete from tb_user where userName='" + comboBox.getSelectedItem()+ "'");
						JOptionPane.showMessageDialog(null, "��ϲ��ɾ���û��ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE, null);
					}
					rs.close();
					
				} catch (Exception ce) {
					System.out.println(ce);
				}
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 18));
		button.setBounds(52, 143, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("�˳�");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 18));
		button_1.setBounds(189, 141, 93, 23);
		contentPane.add(button_1);
		
		comboBox.addItem("���ڵ��û�");
		
		try {
			
			ResultSet resultSet = dbC.getRS("select * from tb_user");
			while(resultSet.next()) {
				String user = resultSet.getString("userName");
				comboBox.addItem(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}	