package mmGPSCoordinates;
import java.awt.Point;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class MmGPSCoordReader {
	
	public ArrayList<GeoCoords> geoCoordLst;
	
	public MmGPSCoordReader(String filename)
	{
	    try
	    {
	    	File gpsCoords =  new File(filename);
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
	    	Document db = dbBuilder.parse(gpsCoords);
	    	
	    	db.getDocumentElement().normalize();
	    	
	    	NodeList geoLst = db.getElementsByTagName("trkpt");
	    	
	    	//System.out.println(geoLst.getLength());
	    	
	    	geoCoordLst = new ArrayList<GeoCoords>();
	    	float lat,lon;
	    	for(int i=0;i<geoLst.getLength();i++)
	    	{
	    	    Node geoNode = geoLst.item(i);
	    	    
	    	    //System.out.println(geoNode.getAttributes().getNamedItem("lat").getNodeValue());
                //reads the lat and lon from the xml file as string and convert to float 	    	 
	    	    lat= Float.parseFloat(geoNode.getAttributes().getNamedItem("lat").getNodeValue());
	    	    lon= Float.parseFloat(geoNode.getAttributes().getNamedItem("lon").getNodeValue());
	    	    
	    	    //the lat and lon values are stored into an array of GeoCoords list as GeoCoords objects
	    	    geoCoordLst.add(new GeoCoords(lat,lon));
	    	}
	    	
	    }
	    catch(Exception e)
	    {
	    	
	    }
	    
	    //System.out.println(geoCoordLst.size());
	}
	
	public class GeoCoords {
		
		private float lat,lon;
		
		public GeoCoords(float x, float y)
		{
			lat = x;
			lon = y;
		}
		
		public void setLat(float x)
		{
			lat = x;
		}
		
		public void setLon(float y)
		{
			lon = y;
		}
		
		public float getLat()
		{
			return lat;
		}
		
		public float getLon()
		{
			return lon;
		}
	}

}
