package pers.qijieh.sims.datamodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MYSQL数据库连接层
 * @author QiJieH
 * 
 */
public class DBConnection {
	private final static String db = "mysql";
	private final static String host = "localhost";
	private final static String port = "3306";
	private final static String dbname = "sims";
	private final static String options = "useSSL=true&serverTimezone=UTC";
	private final static String user = "root";
	private final static String pwd = "root";
	
	public static Connection getDBConnection() 
	{
		// 最新版本驱动包已更改，无需手动加载
        
        String url = "jdbc:"+db+"://"+host+":"+port+"/"+dbname+"?"+options;
        // 获取数据库连接
        try {
			Connection con = DriverManager.getConnection(url, user, pwd);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
