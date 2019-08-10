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
 * Title: ������ 
 * Description: ������ģ�飬�ж�ѧ�����༶��Ժϵ���γ̣��ɼ����û��� ¼�������Ĳ˵�
 * @author ������
 *
 */

public class MainFrame extends JFrame {

	JPanel contentPane;
	public static String level;
	static String name;
	
	//Сͼ��
	Image icon3 = Toolkit.getDefaultToolkit().getImage("img\\icon3.jpg");
	
	//����ͼƬ
	ImageIcon mainBg = new ImageIcon("img\\������ͼƬ.jpg");
	JLabel mainBgImage = new JLabel(mainBg);
	
	JLabel jLabel_welcomeTitle = new JLabel();
	JLabel jLabel_help1 = new JLabel();
	JLabel jLabel_help2 = new JLabel();
	JLabel jLabel_help3 = new JLabel();
	
	JMenuBar jMenuBar = new JMenuBar();
	//ѧ��
	JMenu jMenu_stuManager = new JMenu();
	JMenuItem jMenuItem_stuInfoSignUp = new JMenuItem();
	JMenuItem jMenuItem_stuInfoInquire = new JMenuItem();
	//�༶
	JMenu jMenu_classManager = new JMenu();
	JMenuItem jMenuItem_classInput = new JMenuItem();
	JMenuItem jMenuItem_classManager = new JMenuItem();
	//Ժϵ
	JMenu jMenu_departManager = new JMenu();
	JMenuItem jMenuItem_departInput = new JMenuItem();
	JMenuItem jMenuItem_departManager = new JMenuItem();
	//�γ�
	JMenu jMenu_courceManager = new JMenu();
	JMenuItem jMenuItem_courceInput = new JMenuItem();
	JMenuItem jMenuItem_courceManager = new JMenuItem();
	//����
	JMenu jMenu_scoreManager = new JMenu();
	JMenuItem jMenuItem_scoreInput = new JMenuItem();
	JMenuItem jMenuItem_scoreManager = new JMenuItem();
	//�û�
	JMenu jMenu_userManager = new JMenu();
	JMenuItem jMenuItem_userSignUp = new JMenuItem();
	JMenuItem jMenuItem_userChangePwd = new JMenuItem();
	JMenuItem jMenuItem_userDelete = new JMenuItem();

	//�˳�
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
		//���ñ���ͼƬ
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
	
		//�ô����еĲ��͸��
		JPanel jPanel = (JPanel) this.getContentPane();
		jPanel.setOpaque(false);
		//�ڴ�������ӱ���ͼƬ
		this.getLayeredPane().add(mainBgImage, new Integer(Integer.MIN_VALUE));
	}
	

	private void Init() {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		this.setJMenuBar(jMenuBar);
		setSize(new Dimension(860, 575));
		setLocationRelativeTo(null);
		setTitle("��ӭ��½ѧ������ϵͳ");
		setIconImage(icon3);
		
		jLabel_welcomeTitle.setText("�� ӭ ʹ �� ѧ �� �� Ϣ �� �� ϵ ͳ");
		jLabel_welcomeTitle.setBounds(new Rectangle(180, 130, 600, 33));
		jLabel_welcomeTitle.setFont(new Font("Dialog", Font.BOLD, 30));
		jLabel_welcomeTitle.setForeground(Color.BLACK);

		jLabel_help1.setFont(new Font("Dialog", Font.BOLD, 20));
		jLabel_help1.setText("�����Զԣ�");
		jLabel_help1.setBounds(new Rectangle(500, 340, 400, 30));
		
		jLabel_help2.setFont(new Font("Dialog", Font.BOLD, 20));
		jLabel_help2.setText("ѧ�����༶��Ժϵ���γ̡��ɼ�");
		jLabel_help2.setBounds(new Rectangle(500, 370, 400, 30));
		
		jLabel_help3.setFont(new Font("Dialog", Font.BOLD, 20));
		jLabel_help3.setText("����¼�롢��ѯ���޸ġ�ɾ���Ĳ���");
		jLabel_help3.setBounds(new Rectangle(500, 400, 400, 30));
		
		jMenu_stuManager.setText("ѧ������");
		jMenuItem_stuInfoSignUp.setText("  ¼��");
		jMenuItem_stuInfoInquire.setText("  ��  ��");
		
		jMenu_classManager.setText("�༶����");
		jMenuItem_classInput.setText("  ¼  ��");
		jMenuItem_classManager.setText("  ��  ��");
		
		jMenu_departManager.setText("Ժϵ����");
		jMenuItem_departInput.setText("  ¼  ��");
		jMenuItem_departManager.setText("  ��  ��");
		
		jMenu_courceManager.setText("�γ̹���");
		jMenuItem_courceInput.setText("  ¼  ��");
		jMenuItem_courceManager.setText("  ��  ��");
		
		jMenu_scoreManager.setText("�ɼ�����");
		jMenuItem_scoreInput.setText("  ¼  ��");
		jMenuItem_scoreManager.setText("  ��  ��");
		
		jMenu_userManager.setText("�û�����");
		jMenuItem_userSignUp.setText("����û�");
		jMenuItem_userChangePwd.setText("�޸�����");
		jMenuItem_userDelete.setEnabled(true);
		jMenuItem_userDelete.setText("ɾ���û�");

		jMenu_quit.setText("�˳�");
		jMenuItem_quit.setText("  ��  ��");
		jMenuItem_reboot.setText("��������");
		
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
		
		//�û��ȼ��ж�
		//1λ����Ա��2Ϊ��ͨ�û�
		if(level.equals("1")) {
			//�������κι���
		}
		else if(level.equals("2")) {
			//���ز��ֹ���
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
		//�˳�
		jMenuItem_quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//����
		jMenuItem_reboot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				login.setResizable(false);
			}
		});
		
		//ѧ��¼��ģ��
		jMenuItem_stuInfoSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentInfoAddFrame  siadd = new StudentInfoAddFrame();
			}
		});
		
		//ѧ������ģ��
		jMenuItem_stuInfoInquire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentManager stuM = new StudentManager();
				stuM.setVisible(true);
				stuM.setBounds(100, 100, 780, 554);
				stuM.setResizable(false);
			}
		});
		
		//�༶¼��ģ��
		jMenuItem_classInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClassFrameAdd classframeadd = new ClassFrameAdd();
				classframeadd.setVisible(true);
				classframeadd.setResizable(false);
			}
		});
		
		//�༶��Ϣ����ģ��
		jMenuItem_classManager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClassManager classManager = new ClassManager();
				classManager.setVisible(true);
				classManager.setResizable(false);
			}
		});
		
		//Ժϵ¼��ģ��
		jMenuItem_departInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DepartAddFrame departAddFrame = new DepartAddFrame();
				departAddFrame.setVisible(true);
				departAddFrame.setResizable(false);
			}
		});
		
		//Ժϵ����ģ��
		jMenuItem_departManager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					DepartManage departManage = new DepartManage();
					departManage.setVisible(true);
					departManage.setResizable(false);
			}
		});
		
		//�γ�¼��ģ��
		jMenuItem_courceInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CourseAddFrame cAddFrame = new CourseAddFrame();
				cAddFrame.setVisible(true);
				cAddFrame.setResizable(false);
			}
		});
		
		//�γ̹���ģ��
		jMenuItem_courceManager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CourseManage cManage = new CourseManage();
				cManage.setVisible(true);
				cManage.setResizable(false);
			}
		});
		
		//�ɼ�¼��ģ��
		jMenuItem_scoreInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ScoreAddFrame scoreAddFrame = new ScoreAddFrame();
				scoreAddFrame.setVisible(true);
				scoreAddFrame.setResizable(false);
			}
		});
		
		
		//�ɼ�����ģ��
		jMenuItem_scoreManager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ScoreManage scoreManage = new ScoreManage();
				scoreManage.setVisible(true);
				scoreManage.setResizable(false);
			}
		});
		
		//����û�ģ��
		jMenuItem_userSignUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserAddFrame uFrame = new UserAddFrame();
				uFrame.setVisible(true);
				uFrame.setResizable(false);
			}
		});
		
		//ɾ���û�ģ��
		jMenuItem_userDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserDelete uDelete = new UserDelete();
				uDelete.setVisible(true);
				uDelete.setResizable(false);
			}
		});
		
		//�û������޸�ģ��
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