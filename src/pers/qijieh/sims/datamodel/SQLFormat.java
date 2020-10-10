package pers.qijieh.sims.datamodel;

import java.sql.Types;
import java.util.Arrays;


/**
 * SQL����װ ����Ԥ����PreparedStatementռλ��
 * @author QiJieH
 */
public class SQLFormat {
	// Ԥ������������ֶ�
	private static String table = "students";
	private static String primaryKey = "sid";
	
	
	// Ԥ����������
	public static int[] defType = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.VARCHAR,Types.VARCHAR};
	public static String[] colName = new String[] {"sid","spwd","srole","sname","ssex","sentry","smajor","scollage"};
	
	/** get&&set */
	public static String getPrimaryKey() {
		return primaryKey;
	}
	
	
	
	
	/** ͨ����� */
	// ��ʾ�ֶ�
	private static String fieldSql(String[] field) {
		String buffer1 = Arrays.toString(field);
		String buffer2 = buffer1.substring(1,buffer1.length()-1);
		String sql = buffer2 + " from "+table;
		return sql;
	}
	// �� ��ѯ����
	private static String AndcondSql(String[] keyAry) {
		String sql = " where ";
		for(String key : keyAry) {
			sql += key +"= ? and ";
		}
		sql = sql.substring(0,sql.length()-4);
		return sql;
	}
	// �� ��ѯ����
	private static String OrcondSql(String[] keyAry) {
		String sql = " where ";
		for(String key : keyAry) {
			sql += key +"= ? or ";
		}
		sql = sql.substring(0,sql.length()-4);
		return sql;
	}
	// �Ƿ��ҳ
	private static String pageSql(int limit, int offset) {
		String sql = " limit "+limit+" offset "+offset;
		return sql;
	}

	
	
	/** д����������� */

	
	/**
	 * SQL��乹�� ɾ��
	 * @param keyAry
	 * @author QiJieH
	 */
	public static String deleteSql(String[] keyAry) {
		String sql = "delete from "+table+ OrcondSql(keyAry);
		return sql;
	}
	
	/**
	 * SQL��乹�� ����
	 * @param keyAry
	 * @author QiJieH
	 */
	public static String insertSql(String[][] values,int[] type) {
		String sql = "insert into "+table+ " values ";
		for(int i=0; i<values.length; i++) {
			sql += "(";
			for(int j=0; j<values[i].length; j++) {
				sql += "?,";
			}
			sql = sql.substring(0,sql.length()-1) + "),";
		}
		sql = sql.substring(0,sql.length()-1);
		return sql;
	}
	
	/**
	 * SQL��乹�� ����
	 * @param keyAry
	 * @author QiJieH
	 */
	public static String updateSql(String key, String modifyField, String newValue) {
		String sql = "update "+table+" set ";
		String switchModifyField = modifyField;
		switch (modifyField) {
			case "ѧ��": 
				switchModifyField = "sid";
				break;
			case "����":
				switchModifyField = "spwd";
				break;
			case "��ɫ": 
				switchModifyField = "srole";
				break;
			case "����":
				switchModifyField = "sname";
				break;
			case "�Ա�": 
				switchModifyField = "ssex";
				break;
			case "��ѧʱ��":
				switchModifyField = "sentry";
				break;
			case "רҵ":
				switchModifyField = "smajor";
				break;
			case "ѧԺ":
				switchModifyField = "scollage";
				break;
			default :
				break;
		}
		sql += switchModifyField + "= ? "+AndcondSql(new String[] {key});
		return sql;
	}
	
	
	
	/** ����������������� */
	
	/**
	 * SQL��乹�� ����������ѯ
	 * @author QiJieH
	 */
	public static String baseCondQuery(String[] keyAry) {
		String sql = "select " + fieldSql(new String[] {"*"}) + AndcondSql(keyAry);
		return sql;
	}
	
	/**
	 * SQL��乹�� ��ҳ��ѯȫ������
	 * @param limit ���Ʋ�ѯ������Ŀ
	 * @param offset ������ѯ��Ŀ
	 * @author QiJieH
	 */
	public static String QueryAllByPage(int limit, int offset) {
		String sql = "select " + fieldSql(new String[] {"*"}) + pageSql(limit, offset);
		return sql;
	}
	
	/**
	 * SQL��乹�� ������ҳ��ѯ
	 * @param cond ָ����ѯ�ֶκ�ֵ
	 * @param limit ���Ʋ�ѯ������Ŀ
	 * @param offset ������ѯ��Ŀ
	 * @author QiJieH
	 */
	public static String CondQueryByPage(String[] keyAry, int limit, int offset) {
		String sql = baseCondQuery(keyAry) + pageSql(limit, offset);
		return sql;
	}
	
	/**
	 * SQL��乹�� ������ѯ����ͳ��
	 * @param cond ָ����ѯ�ֶκ�ֵ
	 * @param limit ���Ʋ�ѯ������Ŀ
	 * @param offset ������ѯ��Ŀ
	 * @author QiJieH
	 */
	public static String CondCountItem(String[] keyAry) {
		String sql ="select "+ fieldSql(new String[] {"count(*)"}) + AndcondSql(keyAry);
		return sql;
	}
	
	/**
	 * SQL��乹�� ȫ����ѯ����ͳ��
	 * @author QiJieH
	 */
	public static String AllCountItem() {
		String sql ="select "+ fieldSql(new String[] {"count(*)"});
		return sql;
	}
	
	
	
	/**
	 * SQL��乹�� ȫ����ѯ����ͳ��
	 * @author QiJieH
	 */
	public static String OutData2File(String path) {
		String sql ="select * from "+table+" into outfile "+"'"+path+"'";
		return sql;
	}
}

