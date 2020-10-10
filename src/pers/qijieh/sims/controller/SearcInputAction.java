package pers.qijieh.sims.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import pers.qijieh.sims.resources.IconGroup;
import pers.qijieh.sims.view.SearchResultPanel;

public class SearcInputAction extends KeyAdapter {
	
	JTabbedPane jTabbedPane = new JTabbedPane();
	JTextField jTextField = new JTextField();
	JComboBox<String> comboBox = new JComboBox<String>();
	int role;
	
	public SearcInputAction(JTabbedPane jTabbedPane, JTextField jTextField, JComboBox<String> comboBox, int role) {
		this.jTabbedPane = jTabbedPane;
		this.jTextField = jTextField;
		this.comboBox = comboBox;
		this.role = role;
	}
	
	public void keyPressed(KeyEvent e){
		int keycode = e.getKeyCode();
		if(keycode == KeyEvent.VK_ENTER){
			String field = (String) comboBox.getSelectedItem();
			switch (field) {
				case "学号": field = "sid"; break;
				case "密码": field = "spwd"; break;
				case "角色": field = "srole"; break;
				case "姓名": field = "sname"; break;
				case "性别": field = "ssex"; break;
				case "入学": field = "sentry";break;
				case "专业": field = "smajor"; break;
				case "学院": field = "scollage"; break;
				default : break;
			}
			String value = jTextField.getText().trim();
			
			
			if(value == null) {
				return;
			}
			jTabbedPane.add("结果", new SearchResultPanel(jTabbedPane, new String[] {field, value}, role));
			jTabbedPane.setSelectedIndex(jTabbedPane.getTabCount()-1);
			jTabbedPane.setIconAt(jTabbedPane.getSelectedIndex(), IconGroup.resultIcon);
		}
	}
}
