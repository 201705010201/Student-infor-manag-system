package depart;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dataconnection.DbC;
import manage.MainFrame;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

/**
 * Title：院系信息管理
 * @author moon
 *
 */

public class DepartManage extends JFrame {

	private JPanel contentPane;
	
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	
	String [] columnName = {"专业编号", "学院名称" ,"专业名称"};
	Object[][] rowdata = {};
	
	DbC dbC = new DbC();
	
	int row;
	String sql, find;
	
	
	public DepartManage() {
		
		init();
	}
	
	
	public void init() {
		setTitle("院系信息管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		JLabel label = new JLabel("院   系   信   息   管   理");
		label.setFont(new Font("宋体", Font.BOLD, 22));
		label.setBounds(74, 10, 346, 38);
		contentPane.add(label);
		
		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				getM();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "请选择要修改的院系！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					DepartChange dChange = new DepartChange(find);
					dChange.setVisible(true);					
					dChange.setResizable(false);
					dChange.setLocation(400,  200);
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(24, 394, 93, 24);
		contentPane.add(button);
		
		
		
		JButton button_1 = new JButton("删除");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				getM();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "请选择要删除的院系！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				}
				try {
					dbC .getUpdate("delete from tb_spec where specId='" + Integer.valueOf(find) + "'");
					JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE, null);
				} catch (Exception ce) {
					System.out.println(ce.getMessage());
				}
				
				UpdateRecord();
				
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(199, 394, 93, 25);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("退出");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 20));
		button_2.setBounds(364, 394, 93, 25);
		contentPane.add(button_2);
		
		//用户等级判断
		//1为管理员,2为普通用户
		if(MainFrame.level.equals("1")) {
			//不隐藏任何功能
		} else if (MainFrame.level.equals("2")) {
			//隐藏修改和删除功能
			button.setVisible(false);
			button_1.setVisible(false);
		}
		
		// 选择专业表
		sql = "select * from tb_spec";
		
		//更新表格
		UpdateRecord();
		
	}


	//更新表格
	public void UpdateRecord() {
		
		Vector vector = new Vector(1,1);
		
		model = new DefaultTableModel(rowdata, columnName);
		table = new JTable(model);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 58, 435, 312);
		table.setRowHeight(30);
	    table.setFont(new Font("宋体", Font.PLAIN, 12));
	    scrollPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN));
		
        try {
			ResultSet rs = dbC.getRS(sql);
			while (rs.next()) {
				vector = new Vector();
				vector.add(String.valueOf(rs.getInt("specId")));
				vector.add(rs.getString("departName"));
				vector.add(rs.getString("specName"));
				model.addRow(vector);
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
	
	
	// 获取所选的行
	public void getM() {
		
		row = table.getSelectedRow();

		if (row == -1) {
			return;
		}
		try {
			find = model.getValueAt(row, 0).toString().trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
