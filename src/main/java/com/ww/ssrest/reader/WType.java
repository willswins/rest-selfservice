package com.ww.ssrest.reader;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JFormatter;
import com.sun.codemodel.JType;

public class WType extends JType  {

	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	private String fullName;
	
	public WType() {
		super();
		// TODO Auto-generated constructor stub
	}

	 public void generate(JFormatter f) {
	        f.p(this.getFullName());
	    }

	@Override
	public JCodeModel owner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String fullName() {
		
		return this.fullName;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public JClass array() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JClass boxify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JType unboxify() {
		// TODO Auto-generated method stub
		return null;
	}

}
