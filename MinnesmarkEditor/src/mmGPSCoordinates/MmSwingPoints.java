package mmGPSCoordinates;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jdesktop.swingx.mapviewer.GeoPosition;






public class MmSwingPoints {
	
	Point swingPoint,startPoint,endPoint;
	
	int edgeIndex = -1;
	
	int stationIndex = -1;
	
	boolean isSwingPoint;
	
	
	
	GeoPosition startPointGeoPosition,endPointGeoPosition;
	
	
	public MmSwingPoints()
	{
		
	}
	
	public MmSwingPoints(Point point)
	{
		swingPoint = point;
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
		
		Point pnt1 = new Point(pnt.x,pnt.y+1);
		
		if(startPoint.equals(pnt)||startPoint.equals(pnt1)||startPoint.equals(new Point(pnt.x,pnt.y-1)))
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
		
		return -1;
	}
	
	/*public void setEdgeIndex()
	{
		this.edgeIndex = -1;
	}*/
	
	public int getEdgeIndex()
	{
		return this.edgeIndex;
	}
	
	public boolean isSwingPointPresent(Point pnt)
	{
		/*draw a imaginary circle with radius of five around the swing point
		 * then calculate the distance between the current point and the swing point
		 * the should be less than squre of radius. 
		 */
		double x = (endPoint.x-pnt.x)*(endPoint.x-pnt.x);
		double y = (endPoint.y-pnt.y)*(endPoint.y-pnt.y);
		
		double dist = Math.sqrt((x*x)+(y*y));
		
		//System.out.println("distance "+startPoint+"  "+endPoint+"  "+pnt+"  "+dist);
								
		if(dist<25)
		{
			System.out.println("distance1 "+startPoint+"  "+endPoint+"  "+pnt+"  "+dist);
			return true;
		}
		
		return false;
	}
	
	

}
