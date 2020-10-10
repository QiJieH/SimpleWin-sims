package pers.qijieh.sims.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pers.qijieh.sims.controller.SubmitListener;
import pers.qijieh.sims.controller.TablePopupListener;
import pers.qijieh.sims.datamodel.DataTable;
import pers.qijieh.sims.unit.TableCellListener;
import pers.qijieh.sims.viewmodel.MyTableModel;
import pers.qijieh.sims.viewmodel.PaginationEasy;




public class AddPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4835975664924672770L;
	MyTableModel mTableModel = new MyTableModel();
	PaginationEasy pagination = null;
	DataTable dt = new DataTable();
	DefaultTableModel tableModel = new DefaultTableModel(new String[MainView.rc][MainView.cc],MainView.th);
	
	JScrollPane tableScrollPane;
		JTable table;
		TableCellListener tcl;
		TableRightPopupMenu tableRightPopupMenu;
	JPanel pageJPanel;
		JButton closeButton;
		JButton confimAddBtn;
	

	public AddPanel(JTabbedPane jTabbedPane) {
		this.setLayout(new BorderLayout(0,0));
			tableScrollPane = new JScrollPane();
				table = new JTable(tableModel);
				table.getTableHeader().setPreferredSize(new Dimension(1,25));
				table.setRowHeight(20);
				tableRightPopupMenu = new TableRightPopupMenu(jTabbedPane, table, pagination, null, null, 0, 0);
			tableScrollPane.add(table);
			tableScrollPane.setViewportView(table);
			pageJPanel = new JPanel();
			pageJPanel.setLayout(new FlowLayout());
				closeButton = new JButton("πÿ±’");
				confimAddBtn = new JButton("Ã·Ωª");
				confimAddBtn.addActionListener(new SubmitListener(table));
			pageJPanel.add(closeButton);
			pageJPanel.add(confimAddBtn);
		this.add(tableScrollPane, BorderLayout.CENTER);
		this.add(pageJPanel, BorderLayout.SOUTH);
		
		
		Color defaultColor = table.getSelectionBackground();
		tcl = new TableCellListener(table, new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1385092635133865973L;

			@Override
			public void actionPerformed(ActionEvent e) {
				table.setSelectionBackground(defaultColor);
			}
		} );
		
		table.addMouseListener(new TablePopupListener(tableRightPopupMenu));
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jTabbedPane.remove(jTabbedPane.getSelectedIndex());
			}
		});
	}
	
	
	

}
