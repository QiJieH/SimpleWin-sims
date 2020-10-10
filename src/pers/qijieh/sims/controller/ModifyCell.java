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
		// 如果修改主键
		if(modifyField == SQLFormat.getPrimaryKey() || modifyField == "学号") {
			primaryKey = oldValue;
		}
		String sql = SQLFormat.updateSql("sid", modifyField, newValue);
		// 如果修改密码，将字段加密
		if(modifyField == "密码" || modifyField == "Spwd") {
			newValue = BCrypt.hashpw(newValue, BCrypt.gensalt());
		}
		String[][] column = new String[][] {{newValue, primaryKey}};
		int[] type = new int[] {Types.VARCHAR, Types.VARCHAR};
		// 确认修改字段的数据类型
		switch (modifyField) {
			case "学号": 
				type[0] = Types.VARCHAR;
				break;
			case "密码":
				type[0] = Types.VARCHAR;
				break;
			case "角色": 
				type[0] = Types.VARCHAR;
				break;
			case "姓名":
				type[0] = Types.VARCHAR;
				break;
			case "性别": 
				type[0] = Types.VARCHAR;
				break;
			case "入学时间":
				type[0] = Types.DATE;
				break;
			case "专业":
				type[0] = Types.VARCHAR;
				break;
			case "学院":
				type[0] = Types.VARCHAR;
				break;
			default :
				break;
		}
		
		// 数据库操作
		mTableModel.updateData(column, type, sql);
	}
	
}
