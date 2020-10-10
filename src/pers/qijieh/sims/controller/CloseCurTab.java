package pers.qijieh.sims.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

public class CloseCurTab implements ActionListener {
	JTabbedPane jTabbedPane;

	public CloseCurTab(JTabbedPane jTabbedPane) {
		this.jTabbedPane = jTabbedPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jTabbedPane.remove(jTabbedPane.getSelectedIndex());
	}
}
