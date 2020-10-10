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
 * �¼��������� ����ύ��ť�������¼�
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
				
				// ��ÿ�е�ÿ���ֶν�����֤������֤����洢�ڲ���������
				if(cell == null || cell.trim().length() == 0) {
					verifiedRes[c] = false;
				}else {
					verifiedRes[c] = true;
				}
			}
			
			// ������֤��������verifiedRes�����һ��Ϊfalse�����в�ͨ��
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
		
		// ��ȡδͨ����֤�����Ѿ��༭����
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
		
		// ת��
		column = (String[][]) verified.toArray(new String[0][]);  
        residualData =  (String[][]) unVerifiedEditedRow.toArray(new String[0][]);
        
        
        
        // �������ݿ����
        if(column.length != 0) {
        	try {
				operationRes = mTableModel.insertData(column);
				if(operationRes) {
	            	// ��δͨ����֤�����ݸ��µ�model
	            	System.arraycopy(residualData, 0, tableData, 0, residualData.length);
	            	DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
	                tableModel.setDataVector(tableData, MainView.th);
	                table.updateUI();
	                MainView.console.append(residualData.length+"��δͨ����֤����Ϊ���ع�");
	            }else {
	            	MainView.console.append("���ʧ�ܣ�δ֪����\n");
				}
			} catch (Exception e1) {
				String res = e1.getMessage();
				MainView.console.append("���ʧ��,��ȷ�������ֶ��Ƿ���Ҫ��� :(\n������Ϣ��"+res+"\n");
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
			MainView.console.append("��Ч�ύ\n");
		}
        
        
	}
}
