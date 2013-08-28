package mmStationEvents;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MmMessageEvent {
	
    private String type,eventName,message;
	public JTextField eventMessageName;
	
	JSONObject events,attributes,actions;
	
	JSONArray action = new JSONArray();
	
	boolean collectItem;
	
	String sourcePath,destinationPath,fileName;
	
	public MmMessageEvent()
	{
		events = new JSONObject();
		attributes = new JSONObject();
		actions = new JSONObject();
	}
	
	
	
	public MmMessageEvent(String text)
	{
		this.message = text; 
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	
	public void setMessageFile(String text)
	{
		this.fileName = text;
	}
	
	public void setEventName(String eventName)
	{
		this.eventName=eventName;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void setSourcePath(String src)
	{
		sourcePath = src;
	}
	
	public void setDestinationPath(String des)
	{
		destinationPath = des;
	}
	
	public String getMessage()
	{
		return this.fileName;
	}
	
	public String getEventName()
	{
		return this.eventName;
	}
	
	
	
	public String getSourcePath()
	{
		return sourcePath;
	}
	
	public String getDestinationPath()
	{
		return destinationPath;
	}
	
	public void setCollectItem(boolean collect)
	{
		collectItem = collect;
	}
	
	public boolean getCollectItem()
	{
		return collectItem;
	}
	
	public void makeJSONObject()
	{
		try {
			events.put("name", this.eventName);
			events.put("type", "message");
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addActions(String nextEventName)
	{
		action.put(nextEventName);
	}

	public void JSONActions()
	{
		
		BufferedReader br;
		try {
			   br = new BufferedReader(new FileReader(sourcePath));
		
	           try {
	                 StringBuilder sb = new StringBuilder();
	                 String line = br.readLine();

	                 while (line != null) {
	                       sb.append(line);
	                       sb.append("\n");
	                       line = br.readLine();
	                }
	                this.message = sb.toString();
	              }     
		            catch (FileNotFoundException e1) {
			        // TODO Auto-generated catch block
			        e1.printStackTrace();
	               }
	           finally {
		   	        br.close();
		   	    }
		}       
	    catch(Exception e)
	    {
	    	
	    }
	           
		
		try 
		{
			attributes.put("fileName", this.fileName);
			attributes.put("message", this.message);
			attributes.put("buttonTitle", "Stäng");
			events.put("attributes", attributes);
			
			actions.put("message-disappeared", action);
			events.put("actions",actions);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileChannel src = null,des=null;
		try {
			  
			  
			  File file = new File(destinationPath+"/messages");
			  
			  String[] desPath = sourcePath.split("/");
              
			  File desFile = new File(destinationPath+"/messages/"+desPath);
			  
			  if(!desFile.exists())
			  {	  
			     if(file.isDirectory())
			     {
				    src = new FileInputStream(sourcePath).getChannel(); 
			        des = new FileOutputStream(destinationPath+"/messages/"+desPath[desPath.length-1]).getChannel();
			     }
			     else
			     {
				    file.mkdir();
				    src = new FileInputStream(sourcePath).getChannel();
				    des = new FileOutputStream(destinationPath+"/messages/"+desPath[desPath.length-1]).getChannel();
			     }
			  }
			  
			  
			  try {
				des.transferFrom(src, 0, src.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally
		{
			try {
				src.close();
				des.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public JSONObject getMessageEvent()
	{
		return events;
	}



}
