package pers.qijieh.sims.datamodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import pers.qijieh.sims.log.LogFile;



/**
 * MYSQL���ݿ�ײ��װ
 * @author QiJieH
 *
 */
public class DBManager {
	private LogFile lf = new LogFile();
	private PreparedStatement pstmt;
	private	Connection con;
	private ResultSet rs;
	private String logstr = new String();
	
	
	/**
     * �������ݿ�
     */
	public DBManager() {
		con = DBConnection.getDBConnection();
	}
	
	
	/**
     * ��ȡ��ѯ�����
     * @param coulmn
     * @param type
     * @param sql
     * @throws SQLException
     */
	public DataTable getResultData(String[][] column, int[] type, String sql) throws SQLException
	{
		// ��־
		logstr = sql;
		DataTable dt = new DataTable();
		ArrayList<String[]> list = null;
		
		try {
			if(!setPstmtParam(column,type, sql)) return null;
		} catch (NumberFormatException | SQLException | ParseException e) {
			e.printStackTrace();
		}
		
		lf.write("ִ��SQL��䣺\n"+logstr);
		
		rs = pstmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData(); // ��ȡ�в�������
		int numberOfColumns = rsmd.getColumnCount(); // ��ȡ����
		if(rs.next())
		{	
			list = new ArrayList<String[]>();
			String[] rsAry = new String[numberOfColumns];
			for(int r=0; r<numberOfColumns; r++)
			{
				rsAry[r] = rs.getString(r+1);
			}
			list.add(rsAry);
			// ���ݼ�
			while (rs.next())
			{
				rsAry = new String[numberOfColumns];
				for(int r=0; r<numberOfColumns; r++)
				{
					rsAry[r] = rs.getString(r+1);
				}
				list.add(rsAry);
			}
		}
		
		closeDB();
		dt.setDataTable(list);
		
		// ��������������
		String[] columnName = new String[numberOfColumns];
		String[] typeName = new String[numberOfColumns];
		for(int i=0; i<numberOfColumns; i++)
		{
			typeName[i] = rsmd.getColumnTypeName(i+1);
			columnName[i] = rsmd.getColumnName(i+1);
		}
		dt.setColumn(columnName);
		dt.setTypeName(typeName);
		
		return dt;
	}
	
	
	
	/**
     * ִ���޸���Ӳ���
     * @param coulmn
     * @param type
     * @param sql
     * @return boolean
     * @throws SQLException
     */
	public boolean updataOrAdd(String[][] column, int[] type, String sql) throws SQLException
	{
		try {
			if(!setPstmtParam(column, type, sql)) return false;
		} catch (NumberFormatException | SQLException | ParseException e) {
			e.printStackTrace();
		}
		boolean flag = pstmt.executeUpdate() > 0 ? true : false;
		closeDB();
		return flag;
	}
	
	
	/**
     * Ԥ�����������
     * @param coulmn
     * @param type
     * @param sql
     * @throws SQLException 
     * @throws NumberFormatException 
	 * @throws ParseException 
     */
	private boolean setPstmtParam(String[][] column, int[] type, String sql) throws NumberFormatException, SQLException, ParseException
	{
		if(sql == null) return false;
		pstmt = con.prepareStatement(sql);
		
		if(column != null && type != null && column.length != 0 && type.length != 0)
		{
			int j = 1;
			for(String[] str : column)
			{
				for (int i=0; i<type.length; i++)
				{
					// �ռǼ�¼
					logstr = logstr.replaceFirst("\\?", "'"+str[i]+"'");
					
					switch (type[i]) {
					case Types.INTEGER:
						pstmt.setInt(j, Integer.parseInt(str[i]));
						break;
					case Types.BOOLEAN:
						pstmt.setBoolean(j, Boolean.parseBoolean(str[i]));
						break;
					case Types.CHAR:
						pstmt.setString(j, str[i]);
						break;
					case Types.VARCHAR:
						pstmt.setString(j, str[i]);
						break;
					case Types.DOUBLE:
						pstmt.setDouble(j, Double.parseDouble(str[i]));
						break;
					case Types.FLOAT:
						pstmt.setFloat(j, Float.parseFloat(str[i]));
						break;
					case Types.DATE:
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						pstmt.setDate(j, new java.sql.Date(dateFormat.parse(str[i]).getTime()));
						break;
					default:
						break;
					}
					j++;
				}
			}
		}
		return true;
	}
	
	
	

	
	
	/**
     * �ر����ݿ�
     * @throws SQLException
     */
	private void closeDB() throws SQLException
	{
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(con != null) con.close();
	}
}
