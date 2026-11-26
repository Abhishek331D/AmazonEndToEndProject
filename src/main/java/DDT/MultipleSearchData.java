//package DDT;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//import org.apache.poi.EncryptedDocumentException;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//public class MultipleSearchData {
//	@DataProvider
//	public Object SearchItems() throws EncryptedDocumentException, IOException {
//		Object[][]data=new Object [4][1] ;//row*column
//		FileInputStream fs=new FileInputStream("C:\\selenium\\AmazonEndToEndProject\\test-output\\ExcelSheet\\SheetData.xlsx");
//		Workbook wb=WorkbookFactory.create(fs);
//		
//		for (int row = 0; row < 4; row++) {
//			for (int col = 0; col < 1; col++) {
//				String value= wb.getSheet("SearchData").getRow(row).getCell(col).getStringCellValue();
//				data[row][col]=value;
//				System.out.println(value);
//			}
//			
//		}
//	return data;
//	}
//	
//}
