package pers.qijieh.sims.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import pers.qijieh.sims.datamodel.DBManager;
import pers.qijieh.sims.datamodel.DataTable;
import pers.qijieh.sims.log.LogFile;
import pers.qijieh.sims.view.DevPanel;

public class ConsoleListener extends KeyAdapter {
	LogFile lf = new LogFile();
	
	JTabbedPane jTabbedPane;
	JTextArea  console;
	boolean isDevMode = false;
	String[] devMode = new String[] {"mysql","MYSQL","MySQL"};
	String[] outDevMode = new String[] {"quit","QUIT","Quit"};
	
	String[] writeOp = new String[] {"update","insert"};
	String[] readOp = new String[] {"select"};
	
	public ConsoleListener(JTextArea  console, JTabbedPane jTabbedPane) {
		this.console = console;
		this.jTabbedPane = jTabbedPane;
	}
	
	public void keyPressed(KeyEvent e){
		int keycode = e.getKeyCode();
		if(keycode == KeyEvent.VK_ENTER){
			String his = console.getText();
			String command = his.substring(his.lastIndexOf('\n')+1);
			
			if(!isDevMode) {
				// ��ؿ���
				for(String mod : devMode) {
					if(command.equals(mod)) {
						console.append("\nEnter DEV Mode, You Can Use SQL Format In Console Now :)");
						isDevMode = true;
						break;
					}
				}
			}else {
				// ��عر�
				for(String mod : outDevMode) {
					if(command.equals(mod)) {
						console.append("\nOuted DEV Mode");
						isDevMode = false;
						break;
					}
				}
				
				// ִ�����
				DBManager dm = new DBManager();
				DataTable dt = new DataTable();
				boolean flag = false;
				
				// �жϲ�������
				for(String op : readOp) {
					if(command.startsWith(op)) {
						try {
							dt = dm.getResultData(null, null, command);
							if(dt != null) {
								jTabbedPane.add("DEV",new DevPanel(jTabbedPane, command, dt));
								jTabbedPane.setSelectedIndex(jTabbedPane.getTabCount()-1);
								console.append("\nִ�ж����ɹ�,����Ӱ����Ŀ"+dt.getRowCount()+"��\n");
								lf.write("DEVģʽ:\n"+command+"\n������ɹ�");
							}
						} catch (SQLException e1) {
							console.append("\nִ�ж�������"+e1.getMessage()+"\n");
							lf.write("DEVģʽ:\n"+command+"\n�����ʧ��");
						}
						return;
					}
				}
				for(String op : writeOp) {
					if(command.startsWith(op)) {
						try {
							flag = dm.updataOrAdd(null, null, command);
							if(flag) {
								console.append("\nִ��д���ɹ�\n");
								lf.write("DEVģʽ:\n"+command+"\n������ɹ�");
							}else {
								console.append("\nִ�����ʧ��,δ֪����\n");
							}
						} catch (SQLException el) {
							console.append("\nִ��д������"+ el.getMessage()+"\n");
							lf.write("DEVģʽ:\n"+command+"\n�����ʧ��");
						}
						return;
					}
				}
				
				// �����û��˵���û�����δ֪����
				console.append("\n��������δ֪���� :(\n");
			}
			
		}
	}
}
