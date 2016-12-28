package com.asid;

import java.sql.*;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author sachithra
 */
public class FYPAsid {
    
    /**
     * @param args the command line arguments
     */
        // TODO code application logic here
    
    public static void main(String[] args) throws JSONException {
        
       /* JSONObject obj = new JSONObject();

      obj.put("name", "foo");
      obj.put("num", new Integer(100));
      obj.put("balance", new Double(1000.21));
      obj.put("is_vip", new Boolean(true));

      System.out.print(obj);*/
        
        NICData ex=new NICData();
        DrivingLsnData ex1= new DrivingLsnData();
        VehicleData ex2= new VehicleData();
        CombineData ex3=new CombineData();
        FYPAsid ex4=new FYPAsid();
        GovGui ex5=new GovGui(ex,ex1,ex2,ex3,ex4);
        ex5.setVisible(true);
    }
    public JSONObject doJob(NICData ex,DrivingLsnData ex1,VehicleData ex2,CombineData ex3/*,GovGui ex4*/,String Name) throws JSONException{
        
       // String Name="asanka";
        String[] splited = Name.split("\\s+");
        System.out.println(splited.length);
        for(int i=0;i<splited.length;i++){
            System.out.println(splited[i]);
        }
        if(splited.length>1){
        ResultSet NIC=ex.NIC("select * from nic_data where name like '%"+splited[0]+"%' or name like '%"+splited[1]+"%' or Surname like '%"+splited[0]+"%' or Surname like '%"+splited[1]+"%' or FamilyName like '%"+splited[0]+"%' or FamilyName like '%"+splited[1]+"%'");
        ResultSet DrivingLsn=ex1.DrivingLsn("select * from drivinglsn_data where full_name like '%"+splited[0]+"%' or full_name like '%"+splited[1]+"%'");
        ResultSet Vehicle=ex2.Vehicle("select * from vehicle_data where full_name like '%"+splited[0]+"%' or full_name like '%"+splited[1]+"%'");
        return ex3.Combine(NIC, DrivingLsn, Vehicle/*,ex4*/);
        
    }
        else{
            ResultSet NIC=ex.NIC("select * from nic_data where name like '%"+splited[0]+"%'  or Surname like '%"+splited[0]+"%'  or FamilyName like '%"+splited[0]+"%' ");
        ResultSet DrivingLsn=ex1.DrivingLsn("select * from drivinglsn_data where full_name like '%"+splited[0]+"%' ");
        ResultSet Vehicle=ex2.Vehicle("select * from vehicle_data where full_name like '%"+splited[0]+"%' ");
        return ex3.Combine(NIC, DrivingLsn, Vehicle/*,ex4*/);
        }
    }
    
}
class NICData {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/asid";

   //  Database credentials
   static final String USER = "sachithra";
   static final String PASS = "sachithra";
   ResultSet rs;
   public  ResultSet NIC(String querry) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
      System.out.println("Creating database...");
      stmt = conn.createStatement();
      
      String sql = querry;
     rs= stmt.executeQuery(sql);
     ResultSetMetaData rsmd = rs.getMetaData();
     int columnsNumber = rsmd.getColumnCount();
     /*String[] id=new String[3];
     int counter=0;
     do{
      ++counter;   
     }while(rs.next());
     String[][] results=new String[3][counter];*/
     
   /* while (rs.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
            if (i > 1) System.out.print(",  ");
            String columnValue = rs.getString(i);
            System.out.print(columnValue + " " + rsmd.getColumnName(i));
            //id[i]=rs.getString(columnValue);
            
        }
        System.out.println("");
    }*/
      System.out.println("Database created successfully...");
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }/*finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try*/
   System.out.println("Goodbye!");
   return rs;
}//end main
   
}//end JDBCExample
