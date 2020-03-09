package windows;
import java.awt.BorderLayout;
import javax.swing.*;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
/**
 * @author lijunming
 * @date 2018/7/22 ����6:00
 */
public class getReports extends JPanel {
 
    private JPanel webBrowserPanel;
 
    private JWebBrowser webBrowser;
 
    public getReports(String url) {
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
        //ִ��Js����
//        webBrowser.executeJavascript("alert('hello swing')");
    }
 
 
    /**
     * ��swing����Ƕ�����
     * @param url  Ҫ���ʵ�url
     * @param title    ����ı���
     */
    public  static void  showReport(String url,String title){
        UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame(title);
                //���ô���رյ�ʱ�򲻹ر�Ӧ�ó���
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(new getReports(url), BorderLayout.CENTER);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLocationByPlatform(true);
                //�ô���ɼ�
                frame.setVisible(true);
                //���ô����С
                frame.setResizable(true);
                // ���ô���Ŀ�ȡ��߶�
                frame.setSize(1400, 700);
                // ���ô��������ʾ
                frame.setLocationRelativeTo(frame.getOwner());
            }
        });
        NativeInterface.runEventPump();
    }
    
    public void show(String reportPath, String title) {
        try {
        	showReport(reportPath,title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//    	showReport("F:\\tools\\xrayWin\\2.15.html","test");
//    }
}