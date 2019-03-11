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
	    	  iStmt.executeUpdate("create table HSS_DATA(HSS varchar(15),msisdn varchar(15),imsi varchar(15),impi varchar(50),cap_set varchar(4),YHDZD_ind varchar(5),YHDZD_num varchar(15))");//判断是否有表tables的存在。有则删除
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
				String str2="insert into AS_DATA (\'msisdn\') values(\'"+MS_string+"\');";
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
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
    	
    }
    
    public int insertENUMDNS_DATA(ArrayList MSISDN) {
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
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
