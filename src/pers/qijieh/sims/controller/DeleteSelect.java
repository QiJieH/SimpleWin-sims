package pers.qijieh.sims.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import pers.qijieh.sims.datamodel.DBManager;
import pers.qijieh.sims.datamodel.DataTable;
import pers.qijieh.sims.datamodel.SQLFormat;
import pers.qijieh.sims.view.MainView;
import pers.qijieh.sims.viewmodel.MyTableModel;
import pers.qijieh.sims.viewmodel.PaginationEasy;

public class DeleteSelect implements ActionListener {
	MyTableModel mTableModel = new MyTableModel();
	JTable table;
	int deleteMode;
	PaginationEasy pagination;
	String sql;
	String[] conditionStr;

	public DeleteSelect(JTable table, PaginationEasy pagination, String[] conditionStr, String sql, int deleteMode) {
		this.table = table;
		this.deleteMode = deleteMode;
		this.pagination = pagination;
		this.sql = sql;
		this.conditionStr = conditionStr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int[] selectedRows = table.getSelectedRows(); // 选中行
		String[][] selectedId = new String[1][selectedRows.length]; // 存储选中行的sid
		
		// 获取表中是否存在主键，主键列位置
		int keyI = -1;
		for(int i=0; i<table.getColumnCount(); i++) {
			if(table.getColumnName(i).equals("Sid") || table.getColumnName(i).equals("学号")) {
				keyI = i;
				break;
			}
		}
		
		// 说明DEV模式没有发现sid主键，无法进行删除操作
		if(keyI == -1) {
			return;
		}
		
		int i=0;
		for (int j : selectedRows) {
			selectedId[0][i] = (java.lang.String) table.getValueAt(j, keyI);
			i++;
		}
		int[] type = new int[selectedRows.length]; // 预处理占位符
		for (int k = 0; k < selectedRows.length; k++) {
			type[k] = java.sql.Types.VARCHAR;
		}

		if (selectedRows.length == 0) {
			// console.append("未选中任何数据\n");
			return;
		}

		// 0：增加删除 1: 首页删除 2：搜索删除 3: DEV删除
		if (deleteMode == 0) {
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			for (int rmRow : selectedRows) {
				System.out.println(rmRow);
				tableModel.removeRow(rmRow);
			}
			table.setModel(tableModel);
			table.updateUI();
		}
		if(deleteMode == 1 || deleteMode == 2) {
			mTableModel.deleteData(selectedId, type);
			DataTable dt = new DataTable();
			if(deleteMode == 1) {
				dt = mTableModel.queryData(pagination, null, null, pagination.getCurrentPage());
			}
			if(deleteMode == 2) {
				dt = mTableModel.queryData(pagination, conditionStr[0], conditionStr[1], pagination.getCurrentPage());
			}
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setDataVector(dt.getRow(), MainView.th);
			table.setModel(tableModel);
			table.updateUI();
		}
		if (deleteMode == 3) {
			DataTable dt = new DataTable();
			DBManager dm = new DBManager();
			@SuppressWarnings("unused")
			boolean flag = false;
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			try {
				flag = dm.updataOrAdd(selectedId, type, SQLFormat.deleteSql(new String[] {"sid"}));
				
			} catch (SQLException e1) {
				e1.printStackTrace();	
			}
			try {
				dm = new DBManager();
				dt = dm.getResultData(null, null, sql);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			

			tableModel.setDataVector(dt.getRow(), dt.getColumn());
			table.setModel(tableModel);
			table.updateUI();
		}
	}
}
