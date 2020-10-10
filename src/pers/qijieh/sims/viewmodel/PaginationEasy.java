package pers.qijieh.sims.viewmodel;

import java.sql.SQLException;
import java.sql.Types;

import pers.qijieh.sims.datamodel.DBManager;
import pers.qijieh.sims.datamodel.DataTable;
import pers.qijieh.sims.datamodel.SQLFormat;

public class PaginationEasy {
	private int currentPage = 1;
	private int offset;
	private int totalItem;
	private int totalPage;
	private String showLocation;
	private boolean hasNext;
	@SuppressWarnings("unused")
	private boolean hasPre;
	private int showItemNum = 10;
	
	
	public PaginationEasy() {
		
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.offset = (currentPage-1)*showItemNum;
	}
	
	public void setShowItemNum(int num) {
		this.showItemNum = num;
	}
	
	public String showLocation() {
		showLocation = currentPage + "/" + totalPage;
		return showLocation;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public Boolean hasNext() {
		if(currentPage+1 > totalPage) {
			hasNext = false;
			return hasNext;
		}else {
			hasNext = true;
			return hasNext;
		}
	}
	
	public Boolean hasPre() {
		if(currentPage-1 > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getTotalItem() {
		return totalItem;
	}
	
	
	/**
	 * ˽�з��� ͳ���ֶ�
	 * @param column ����ֵ
	 * @param type ������������
	 * @author QiJieH
	 */
	private void countDataItem(String[][] column,int[] type,String sql)
	{
		DBManager dm = new DBManager();
		DataTable dt = new DataTable();
		try {
			dt = dm.getResultData(column, type, sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.totalItem = Integer.parseInt(dt.getRow()[0][0]);
		this.totalPage = totalItem % showItemNum == 0 ? totalItem/showItemNum : totalItem/showItemNum +1;
	}
	
	
	/**
	 * ��ҳ��ѯȫ������
	 * @return DataTable
	 * @author QiJieH
	 */
	public DataTable NormalQuery() throws SQLException 
	{
		// ͳ��ȫ������
		countDataItem(null, null, SQLFormat.AllCountItem());
		// ��ҳ��ѯȫ��
		DBManager dm = new DBManager();
		DataTable dt = new DataTable();
		String[][] column = null;
		int[] type = null;
		dt = dm.getResultData(column, type, SQLFormat.QueryAllByPage(showItemNum, offset));
		return dt;
	}
	
	
	/**
	 * ��ҳ������ѯ
	 * @param field �����ֶ�
	 * @param value ����ֵ
	 * @return DataTable
	 * @author QiJieH
	 */
	public DataTable conditionQuery(String field, String value) throws SQLException 
	{
		DBManager dm = new DBManager();
		DataTable dt = new DataTable();
		String[] keyAry = new String[] {field};
		String[][] column = new String[][] {{value}};
		int[] type = new int[1];
		// �����ֶ��ж���������
		switch (field) {
			case "sid": 
				type[0] = Types.VARCHAR;
				break;
			case "spwd":
				type[0] = Types.VARCHAR;
				break;
			case "srole": 
				type[0] = Types.VARCHAR;
				break;
			case "sname":
				type[0] = Types.VARCHAR;
				break;
			case "ssex": 
				type[0] = Types.VARCHAR;
				break;
			case "sentry":
				type[0] = Types.DATE;
				break;
			case "smajor":
				type[0] = Types.VARCHAR;
				break;
			case "scollage":
				type[0] = Types.VARCHAR;
				break;
			default :
				break;
		}
		// ͳ�Ʒ�������������
		countDataItem(column,type, SQLFormat.CondCountItem(keyAry));
		// ��ҳ���ݻ�ȡ
		dt = dm.getResultData(column, type, SQLFormat.CondQueryByPage(keyAry, showItemNum, offset));
		return dt;
	}
}
