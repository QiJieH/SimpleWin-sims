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
	
	
	
	// ����log�ļ�����������ھʹ���
	public LogFile() {
		file = new File(System.getProperty("user.home"),"Log.txt");
		if(!file.exists()){
	        try{
	            file.createNewFile();
	        }catch(IOException e){
	            System.out.println("�����ļ�ʧ�ܣ������ڸ�Ŀ¼�³�����Ȩ��");
	        }
	    }
	}
	
	// д����־
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
	
	// ����־
	public void open() {
		try {
            java.awt.Desktop.getDesktop().edit(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
	}
	
}
