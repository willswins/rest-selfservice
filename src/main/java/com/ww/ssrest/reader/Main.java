package com.ww.ssrest.reader;

import com.ww.ssrest.reader.API;
import java.util.List;

public class Main {
	String name;
	String mapping;
	String doc;
	List<API> apis;
	String mainPackalge;
	private static final String MAIN_ANNOTATION = "@RestController";
	private static final String REQUEST_MAPPING = "@RequestMapping";
	private static final String CLASS = "class";

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMapping() {
		return this.mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public String getDoc() {
		return this.doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getMainPackalge() {
		return this.mainPackalge;
	}

	public void setMainPackalge(String mainPackalge) {
		this.mainPackalge = mainPackalge;
	}

	public List<API> getApis() {
		return this.apis;
	}

	public void setApis(List<API> apis) {
		this.apis = apis;
	}

	public String toCode() {
		StringBuilder code = new StringBuilder();
		code.append("package").append(" ").append(this.mainPackalge).append(';');
		code.append("\n");
		code.append("@RestController").append("\n");
		code.append("@RequestMapping").append("");
		return code.toString();
	}
}