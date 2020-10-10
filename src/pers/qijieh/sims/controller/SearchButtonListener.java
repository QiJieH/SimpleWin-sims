package pers.qijieh.sims.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import pers.qijieh.sims.resources.IconGroup;
import pers.qijieh.sims.view.SearchResultPanel;

public class SearchButtonListener implements ActionListener {
	JTabbedPane jTabbedPane = new JTabbedPane();
	JTextField jTextField = new JTextField();
	JComboBox<String> comboBox = new JComboBox<String>();
	int role;
	
	public SearchButtonListener(JTabbedPane jTabbedPane, JTextField jTextField, JComboBox<String> comboBox, int role) {
		this.jTabbedPane = jTabbedPane;
		this.jTextField = jTextField;
		this.comboBox = comboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String field = (String) comboBox.getSelectedItem();
		switch (field) {
			case "ѧ��": field = "sid"; break;
			case "����": field = "spwd"; break;
			case "��ɫ": field = "srole"; break;
			case "����": field = "sname"; break;
			case "�Ա�": field = "ssex"; break;
			case "��ѧ": field = "sentry";break;
			case "רҵ": field = "smajor"; break;
			case "ѧԺ": field = "scollage"; break;
			default : break;
		}
		String value = jTextField.getText().trim();
		
		
		if(value == null) {
			return;
		}
		jTabbedPane.add("���", new SearchResultPanel(jTabbedPane, new String[] {field, value}, role));
		jTabbedPane.setSelectedIndex(jTabbedPane.getTabCount()-1);
		jTabbedPane.setIconAt(jTabbedPane.getSelectedIndex(), IconGroup.resultIcon);
	}
	
}
