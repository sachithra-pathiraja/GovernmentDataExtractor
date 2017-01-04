package com.asid;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sachithra
 */
public class CombineData {
    public JSONObject Combine(ResultSet NIC,ResultSet DrivingLsn,ResultSet Vehicle/*,GovGui ui*/) throws JSONException{
        ResultSetMetaData rsmd,rsmd1,rsmd2;
        try {
            rsmd = NIC.getMetaData();
            rsmd1=DrivingLsn.getMetaData();
            rsmd2=Vehicle.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            int columnsNumber1 = rsmd1.getColumnCount();
            int columnsNumber2 = rsmd2.getColumnCount();
            JSONObject[] obj = new JSONObject[50];
            JSONObject[] NICObj = new JSONObject[50];
            JSONObject[] DrivingLsnObj = new JSONObject[50];
            JSONObject[] VehicleObj = new JSONObject[50];
            JSONObject a=new JSONObject();
            
            
            
            
         String[] id=new String[50];
         for(int i=0;i<50;i++){
             id[i]="kkk";
             obj[i]=new JSONObject();
             NICObj[i]=new JSONObject();
             DrivingLsnObj[i]=new JSONObject();
             VehicleObj[i]=new JSONObject();
            // obj[i]=null;
           // NICObj[i]=null;
            // DrivingLsnObj[i]=null;
            // VehicleObj[i]=null;
         }
         int counter=0;
    while (NIC.next()) {
        id[counter]="NYC data is, NIC="+NIC.getString("NIC")+" Name="+NIC.getString("name");
        
        NICObj[counter].put("Name", NIC.getString("name"));
        NICObj[counter].put("ID", NIC.getString("NIC"));
        NICObj[counter].put("FamilyName", NIC.getString("FamilyName"));
        NICObj[counter].put("Surname", NIC.getString("Surname"));
        NICObj[counter].put("Civil Status", NIC.getString("Civil_Status"));
        NICObj[counter].put("Profession", NIC.getString("Profession"));
        NICObj[counter].put("place of birth", NIC.getString("place_of_birth"));
        NICObj[counter].put("secretariat division", NIC.getString("secretariat_division_"));
        NICObj[counter].put("District", NIC.getString("district"));
        NICObj[counter].put("Number of the house", NIC.getString("Number_of_the_house"));
        NICObj[counter].put("street", NIC.getString("street"));
        NICObj[counter].put("village or city", NIC.getString("village_or_city"));
         NICObj[counter].put("place of birth", NIC.getString("place_of_birth"));
        NICObj[counter].put("postal code", NIC.getString("postal_code"));
        NICObj[counter].put("telephone", NIC.getString("telephone"));
        NICObj[counter].put("E-mail", NIC.getString("E-mail"));
        NICObj[counter].put("Date of birth", NIC.getString("date_of_birth"));
        NICObj[counter].put("picture", NIC.getString("Picture"));
        
        /*for(int i=0;i<columnsNumber;i++){
            String columnValue = NIC.getString(i);
        id[counter]=id[counter]+columnValue + " " + rsmd.getColumnName(i)+",";
                }*/
        while (DrivingLsn.next()) {
            if(NIC.getString("NIC").equals(DrivingLsn.getString("NIC"))){
            id[counter]=id[counter]+"\r\n Driving license data is, Name="+DrivingLsn.getString("Name_With_Initials")+" License Number="+DrivingLsn.getString("Driving_License_Number");
            DrivingLsnObj[counter].put("Full Name", DrivingLsn.getString("Full_Name"));
            DrivingLsnObj[counter].put("Name with initials", DrivingLsn.getString("Name_With_Initials"));
            DrivingLsnObj[counter].put("sex", DrivingLsn.getString("sex"));
            DrivingLsnObj[counter].put("address", DrivingLsn.getString("Address"));
            DrivingLsnObj[counter].put("ID", DrivingLsn.getString("NIC"));
            DrivingLsnObj[counter].put("Date of birth", DrivingLsn.getString("date_of_birth"));
            DrivingLsnObj[counter].put("driving licence number", DrivingLsn.getString("Driving_License_Number"));
            DrivingLsnObj[counter].put("Blood Type", DrivingLsn.getString("Blood_type"));
        
            }
        }
        DrivingLsn.beforeFirst();
            while(Vehicle.next()){
                if(NIC.getString("NIC").equals(Vehicle.getString("NIC"))){
                id[counter]=id[counter]+"\r\n Vehicle data is, Name="+Vehicle.getString("Full_Name")+" Vehicle Number="+Vehicle.getString("Vehicle_Number");
                VehicleObj[counter].put("Full Name", Vehicle.getString("Full_Name"));
                VehicleObj[counter].put("Name with initials", Vehicle.getString("Name_With_Initials"));
                VehicleObj[counter].put("Address", Vehicle.getString("Address"));
                VehicleObj[counter].put("NIC", Vehicle.getString("NIC"));
                VehicleObj[counter].put("Date of birth", Vehicle.getString("Date_Of_Birth"));
                VehicleObj[counter].put("Engine Model", Vehicle.getString("Engine_Model"));
                VehicleObj[counter].put("Year of manufactured", Vehicle.getString("year_of_manufactured"));
                VehicleObj[counter].put("vehicle number", Vehicle.getString("vehicle_number"));
                }
               // for(int k=1; k<=columnsNumber2;k++){
                    
           // if (i > 1) System.out.print(",  ");
           // String columnValue = NIC.getString(i);
            //System.out.print(columnValue + " " + rsmd.getColumnName(i));
           // id[i]=NIC.getString("NIC");
      
    }
            Vehicle.beforeFirst();
            
    
        counter++;
    }
    for(int i=0;i<50;i++){
        if(NICObj[i]!=null){
            obj[i].put("NIC data", NICObj[i]);
        }
        if(DrivingLsnObj[i]!=null){
            obj[i].put("Driving License data", DrivingLsnObj[i]);
        }
        if(VehicleObj[i]!=null){
            obj[i].put("Vehicle data", VehicleObj[i]);
        }
    }
    a.put("Everything", obj);
    /*for(int i=0;i<50;i++){
        if(id[i]!="kkk"){
    System.out.println(id[i]);
    System.out.println(" ");
    System.out.println(" ");
        }
    }*/
   /* for(int i=0;i<3;i++){
        if(VehicleObj[i]!=null){
          JSONObject vehicle_data = (JSONObject) obj[i].get("Vehicle data");
       //   System.out.println(vehicle_data.get("Address"));
            //System.out.println(vehicle_data.get("Year of manufactured"));
            //System.out.println(DrivingLsnObj[i]);
           System.out.println(VehicleObj[i]);
        }
    }
   /* ui.input(id);*/
    return a;
        } catch (SQLException ex) {
            Logger.getLogger(CombineData.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
     
    }
    
}
