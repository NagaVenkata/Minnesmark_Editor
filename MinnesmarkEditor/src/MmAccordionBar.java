import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.Border;


public class MmAccordionBar extends JButton  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String buttonName;
	private int buttonWidth,buttonHeight;
	private Color backgroundColor,foregroundColor;
	private Color borderColor;
    public JButton button;	
    public JButton accordionButton;
	
	protected MmAccordionBar()
	{
		
	}
	
	public MmAccordionBar(String name,int width,int height)
	{
		super();
		URL url =  getClass().getResource("button.png");
		
		System.out.println(url.getPath());
		
		//String text = "<html><table  border='0' width='300' height='48' cellpadding='0' cellspacing='1' background='" + url.toExternalForm() + "'><tr><td width='46' height='46' align='center'>"+name+"</td></tr></table></html>";
	    this.button = new JButton();		
		//ImageIcon imageIcon = new ImageIcon("images/button.png");
		//this.setIcon(imageIcon);
	    System.out.println("width and height "+width+"  "+height);
		this.button.setPreferredSize(new Dimension(width,height));
	    this.button.setHorizontalAlignment(SwingConstants.LEFT);
	    //this.setBorderPainted(true);
	    //this.setFocusPainted(false);
	   // this.setForeground(Color.blue);
	    this.button.setBackground(new Color(96,138,188));
	    this.button.setOpaque(true);
	    	    
	    this.button.setLayout(new BorderLayout());
	    
	    
	    //this.add(new JLabel(name),BorderLayout.CENTER);
	    
	    //Color color = Color.darkGray;
	    //Border b = BorderFactory.createEmptyBorder(1, 1, 1, 1);
	    //Border buttonBorder = BorderFactory.createCompoundBorder(b, new MmAccordionBorder(color));
	      
	    	    
	    //this.setTransferHandler(new TransferHandler(name));
	    //this.addMouseListener(new buttonMouseListener());
	    
	    //JButton bt = new JButton("Click");
	    //this.button.setPreferredSize(new Dimension(25,50));
	    //this.button.add(bt);
	    
	    JPanel panel  = new JPanel();
	    panel.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
	    panel.setLayout(new GridBagLayout());	    
	    GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		accordionButton = new JButton("+");
		accordionButton.setPreferredSize(new Dimension(25,25));
		
		constraints.gridx=0;
		constraints.gridy=0;
		
		
	    panel.add(accordionButton,constraints);
	    
	    JLabel lb = new JLabel(name);
	    
        constraints.fill = GridBagConstraints.HORIZONTAL;
		
		constraints.gridx=1;
		constraints.gridy=0;
		constraints.weightx=0.5;
		constraints.gridwidth = 2;
		
		
		panel.add(lb,constraints);
	    
	    this.button.add(panel);
	    
	    
	    	 
	}
	
	//setters of all button properties
	
	void setButtonWidth(int width)
	{
		buttonWidth = width;
	}
	
	void setButtonHeight(int height)
	{
		buttonHeight = height;
	}
	
	void setBackgroundColor(Color color)
	{
		backgroundColor = color;
	}
	
	void setForegroundColor(Color color)
	{
		foregroundColor = color;
	}
	
	void setBorderColor(Color color)
	{
		borderColor = color;
	}
	
	//getters for all button properties
	
	int getButtonWidth()
	{
		return buttonWidth;
	}
	
	int getButtonHeight()
	{
		return buttonHeight;
	}
	
	Color getBackgroundColor()
	{
		return backgroundColor;
	}
	
	Color getForegroundColor()
	{
		return foregroundColor;
	}
	
	Color getBorderColor()
	{
		return borderColor;
	}
	
	public JButton getButton()
	{
		return this;
	}
	
	
	public void paint(Graphics g)
	{
		/*super.paint(g);
		//Graphics2D g2 = (Graphics2D) g;
		Image img = Toolkit.getDefaultToolkit().getImage("images/image_add.png");
		System.out.println(img);
		g.drawImage(img, 16, 16, null);
		g.finalize();
		this.button.paint(g);
		this.repaint();
		this.button.repaint();*/
	}

	
	
	public class buttonMouseListener extends MouseAdapter{
		  public void mousePressed(MouseEvent e)
		  {
			 //System.out.println(e.getSource()); 
		     JButton button1 = (JButton)e.getSource();
		     TransferHandler handler = button.getTransferHandler();
		     handler.exportAsDrag(button, e, TransferHandler.COPY);
		     
	
		  }
		  
		  public void mouseMove(MouseEvent e)
		  {
			  
		  }
	
		}
		
}
