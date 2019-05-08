
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	            // ��ȡ������
	            int columnNumCount = cloumnCount;
	            // ��ȡExcel�ĵ�
	            File finalXlsxFile = new File(finalXlsxPath);
	            Workbook workBook = getWorkbok(finalXlsxFile);
	            // sheet ��Ӧһ������ҳ
	            Sheet sheet = workBook.getSheetAt(0);
	            /**
	             * ɾ��ԭ�����ݣ�����������
	             */
	            int rowNumber = sheet.getLastRowNum();    // ��һ�д�0��ʼ��
	            System.out.println("ԭʼ�������������������У�" + rowNumber);
	            for (int i = 1; i <= rowNumber; i++) {
	                Row row = sheet.getRow(i);
	                sheet.removeRow(row);
	            }
	            // �����ļ��������������ӱ����������У���������sheet�������κβ�����������Ч
	            out =  new FileOutputStream(finalXlsxPath);
	            workBook.write(out);
	            /**
	             * ��Excel��д������
	             */
	            for (int j = 0; j < dataList.size(); j++) {
	                // ����һ�У��ӵڶ��п�ʼ������������
	                Row row = sheet.createRow(j + 1);
	                // �õ�Ҫ�����ÿһ����¼
	                Map dataMap = dataList.get(j);
	                String name = dataMap.get("BankName").toString();
	                String address = dataMap.get("Addr").toString();
	                String phone = dataMap.get("Phone").toString();
	                for (int k = 0; k <= columnNumCount; k++) {
	                // ��һ����ѭ��
	                Cell first = row.createCell(0);
	                first.setCellValue(name);
	        
	                Cell second = row.createCell(1);
	                second.setCellValue(address);
	        
	                Cell third = row.createCell(2);
	                third.setCellValue(phone);
	                }
	            }
	            // �����ļ��������׼��������ӱ����������У���������sheet�������κβ�����������Ч
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
	        System.out.println("���ݵ����ɹ�");
	    }

	    /**
	     * �ж�Excel�İ汾,��ȡWorkbook
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
	    
	    
	  //��ȡexcel
	    public static Workbook readExcel(String filePath){
	        Workbook wb = null;
	        if(filePath==null){
	            return null;
	        }
	        String extString = filePath.substring(filePath.lastIndexOf("."));
	        InputStream is = null;
	        try {
	            is = new FileInputStream(filePath);
	            if(".xls".equals(extString)){
	                return wb = new HSSFWorkbook(is);
	            }else if(".xlsx".equals(extString)){
	                return wb = new XSSFWorkbook(is);
	            }else{
	                return wb = null;
	            }
	            
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return wb;
	    }
	    public static Object getCellFormatValue(Cell cell){
	        Object cellValue = null;
	        if(cell!=null){
	            //�ж�cell����
	            switch(cell.getCellType()){
	            case NUMERIC:{
	                cellValue = String.valueOf(cell.getNumericCellValue());
	                break;
	            }
	            case FORMULA:{
	                //�ж�cell�Ƿ�Ϊ���ڸ�ʽ
	                if(DateUtil.isCellDateFormatted(cell)){
	                    //ת��Ϊ���ڸ�ʽYYYY-mm-dd
	                    cellValue = cell.getDateCellValue();
	                }else{
	                    //����
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
