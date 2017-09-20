package com.ww.ssrest.reader;

import java.util.List;

public class Main {

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMapping() {
		return mapping;
	}
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	
	
	
	String name;
	String mapping;
	String doc;
	List<API> apis;

	public String getMainPackalge() {
		return mainPackalge;
	}

	public void setMainPackalge(String mainPackalge) {
		this.mainPackalge = mainPackalge;
	}

	String mainPackalge;

    private final static String MAIN_ANNOTATION="@RestController";

    private final static String REQUEST_MAPPING="@RequestMapping";

    private final static String CLASS="class";



    public List<API> getApis() {
		return apis;
	}
	public void setApis(List<API> apis) {
		this.apis = apis;
	}

	public String toCode()
	{
		StringBuilder code=new StringBuilder();
		code.append("package").append(" ").append(this.mainPackalge).append(';');
		code.append("\n");
        code.append(MAIN_ANNOTATION).append("\n");
        code.append(REQUEST_MAPPING).append("");
        return code.toString();
	}


}
