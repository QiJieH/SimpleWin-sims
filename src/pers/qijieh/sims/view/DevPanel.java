package pers.qijieh.sims.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pers.qijieh.sims.controller.ModifyCell;
import pers.qijieh.sims.controller.TablePopupListener;
import pers.qijieh.sims.datamodel.DBManager;
import pers.qijieh.sims.datamodel.DataTable;
import pers.qijieh.sims.unit.TableCellListener;

public class DevPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String sql = new String();
	DataTable dt = new DataTable();
	DefaultTableModel tableModel = new DefaultTableModel();
	
	JScrollPane tableScrollPane;
		JTable table;
		TableCellListener tcl;
		TableRightPopupMenu tableRightPopupMenu;
	JPanel pageJPanel;
		JButton prePageBtn;
		JTextField locationField;
		JButton nextPageBtn;
		JButton closeButton;
	
		

	public DevPanel(JTabbedPane jTabbedPane, String sql, DataTable dt) {
		this.dt = dt;
		this.sql = sql;
		this.tableModel.setDataVector(dt.getRow(), dt.getColumn());
		
		this.setLayout(new BorderLayout(0,0));
			tableScrollPane = new JScrollPane();
				table = new JTable(tableModel);
				table.getTableHeader().setPreferredSize(new Dimension(1,25));
				table.setRowHeight(20);
				tableRightPopupMenu = new TableRightPopupMenu(jTabbedPane, table, null, null,sql, 3, 3);
				tcl = new TableCellListener(table, new ModifyCell(table));
			tableScrollPane.add(table);
			tableScrollPane.setViewportView(table);
			pageJPanel = new JPanel();
			pageJPanel.setLayout(new FlowLayout());
				closeButton = new JButton("¹Ø±Õ");
			pageJPanel.add(closeButton);
		this.add(tableScrollPane, BorderLayout.CENTER);
		this.add(pageJPanel, BorderLayout.SOUTH);
		
		
		table.addMouseListener(new TablePopupListener(tableRightPopupMenu));
		
		
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jTabbedPane.remove(jTabbedPane.getSelectedIndex());
			}
		});
	}
	
	
	@SuppressWarnings("unused")
	private void refreshData(String sql) {
		DataTable dt = new DataTable();
		DBManager dm = new DBManager();
		try {
			dt = dm.getResultData(null, null, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tableModel.setDataVector(dt.getRow(), dt.getColumn());
		table.setModel(tableModel);
		table.updateUI();
	}
	
}
