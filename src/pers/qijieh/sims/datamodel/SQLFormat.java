package pers.qijieh.sims.datamodel;

import java.sql.Types;
import java.util.Arrays;


/**
 * SQL语句封装 构造预编译PreparedStatement占位符
 * @author QiJieH
 */
public class SQLFormat {
	// 预设表名，主键字段
	private static String table = "students";
	private static String primaryKey = "sid";
	
	
	// 预设数据类型
	public static int[] defType = new int[] {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.VARCHAR,Types.VARCHAR};
	public static String[] colName = new String[] {"sid","spwd","srole","sname","ssex","sentry","smajor","scollage"};
	
	/** get&&set */
	public static String getPrimaryKey() {
		return primaryKey;
	}
	
	
	
	
	/** 通用语句 */
	// 显示字段
	private static String fieldSql(String[] field) {
		String buffer1 = Arrays.toString(field);
		String buffer2 = buffer1.substring(1,buffer1.length()-1);
		String sql = buffer2 + " from "+table;
		return sql;
	}
	// 并 查询条件
	private static String AndcondSql(String[] keyAry) {
		String sql = " where ";
		for(String key : keyAry) {
			sql += key +"= ? and ";
		}
		sql = sql.substring(0,sql.length()-4);
		return sql;
	}
	// 或 查询条件
	private static String OrcondSql(String[] keyAry) {
		String sql = " where ";
		for(String key : keyAry) {
			sql += key +"= ? or ";
		}
		sql = sql.substring(0,sql.length()-4);
		return sql;
	}
	// 是否分页
	private static String pageSql(int limit, int offset) {
		String sql = " limit "+limit+" offset "+offset;
		return sql;
	}

	
	
	/** 写操作构造语句 */

	
	/**
	 * SQL语句构造 删除
	 * @param keyAry
	 * @author QiJieH
	 */
	public static String deleteSql(String[] keyAry) {
		String sql = "delete from "+table+ OrcondSql(keyAry);
		return sql;
	}
	
	/**
	 * SQL语句构造 插入
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
	 * SQL语句构造 更改
	 * @param keyAry
	 * @author QiJieH
	 */
	public static String updateSql(String key, String modifyField, String newValue) {
		String sql = "update "+table+" set ";
		String switchModifyField = modifyField;
		switch (modifyField) {
			case "学号": 
				switchModifyField = "sid";
				break;
			case "密码":
				switchModifyField = "spwd";
				break;
			case "角色": 
				switchModifyField = "srole";
				break;
			case "姓名":
				switchModifyField = "sname";
				break;
			case "性别": 
				switchModifyField = "ssex";
				break;
			case "入学时间":
				switchModifyField = "sentry";
				break;
			case "专业":
				switchModifyField = "smajor";
				break;
			case "学院":
				switchModifyField = "scollage";
				break;
			default :
				break;
		}
		sql += switchModifyField + "= ? "+AndcondSql(new String[] {key});
		return sql;
	}
	
	
	
	/** 读操作基础构造语句 */
	
	/**
	 * SQL语句构造 基础条件查询
	 * @author QiJieH
	 */
	public static String baseCondQuery(String[] keyAry) {
		String sql = "select " + fieldSql(new String[] {"*"}) + AndcondSql(keyAry);
		return sql;
	}
	
	/**
	 * SQL语句构造 分页查询全部数据
	 * @param limit 限制查询返回数目
	 * @param offset 跳过查询数目
	 * @author QiJieH
	 */
	public static String QueryAllByPage(int limit, int offset) {
		String sql = "select " + fieldSql(new String[] {"*"}) + pageSql(limit, offset);
		return sql;
	}
	
	/**
	 * SQL语句构造 条件分页查询
	 * @param cond 指定查询字段和值
	 * @param limit 限制查询返回数目
	 * @param offset 跳过查询数目
	 * @author QiJieH
	 */
	public static String CondQueryByPage(String[] keyAry, int limit, int offset) {
		String sql = baseCondQuery(keyAry) + pageSql(limit, offset);
		return sql;
	}
	
	/**
	 * SQL语句构造 条件查询数量统计
	 * @param cond 指定查询字段和值
	 * @param limit 限制查询返回数目
	 * @param offset 跳过查询数目
	 * @author QiJieH
	 */
	public static String CondCountItem(String[] keyAry) {
		String sql ="select "+ fieldSql(new String[] {"count(*)"}) + AndcondSql(keyAry);
		return sql;
	}
	
	/**
	 * SQL语句构造 全部查询数量统计
	 * @author QiJieH
	 */
	public static String AllCountItem() {
		String sql ="select "+ fieldSql(new String[] {"count(*)"});
		return sql;
	}
	
	
	
	/**
	 * SQL语句构造 全部查询数量统计
	 * @author QiJieH
	 */
	public static String OutData2File(String path) {
		String sql ="select * from "+table+" into outfile "+"'"+path+"'";
		return sql;
	}
}

