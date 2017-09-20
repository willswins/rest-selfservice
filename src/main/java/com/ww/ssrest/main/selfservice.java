package com.ww.ssrest.main;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.xml.sax.SAXException;

import com.ww.ssrest.reader.API;
import com.ww.ssrest.reader.Main;

public class selfservice {

	public static void main(String args[]) {
		try {
			new selfservice().Go();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Go() throws IOException, SAXException, InvalidFormatException {
		InputStream inputXML = new BufferedInputStream(
				getClass().getClassLoader().getResourceAsStream("ServiceDef.xml"));
		final XLSReader xlsReader = ReaderBuilder.buildFromXML(inputXML);

		final List<API> result = new ArrayList<API>();
		final Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("apis", result);
		Main main = new Main();
		beans.put("main", main);
		InputStream inputStream = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream("test.xlsx"));
		xlsReader.read(inputStream, beans);
		System.out.println(result.get(0).a);
		System.out.println(main.getMapping());
		getJson();

	}

	public void getJson()
	{
		String message;
		JSONObject json = new JSONObject();
		json.put("name", "student");

		JSONArray array = new JSONArray();
		JSONObject item = new JSONObject();
		item.put("information", "test");
		item.put("id", 3);
		item.put("name", "course1");
		array.put(item);

		json.put("course", array);

		message = json.toString();
		System.out.println(message);
	}

}
