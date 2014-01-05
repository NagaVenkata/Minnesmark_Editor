package mmStationEvents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.*;
import org.json.*;

import org.json.JSONArray;
import org.json.JSONException;

public class MmPanoramaEvent {
	
	private String type,eventName,panoramaFile,fileExtension;
	
	public JSONObject events,attributes,actions;
	
	JSONArray action = new JSONArray();
	
	boolean collectItem;
	
	String sourcePath,destinationPath;
	
	public MmPanoramaEvent()
	{
		events = new JSONObject();
		attributes = new JSONObject();
		actions = new JSONObject();
	}
	
	public MmPanoramaEvent(String name)
	{
		this.panoramaFile = name; 
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	
	public void setPanoramaFile(String fileName)
	{
		this.panoramaFile = fileName;
	}
	
	public void setEventName(String name)
	{
		this.eventName=name;
	}
	
	public void setSourcePath(String src)
	{
		sourcePath = src;
	}
	
	public void setDestinationPath(String des)
	{
		destinationPath = des;
	}
	
	public String getSourcePath()
	{
		return sourcePath;
	}
	
	public String getDestinationPath()
	{
		return destinationPath;
	}
	
	
	public String getType()
	{
		return this.type;
	}
	
	public String getPanoramaFile()
	{
		return this.panoramaFile;
	}
	
	public String getEventName()
	{
		return this.eventName;
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
			   events.put("type", "panorama");
			   attributes.put("imageName", this.panoramaFile);
			   this.fileExtension = this.panoramaFile.substring(this.panoramaFile.lastIndexOf('.')+1,this.panoramaFile.length());
			   attributes.put("imagetype", this.fileExtension);
			   File desFilePath = new File(sourcePath);
			   attributes.put("imageSize", desFilePath.length());
			   
			   if(collectItem)
				    attributes.put("collectItem", true);
			   else
					attributes.put("collectItem", false);
				
				events.put("attributes", attributes);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void JSONActions()
	{
		
		try 
		{
						
			actions.put("panorama-finished-viewing",action);
			events.put("actions",actions);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileChannel src = null,des=null;
		try {
			  
			  
			  File file = new File(destinationPath+"/images");
			  
			  File desFilePath = new File(sourcePath);
			  String desPath = desFilePath.getName();
			  
			  File desFile = new File(destinationPath+"/images/"+desPath);
			  
			  if(!desFile.exists())
			  {		  
			     if(file.isDirectory())
			     {  
				    src = new FileInputStream(sourcePath).getChannel(); 
			        des = new FileOutputStream(destinationPath+"/images/"+desPath).getChannel();
			     }
			     else
			     {
				    file.mkdir();
				    src = new FileInputStream(sourcePath).getChannel();
				    des = new FileOutputStream(destinationPath+"/images/"+desPath).getChannel();
			     }
			  } 			  
			  
			  try {
				    if(src!=null && des!=null)
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
				  if(src!=null)
				  {		
				     src.close();
				     des.close();
				  } 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void addActions(String nextEventName)
	{
		action.put(nextEventName);
	}
	
	public JSONObject getPanoramaEvent()
	{
		return events;
	}
	
	
}
