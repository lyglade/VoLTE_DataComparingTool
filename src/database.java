import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class database {
//	private String iHost;
//	private String iUser;
//	private String iPWD;
	private Connection iCon;
	private Statement iStmt;
	private ResultSet iRS;
	private PreparedStatement iPS;
	private String iUrl;
	
	public database() {
		initialize();
	}
	
	private void initialize() {
	    iCon = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      iUrl="jdbc:sqlite:sys.db";
	      iCon = DriverManager.getConnection(iUrl);
	      iStmt=iCon.createStatement();   //创建连接对象，是Java的一个操作数据库的重要接口
	      iRS =iCon.getMetaData().getTables(null, null, "AS_DATA", null);
	      if (!iRS.next()) {
	    	  iStmt.executeUpdate("create table AS_DATA(msisdn varchar(15))");//判断是否为空数据库文件，如果是空，则初始建立基础表
	    	  iStmt.executeUpdate("create table ENUMDNS_DATA(msisdn varchar(15))");//建立ENUMDNS表
	    	  iStmt.executeUpdate("create table HSS_DATA(HSS varchar(15),msisdn varchar(15),imsi varchar(15),impi varchar(100),cap_set varchar(4),YHDZD_ind varchar(5),YHDZD_num varchar(15))");
	    	  iStmt.executeUpdate("create table AS_ENUMDNS(msisdn varchar(15))");//建立AS_ENUMDNS表
	    	  iStmt.executeUpdate("create table IMPU(msisdn varchar(15),impu varchar(100))");//建立IMPU表
	    	  iStmt.executeUpdate("create table SIFC(msisdn varchar(15),sifc varchar(20))");//建立IMPU表
	    	  iStmt.executeUpdate("create table APP_CONFIG (ID varchar(10),CONFIG_STR varchar(300))");
	    	  iStmt.executeUpdate("INSERT INTO APP_CONFIG (\'ID\') values(\'1\')");   //APP_CONFIG表中，记录打开文件的目录位置
	    	  iStmt.executeUpdate("INSERT INTO APP_CONFIG (\'ID\') values(\'2\')");   //APP_CONFIG表中，记录AS label的内容
	    	  iStmt.executeUpdate("INSERT INTO APP_CONFIG (\'ID\') values(\'3\')");   //APP_CONFIG表中，记录ENUMDNS label的内容
	    	  iStmt.executeUpdate("INSERT INTO APP_CONFIG (\'ID\') values(\'4\')");   //APP_CONFIG表中，记录HSS label的内容
          }	      
	    } 
	    catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");		
	}
	
	public boolean IsConnected() {
		try {
			return iCon.isValid(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public void reConnect(database qSQL) {
		try {
			qSQL.iCon=DriverManager.getConnection(iUrl);
			qSQL.iStmt=iCon.createStatement();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reConnect() {
		try {
			this.iCon=DriverManager.getConnection(iUrl);
			this.iStmt=iCon.createStatement();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public int insertASDATA(ArrayList MSISDN) {
    	if(!IsConnected()) this.reConnect();
    	if(MSISDN.size()==0) return 0;
//    	String[] MS_string = (String[])MSISDN.toArray(new String[MSISDN.size()]);
    	int batchSize = 5000;
    	int i;
    	try {
    		iCon.setAutoCommit(false);
    		String iSql1="DELETE FROM AS_DATA;";
    		this.iPS=iCon.prepareStatement(iSql1);
    		iPS.executeUpdate();	
//			iSql1="insert into AS_DATA (\'msisdn\') values(?);";
//			this.iPS=iCon.prepareStatement(iSql1);
			String[] MS_string = (String[])MSISDN.toArray(new String[MSISDN.size()]); 
			for(i=0;i<MSISDN.size();i++) {
				String str2="insert into AS_DATA (\'msisdn\') values(\'"+MS_string[i]+"\');";
				iStmt.addBatch(str2);
//				iPS.setString(1, MS_string[i]);
//				iPS.addBatch();
				
			    if ((i + 1) % batchSize == 0) {
			    	iStmt.executeBatch();
			    	iCon.commit();
//			        iPS.executeBatch();
			    	System.out.println(i);
			    }
			}
			if (MSISDN.size() % batchSize != 0) {
//				iPS.executeBatch();
				iStmt.executeBatch();
				iCon.commit();
			}
			iCon.setAutoCommit(true);
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
    	
    }
    
    public int insertENUMDNS_DATA(ArrayList MSISDN) {
    	if(!IsConnected()) this.reConnect();
    	if(MSISDN.size()==0) return 0;
//    	String[] MS_string = (String[])MSISDN.toArray(new String[MSISDN.size()]);
    	int batchSize = 5000;
    	int i;
    	try {
    		iCon.setAutoCommit(false);
    		String iSql1="DELETE FROM ENUMDNS_DATA;";
    		this.iPS=iCon.prepareStatement(iSql1);
    		iPS.executeUpdate();
			iSql1="insert into ENUMDNS_DATA (\'msisdn\') values(?);";
			this.iPS=iCon.prepareStatement(iSql1);
			String[] MS_string = (String[])MSISDN.toArray(new String[MSISDN.size()]); 
			for(i=0;i<MSISDN.size();i++) {
				iPS.setString(1, MS_string[i]);
				iPS.addBatch();
			    if ((i + 1) % batchSize == 0) {
			        iPS.executeBatch();
			        iCon.commit();
			        System.out.println(i);
			    }
			}
			if (MSISDN.size() % batchSize != 0) {
				iPS.executeBatch();
				iCon.commit();
			}
			iCon.setAutoCommit(true);
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
    	
    }
    
    public int insertHSS_DATA(String MSISDN,String IMSI,String IMPI,String YHDZD_IND,String YHDZD_NUM,String MCAP, ArrayList IMPU,ArrayList sIFC) {
    	if(!IsConnected()) this.reConnect();
    	int i,j;
    	try {
    		iCon.setAutoCommit(false);
    		String iSql1="insert into HSS_DATA (\'msisdn\',\'imsi\',\'impi\',\'yhdzd_ind\',\'yhdzd_num\',\'cap_set\') values(?,?,?,?,?,?);";
    		this.iPS=iCon.prepareStatement(iSql1);
    		iPS.setString(1, MSISDN);
    		iPS.setString(2, IMSI);
    		iPS.setString(3, IMPI);
    		iPS.setString(4, YHDZD_IND);
    		iPS.setString(5, YHDZD_NUM);
    		iPS.setString(6, MCAP);
    		iPS.addBatch();
    		iPS.executeBatch();
    		
			for(i=0;i<IMPU.size();i++) {
	    		iSql1="insert into IMPU (\'msisdn\',\'impu\') values(?,?)";
	    		this.iPS=iCon.prepareStatement(iSql1);
				iPS.setString(1, MSISDN);
				iPS.setString(2, (String) IMPU.get(i));
				iPS.addBatch();
				iPS.executeBatch();
			    }
			
			for(j=0;j<sIFC.size();j++) {
	    		iSql1="insert into SIfC (\'msisdn\',\'sifc\') values(?,?)";
	    		this.iPS=iCon.prepareStatement(iSql1);
				iPS.setString(1, MSISDN);
				iPS.setString(2, (String) sIFC.get(j));
				iPS.addBatch();
				iPS.executeBatch();
			    }
//			iCon.commit();
//			iCon.setAutoCommit(true);
			if(i<=j)return i;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return 0;
		}
		return j;
    }
    
    public void DoCommit() {
    	try {
			iCon.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void CountItems() {
    	if(!IsConnected()) this.reConnect();
    	String  iSql="select * FROM AS_DATA;";
		
		try {
			iRS=iStmt.executeQuery(iSql);
			int rowCount = 0; 
			while(iRS.next()){ 
			 rowCount++; 
			} 
			
			System.out.println(rowCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
	public void GetUnionSet() {		
		try {
			if(!IsConnected()) this.reConnect();
			iStmt.executeUpdate("DELETE FROM AS_ENUMDNS;");
			iStmt.executeUpdate("INSERT INTO AS_ENUMDNS (\'msisdn\') SELECT msisdn FROM AS_DATA");
			iStmt.executeUpdate("INSERT INTO AS_ENUMDNS (\'msisdn\') SELECT msisdn FROM ENUMDNS_DATA");
//			iCon.commit();
			iRS=iStmt.executeQuery("select DISTINCT * FROM  AS_ENUMDNS;");
			int rowCount = 1; 
			System.out.println(iRS.getString(1));
			while(iRS.next()){ 
			 rowCount++;
			 if(rowCount>19570) System.out.println(iRS.getString(1));
			} 
			System.out.println(rowCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public void exportDATA() {
		FileOutputStream  fps=null;
		try {
			if(!IsConnected()) this.reConnect();
			fps=new FileOutputStream ("d:\\as_data.txt",false);
			String RSrecord=null;
			iRS= iStmt.executeQuery("SELECT msisdn FROM AS_DATA");
			System.out.println(iRS.getMetaData());
			RSrecord=iRS.getString(1);
			while(iRS.next()) {
				RSrecord=iRS.getString(1);
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			fps=new FileOutputStream ("d:\\enumdns_data.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT msisdn FROM ENUMDNS_DATA");
			System.out.println(iRS.getMetaData());
			RSrecord=iRS.getString(1);
			while(iRS.next()) {
				RSrecord=iRS.getString(1);
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();		
			
			
			fps=new FileOutputStream ("d:\\as_enumdns.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT msisdn FROM AS_ENUMDNS");
			System.out.println(iRS.getMetaData());
			RSrecord=iRS.getString(1);
			while(iRS.next()) {
				RSrecord=iRS.getString(1);
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();		
			
			fps=new FileOutputStream ("d:\\APP_CONFIG.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM APP_CONFIG");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1) + " " +iRS.getString(2);
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			fps=new FileOutputStream ("d:\\HSS_DATA.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM HSS_DATA");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1) + " " +iRS.getString(2)+ " "+ iRS.getString(3)+ " "+ iRS.getString(4)+ " "+ iRS.getString(5)+ " "+ iRS.getString(6)+ " "+ iRS.getString(7) ;
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			fps=new FileOutputStream ("d:\\IMPU.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM IMPU");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1) + " " +iRS.getString(2);
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			fps=new FileOutputStream ("d:\\SIFC.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM SIFC");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1) + " " +iRS.getString(2);
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	public 	String getConfig(int ItemNumber) {
		if(!IsConnected()) this.reConnect();
		
    	try {
    		String iSql3="SELECT * FROM APP_CONFIG WHERE ID=\'"+ Integer.toString(ItemNumber)+"\'";
    		System.out.println(iRS.isClosed());
    		iRS=iStmt.executeQuery(iSql3);
    		String result=null;
    		if(iRS.isClosed()!=true) {
    			result =iRS.getString(2);
    			System.out.println(result);
    		}
    		
    		return result; 
    	}
    	 catch(SQLException e) {
    		 System.err.print(e);
    	 }
		return null;
	}
	
	public 	void setConfig(int ItemNumber,String ConfigString) {
		if(!IsConnected()) this.reConnect();
    	try {
    		iCon.setAutoCommit(true);
    		String iSql3="UPDATE APP_CONFIG SET CONFIG_STR =\'"+ ConfigString + "\' where ID=\'"+ Integer.toString(ItemNumber)+"\'";
    		iStmt.executeUpdate(iSql3);
//    		iCon.commit();
    	}
    	 catch(SQLException e) {
    		 System.err.print(e);
    		 
    	 }
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
