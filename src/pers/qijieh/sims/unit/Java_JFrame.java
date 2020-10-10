package pers.qijieh.sims.unit;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Java_JFrame extends JFrame         //Ĭ�ϵ���BorderLayout
{  
    /**
	 * 
	 */
	private static final long serialVersionUID = 3146277077340261197L;
	//����һ������  
    Container ct;  
    //����������塣  
    BackgroundPanel bgp;         //�Զ���ı����� 

    //����һ����ť������֤�����ǵ�ȷ�Ǵ����˱���ͼƬ��������һ��ͼƬ��  
    JButton jb;  
    public static void main(String[] args)  
    {  
        new Java_JFrame();  
    }  
    public Java_JFrame()  
    {  
        //�������κβ��ַ�ʽ��  
        ct=this.getContentPane();
        this.setLayout(null);  

        //���»��Ʊ���ͼƬ  
        bgp=new BackgroundPanel((new ImageIcon(".\\src\\static\\loginBanner.jpg")).getImage()); //������һ��Image����,
        bgp.setBounds(0,0,400,300);  
        ct.add(bgp);  

        //������ť  
        jb=new JButton("���԰�ť");  
        jb.setBounds(60,30,160,30);  
        ct.add(jb);  

        this.setSize(400,300);  
        this.setLocation(400,300);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
    }  
}  
class BackgroundPanel extends JPanel  
{  
    /**
	 * 
	 */
	private static final long serialVersionUID = -7775483349636155401L;
	Image im;  
    public BackgroundPanel(Image im)  
    {  
        this.im=im;  
        this.setOpaque(true);                    //���ÿؼ���͸��,����false,��ô����͸��
    }  
    //Draw the background again,�̳���Jpanle,��Swing�ؼ���Ҫ�̳�ʵ�ֵķ���,������AWT�е�Paint()
    public void paintComponent(Graphics g)       //��ͼ��,����ɼ�������Java �� java-Graphics 
    {  
        super.paintComponents(g);  
        g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);  //����ָ��ͼ���е�ǰ���õ�ͼ��ͼ������Ͻ�λ�ڸ�ͼ������������ռ�� (x, y)��ͼ���е�͸�����ز�Ӱ��ô��Ѵ��ڵ�����

    }  
}
