package pers.qijieh.sims.viewmodel;

import java.sql.SQLException;
import java.util.Arrays;

import pers.qijieh.sims.datamodel.DBManager;
import pers.qijieh.sims.datamodel.DataTable;
import pers.qijieh.sims.datamodel.SQLFormat;
import pers.qijieh.sims.unit.BCrypt;
import pers.qijieh.sims.view.MainView;



/**
 * 数据库操作 所有在前端的数据库操作都会在该类调用
 * @author QiJieH
 * @return 
 */
public class MyTableModel {
	// 发起数据库修改
    public boolean updateData(String[][] column, int[] type, String sql) {
    	DBManager dm = new DBManager();
	    boolean resStu = false;
	    try {
	    	long startTime = System.currentTimeMillis();
	        resStu = dm.updataOrAdd(column, type, sql);
	        long endTime = System.currentTimeMillis();
	        long useTime = endTime - startTime;
	        if(resStu) {
	        	MainView.console.append("修改数据成功，共计修改"+column.length+"条数据，累计耗时"+useTime+"ms\n");
	        }else {
	        	MainView.console.append("操作失败，未知原因\n");
			}
	    } catch (SQLException e) {
	    	MainView.console.append("操作失败："+e.getMessage()+"\n");
	    }
	    return resStu;
	}
    
    // 发起数据库删除
    public boolean deleteData(String[][] column, int[] type) {
    	DBManager dm = new DBManager();
	    boolean resStu = false;
	    try {
	    	long startTime = System.currentTimeMillis();
	    	String[] keyAry = new String[type.length];
	    	Arrays.fill(keyAry, "sid");
	        resStu = dm.updataOrAdd(column, type, SQLFormat.deleteSql(keyAry));
	        long endTime = System.currentTimeMillis();
	        long useTime = endTime - startTime;
	        if(resStu) {
	        	MainView.console.append("删除数据成功，共计删除"+type.length+"条数据，累计耗时"+useTime+"ms\n");
	        }else {
	        	MainView.console.append("操作失败，未知原因\n");
			}
	    } catch (SQLException e) {
	    	MainView.console.append("操作失败："+e.getMessage()+"\n");
	    }
	    return resStu;
	}
	
	// 发起数据库插入 将错误抛出处理
	public boolean insertData(String[][] column) throws Exception {
		long startTime = System.currentTimeMillis();
		DBManager dm = new DBManager();
		boolean resStu = false;
		
		// 密码字段加密
		for(int i=0; i<column.length; i++) {
			column[i][1] = BCrypt.hashpw(column[i][1], BCrypt.gensalt());
		}
		
		resStu = dm.updataOrAdd(column, SQLFormat.defType, SQLFormat.insertSql(column, SQLFormat.defType));
		long endTime = System.currentTimeMillis();
		long useTime = endTime - startTime;
		if(resStu) {
        	MainView.console.append("添加数据成功，共计添加"+column.length+"条数据，累计耗时"+useTime+"ms\n");
        }else {
        	MainView.console.append("操作失败，未知原因\n");
		}

		return resStu;
	}
	
    // 发起数据库分页查询
	public DataTable queryData(PaginationEasy pagination, String field, String value, int index) {
		pagination.setCurrentPage(index);
		DataTable dt = new DataTable();
		if(field != null && value != null) {
			try {
				long startTime = System.currentTimeMillis();
				dt = pagination.conditionQuery(field, value);
				long endTime = System.currentTimeMillis();
				long useTime = endTime - startTime;
		        MainView.console.append("查询数据成功，共计"+dt.getRowCount()+"条，累计耗时"+useTime+"ms\n");
			} catch (SQLException e) {
				MainView.console.append("查询失败："+e.getMessage()+"\n");
			}
		}else {
			try {
				long startTime = System.currentTimeMillis();
				dt = pagination.NormalQuery();
				long endTime = System.currentTimeMillis();
				long useTime = endTime - startTime;
		        MainView.console.append("加载数据成功，共计"+pagination.getTotalItem()+"条，累计耗时"+useTime+"ms\n");
			} catch (SQLException e) {
				MainView.console.append("加载失败："+e.getMessage()+"\n");
			}
		}
		return dt;
	}
	
	
}
