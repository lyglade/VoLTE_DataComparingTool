
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.common.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.xmlbeans.*;

//import jxl.read.biff.BiffException;

import java.util.ArrayList;
import java.util.List;

public class ExcelTool {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	      Map<String, String> dataMap=new HashMap<String, String>();
	        dataMap.put("BankName", "BankName");
	        dataMap.put("Addr", "Addr");
	        dataMap.put("Phone", "Phone");
	        List<Map> list=new ArrayList<Map>();
	        list.add(dataMap);
//	        writeExcel(list, 3, "D:/writeExcel.xlsx");

	}
	
	 public static void writeExcel(List<Map<String,String>> list, int WriteInd , File file) throws FileNotFoundException, IOException{
        //创建workbook
		 Workbook XWorkbook=null;
		 FileInputStream fileInputStream=null;
		 boolean FileInputInd=false;
		 if(file.length()==0) {
			 XWorkbook = new XSSFWorkbook(); 
		 }else {
			 fileInputStream=new FileInputStream(file);
			 XWorkbook = new XSSFWorkbook(fileInputStream);  //如果文件不为空，则读入文件
			 FileInputInd=true;
		 }
		 
        //新建文件
        FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
        if(WriteInd==1) {
            XWorkbook.createSheet("City");
            Sheet sheet=XWorkbook.getSheet("City");
            Row row = sheet.createRow(0);
            Cell cell1=row.createCell(0);
            cell1.setCellValue("City");
            Cell cell2=row.createCell(1);
            cell2.setCellValue("NumPrefix");
            Cell cell3=row.createCell(2);
            cell3.setCellValue("HSS");
            
            for (int i= 0; i < list.size(); i++) {
                // 创建一行：从第二行开始，跳过属性列
                row = sheet.createRow(i + 1);
                Map<String,String>  dataMap = list.get(i);
                cell1 = row.createCell(0);
                cell1.setCellValue(dataMap.get("City").toString());
        
                cell2 = row.createCell(1);
                cell2.setCellValue(dataMap.get("NumPrefix").toString());
        
                cell3 = row.createCell(2);
                cell3.setCellValue(dataMap.get("HSS").toString());
            }
        }else if(WriteInd==2) {
            XWorkbook.createSheet("HSS");
            Sheet sheet=XWorkbook.getSheet("HSS");
            Row row = sheet.createRow(0);
            Cell cell1=row.createCell(0);
            cell1.setCellValue("HSS");
            Cell cell2=row.createCell(1);
            cell2.setCellValue("Manufacturer");
            Cell cell3=row.createCell(2);
            cell3.setCellValue("IP");
            Cell cell4=row.createCell(3);
            cell4.setCellValue("USER");
            Cell cell5=row.createCell(4);
            cell5.setCellValue("PASSWD");
            Cell cell6=row.createCell(5);
            cell6.setCellValue("PORT");
            Cell cell7=row.createCell(6);
            cell7.setCellValue("PROMPT");
            for (int i= 0; i < list.size(); i++) {
                // 创建一行：从第二行开始，跳过属性列
                row = sheet.createRow(i + 1);
                Map<String,String> dataMap = list.get(i);
                cell1 = row.createCell(0);
                cell1.setCellValue(dataMap.get("HSS").toString());
                cell2 = row.createCell(1);
                cell2.setCellValue(dataMap.get("Manufacturer").toString());
                cell3 = row.createCell(2);
                cell3.setCellValue(dataMap.get("IP").toString());
                cell4= row.createCell(3);
                cell4.setCellValue(dataMap.get("USER").toString());
                cell5 = row.createCell(4);
                cell5.setCellValue(dataMap.get("PASSWD").toString());
                cell6 = row.createCell(5);
                cell6.setCellValue(dataMap.get("PORT").toString());
                cell7 = row.createCell(6);
                cell7.setCellValue(dataMap.get("PROMPT").toString());
            }	
        }else {
            XWorkbook.createSheet("Numlist");
            Sheet sheet=XWorkbook.getSheet("Numlist");
            Row row = sheet.createRow(0);
            Cell cell1=row.createCell(0);
            cell1.setCellValue("Province");
            Cell cell2=row.createCell(1);
            cell2.setCellValue("City");
            Cell cell3=row.createCell(2);
            cell3.setCellValue("Areacode");
            Cell cell4=row.createCell(3);
            cell4.setCellValue("Number");
            
            for (int i= 0; i < list.size(); i++) {
                // 创建一行：从第二行开始，跳过属性列
                row = sheet.createRow(i + 1);
                Map<String,String>  dataMap = list.get(i);
                cell1 = row.createCell(0);
                cell1.setCellValue(dataMap.get("Province").toString());
                cell2 = row.createCell(1);
                cell2.setCellValue(dataMap.get("City").toString());
                cell3 = row.createCell(2);
                cell3.setCellValue(dataMap.get("AreaCode").toString());
                cell4= row.createCell(3);
                cell4.setCellValue(dataMap.get("Number").toString());
            }
        }
        	
        XWorkbook.write(fileOutputStream);
        XWorkbook.close();
        fileOutputStream.flush();
        fileOutputStream.close();
        if(FileInputInd==true) fileInputStream.close();
        
	    }

	    /**
	     * 判断Excel的版本,获取Workbook
	     * @param in
	     * @param filename
	     * @return
	     * @throws IOException
	     */
	    public static Workbook getWorkbok(File file) throws IOException{
	        Workbook wb = null;
	        FileInputStream in = new FileInputStream(file);
	        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
	            wb = new HSSFWorkbook(in);
	        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
	            wb = new XSSFWorkbook(in);
	        }
	        return wb;
	    }
	    
	    
	  //读取excel
	    public static List<Map<String,String>> readExcel(File file,int ReadInd)throws IOException,FileNotFoundException {
	    	String filePath=file.getAbsolutePath();
	        Workbook wb = null;
	        Sheet sheet = null;
	        Row row = null;
	        List<Map<String,String>> list = null;
	        String cellData=null;
	        if(filePath!=null){
		        String extString = filePath.substring(filePath.lastIndexOf("."));
		        InputStream is = null;

	            is = new FileInputStream(filePath);
	            if(".xls".equals(extString)){
	                 wb = new HSSFWorkbook(is);
	            }else if(".xlsx".equals(extString)){
	                 wb = new XSSFWorkbook(is);
	            }else{
	                 wb = null;
	            }
	           

	          //用来存放表中数据
	            list = new ArrayList<Map<String,String>>();
	            
	            if(ReadInd==1) {
		          //获取 City sheet
		            sheet=wb.getSheet("City");
		            if(sheet==null) {
		            	is.close();
		            	return null;
		            }
		            //获取最大行数
		            int rownum = sheet.getPhysicalNumberOfRows();
		            //获取第一行
		            row = sheet.getRow(0);
		            //获取最大列数
		            int colnum = row.getPhysicalNumberOfCells();
		            String columns[] = {"City","NumPrefix","HSS"};
			          //从第二行开始，因为第一行是标题
		            for (int i = 1; i<rownum; i++) {
		                Map<String,String> map = new HashMap<String,String>();
		                row = sheet.getRow(i);
		                if(row !=null){
		                    for (int j=0;j<colnum;j++){
		                        cellData = (String) getCellFormatValue(row.getCell(j));
								map.put(columns[j], cellData);
		                    }
		                }else{
		                    break;
		                }
		                list.add(map);
		            }
	            }else if(ReadInd==2) {
	  	          //获取 HSS sheet
		            sheet=wb.getSheet("HSS");
		            if(sheet==null) {
		            	is.close();
		            	return null;
		            }
		            //获取最大行数
		            int rownum = sheet.getPhysicalNumberOfRows();
		            //获取第一行
		            row = sheet.getRow(0);
		            //获取最大列数
		            int colnum = row.getPhysicalNumberOfCells();
		            String columns[] = {"HSS","Manufacturer","IP","USER","PASSWD","PORT","PROMPT"};
			          //从第二行开始，因为第一行是标题
		            for (int i = 1; i<rownum; i++) {
		                Map<String,String> map = new HashMap<String,String>();
		                row = sheet.getRow(i);
		                if(row !=null){
		                    for (int j=0;j<colnum;j++){
		                        cellData = (String) getCellFormatValue(row.getCell(j));
								map.put(columns[j], cellData);
		                    }
		                }else{
		                    break;
		                }
		                list.add(map);
		            }
	            }else if(ReadInd==3){
	  	          //获取 City sheet
		            sheet=wb.getSheet("Numlist");
		            if(sheet==null) {
		            	is.close();
		            	return null;
		            }
		            //获取最大行数
		            int rownum = sheet.getPhysicalNumberOfRows();
		            //获取第一行
		            row = sheet.getRow(0);
		            //获取最大列数
		            int colnum = row.getPhysicalNumberOfCells();
		            String columns[] = {"Province","City","AreaCode","Number"};	
			          //从第二行开始，因为第一行是标题
		            for (int i = 1; i<rownum; i++) {
		                Map<String,String> map = new HashMap<String,String>();
		                row = sheet.getRow(i);
		                if(row !=null){
		                    for (int j=0;j<colnum;j++){
		                        cellData = (String) getCellFormatValue(row.getCell(j));
								map.put(columns[j], cellData);
		                    }
		                }else{
		                    break;
		                }
		                list.add(map);
		            }
	            }else {
	            	is.close();
	            	return null;
	            }

	            is.close();
	        }
			return list;
	    }
	    public static Object getCellFormatValue(Cell cell){
	        Object cellValue = null;
	        if(cell!=null){
	            //判断cell类型
	            switch(cell.getCellType()){
	            case NUMERIC:{
//	                cellValue = String.valueOf(cell.getNumericCellValue());
	                DecimalFormat df=new DecimalFormat();  //利用DecimalFormat类，实现数字规整
	                df.setMaximumFractionDigits(0);    //设置小数位数为0
	                df.setGroupingSize(0);
	                cellValue=df.format(cell.getNumericCellValue());
	                break;
	            }
	            case FORMULA:{
	                //判断cell是否为日期格式
	                if(DateUtil.isCellDateFormatted(cell)){
	                    //转换为日期格式YYYY-mm-dd
	                    cellValue = cell.getDateCellValue();
	                }else{
	                    //数字
	                    cellValue = String.valueOf(cell.getNumericCellValue());
	                }
	                break;
	            }
	            case STRING:{
	                cellValue = cell.getRichStringCellValue().getString();
	                break;
	            }
	            default:
	                cellValue = "";
	            }
	        }else{
	            cellValue = "";
	        }
	        return cellValue;
	    }


}
