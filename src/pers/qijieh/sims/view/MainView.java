package pers.qijieh.sims.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.FontUIResource;

import pers.qijieh.sims.controller.BackHome;
import pers.qijieh.sims.controller.ConsoleListener;
import pers.qijieh.sims.controller.CreateAddTableTab;
import pers.qijieh.sims.controller.LogOutAction;
import pers.qijieh.sims.controller.OpenLogFileAction;
import pers.qijieh.sims.controller.SearcInputAction;
import pers.qijieh.sims.controller.SearchButtonListener;
import pers.qijieh.sims.datamodel.DBManager;
import pers.qijieh.sims.datamodel.SQLFormat;
import pers.qijieh.sims.log.LogFile;
import pers.qijieh.sims.resources.IconGroup;

@SuppressWarnings("serial")
public class MainView extends JFrame {
	LogFile lf = new LogFile();
	public static String[] th = {"ѧ��","����","��ɫ","����","�Ա�","��ѧʱ��","רҵ","ѧԺ"};
	public static int rc = 16;
	public static int cc = th.length;
	private  LinkedHashMap<String, String> loginUserInfo = new LinkedHashMap<String,String>();
	int role = 3;
	
	// ����
	Font font_1 = new Font("Dialog",Font.PLAIN,12);
	Font font_2 = new Font("΢���ź�",Font.PLAIN,12);
	
	JMenuBar menuBar;
		JMenu menu1;
			JMenuItem item1_1;
			JMenuItem item1_2;
			JMenuItem item1_3;
			JMenuItem item1_4;
		JMenu menu2;
			JMenuItem item2_1;
			JMenuItem item2_2;
			JMenuItem item2_3;
			JMenuItem item2_4;
		JPanel searchJPanel;
			JTextField searcInput;
			JComboBox<String> comBox;
			JButton searchBtn;
		JMenu menu3;
	
	JPanel contePanel;
		JTabbedPane tableJTabbedPane;
		JTabbedPane consoleJTabbedPane;
			JScrollPane consoleScrollPane;
				// ������̨��¶�ӿ�
				public static JTextArea console = new JTextArea(5,40);;
				
				
	public MainView(LinkedHashMap<String, String> loginUserInfo) 
	{
		// ��¼��Ϣ
		this.loginUserInfo = loginUserInfo;
		

		// ������
		try {
			// UI���
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			
			
			
			FontUIResource fontRes = new FontUIResource(font_2);
		    for(@SuppressWarnings("rawtypes")
			Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();){
		        Object key = keys.nextElement();
		        Object value = UIManager.get(key);
		        if(value instanceof FontUIResource)
		            UIManager.put(key, fontRes);
		    }		
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		
		// ���캯��
		init();
		// ���ھ�����ʾ
		this.setSize(900,600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int screenWidth = screenSize.width;
	    int screenHeight = screenSize.height;
	    int x = (screenWidth - this.getSize().width) / 2;
	    int y = (screenHeight - this.getSize().height) / 2;
	    
	    // ��������
	    this.setTitle("ѧ����Ϣ��");
	    this.setLocation(x, y);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
		
	private void init()
	{ 
	    menuBar = new JMenuBar();
			menu1 = new JMenu("�˵�");
			menu1.setIcon(IconGroup.menuIcon);
				item1_1 = new JMenuItem("������ҳ", IconGroup.homeIcon);
				item1_2 = new JMenuItem("��������", IconGroup.addIcon);
				item1_3 = new JMenuItem("��������", IconGroup.importIcon);
				item1_4 = new JMenuItem("��������", IconGroup.exportIcon);
			menu2 = new JMenu("����");
			menu2.setIcon(IconGroup.settingIcon);
				item2_1 = new JMenuItem("ע����½", IconGroup.unloginIcon);
				item2_2 = new JMenuItem("����־", IconGroup.logIcon);
				item2_3 = new JMenuItem("��������", IconGroup.connectIcon);
				item2_4 = new JMenuItem("�������", IconGroup.githubIcon);
				searchJPanel = new JPanel();
				searchJPanel.setBackground(new Color(255,255,255));
				searchJPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
					searcInput = new JTextField(20);
					comBox = new JComboBox<String>();
						comBox.addItem("ѧ��");
						comBox.addItem("��ɫ");
						comBox.addItem("����");
						comBox.addItem("�Ա�");
						comBox.addItem("��ѧ");
						comBox.addItem("רҵ");
						comBox.addItem("ѧԺ");
					searchBtn = new JButton("����");
					searchBtn.setIcon(IconGroup.searchIcon);
				searchJPanel.add(searcInput);
				searchJPanel.add(comBox);
				searchJPanel.add(searchBtn);
			menu3 = new JMenu("����");
			menu3.setIcon(IconGroup.helpIcon);
			menu1.add(item1_1);
			menu1.add(item1_2);
			menu1.add(item1_3);
			menu1.add(item1_4);
			menu2.add(item2_1);
			menu2.add(item2_2);
			menu2.add(item2_3);
			menu2.add(item2_4);
		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(searchJPanel);
		
		
		this.setJMenuBar(menuBar);
		
		
		contePanel = new JPanel();
		contePanel.setBorder(new EmptyBorder(5,5,5,5));
		contePanel.setLayout(new BorderLayout(0,0));
			tableJTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
			//tableJTabbedPane.addTab("��ҳ", new HomePanel(tableJTabbedPane));
		contePanel.add(tableJTabbedPane,BorderLayout.CENTER);
			consoleJTabbedPane = new JTabbedPane();
				consoleScrollPane = new JScrollPane();
					//console = new JTextArea(5,50);
					console.setCaretPosition(console.getText().length());
					console.setFont(font_2);
				consoleScrollPane.add(console);
				consoleScrollPane.setViewportView(console);
			consoleJTabbedPane.add("����̨",consoleScrollPane);
			consoleJTabbedPane.setIconAt(0, IconGroup.consoleIcon);
		contePanel.add(consoleJTabbedPane, BorderLayout.SOUTH);
		
		
		// ���ʶ��
		if(loginUserInfo != null) {
			console.append(loginUserInfo.get("Sname")+",��ӭʹ��ѧ������ϵͳ,����Ȩ��Ϊ��"+loginUserInfo.get("Srole")+"��\n");
			lf.write("�������ϵͳ\n���:"+loginUserInfo.get("Sid")+" "+loginUserInfo.get("Sname")+"\nȨ��:"+loginUserInfo.get("Srole"));
			authDefine();
		}else {
			console.append("����ģʽ\n");
			tableJTabbedPane.addTab("��ҳ", new HomePanel(tableJTabbedPane, role));
			tableJTabbedPane.setIconAt(0, IconGroup.homeIcon);
		}
		
	
		
		// �¼�����
		item1_1.addActionListener(new BackHome(tableJTabbedPane));
		item1_2.addActionListener(new CreateAddTableTab(tableJTabbedPane));
		item2_1.addActionListener(new LogOutAction(this));
		item2_2.addActionListener(new OpenLogFileAction(lf));
		searcInput.addKeyListener(new SearcInputAction(tableJTabbedPane, searcInput, comBox, role));
		searchBtn.addActionListener(new SearchButtonListener(tableJTabbedPane, searcInput, comBox, role));
		console.addKeyListener(new ConsoleListener(console, tableJTabbedPane));
		item2_4.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutUs();
			}
		});
		item1_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileSystemView fsv = FileSystemView.getFileSystemView();
				File com=fsv.getHomeDirectory(); 
				DBManager dm = new DBManager();
				String path = new String();
				path.replaceAll("\\", "/");
				try {
					dm.getResultData(null, null, SQLFormat.OutData2File(com.getPath()+"/out.txt"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(item1_4, "�����ɹ�" , "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		this.setContentPane(contePanel);
	}
	
	
	// ���Ȩ��
	private void authDefine() {
		switch (loginUserInfo.get("Srole")) {
			case "ѧ��":
				item1_1.setEnabled(false);
				item1_2.setEnabled(false);
				item1_3.setEnabled(false);
				item1_4.setEnabled(false);
				item2_2.setEnabled(false);
				item2_3.setEnabled(false);
				searcInput.setEditable(false);
				searchBtn.setEnabled(false);
				console.setEditable(false);
				role = 1;
				tableJTabbedPane.addTab("��ҳ", new SearchResultPanel(tableJTabbedPane, new String[] {"sid",loginUserInfo.get("Sid")}, role));
				tableJTabbedPane.setIconAt(0, IconGroup.homeIcon);
				break;
			case "��ʦ":
				role = 2;
				tableJTabbedPane.addTab("��ҳ", new HomePanel(tableJTabbedPane, role));
				tableJTabbedPane.setIconAt(0, IconGroup.homeIcon);
				console.setEditable(false);
				break;
			case "����Ա":
				role = 3;
				tableJTabbedPane.addTab("��ҳ", new HomePanel(tableJTabbedPane, role));
				tableJTabbedPane.setIconAt(0, IconGroup.homeIcon);
				console.setEditable(true);
				break;
		}
	}
	
	
}
