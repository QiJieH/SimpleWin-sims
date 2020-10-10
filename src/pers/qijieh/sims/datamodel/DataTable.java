package pers.qijieh.sims.datamodel;

import java.util.ArrayList;

/**
 * 数据集封装
 * @author QiJieH
 */

public class DataTable {
	public String[] column = null;	// 列名
	public String[][] row = null;	// 行值
	public int rowCount = 0; // 行数
	public int colCount = 0; // 列数
	private String[] typeName; // 数据类型
	
	
	public DataTable() {
		super();
	}
	
	public DataTable(String[] column, String[][] row, int rowCount, int colCount) 
	{
		super();
		this.column = column;
		this.row = row;
		this.rowCount = rowCount;
		this.colCount = colCount;
	}
	
	public void setDataTable(ArrayList<String[]> list)
	{
		if(list == null) {
			return;
		}
		rowCount = list.size();
		colCount = list.get(0).length;
		column = new String[colCount];
		row = new String[rowCount][colCount];
		
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				row[i][j] = list.get(i)[j];
			}
		}
	}
	
	public String[] getColumn() {
		return column;
	}
	
	public void setColumn(String[] column) {
		this.column = column;
	}
	
	public String[][] getRow() {
		return row;
	}
	
	public void setRow(String[][] row) {
		this.row = row;
	}
	
    public int getRowCount() {
        return rowCount;
    }
 
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
 
    public int getColCoun() {
        return colCount;
    }
 
    public void setColCoun(int colCoun) {
        this.colCount= colCoun;
    }
    
    public String[] getTypeName() {
    	return typeName;
    }
    
    public void setTypeName(String[] typeName) {
		this.typeName = typeName;
	}
}
