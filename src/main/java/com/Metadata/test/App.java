package com.Metadata.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * Hello world!
 *
 */
public class App 
{
	public static long main(String[] args) throws Exception {
		// get the total memory for my app
		long total = Runtime.getRuntime().totalMemory();
		System.out.println("Available: "+total);
		JSONObject loginObject = RestLogin.GetLoginObject();

		String startdate = "2018-02-01T17:23:04.000Z";
		String enddate = "2018-03-01T17:23:04.000Z";

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();
		Element xmlroot = doc.createElement("Package");
		Attr attrType1 = doc.createAttribute("xmlns");
		attrType1.setValue("http://soap.sforce.com/2006/04/metadata");
		xmlroot.setAttributeNode(attrType1);
		doc.appendChild(xmlroot);

		/* ----------------------CustomObject Start--------------- */

		JSONArray customObjectList = DataWarehouse.getCustomObjectList(loginObject, startdate, enddate);
		Element xmlcustomobjecttype = doc.createElement("types");
		xmlroot.appendChild(xmlcustomobjecttype);
		if (customObjectList.length() > 0) {
			for (int j = 0; j < customObjectList.length(); j++) {
				Element xmlcustomobjectMembers = doc.createElement("members");
				xmlcustomobjectMembers
						.appendChild(doc.createTextNode(customObjectList.getJSONObject(j).getString("DeveloperName")));
				xmlcustomobjecttype.appendChild(xmlcustomobjectMembers);
			}
		}
		Element xmlcustomobjectName = doc.createElement("name");
		xmlcustomobjectName.appendChild(doc.createTextNode("CustomObject"));// Make
		xmlcustomobjecttype.appendChild(xmlcustomobjectName);


		/* ----------------------Apex Trigger Start--------------- */

		JSONArray apexTriggerList = DataWarehouse.getApexTriggerList(loginObject, startdate, enddate);
		Element xmlapextriggertype = doc.createElement("types");
		xmlroot.appendChild(xmlapextriggertype);
		for (int i = 0; i < apexTriggerList.length(); i++) {
			try {
				Element xmlapextriggerMembers = doc.createElement("members");
				xmlapextriggerMembers
						.appendChild(doc.createTextNode(apexTriggerList.getJSONObject(i).getString("Name")));
				xmlapextriggertype.appendChild(xmlapextriggerMembers);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Element xmlapextriggerName = doc.createElement("name");
		xmlapextriggerName.appendChild(doc.createTextNode("ApexTrigger"));// Make it
		xmlapextriggertype.appendChild(xmlapextriggerName);

		/* ----------------------Apex Trigger End--------------- */
		

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				"D:\\XML\\" + (new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()).toString()) + ".xml"));
		transformer.transform(source, result);
		// get the free memory available
		long free = Runtime.getRuntime().freeMemory();

		// some simple arithmetic to see how much i use
		long used = total - free;

		System.out.println("Used memory in bytes: " + used);
		return used;
	}
}
