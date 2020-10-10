package pers.qijieh.sims.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFile {
	File file;
	
	
	
	// 加载log文件，如果不存在就创建
	public LogFile() {
		file = new File(System.getProperty("user.home"),"Log.txt");
		if(!file.exists()){
	        try{
	            file.createNewFile();
	        }catch(IOException e){
	            System.out.println("创建文件失败，可能在该目录下程序无权限");
	        }
	    }
	}
	
	// 写入日志
	public void write(String logStr) {
		try {
			Date dNow = new Date( );
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			String writeDate = ft.format(dNow);
			@SuppressWarnings("resource")
			PrintStream ps = new PrintStream(new FileOutputStream(file,true));
			ps.append(writeDate+"\n"+logStr+"\n\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 打开日志
	public void open() {
		try {
            java.awt.Desktop.getDesktop().edit(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
	}
	
}
