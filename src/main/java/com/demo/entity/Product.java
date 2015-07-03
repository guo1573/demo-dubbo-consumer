package com.demo.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Product implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4578474164514838434L;
	private Integer proId;
	private String prodName;
	private String proImgUrl;
	private Date createTime;
	private List list;
	private List<Map<String,String>> propertys; 

	@Override
	public String toString() {
		return this.proId + ":" + this.prodName+"@"+getClass().getName();
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}
	
	public String getProImgUrl() {
		return proImgUrl;
	}
	
	public void setProImgUrl(String proImgUrl) {
		this.proImgUrl = proImgUrl;
	}
	
	public List<Map<String,String>> getPropertys() {
		return propertys;
	}
	
	public void setPropertys(List<Map<String,String>> propertys) {
		this.propertys = propertys;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}