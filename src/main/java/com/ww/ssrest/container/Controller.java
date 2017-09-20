package com.ww.ssrest.container;

import java.util.List;

public class Controller {


	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Methods> getMethods() {
		return methods;
	}

	public void setMethods(List<Methods> methods) {
		this.methods = methods;
	}

	private List<Methods> methods;


}
