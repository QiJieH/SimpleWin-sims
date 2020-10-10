package pers.qijieh.sims.app;

import pers.qijieh.sims.datamodel.InitTestData;
import pers.qijieh.sims.view.LoginView;
import pers.qijieh.sims.view.MainView;

public class Index {
	public static void main(String args[]) {
		/** 构造测试数据 */
		//InitTestData.build();
		/** 免登录进入开发模式 */
		MainView mv = new MainView(null);
		
		/** 登录程序 */
		//LoginView lv = new LoginView();
	}
}



// TODO 导出数据
// TODO 批量导入