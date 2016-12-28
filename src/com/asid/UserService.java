package com.asid;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/UserService")
public class UserService {

  // UserDao userDao = new UserDao();
	FYPAsid asid=new FYPAsid();
	NICData ex=new NICData();
    DrivingLsnData ex1= new DrivingLsnData();
    VehicleData ex2= new VehicleData();
    CombineData ex3=new CombineData();
    FYPAsid ex4=new FYPAsid();

   @GET
   @Path("/user")
   @Produces(MediaType.APPLICATION_JSON)
   public String getUsers(@QueryParam("name")String name) throws JSONException{
	   System.out.println("sachithra");
     return asid.doJob(ex, ex1, ex2, ex3, name).toString();
	   //return "sachithra";
   }	
}