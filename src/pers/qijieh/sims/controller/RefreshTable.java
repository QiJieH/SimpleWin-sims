package pers.qijieh.sims.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pers.qijieh.sims.datamodel.DBManager;
import pers.qijieh.sims.datamodel.DataTable;
import pers.qijieh.sims.view.MainView;
import pers.qijieh.sims.viewmodel.MyTableModel;
import pers.qijieh.sims.viewmodel.PaginationEasy;

public class RefreshTable implements ActionListener {
	MyTableModel mTableModel = new MyTableModel();
	JTable table;
	PaginationEasy pagination;
	String[] conditionStr;
	String sql;
	int refreshMode;
	
	public RefreshTable(JTable table, PaginationEasy pagination, String[] conditionStr,String sql ,int refreshMode) {
		this.table = table;
		this.refreshMode = refreshMode;
		this.pagination = pagination;
		this.conditionStr = conditionStr;
		this.sql = sql;
	}
	
	// 0：增加刷新 1: 首页刷新 2：搜索刷新 3: DEV刷新
	@Override
	public void actionPerformed(ActionEvent e) {
		if (refreshMode == 0) {
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			table.setModel(tableModel);
			table.updateUI();
		}
		if (refreshMode == 1) {
			DataTable dt = new DataTable();
			dt = mTableModel.queryData(pagination, null, null, pagination.getCurrentPage());
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setDataVector(dt.getRow(), MainView.th);
			table.setModel(tableModel);
			table.updateUI();
		}
		if (refreshMode == 2) {
			DataTable dt = new DataTable();
			dt = mTableModel.queryData(pagination, conditionStr[0], conditionStr[1], pagination.getCurrentPage());
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			tableModel.setDataVector(dt.getRow(), MainView.th);
			table.setModel(tableModel);
			table.updateUI();
		}
		if (refreshMode ==3) {
			DataTable dt = new DataTable();
			DBManager dm = new DBManager();
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			try {
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
