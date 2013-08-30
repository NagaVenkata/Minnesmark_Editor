package mmStationEvents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.*;

import org.json.*;

public class MmMarkerEvent {
	
	private String eventName,markerFile,modelFile,nextEventName;
	private int makerIndex,audioIndex,videoIndex,panoramaIndex;
	public JLabel  markerFileLabel, modelFileLabel;
	
	public JSONObject events,attributes,actions;
	
	JSONArray action = new JSONArray();
	
    String sourcePath,destinationPath;
    
    boolean collectItem;
	
	public boolean isCollectItem() {
		return collectItem;
	}

	public void setCollectItem(boolean collectItem) {
		this.collectItem = collectItem;
	}

	public MmMarkerEvent()
	{
		events = new JSONObject();
		attributes = new JSONObject();
		actions = new JSONObject();
	}
	
	public MmMarkerEvent(String name)
	{
		this.markerFile = name; 
	}
	
	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}
	
	
	public void setStationMarkerFile(String name)
	{
		this.markerFile = name;
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
	
	public void makeJSONObject()
	{
		try 
		{
			events.put("name",this.eventName);
			events.put("type", "marker");
			attributes.put("markerName", "patt.marker"+Integer.toString(this.makerIndex));
			attributes.put("modelName", this.modelFile);
			
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
	
	public String getModelFile() {
		return modelFile;
	}

	public void setModelFile(String modelFile) {
		this.modelFile = modelFile;
	}

	public void addActions(String nextEventName)
	{
		action.put(nextEventName);
	}
	
	public void JSONActions()
	{
		
		try 
		{
			actions.put("marker-found",action);
			events.put("actions",actions);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileChannel src = null,des=null;
		try {
			  
			  
			  File file = new File(destinationPath+"/markers");
			  
			  
			  
			  this.setSourcePath(System.getProperty("user.dir")+"/markers/"+"patt.marker"+Integer.toString(this.makerIndex));
			  
			  File desFilePath = new File(sourcePath);
			  String desPath = desFilePath.getName();
			  
			  
			  
			  File desFile = new File(destinationPath+"/markers/"+desPath);
			  			  
			 //JOptionPane.showMessageDialog(null, "destination file exists "+ desFile.exists());
			  
			  if(!desFile.exists())
			  {
				 //JOptionPane.showMessageDialog(null, "destination file "+ desFile.getPath()+"  "+desFile.getName());
			     if(file.isDirectory())
			     {
			        //JOptionPane.showMessageDialog(null, sourcePath+"  "+destinationPath); 
				    src = new FileInputStream(sourcePath).getChannel(); 
			        des = new FileOutputStream(destinationPath+"/markers/"+desPath).getChannel();
			     }
			     else
			     {
				    file.mkdir();
				    src = new FileInputStream(sourcePath).getChannel();
				    des = new FileOutputStream(destinationPath+"/markers/"+desPath).getChannel();
			     }
			     
			     try {
					  
		                if(src!=null)
						   des.transferFrom(src, 0, src.size());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
	
	
	public JSONObject getMarkerEvent()
	{
		return events;
	}

}
