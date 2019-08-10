package classs;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import java.awt.Color;


import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import dataconnection.DbC;
import manage.MainFrame;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.IllegalFormatCodePointException;
import java.util.Vector;
import java.awt.event.ActionEvent;

/**
 * Title:班级信息管理
 * Description: 实现班级管理模块，包含班级查询和班级删除，同时也是班级内容修改的入口。
 * @author moon
 *
 */
public class ClassManager extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	
	String[] arrField = {"班级编号", "学院名称", "专业名称", "班级名称"};
	Object[][] arrData = {};
	
	
	DbC dbC = new DbC();
	
	String sql,find;
	int row;
	
	ButtonGroup buttonGroup = new ButtonGroup();
	
	
	public ClassManager() {
		
		init();
		
	}

	/*
	 * 初始化窗体
	 */

	private void init() {
		setTitle("班级信息管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("班   级   信   息   管   理");
		label.setFont(new Font("宋体", Font.BOLD, 24));
		label.setBounds(125, 25, 379, 26);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("按学院查询：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(56, 66, 120, 34);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("请选择学院：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(184, 68, 125, 31);
		contentPane.add(label_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
		comboBox.setBounds(319, 66, 185, 34);
		contentPane.add(comboBox);
		
		//按学院查询的按钮
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				sql = "select * from tb_class "
						+ "where departName='" + String.valueOf(comboBox.getSelectedItem()) + "'";
				UpdateRecord();
				
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(535, 66, 93, 34);
		contentPane.add(button);
		
		JLabel label_3 = new JLabel("按班级查询：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(54, 118, 120, 27);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("请输入班级：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(184, 118, 124, 26);
		contentPane.add(label_4);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBounds(319, 114, 185, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//按班级查询的按钮
		JButton button_1 = new JButton("查询");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				sql = "select * from tb_class "
						+ "where className='" + textField.getText() + "'";
				UpdateRecord();
				
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(535, 114, 93, 35);
		contentPane.add(button_1);
		
		//按学院查询的单选按钮
		JRadioButton radioButton = new JRadioButton();
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				comboBox.setEnabled(true);
				button.setEnabled(true);
				
			}
		});
		radioButton.setBounds(29, 66, 21, 23);
		contentPane.add(radioButton);
		
		//按班级查询的单选按钮
		JRadioButton radioButton_1 = new JRadioButton();
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textField.setEnabled(true);
				button_1.setEnabled(true);
				
			}
		});
		radioButton_1.setBounds(27, 118, 21, 23);
		contentPane.add(radioButton_1);
		
		//加入到ButtonGroup中的JRdioButton只有一个可以被选中
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton);
		buttonGroup.add(radioButton_1);
		
		
		JButton button_2 = new JButton("修改");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getM();
				//System.out.println(row);
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "请选择要修改的班级！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					ClassChange classChange = new ClassChange(find);
					classChange.setVisible(true);
					classChange.setResizable(false);
				}
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 20));
		button_2.setBounds(41, 496, 93, 26);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("删除");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				getM();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "请选择要删除的班级！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} 
				
				try {
					dbC.getUpdate("delete from tb_class "
							+ "where classId='" + Integer.valueOf(find) + "'");
					JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				
				UpdateRecord();
		
			}
		});
		button_3.setFont(new Font("宋体", Font.PLAIN, 20));
		button_3.setBounds(287, 496, 93, 26);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("退出");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_4.setFont(new Font("宋体", Font.PLAIN, 20));
		button_4.setBounds(514, 496, 93, 26);
		contentPane.add(button_4);
		
		comboBox.addItem("请选择学院");
		
		
		//用户等级判断
		//1为管理员,2为普通用户
		if(MainFrame.level.equals("1")) {
			//不隐藏任何功能
		} else if (MainFrame.level.equals("2")) {
			//隐藏修改和删除功能
			button_2.setVisible(false);
			button_3.setVisible(false);
		}
		
		//选择学院
		try {
			
			ResultSet rSet = dbC.getRS("select * from tb_depart");
			
			while(rSet.next()) {
				String xi = rSet.getString("departName");
				comboBox.addItem(xi);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("++++++++" + e);
		}
		
		comboBox.setEnabled(false);
		button.setEnabled(false);
		textField.setEnabled(false);
		button_1.setEnabled(false);
		
		sql = "select * from tb_class";
		UpdateRecord();
		
		
	}
	

	//获取选定的行
	protected void getM() {
		row = table.getSelectedRow();
		if(row == -1) {
			return;
		}
		try {
			
			/*
			 * getValueAt(int row, int column) 
			 *  返回 row 和 column 位置的单元格值。
			 */
			
			find = model.getValueAt(row, 0).toString().trim();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//更新表格
	public void UpdateRecord() {
		
		//不知道行数有几行，所以这里用Vector
		//从1开始,增值为1
		Vector vec = new Vector(1, 1);
		model = new DefaultTableModel(arrData, arrField);
		table = new JTable();
		table.setModel(model);
		scrollPane = new JScrollPane(table);
		
        scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 255, 255), new Color(0, 255, 255), new Color(0, 255, 255), new Color(0, 255, 255)));
        scrollPane.setBounds(22, 165, 606, 311);
        
		table.setRowHeight(30);
		table.setFont(new Font("SimSun", Font.PLAIN, 12));
		
		 // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景

		 // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
		
        try {
			
        	ResultSet resultSet = dbC.getRS(sql);
        	while(resultSet.next()) {
        		vec = new Vector();
        		vec.add(String.valueOf(resultSet.getInt("classId")));
        		vec.add(resultSet.getString("departName").trim());
        		vec.add(resultSet.getString("specName"));
        		vec.add(resultSet.getString("className"));
        		model.addRow(vec);
        	}
        	resultSet.close();	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
        //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        table.setGridColor(Color.blue);
        table.setDragEnabled(true);
        table.setSelectionForeground(Color.red);
        table.setSelectionBackground(Color.green);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setShowVerticalLines(true);
		contentPane.add(scrollPane);
		
	}
	
}
