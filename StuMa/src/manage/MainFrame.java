package manage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import classs.ClassFrameAdd;
import classs.ClassManager;
import course.CourseAddFrame;
import course.CourseManage;
import depart.DepartAddFrame;
import depart.DepartManage;
import score.ScoreAddFrame;
import score.ScoreManage;
import student.StudentInfoAddFrame;
import student.StudentManager;
import user.UserAddFrame;
import user.UserDelete;
import user.UserPassword;

/**
 * Title: 主界面 
 * Description: 主界面模块，有对学生，班级，院系，课程，成绩，用户的 录入与管理的菜单
 * @author 程梦月
 *
 */

public class MainFrame extends JFrame {

	JPanel contentPane;
	public static String level;
	static String name;
	
	//小图标
	Image icon3 = Toolkit.getDefaultToolkit().getImage("img\\icon3.jpg");
	
	//背景图片
	ImageIcon mainBg = new ImageIcon("img\\主界面图片.jpg");
	JLabel mainBgImage = new JLabel(mainBg);
	
	JLabel jLabel_welcomeTitle = new JLabel();
	JLabel jLabel_help1 = new JLabel();
	JLabel jLabel_help2 = new JLabel();
	JLabel jLabel_help3 = new JLabel();
	
	JMenuBar jMenuBar = new JMenuBar();
	//学生
	JMenu jMenu_stuManager = new JMenu();
	JMenuItem jMenuItem_stuInfoSignUp = new JMenuItem();
	JMenuItem jMenuItem_stuInfoInquire = new JMenuItem();
	//班级
	JMenu jMenu_classManager = new JMenu();
	JMenuItem jMenuItem_classInput = new JMenuItem();
	JMenuItem jMenuItem_classManager = new JMenuItem();
	//院系
	JMenu jMenu_departManager = new JMenu();
	JMenuItem jMenuItem_departInput = new JMenuItem();
	JMenuItem jMenuItem_departManager = new JMenuItem();
	//课程
	JMenu jMenu_courceManager = new JMenu();
	JMenuItem jMenuItem_courceInput = new JMenuItem();
	JMenuItem jMenuItem_courceManager = new JMenuItem();
	//分数
	JMenu jMenu_scoreManager = new JMenu();
	JMenuItem jMenuItem_scoreInput = new JMenuItem();
	JMenuItem jMenuItem_scoreManager = new JMenuItem();
	//用户
	JMenu jMenu_userManager = new JMenu();
	JMenuItem jMenuItem_userSignUp = new JMenuItem();
	JMenuItem jMenuItem_userChangePwd = new JMenuItem();
	JMenuItem jMenuItem_userDelete = new JMenuItem();

	//退出
	JMenu jMenu_quit = new JMenu();
	JMenuItem jMenuItem_quit = new JMenuItem();
	JMenuItem jMenuItem_reboot = new JMenuItem();
	
	JPanel jPanel_box = new JPanel();
	//BorderLayout borderLayout = new BorderLayout();
	
	JOptionPane jOptionPane = new JOptionPane();
	
	public MainFrame (String level,String name){
		this.level = level;
		this.name = name;
		Init();
		tou();
		addActionListener1();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void tou() {
		//设置背景图片
		mainBgImage.setBounds(0, 0, mainBg.getIconWidth(), mainBg.getIconHeight());
		
		jLabel_welcomeTitle.setOpaque(false);
		jLabel_help1.setOpaque(false);
		jLabel_help2.setOpaque(false);
		jLabel_help3.setOpaque(false);
		
		jPanel_box.setOpaque(false);
		jMenu_stuManager.setOpaque(false);
		jMenu_classManager.setOpaque(false);
		jMenu_departManager.setOpaque(false); 
		jMenu_courceManager.setOpaque(false); 
		jMenu_scoreManager.setOpaque(false);
		jMenu_userManager.setOpaque(false); 
		jMenu_quit.setOpaque(false);
	
		//让窗口中的层次透明
		JPanel jPanel = (JPanel) this.getContentPane();
		jPanel.setOpaque(false);
		//在窗体中添加背景图片
		this.getLayeredPane().add(mainBgImage, new Integer(Integer.MIN_VALUE));
	}
	

	private void Init() {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		this.setJMenuBar(jMenuBar);
		setSize(new Dimension(860, 575));
		setLocationRelativeTo(null);
		setTitle("欢迎登陆学生管理系统");
		setIconImage(icon3);
		
		jLabel_welcomeTitle.setText("欢 迎 使 用 学 生 信 息 管 理 系 统");
		jLabel_welcomeTitle.setBounds(new Rectangle(180, 130, 600, 33));
		jLabel_welcomeTitle.setFont(new Font("Dialog", Font.BOLD, 30));
		jLabel_welcomeTitle.setForeground(Color.BLACK);

		jLabel_help1.setFont(new Font("Dialog", Font.BOLD, 20));
		jLabel_help1.setText("您可以对：");
		jLabel_help1.setBounds(new Rectangle(500, 340, 400, 30));
		
		jLabel_help2.setFont(new Font("Dialog", Font.BOLD, 20));
		jLabel_help2.setText("学生、班级、院系、课程、成绩");
		jLabel_help2.setBounds(new Rectangle(500, 370, 400, 30));
		
		jLabel_help3.setFont(new Font("Dialog", Font.BOLD, 20));
		jLabel_help3.setText("进行录入、查询、修改、删除的操作");
		jLabel_help3.setBounds(new Rectangle(500, 400, 400, 30));
		
		jMenu_stuManager.setText("学生管理");
		jMenuItem_stuInfoSignUp.setText("  录入");
		jMenuItem_stuInfoInquire.setText("  管  理");
		
		jMenu_classManager.setText("班级管理");
		jMenuItem_classInput.setText("  录  入");
		jMenuItem_classManager.setText("  管  理");
		
		jMenu_departManager.setText("院系管理");
		jMenuItem_departInput.setText("  录  入");
		jMenuItem_departManager.setText("  管  理");
		
		jMenu_courceManager.setText("课程管理");
		jMenuItem_courceInput.setText("  录  入");
		jMenuItem_courceManager.setText("  管  理");
		
		jMenu_scoreManager.setText("成绩管理");
		jMenuItem_scoreInput.setText("  录  入");
		jMenuItem_scoreManager.setText("  管  理");
		
		jMenu_userManager.setText("用户管理");
		jMenuItem_userSignUp.setText("添加用户");
		jMenuItem_userChangePwd.setText("修改密码");
		jMenuItem_userDelete.setEnabled(true);
		jMenuItem_userDelete.setText("删除用户");

		jMenu_quit.setText("退出");
		jMenuItem_quit.setText("  退  出");
		jMenuItem_reboot.setText("重新启动");
		
		contentPane.add(jLabel_welcomeTitle);
		contentPane.add(jLabel_help1);
		contentPane.add(jLabel_help2);
		contentPane.add(jLabel_help3);
		contentPane.add(jPanel_box);
		
		jMenuBar.add(jMenu_stuManager);
		jMenuBar.add(jMenu_classManager);
		jMenuBar.add(jMenu_departManager);
		jMenuBar.add(jMenu_courceManager);
		jMenuBar.add(jMenu_scoreManager);
		jMenuBar.add(jMenu_userManager);
		jMenuBar.add(jMenu_quit);
		
		jMenu_stuManager.add(jMenuItem_stuInfoSignUp);
		jMenu_stuManager.add(jMenuItem_stuInfoInquire);

		jMenu_classManager.add(jMenuItem_classInput);
		jMenu_classManager.add(jMenuItem_classManager);

		jMenu_departManager.add(jMenuItem_departInput);
		jMenu_departManager.add(jMenuItem_departManager);

		jMenu_courceManager.add(jMenuItem_courceInput);
		jMenu_courceManager.add(jMenuItem_courceManager);

		jMenu_scoreManager.add(jMenuItem_scoreInput);
		jMenu_scoreManager.add(jMenuItem_scoreManager);

		jMenu_userManager.add(jMenuItem_userSignUp);
		jMenu_userManager.add(jMenuItem_userDelete);
		jMenu_userManager.add(jMenuItem_userChangePwd);

		jMenu_quit.add(jMenuItem_quit);
		jMenu_quit.add(jMenuItem_reboot);
		
		//用户等级判断
		//1位管理员，2为普通用户
		if(level.equals("1")) {
			//不隐藏任何功能
		}
		else if(level.equals("2")) {
			//隐藏部分功能
			this.jMenuItem_userSignUp.setVisible(false);
			this.jMenuItem_userDelete.setVisible(false);
			this.jMenuItem_stuInfoSignUp.setVisible(false);
			this.jMenuItem_classInput.setVisible(false);
			this.jMenuItem_departInput.setVisible(false);
			this.jMenuItem_courceInput.setVisible(false);
			this.jMenuItem_scoreInput.setVisible(false);
		}
	}
	

	public void addActionListener1() {
		//退出
		jMenuItem_quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//重启
		jMenuItem_reboot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				login.setResizable(false);
			}
		});
		
		//学生录入模块
		jMenuItem_stuInfoSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentInfoAddFrame  siadd = new StudentInfoAddFrame();
			}
		});
		
		//学生管理模块
		jMenuItem_stuInfoInquire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentManager stuM = new StudentManager();
				stuM.setVisible(true);
				stuM.setBounds(100, 100, 780, 554);
				stuM.setResizable(false);
			}
		});
		
		//班级录入模块
		jMenuItem_classInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClassFrameAdd classframeadd = new ClassFrameAdd();
				classframeadd.setVisible(true);
				classframeadd.setResizable(false);
			}
		});
		
		//班级信息管理模块
		jMenuItem_classManager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClassManager classManager = new ClassManager();
				classManager.setVisible(true);
				classManager.setResizable(false);
			}
		});
		
		//院系录入模块
		jMenuItem_departInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DepartAddFrame departAddFrame = new DepartAddFrame();
				departAddFrame.setVisible(true);
				departAddFrame.setResizable(false);
			}
		});
		
		//院系管理模块
		jMenuItem_departManager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					DepartManage departManage = new DepartManage();
					departManage.setVisible(true);
					departManage.setResizable(false);
			}
		});
		
		//课程录入模块
		jMenuItem_courceInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CourseAddFrame cAddFrame = new CourseAddFrame();
				cAddFrame.setVisible(true);
				cAddFrame.setResizable(false);
			}
		});
		
		//课程管理模块
		jMenuItem_courceManager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CourseManage cManage = new CourseManage();
				cManage.setVisible(true);
				cManage.setResizable(false);
			}
		});
		
		//成绩录入模块
		jMenuItem_scoreInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ScoreAddFrame scoreAddFrame = new ScoreAddFrame();
				scoreAddFrame.setVisible(true);
				scoreAddFrame.setResizable(false);
			}
		});
		
		
		//成绩管理模块
		jMenuItem_scoreManager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ScoreManage scoreManage = new ScoreManage();
				scoreManage.setVisible(true);
				scoreManage.setResizable(false);
			}
		});
		
		//添加用户模块
		jMenuItem_userSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserAddFrame uFrame = new UserAddFrame();
				uFrame.setVisible(true);
				uFrame.setResizable(false);
			}
		});
		
		//删除用户模块
		jMenuItem_userDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserDelete uDelete = new UserDelete();
				uDelete.setVisible(true);
				uDelete.setResizable(false);
			}
		});
		
		//用户密码修改模块
		jMenuItem_userChangePwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserPassword uPassword = new UserPassword(level, name);
				uPassword.setVisible(true);
				uPassword.setResizable(false);
			}
		});
		
	}
	
}