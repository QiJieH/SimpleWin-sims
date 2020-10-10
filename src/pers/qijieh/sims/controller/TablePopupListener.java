package pers.qijieh.sims.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

public class TablePopupListener extends MouseAdapter {
	JPopupMenu popupMenu;
	
    public TablePopupListener(JPopupMenu popupMenu) {
    	this.popupMenu = popupMenu;
	}
    
    public void mousePressed(MouseEvent e) {
        showPopupMenu(e);
    }
    public void mouseReleased(MouseEvent e) {
        showPopupMenu(e);
    }
    private void showPopupMenu(MouseEvent e) {
        if(e.isPopupTrigger()) {
            popupMenu.show(e.getComponent(),e.getX(),e.getY());
        }
    }
}
