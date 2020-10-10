package pers.qijieh.sims.controller;

import java.awt.event.ActionEvent;
import java.sql.Types;

import javax.swing.AbstractAction;
import javax.swing.JTable;

import pers.qijieh.sims.datamodel.SQLFormat;
import pers.qijieh.sims.unit.BCrypt;
import pers.qijieh.sims.unit.TableCellListener;
import pers.qijieh.sims.viewmodel.MyTableModel;

public class ModifyCell extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyTableModel mTableModel = new MyTableModel();
	private JTable table;
	
	public ModifyCell(JTable table) {
		this.table = table;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		TableCellListener tcl = (TableCellListener) e.getSource();
		String primaryKey = (String) table.getValueAt(tcl.getRow(), 0);
		String modifyField = table.getColumnName(tcl.getColumn());
		String oldValue = (String) tcl.getOldValue();
		String newValue = (String) tcl.getNewValue();
		// ����޸�����
		if(modifyField == SQLFormat.getPrimaryKey() || modifyField == "ѧ��") {
			primaryKey = oldValue;
		}
		String sql = SQLFormat.updateSql("sid", modifyField, newValue);
		// ����޸����룬���ֶμ���
		if(modifyField == "����" || modifyField == "Spwd") {
			newValue = BCrypt.hashpw(newValue, BCrypt.gensalt());
		}
		String[][] column = new String[][] {{newValue, primaryKey}};
		int[] type = new int[] {Types.VARCHAR, Types.VARCHAR};
		// ȷ���޸��ֶε���������
		switch (modifyField) {
			case "ѧ��": 
				type[0] = Types.VARCHAR;
				break;
			case "����":
				type[0] = Types.VARCHAR;
				break;
			case "��ɫ": 
				type[0] = Types.VARCHAR;
				break;
			case "����":
				type[0] = Types.VARCHAR;
				break;
			case "�Ա�": 
				type[0] = Types.VARCHAR;
				break;
			case "��ѧʱ��":
				type[0] = Types.DATE;
				break;
			case "רҵ":
				type[0] = Types.VARCHAR;
				break;
			case "ѧԺ":
				type[0] = Types.VARCHAR;
				break;
			default :
				break;
		}
		
		// ���ݿ����
		mTableModel.updateData(column, type, sql);
	}
	
}
