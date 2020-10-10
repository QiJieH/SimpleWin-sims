package pers.qijieh.sims.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pers.qijieh.sims.datamodel.SQLFormat;
import pers.qijieh.sims.view.MainView;
import pers.qijieh.sims.viewmodel.MyTableModel;


/**
 * 事件触发对象 点击提交按钮触发该事件
 * @author QiJieH
 * @return 
 */
public class SubmitListener extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = -245392145629976074L;
	MyTableModel mTableModel = new MyTableModel();
	JTable table;
	
	public SubmitListener(JTable table) {
		this.table = table;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String[]> verified = new ArrayList<String[]>();
		ArrayList<String[]> unVerified = new ArrayList<String[]>();
		ArrayList<String[]> unVerifiedEditedRow = new ArrayList<String[]>();
		String[][] column;
		String[][] residualData;
		String[][] tableData = new String[MainView.rc][MainView.cc];
		boolean operationRes = false;
		
		
		for(int r=0; r<table.getRowCount(); r++)
		{
			boolean flag = true;
			boolean[] verifiedRes = new boolean[table.getColumnCount()];
			String[] row = new String[table.getColumnCount()];
			
			for(int c=0; c<table.getColumnCount(); c++)
			{
				String cell = (String) table.getValueAt(r, c);
				row[c] = cell;
				
				// 对每行的每个字段进行验证并将验证结果存储在布尔数组中
				if(cell == null || cell.trim().length() == 0) {
					verifiedRes[c] = false;
				}else {
					verifiedRes[c] = true;
				}
			}
			
			// 遍历验证布尔数组verifiedRes如果有一个为false即该行不通过
			for(boolean res : verifiedRes) {
				if(!res) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				verified.add(row);
			}else {
				unVerified.add(row);
			}
		}
		
		// 提取未通过验证但是已经编辑过行
		for(String[] str : unVerified) {
			boolean flag = false;
			for(String res : str) {
				if(res != null && res.trim().length() != 0 ) {
					flag = true;
					break;
				}	
			}
			if(flag) {
				unVerifiedEditedRow.add(str);
			}
		}
		
		// 转换
		column = (String[][]) verified.toArray(new String[0][]);  
        residualData =  (String[][]) unVerifiedEditedRow.toArray(new String[0][]);
        
        
        
        // 发起数据库操作
        if(column.length != 0) {
        	try {
				operationRes = mTableModel.insertData(column);
				if(operationRes) {
	            	// 将未通过验证的数据更新到model
	            	System.arraycopy(residualData, 0, tableData, 0, residualData.length);
	            	DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
	                tableModel.setDataVector(tableData, MainView.th);
	                table.updateUI();
	                MainView.console.append(residualData.length+"条未通过验证，已为您重构");
	            }else {
	            	MainView.console.append("添加失败，未知错误\n");
				}
			} catch (Exception e1) {
				String res = e1.getMessage();
				MainView.console.append("添加失败,请确保您的字段是符合要求的 :(\n错误信息："+res+"\n");
				for(int i=0; i<SQLFormat.colName.length; i++) {
					if(res.contains(SQLFormat.colName[i])) {
						table.setColumnSelectionAllowed(true);
						table.setColumnSelectionInterval(i, i);
						table.setRowSelectionInterval(residualData.length, residualData.length);
						table.setSelectionBackground(new Color(217,83,79));
					}
				}
				
			}
            
        }else {
			MainView.console.append("无效提交\n");
		}
        
        
	}
}
