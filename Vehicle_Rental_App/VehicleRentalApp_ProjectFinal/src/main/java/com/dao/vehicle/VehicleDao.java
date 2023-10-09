package com.dao.vehicle;

import java.util.ArrayList;

import com.entity.CustomerDetails;
import com.entity.VehicleDetails;
import com.exception.DataAccessException;


public interface VehicleDao {
	
	void addVehicleDetails(VehicleDetails vehicledetails) throws DataAccessException;
    ArrayList<String> getRegNo(CustomerDetails customerdetails) throws DataAccessException;
    void addCustomerDetails(CustomerDetails customerdetails) throws DataAccessException;
    void displayReportDetail() throws DataAccessException;

}
