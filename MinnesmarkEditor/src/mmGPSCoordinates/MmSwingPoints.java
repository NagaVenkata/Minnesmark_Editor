package mmGPSCoordinates;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jdesktop.swingx.mapviewer.GeoPosition;






public class MmSwingPoints {
	
	Point swingPoint,startPoint,endPoint;
	
	int edgeIndex = -1;
	
	int stationIndex = -1;
	
	boolean isSwingPoint;
	
	int startPointRadius=0,endPointRadius=0;
	
	
	
	GeoPosition startPointGeoPosition,endPointGeoPosition;
	
	
	public MmSwingPoints()
	{
		startPointRadius=0;
		endPointRadius=0;
	}
	
	public MmSwingPoints(Point point)
	{
		swingPoint = point;
		startPointRadius=0;
		endPointRadius=0;
		
	}
	
	public void setStartPoint(Point point)
	{
		startPoint = point;
	}
	
	public void setEndPoint(Point point)
	{
		endPoint = point;
	}
	
	public Point getStartPoint()
	{
		return startPoint;
	}
	
	public Point getEndPoint()
	{
		return endPoint;
	}
	
	public void setSwingPoint(Point pnt)
	{
		swingPoint = pnt;
	}
	
	public Point getSwingPoint()
	{
		return swingPoint;
	}
	
	/*
	 * set true if the current point is swing point
	 */
	public void setStartPointType(boolean pointType)
	{
		isSwingPoint = pointType;
	}
	
	public void setEndPointType(boolean pointType)
	{
		isSwingPoint = pointType;
	}
	
	/*
	 * return point type, true if a point is swing point  
	 */
	
	public boolean getStartPointType()
	{
		return isSwingPoint;
	}
	
	public boolean getEndPointType()
	{
		return isSwingPoint;
	}
	
	
	
	public void setStationIndex(int index)
	{
		stationIndex = index;
	}
	
	public int getStationIndex()
	{
		return stationIndex;
	}
	
	public void setStartGeoPosition(GeoPosition point)
	{
		startPointGeoPosition = point;
	}
	
	public void setEndGeoPosition(GeoPosition point)
	{
		endPointGeoPosition = point;
	}
	
	
	public GeoPosition getStartGeoPosition()
	{
		return startPointGeoPosition;
	}
	
	public GeoPosition getEndGeoPosition()
	{
		return endPointGeoPosition;
	}
	
	public int getStartPointRadius() {
		return startPointRadius;
	}

	public void setStartPointRadius(int radius) {
		this.startPointRadius = radius;
	}
	
	public int getEndPointRadius() {
		return endPointRadius;
	}

	public void setEndPointRadius(int radius) {
		this.endPointRadius = radius;
	}

	
	public void print()
	{
		//System.out.println("Start point "+startPoint+"   "+endPoint+"  "+stationIndex);
	}
	
	public boolean isPointBetweenStations(Point point,Point point2)
	{
		if(startPoint.equals(point) && endPoint.equals(point2))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean isPointStation()
	{
		return isSwingPoint;
	}
	
	public int getStationIndex(GeoPosition point)
	{
		//JOptionPane.showMessageDialog(null, " point in swing  "+point+"   "+startPointGeoPosition+"  "+endPointGeoPosition+"  "+stationIndex);
		if(startPointGeoPosition.equals(point))
		{
			//System.out.println("start data "+point);
			return stationIndex;
		}
		
		if(endPointGeoPosition.equals(point))
		{
			//System.out.println("end data "+point);
			return stationIndex;
		}
		
		return -1;
	}
	
	public int isPointPresent(ArrayList<MmMousePoints> pnts)
	{
		
		Point pnt1 = null;
		
		for(int i=0;i<pnts.size();i++)
		{
			pnt1 = new Point(pnts.get(i).getx(),pnts.get(i).gety()+1);
					
			if(startPoint.equals(pnts.get(i).getPoint())||startPoint.equals(pnt1))
			{
				//System.out.println("mouse pnts "+pnts.get(i).getPoint()+"   "+startPoint+"  "+endPoint);
				stationIndex = i+1;
				isSwingPoint = false;
				return stationIndex;
				
			}
			
			if(endPoint.equals(pnts.get(i).getPoint())||endPoint.equals(pnt1))
			{
				
				stationIndex = i+1;
				isSwingPoint = false;
				return stationIndex;
				
			}
			
		}
       
		isSwingPoint = true;
		return -1;
	}
	
	
	
	
	
	public int getEdgeIndex(Point pnt)
	{
		//System.out.println("pnt "+startPoint+"  "+endPoint+"  "+pnt);
		
		/*Point pnt1 = new Point(pnt.x,pnt.y+1);
		
		/*if(startPoint.equals(pnt)||startPoint.equals(pnt1)||startPoint.equals(new Point(pnt.x,pnt.y-1)))
		{
			this.edgeIndex=0;
			//System.out.println("Edge index "+edgeIndex);
			return this.edgeIndex;
			
		}
		
		pnt1 = new Point(pnt.x,pnt.y+1);
		if(endPoint.equals(pnt)||endPoint.equals(pnt1)||startPoint.equals(new Point(pnt.x,pnt.y-1)))
		{
			
			this.edgeIndex=1;
			return this.edgeIndex;
		}
		
		if((pnt.x>=(startPoint.x-24) && pnt.y>=(startPoint.y-48))&&(pnt.x<=(startPoint.x+24) && pnt.y<=(startPoint.y+15)))
		{
			this.edgeIndex=0;
			//System.out.println("Edge index "+edgeIndex);
			return this.edgeIndex;
			
		}
		
		pnt1 = new Point(pnt.x,pnt.y+1);
		if((pnt.x>=(endPoint.x-24) && pnt.y>=(endPoint.y-48))&&(pnt.x<=(endPoint.x+24) && pnt.y<=(endPoint.y+15)))
		{
			
			this.edgeIndex=1;
			return this.edgeIndex;
		}*/
		
		if(getStartPointRadius()==10)
		{	
			Rectangle rect = new Rectangle(startPoint.x-20,startPoint.y-25,40,40);
			
			//System.out.println(" rectangle contains "+ rect.contains(pnt));
			
			if(rect.contains(pnt))
			{	
				this.edgeIndex=0;
				return edgeIndex;
			}	
			
		   /*double dist = (((startPoint.x-20)-pnt.x)*(((startPoint.x-20)-pnt.x))+(((startPoint.y-25)-pnt.y)*((startPoint.y-25)-pnt.y)));
		   if(Math.sqrt(dist)<40)
		   {
			    //System.out.println("distance startPoint "+ dist);
				this.edgeIndex=0;
				return edgeIndex;
		   }*/
		}
		
		if(getEndPointRadius()==10)
		{	
			
            Rectangle rect = new Rectangle(endPoint.x-20,endPoint.y-25,40,40);
			
			//System.out.println(" rectangle contains "+ rect.contains(pnt));
			
			if(rect.contains(pnt))
			{	
				this.edgeIndex=1;
				return edgeIndex;
			}
		   /*double dist1 = (((endPoint.x-20)-pnt.x)*(((endPoint.x-20)-pnt.x))+(((endPoint.y-25)-pnt.y)*((endPoint.y-25)-pnt.y)));
		
		   //System.out.println("distance endPoint "+ dist1);
		   if(Math.sqrt(dist1)<40)
		   {
			   this.edgeIndex=1;
			   return edgeIndex;
		   }*/
		}
		return -1;
	}
	
	/*public int getEdgeIndex(GeoPosition pnt)
	{
		
		if(isPointInRegion(this.startPointGeoPosition,pnt))
		{
			this.edgeIndex=0;
			System.out.println("Edge index "+edgeIndex);
			return this.edgeIndex;
			
		}
		else if(isPointInRegion(this.endPointGeoPosition,pnt))
		{
			
			this.edgeIndex=1;
			return this.edgeIndex;
		}
		
		return -1;
	}*/
	
	/*public void setEdgeIndex()
	{
		this.edgeIndex = -1;
	}*/
	
	public int getEdgeIndex()
	{
		return this.edgeIndex;
	}
	
	public boolean isPointInRegion(GeoPosition pnt,GeoPosition pnt1)
	{
		double earthRadius = 3958.75; 
		
		
		
		double dLat = Math.toRadians((pnt.getLatitude()-pnt1.getLatitude()));
	    double dLng = Math.toRadians((pnt.getLongitude()-pnt1.getLongitude()));
	        
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
	               Math.cos(DegreesToRadians(pnt.getLatitude())) *
	    		   Math.cos(DegreesToRadians(pnt1.getLatitude())) *
	    		   Math.sin(dLng/2) * Math.sin(dLng/2);
	   
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;
	    
	    int meterConversion = 1609;
	    float pntDist = new Float(dist * meterConversion).floatValue();
	       
	     //System.out.println("pnt distance "+pntDist);  
	       	       
	     if(pntDist<=40)
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
	
	public boolean isSwingPointPresent(Point pnt)
	{
		/*draw a imaginary circle with radius of five around the swing point
		 * then calculate the distance between the current point and the swing point
		 * the should be less than squre of radius. 
		 */
	    //System.out.println("type "+getEndPointType()+"  "+getEndPointRadius());
		if(getEndPointRadius()!=10)
		{	
		  	
			
			Rectangle rect = new Rectangle(endPoint.x-5,endPoint.y-5,10,10);
			
			//System.out.println("data "+rect.contains(pnt));
			
			if(rect.contains(pnt))
			{
				return true;
			}
			
		   /*double x = (endPoint.x-pnt.x)*(endPoint.x-pnt.x);
		   double y = (endPoint.y-pnt.y)*(endPoint.y-pnt.y);
		
		   double dist = Math.sqrt((x)+(y));
		
		   //System.out.println("distance "+startPoint+"  "+endPoint+"  "+pnt+"  "+dist);
								
		   if(dist<25)
		   {
			  //System.out.println("distance1 "+startPoint+"  "+endPoint+"  "+pnt+"  "+dist);
			  return true;
		   }
		   else
			   return false;*/
		}
		
		return false;
	}
	
	public boolean isPointPresent(Point pnt)
	{
		/*draw a imaginary circle with radius of five around the swing point
		 * then calculate the distance between the current point and the swing point
		 * the should be less than squre of radius. 
		 */
	    //System.out.println("type "+getEndPointType()+"  "+getEndPointRadius());
		
		if(getEndPointRadius()==10)
		{	
		  	
			
			Rectangle rect = new Rectangle(endPoint.x-20,endPoint.y-25,40,40);
			
			//System.out.println("data "+rect.contains(pnt));
			
			if(rect.contains(pnt))
			{
				return true;
			}
			
		}
		else if(getEndPointRadius()!=10)
		{	
		  	
			
			Rectangle rect1 = new Rectangle(endPoint.x-5,endPoint.y-5,10,10);
			
			//System.out.println("data "+rect.contains(pnt));
			
			if(rect1.contains(pnt))
			{
				return true;
			}
			
		}	   	
		
		return false;
	}
	
	
	

}
