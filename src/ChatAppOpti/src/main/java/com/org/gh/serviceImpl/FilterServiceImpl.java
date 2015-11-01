package com.org.gh.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.org.gh.service.FilterService;

public class FilterServiceImpl implements FilterService {

	public Map parseData(String data) 
    {
    	Map<String, Double> dataMap = new HashMap<String, Double>();
    	List<String> dataList = null;
    	try{
	    	data = data.replace("\\", "");
	    	data = data.replace("\"", "");
	    	System.out.println(data);
	    	data = data.substring(data.indexOf("["), data.indexOf("]")+1);
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
	    	String[] eachLocn = data.split("\\},\\{");
	    	String locn;
	    	Double weight ;
	    	for(int i=0; i< eachLocn.length; i++)
	    	{
	    		locn = eachLocn[i].substring(eachLocn[i].indexOf(":")+1,eachLocn[i].indexOf(","));
	    		try{
	    			if(i != eachLocn.length-1)
	    				weight = Double.valueOf(eachLocn[i].substring(eachLocn[i].lastIndexOf(":")+1));
	    			else
	    				weight = Double.valueOf(eachLocn[i].substring(eachLocn[i].indexOf(":")+1, eachLocn[i].indexOf("\\}")));
	    		}catch(Exception e)
	    		{
	    			weight = 0.0;
	    			//e.printStackTrace();
	    		}
	    		dataMap.put(locn, weight);
	    	}
    	
    	return dataMap;
    }
}
