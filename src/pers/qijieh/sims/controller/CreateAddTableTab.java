package pers.qijieh.sims.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import pers.qijieh.sims.resources.IconGroup;
import pers.qijieh.sims.view.AddPanel;

public class CreateAddTableTab implements ActionListener {
	JTabbedPane jTabbedPane;
	
	public CreateAddTableTab(JTabbedPane jTabbedPane) {
		this.jTabbedPane = jTabbedPane;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		jTabbedPane.add("Ôö¼Ó", new AddPanel(jTabbedPane));
		jTabbedPane.setSelectedIndex(jTabbedPane.getTabCount()-1);
		jTabbedPane.setIconAt(jTabbedPane.getSelectedIndex(), IconGroup.emptyIcon);
	}
	
}
