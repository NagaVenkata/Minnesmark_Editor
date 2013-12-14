package mmAccordionMenu;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.MouseAdapter;

import javax.swing.*;

public abstract class MmAccordionItem extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ImageIcon icon;
	ImageIcon selectedIcon;
	public boolean selected;
	
	public MmAccordionItem(String text)
	{
		super(text);
		addMouseListener(AccordionMenuMouseIcon());
		setIcon(getMenuIcon());
		icon=getMenuIcon();
		selectedIcon = getSelectedIcon();
		setSelectedMenuIcon();
		setOpaque(false);
	}
	
	public abstract MouseAdapter AccordionMenuMouseIcon();
	
	public abstract Paint getBackgroundPaint();
	
	public abstract ImageIcon getMenuImage();
	
	public abstract ImageIcon getMenuIcon();
	
	public abstract ImageIcon getSelectedIcon();
	
	public void setLanguageText(String text)
	{
		this.setText(text);
	}
	
	
	@Override
    protected void paintComponent(Graphics g) {
       if (getBackgroundPaint() != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(getBackgroundPaint());
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
        super.paintComponent(g);
    }
	
	public void setSelectedMenuIcon() {
        
		if(!selected)
		{
			setIcon(icon);
		}
		else
		{
			setIcon(selectedIcon);
		}
    }
	
	

}
