package pers.qijieh.sims.view;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pers.qijieh.sims.resources.IconGroup;

public class AboutUs extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1560269503101469246L;
	JPanel contentPanel;
		JLabel jLabel1;
		JLabel jLabel2;
		JLabel jLabel3;
		JLabel jLabel4;
		
	public AboutUs() {
		// 窗口居中显示
		this.setSize(360,250);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int x = (screenWidth - this.getSize().width) / 2;
	    int y = (screenHeight - this.getSize().height) / 2;
	    
	    

	    contentPanel = new JPanel(new FlowLayout()); 
	    	jLabel1 = new JLabel("<html><br><br><br><br><br><br><br><br><br><br><br></html>");
	    	jLabel2 = new JLabel(IconGroup.githubAbIcon);
	    	jLabel3 = new JLabel("<html>软件名称：学生管理系统<br>作者：QiJieH<br>项目源码：<a href='https://github.com/QiJieH'>https://github.com/QiJieH</a></html>");
	    contentPanel.add(jLabel1);
	    contentPanel.add(jLabel2);
	    contentPanel.add(jLabel3);
	    
	    jLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		jLabel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI("https://github.com/QiJieH"));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
	    
	    this.setContentPane(contentPanel);
	    
	    
	    
	    
	    
	    // 窗口属性
	    this.setResizable(false);
	    this.setTitle("关于");
	    this.setLocation(x, y);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
