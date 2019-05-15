import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserListener;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;

public class ResultDisplay extends JPanel {
    private JPanel webBrowserPanel;
    private JWebBrowser webBrowser;
    public ResultDisplay(String url) {
        super(new BorderLayout());
        webBrowserPanel = new JPanel(new BorderLayout());
        webBrowser = new JWebBrowser();
        webBrowser.navigate(url);
        webBrowser.setButtonBarVisible(false);
        webBrowser.setMenuBarVisible(false);
        webBrowser.setBarsVisible(false);
        webBrowser.setStatusBarVisible(false);
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        add(webBrowserPanel, BorderLayout.CENTER);
        webBrowser.setVisible(true);
        //执行Js代码

//        webBrowser.executeJavascript("alert('hello swing')");
    }

    /**
     * 在swing里内嵌浏览器
     * @param url  要访问的url
     * @param title    窗体的标题
     */

    public  static void  openForm(String url,String title,MainBoard Mainb){
        UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame(title);
                //设置窗体关闭的时候不关闭应用程序
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(new ResultDisplay(url), BorderLayout.CENTER);
                frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
                frame.setLocationByPlatform(true);
                //让窗体可见
                frame.setVisible(true);
                //重置窗体大小
                frame.setResizable(true);
                // 设置窗体的宽度、高度
                frame.setSize(1400, 700);
                // 设置窗体居中显示
                frame.setLocationRelativeTo(frame.getOwner());
                frame.addWindowListener(new WindowListener() {
					
					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						Mainb.SetFrameEnable();
					}
					
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
                
//                frame.addWindowListener(new WindowAdapter() {
//                	public void windowClosed(WindowEvent e)
//        			{
//        				System.out.println("触发windowClosed事件");
//        			}
//                }
//                		
//);
            }
        });
        NativeInterface.runEventPump();
    }

 

    public static void main(String[] args) {

//        openForm(System.getProperty("user.dir")+ "\\WEB\\chartjs.html","ResultDisplay");

    }

}
