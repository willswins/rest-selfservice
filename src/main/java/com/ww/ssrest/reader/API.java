package com.ww.ssrest.reader;

public class API {
	private String name;
	private String mapping;
	private String method;
	private String request;
	private String response;
	private String responseStatus;
	public String a = "@RequestMapping(method = RequestMethod.GET)\n@ResponseBody\npublic List<Foo> findAll() {\n    return service.findAll();\n}";

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

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequest() {
		return this.request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponseStatus() {
		return this.responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
}