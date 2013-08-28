import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;


import javax.swing.border.AbstractBorder;

//class to redraw button border using java 2d graphics
public class MmAccordionBorder extends AbstractBorder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color borderColor;
	
	MmAccordionBorder(Color color)
	{
		borderColor = color;
	}
	
	public void setBorderColor(Color color)
	{
		borderColor = color;
	}
	
	public Color getBorderColor() 
	{
		return borderColor;
	}
	
	@Override
	
	public void paintBorder(Component c, Graphics g, int x,int y,int width,int height)
	{
		((Graphics2D) g).setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(borderColor);
		g.drawRoundRect(x, y, width, height, 2, 2);
	}
	
	@Override
	
	public Insets getBorderInsets(Component c)
	{
		return new Insets(2,2,2,2);
	}
	
	@Override
	
	public Insets getBorderInsets(Component c, Insets inset)
	{
		inset.left = 2;
		inset.top = 2;
		inset.right = 2;
		inset.bottom = 2;
		
		return inset;
	}
	
	

}
