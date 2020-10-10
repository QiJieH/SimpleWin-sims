package pers.qijieh.sims.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

public class BackHome implements ActionListener {
	JTabbedPane jTabbedPane;
	
	public BackHome(JTabbedPane jTabbedPane) {
		this.jTabbedPane = jTabbedPane;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		jTabbedPane.setSelectedIndex(0);
	}

}
