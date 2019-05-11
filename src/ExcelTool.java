
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
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
	        writeExcel(list, 3, "D:/writeExcel.xlsx");


	}
	 public static void writeExcel(List<Map> dataList, int cloumnCount,String finalXlsxPath){
	        OutputStream out = null;
	        try {
	            // 获取总列数
	            int columnNumCount = cloumnCount;
	            // 读取Excel文档
	            File finalXlsxFile = new File(finalXlsxPath);
	            Workbook workBook = getWorkbok(finalXlsxFile);
	            // sheet 对应一个工作页
	            Sheet sheet = workBook.getSheetAt(0);
	            /**
	             * 删除原有数据，除了属性列
	             */
	            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
	            System.out.println("原始数据总行数，除属性列：" + rowNumber);
	            for (int i = 1; i <= rowNumber; i++) {
	                Row row = sheet.getRow(i);
	                sheet.removeRow(row);
	            }
	            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
	            out =  new FileOutputStream(finalXlsxPath);
	            workBook.write(out);
	            /**
	             * 往Excel中写新数据
	             */
	            for (int j = 0; j < dataList.size(); j++) {
	                // 创建一行：从第二行开始，跳过属性列
	                Row row = sheet.createRow(j + 1);
	                // 得到要插入的每一条记录
	                Map dataMap = dataList.get(j);
	                String name = dataMap.get("BankName").toString();
	                String address = dataMap.get("Addr").toString();
	                String phone = dataMap.get("Phone").toString();
	                for (int k = 0; k <= columnNumCount; k++) {
	                // 在一行内循环
	                Cell first = row.createCell(0);
	                first.setCellValue(name);
	        
	                Cell second = row.createCell(1);
	                second.setCellValue(address);
	        
	                Cell third = row.createCell(2);
	                third.setCellValue(phone);
	                }
	            }
	            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
	            out =  new FileOutputStream(finalXlsxPath);
	            workBook.write(out);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally{
	            try {
	                if(out != null){
	                    out.flush();
	                    out.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        System.out.println("数据导出成功");
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
	    public static List<Map<String,String>> readExcel(File file,int ReadInd)throws IOException,FileNotFoundException  {
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
	            	return null;
	            }


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
