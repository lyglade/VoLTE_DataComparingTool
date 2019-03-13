import java.awt.EventQueue;
import java.io.File;
import java.lang.StringIndexOutOfBoundsException;

import javax.swing.JFrame;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.awt.Color;
import java.awt.Dimension;

public class MainBoard {
	Properties p=new Properties();
	database qSQL=new database();
	FileOutputStream  fops=null;
	String RUN_path;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainBoard window = new MainBoard();
					window.frame.setVisible(true);	

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
		
		frame = new JFrame();
		frame.setBounds(100, 100, 528, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String[] JCBList1 = { "华为", "中兴", "爱立信" ,"诺基亚","贝尔"};
		JComboBox comboBox = new JComboBox(JCBList1);
		comboBox.setBounds(31, 172, 79, 23);		
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("\u5BFC\u5165HSS\u6570\u636E");

		btnNewButton.setBounds(120, 172, 113, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblHss = new JLabel("HSS:");
		lblHss.setFont(new Font("微软雅黑", Font.BOLD, 17));
		lblHss.setBounds(31, 147, 54, 15);
		frame.getContentPane().add(lblHss);
		
		JButton btnNewButton_1 = new JButton("\u793A\u4F8B\u6587\u4EF6");
		btnNewButton_1.setBounds(336, 172, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 68, 492, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblAs = new JLabel("AS:");
		lblAs.setFont(new Font("微软雅黑", Font.BOLD, 17));
		lblAs.setBounds(31, 10, 54, 15);
		frame.getContentPane().add(lblAs);
		
		String[] JCBList2 = { "华为", "中兴"};
		JComboBox comboBox_1 = new JComboBox(JCBList2);
		comboBox_1.setBounds(31, 35, 79, 23);
		frame.getContentPane().add(comboBox_1);
		
		JButton btnNewButton_2 = new JButton("\u5BFC\u5165AS\u6570\u636E");
		btnNewButton_2.setBounds(120, 35, 113, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton button = new JButton("\u793A\u4F8B\u6587\u4EF6");
		button.setBounds(336, 35, 93, 23);
		frame.getContentPane().add(button);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 138, 492, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblEnumdns = new JLabel("ENUMDNS:");
		lblEnumdns.setFont(new Font("微软雅黑", Font.BOLD, 17));
		lblEnumdns.setBounds(31, 80, 152, 15);
		frame.getContentPane().add(lblEnumdns);
		
		JComboBox comboBox_2 = new JComboBox(JCBList2);
		comboBox_2.setBounds(31, 105, 79, 23);
		frame.getContentPane().add(comboBox_2);
		
		JButton button_1 = new JButton("\u5BFC\u5165AS\u6570\u636E");
		button_1.setBounds(120, 105, 113, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u793A\u4F8B\u6587\u4EF6");
		button_2.setBounds(336, 105, 93, 23);
		frame.getContentPane().add(button_2);
		
		JLabel lblNewLabel = new JLabel("\u5C1A\u672A\u5BFC\u5165\u6570\u636E");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
		lblNewLabel.setBounds(138, 12, 364, 15);
		String label_str=qSQL.getConfig(2);
		if(label_str!=null)lblNewLabel.setText(label_str);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("\u5C1A\u672A\u5BFC\u5165\u6570\u636E");
		label.setForeground(Color.RED);
		label.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label.setBounds(138, 82, 364, 15);
		label_str=qSQL.getConfig(3);
		if(label_str!=null)label.setText(label_str);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5C1A\u672A\u5BFC\u5165\u6570\u636E");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 12));
		label_1.setBounds(138, 149, 364, 15);
		label_str=qSQL.getConfig(4);
		if(label_str!=null)label_1.setText(label_str);
		frame.getContentPane().add(label_1);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qSQL.exportDATA();
			}
		});
		btnNewButton_3.setBounds(10, 291, 93, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qSQL.GetUnionSet();
			}
		});
		btnNewButton_4.setBounds(140, 291, 93, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 208, 492, 2);
		frame.getContentPane().add(separator_2);
		
		JLabel label_2 = new JLabel("\u8BBE\u7F6E:");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 17));
		label_2.setBounds(31, 220, 54, 15);
		frame.getContentPane().add(label_2);
		
		JButton button_3 = new JButton("\u5BFC\u5165\u7701\u5185\u53F7\u6BB5");
		button_3.setBounds(31, 245, 113, 23);
		frame.getContentPane().add(button_3);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList MSISDN=new ArrayList();
				JFileChooser fd = new JFileChooser();
				RUN_path=qSQL.getConfig(1);
				System.out.println(RUN_path);
				if(RUN_path!=null) fd.setCurrentDirectory(new File(RUN_path));
				if(fd.showOpenDialog(null)==fd.APPROVE_OPTION) {
				File f = fd.getSelectedFile();
				if(f != null){
			        int i = 0;
			        int write = 0;
				    FileInputStream fis = null;
				    InputStreamReader isr = null;
					    BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
					        try {
					            String str = "";
					            fis = new FileInputStream(f);// FileInputStream
					            
					            // 从文件系统中的某个文件中获取字节
					            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
					            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
					                                            // InputStreamReader的对象
					            while ((str = br.readLine()) != null) {
					                if(comboBox_1.getSelectedIndex()==0) {     //如果是华为AS，进行数据处理
					                	MSISDN.add(str.substring(7,18))  ;
					                }
					                else {              						//如果室中兴AS，进行数据处理
					                	MSISDN.add(str.substring(7,18)) ;
					                }
									write++;
									if(write%100==0) System.out.println("write="+write);
					            }
					            if(qSQL.IsConnected()!=true) qSQL.reConnect();
					            int AS_result=qSQL.insertASDATA(MSISDN);
					            if(AS_result==write) {
					            	String str1=Integer.toString(write);
					            	lblNewLabel.setText("AS文件导入成功！共导入"+ str1	 +"行数据！");
					            	qSQL.setConfig(2, lblNewLabel.getText());
					            }else {
					            	lblNewLabel.setText("AS文件导入失败，请检查数据文件！");
					            }
					            	
				            
				        } catch (FileNotFoundException e1) {
				        	lblNewLabel.setText("找不到指定文件！");
				            System.out.println("找不到指定文件");
				        } catch (IOException e1) {
				        	lblNewLabel.setText("文件错误，请选择有效AS数据文件！");
				            System.out.println("读取文件失败");
				        }
					        catch (StringIndexOutOfBoundsException e1){
					        lblNewLabel.setText("文件错误，请选择有效AS数据文件！");
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
					lblNewLabel.setText("文件错误，请选择有效AS数据文件！");
				}
				
				}
				qSQL.setConfig(1,fd.getCurrentDirectory().toString());
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList MSISDN=new ArrayList();
				JFileChooser fd = new JFileChooser();
				RUN_path=qSQL.getConfig(1);
				if(RUN_path!="") fd.setCurrentDirectory(new File(RUN_path));
				if(fd.showOpenDialog(null)==fd.APPROVE_OPTION) {
				File f = fd.getSelectedFile();
				if(f != null){
			        int i = 0;
			        int write = 0;
				    FileInputStream fis = null;
				    InputStreamReader isr = null;
					    BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
					        try {
					            String str = "";
					            fis = new FileInputStream(f);// FileInputStream
					            fops.write(("enumdns data insert start! Data file:\"" + f+"\"" ).getBytes());
					            // 从文件系统中的某个文件中获取字节
					            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
					            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
					                                            // InputStreamReader的对象
					            while ((str = br.readLine()) != null) {
					                if(comboBox_1.getSelectedIndex()==0) {     //如果是华为DNS，进行数据处理
					                	MSISDN.add(str.substring(7,18))  ;
					                }
					                else {              						//如果室中兴DNS，进行数据处理
					                	MSISDN.add(str.substring(7,18)) ;
					                }
									write++;
									if(write%100==0) System.out.println("write="+write);
					            }
					            if(qSQL.IsConnected()!=true) qSQL.reConnect();
					            int AS_result=qSQL.insertENUMDNS_DATA(MSISDN);
					            if(AS_result==write) {
					            	String str1=Integer.toString(write);
					            	label.setText("ENUMDNS文件导入成功！共导入"+ str1	 +"行数据！");
					            	fops.write(("ENUMDNS data file insert success!Data file:\"" + f+"\"" ).getBytes());
					            	qSQL.setConfig(3,label.getText());
					            }else {
					            	label.setText("ENUMDNS文件导入失败，请检查数据文件！");
					            	fops.write(("ENUMDNS data file insert fail!Data file:\"" + f+"\"" ).getBytes());
					            }
					            	
				            
				        } catch (FileNotFoundException e1) {
				        	label.setText("找不到指定文件！");
				        	try {
								fops.write(("ENUMDNS data file insert fail,can't find the data file!Data file:\"" + f+"\"" ).getBytes());
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
				            System.out.println("找不到指定文件");
				        } catch (IOException e1) {
				        	label.setText("文件错误，请选择有效ENUMDNS数据文件！");
				        	try {
								fops.write(("ENUMDNS data file insert fail,file error!Data file:\"" + f+"\"" ).getBytes());
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
				            System.out.println("读取文件失败");
				        }
					      catch (StringIndexOutOfBoundsException e1){
					    	  label.setText("文件错误，请选择有效ENUMDNS数据文件！");
					    	  try {
								fops.write(("Data file error,please input valid enumdns data file!Data file:\"" + f+"\"" ).getBytes());
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
						fops.write(("Data file error,please input valid enumdns data file!Data file:\"" + f+"\"" ).getBytes());
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
				if(RUN_path!="") fd.setCurrentDirectory(new File(RUN_path));
				if(fd.showOpenDialog(null)==fd.APPROVE_OPTION) {
				File[] f= fd.getSelectedFiles();
				if(f.length != 0){
			        int i = 0;
			        int write = 0;
			        int Pos1=0;
				    FileInputStream fis = null;
				    InputStreamReader isr = null;
					BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
					String MSISDN,IMSI,IMPI,YHDZD_NUM,YHDZD_IND,MCAP;
					for(i=0;i<f.length;i++) {
				        try {
				            String str = "";
				            fis = new FileInputStream(f[i]);// FileInputStream
				            // 从文件系统中的某个文件中获取字节
				            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
				            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
				                                            // InputStreamReader的对象
				            fops.write(("HSS data insert start! Data file:\"" + f[i]+"\"" ).getBytes());
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
				            		while((str=br.readLine().trim())!="<SUBEND") {
				            			Pos1=0;
				            			if(str.startsWith("MCAP")&&MCAP=="")  MCAP=str.substring(5,str.indexOf(";"));
				            			else if(str.startsWith("IMPI")&&IMPI=="") IMPI=str.substring(5,str.indexOf(";"));
				            			else if(str.startsWith("IMSI")&&IMSI=="") IMSI=str.substring(5,str.indexOf(";"));
				            			else if(str.startsWith("MSISDN")&&MSISDN=="") MSISDN=str.substring(9,str.indexOf(";"));
				            			else if(str.startsWith("IMPU")) IMPU.add(str.substring(9,str.indexOf(";")));
				            			else if(str.startsWith("SharediFCSetID")) sIFC.add(str.substring(15,str.indexOf(";")));
				            			else if(str.startsWith("ServiceData=")&&(Pos1=str.indexOf("<conpr><act>1</act><seq>2</seq><cdti>0</cdti><num>tel:"))!=0) {
				            				YHDZD_IND=1;
				            				YHDZD_NUM=str.substring(Pos1+62,str.indexOf("</num>",Pos1)-(Pos1+63));
				            			}
				            				
				            		}
				            		int Wr_result = qSQL.insertHSS_DATA(MSISDN, IMSI, IMPI, YHDZD_NUM, MCAP, IMPU, sIFC);
				            		if(Wr_result==0) {
				            			fops.write(("Find error when insert HSS data! "+MSISDN+" insert error！").getBytes());
//				            			FileOutputStream  fps=null;
//				            			try {
//				            				fps=new FileOutputStream ("d:\\as_data.txt",false);
//				            				String RSrecord=null;
//				            				iRS= iStmt.executeQuery("SELECT msisdn FROM AS_DATA");
//				            				System.out.println(iRS.getMetaData());
//				            				RSrecord=iRS.getString(1);
//				            				while(iRS.next()) {
//				            					RSrecord=iRS.getString(1);
//				            					fps.write(RSrecord.getBytes());
//				            					fps.write("\r\n".getBytes());
//				            				}
//				            				fps.close();
				            		}
				            		
				            		
				            	}
				            	
								write++;
								if(write%100==0) System.out.println("write="+write);
				            }
				            
				          
					            	
				            
				        } catch (FileNotFoundException e1) {
				        	label.setText("找不到指定文件！");
				            System.out.println("找不到指定文件");
				        } catch (IOException e1) {
				        	label.setText("文件错误，请选择有效ENUMDNS数据文件！");
				            System.out.println("读取文件失败");
				        }
					      catch (StringIndexOutOfBoundsException e1){
					    	  label.setText("文件错误，请选择有效ENUMDNS数据文件！");
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
//		            if(qSQL.IsConnected()!=true) qSQL.reConnect();
//		            int AS_result=qSQL.insertENUMDNS_DATA(MSISDN);
//		            if(AS_result==write) {
//		            	String str1=Integer.toString(write);
//		            	label.setText("ENUMDNS文件导入成功！共导入"+ str1	 +"行数据！");
//		            	qSQL.setConfig(3,label.getText());
//		            }else {
//		            	label.setText("ENUMDNS文件导入失败，请检查数据文件！");
//		            }
					
				}

					
				else {
					label.setText("文件错误，请选择有效HSS数据文件！");
					try {
						fops.write("Data file error,please input valid HSS data file!" .getBytes());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				}
				qSQL.setConfig(1,fd.getCurrentDirectory().toString());
			}
		});
	}
//	private void HssDataProcess(database qSql,int Indicator,String FetchStr) {
//        if(Indicator==0) {     //如果是华为DNS，进行数据处理
//        	
//        	
//        	
//        	
//        	MSISDN.add(str.substring(7,18))  ;
//        }
//        elseif(Indicator==1) {              						//如果室中兴DNS，进行数据处理
//        	MSISDN.add(str.substring(7,18)) ;
//        }	
//	}
}
//class HW_HSSDATA() {
//	String MSISDN,IMSI,IMPI,YHDZD_IND;
//	
//	
//	
//	
//}
