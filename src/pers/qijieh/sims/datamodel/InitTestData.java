package pers.qijieh.sims.datamodel;

import pers.qijieh.sims.unit.BCrypt;

public class InitTestData {
	
	public static void build() {
		System.out.println("��ʼ����������� ��ʼ����Ա�ʺţ�admin ���룺admin");
		DBManager dm = new DBManager();

	    String[][] column = new String[30][8];
	    column[0][0] = "admin";
	    column[0][1] = BCrypt.hashpw("admin", BCrypt.gensalt());
    	column[0][2] = "����Ա";
    	column[0][3] = "����Ա";
    	column[0][4] = "��";
    	column[0][5] = "2018-08-01";
    	column[0][6] = "�������";
    	column[0][7] = "�����ѧԺ";
	    
	    for(int i=1; i<column.length; i++) {
	    	column[i][0] = "2018404075" + i;
	    	column[i][1] = BCrypt.hashpw("123456", BCrypt.gensalt());
	    	column[i][2] = "ѧ��";
	    	column[i][3] = "A"+i;
	    	column[i][4] = "��";
	    	column[i][5] = "2018-08-01";
	    	column[i][6] = "���繤��";
	    	column[i][7] = "�����ѧԺ";
	    }
	    
	    try {
	        boolean resStu = dm.updataOrAdd(column, SQLFormat.defType, SQLFormat.insertSql(column, SQLFormat.defType));
	        if(resStu) {
	            System.out.println("����ɹ�");
	        }else {
	            System.out.println("����ʧ��");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println("���������ѹ������");
	}
	
}
