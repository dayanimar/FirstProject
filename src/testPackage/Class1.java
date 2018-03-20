package testPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//import jxl.Sheet;
//import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Class1 {

	public static void main(String[] args) throws BiffException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		
		WebDriver driver;
		String username, password;
		
		/**File f = new File("C:/Users/dayan/Desktop/test.txt");
		System.out.println(f.getPath());*/
		FileReader freader = new FileReader("C:/Users/dayan/Desktop/test.txt");
		
		BufferedReader br = new BufferedReader(freader);
		String s;
		while((s = br.readLine()) != null) {
		System.out.println(s);
		}
		freader.close();
		FileInputStream f = new FileInputStream( new File("C:/Users/dayan/Desktop/input.xlsx"));
		//System.out.println(f.getPath());
		XSSFWorkbook w = new XSSFWorkbook(f);
		Sheet s1 = w.getSheet("Sheet1"); 
		int rowCount = s1.getLastRowNum() - s1.getFirstRowNum();
		//Create a loop over all the rows of excel file to read it

	    for (int i = 0; i < rowCount+1; i++) {

	        Row row = s1.getRow(i);

	        //Create a loop to print cell values in a row

	        for (int j = 0; j < row.getLastCellNum(); j++) {

	            //Print Excel data in console

	        	System.out.print(row.getCell(j) + "  ");

	        }
	        
	        username = row.getCell(0).getStringCellValue();	
	        password = row.getCell(1).getStringCellValue();
	        System.setProperty("webdriver.gecko.driver", "C:\\Users\\dayan\\Downloads\\geckodriver-v0.19.1-win32\\geckodriver.exe");
        	driver = new FirefoxDriver();
        	driver.get("http://www.gcrit.com/build3/admin/");
        	driver.findElement(By.name("username")).sendKeys(username);
        	driver.findElement(By.name("password")).sendKeys(password);
        	driver.findElement(By.id("tdb1")).click();
        	if (driver.getCurrentUrl().equals("http://www.gcrit.com/build3/admin/index.php"))
        	{
        		System.out.println("Test "+i +" passed" );
        	}
        	else
        	{
        		System.out.println("Test "+i +" failed" );
        	}
        	//Assert.assertEquals("http://www.gcrit.com/build3/admin/index.php", driver.getCurrentUrl());
        	driver.close();
        	
	        System.out.println();

	    }
		//System.out.println(rows);
		f.close();

	}

}
