package com.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.dao.vehicle.VehicleDao;
import com.entity.CustomerDetails;
import com.entity.VehicleDetails;
import com.exception.DataAccessException;

public class VehicleDaoImpl extends BaseDao implements VehicleDao {


    Connection con=null;
    PreparedStatement ps=null;
    private static final String ADD_VEHICLE_DETAILS="insert into vehicledetails(VRegno,Vcategory,manufacturer, drent, mileage, fueltype, description,RegDate) values(?,?,?,?,?,?,?,?)";
	private static final String GET_REGNO = "select VRegno from vehicledetails where vcategory = ?";
	private static final String ADD_CUSTOMER_DETAILS="insert into customerdetails(Cname,Ccategory,CRegno,Fdate,Tdate) values(?,?,?,?,?)";
	private static final String GET_VEHICLES_TOTAL_RENT = "select drent*datediff(tdate,fdate) from vehicledetails left join customerdetails on vehicledetails.VRegno=customerdetails.CRegno where VRegno=?";
	private static final String GET_MONTHLY_REPORTS="select Vcategory,count(Vcategory),count(Ccategory),sum(drent*datediff(tdate,fdate)) from vehicledetails v left join customerdetails c on v.VRegno=c.CRegno where RegDate between ? and ? group by Vcategory";
	
	@Override
	public void addVehicleDetails(VehicleDetails vehicledetails) throws DataAccessException {
		try{
		    con=getConnection();
		    ps=con.prepareStatement(ADD_VEHICLE_DETAILS);
		    ps.setString(1, vehicledetails.getRegNo());
		    ps.setString(2, vehicledetails.getCategory());
		    ps.setString(3, vehicledetails.getManufacturer());
		    ps.setDouble(4, vehicledetails.getDRent());
		    ps.setFloat(5, vehicledetails.getMileage());
		    ps.setString(6, vehicledetails.getFType());
		    ps.setString(7, vehicledetails.getDescp());
		    LocalDate currDate = LocalDate.now();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    String formattedString = currDate.format(formatter);
		    ps.setString(8, formattedString);
		    ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		    System.out.println("unable to load");
		}finally{
		    releaseResources(con, ps);
		    System.out.println("Successfully Added");
		}
	}

	@Override
	public ArrayList<String> getRegNo(CustomerDetails customerdetails) throws DataAccessException {
		ArrayList<String> ls=new ArrayList<String>();
		try{
		    con=getConnection();
		    ps=con.prepareStatement(GET_REGNO);
		    ps.setString(1, customerdetails.getVCategory());
		    ResultSet result=ps.executeQuery();
		    while(result.next()){
			String name = result.getString(1);
			ls.add(name);
		    }
		}catch(SQLException e){
		    throw new DataAccessException("cannot be load..");
		}finally{
		    releaseResources(con, ps); 	
		    return ls;
		}
	}

	@Override
	public void addCustomerDetails(CustomerDetails customerdetails) throws DataAccessException {
		try{
			ArrayList<Integer> ls = new ArrayList<Integer>();
			ls.clear();
		    con=getConnection();
		    ps=con.prepareStatement(ADD_CUSTOMER_DETAILS);
		    ps.setString(1, customerdetails.getCname());
		    ps.setString(2, customerdetails.getVCategory());
		    ps.setString(3, customerdetails.getVRegNo());
		    ps.setString(4,customerdetails.getFDate());
		    ps.setString(5, customerdetails.getTDate());
		    ps.executeUpdate();
		    ps=con.prepareStatement(GET_VEHICLES_TOTAL_RENT);
		    ps.setString(1, customerdetails.getVRegNo());
		    ResultSet rs = ps.executeQuery();
		    System.out.println("Vehicle Successfully Booked");
		    while(rs.next())
		    {
		    	int i = rs.getInt(1);
		    	ls.add(i);
		    }
		    for(int i=1;i<2;i++)
		    {
		    	System.out.println("RENT : "+ls.get(ls.size()-1));
		    }
		    ls.clear();
		}catch(SQLException e){
			e.printStackTrace();
		    System.out.println("unable to load");
		}finally{
		    releaseResources(con, ps); 
		}
	}

	@Override
	public void displayReportDetail() throws DataAccessException {
		// TODO Auto-generated method stub
		try{
			ArrayList<Integer> ls = new ArrayList<Integer>();
		    con=getConnection();
		    ps=con.prepareStatement(GET_MONTHLY_REPORTS);
		    LocalDate currDate = LocalDate.now();
			int currMonth = currDate.getMonthValue();
			int currYear = currDate.getYear();
			String fromDate  = currYear+"-"+currMonth+"-"+"01";
			String toDate  = currYear+"-"+currMonth+"-"+"31";
		    ps.setString(1, fromDate);
		    ps.setString(2, toDate);
		    ResultSet rs = ps.executeQuery();
		    System.out.println("Vehicle Type"+"\t"+"Total Number of Vehicles"+"\t"+"Total Numbe of Vehicles Rented"+"\t"+"Total Rent Earned");
		    while(rs.next())
		    {
		    	String a = rs.getString(1);
		    	int b = rs.getInt(2);
		    	int c = rs.getInt(3);
		    	int d = rs.getInt(4);
		    	System.out.println("   "+a+"\t\t\t"+"    "+b+"\t\t\t\t\t"+c+"\t\t"+d);
		    }
		    	
		}catch(SQLException e){
			e.printStackTrace();
		    System.out.println("unable to load");
		}finally{
		    releaseResources(con, ps);
		    
		}
	}
}	
	



