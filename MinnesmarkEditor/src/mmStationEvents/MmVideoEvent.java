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

public class MmVideoEvent {
	
	private String eventName,type,videoFile,fileExtension,nextEventName;
	private int makerIndex,audioIndex,videoIndex,panoramaIndex;
	
	
	
	public JSONObject events,attributes,actions;
	
	boolean collectItem;
	
    String sourcePath,destinationPath;
	
	JSONArray action = new JSONArray();
	
	public MmVideoEvent()
	{
		events = new JSONObject();
		attributes = new JSONObject();
		actions = new JSONObject();
	}
	
	public MmVideoEvent(String name)
	{
		this.videoFile = name; 
	}
	
	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}
	
	
	public void setVideoFileName(String name)
	{
		this.videoFile = name;
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
	
	public void setMarkerIndex(int markerIndex)
	{
		this.makerIndex=markerIndex;
	}
	
	public void setAudioIndex(int audioIndex)
	{
		this.audioIndex=audioIndex;
	}
	
	public void setVideoIndex(int videoIndex)
	{
		this.videoIndex=videoIndex;
	}
	
	public void setPanoramaIndex(int panoramaIndex)
	{
		this.panoramaIndex=panoramaIndex;
	}
	
	public String getEventName()
	{
		return this.eventName;
	}
	
	public String getVideoFileName()
	{
		return this.videoFile;
	}
	
	public int getMarkerIndex()
	{
		return this.makerIndex;
	}
	
	public int getAudioIndex()
	{
		return this.audioIndex;
	}
	
	public int getVideoindex()
	{
		return this.videoIndex;
	}
	
	public int getPanoramaIndex()
	{
		return this.panoramaIndex;
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
			   events.put("type", "video");
			   attributes.put("filename", this.videoFile);
			   attributes.put("filetype", "m4v");
			   
			   attributes.put("collectItem", collectItem);
				
			   events.put("attributes", attributes);
			   events.put("actions", action);
			   
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
		
		try 
		{
			actions.put("video-finished-playing", action);
			events.put("actions",actions);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileChannel src = null,des=null;
		try {
			  
			  
			  File file = new File(destinationPath+"/videos");
			  
			  File desFilePath = new File(sourcePath);
			  String desPath = desFilePath.getName();
			  
              File desFile = new File(destinationPath+"/videos/"+desPath);
			  
			  if(!desFile.exists())
			  {
			  
			      if(file.isDirectory())
			      {
				      src = new FileInputStream(sourcePath).getChannel(); 
			          des = new FileOutputStream(destinationPath+"/videos/"+desPath).getChannel();
			      }
			      else
			      {
				      file.mkdir();
				      src = new FileInputStream(sourcePath).getChannel();
				      des = new FileOutputStream(destinationPath+"/videos/"+desPath).getChannel();
			      }
			  }    
			  
			  
			  
			  try {
				  if(des!=null)
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
	
	public JSONObject getVideoEvent()
	{
		return events;
	}

}
