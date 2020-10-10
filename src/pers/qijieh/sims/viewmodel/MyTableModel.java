package pers.qijieh.sims.viewmodel;

import java.sql.SQLException;
import java.util.Arrays;

import pers.qijieh.sims.datamodel.DBManager;
import pers.qijieh.sims.datamodel.DataTable;
import pers.qijieh.sims.datamodel.SQLFormat;
import pers.qijieh.sims.unit.BCrypt;
import pers.qijieh.sims.view.MainView;



/**
 * ���ݿ���� ������ǰ�˵����ݿ���������ڸ������
 * @author QiJieH
 * @return 
 */
public class MyTableModel {
	// �������ݿ��޸�
    public boolean updateData(String[][] column, int[] type, String sql) {
    	DBManager dm = new DBManager();
	    boolean resStu = false;
	    try {
	    	long startTime = System.currentTimeMillis();
	        resStu = dm.updataOrAdd(column, type, sql);
	        long endTime = System.currentTimeMillis();
	        long useTime = endTime - startTime;
	        if(resStu) {
	        	MainView.console.append("�޸����ݳɹ��������޸�"+column.length+"�����ݣ��ۼƺ�ʱ"+useTime+"ms\n");
	        }else {
	        	MainView.console.append("����ʧ�ܣ�δ֪ԭ��\n");
			}
	    } catch (SQLException e) {
	    	MainView.console.append("����ʧ�ܣ�"+e.getMessage()+"\n");
	    }
	    return resStu;
	}
    
    // �������ݿ�ɾ��
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
	        	MainView.console.append("ɾ�����ݳɹ�������ɾ��"+type.length+"�����ݣ��ۼƺ�ʱ"+useTime+"ms\n");
	        }else {
	        	MainView.console.append("����ʧ�ܣ�δ֪ԭ��\n");
			}
	    } catch (SQLException e) {
	    	MainView.console.append("����ʧ�ܣ�"+e.getMessage()+"\n");
	    }
	    return resStu;
	}
	
	// �������ݿ���� �������׳�����
	public boolean insertData(String[][] column) throws Exception {
		long startTime = System.currentTimeMillis();
		DBManager dm = new DBManager();
		boolean resStu = false;
		
		// �����ֶμ���
		for(int i=0; i<column.length; i++) {
			column[i][1] = BCrypt.hashpw(column[i][1], BCrypt.gensalt());
		}
		
		resStu = dm.updataOrAdd(column, SQLFormat.defType, SQLFormat.insertSql(column, SQLFormat.defType));
		long endTime = System.currentTimeMillis();
		long useTime = endTime - startTime;
		if(resStu) {
        	MainView.console.append("������ݳɹ����������"+column.length+"�����ݣ��ۼƺ�ʱ"+useTime+"ms\n");
        }else {
        	MainView.console.append("����ʧ�ܣ�δ֪ԭ��\n");
		}

		return resStu;
	}
	
    // �������ݿ��ҳ��ѯ
	public DataTable queryData(PaginationEasy pagination, String field, String value, int index) {
		pagination.setCurrentPage(index);
		DataTable dt = new DataTable();
		if(field != null && value != null) {
			try {
				long startTime = System.currentTimeMillis();
				dt = pagination.conditionQuery(field, value);
				long endTime = System.currentTimeMillis();
				long useTime = endTime - startTime;
		        MainView.console.append("��ѯ���ݳɹ�������"+dt.getRowCount()+"�����ۼƺ�ʱ"+useTime+"ms\n");
			} catch (SQLException e) {
				MainView.console.append("��ѯʧ�ܣ�"+e.getMessage()+"\n");
			}
		}else {
			try {
				long startTime = System.currentTimeMillis();
				dt = pagination.NormalQuery();
				long endTime = System.currentTimeMillis();
				long useTime = endTime - startTime;
		        MainView.console.append("�������ݳɹ�������"+pagination.getTotalItem()+"�����ۼƺ�ʱ"+useTime+"ms\n");
			} catch (SQLException e) {
				MainView.console.append("����ʧ�ܣ�"+e.getMessage()+"\n");
			}
		}
		return dt;
	}
	
	
}
