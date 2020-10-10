package pers.qijieh.sims.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Enumeration;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import pers.qijieh.sims.datamodel.DBManager;
import pers.qijieh.sims.datamodel.DataTable;
import pers.qijieh.sims.datamodel.SQLFormat;
import pers.qijieh.sims.log.LogFile;
import pers.qijieh.sims.resources.IconGroup;
import pers.qijieh.sims.unit.BCrypt;

@SuppressWarnings("serial")
public class LoginView extends JFrame {
	LinkedHashMap<String, String> loginUserInfo = new LinkedHashMap<>();
	LogFile lf = new LogFile();
	JPanel contentPane;
		JPanel form;
			JPanel userPanel;
				JLabel userLab;
				JTextField userInput;
			JPanel pwdPanel;
				JLabel pwdLab;
				JPasswordField pwdInput;
		JPanel opreationPanel;
			JButton loginBtn;
			JButton emptyBtn;
		
				
				
	public LoginView() {
		// ������
		try {
			// UI���
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			
			
			
			FontUIResource fontRes = new FontUIResource(new Font("΢���ź�",Font.PLAIN,12));
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
		init();
		// ���ھ�����ʾ
		this.setSize(360,180);
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int screenWidth = screenSize.width;
	    int screenHeight = screenSize.height;
	    int x = (screenWidth - this.getSize().width) / 2;
	    int y = (screenHeight - this.getSize().height) / 2;
	    
	    // ��������
	    this.setTitle("ѧ������ϵͳ��¼");
	    this.setLocation(x, y);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void init() {
		contentPane = new JPanel(new BorderLayout());

		form = new JPanel();
			userPanel = new JPanel(new FlowLayout());
				userLab = new JLabel("ѧ��:");
				userInput = new JTextField(15);
			userPanel.add(userLab);
			userPanel.add(userInput);
			
			pwdPanel = new JPanel(new FlowLayout());
				pwdLab = new JLabel("����:");
				pwdInput = new JPasswordField(15);
			pwdPanel.add(pwdLab);
			pwdPanel.add(pwdInput);
		form.add(userPanel);
		form.add(pwdPanel);
		
		contentPane.add(form, BorderLayout.CENTER);
		
		opreationPanel = new JPanel(new FlowLayout());
			loginBtn = new JButton("��¼", IconGroup.loginIcon);
			emptyBtn = new JButton("���", IconGroup.clearIptIcon);
		opreationPanel.add(loginBtn);
		opreationPanel.add(emptyBtn);
		
		contentPane.add(opreationPanel, BorderLayout.SOUTH);
		
		this.setContentPane(contentPane);
		
		
		
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DataTable dt = new DataTable();
				DBManager dm = new DBManager();
				
				// ���ж��û��Ƿ�����
				if(userInput.getText().trim().length() == 0 || (new String(pwdInput.getPassword())).trim().length() == 0) {
					JOptionPane.showMessageDialog(contentPane, "�������˷Ƿ��ַ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				// ͨ��sid�����Ƿ�����û�
				try {
					dt = dm.getResultData(new String[][] {{userInput.getText()}}, new int[] {Types.VARCHAR}, SQLFormat.baseCondQuery(new String[] {"sid"}));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				// ���Ϊ�յ�¼ʧ��
				if(dt.getRow() == null) {
					JOptionPane.showMessageDialog(contentPane, "��¼ʧ�ܣ�û�и��˺�" , "ʧ��", JOptionPane.WARNING_MESSAGE);
					lf.write("�û����Ե�¼  ���:ʧ��");
					return;
				}
				
				// ��������û���У������
				String[][] result = dt.getRow();
				String plaintext = new String(pwdInput.getPassword());
				String hashed = result[0][1];
				if(!BCrypt.checkpw(plaintext, hashed)) {
					JOptionPane.showMessageDialog(contentPane, "��¼ʧ�ܣ��������" , "ʧ��", JOptionPane.WARNING_MESSAGE);
				}else {
					// ����¼��Ϣ�洢
					String[] field = dt.getColumn();
					for (int i = 0; i < result[0].length; i++) {
						loginUserInfo.put(field[i], result[0][i]);
					}
					lf.write("�û����Ե�¼  ���:�ɹ�");
					// ע����¼����
					dispose();
					// �����û���ɫ�ֶι���������
					new MainView(loginUserInfo);
				}
			}
		});
		
		emptyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userInput.setText("");
				pwdInput.setText("");
			}
		});
		
		
	}
}
