import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.File;
import java.lang.StringIndexOutOfBoundsException;
import java.lang.invoke.VolatileCallSite;

import javax.swing.JFrame;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JSeparator;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.awt.Color;
import java.awt.Toolkit;

public class MainBoard {
	Properties p=new Properties();
	database qSQL=new database();
	FileOutputStream  fops=null;
	String RUN_path;
	private  JFrame frmVolte;
	
	public  void SetFrameEnable() {
		frmVolte.setEnabled(true);
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainBoard window = new MainBoard();
					window.frmVolte.setVisible(true);
					window.frmVolte.setResizable(false);
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
	}

	/**
	 * Create the application.
	 */
	public MainBoard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
//		Properties p=new Properties();
//		database qSQL=new database();
		RUN_path=qSQL.getConfig(1);
		try {
			fops=new FileOutputStream (System.getProperty("user.dir")+"\\RunRecord.log",false);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		frmVolte = new JFrame();
		frmVolte.setName("frame20");
		frmVolte.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Users\\HP\\git\\VoLTE_DataComparingTool\\src\\IMG\\logo2.gif"));
		frmVolte.setTitle("VoLTE\u4E09\u65B9\u6570\u636E\u5BF9\u6BD4\u5DE5\u5177");
		
		int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();  //获取屏幕宽度
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();//获取屏幕高度
		
		frmVolte.setBounds(screenWidth/2-260, screenHeight/2-200, 528, 394);
		frmVolte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVolte.getContentPane().setLayout(null);
		
		String[] JCBList1 = { "华为", "中兴", "爱立信", "诺基亚", "贝尔" };
		JComboBox comboBox = new JComboBox(JCBList1);
		comboBox.setBounds(31, 172, 79, 23);		
		frmVolte.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("\u5BFC\u5165HSS\u6570\u636E");

		btnNewButton.setBounds(120, 172, 113, 23);
		frmVolte.getContentPane().add(btnNewButton);
		
		JLabel lblHss = new JLabel("HSS:");
		lblHss.setFont(new Font("微软雅黑", Font.BOLD, 17));
		lblHss.setBounds(31, 147, 54, 15);
		frmVolte.getContentPane().add(lblHss);
		
		JButton btnNewButton_1 = new JButton("\u793A\u4F8B\u6587\u4EF6");
		btnNewButton_1.setBounds(336, 172, 93, 23);
		frmVolte.getContentPane().add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 68, 492, 2);
		frmVolte.getContentPane().add(separator);
		
		JLabel lblAs = new JLabel("HSS APN:");
		lblAs.setFont(new Font("微软雅黑", Font.BOLD, 17));
		lblAs.setBounds(31, 10, 97, 15);
		frmVolte.getContentPane().add(lblAs);
		
		String[] JCBList2 = { "华为", "中兴"};
		JComboBox comboBox_1 = new JComboBox(JCBList2);
		comboBox_1.setBounds(31, 35, 79, 23);
		frmVolte.getContentPane().add(comboBox_1);
		
		JButton btnNewButton_2 = new JButton("\u5BFC\u5165AS\u6570\u636E");
		btnNewButton_2.setBounds(120, 35, 113, 23);
		frmVolte.getContentPane().add(btnNewButton_2);
		
		JButton button = new JButton("\u793A\u4F8B\u6587\u4EF6");
		button.setBounds(336, 35, 93, 23);
		frmVolte.getContentPane().add(button);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 138, 492, 2);
		frmVolte.getContentPane().add(separator_1);
		
		JLabel lblEnumdns = new JLabel("ENUMDNS:");
		lblEnumdns.setFont(new Font("微软雅黑", Font.BOLD, 17));
		lblEnumdns.setBounds(31, 80, 152, 15);
		frmVolte.getContentPane().add(lblEnumdns);
		
		JComboBox comboBox_2 = new JComboBox(JCBList2);
		comboBox_2.setBounds(31, 105, 79, 23);
		frmVolte.getContentPane().add(comboBox_2);
		
		JButton btnenum = new JButton("\u5BFC\u5165ENUM\u6570\u636E");
		btnenum.setBounds(120, 105, 113, 23);
		frmVolte.getContentPane().add(btnenum);
		
		JButton button_2 = new JButton("\u793A\u4F8B\u6587\u4EF6");
		button_2.setBounds(336, 105, 93, 23);
		frmVolte.getContentPane().add(button_2);
		
		JLabel lblNewLabel = new JLabel("\u5C1A\u672A\u5BFC\u5165\u6570\u636E");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
		lblNewLabel.setBounds(138, 12, 364, 15);
		String label_str=qSQL.getConfig(2);
		if(label_str!=null)lblNewLabel.setText(label_str);
		frmVolte.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("\u5C1A\u672A\u5BFC\u5165\u6570\u636E");
		label.setForeground(Color.RED);
		label.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label.setBounds(138, 82, 364, 15);
		label_str=qSQL.getConfig(3);
		if(label_str!=null)label.setText(label_str);
		frmVolte.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5C1A\u672A\u5BFC\u5165\u6570\u636E");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_1.setBounds(138, 149, 364, 15);
		label_str=qSQL.getConfig(4);
		if(label_str!=null)label_1.setText(label_str);
		frmVolte.getContentPane().add(label_1);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qSQL.exportDATA();
			}
		});
		btnNewButton_3.setBounds(31, 322, 93, 23);
		frmVolte.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qSQL.GetUnionSet();
			}
		});
		btnNewButton_4.setBounds(152, 322, 93, 23);
		frmVolte.getContentPane().add(btnNewButton_4);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 208, 492, 2);
		frmVolte.getContentPane().add(separator_2);
		
		JLabel label_2 = new JLabel("\u8BBE\u7F6E:");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 17));
		label_2.setBounds(31, 220, 54, 15);
		frmVolte.getContentPane().add(label_2);
		
		JButton button_3 = new JButton("\u5BFC\u5165\u914D\u7F6E\u4FE1\u606F");
		button_3.setBounds(31, 245, 113, 23);
		frmVolte.getContentPane().add(button_3);
		
		JButton btnNewButton_5 = new JButton("\u5220\u9664\u6570\u636E");

		btnNewButton_5.setBounds(233, 172, 93, 23);
		frmVolte.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmVolte.setEnabled(false);
				DataAnalyze();
				
			}
		});
		btnNewButton_6.setBounds(269, 322, 93, 23);
		frmVolte.getContentPane().add(btnNewButton_6);
		
		JButton button_1 = new JButton("\u5BFC\u51FA\u914D\u7F6E\u4FE1\u606F");
		button_1.setBounds(31, 278, 113, 23);
		frmVolte.getContentPane().add(button_1);
		
		JLabel label_3 = new JLabel("\u5C1A\u672A\u5BFC\u5165\u6570\u636E");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_3.setBounds(84, 220, 364, 15);
		frmVolte.getContentPane().add(label_3);
		
		JButton button_4 = new JButton("\u5220\u9664\u6570\u636E");

		button_4.setBounds(233, 35, 93, 23);
		frmVolte.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("\u5220\u9664\u6570\u636E");

		button_5.setBounds(233, 105, 93, 23);
		frmVolte.getContentPane().add(button_5);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> MSISDN=new ArrayList<String>();
				JFileChooser fd = new JFileChooser();
				fd.setMultiSelectionEnabled(true);
				RUN_path=qSQL.getConfig(1);
//				System.out.println(RUN_path);
				if(RUN_path!=null) fd.setCurrentDirectory(new File(RUN_path));
				if(fd.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				File[] f= fd.getSelectedFiles();
				if(f.length != 0){
			        int i = 0;
			        int write = 0;
				    FileInputStream fis = null;
				    InputStreamReader isr = null;
					    BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
					        try {
					            if(comboBox_1.getSelectedIndex()==0) {     //选择为华为的HSS
					            	for(i=0;i<f.length;i++) {
					            		String str = "";
					            		fis = new FileInputStream(f[i]);// FileInputStream
					            		// 从文件系统中的某个文件中获取字节
							            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
							            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
							            // InputStreamReader的对象
							            while ((str = br.readLine()) != null) {
							            	if(str.substring(0,3).equals("460")) {
							                	MSISDN.add(str.substring(19,30))  ;
							                	write++;
							                	if(write%100==0) System.out.println("write="+write);
							            	}
							            }
							            
					            	}
					            }
					            else if(comboBox_1.getSelectedIndex()==1){//选择为中兴的HSS
					            	for(i=0;i<f.length;i++) {
					            		String str = "";
					            		fis = new FileInputStream(f[i]);// FileInputStream
					            		// 从文件系统中的某个文件中获取字节
							            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
							            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
							            // InputStreamReader的对象
							            while ((str = br.readLine()) != null) {
							            	if(str.startsWith("86")){
							                	MSISDN.add(str.substring(2,13))  ;
							                	write++;
							                	if(write%100==0) System.out.println("write="+write);
							            	}
							            }
							            
					            	}
					            }
					                                            

					            if(qSQL.IsConnected()!=true) qSQL.reConnect();
					            int APN_result=qSQL.insertAPNDATA(MSISDN);
					            if(APN_result==write) {
					            	String APN_input_total=String.valueOf(Integer.valueOf(qSQL.getConfig(6))+write);
					            	lblNewLabel.setText("APN文件导入成功！共导入"+ APN_input_total	 +"行数据！");
					            	qSQL.setConfig(2, lblNewLabel.getText());
					            	qSQL.setConfig(6, APN_input_total);
					            }else {
					            	lblNewLabel.setText("APN文件导入失败，请检查数据文件！");
					            }
					            	
				            
				        } catch (FileNotFoundException e1) {
				        	lblNewLabel.setText("找不到指定文件！");
				            System.out.println("找不到指定文件");
				        } catch (IOException e1) {
				        	lblNewLabel.setText("文件错误，请选择有效APN数据文件！");
				            System.out.println("读取文件失败");
				        }
					        catch (StringIndexOutOfBoundsException e1){
					        lblNewLabel.setText("文件错误，请选择有效APN数据文件！");
				        	System.out.println("文件格式错误！");
				        }
					        finally {
				        }
				            try {
				                br.close();
				                isr.close();
				                fis.close();
				                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
				            } catch (IOException e1) {
				                e1.printStackTrace();
				            }
				        }
				    

					
				else {
					lblNewLabel.setText("文件错误，请选择有效APN数据文件！");
				}
				
				}
				qSQL.setConfig(1,fd.getCurrentDirectory().toString());
			}
		});
		btnenum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> MSISDN=new ArrayList<String>();
				JFileChooser fd = new JFileChooser();
				RUN_path=qSQL.getConfig(1);
				if(RUN_path!="") fd.setCurrentDirectory(new File(RUN_path));
				if(fd.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				File f = fd.getSelectedFile();
				if(f != null){
//			        int i = 0;
			        int write = 0;
				    FileInputStream fis = null;
				    InputStreamReader isr = null;
					    BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
					        try {
					            String str = "";
					            fis = new FileInputStream(f);// FileInputStream
					            fops.write(("enumdns data insert start! Data file:\"" + f+"\"\r\n" ).getBytes());
					            // 从文件系统中的某个文件中获取字节
					            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
					            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
					                                            // InputStreamReader的对象
				                if(comboBox_1.getSelectedIndex()==0) {     //如果是华为DNS，进行数据处理
				                	int pos1,pos2;
						            while ((str = br.readLine()) != null) {
						            	pos1=str.indexOf("sip:+861");
						            	if(pos1!=-1) {                 //如果该行有特征码
						            		pos2=str.indexOf("@", pos1);
						            		if(pos2-pos1!=17) {       //如果该行有特征码，并且不是8610开头的座机
							                	MSISDN.add(str.substring(pos1+7,pos2)) ;
												write++;
												if(write%100==0) System.out.println("write="+write);	
						            		}
						            	}
						            }
						        }
				                else {              						//如果室中兴DNS，进行数据处理
						            while ((str = br.readLine()) != null) {
					                	MSISDN.add(str.substring(7,18)) ;
					                
										write++;
										if(write%100==0) System.out.println("write="+write);
						            }
			                	}
			                	
					            if(qSQL.IsConnected()!=true) qSQL.reConnect();
					            int APN_result=qSQL.insertENUMDNS_DATA(MSISDN);
					            if(APN_result==write) {
					            	String str1=Integer.toString(write);
					            	label.setText("ENUMDNS文件导入成功！共导入"+ str1	 +"行数据！");
					            	fops.write(("ENUMDNS data file insert success!Data file:\"" + f+"\"\r\n" ).getBytes());
					            	qSQL.setConfig(3,label.getText());
					            }else {
					            	label.setText("ENUMDNS文件导入失败，请检查数据文件！");
					            	fops.write(("ENUMDNS data file insert fail!Data file:\"" + f+"\"\r\n" ).getBytes());
					            }
					            	
				            
				        } catch (FileNotFoundException e1) {
				        	label.setText("找不到指定文件！");
				        	try {
								fops.write(("ENUMDNS data file insert fail,can't find the data file!Data file:\"" + f+"\"\r\n" ).getBytes());
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
				            System.out.println("找不到指定文件");
				        } catch (IOException e1) {
				        	label.setText("文件错误，请选择有效ENUMDNS数据文件！");
				        	try {
								fops.write(("ENUMDNS data file insert fail,file error!Data file:\"" + f+"\"\r\n" ).getBytes());
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
				            System.out.println("读取文件失败");
				        }
					      catch (StringIndexOutOfBoundsException e1){
					    	  label.setText("文件错误，请选择有效ENUMDNS数据文件！");
					    	  try {
								fops.write(("Data file error,please input valid enumdns data file!Data file:\"" + f+"\"\r\n" ).getBytes());
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
					    	  System.out.println("文件格式错误！");
				        }
					        finally {
				        }
				            try {
				                br.close();
				                isr.close();
				                fis.close();
				                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
				            } catch (IOException e1) {
				                e1.printStackTrace();
				            }
				        }
				    

					
				else {
					label.setText("文件错误，请选择有效ENUMDNS数据文件！");
					try {
						fops.write(("Data file error,please input valid enumdns data file!Data file:\"" + f+"\"\r\n" ).getBytes());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				}
				qSQL.setConfig(1,fd.getCurrentDirectory().toString());
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fd = new JFileChooser();
				fd.setMultiSelectionEnabled(true);
				RUN_path=qSQL.getConfig(1);
		        try {
				if(RUN_path!=null&&RUN_path!="") fd.setCurrentDirectory(new File(RUN_path));
				if(fd.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				File[] f= fd.getSelectedFiles();
				if(f.length != 0){
			        int i = 0;
			        int write = 0;
			        int Pos1=0;
				    FileInputStream fis = null;
				    InputStreamReader isr = null;
					BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
					String MSISDN,IMSI,IMPI,YHDZD_NUM,YHDZD_IND,MCAP;
					if(comboBox.getSelectedIndex()==0) {     //选择为华为的HSS
						for(i=0;i<f.length;i++) {
					            String str = "";
					            fis = new FileInputStream(f[i]);// FileInputStream
					            // 从文件系统中的某个文件中获取字节
					            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
					            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
					                                            // InputStreamReader的对象
					            fops.write(("HSS data insert start! Data file:\"" + f[i]+"\"\r\n" ).getBytes());
					            while ((str = br.readLine()) != null) {
					            	if(str.startsWith("<SUBBEGIN")){
										MSISDN="";
										IMSI="";
										IMPI="";
										MCAP="";
										YHDZD_IND="";
										YHDZD_NUM="";
										ArrayList<String> IMPU=new ArrayList<String>();
										ArrayList<String> sIFC=new ArrayList<String>();
					            		while(!(str=br.readLine().trim()).equals("<SUBEND")) {
					            			Pos1=0;
					            			if(str.startsWith("MCAP=")&&MCAP=="")  MCAP=str.substring(5,str.indexOf(";"));
					            			else if(str.startsWith("IMPI=")&&IMPI=="") IMPI=str.substring(5,str.indexOf(";"));
					            			else if(str.startsWith("IMSI=")&&IMSI=="") IMSI=str.substring(5,str.indexOf(";"));
					            			else if(str.startsWith("MSISDN=")&&MSISDN=="") MSISDN=str.substring(9,str.indexOf(";"));
					            			else if(str.startsWith("IMPU=")&&!(IMPU.contains(str.substring(5,str.indexOf(";"))))) IMPU.add(str.substring(5,str.indexOf(";")));
					            			else if(str.startsWith("SharediFCSetID=")) sIFC.add(str.substring(15,str.indexOf(";")));
					            			else if(str.startsWith("ServiceData=")&&(Pos1=str.indexOf("<conpr><act>1</act><seq>2</seq><cdti>0</cdti><num>tel:"))!=-1) {
					            				YHDZD_IND="1";
					            				YHDZD_NUM=str.substring(Pos1+57,str.indexOf("</num>",Pos1));
					            			}
					            			else if(str.startsWith("ServiceData=")&&(Pos1=str.indexOf("<hon><act>1</act><mix>1</mix><mode>0</mode><calllimit>1</calllimit><dft><shownum>tel:"))!=-1) {
					            				YHDZD_IND="2";
					            				YHDZD_NUM=str.substring(Pos1+88,str.indexOf("</shownum>",Pos1));
					            			}
					            				
					            		}
					            		
					            		int Wr_result = qSQL.insertHSS_DATA(MSISDN, IMSI, IMPI,YHDZD_IND, YHDZD_NUM, MCAP, IMPU, sIFC);
					            		if(Wr_result==0) {
					            			fops.write(("Find error when insert HSS data! "+MSISDN+" insert error！\r\n").getBytes());
					            		}else write++;
					            	}
									if(write%1000==0&&write!=0) {
										System.out.println("write="+write);
										qSQL.DoCommit();
									}
					            }
			            		if(write!=0){
					            	fops.write(("HSS data file import success,import"+write+" lines of data!Data file:\"" + f[i]+"\"\r\n" ).getBytes());
			            		}
//			            		qSQL.DoCommit();    //完成剩余部分的commit
					        }
						
					}else if(comboBox.getSelectedIndex()==1) {    //选择是中兴HSS时
						for(i=0;i<f.length;i++) {
					            String str = "";
					            fis = new FileInputStream(f[i]);// FileInputStream
					            // 从文件系统中的某个文件中获取字节
					            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
					            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
					                                            // InputStreamReader的对象
					            fops.write(("HSS data insert start! Data file:\"" + f[i]+"\"\r\n" ).getBytes());
					            while ((str = br.readLine()) != null) {
					            	if(str.startsWith("PUIINFO:")){
										MSISDN="";
										IMSI="";
										IMPI="";
										MCAP="";
										YHDZD_IND="";
										YHDZD_NUM="";
										ArrayList<String> IMPU=new ArrayList<String>();
										ArrayList<String> sIFC=new ArrayList<String>();
					            		do {
					            			Pos1=0;
					            			if(str.startsWith("PUIINFO:")) {
					            				int i1=(str.indexOf("SOAPSIFCIDList="))+15;
					            				int i2=(str.indexOf("$",i1));
					            				int i3=str.indexOf("^",i1);
					            				if(i3==-1||i2<i3) {//如果该行没有^符，则直接去=后和$符之间的内容。
					            					sIFC.add(str.substring(i1,str.indexOf("$",i1)));
					            				}
					            				else {
					            					while(i3<i2&&i3!=-1) {  //当该行在,符前还有$符并且在上个^符之后还有^符的情况下，继续循环
					            					sIFC.add(str.substring(i1,i3));
					            					i1=i3+1;
					            					i3=str.indexOf("^",i1);
					            					}
					            					sIFC.add(str.substring(i1,str.indexOf("$",i1)));
					            				}
					            				
					            				i1=(str.indexOf("PUILIST="))+8;
					            				i2=(str.indexOf(",",i1));
					            				i3=str.indexOf("$",i1);
					            				if(i3==-1||i2<i3) {//如果该行没有$符，则直接去=后和,符之间的内容。
					            					IMPU.add(str.substring(i1,str.indexOf(",",i1)));
					            				}else {
						            				while(i3<i2&&i3!=-1) {  //当该行在,符前还有$符并且在上个^符之后还有^符的情况下，继续循环
						            					IMPU.add(str.substring(i1,i3));
						            					i1=i3+1;
						            					i3=str.indexOf("$",i1);
						            				}
						            				IMPU.add(str.substring(i1,str.indexOf(",",i1)));
					            				}
					            			}
					            			else if(str.startsWith("PVIINFO:")) {
					            				int i1=(str.indexOf("PVILIST="))+8;
					            				IMPI=str.substring(i1,str.indexOf(",",i1));
					            						
					            				i1=(str.indexOf("IMSIList="))+9;
					            				IMSI=str.substring(i1,str.indexOf(",",i1));
					            				
					            				i1=(str.indexOf("ISDNList="))+11;
					            				MSISDN=str.substring(i1,str.length());
					            			}
					            			else if(str.startsWith("REPOSINFO:")&&(Pos1=str.indexOf("<conpr><act>1</act><seq>2</seq><cdti>0</cdti><num>tel:"))!=-1) {
					            				YHDZD_IND="1";
					            				YHDZD_NUM=str.substring(Pos1+57,str.indexOf("</num>",Pos1));
					            			}
					            			else if(str.startsWith("REPOSINFO:")&&(Pos1=str.indexOf("<hon><act>1</act>"))!=-1) {
					            				YHDZD_IND="2";
					            				YHDZD_NUM=str.substring(Pos1+88,str.indexOf("</shownum>",Pos1));
					            			}
					            			else if(str.startsWith("SIDINFO:")) {
					            				int i1=(str.indexOf("CAPSIDLIST="))+11;
					            				MCAP=str.substring(i1,str.indexOf(",",i1));
					            			}
					            			
					            			else if(str.startsWith("ServiceData=")&&(Pos1=str.indexOf("<conpr><act>1</act><seq>2</seq><cdti>0</cdti><num>tel:"))!=-1) {
					            				YHDZD_IND="1";
					            				YHDZD_NUM=str.substring(Pos1+57,str.indexOf("</num>",Pos1));
					            			}
					            			else if(str.startsWith("ServiceData=")&&(Pos1=str.indexOf("<hon><act>1</act><mix>1</mix><mode>0</mode><calllimit>1</calllimit><dft><shownum>tel:"))!=-1) {
					            				YHDZD_IND="2";
					            				YHDZD_NUM=str.substring(Pos1+88,str.indexOf("</shownum>",Pos1));
					            			}
					            				
					            		}while(!(str=br.readLine().trim()).equals("ENDOFUSER")) ;
					            		int Wr_result = qSQL.insertHSS_DATA(MSISDN, IMSI, IMPI,YHDZD_IND, YHDZD_NUM, MCAP, IMPU, sIFC);
					            		if(Wr_result==0) {
					            			fops.write(("Find error when insert HSS data! "+MSISDN+" insert error！\r\n").getBytes());
					            		}else write++;
					            	}
									if(write%100==0&&write!=0) {
										System.out.println("write="+write);
										qSQL.DoCommit();
									}
					            }
			            		if(write!=0){
					            	fops.write(("HSS data file import success,import"+write+" lines of data!Data file:\"" + f[i]+"\"\r\n" ).getBytes());
			            		}
//			            		qSQL.DoCommit();    //完成剩余部分的commit
							}
						}				
					if(write!=0){
						String HSS_input_total=String.valueOf(Integer.valueOf(qSQL.getConfig(5))+write);
						label_1.setText("HSS文件导入成功！本次共导入"+ write	 +"行数据！一共导入" + HSS_input_total+"行数据！");
		            	qSQL.setConfig(4,label_1.getText());
		            	qSQL.setConfig(5, HSS_input_total);
						
					}else if(write==0) {
						label_1.setText("文件有误！无数据导入。");
						fops.write(("error exist！no data import。\r\n" ).getBytes());
					}
	                br.close();
	                isr.close();
	                fis.close();
	                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
					
				}
				else {
					label_1.setText("文件错误，请选择有效HSS数据文件！");
					try {
						fops.write("Data file error,please input valid HSS data file!\r\n" .getBytes());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				}
		        } catch (FileNotFoundException e1) {
		        	label_1.setText("找不到指定文件！");
		            System.out.println("找不到指定文件");
		        } catch (IOException e1) {
		        	label_1.setText("文件错误，请选择有效ENUMDNS数据文件！");
		            System.out.println("读取文件失败");
		        }
			      catch (StringIndexOutOfBoundsException e1){
			    	  label_1.setText("文件错误，请选择有效ENUMDNS数据文件！");
			    	  System.out.println("文件格式错误！");
		        }
				qSQL.setConfig(1,fd.getCurrentDirectory().toString());
			}
		});
		
		btnNewButton_5.addActionListener(new ActionListener() {    //删除hss 的IMS的data
			public void actionPerformed(ActionEvent arg0) {
				qSQL.delHSS_DATA();
				qSQL.setConfig(5, "0");
				qSQL.setConfig(4, "尚未导入数据");
				label_1.setText("删除成功！");
			}
		});
		
		button_4.addActionListener(new ActionListener() {           //删除hss的apn数据
			public void actionPerformed(ActionEvent arg0) {
				qSQL.delAPN_DATA();
				qSQL.setConfig(6, "0");
				qSQL.setConfig(2, "尚未导入数据");
				lblNewLabel.setText("删除成功！");
			}
		});
		
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qSQL.delENUMDNS_DATA();
				qSQL.setConfig(3, "尚未导入数据");
				lblNewLabel.setText("删除成功！");
			}
		});
		
		button_3.addActionListener(new ActionListener() {           //导入配置文件
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fd = new JFileChooser();
				RUN_path=qSQL.getConfig(1);
				if((RUN_path!="") &&( RUN_path!=null))  fd.setCurrentDirectory(new File(RUN_path));
				if(fd.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
					String tmp_String= fd.getSelectedFile().getPath();
					qSQL.setConfig(1, tmp_String.substring(0, tmp_String.lastIndexOf("\\")));
					System.out.print(tmp_String.lastIndexOf("\\"));
					File f = fd.getSelectedFile();
					if(f != null){
						try {
							List<Map<String,String>> list = ExcelTool.readExcel(f, 1);
							if(list==null) {
								label_3.setText("文件错误！");
								return;
							}	 
							qSQL.insertCity(list);
							list.clear();	 
							list = ExcelTool.readExcel(f, 2);
							if(list==null) {
								label_3.setText("文件错误！");
								return;
							}	 
							qSQL.insertHSS(list);	
							list.clear();	
							list = ExcelTool.readExcel(f, 3);
							if(list==null) {
								label_3.setText("文件错误！");
								return;
							}	 
							qSQL.insertNumlist(list);	
							
							label_3.setText("配置数据导入成功！");
							
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							label_3.setText("配置文件未找到！");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							label_3.setText("配置文件读取异常！");
						} catch(SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();	
						} 
						
						
					}
				}
				
				
			}
		});
		
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fd = new JFileChooser();
				RUN_path=qSQL.getConfig(1);
				if((RUN_path!="") &&( RUN_path!=null))  fd.setCurrentDirectory(new File(RUN_path));
				fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(fd.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
					qSQL.setConfig(1, fd.getSelectedFile().getPath());
					File f = new File(fd.getSelectedFile().getPath()+"\\setup.xlsx");
					if(f.exists()) {
						JDialog dialog=new JDialog(frmVolte,"该文件已经存在，是否覆盖?",true);
						dialog.setBounds(frmVolte.getX()+150, frmVolte.getY()+100, 350, 80);//设置弹出对话框的位置和大小
						dialog.getContentPane().setLayout(new FlowLayout());//设置弹出对话框的布局为流式布局
						JLabel lab = new JLabel();//创建lab标签填写提示内容
						lab.setText("文件已存在，是否覆盖？");
						JButton okBut = new JButton("确定");//创建确定按钮
						JButton noBut=new JButton("取消"); //创建取消按钮
						dialog.getContentPane().add(lab);
						dialog.getContentPane().add(okBut);
						dialog.getContentPane().add(noBut);

				        // 确定按钮监听器
				        okBut.addActionListener(new ActionListener() {
				            public void actionPerformed(ActionEvent e) {
				            	dialog.setVisible(false);
				            	if(!f.delete()) {
				            		label_3.setText("原有配置文件删除失败，未成功导出!");
				            		return;
				            	}
				            	try {
									f.createNewFile();
					            	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
					            	list=qSQL.GetCity();
					            	ExcelTool.writeExcel(list, 1, f);
					            	list=qSQL.GetHSS();
					            	ExcelTool.writeExcel(list, 2, f);
					            	list=qSQL.GetNumlist();
					            	ExcelTool.writeExcel(list, 3, f);
					            	label_3.setText("成功导出!");
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

				            	
				                
				            }

				        });
						
				        // 取消按钮监听器
				        noBut.addActionListener(new ActionListener() {
				            public void actionPerformed(ActionEvent e) {
				                dialog.setVisible(false);
				            }

				        });
						
						dialog.setVisible(true);
					}else {
		            	try {
							f.createNewFile();
			            	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			            	list=qSQL.GetCity();
			            	ExcelTool.writeExcel(list, 1, f);
			            	list=qSQL.GetHSS();
			            	ExcelTool.writeExcel(list, 2, f);
			            	list=qSQL.GetNumlist();
			            	ExcelTool.writeExcel(list, 3, f);
			            	label_3.setText("成功导出!");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
				
				
			}
		});
		
	}
	
	
	
	
	private void DataAnalyze() {
		qSQL.Data_Analyze();
		String[] ResultCount=qSQL.Analyze_Count();

		String cont = read(System.getProperty("user.dir")+ "\\WEB\\chartjs.html.orig");
		cont = cont.replace("{%HSS_DATA%}", ResultCount[0]);
		cont = cont.replace("{%APN_DATA%}", ResultCount[1]);
		cont = cont.replace("{%ENUMDNS_DATA%}", ResultCount[2]);
		cont = cont.replace("{%ALL_FIX%}", ResultCount[3]);
		cont = cont.replace("{%HSS_APN_FIX%}", ResultCount[4]);
		cont = cont.replace("{%HSS_ENUMDNS_FIX%}", ResultCount[5]);
		cont = cont.replace("{%APN_ENUMDNS_FIX%}", ResultCount[6]);
		cont = cont.replace("{%HSS_ONLY%}", ResultCount[7]);
		cont = cont.replace("{%APN_ONLY%}", ResultCount[8]);
		cont = cont.replace("{%ENUMDNS_ONLY%}", ResultCount[9]);
		int YHDZD_TOTAL=Integer.parseInt(ResultCount[10])+Integer.parseInt(ResultCount[11]);
		cont = cont.replace("{%YHDZD_TOTAL%}",Integer.toString(YHDZD_TOTAL) );
		cont = cont.replace("{%YHDZD_MASTER%}", ResultCount[10]);
		cont = cont.replace("{%YHDZD_SLAVE%}", ResultCount[11]);
		String MCAP_STR;
		if(ResultCount[12].indexOf(";")<=ResultCount[12].length()) {    //判断是否为空
			do{
				MCAP_STR=ResultCount[12].substring(0,ResultCount[12].indexOf(";"));
				cont = cont.replace("{%MCAP_STR%}", "                        <li style=\"display: inline-block;color: black;font-weight: bold;font-size:18px;width: 150px;\">"+MCAP_STR+ "</li>\r\n{%MCAP_STR%}");
				ResultCount[12]=ResultCount[12].substring(ResultCount[12].indexOf(";")+1);
			}while(ResultCount[12].indexOf(";")!=-1);
		}
		cont = cont.replace("{%MCAP_STR%}", "");   //删除{%MCAP_STR%}标记
		String SIFC_STR,SIFC,SIFC_COUNT;
		if(ResultCount[13].indexOf(";")<=ResultCount[13].length()) {    //判断是否为空
			do{
				SIFC_STR=ResultCount[13].substring(0,ResultCount[13].indexOf(";"));
				SIFC=SIFC_STR.substring(0,SIFC_STR.indexOf(":"));
				SIFC_COUNT=SIFC_STR.substring(SIFC_STR.indexOf(":")+1);
				cont = cont.replace("{%SIFC_STR%}", "                    <div class=\"col-lg-1\" style=\"background-color:#61A0A8;margin: 5px 10px ;height:75px,width:50px;border-radius: 5px;box-shadow:0px 3px 2px #aab2bd;\">\r\n" + 
						"                        <div style=\"margin:0px 20px;padding:5px 0px 0px 0px;\">\r\n" + 
						"                            <span style=\"color:black;font-size: 15px;font-weight: bold;\">"+SIFC+"</span>\r\n" + 
						"                        </div>                            \r\n" + 
						"                        <div style=\"color:white;font-size:28px;margin:0px 15px;\">"+SIFC_COUNT+"</div>\r\n" + 
						"                    </div>\r\n{%SIFC_STR%}");
				
				
				cont = cont.replace("{%SIFC_STR1%}", "                        <li style=\"display: inline-block;color: black;font-weight: bold;font-size:18px;width: 150px;\">"+SIFC_STR+ "</li>\r\n{%SIFC_STR1%}");
				ResultCount[13]=ResultCount[13].substring(ResultCount[13].indexOf(";")+1);
			}while(ResultCount[13].indexOf(";")!=-1);
			cont = cont.replace("{%SIFC_STR%}", "");   //删除{%MCAP_STR%}标记
			cont = cont.replace("{%SIFC_STR1%}", "");   //删除{%MCAP_STR%}标记
		}
		
		
		File f=new File(System.getProperty("user.dir")+ "\\WEB\\chartjs.html");
		write(cont, f);
		
		ResultDisplay.openForm(System.getProperty("user.dir")+ "\\WEB\\chartjs.html", "ResultDisplay",this);
//		int HSS_DATA_COUNT=qSQL.CountItems("HSS_DATA");
//		int ALL_FIX_COUNT=qSQL.CountItems("ALL_FIX");
		
		
		
//		System.out.println(Integer.toString(HSS_DATA_COUNT)+" "+Integer.toString(ALL_FIX_COUNT) +" "+aa);
		
		
	}
	
	private  String read(String path) {
		StringBuffer res = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(path);   
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");   
			BufferedReader reader = new BufferedReader(isr);   
//		File file = new File(path);

			String line = null;
		  
//		   BufferedReader reader = new BufferedReader(new FileReader(file));
		   while ((line = reader.readLine()) != null) {
		    res.append(line + "\r\n");
		   }
		   reader.close();
		  } catch (FileNotFoundException e) {
		   e.printStackTrace();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  return res.toString();
	 }

 

	private  boolean write(String cont, File dist) {
		 try {
			 FileOutputStream FOS=new FileOutputStream(dist.getPath());
			 OutputStreamWriter writer = new OutputStreamWriter(FOS, "utf-8"); 
		   
		   writer.write(cont);
		   writer.flush();
		   writer.close();
		   FOS.close();
		   return true;
		 } catch (IOException e) {
		   e.printStackTrace();
		   return false;
		 }
	}
}
