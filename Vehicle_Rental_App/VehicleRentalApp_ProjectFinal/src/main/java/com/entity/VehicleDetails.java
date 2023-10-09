package com.entity;

public class VehicleDetails {
	
	private String RegNo;
	private String Category;
	private String Manufacturer;
	private double DRent;
	private float Mileage;
	private String FType;
	private String descp;

	public VehicleDetails() {
		// TODO Auto-generated constructor stub
	}
	public VehicleDetails(String name) {
		// TODO Auto-generated constructor stub
		Category=name;
	}
	public String getRegNo() {
		return RegNo;
	}
	public void setRegNo(String regNo) {
		RegNo = regNo;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getManufacturer() {
		return Manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}
	public double getDRent() {
		return DRent;
	}
	public void setDRent(double dRent) {
		DRent = dRent;
	}
	public float getMileage() {
		return Mileage;
	}
	public void setMileage(float mileage) {
		Mileage = mileage;
	}
	public String getFType() {
		return FType;
	}
	public void setFType(String fType) {
		FType = fType;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
	}

	
	

}
