package pers.qijieh.sims.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pers.qijieh.sims.controller.JumpPage;
import pers.qijieh.sims.controller.ModifyCell;
import pers.qijieh.sims.controller.TablePopupListener;
import pers.qijieh.sims.datamodel.DataTable;
import pers.qijieh.sims.resources.IconGroup;
import pers.qijieh.sims.unit.TableCellListener;
import pers.qijieh.sims.viewmodel.MyTableModel;
import pers.qijieh.sims.viewmodel.PaginationEasy;

public class HomePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyTableModel mTableModel = new MyTableModel();
	PaginationEasy pagination = new PaginationEasy();
	DataTable dt = new DataTable();
	DefaultTableModel tableModel = new DefaultTableModel();
	int role = 3;
	
	JScrollPane tableScrollPane;
		JTable table;
		TableCellListener tcl;
		TableRightPopupMenu tableRightPopupMenu;
	JPanel pageJPanel;
		JButton prePageBtn;
		JTextField locationField;
		JButton nextPageBtn;
	
		

	public HomePanel(JTabbedPane jTabbedPane, int role) {
		this.role = role;
		pagination.setShowItemNum(MainView.rc);
		
		this.setLayout(new BorderLayout(0,0));
			tableScrollPane = new JScrollPane();
				table = new JTable();
				table.getTableHeader().setPreferredSize(new Dimension(1,34));
				
				table.setRowHeight(20);
				tableRightPopupMenu = new TableRightPopupMenu(jTabbedPane, table, pagination, null, null, 1, 1);
				tableRightPopupMenu.closeCurTab.setEnabled(false);
				refreshData(1);
				tcl = new TableCellListener(table, new ModifyCell(table));
			tableScrollPane.add(table);
			tableScrollPane.setViewportView(table);
			pageJPanel = new JPanel();
			pageJPanel.setLayout(new FlowLayout());
				prePageBtn = new JButton(IconGroup.preIcon);
				if(!pagination.hasPre()) {
					prePageBtn.setEnabled(false);
				}
				locationField = new JTextField(5);
				locationField.setHorizontalAlignment(JTextField.CENTER);
				locationField.setText(pagination.showLocation());
				nextPageBtn = new JButton(IconGroup.nextIcon);
				if(!pagination.hasNext()) {
					nextPageBtn.setEnabled(false);
				}
			pageJPanel.add(prePageBtn);
			pageJPanel.add(locationField);
			pageJPanel.add(nextPageBtn);
		this.add(tableScrollPane, BorderLayout.CENTER);
		this.add(pageJPanel, BorderLayout.SOUTH);
		
		
		table.addMouseListener(new TablePopupListener(tableRightPopupMenu));
		nextPageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshData(pagination.getCurrentPage()+1);
				if(!pagination.hasPre()) {
					prePageBtn.setEnabled(false);
				}else {
					prePageBtn.setEnabled(true);
				}
				locationField.setText(pagination.showLocation());
				if(!pagination.hasNext()) {
					nextPageBtn.setEnabled(false);
				}else {
					nextPageBtn.setEnabled(true);
				}
			}
		});
		prePageBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshData(pagination.getCurrentPage()-1);
				if(!pagination.hasPre()) {
					prePageBtn.setEnabled(false);
				}else {
					prePageBtn.setEnabled(true);
				}
				locationField.setText(pagination.showLocation());
				if(!pagination.hasNext()) {
					nextPageBtn.setEnabled(false);
				}else {
					nextPageBtn.setEnabled(true);
				}
			}
		});
		locationField.addKeyListener(new JumpPage(locationField, pagination, table, null, 1));
		
		
		
		// ²Ù×÷È¨ÏÞ
		switch (role) {
			case 1:
				break;
			case 2:
				tableRightPopupMenu.DeleteSelect.setEnabled(false);
				break;
			case 3:
				break;
		}
		
		
		
		
	}
	
	
	private void refreshData(int index) {
		DataTable dt = new DataTable();
		dt = mTableModel.queryData(pagination, null, null, index);
		tableModel.setDataVector(dt.getRow(), MainView.th);
		table.setModel(tableModel);
		table.updateUI();
	}

}
