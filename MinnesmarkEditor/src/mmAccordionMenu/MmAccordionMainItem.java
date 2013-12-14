package mmAccordionMenu;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import mmEvents.MmAddGlobalMarkers;
import mmStationEvents.*;

import javax.swing.*;

public class MmAccordionMainItem extends MmAccordionItem {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MmAccordionItemPanel itemPanel;
	private List<MmAccordionMenuItems> menuItems;
	
	MmAddGlobalMarkers markerEvents;

	public MmAccordionMainItem(String text)
	{
		super(text);
        itemPanel = new MmAccordionItemPanel();
        menuItems = new ArrayList<MmAccordionMenuItems>();
        selected = false;
	}
	
	public Paint getBackgroundPaint()
	{
	    Color color1,color2;
	    color1= getBackground();
	    color2=Color.white;
		return new GradientPaint(0, 0, color1, 0, getHeight(), color2);
	}
	
	public ImageIcon getMenuImage()
	{
		return null;
	}
	
		
	public ImageIcon getMenuIcon()
	{
		//JOptionPane.showMessageDialog(null, System.getProperty("user.dir"));
		return new ImageIcon(getClass().getResource("/blu_arrow_right.png"));
			
	}
	
	public ImageIcon getSelectedIcon()
	{
		return new ImageIcon(getClass().getResource("/blu_arrow_down.png"));
	}
	
	public void setLanguageText(String text)
	{
		super.setLanguageText(text);
	}
	
	
	
	public MouseAdapter AccordionMenuMouseIcon()
	{
		return new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e)
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			
		};
	}
	
	public MmAccordionItemPanel getItemPanel()
	{
		return itemPanel;
	}
	
	public List<MmAccordionMenuItems> getMenuItem()
	{
		return menuItems;
	}

}
