package pers.qijieh.sims.view;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import pers.qijieh.sims.controller.CloseCurTab;
import pers.qijieh.sims.controller.CreateAddTableTab;
import pers.qijieh.sims.viewmodel.PaginationEasy;
import pers.qijieh.sims.controller.DeleteSelect;
import pers.qijieh.sims.controller.RefreshTable;

@SuppressWarnings("serial")
public class TableRightPopupMenu extends JPopupMenu {
	
	public JMenuItem refresh;
	public JMenuItem openAddTab;
	public JMenuItem closeCurTab;
	public JMenuItem DeleteSelect;
	public JMenuItem exit;
	
	public TableRightPopupMenu(JTabbedPane jTabbedPane, JTable table, PaginationEasy pagination,String[] conditionStr, String sql, int deleteMode, int refreshMode) {
		
		refresh = new JMenuItem("刷新");
		refresh.addActionListener(new RefreshTable(table, pagination, conditionStr, sql, refreshMode));
		openAddTab = new JMenuItem("新建");
        openAddTab.addActionListener(new CreateAddTableTab(jTabbedPane));
        closeCurTab = new JMenuItem("关闭");
        closeCurTab.addActionListener(new CloseCurTab(jTabbedPane));
        DeleteSelect = new JMenuItem("删除");
        DeleteSelect.addActionListener(new DeleteSelect(table, pagination, conditionStr,sql, deleteMode));
        exit = new JMenuItem("退出");
        
        this.add(refresh);
        this.add(DeleteSelect);
        this.addSeparator();
        this.add(openAddTab);
        this.add(closeCurTab);
        this.addSeparator();
        this.add(exit);
	}
	
}
