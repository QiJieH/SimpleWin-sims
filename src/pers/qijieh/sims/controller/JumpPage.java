package pers.qijieh.sims.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pers.qijieh.sims.datamodel.DataTable;
import pers.qijieh.sims.view.MainView;
import pers.qijieh.sims.viewmodel.PaginationEasy;

public class JumpPage extends KeyAdapter {
	JTextField locationField;
	PaginationEasy pagination;
	JTable jTable;
	int jumpMode;
	String[] conditionStr;
	
	public JumpPage(JTextField locationField, PaginationEasy pagination, JTable jTable, String[] conditionStr, int jumpMode) {
		this.jTable = jTable;
		this.locationField = locationField;
		this.pagination = pagination;
		this.jumpMode = jumpMode;
		this.conditionStr = conditionStr;
	}
	
	public void keyPressed(KeyEvent e){
		int jumpIndex;
		int keycode = e.getKeyCode();
		if(keycode == KeyEvent.VK_ENTER){
			String str = locationField.getText();
			if(str.contains("/")) {
				jumpIndex = Integer.parseInt(str.substring(0, str.indexOf('/')));
			}else {
				jumpIndex = Integer.parseInt(str);
			}
			if(jumpIndex > pagination.getTotalPage() || jumpIndex < 1) {
				MainView.console.append("³¬³öÊý¾Ý·¶Î§");
				return;
			}
			
			// Ê×Ò³ÌøÒ³ 1; ËÑË÷ÌøÒ³ 2;
			DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
			DataTable dt = new DataTable();
			pagination.setCurrentPage(jumpIndex);
			if(jumpMode == 1) {
				try {
					dt = pagination.NormalQuery();
				} catch (SQLException e1) {
					MainView.console.append("·¢Éú´íÎó£º"+e1.getMessage());
				}
			}
			if(jumpMode == 2) {
				try {
					dt = pagination.conditionQuery(conditionStr[0], conditionStr[1]);
				} catch (SQLException e1) {
					MainView.console.append("·¢Éú´íÎó£º"+e1.getMessage());
				}
			}
			locationField.setText(pagination.showLocation());
			tableModel.setDataVector(dt.getRow(), MainView.th);
			jTable.setModel(tableModel);
			jTable.updateUI();
			
			
		}
	}
}
