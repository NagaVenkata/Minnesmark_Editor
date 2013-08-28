package mmStationEvents;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.*;

public class MmImageEvent {
	
    private String type,eventName,imageFile;
    private int width=345;
    private int height=218;
	JSONObject eventType,attributes,actions;
	
	String imageType;
	
	boolean collectItem;
	
	String sourcePath,destinationPath;
	
	JSONArray action = new JSONArray();
	
	public MmImageEvent()
	{
		eventType = new JSONObject();
		attributes = new JSONObject();
		actions = new JSONObject();
	}
			
	public MmImageEvent(String name)
	{
		this.imageFile = name; 
	}
	
	public void setCollectItem(boolean collect)
	{
		collectItem = collect;
	}
	
	public boolean getCollectItem()
	{
		return collectItem;
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
	
	public void makeJSONObject()
	{
		
		try {
			   eventType.put("name",this.eventName);
			   eventType.put("type","image");
			   attributes.put("imageName", this.imageFile);
			   attributes.put("imagetype", imageType);
			   
			   
			   BufferedImage readImage = null;

			   try {
			       readImage = ImageIO.read(new File(sourcePath));
			       this.height = readImage.getHeight();
			       this.width = readImage.getWidth();
			   } catch (Exception e) {
			       readImage = null;
			   }

			   
			   attributes.put("width", this.width);
			   attributes.put("height", this.height);
			   attributes.put("collectItem", collectItem);
			   
			   eventType.put("attributes", attributes);
			  
			   
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	
	
	public void JSONActions()
	{
		
		try 
		{
			 actions.put("image-disappeared",action);
			 eventType.put("actions", actions);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileChannel src = null,des=null;
		
		try 
		{
			  			  
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
	
	public void addActions(String nextEventName)
	{
		action.put(nextEventName);
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	
	public void setImageFile(String name)
	{
		this.imageFile = name;
		
	}
	
	public void setImageType(String imagetype)
	{
		this.imageType = imagetype;
	}
	
	public void setEventName(String eventName)
	{
		this.eventName=eventName;
		
	}
	
	
	public String getType()
	{
		return this.type;
	}
	
	public String getImageFile()
	{
		return this.imageFile;
	}
	
	public String getEventName()
	{
		return this.eventName;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public JSONObject getImageEvent()
	{
		return eventType;
	}

}
