package com.Metadata.test;

public class ToolingQueryList {
	public static String getCustomObjects(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,DeveloperName,CreatedById,CreatedDate,LastModifiedDate+from+CustomObject+order+by+DeveloperName+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,DeveloperName,CreatedById,CreatedDate,LastModifiedDate+from+CustomObject+where+LastModifiedDate%3E"
					+ startdate + "+order+by+DeveloperName+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,DeveloperName,CreatedById,CreatedDate,LastModifiedDate+from+CustomObject+where+LastModifiedDate%3E"
					+ enddate + "+order+by+DeveloperName+asc";
		} else {

			return "select+Id,DeveloperName,CreatedById,CreatedDate,LastModifiedDate+from+CustomObject+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+DeveloperName+asc";
		}
	}
	
	public static String getCustomFields(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,DeveloperName,TableEnumOrId,LastModifiedDate+from+CustomField+order+by+DeveloperName+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,DeveloperName,TableEnumOrId,LastModifiedDate+from+CustomField+where+LastModifiedDate%3E"
					+ startdate + "+order+by+DeveloperName+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,DeveloperName,TableEnumOrId,LastModifiedDate+from+CustomField+where+LastModifiedDate%3E"
					+ enddate + "+order+by+DeveloperName+asc";
		} else {

			return "select+Id,DeveloperName,TableEnumOrId,LastModifiedDate+from+CustomField+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+DeveloperName+asc";
		}
	}
	
	public static String getApexTriggers(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,LastModifiedDate+from+ApexTrigger+order+by+Name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,LastModifiedDate+from+ApexTrigger+where+LastModifiedDate%3E"
					+ startdate + "+order+by+Name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,LastModifiedDate+from+ApexTrigger+where+LastModifiedDate%3E"
					+ enddate + "+order+by+Name+asc";
		} else {

			return "select+Id,Name,LastModifiedDate+from+ApexTrigger+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+Name+asc";
		}
	}
	
	public static String getApexClasses(String startdate, String enddate) {

		if ((startdate.equalsIgnoreCase("") || startdate == null)
				&& (enddate.equalsIgnoreCase("") || enddate == null)) {
			return "select+Id,Name,LastModifiedDate+from+ApexClass+order+by+name+asc";

		} else if (enddate.equalsIgnoreCase("") || enddate == null) {
			return "select+Id,Name,LastModifiedDate+from+ApexClass+where+LastModifiedDate%3E"
					+ startdate + "+order+by+name+asc";

		} else if (startdate.equalsIgnoreCase("") || startdate == null) {
			return "select+Id,Name,LastModifiedDate+from+ApexClass+where+LastModifiedDate%3E"
					+ enddate + "+order+by+name+asc";
		} else {

			return "select+Id,Name,LastModifiedDate+from+ApexClass+where+LastModifiedDate%3E"
					+ startdate + "+and+LastModifiedDate%3C" + enddate + "+order+by+name+asc";
		}
	}
	
	public static String getObjectNameQuery(String CustomObjectId)
	{
		return "select+Id,DeveloperName+from+CustomObject+where+Id='"+CustomObjectId+"'";
	}

}