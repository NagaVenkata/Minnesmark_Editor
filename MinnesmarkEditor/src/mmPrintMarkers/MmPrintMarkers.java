package mmPrintMarkers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import mmLanguage.MmLanguage;
import mmStationEvents.MmGlobalMarkerEvents;

public class MmPrintMarkers implements Printable {
	
	ImageIcon img;
	MmGlobalMarkerEvents events;
	
	int language;
	
	

	public MmPrintMarkers(String imageName,MmGlobalMarkerEvents event,int lang)
	{
		img = new ImageIcon(imageName);
		//events = new MmGlobalMarkerEvents(); 
		
		events = event;
		
		this.language = lang;
		
		//JOptionPane.showMessageDialog(null, events.getMarker().getMarkerEvent());
	}

	@Override
	public int print(Graphics g, PageFormat print, int page)
			throws PrinterException {
		// TODO Auto-generated method stub
		
	/*	if (page > 0) {
            return NO_SUCH_PAGE;
       } */
    	
      
      
      
      

       // User (0,0) is typically outside the
       // imageable area, so we must translate
       // by the X and Y values in the PageFormat
       // to avoid clipping.
       Graphics2D g2d = (Graphics2D)g;
       g2d.setRenderingHint
       (RenderingHints.KEY_ANTIALIASING, 
         RenderingHints.VALUE_ANTIALIAS_ON);
       g2d.translate(print.getImageableX(), print.getImageableY());
       //g2d.drawString("Hi Printer", 100, 100);
       g2d.drawImage(img.getImage(),150,150,null);
       
       g2d.drawString(MmLanguage.language_print[language][0]+":"+"patt.marker"+Integer.toString(events.getMarkerIndex()+1), 100, img.getIconHeight()+450);
       g2d.drawString(MmLanguage.language_print[language][1]+":"+events.getMarkerMediaFiles(), 100, img.getIconHeight()+475);
       
       
       
      
       return PAGE_EXISTS;
       
		
		
	}
	
	
	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

}
