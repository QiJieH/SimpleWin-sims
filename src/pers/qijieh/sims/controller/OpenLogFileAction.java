package pers.qijieh.sims.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pers.qijieh.sims.log.LogFile;

public class OpenLogFileAction implements ActionListener {
	LogFile lf;
	
	public OpenLogFileAction(LogFile lf) {
		this.lf = lf;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		lf.open();
	}

}
