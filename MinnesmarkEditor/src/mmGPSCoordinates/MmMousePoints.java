package mmGPSCoordinates;

import java.awt.Point;
import java.awt.Rectangle;

import org.jdesktop.swingx.mapviewer.GeoPosition;

public class MmMousePoints {
	
	int mousePosx,mousePosy;
	private boolean linedrawn;
    private int clickCount=0;
    private int minx,miny,maxx,maxy;
	public MmMousePoints(int pointx,int pointy)
	{
		this.mousePosx=pointx;
		this.mousePosy = pointy;
	}
	
	public MmMousePoints(Point pnt)
	{
		this.mousePosx=pnt.x;
		this.mousePosy = pnt.y;
	}
	
	public void setPosx(int posx)
	{
		this.mousePosx = posx;
	}
	
	public void setPosy(int posy)
	{
		this.mousePosy = posy;
	}
	
	public void setPoint(Point pnt)
	{
		
		this.mousePosx=pnt.x;
		this.mousePosy = pnt.y;
	}
	
	public int getx()
	{
		return this.mousePosx;
				
	}
	
	public int gety()
	{
		return this.mousePosy;
				
	}
	
	public void setLineDrawn(boolean lineDrawn)
	{
		this.linedrawn = lineDrawn;
	}
	
	public boolean getLineDrawn()
	{
		return this.linedrawn;
	}
	
	public void setClickCount()
	{
		clickCount++;
	}
	
	public int getClickCount()
	{
		return clickCount;
	}
	
	public void drawRegion()
	{
		//draw a bounding box around the point added on the map
		this.minx=mousePosx;
		this.miny=mousePosy;
		
		this.maxx=this.mousePosx+24;
		this.maxy=this.mousePosy+15;
	}
	
		
	public boolean isPointInRegion(MmMousePoints pnt)
	{
		//boolean isPointPresent=false;
		int x = this.mousePosx-20;
		int y = this.mousePosy-25;
		
		//double dist = (((x-pnt.mousePosx)*(x-pnt.mousePosx))+((y-pnt.mousePosy)*(y-pnt.mousePosy)));
		
		Rectangle rect = new Rectangle(x,y,40,40);
		
		//System.out.println(" rectangle contains "+ rect.contains(pnt.getPoint()));
		
		if(rect.contains(pnt.getPoint()))
			return true;
		else
			return false;
		
		//System.out.println("distance  mouse "+" "+pnt.mousePosx+" "+pnt.mousePosy+" "+"  "+this.mousePosx+"  "+this.mousePosy+"  "+ Math.sqrt(dist));
		
	/*	if(Math.sqrt(dist)<40)
		{
			 return true;
			
		}
		else
			return false;
				
		//simple algorithm to find a mouse click in region
		
		//System.out.println("Entered data "+pnt.getx()+"  "+pnt.gety());
		//System.out.println("mouse pos "+(mousePosx-24)+" "+(mousePosy-48)+" "+maxx+" "+maxy);
	/*	
		if(pnt.mousePosx==mousePosx && pnt.mousePosy==mousePosy)
		{
			isPointPresent = true;
			return isPointPresent;
		}
		
		if((pnt.mousePosx>=(mousePosx-24) && pnt.mousePosy>=(mousePosy-48)) && (pnt.mousePosx<=maxx && pnt.mousePosy<=maxy))
		{
			isPointPresent = true;
			return isPointPresent;
		}
		else
		{
			return false;
		}
		
		/*double x = (mousePosx-pnt.mousePosx)*(mousePosx-pnt.mousePosx);
		double y = (mousePosy-pnt.mousePosy)*(mousePosy-pnt.mousePosy);
		
		double dist = Math.sqrt((x)+(y));
		
		//System.out.println("distance "+startPoint+"  "+endPoint+"  "+pnt+"  "+dist);
								
		if(dist<60)
		{
			//System.out.println("distance1 "+startPoint+"  "+endPoint+"  "+pnt+"  "+dist);
			return true;
		}
		else
			return false;*/
		
		//return isPointPresent;*/
			
	}
	
	
	
	public boolean isPointInRegion(GeoPosition pnt,GeoPosition pnt1)
	{
		double earthRadius = 3958.75; 
		
		
		
		double dLat = Math.toRadians((pnt.getLatitude()-pnt1.getLatitude()));
	    double dLng = Math.toRadians((pnt.getLongitude()-pnt1.getLongitude()));
	        
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
	               Math.cos(DegreesToRadians(pnt1.getLatitude())) *
	    		   Math.cos(DegreesToRadians(pnt.getLatitude())) *
	    		   Math.sin(dLng/2) * Math.sin(dLng/2);
	   
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;
	    
	    int meterConversion = 1609;
	    float pntDist = new Float(dist * meterConversion).floatValue();
	       
	     //System.out.println("pnt distance "+pntDist);  
	       	       
	     if(pntDist<=35)
	     {	   
	    	 return true;
	    	  
	     }	   
	     else
	     {	   
	    	 return false;
	     } 
	    
	}
	
	public double DegreesToRadians(double degrees)
	{
	    return degrees * Math.PI / 180;
	}
	
	public Point getPoint()
	{
		
		return new Point(mousePosx,mousePosy);
	}

}
