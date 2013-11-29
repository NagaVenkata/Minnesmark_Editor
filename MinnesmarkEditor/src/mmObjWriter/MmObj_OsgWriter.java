package mmObjWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MmObj_OsgWriter {
	
	String filePath;
	
	public MmObj_OsgWriter(String path)
	{
		filePath=path;
	}
	
	public  static void WriteObj_OsgFile(String sourcePath,String destinationPath)
	{
		
		   FileChannel src = null,des=null;
		   try {
			    
			     JOptionPane.showMessageDialog(null, destinationPath);	
			   
			     File file = new File(destinationPath);
			  
			     File desFilePath = new File(sourcePath);
			     String desPath = desFilePath.getName();
			  
			     File desFile = new File(destinationPath+desPath);
			  
			     if(!desFile.exists())
			     { 	 
			        if(file.isDirectory())
			        {
				        src = new FileInputStream(sourcePath).getChannel(); 
			            des = new FileOutputStream(destinationPath+desPath).getChannel();
			        }
			        else
			        {
			           	
				       file.mkdir();
				       JOptionPane.showMessageDialog(null, file.isDirectory());
				       src = new FileInputStream(sourcePath).getChannel();
				       des = new FileOutputStream(destinationPath+desPath).getChannel();
			       }
			        
			            
			     
			       if(desPath.contains(".obj"))
			       {
				      readObj(sourcePath,destinationPath);
			       }
			  
			       if(desPath.contains(".osg"))
			       {
				      readOsg(sourcePath,destinationPath);
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
	
	public static void readObj(String sourcePath,String destinationPath)
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
		     readMaterialFiles(materialTexts.get(i),sourcePath,destinationPath);
		
	}
	
	public static void readMaterialFiles(String materialFiles,String sourcePath,String destinationPath)
	{
		FileChannel src = null,des=null;
		
		String srcFile = null;
			
		try
		{
			File file = new File(destinationPath);
			  
		     String desPath = materialFiles;
		  
		     srcFile = sourcePath.substring(0,sourcePath.lastIndexOf('/')+1);
		     
		     srcFile = srcFile+materialFiles;
		     
		     		     		  
		     src = new FileInputStream(srcFile).getChannel(); 
		     des = new FileOutputStream(destinationPath+desPath).getChannel();
		     
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
			writeFile(imageFiles.get(i),sourcePath,destinationPath);
	}
	
	public static void writeFile(String filename,String sourcePath,String destinationPath)
	{
		FileChannel src = null,des=null;
		try
		{
			File file = new File(destinationPath);
			  
		     String desPath = filename;
		  
		     String srcFile1 = sourcePath.substring(0,sourcePath.lastIndexOf('/')+1);
		     
		     
		     	     
		     srcFile1 = srcFile1+filename;
		     
		     
		     
		     		     		  
		     src = new FileInputStream(srcFile1).getChannel(); 
		     des = new FileOutputStream(destinationPath+desPath).getChannel();
		     
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
	
	public static void readOsg(String sourcePath,String destinationPath)
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
			writeFile(materialTexts.get(i),sourcePath,destinationPath);
	}

}
