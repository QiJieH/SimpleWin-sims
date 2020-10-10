package pers.qijieh.sims.unit;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Java_JFrame extends JFrame         //默认的事BorderLayout
{  
    /**
	 * 
	 */
	private static final long serialVersionUID = 3146277077340261197L;
	//创建一个容器  
    Container ct;  
    //创建背景面板。  
    BackgroundPanel bgp;         //自定义的背景类 

    //创建一个按钮，用来证明我们的确是创建了背景图片，而不是一张图片。  
    JButton jb;  
    public static void main(String[] args)  
    {  
        new Java_JFrame();  
    }  
    public Java_JFrame()  
    {  
        //不采用任何布局方式。  
        ct=this.getContentPane();
        this.setLayout(null);  

        //重新绘制背景图片  
        bgp=new BackgroundPanel((new ImageIcon(".\\src\\static\\loginBanner.jpg")).getImage()); //参数是一个Image对象,
        bgp.setBounds(0,0,400,300);  
        ct.add(bgp);  

        //创建按钮  
        jb=new JButton("测试按钮");  
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
        this.setOpaque(true);                    //设置控件不透明,若是false,那么就是透明
    }  
    //Draw the background again,继承自Jpanle,是Swing控件需要继承实现的方法,而不是AWT中的Paint()
    public void paintComponent(Graphics g)       //绘图类,详情可见博主的Java 下 java-Graphics 
    {  
        super.paintComponents(g);  
        g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);  //绘制指定图像中当前可用的图像。图像的左上角位于该图形上下文坐标空间的 (x, y)。图像中的透明像素不影响该处已存在的像素

    }  
}
