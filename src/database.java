import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	      iRS =iCon.getMetaData().getTables(null, null, "APN_DATA", null);
	      if (!iRS.next()) {
	    	  iStmt.executeUpdate("create table APN_DATA(msisdn varchar(15))");//判断是否为空数据库文件，如果是空，则初始建立基础表
	    	  iStmt.executeUpdate("create table ENUMDNS_DATA(msisdn varchar(15),domain varchar(100))");//建立ENUMDNS表
	    	  iStmt.executeUpdate("create table HSS_DATA(HSS varchar(15),msisdn varchar(15),imsi varchar(15),impi varchar(100),cap_set varchar(4),YHDZD_ind varchar(5),YHDZD_num varchar(15))");
	    	  iStmt.executeUpdate("create table APN_ENUMDNS(msisdn varchar(15))");//建立APN_ENUMDNS表
	    	  iStmt.executeUpdate("create table IMPU(msisdn varchar(15),impu varchar(100))");//建立IMPU表
	    	  iStmt.executeUpdate("create table SIFC(msisdn varchar(15),sifc varchar(20))");//建立IMPU表
	    	  iStmt.executeUpdate("create table ALL_FIX(msisdn varchar(15))");//建立全匹配记录表
	    	  iStmt.executeUpdate("create table HSS_APN_FIX(msisdn varchar(15))");//建立HSS_APN匹配记录表
	    	  iStmt.executeUpdate("create table HSS_ENUMDNS_FIX(msisdn varchar(15))");//建立HSS_ENUMDNS匹配记录表
	    	  iStmt.executeUpdate("create table APN_ENUMDNS_FIX(msisdn varchar(15))");//建立APN_ENUMDNSS匹配记录表	    	  
	    	  iStmt.executeUpdate("create table HSS_ONLY(msisdn varchar(15))");//建立ENUMDNS_HSS匹配记录表
	    	  iStmt.executeUpdate("create table ENUMDNS_ONLY(msisdn varchar(15))");//建立ENUMDNS_APN匹配记录表
	    	  iStmt.executeUpdate("create table APN_ONLY(msisdn varchar(15))");//建立APN_HSS匹配记录表
	    	  iStmt.executeUpdate("create table HSS (HSS varchar(10),Manufacturer varchar(10),IP varchar(15),USER varchar(15), PASSWD varchar(15),PORT varchar(15),PROMPT varchar(15))");//建立hss表
	    	  iStmt.executeUpdate("create table City(City varchar(15),NumPrefix varchar(10),HSS varchar(10))");//建立CITY表
	    	  iStmt.executeUpdate("create table Numlist(Province varchar(15),City varchar(15),AreaCode varchar(10), Number varchar(15))");//建立numlist表	    	  
	    	  iStmt.executeUpdate("create table APP_CONFIG (ID varchar(10),CONFIG_STR varchar(300))");
	    	  iStmt.executeUpdate("INSERT INTO APP_CONFIG (\'ID\') values(\'1\')");   //APP_CONFIG表中，记录打开文件的目录位置
	    	  iStmt.executeUpdate("INSERT INTO APP_CONFIG (\'ID\') values(\'2\')");   //APP_CONFIG表中，记录APN label的内容
	    	  iStmt.executeUpdate("INSERT INTO APP_CONFIG (\'ID\') values(\'3\')");   //APP_CONFIG表中，记录ENUMDNS label的内容
	    	  iStmt.executeUpdate("INSERT INTO APP_CONFIG (\'ID\') values(\'4\')");   //APP_CONFIG表中，记录HSS label的内容
	    	  iStmt.executeUpdate("INSERT INTO APP_CONFIG (\'ID\') values(\'5\')");   //APP_CONFIG表中，记录HSS的导入数据条数
	    	  iStmt.executeUpdate("INSERT INTO APP_CONFIG (\'ID\') values(\'6\')");   //APP_CONFIG表中，记录APN的导入数据条数
	    	  iStmt.executeUpdate("INSERT INTO APP_CONFIG (\'ID\') values(\'7\')");   //APP_CONFIG表中，备用
	    	  iStmt.executeUpdate("INSERT INTO APP_CONFIG (\'ID\') values(\'8\')");   //APP_CONFIG表中，备用
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
    public int insertAPNDATA(ArrayList MSISDN) {
    	if(!IsConnected()) this.reConnect();
    	if(MSISDN.size()==0) return 0;
//    	String[] MS_string = (String[])MSISDN.toArray(new String[MSISDN.size()]);
    	int batchSize = 5000;
    	int i;
    	try {
    		iCon.setAutoCommit(false);
//    		String iSql1="DELETE FROM APN_DATA;";
//    		this.iPS=iCon.prepareStatement(iSql1);
//    		iPS.executeUpdate();	
//			iSql1="insert into APN_DATA (\'msisdn\') values(?);";
//			this.iPS=iCon.prepareStatement(iSql1);
			String[] MS_string = (String[])MSISDN.toArray(new String[MSISDN.size()]); 
			for(i=0;i<MSISDN.size();i++) {
				String str2="insert into APN_DATA (\'msisdn\') values(\'"+MS_string[i]+"\');";
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
    public void delHSS_DATA() {
    	if(!IsConnected()) this.reConnect();
		try {
			iStmt.executeUpdate("DELETE FROM HSS_DATA;");
			iStmt.executeUpdate("DELETE FROM IMPU;");
			iStmt.executeUpdate("DELETE FROM SIFC;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			if(i==0&&j==0) return 1;
			else if(i<j) return i;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return 0;
		}
		return j;
    }
    
    public void insertCity(List<Map<String,String>> list) throws SQLException {
    	if(!IsConnected()) this.reConnect();
		iStmt.executeUpdate("DELETE FROM City;");
		for (int i = 0; i < list.size(); i++) {
			Map map=list.get(i);
    		iCon.setAutoCommit(false);
    		String iSql1="insert into City (\'City\',\'NumPrefix\',\'HSS\') values(?,?,?);";
    		this.iPS=iCon.prepareStatement(iSql1);
    		iPS.setString(1, map.get("City").toString());
    		iPS.setString(2, map.get("NumPrefix").toString());
    		iPS.setString(3, map.get("HSS").toString());
    		iPS.addBatch();
    		iPS.executeBatch();
		 }
    	
    }
    
    public void insertHSS(List<Map<String,String>> list) throws SQLException {
    	if(!IsConnected()) this.reConnect();
		iStmt.executeUpdate("DELETE FROM HSS;");
		for (int i = 0; i < list.size(); i++) {
			Map map=list.get(i);
    		iCon.setAutoCommit(false);
    		String iSql1="insert into HSS (\'HSS\',\'Manufacturer\',\'IP\',\'USER\',\'PASSWD\',\'PORT\',\'PROMPT\') values(?,?,?,?,?,?,?);";
    		this.iPS=iCon.prepareStatement(iSql1);
    		iPS.setString(1, map.get("HSS").toString());
    		iPS.setString(2, map.get("Manufacturer").toString());
    		iPS.setString(3, map.get("IP").toString());
    		iPS.setString(4, map.get("USER").toString());
    		iPS.setString(5, map.get("PASSWD").toString());
    		iPS.setString(6, map.get("PORT").toString());
    		iPS.setString(7, map.get("PROMPT").toString());
    		iPS.addBatch();
    		iPS.executeBatch();
		 }
    	
    }
    
    public void insertNumlist(List<Map<String,String>> list) throws SQLException {
    	if(!IsConnected()) this.reConnect();
		iStmt.executeUpdate("DELETE FROM Numlist;");
		for (int i = 0; i < list.size(); i++) {
			Map map=list.get(i);
    		iCon.setAutoCommit(false);
    		String iSql1="insert into Numlist (\'Province\',\'City\',\'AreaCode\',\'Number\') values(?,?,?,?);";
    		this.iPS=iCon.prepareStatement(iSql1);
    		iPS.setString(1, map.get("Province").toString());
    		iPS.setString(2, map.get("City").toString());
    		iPS.setString(3, map.get("AreaCode").toString());
    		iPS.setString(4, map.get("Number").toString());
    		iPS.addBatch();
    		iPS.executeBatch();
		 }
    	
    }
    
    public void DoCommit() {
    	try {
			iCon.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public int CountItems(String TableName) {
    	if(!IsConnected()) this.reConnect();
    	String  iSql="select * FROM "+TableName;
		try {
			iRS=iStmt.executeQuery(iSql);
			int rowCount = 0; 
			while(iRS.next()){ 
			 rowCount++; 
			} 
			
			System.out.println(rowCount);
			return rowCount;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
    }
    
	public void GetUnionSet() {		
		try {
			if(!IsConnected()) this.reConnect();
			iStmt.executeUpdate("DELETE FROM APN_ENUMDNS;");
			iStmt.executeUpdate("INSERT INTO APN_ENUMDNS (\'msisdn\') SELECT msisdn FROM APN_DATA");
			iStmt.executeUpdate("INSERT INTO APN_ENUMDNS (\'msisdn\') SELECT msisdn FROM ENUMDNS_DATA");
//			iCon.commit();
			iRS=iStmt.executeQuery("select DISTINCT * FROM  APN_ENUMDNS;");
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
			fps=new FileOutputStream ("d:\\APN_data.txt",false);
			String RSrecord=null;
			iRS= iStmt.executeQuery("SELECT msisdn FROM APN_DATA");
			System.out.println(iRS.getMetaData());
//			if(iRS.isAfterLast()!=true)RSrecord=iRS.getString(1);
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
//			if(iRS.isAfterLast()!=true)RSrecord=iRS.getString(1);
			while(iRS.next()) {
				RSrecord=iRS.getString(1);
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();		
			
			
			fps=new FileOutputStream ("d:\\APN_enumdns.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT msisdn FROM APN_ENUMDNS");
			System.out.println(iRS.getMetaData());
//			if(iRS.isAfterLast()!=true)RSrecord=iRS.getString(1);
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
			
			fps=new FileOutputStream ("d:\\ALL_FIX.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM ALL_FIX");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1);
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			fps=new FileOutputStream ("d:\\HSS_APN_FIX.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM HSS_APN_FIX");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1);
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			fps=new FileOutputStream ("d:\\HSS_ENUMDNS_FIX.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM HSS_ENUMDNS_FIX");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1);
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			fps=new FileOutputStream ("d:\\APN_ENUMDNS_FIX.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM APN_ENUMDNS_FIX");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1) ;
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			fps=new FileOutputStream ("d:\\HSS_ONLY.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM HSS_ONLY");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1) ;
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			fps=new FileOutputStream ("d:\\APN_ONLY.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM APN_ONLY");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1) ;
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			fps=new FileOutputStream ("d:\\ENUMDNS_ONLY.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM ENUMDNS_ONLY");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1) ;
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			fps=new FileOutputStream ("d:\\City.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM City");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1) + " " +iRS.getString(2) + " " +iRS.getString(3) ;
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			
			fps=new FileOutputStream ("d:\\HSS.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM HSS");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1) + " " +iRS.getString(2) + " " +iRS.getString(3)  + " "+iRS.getString(4) + " " +iRS.getString(5) + " " +iRS.getString(6)  + " "+iRS.getString(7) ;
				fps.write(RSrecord.getBytes());
				fps.write("\r\n".getBytes());
			}
			fps.close();
			
			fps=new FileOutputStream ("d:\\Numlist.txt",false);
			RSrecord=null;
			iRS= iStmt.executeQuery("SELECT * FROM Numlist");
			System.out.println(iRS.getMetaData());
			while(iRS.next()) {
				RSrecord=iRS.getString(1) + " " +iRS.getString(2) + " " +iRS.getString(3)  + " "+iRS.getString(4) ;
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
    		if(ItemNumber==5&&result==null) result="0";
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
	public void Data_Analyze() {
		if(!IsConnected()) this.reConnect();
		GetUnionSet();
		try {
			String iSql1="DELETE FROM ALL_FIX;DELETE FROM HSS_APN_FIX;DELETE FROM HSS_ENUMDNS_FIX;DELETE FROM APN_ENUMDNS_FIX;DELETE FROM HSS_ONLY;DELETE FROM APN_ONLY;DELETE FROM ENUMDNS_ONLY" ;
			iStmt.executeUpdate(iSql1);
			
//			iSql1="DELETE FROM APN_FIX" ;
//			iStmt.executeUpdate(iSql1);			
//			DELETE FROM APN_FIX
			iSql1="INSERT INTO ALL_FIX (msisdn) SELECT  HSS_DATA.msisdn " + 
					"FROM HSS_DATA INNER JOIN (APN_DATA INNER JOIN ENUMDNS_DATA ON APN_DATA.msisdn = ENUMDNS_DATA.msisdn) ON HSS_DATA.\'msisdn\' = ENUMDNS_DATA.\'msisdn\'";
			iStmt.executeUpdate(iSql1);
			
//			iSql1="INSERT INTO ALL_FIX (msisdn) SELECT  HSS_DATA.msisdn " + 
//					"FROM HSS_DATA WHERE HSS_DATA.msisdn IN (SELECT APN_DATA.msisdn FROM APN_DATA) AND HSS_DATA.msisdn IN (SELECT ENUMDNS_DATA.msisdn FROM ENUMDNS_DATA)";
//			iStmt.executeUpdate(iSql1);
					
			iSql1="INSERT INTO HSS_APN_FIX (msisdn) SELECT  HSS_DATA.msisdn " + 
					"FROM HSS_DATA WHERE HSS_DATA.msisdn IN  (SELECT APN_DATA.msisdn  FROM APN_DATA WHERE APN_DATA.msisdn NOT IN (SELECT ALL_FIX.msisdn FROM ALL_FIX))";
			iStmt.executeUpdate(iSql1);			
			
			iSql1="INSERT INTO HSS_ENUMDNS_FIX (msisdn) SELECT  HSS_DATA.msisdn " + 
					"FROM HSS_DATA WHERE HSS_DATA.msisdn IN  (SELECT ENUMDNS_DATA.msisdn  FROM ENUMDNS_DATA WHERE ENUMDNS_DATA.msisdn NOT IN (SELECT ALL_FIX.msisdn FROM ALL_FIX))";
			iStmt.executeUpdate(iSql1);	
			
			iSql1="INSERT INTO APN_ENUMDNS_FIX (msisdn) SELECT  APN_DATA.msisdn " + 
					"FROM APN_DATA WHERE APN_DATA.msisdn IN  (SELECT ENUMDNS_DATA.msisdn  FROM ENUMDNS_DATA WHERE ENUMDNS_DATA.msisdn NOT IN (SELECT ALL_FIX.msisdn FROM ALL_FIX))";
			iStmt.executeUpdate(iSql1);				
			
			iSql1="INSERT INTO HSS_ONLY (msisdn) SELECT  HSS_DATA.msisdn " + 
					"FROM HSS_DATA WHERE HSS_DATA.msisdn NOT IN  (SELECT ALL_FIX.msisdn  FROM ALL_FIX) AND HSS_DATA.msisdn NOT IN  (SELECT HSS_APN_FIX.msisdn  FROM HSS_APN_FIX) AND HSS_DATA.msisdn NOT IN  (SELECT HSS_ENUMDNS_FIX.msisdn  FROM HSS_ENUMDNS_FIX)";
			iStmt.executeUpdate(iSql1);	
			
			iSql1="INSERT INTO APN_ONLY (msisdn) SELECT  APN_DATA.msisdn " + 
					"FROM APN_DATA WHERE APN_DATA.msisdn NOT IN  (SELECT ALL_FIX.msisdn  FROM ALL_FIX) AND APN_DATA.msisdn NOT IN  (SELECT HSS_APN_FIX.msisdn  FROM HSS_APN_FIX) AND APN_DATA.msisdn NOT IN  (SELECT APN_ENUMDNS_FIX.msisdn  FROM APN_ENUMDNS_FIX)";
			iStmt.executeUpdate(iSql1);	
			
			iSql1="INSERT INTO ENUMDNS_ONLY (msisdn) SELECT  ENUMDNS_DATA.msisdn " + 
					"FROM ENUMDNS_DATA WHERE ENUMDNS_DATA.msisdn NOT IN  (SELECT ALL_FIX.msisdn  FROM ALL_FIX) AND ENUMDNS_DATA.msisdn NOT IN  (SELECT APN_ENUMDNS_FIX.msisdn  FROM APN_ENUMDNS_FIX) AND ENUMDNS_DATA.msisdn NOT IN  (SELECT HSS_ENUMDNS_FIX.msisdn  FROM HSS_ENUMDNS_FIX)";
			iStmt.executeUpdate(iSql1);	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public 	String[] Analyze_Count() {
		if(!IsConnected()) this.reConnect();
		String[] result=new String[10];
    	try {
    		String iSql="SELECT COUNT(msisdn) FROM HSS_DATA ";
    		iRS=iStmt.executeQuery(iSql);
    		result[0] =iRS.getString(1);
    		
    		iSql="SELECT COUNT(msisdn) FROM APN_DATA ";
    		iRS=iStmt.executeQuery(iSql);
    		result[1] =iRS.getString(1);
    		
    		iSql="SELECT COUNT(msisdn) FROM ENUMDNS_DATA ";
    		iRS=iStmt.executeQuery(iSql);
    		result[2] =iRS.getString(1);
    		
    		iSql="SELECT COUNT(msisdn) FROM ALL_FIX ";
    		iRS=iStmt.executeQuery(iSql);
    		result[3] =iRS.getString(1);
    		
    		iSql="SELECT COUNT(msisdn) FROM HSS_APN_FIX ";
    		iRS=iStmt.executeQuery(iSql);
    		result[4] =iRS.getString(1);
    		
    		iSql="SELECT COUNT(msisdn) FROM HSS_ENUMDNS_FIX ";
    		iRS=iStmt.executeQuery(iSql);
    		result[5] =iRS.getString(1);
    		
    		iSql="SELECT COUNT(msisdn) FROM APN_ENUMDNS_FIX ";
    		iRS=iStmt.executeQuery(iSql);
    		result[6] =iRS.getString(1);
    		
    		iSql="SELECT COUNT(msisdn) FROM HSS_ONLY ";
    		iRS=iStmt.executeQuery(iSql);
    		result[7] =iRS.getString(1);
    		
    		iSql="SELECT COUNT(msisdn) FROM APN_ONLY ";
    		iRS=iStmt.executeQuery(iSql);
    		result[8] =iRS.getString(1);
    		
    		iSql="SELECT COUNT(msisdn) FROM ENUMDNS_ONLY ";
    		iRS=iStmt.executeQuery(iSql);
    		result[9] =iRS.getString(1);
    		return result; 
    	}
    	 catch(SQLException e) {
    		 System.err.print(e);
    		 return null;
    	 }
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
