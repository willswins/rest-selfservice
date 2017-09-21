package com.ww.ssrest.container;

import com.ww.ssrest.container.Methods;
import java.util.List;

public class Controller {
	private String className;
	private List<Methods> methods;

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Methods> getMethods() {
		return this.methods;
	}

	public void setMethods(List<Methods> methods) {
		this.methods = methods;
	}
}