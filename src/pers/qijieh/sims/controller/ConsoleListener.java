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
				// 监控开启
				for(String mod : devMode) {
					if(command.equals(mod)) {
						console.append("\nEnter DEV Mode, You Can Use SQL Format In Console Now :)");
						isDevMode = true;
						break;
					}
				}
			}else {
				// 监控关闭
				for(String mod : outDevMode) {
					if(command.equals(mod)) {
						console.append("\nOuted DEV Mode");
						isDevMode = false;
						break;
					}
				}
				
				// 执行语句
				DBManager dm = new DBManager();
				DataTable dt = new DataTable();
				boolean flag = false;
				
				// 判断操作类型
				for(String op : readOp) {
					if(command.startsWith(op)) {
						try {
							dt = dm.getResultData(null, null, command);
							if(dt != null) {
								jTabbedPane.add("DEV",new DevPanel(jTabbedPane, command, dt));
								jTabbedPane.setSelectedIndex(jTabbedPane.getTabCount()-1);
								console.append("\n执行读语句成功,共计影响条目"+dt.getRowCount()+"条\n");
								lf.write("DEV模式:\n"+command+"\n结果：成功");
							}
						} catch (SQLException e1) {
							console.append("\n执行读语句错误："+e1.getMessage()+"\n");
							lf.write("DEV模式:\n"+command+"\n结果：失败");
						}
						return;
					}
				}
				for(String op : writeOp) {
					if(command.startsWith(op)) {
						try {
							flag = dm.updataOrAdd(null, null, command);
							if(flag) {
								console.append("\n执行写语句成功\n");
								lf.write("DEV模式:\n"+command+"\n结果：成功");
							}else {
								console.append("\n执行语句失败,未知错误\n");
							}
						} catch (SQLException el) {
							console.append("\n执行写语句错误："+ el.getMessage()+"\n");
							lf.write("DEV模式:\n"+command+"\n结果：失败");
						}
						return;
					}
				}
				
				// 如果都没有说明用户输入未知命令
				console.append("\n您输入了未知命令 :(\n");
			}
			
		}
	}
}
