package com.scp.dataProvider;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.scp.hibernateUtil.Verification;

public class TestNG {
	@Test(dataProvider="getDataProvider")
	public void authetication(String uname,String password,String expected)
	{
		String actual=Verification.verification(uname, password);
		
		//System.out.println(actual);
		Assert.assertEquals(expected,actual);
	}
	@DataProvider(name="getDataProvider")
	public Object[][] getDataProvider() throws InterruptedException, IOException
	{
		File file=new File("C:\\Users\\Tanmayeee\\Desktop\\EmployeeInfo.xlsx");
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheetAt(0);
		Object[][]data=new Object[sheet.getPhysicalNumberOfRows()][3];
		Iterator<Row>rowiterator=sheet.iterator();
		while(rowiterator.hasNext())
		{
			
			for(int i=0;i<sheet.getPhysicalNumberOfRows();i++)
			{
				Row row=rowiterator.next();
				Iterator<Cell>celliterator=row.iterator();
				Thread.sleep(2000);
				
				for(int j=0;j<3;j++)
				{
					Thread.sleep(2000);
					Cell cell=celliterator.next();
					
					System.out.println("Inside i'th row "+i +" j "+j+" cell :"+cell.getStringCellValue());
					data[i][j]=cell.getStringCellValue();
				}
			}
		}
		return data;
	}

	}


