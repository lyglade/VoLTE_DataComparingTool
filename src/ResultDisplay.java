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
        //ִ��Js����

//        webBrowser.executeJavascript("alert('hello swing')");
    }

    /**
     * ��swing����Ƕ�����
     * @param url  Ҫ���ʵ�url
     * @param title    ����ı���
     */

    public  static void  openForm(String url,String title,MainBoard Mainb){
        UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame(title);
                //���ô���رյ�ʱ�򲻹ر�Ӧ�ó���
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(new ResultDisplay(url), BorderLayout.CENTER);
                frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
                frame.setLocationByPlatform(true);
                //�ô���ɼ�
                frame.setVisible(true);
                //���ô����С
                frame.setResizable(true);
                // ���ô���Ŀ�ȡ��߶�
                frame.setSize(1400, 700);
                // ���ô��������ʾ
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
//        				System.out.println("����windowClosed�¼�");
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
