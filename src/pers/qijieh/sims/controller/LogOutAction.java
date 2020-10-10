package pers.qijieh.sims.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pers.qijieh.sims.view.LoginView;

public class LogOutAction implements ActionListener {
	JFrame jFrame;
	
	public LogOutAction(JFrame jFrame) {
		this.jFrame = jFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int isConfim = JOptionPane.showConfirmDialog(jFrame, "��ȷ����Ҫע����¼?", "����ȷ��", JOptionPane.YES_NO_OPTION);
		if(isConfim == JOptionPane.YES_NO_OPTION) {
			jFrame.dispose();
			new LoginView();
		}else {
			return;
		}
		
	}

}
