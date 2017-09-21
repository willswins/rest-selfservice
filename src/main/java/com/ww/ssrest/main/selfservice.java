package com.ww.ssrest.main;

import com.ww.ssrest.reader.API;
import com.ww.ssrest.reader.Main;
import com.ww.ssrest.reader.Pojo;
import com.ww.ssrest.reader.WType;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.xml.sax.SAXException;


import com.sun.codemodel.ClassType; 
import com.sun.codemodel.JBlock; 
import com.sun.codemodel.JClass; 
import com.sun.codemodel.JClassAlreadyExistsException; 
import com.sun.codemodel.JCodeModel; 
import com.sun.codemodel.JDefinedClass; 
import com.sun.codemodel.JExpr; 
import com.sun.codemodel.JFieldVar; 
import com.sun.codemodel.JForEach; 
import com.sun.codemodel.JInvocation; 
import com.sun.codemodel.JMethod; 
import com.sun.codemodel.JMod; 
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JPrimitiveType;
import com.sun.codemodel.JType; 
import com.sun.codemodel.JVar; 
import com.sun.codemodel.writer.FileCodeWriter; 


public class selfservice {
	public static void main(String[] args) {
		try {
			(new selfservice()).Go();
		} catch (IOException arg1) {
			arg1.printStackTrace();
		} catch (SAXException arg2) {
			arg2.printStackTrace();
		} catch (InvalidFormatException arg3) {
			arg3.printStackTrace();
		}

	}

	public void Go() throws IOException, SAXException, InvalidFormatException {
		BufferedInputStream inputXML = new BufferedInputStream(
				this.getClass().getClassLoader().getResourceAsStream("ServiceDef.xml"));
		XLSReader xlsReader = ReaderBuilder.buildFromXML(inputXML);
		ArrayList result = new ArrayList();
		HashMap beans = new HashMap();
		beans.put("apis", result);
		Main main = new Main();
		beans.put("main", main);
		BufferedInputStream inputStream = new BufferedInputStream(
				this.getClass().getClassLoader().getResourceAsStream("test.xlsx"));
		xlsReader.read(inputStream, beans);
		System.out.println(((API) result.get(0)).a);
		System.out.println(main.getMapping());

		try {
			inputStream = new BufferedInputStream(this.getClass().getClassLoader().getResourceAsStream("test.xls"));
			HSSFWorkbook e = null;
			e = new HSSFWorkbook(inputStream);
			Sheet sheet = e.getSheet("Ref");
			Iterator rowIterator = sheet.iterator();
			int rowCounter = 0;
			boolean pojoToAdd=false;
			Pojo pojo = null;
			ArrayList pojos = new ArrayList();
			// for (ArrayList pojos = new ArrayList(); rowIterator.hasNext();
			// System.out.println(pojos)) {
			while (rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();
				rowCounter = rowCounter + 1;

				if (rowCounter == 1)
					continue;

				Iterator cellIterator = row.cellIterator();
				int cellCounter = -1;
				while (cellIterator.hasNext()) {
					Cell cell;
					String readValue = null;

					cell = (Cell) cellIterator.next();
					++cellCounter;

					readValue = cell.getStringCellValue();
					if (cellCounter == 0 && !StringUtils.isEmpty(readValue) && !".".equalsIgnoreCase(readValue)) {
						if (pojo !=null)
						{
							pojos.add(pojo);
							
						}
						pojo = new Pojo();
						pojoToAdd=true;
						pojo.name=readValue;
						pojo.basePackage="com.ww.model";
						System.out.println(pojo);
						break;
					} else if (cellCounter == 0 && ".".equalsIgnoreCase(readValue)) {

						continue;
					} else if (cellCounter == 1 && !StringUtils.isEmpty(readValue)) {

						cell = (Cell) cellIterator.next();
						++cellCounter;
						String typeNameCell = readCell(cell);
						pojo.members.put(typeNameCell, readValue);
						cell = (Cell) cellIterator.next();
						++cellCounter;
						String value1 = readCell(cell);
						pojo.values1.put(typeNameCell, value1);
						cell = (Cell) cellIterator.next();
						++cellCounter;
						String value2 = readCell(cell);
						pojo.values2.put(typeNameCell, value2);
						cell = (Cell) cellIterator.next();
						++cellCounter;
						String value3 = readCell(cell);
						pojo.values3.put(typeNameCell, value3);

						

					}

				}
			}
			
			if (pojoToAdd ==true)
			{
				pojos.add(pojo);
				
			}
			
			
			
			System.out.println(pojos);
			createPojos(pojos);
		} catch (Exception arg22) {
			arg22.printStackTrace();
			;
		}

	}

	private void createPojos(ArrayList<Pojo> pojos) throws JClassAlreadyExistsException, IOException {


		
		
		for (Pojo pojo : pojos)
		{
			JCodeModel codeModel = new JCodeModel();
			JPackage jp = codeModel._package(pojo.basePackage);
			JDefinedClass jc = jp._class(pojo.name);
			jc.javadoc().add("Generated class.");
			
			for (Map.Entry<Object, Object> entry : pojo.members.entrySet()) {
			    String key = (String) entry.getKey();
			    String value = (String) entry.getValue();
			    
			    
			    
			    
			    JFieldVar varField = jc.field(JMod.PRIVATE, returnType(codeModel,value,pojo.basePackage), key);
			    JMethod getVar = jc.method(JMod.PUBLIC, varField.type(), "get"+StringUtils.capitalize(key));
			    getVar.body()._return(varField);
			    
			    JMethod setVar = jc.method(JMod.PUBLIC, codeModel.VOID, "set"+StringUtils.capitalize(key));
			    setVar.param(varField.type(), varField.name());
			    setVar.body().assign(JExpr._this().ref(varField.name()), JExpr.ref(varField.name()));
			    
			}
			 codeModel.build(new File("src/main/java/"));
			
		}
		
		
		
	}

	String readCell(Cell cell) {
		String ret = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			ret = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			ret = cell.getStringCellValue();
			break;
		}
		return ret;
	}
	
	
	JType returnType(JCodeModel codeModel, String type, String basePackage) {
		JPrimitiveType ret;
		try
		{
		ret=JType.parse(codeModel, type);
		}
		catch(IllegalArgumentException e)
		{
			WType retu;
			switch (type){
				case "string":
					retu = new WType();
					retu.setName("String");
					retu.setFullName("java.lang.String");
					return retu;
				case "list":
					retu = new WType();
					retu.setName("String");
					retu.setFullName("java.util.ArrayList");
					return retu;
				default :
					retu = new WType();
					retu.setName(type);
					retu.setFullName(basePackage+"."+type);
					return retu;
			}
		}
		return ret;
		
		 
		
			

		
		
	}

}