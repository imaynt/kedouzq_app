package com.hck.zhuanqian.bean;import org.codehaus.jackson.annotate.JsonAutoDetect;import org.codehaus.jackson.annotate.JsonProperty;import org.codehaus.jackson.map.annotate.JsonSerialize;@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)public class OrderBean {	@JsonProperty("id")	private int id;	@JsonProperty("userName")	private String name;	@JsonProperty("content")	private String content;	@JsonProperty("timestamp")	private String time;	public int getId() {		return id;	}	public void setId(int id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	public String getTime() {		return time;	}	public void setTime(String time) {		this.time = time;	}}