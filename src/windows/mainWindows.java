package windows;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.awt.event.ActionEvent;
import xray.*;

public class mainWindows extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTabbedPane mainWindows = new JTabbedPane();// 存放选项卡的组件
	private JTextField targetUrl;
	private JTextField webHookUrl;
	private JTextField iport;
	private JTextField listenIP;

	public mainWindows() {
		layoutComponents();
	}

	private void layoutComponents() {

		setLayout(new GridLayout(1, 1));
		// setLayout(null);
		add(mainWindows);

		// Webscan
		JPanel Webscan = new JPanel();
		mainWindows.addTab("Webscan", null, Webscan, null);

		targetUrl = new JTextField();
		targetUrl.setText("http://testphp.vulnweb.com/");
		targetUrl.setBounds(169, 102, 210, 21);

		JLabel target = new JLabel("TargetUrl:");
		target.setBounds(99, 105, 60, 15);

		JLabel Plugins = new JLabel("Plugins:");
		Plugins.setBounds(99, 130, 48, 15);

		JComboBox plugins = new JComboBox();
		plugins.setBounds(148, 127, 110, 21);
		plugins.setModel(new DefaultComboBoxModel(new String[] { "all", "xss", "ssrf", "struts", "jsonp", "sqldet",
				"dirscan", "upload", "baseline", "redirect", "fastjson", "thinkphp", "brute_force", "crlf_injection",
				"cmd_injection", "path_traversal", "xxe phantasm" }));

		JLabel out = new JLabel("Output:");
		out.setBounds(268, 130, 42, 15);

		JComboBox output = new JComboBox();
		output.setBounds(311, 127, 68, 21);
		output.setModel(new DefaultComboBoxModel(new String[] { "html", "json", "webhook" }));

		JButton scan = new JButton("SCAN");
		scan.setBounds(99, 155, 280, 23);
		scan.addActionListener(new ActionListener() {
			private Component jPanel;

			public void actionPerformed(ActionEvent arg0) {

				// scan.setText("SCANING");

				String url = targetUrl.getText();
				// System.out.println(targetUrl.getText());

				String plugin = (String) plugins.getSelectedItem();
				// System.out.println((String) plugins.getSelectedItem());

				String outputStr = (String) output.getSelectedItem();
				// System.out.println((String) output.getSelectedItem());

				//

				String timestamp = String.valueOf(System.currentTimeMillis());
				String outputType = "";
				String outputFile = "";

				if (outputStr == "html") {
					outputType = "--html-output";
					outputFile = timestamp + ".html";
				} else if (outputStr == "json") {
					outputType = "--json-output";
					outputFile = timestamp + ".json";
				} else {
					outputType = "--webhook-output";
					outputFile = webHookUrl.getText();
					if (outputFile == "") {
						JOptionPane.showMessageDialog(jPanel, "Webhook not set", "TheKingOfDuck:",
								JOptionPane.WARNING_MESSAGE);
					}
				}

				// TODO: 判断目标URL有效性
				URL urlTest;
				try {
					urlTest = new URL(url);
					java.io.InputStream in = urlTest.openStream();
					// System.out.println("true");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(jPanel, "Can not connect to target\n" + url, "TheKingOfDuck:",
							JOptionPane.WARNING_MESSAGE);
					// System.out.println("false");
					urlTest = null;
				}

				if (urlTest != null) {

					String scanCmd = "";

					if (plugin == "all") {
						scanCmd = "webscan --url " + url + " " + outputType + " " + outputFile;
					} else {
						scanCmd = "webscan " + "--plugins " + plugin + " --url " + url + " " + outputType + " "
								+ outputFile;
					}

					new cmd().run(scanCmd);

					System.out.println(scanCmd);

					String teString = System.getProperty("user.dir") + "/" + outputFile;
					teString = teString.replace("\\", "/");
					System.out.println(teString);
					// 预览报告
					//new getReports(scanCmd).show(teString, scanCmd);
				}

			}
		});
		Webscan.setLayout(null);
		Webscan.add(scan);
		Webscan.add(Plugins);
		Webscan.add(plugins);
		Webscan.add(out);
		Webscan.add(output);
		Webscan.add(target);
		Webscan.add(targetUrl);

		JLabel lblNewLabel = new JLabel("XRAY");
		lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 50));
		lblNewLabel.setBounds(189, 19, 110, 73);
		Webscan.add(lblNewLabel);

		// Servicescan
		JPanel Servicescan = new JPanel();
		mainWindows.addTab("Servicescan", null, Servicescan, null);// 加入第一个页面
		Servicescan.setLayout(null);

		JLabel service = new JLabel("target");
		service.setBounds(85, 109, 54, 15);
		Servicescan.add(service);

		iport = new JTextField();
		iport.setText("127.0.0.1:8009");
		iport.setBounds(132, 106, 158, 21);
		Servicescan.add(iport);
		iport.setColumns(10);

		JButton check = new JButton("Check");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String iportString = iport.getText();
				String scanCmd = "servicescan --target " + iportString;
				new cmd().run(scanCmd);

			}
		});
		check.setBounds(300, 105, 93, 23);
		Servicescan.add(check);

		JLabel lblNewLabel_3 = new JLabel("XRAY");
		lblNewLabel_3.setFont(new Font("SimSun", Font.PLAIN, 50));
		lblNewLabel_3.setBounds(180, 26, 110, 58);
		Servicescan.add(lblNewLabel_3);

		// PassiveScan
		JPanel passiveScan = new JPanel();
		mainWindows.addTab("passiveScan", null, passiveScan, null);
		passiveScan.setLayout(null);

		JComboBox output_1 = new JComboBox();
		output_1.setModel(new DefaultComboBoxModel(new String[] { "html", "json", "webhook" }));
		output_1.setBounds(307, 98, 54, 21);
		passiveScan.add(output_1);

		JLabel lblNewLabel_1 = new JLabel("output");
		lblNewLabel_1.setBounds(254, 101, 45, 15);
		passiveScan.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("iport");
		lblNewLabel_2.setBounds(99, 101, 39, 15);
		passiveScan.add(lblNewLabel_2);

		listenIP = new JTextField();
		listenIP.setText("127.0.0.1:55555");
		listenIP.setBounds(139, 98, 105, 21);
		passiveScan.add(listenIP);
		listenIP.setColumns(10);

		JButton listen = new JButton("LISTEN");
		listen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String iporsString = listenIP.getText();
				String outputStr = (String) output_1.getSelectedItem();
				// System.out.println((String) output.getSelectedItem());

				//

				String timestamp = String.valueOf(System.currentTimeMillis());
				String outputType = "";
				String outputFile = "";

				if (outputStr == "html") {
					outputType = "--html-output";
					outputFile = timestamp + ".html";
				} else if (outputStr == "json") {
					outputType = "--json-output";
					outputFile = timestamp + ".json";
				} else {
					outputType = "--webhook-output";
					outputFile = webHookUrl.getText();
					if (outputFile == "") {
						Component jPanel = null;
						JOptionPane.showMessageDialog(jPanel, "Webhook not set", "TheKingOfDuck:",
								JOptionPane.WARNING_MESSAGE);
					}
				}

				String scanCmd = "webscan --listen " + iporsString + " " + outputType + " " + outputFile;
				;
				new cmd().run(scanCmd);
			}
		});
		listen.setBounds(99, 130, 262, 23);
		passiveScan.add(listen);

		JButton btnNewButton = new JButton("LISTEN");
		btnNewButton.setBounds(99, 140, 262, 23);
//		btnNewButton.addActionListener(new ActionListener() {
//			private Component jPanel;
//
//			public void actionPerformed(ActionEvent arg0) {
//
//				
//				
//			}
//		});

		// btnNewButton.add(btnNewButton);

		// Settings
		JPanel Settings = new JPanel();
		mainWindows.addTab("Settings", null, Settings, null);
		Settings.setLayout(null);

		webHookUrl = new JTextField();
		webHookUrl.setBounds(127, 73, 216, 21);
		webHookUrl.setColumns(10);

		// 读取配置文件
		File file = new File("config.ini"); // 创建文件对象
		try {
			FileReader in = new FileReader(file); // 创建FileReader对象
			char byt[] = new char[1024]; // 创建char型数组
			int len = in.read(byt); // 将字节读入数组
			webHookUrl.setText(new String(byt, 0, len)); // 设置文本域的显示信息
			in.close(); // 关闭流
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		JLabel webhooklab = new JLabel("WebHook");
		webhooklab.setBounds(31, 76, 79, 15);

		JButton webHookModify = new JButton("modify");
		webHookModify.setBounds(353, 72, 69, 23);
		webHookModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// 修改配置文件
				File file = new File("config.ini"); // 创建文件对象
				try {
					FileWriter out = new FileWriter(file); // 创建FileWriter对象
					String s = webHookUrl.getText(); // 获取文本域中的文本
					out.write(s); // 将信息写入磁盘文件
					out.close(); // 将流关闭
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		Settings.setLayout(null);
		Settings.add(webhooklab);
		Settings.add(webHookUrl);
		Settings.add(webHookModify);

	}

	public static void main(String[] args) {
		
		// GUI加载
		
//        //启用BeautyEye主题
//        try
//        {
//            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//        }
//
//        catch(Exception e)
//        {
//            System.exit(-1);
//        }
//
//        //关闭setup按钮
//        UIManager.put("RootPane.setupButtonVisible",false);


		// TODO: 创建主窗口
		// JFrame.setDefaultLookAndFeelDecorated(true);// 将组建外观设置为Java外观
		JFrame frame = new JFrame("Xray GUI " + config.VERSION);
		frame.getContentPane().setLayout(null);
		frame.setContentPane(new mainWindows());
		frame.setSize(500, 300);
		frame.setVisible(true);
		frame.setResizable(false);

	}

}
