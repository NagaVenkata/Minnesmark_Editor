package mmStationEvents;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javax.swing.*;

import org.json.*;

import mmObjWriter.*;


public class MmModelEvent {
	
	private String type,eventName,modelFile,markerFileName,modelType=null;
	boolean shouldshouldDisplayOuterView = false;
    
	public JSONObject events,attributes,actions;
	
	boolean collectItem;
	
	String sourcePath,destinationPath,srcFile;
	
	JSONArray action = new JSONArray();
	
	public MmModelEvent()
	{
		events = new JSONObject();
		attributes = new JSONObject();
		actions = new JSONObject();
	}
	
	public MmModelEvent(String modelFile)
	{
		this.modelFile = modelFile; 
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	
	public void setModelFile(String modelFile)
	{
		this.modelFile = modelFile;
	}
	
	public void setModelType(String modelType)
	{
		this.modelType = modelFile;
	}
	
	public void setEventName(String eventName)
	{
		this.eventName=eventName;
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
	
	public String getImageFile()
	{
		return this.modelFile;
	}
	
	public String getEventName()
	{
		return this.eventName;
	}
	
	public boolean getDisplayOuterView()
	{
		return this.shouldshouldDisplayOuterView;
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
			events.put("type", "model");
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
	
	public void JSONActions()
	{
		
		try 
		{
			actions.put("model-finished-viewing",action);
			events.put("actions",actions);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
		if(modelType==null)
		{	
		   FileChannel src = null,des=null;
		   try {
			  
			   
			     
			   
			     File file = new File(destinationPath+"/osg_obj");
			  
			     File desFilePath = new File(sourcePath);
			     String desPath = desFilePath.getName();
			  
			     File desFile = new File(destinationPath+"/osg_obj"+desPath);
			  
			     if(!desFile.exists())
			     { 	 
			        if(file.isDirectory())
			        {
				        src = new FileInputStream(sourcePath).getChannel(); 
			            des = new FileOutputStream(destinationPath+"/osg_obj/"+desPath).getChannel();
			        }
			        else
			        {
				       file.mkdir();
				       src = new FileInputStream(sourcePath).getChannel();
				       des = new FileOutputStream(destinationPath+"/osg_obj/"+desPath).getChannel();
			       }
			        
			            
			     
			       if(desPath.contains(".obj"))
			       {
				      readObj();
			       }
			  
			       if(desPath.contains(".osg"))
			       {
				      readOsg();
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
	else {
		JOptionPane.showMessageDialog(null, "entered data");
		MmObjWriter objWriter = new MmObjWriter(destinationPath+"/osg_obj/"+this.modelFile);
		objWriter.setImageSrcPath(sourcePath);
		objWriter.beginSave(destinationPath+"/osg_obj/"+this.modelFile);
		objWriter.endSave();
	   
		FileChannel src = null,des=null;
		   try {
			  
			  
			     
			   
			     File file = new File(destinationPath+"/osg_obj");
			  
			     File desFilePath = new File(sourcePath);
			     String desPath = desFilePath.getName();
			     
			     
			  
			     		  
			     if(file.isDirectory())
			     {
				     src = new FileInputStream(sourcePath).getChannel(); 
			         des = new FileOutputStream(destinationPath+"/osg_obj/"+desPath).getChannel();
			     }
			     else
			     {
				    file.mkdir();
				    src = new FileInputStream(sourcePath).getChannel();
				    des = new FileOutputStream(destinationPath+"/osg_obj/"+desPath).getChannel();
			     }
			     
			     JOptionPane.showMessageDialog(null, "Entered");
			     
			     if(desPath.contains(".obj"))
			     {
				     readObj();
			     }
			  
			     if(desPath.contains(".osg"))
			     {
				     readOsg();
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
			
		
	}
	
	public void readObj()
	{
		ArrayList<String> materialTexts = new ArrayList<String>();
		try
		{
			
		   BufferedReader br = new BufferedReader(new FileReader(sourcePath));
		   String line;
		   while((line=br.readLine())!=null)
		   {
			   if(line.contains(".mtl"))
			   {
				   String[] lines = line.split(" ");
				   if(lines[lines.length-1].contains(".mtl"))
				   {
					   materialTexts.add(lines[lines.length-1]);
				   }
			   }
		   }
		}
		catch(Exception e)
		{
			
		}
		
		for(int i=0;i<materialTexts.size();i++)
		     readMaterialFiles(materialTexts.get(i));
		
	}
	
	public void readMaterialFiles(String materialFiles)
	{
		FileChannel src = null,des=null;
			
		try
		{
			File file = new File(destinationPath+"/osg_obj");
			  
		     String desPath = materialFiles;
		  
		     srcFile = sourcePath.substring(0,sourcePath.lastIndexOf('/')+1);
		     
		     srcFile = srcFile+materialFiles;
		     
		     		     		  
		     src = new FileInputStream(srcFile).getChannel(); 
		     des = new FileOutputStream(destinationPath+"/osg_obj/"+desPath).getChannel();
		     
		     try {
			       des.transferFrom(src, 0, src.size());
		     } catch (IOException e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
		     }
		     
		}
		catch(Exception e)
		{
			
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
		
		ArrayList<String> imageFiles = new ArrayList<String>();
		try
		{
		   BufferedReader br = new BufferedReader(new FileReader(srcFile));
		   
		   String line;
		   while((line=br.readLine())!=null)
		   {
			   if(line.contains("map_Kd"))
			   {
				   
				   String[] lines = line.split(" ");
				   if(lines[lines.length-1].contains(".jpg")|| lines[lines.length-1].contains(".png"))
				   {
					   imageFiles.add(lines[lines.length-1]);
				   }
			   }
		   }
		}
		catch(Exception e)
		{
			
		}
		
		for(int i=0;i<imageFiles.size();i++)
			writeFile(imageFiles.get(i));
	}
	
	public void writeFile(String filename)
	{
		FileChannel src = null,des=null;
		try
		{
			File file = new File(destinationPath+"/osg_obj");
			  
		     String desPath = filename;
		  
		     String srcFile1 = sourcePath.substring(0,sourcePath.lastIndexOf('/')+1);
		     
		     
		     	     
		     srcFile1 = srcFile1+filename;
		     
		     
		     
		     		     		  
		     src = new FileInputStream(srcFile1).getChannel(); 
		     des = new FileOutputStream(destinationPath+"/osg_obj/"+desPath).getChannel();
		     
		     try {
			       des.transferFrom(src, 0, src.size());
		     } catch (IOException e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
		     }
		     
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
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
	
	public void readOsg()
	{
		ArrayList<String> materialTexts = new ArrayList<String>();
		try
		{
		   BufferedReader br = new BufferedReader(new FileReader(sourcePath));
		   String line;
		   while((line=br.readLine())!=null)
		   {
			   if(line.contains("file"))
			   {
				   String[] lines = line.split(" ");
				   if(lines[lines.length-1].contains(".jpg")||lines[lines.length-1].contains(".png"))
				   {
					   String fileName = lines[lines.length-1].substring(1, lines[lines.length-1].length()-1);
					   materialTexts.add(fileName);
				   }
			   }
		   }
		}
		catch(Exception e)
		{
			
		}
		
		for(int i=0;i<materialTexts.size();i++)
			writeFile(materialTexts.get(i));
	}
	
	public void addActions(String nextEventName)
	{
		action.put(nextEventName);
	}
	
	public JSONObject getModelEvent()
	{
		return events;
	}

}
