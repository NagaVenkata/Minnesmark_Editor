import java.awt.BorderLayout;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.*;
import mmMouseEvents.MmMouseEvents;




public class MmAccordionPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel label;
	public static JLabel uploadLandscape,uploadPortrait,imageLabel;
	GridLayout panelGridLayout;

	int mainPanelWidth;
	public JPanel componentsPanel;

	JPanel mainPanle;
	
	private GridBagConstraints constraints; 
	
	List<MmAccordionPanelRows> rows = new ArrayList<MmAccordionPanelRows>();
	
	public Component SelectedComponent;
	
	//MmMouseEvents mouseEvent;
	
	JButton minusButton,plusButton;
	public String barTitle; 
	
	int panelId=0;
	
	boolean showButtons;
	
	public MmAccordionTrialDialog eventsDialog;
	
	List<JCheckBox> checkBox = new ArrayList<JCheckBox>();
	List<JLabel> labels = new ArrayList<JLabel>();
	String componentName;
	
	public MmAccordionPanel()
	{
		super();
		mainPanelWidth = 300; 
		this.componentsPanel = new JPanel();
		this.showButtons = false;
		checkBox.clear();
		labels.clear();
		panelLayout();
	}
	
	public MmAccordionPanel(String barTitle,String title,int width,boolean showButtons)
	{
		super();
		
	    
		mainPanelWidth = width; 
		
		this.barTitle=barTitle;
		
		this.showButtons = showButtons;
		
		
		this.label = new JLabel(title+":");
				
		this.componentsPanel = new JPanel();
		checkBox.clear();
		
		panelLayout();
	}
	
	public void setPanel(JPanel panel)
	{
		this.mainPanle = panel;
	}
	
	public JPanel getPanel()
	{
		return this.mainPanle;
	}
	
	public void addText(String title, int gridx,int gridy)
	{
	
       	JLabel label = new JLabel(title+":");
		label.setName(title);
		componentName=title;
		this.constraints.anchor = GridBagConstraints.WEST;
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.constraints.gridx=gridx;
		this.constraints.gridy=gridy;
		this.constraints.weighty=0.25;
		this.constraints.insets = new Insets(5,0,0,0);
		this.rows.add(new MmAccordionPanelRows(label,gridx,gridy,this.componentsPanel.getComponentCount()));
		//mouseEvent = new MmMouseEvents(label,this.componentsPanel,1,rows);
		//label.addMouseListener(mouseEvent);
		this.componentsPanel.add(label,this.constraints);
		labels.add(label);
				
	}
	
	
	public void addText(String title, int gridx,int gridy,boolean visibility)
	{
	
       
		JLabel label = new JLabel(title+":");
		label.setName(title);
		componentName=title;
		this.constraints.anchor = GridBagConstraints.WEST;
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.constraints.gridx=gridx;
		this.constraints.gridy=gridy;
		this.constraints.weighty=0.25;
		this.constraints.insets = new Insets(5,0,0,0);
		this.rows.add(new MmAccordionPanelRows(label,gridx,gridy,this.componentsPanel.getComponentCount()));
		//mouseEvent = new MmMouseEvents(label,this.componentsPanel,1,rows);
		//label.addMouseListener(mouseEvent);
		this.componentsPanel.add(label,this.constraints);
		label.setVisible(visibility);
		labels.add(label);
			
	}
	
	public void addText(ImageIcon image, int gridx,int gridy)
	{
	
		JLabel label = new JLabel(image);
		label.setName(componentName);
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.constraints.gridx=gridx;
		this.constraints.gridy=gridy;
		this.constraints.weighty=0.25;
		//mouseEvent = new MmMouseEvents(label,this.componentsPanel,1,rows);
		this.componentsPanel.add(label,this.constraints);
		this.rows.add(new MmAccordionPanelRows(label,gridx,gridy,this.componentsPanel.getComponentCount()));	
	}
	
	
	public void addTextField(int gridx,int gridy)
	{
		
		JTextField text = new JTextField(5);
		text.setPreferredSize(new Dimension(10,25));
		text.setName(componentName);
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.constraints.weighty=0.0;
		this.constraints.gridx=gridx;
		this.constraints.gridy=gridy;
		this.constraints.gridwidth=2;
		//mouseEvent = new MmMouseEvents(text,this.componentsPanel,1,rows);
		this.componentsPanel.add(text,constraints);
		this.rows.add(new MmAccordionPanelRows(text,gridx,gridy,this.componentsPanel.getComponentCount()));	
	}
	
	public void addButton(String title, int gridx,int gridy)
	{
	
		JButton button = new JButton(title);
		button.setPreferredSize(new Dimension(5,15));
		button.setName(componentName);
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.constraints.gridx=gridx;
		this.constraints.gridy=gridy;
		this.componentsPanel.add(button,this.constraints);
		this.rows.add(new MmAccordionPanelRows(button,gridx,gridy,this.componentsPanel.getComponentCount()));		
			
	}
	
	public void addButton(ImageIcon image, int gridx,int gridy)
	{
	
		JButton button = new JButton(image);
		button.setName(componentName);	
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.constraints.gridx=gridx;
		this.constraints.gridy=gridy;
		this.componentsPanel.add(button,this.constraints);
		this.rows.add(new MmAccordionPanelRows(button,gridx,gridy,this.componentsPanel.getComponentCount()));	
	}
	
	public void addCheckBox(String checkBoxName, int gridx,int gridy)
	{
		
        JCheckBox checkbox = new JCheckBox();
		checkbox.setName(checkBoxName);
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.constraints.gridx=gridx;
		this.constraints.gridy=gridy;
		this.componentsPanel.add(checkbox,this.constraints);
		checkBox.add(checkbox);

		//this.rows.add(new MmAccordionPanelRows(button,gridx,gridy,this.componentsPanel.getComponentCount()));
	}
	
	public void addCheckBox(String checkBoxName, int gridx,int gridy,boolean visibility)
	{
		
        JCheckBox checkbox = new JCheckBox();
		checkbox.setName(checkBoxName);
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.constraints.gridx=gridx;
		this.constraints.gridy=gridy;
		this.componentsPanel.add(checkbox,this.constraints);
		checkbox.setVisible(visibility);
		checkBox.add(checkbox);

		//this.rows.add(new MmAccordionPanelRows(button,gridx,gridy,this.componentsPanel.getComponentCount()));
	}
	
	public List<JLabel> getLabels()
	{
		return labels;
	}
	
	public List<JCheckBox> getCheckBoxes()
	{
		return checkBox;
	}
	
	public void addPanel(int panelId)
	{
		add(this.componentsPanel,BorderLayout.NORTH);
		this.panelId=panelId;
	}
	
	public void addPanel()
	{
		/*this.componentsPanel.validate();
		this.componentsPanel.repaint();*/
		add(this.componentsPanel,BorderLayout.NORTH);
		
	}
	
	public void panelLayout()
	{
			
		this.setLayout(new BorderLayout());
		
		long  componentsPanelWidth = Math.round((90.0/100.0)*(long)mainPanelWidth*1.0);
		System.out.println("component width "+componentsPanelWidth);
		
		//this.componentsPanel.removeAll();
		//this.componentsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		GridBagLayout layout = new GridBagLayout();
		this.componentsPanel.setLayout(layout);
		this.componentsPanel.setPreferredSize(new Dimension((int)componentsPanelWidth,200));
	    this.constraints = new GridBagConstraints();
		
		
		/*constraints.fill = GridBagConstraints.HORIZONTAL;
		
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.weighty=0.5;
	    
		this.componentsPanel.add(this.label,constraints);
	 
		JTextField text = new JTextField(5);
		text.setPreferredSize(new Dimension(10,25));
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty=0.0;
		constraints.gridx=1;
		constraints.gridy=0;
		constraints.gridwidth=2;
		this.componentsPanel.add(text,constraints);
		
			
		JLabel backgroundLandscapeLabel = new JLabel("BackGroundImageLandscape:");
		backgroundLandscapeLabel.setFont(new Font("Serif",Font.PLAIN,15));
        constraints.fill = GridBagConstraints.HORIZONTAL;
		
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
		
		this.componentsPanel.add(backgroundLandscapeLabel,constraints);
		
        uploadLandscape = new JLabel(imageIcon);
		
		uploadLandscape.setFont(new Font("Serif",Font.PLAIN,15));
		uploadLandscape.setToolTipText("LandscapeImage");
		
        constraints.fill = GridBagConstraints.HORIZONTAL;
		
		constraints.gridx=2;
		constraints.gridy=1;
		constraints.weightx=0.0;
		constraints.weighty=0.0;
		
		this.componentsPanel.add(uploadLandscape,constraints);
		uploadLandscape.addMouseListener(new MmMouseEvents(uploadLandscape,MmAccordionPanel.this));
		
		JLabel backgroundPortraitLabel = new JLabel("BackGroundImagePortrait:");
		backgroundPortraitLabel.setFont(new Font("Serif",Font.PLAIN,15));
        constraints.fill = GridBagConstraints.HORIZONTAL;
		
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.weightx=0;
		constraints.weighty=0;
		
		this.componentsPanel.add(backgroundPortraitLabel,constraints);
		
        uploadPortrait = new JLabel(imageIcon);
		
        uploadPortrait.setFont(new Font("Serif",Font.PLAIN,15));
        
        uploadPortrait.setToolTipText("PortraitImage");
		
        constraints.fill = GridBagConstraints.HORIZONTAL;
		
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.weightx=0.0;
		constraints.weighty=0.0;
		
		this.componentsPanel.add(uploadPortrait,constraints);
		uploadPortrait.addMouseListener(new MmMouseEvents(uploadPortrait,MmAccordionPanel.this));
		
		JLabel urlLabel = new JLabel("URL");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.weightx=0.5;
		constraints.weighty=0.5;
	    
		this.componentsPanel.add(urlLabel,constraints);
	 
		JTextField urlText = new JTextField(5);
		urlText.setPreferredSize(new Dimension(10,25));
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		constraints.gridx=1;
		constraints.gridy=3;
		constraints.gridwidth=2;
		this.componentsPanel.add(urlText,constraints);*/
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		if(this.showButtons)
		{	
		   minusButton = new JButton("-");
		   minusButton.setPreferredSize(new Dimension(15,15));
		
           plusButton = new JButton("+");
		   plusButton.setPreferredSize(new Dimension(15,15));
		
		   buttonPanel.add(minusButton);
		
		   buttonPanel.add(plusButton);
           	
		   //minusButton.addActionListener(this);
		   //plusButton.addActionListener(this);
		}   
		
		/*plusButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				
				JFrame frame = new JFrame("Minesmark Events");
				frame.setPreferredSize(new Dimension(400,400));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setAlwaysOnTop(true);
				
				MmAddEventsDialog eventsDialog = new MmAddEventsDialog(frame);
				
				eventsDialog.setOpaque(false);
				frame.setContentPane(eventsDialog);
				frame.pack();
				frame.setVisible(true);
			}
			
			
			
		});*/
		
		//add(this.componentsPanel,BorderLayout.NORTH);
		add(buttonPanel,BorderLayout.SOUTH);

	}
	
	public void actionPerformed(ActionEvent e)
	{
		/*if(e.getSource()==minusButton)
		{
			mouseEvent.removeComponent();
		}
		
		if(e.getSource() == plusButton)
		{
			
			JFrame frame = new JFrame("Minesmark Events");
			frame.setPreferredSize(new Dimension(400,400));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setAlwaysOnTop(true);
			
			//MmAddEventsDialog eventsDialog = new MmAddEventsDialog(frame);
			MmAccordionTrialDialog eventsDialog =null;
			List<String> labels = new ArrayList<String>();
			if(this.panelId==1)			
			{
				labels.add("Trial Title");
				labels.add("Url");
				eventsDialog = new MmAccordionTrialDialog(frame,labels,this.mainPanle);
			}
			
			if(this.panelId==2)
			{
				labels.add("BackgroundImageLandscape");
				labels.add("BackgroundImagePortrait");
				labels.add("BackgroundAudio");
				labels.add("BackgroundVideo");
				eventsDialog = new MmAccordionTrialDialog(frame,labels,this.mainPanle);
			}
			
			eventsDialog.setOpaque(false);
			frame.setContentPane(eventsDialog);
			frame.pack();
			frame.setVisible(true);
		}*/
	}
	
	public void setLabel(JLabel label)
	{
		this.label = label;
	}
	
	
	
	public JLabel getLabel()
	{
	     return this.label;	
	}
	
	public void setWidth(int width)
	{
		mainPanelWidth = width;
	}
	
	public int getWidth()
	{
		return mainPanelWidth;
	}
	
	public JButton getMinusButton()
	{
		return minusButton;
	}
	
	public JButton getPlusButton()
	{
		return plusButton;
	}
	
	public void adjustLayout()
	{
		long  componentsPanelWidth = Math.round((90.0/100.0)*(long)mainPanelWidth*1.0);
		System.out.println("component width "+componentsPanelWidth);
		
		this.componentsPanel.setPreferredSize(new Dimension((int)componentsPanelWidth,200));
	}
}
