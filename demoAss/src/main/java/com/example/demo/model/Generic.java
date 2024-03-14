package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Generic<T> {
	List<T> list = new ArrayList<T>();
	public Generic() {
		super();
		// TODO Auto-generated constructor stub
		
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
