package student;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataconnection.DbC;

/**
 * Title: 学生信息录入
 * @author 程梦月
 *
 */

public class StudentInfoAddFrame extends JFrame {
	
	JPanel contentPane;
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	JLabel jLabel6 = new JLabel();
	
	JTextField jTextField1 = new JTextField();
	JTextField jTextField2 = new JTextField();
	
	//下拉列表
	JComboBox jComboBox1 = new JComboBox();
	JComboBox jComboBox2 = new JComboBox();
	JComboBox jComboBox3 = new JComboBox();
	
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	
	DbC conn = new DbC();//连接数据库
	
	JOptionPane jOptionPane1 = new JOptionPane();
	JOptionPane jOptionPane2 = new JOptionPane();
	JOptionPane jOptionPane_enter = new JOptionPane();
	
	String number, name, depart, sspec, sclass;
	
	public StudentInfoAddFrame() {
		Init();
		addActionListener2();
		addActionListener3();
	}

	private void Init() {
		contentPane = (JPanel) getContentPane();
		//contentPane = new JPanel();
		contentPane.setLayout(null);
		setSize(new Dimension(562,500));
		setVisible(true);
		setLocation(400, 200);
		//setResizable(false);
		setTitle("学生信息录入");
		
		jLabel1.setText("学  生  信  息  录  入");
		jLabel1.setFont(new Font("Dialog", Font.BOLD, 23));
		jLabel1.setBounds(new Rectangle(196, 16, 232, 25));
		
		jLabel2.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel2.setText("学       号:");
		jLabel2.setBounds(new Rectangle(50, 74, 90, 22));
		
		jLabel3.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel3.setText("姓       名:");
		jLabel3.setBounds(new Rectangle(50, 124, 90, 22));
		
		jLabel4.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel4.setText("所属学院:");
		jLabel4.setBounds(new Rectangle(50, 174, 90, 22));
		
		jLabel5.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel5.setText("所属专业:");
		jLabel5.setBounds(new Rectangle(297, 174, 90, 22));
		
		jLabel6.setFont(new Font("Dialog", Font.PLAIN, 18));
		jLabel6.setText("所在班级：");
		jLabel6.setBounds(new Rectangle(50, 224, 93, 29));
		
		jTextField1.setFont(new Font("Dialog", Font.PLAIN, 16));
		jTextField1.setText("");
		jTextField1.setBounds(new Rectangle(150, 74, 195, 23));
		
		jTextField2.setFont(new Font("Dialog", Font.PLAIN, 16));
		jTextField2.setText("");
		jTextField2.setBounds(new Rectangle(150, 124, 195, 23));
		
		jComboBox1.setFont(new Font("Dialog", Font.PLAIN, 16));
		jComboBox1.setBounds(new Rectangle(150, 174, 125, 25));
		
		jComboBox2.setEnabled(false);//设置控件不可用
		jComboBox2.setFont(new Font("Dialog", Font.PLAIN, 16));
		jComboBox2.setBounds(new Rectangle(390, 174, 125, 25));
		
		jComboBox3.setEnabled(false);//设置控件不可用
		jComboBox3.setFont(new Font("Dialog", Font.PLAIN, 16));
		jComboBox3.setBounds(new Rectangle(150, 224, 125, 25));
		
		jButton1.setText("提  交");
		jButton1.setFont(new Font("Dialog", Font.PLAIN, 16));
		jButton1.setBounds(new Rectangle(147, 274, 90, 27));
		jButton2.setText("退  出");
		jButton2.setFont(new Font("Dialog", Font.PLAIN, 16));
		jButton2.setBounds(new Rectangle(375, 274, 90, 27));
		
		contentPane.add(jLabel1);
		contentPane.add(jLabel2);
		contentPane.add(jLabel3);
		contentPane.add(jLabel4);
		contentPane.add(jLabel5);
		contentPane.add(jLabel6);
		
		contentPane.add(jTextField1);
		contentPane.add(jTextField2);
		
		contentPane.add(jComboBox1);
		contentPane.add(jComboBox2);
		contentPane.add(jComboBox3);
		
		contentPane.add(jButton1);
		contentPane.add(jButton2);
		contentPane.add(jOptionPane_enter);
		jComboBox1.addItem("请选择学院");
		
		//选择学院
		try {
			ResultSet rs = conn.getRS("select * from tb_depart");
			while(rs.next()) {
				String xueyuan = rs.getString("departName");
				jComboBox1.addItem(xueyuan);
			} 
		} catch(Exception ce) {
			//System.out.println(ce);
			ce.printStackTrace();
		}
		jComboBox2.setEnabled(false);
		jComboBox3.setEnabled(false);
	}
	
	public void addActionListener2() {
		jComboBox1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jComboBox2.setEnabled(true);
				jcb();
			}
		});
		
		jComboBox2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jComboBox3.setEnabled(true);
				classAdd();
			}
		});
	}
	
	//选择学院触发选择专业
	public void jcb() {
		jComboBox2.removeAllItems();
		jComboBox2.addItem("请选择专业");
		try {
			ResultSet rs = conn.getRS("select * from tb_spec where departName='" + String.valueOf(jComboBox1.getSelectedItem()) + "' ");
			while(rs.next()) {
				String zhuan = rs.getString("specName");
				jComboBox2.addItem(zhuan);
			}
			rs.close();
		} catch(Exception e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
		
	// 选择专业触发选择班级
	public void classAdd() {
		jComboBox3.removeAllItems();
		jComboBox3.addItem("请选择班级");
		try {
			ResultSet rs = conn.getRS("select * from tb_class where specName='" + String.valueOf(jComboBox2.getSelectedItem()) + "' ");
			while (rs.next()) {
				String banji = rs.getString("className");
				jComboBox3.addItem(banji);
			}
			rs.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	
	public void addActionListener3() {
		//提交按钮的监听
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 异常判断
				if (jTextField1.getText().trim().equals("")) {
					jOptionPane1.showMessageDialog(null, "请输入学生学号！", "提 示", jOptionPane1.INFORMATION_MESSAGE);
				} else if (jTextField2.getText().trim().equals("")) {
					jOptionPane1.showMessageDialog(null, "请输入学生姓名！", "提 示", jOptionPane1.INFORMATION_MESSAGE);
				} else if (jComboBox1.getSelectedIndex() == 0) {
					jOptionPane1.showMessageDialog(null, "请选择学生所在学院！", "提 示", jOptionPane1.INFORMATION_MESSAGE);
				} else if (jComboBox2.getSelectedIndex() == 0) {
					jOptionPane1.showMessageDialog(null, "请选择学生所属专业！", "提 示", jOptionPane1.INFORMATION_MESSAGE);
				} else if (jComboBox3.getSelectedIndex() == 0) {
					jOptionPane1.showMessageDialog(null, "请选择学生所在班级！", "提 示", jOptionPane1.INFORMATION_MESSAGE);
				} else {
					try {
						//查询所选的学院是否存在
						findDepart();
						//查询所选的专业是否存在
						findSpec();
						//查询所选的班级是否存在
						findClass();
						boolean Num = false;
						ResultSet rs = conn.getRS("select * from tb_student");
						while (rs.next()) {
							if (jTextField1.getText().trim().equals(rs.getString("stuNumber").trim())) {
								Num = true;
							}
						}
						rs.close();
						if (Num) {
							JOptionPane.showMessageDialog(null, "学生学号已存在，请重新输入！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
						} else {
							number = jTextField1.getText().trim();
							name = jTextField2.getText().trim();
							instu();
						}
					} catch (Exception ce) {
						System.out.println(ce.getMessage());
					}
				}
				
			}
		});
		
		//退出按钮的监听
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}
	
	
	
	//查询学院
	public void findDepart() {
		try {
			ResultSet rs = conn.getRS(
					"select * from tb_depart where departName='" + String.valueOf(jComboBox1.getSelectedItem()) + "' ");
			while (rs.next()) {
				depart = rs.getString("departName");
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 查询专业
	public void findSpec() {
		try {
			ResultSet rs = conn.getRS(
					"select * from tb_spec where specName='" + String.valueOf(jComboBox2.getSelectedItem()) + "' ");
			while (rs.next()) {
				sspec = rs.getString("specName");
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 查询班级
	public void findClass() {
		try {
			ResultSet rs = conn.getRS(
					"select * from tb_class where className='" + String.valueOf(jComboBox3.getSelectedItem()) + "' ");
			while (rs.next()) {
				sclass = rs.getString("className");
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	// 学生信息录入
	public void instu() {
		try {
			if (0 < conn.getUpdate("insert into tb_student (stuNumber,stuName,stuDepart,stuSpec,stuClass) values ('"
					+ number + "','" + name + "','" + depart + "','" + sspec + "','" + sclass + "')")) {
				jOptionPane_enter.showMessageDialog(this, "学生信息录入成功！", "提 示",
						jOptionPane_enter.INFORMATION_MESSAGE);
			} else {
				//System.err.printf("修改 tb_student 表中 stuNumber = %d 的记录失败\n", number);
				jOptionPane_enter.showMessageDialog(this, "学生信息录入失败！", "提 示",
						jOptionPane_enter.INFORMATION_MESSAGE);
			}
		} catch (Exception ce) {
			System.out.println(ce.getMessage());
		}
	}
	
}