package pers.qijieh.sims.datamodel;

import pers.qijieh.sims.unit.BCrypt;

public class InitTestData {
	
	public static void build() {
		System.out.println("开始构造测试数据 初始管理员帐号：admin 密码：admin");
		DBManager dm = new DBManager();

	    String[][] column = new String[30][8];
	    column[0][0] = "admin";
	    column[0][1] = BCrypt.hashpw("admin", BCrypt.gensalt());
    	column[0][2] = "管理员";
    	column[0][3] = "管理员";
    	column[0][4] = "男";
    	column[0][5] = "2018-08-01";
    	column[0][6] = "软件工程";
    	column[0][7] = "计算机学院";
	    
	    for(int i=1; i<column.length; i++) {
	    	column[i][0] = "2018404075" + i;
	    	column[i][1] = BCrypt.hashpw("123456", BCrypt.gensalt());
	    	column[i][2] = "学生";
	    	column[i][3] = "A"+i;
	    	column[i][4] = "男";
	    	column[i][5] = "2018-08-01";
	    	column[i][6] = "网络工程";
	    	column[i][7] = "计算机学院";
	    }
	    
	    try {
	        boolean resStu = dm.updataOrAdd(column, SQLFormat.defType, SQLFormat.insertSql(column, SQLFormat.defType));
	        if(resStu) {
	            System.out.println("插入成功");
	        }else {
	            System.out.println("插入失败");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println("所有数据已构造完成");
	}
	
}
