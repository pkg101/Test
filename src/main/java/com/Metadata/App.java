package com.Metadata;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("sfdcmetadataPSQL")
public class App {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void getdata(String sfdcuserid_usrname) throws Exception {
		System.out.println("calling metadataPSQL-- ");
        System.out.println("Session Id: "+sfdcuserid_usrname); 
	}
}