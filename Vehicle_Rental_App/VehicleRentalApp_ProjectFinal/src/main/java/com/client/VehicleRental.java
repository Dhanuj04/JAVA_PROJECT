package com.client;

import java.text.ParseException;
import java.util.ArrayList;

import com.dao.jdbc.VehicleDaoImpl;
import com.dao.vehicle.VehicleDao;
import com.entity.CustomerDetails;
import com.entity.VehicleDetails;
import com.exception.DataAccessException;
import com.keyutil.KeyBoardUtil;

public class VehicleRental {
	public static void main(String[] args) throws DataAccessException, ParseException{
		VehicleDetails v = new VehicleDetails();
		CustomerDetails c = new CustomerDetails();
		VehicleDao ob=new VehicleDaoImpl();
		while(true){
		    System.out.println("Main Menu");
		    System.out.println("1.Add New Vehicle");
		    System.out.println("2.Book Vehicle");
		    System.out.println("3.Monthly Vehicle Booking Report");
		    int choice=KeyBoardUtil.getInt("Please Choose ONE Option [1, 2, 3]:");
		    switch(choice)
		    {
		    case 1:
		    	v.setRegNo(KeyBoardUtil.getString("Enter Vehicle Registration Number :"));
		    	v.setCategory(KeyBoardUtil.getString("Enter Vehicle Type [Car , Bus , Truck] :"));
		    	v.setManufacturer(KeyBoardUtil.getString("Enter Vehicle Manufacturer :"));
		    	v.setDRent(KeyBoardUtil.getInt("Enter Per-Day Rent :"));
		    	v.setFType(KeyBoardUtil.getString("Enter Vehicle Fuel Type :"));
		    	v.setMileage(KeyBoardUtil.getInt("Enter Vehicle Mileage :"));
		    	v.setDescp(KeyBoardUtil.getString("Enter Vehicle Description :"));
		    	ob.addVehicleDetails(v);
		    	break;
		    case 2:
		    	c.setCname(KeyBoardUtil.getString("Enter Your Name :"));
		    	c.setVCategory(KeyBoardUtil.getString("Enter Vehicle Type [Car , Bus , Truck] :"));
		    	ArrayList<String> ls1=ob.getRegNo(c);
				for( String d:ls1)
				{
				    System.out.println(d);
				    }
		    	c.setVRegNo(KeyBoardUtil.getString("Choose Vehicle Registration Number :"));
		    	c.setFDate(KeyBoardUtil.getString("Enter From Date :"));
		    	c.setTDate(KeyBoardUtil.getString("Enter To Date :"));
		    	ob.addCustomerDetails(c);
		    	break;
		    case 3:
		    	ob.displayReportDetail(); 	
		    }
		    break;
		    
		}
	}
}
		    
		    
		    
		    
		    
		 