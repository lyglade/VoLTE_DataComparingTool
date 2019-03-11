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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.awt.Color;

public class MainBoard {

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
		Properties p=new Properties();
		database qSQL=new database();
		frame = new JFrame();
		frame.setBounds(100, 100, 528, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String[] JCBList1 = { "��Ϊ", "����", "������" ,"ŵ����","����"};
		JComboBox comboBox = new JComboBox(JCBList1);
		comboBox.setBounds(31, 172, 79, 23);		
		frame.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("\u5BFC\u5165HSS\u6570\u636E");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fd = new JFileChooser();
				//fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fd.setMultiSelectionEnabled(true);
				fd.showOpenDialog(null);				
				File f[] = fd.getSelectedFiles();
				if(f != null){
					
					
				}
			}
		});
		btnNewButton.setBounds(120, 172, 113, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblHss = new JLabel("HSS:");
		lblHss.setFont(new Font("΢���ź�", Font.BOLD, 17));
		lblHss.setBounds(31, 147, 54, 15);
		frame.getContentPane().add(lblHss);
		
		JButton btnNewButton_1 = new JButton("\u793A\u4F8B\u6587\u4EF6");
		btnNewButton_1.setBounds(336, 172, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 68, 492, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblAs = new JLabel("AS:");
		lblAs.setFont(new Font("΢���ź�", Font.BOLD, 17));
		lblAs.setBounds(31, 10, 54, 15);
		frame.getContentPane().add(lblAs);
		
		String[] JCBList2 = { "��Ϊ", "����"};
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
		lblEnumdns.setFont(new Font("΢���ź�", Font.BOLD, 17));
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
		lblNewLabel.setFont(new Font("΢���ź�", Font.BOLD, 12));
		lblNewLabel.setBounds(138, 12, 364, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("\u5C1A\u672A\u5BFC\u5165\u6570\u636E");
		label.setForeground(Color.RED);
		label.setFont(new Font("΢���ź�", Font.BOLD, 12));
		label.setBounds(138, 82, 364, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5C1A\u672A\u5BFC\u5165\u6570\u636E");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("΢���ź�", Font.BOLD, 12));
		label_1.setBounds(138, 149, 364, 15);
		frame.getContentPane().add(label_1);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList MSISDN=new ArrayList();
				JFileChooser fd = new JFileChooser();
				if(fd.showOpenDialog(null)==fd.APPROVE_OPTION) {
				File f = fd.getSelectedFile();
				if(f != null){
			        int i = 0;
			        int write = 0;
				    FileInputStream fis = null;
				    InputStreamReader isr = null;
					    BufferedReader br = null; // ���ڰ�װInputStreamReader,��ߴ������ܡ���ΪBufferedReader�л���ģ���InputStreamReaderû�С�
					        try {
					            String str = "";
					            fis = new FileInputStream(f);// FileInputStream
					            
					            // ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
					            isr = new InputStreamReader(fis);// InputStreamReader ���ֽ���ͨ���ַ���������,
					            br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new
					                                            // InputStreamReader�Ķ���
					            while ((str = br.readLine()) != null) {
					                if(comboBox_1.getSelectedIndex()==0) {     //����ǻ�ΪAS���������ݴ���
					                	MSISDN.add(str.substring(7,18))  ;
					                }
					                else {              						//���������AS���������ݴ���
					                	MSISDN.add(str.substring(7,18)) ;
					                }
									write++;
									if(write%100==0) System.out.println("write="+write);
					            }
					            if(qSQL.IsConnected()!=true) qSQL.reConnect();
					            int AS_result=qSQL.insertASDATA(MSISDN);
					            if(AS_result==write) {
					            	String str1=Integer.toString(write);
					            	lblNewLabel.setText("AS�ļ�����ɹ���������"+ str1	 +"�����ݣ�");
					            }else {
					            	lblNewLabel.setText("AS�ļ�����ʧ�ܣ����������ļ���");
					            }
					            	
				            
				        } catch (FileNotFoundException e1) {
				        	lblNewLabel.setText("�Ҳ���ָ���ļ���");
				            System.out.println("�Ҳ���ָ���ļ�");
				        } catch (IOException e1) {
				        	lblNewLabel.setText("�ļ�������ѡ����ЧAS�����ļ���");
				            System.out.println("��ȡ�ļ�ʧ��");
				        }
					        catch (StringIndexOutOfBoundsException e1){
					        lblNewLabel.setText("�ļ�������ѡ����ЧAS�����ļ���");
				        	System.out.println("�ļ���ʽ����");
				        }
					        finally {
				        }
				            try {
				                br.close();
				                isr.close();
				                fis.close();
				                // �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
				            } catch (IOException e1) {
				                e1.printStackTrace();
				            }
				        }
				    

					
				else {
					lblNewLabel.setText("�ļ�������ѡ����ЧAS�����ļ���");
				}
				
				}
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList MSISDN=new ArrayList();
				JFileChooser fd = new JFileChooser();
				if(fd.showOpenDialog(null)==fd.APPROVE_OPTION) {
				File f = fd.getSelectedFile();
				if(f != null){
			        int i = 0;
			        int write = 0;
				    FileInputStream fis = null;
				    InputStreamReader isr = null;
					    BufferedReader br = null; // ���ڰ�װInputStreamReader,��ߴ������ܡ���ΪBufferedReader�л���ģ���InputStreamReaderû�С�
					        try {
					            String str = "";
					            fis = new FileInputStream(f);// FileInputStream
					            
					            // ���ļ�ϵͳ�е�ĳ���ļ��л�ȡ�ֽ�
					            isr = new InputStreamReader(fis);// InputStreamReader ���ֽ���ͨ���ַ���������,
					            br = new BufferedReader(isr);// ���ַ��������ж�ȡ�ļ��е�����,��װ��һ��new
					                                            // InputStreamReader�Ķ���
					            while ((str = br.readLine()) != null) {
					                if(comboBox_1.getSelectedIndex()==0) {     //����ǻ�ΪDNS���������ݴ���
					                	MSISDN.add(str.substring(7,18))  ;
					                }
					                else {              						//���������DNS���������ݴ���
					                	MSISDN.add(str.substring(7,18)) ;
					                }
									write++;
									if(write%100==0) System.out.println("write="+write);
					            }
					            if(qSQL.IsConnected()!=true) qSQL.reConnect();
					            int AS_result=qSQL.insertENUMDNS_DATA(MSISDN);
					            if(AS_result==write) {
					            	String str1=Integer.toString(write);
					            	label.setText("ENUMDNS�ļ�����ɹ���������"+ str1	 +"�����ݣ�");
					            }else {
					            	label.setText("ENUMDNS�ļ�����ʧ�ܣ����������ļ���");
					            }
					            	
				            
				        } catch (FileNotFoundException e1) {
				        	label.setText("�Ҳ���ָ���ļ���");
				            System.out.println("�Ҳ���ָ���ļ�");
				        } catch (IOException e1) {
				        	label.setText("�ļ�������ѡ����ЧENUMDNS�����ļ���");
				            System.out.println("��ȡ�ļ�ʧ��");
				        }
					      catch (StringIndexOutOfBoundsException e1){
					    	  label.setText("�ļ�������ѡ����ЧENUMDNS�����ļ���");
					    	  System.out.println("�ļ���ʽ����");
				        }
					        finally {
				        }
				            try {
				                br.close();
				                isr.close();
				                fis.close();
				                // �رյ�ʱ����ð����Ⱥ�˳��ر���󿪵��ȹر������ȹ�s,�ٹ�n,����m
				            } catch (IOException e1) {
				                e1.printStackTrace();
				            }
				        }
				    

					
				else {
					label.setText("�ļ�������ѡ����ЧENUMDNS�����ļ���");
				}
				
				}
			}
		});
	}
	
}
