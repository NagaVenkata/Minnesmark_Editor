import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MmAccordionTrialDialog extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JPanel panel;
	
	public MmAccordionPanel componentPanel;
	
	public JButton okButton;
	public JButton cancelButton;
	JPanel mainPanel;
	
	public MmAccordionTrialDialog()
	{
		super(new BorderLayout());
		
		JPanel panel = new JPanel();
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
				
		panel.add(okButton);
		panel.add(cancelButton);
		add(panel,BorderLayout.SOUTH);
		componentPanel = new MmAccordionPanel();
	}
	
	public MmAccordionTrialDialog(JFrame frame,List<String> labels,JPanel mainPanel)
	{
		super(new BorderLayout());
		this.frame=frame;
        this.frame.setLocation(200,100);
        
        this.mainPanel=mainPanel;
        
        componentPanel = new MmAccordionPanel("Trial Title","Trial Title",300,false);
        int j=0;
        for(int i=0;i<labels.size();i++)
        {	
		   
		   componentPanel.addText(labels.get(i), 0,i);
		   componentPanel.addCheckBox(labels.get(i),1,i);
		   		   
        }   
		
        componentPanel.addPanel();
		
		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());
		this.panel.add(componentPanel,BorderLayout.NORTH);
		
		add(panel,BorderLayout.NORTH);
		this.frame.setBackground(new Color(255,255,255,255));
		
		JPanel panel = new JPanel();
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
				
		panel.add(okButton);
		panel.add(cancelButton);
		add(panel,BorderLayout.SOUTH);
	}
	
	public void setFrame(JFrame frame)
	{
		this.frame = frame;
		this.frame.setLocation(200,100);
		this.frame.setBackground(new Color(255,255,255,255));
	}
	
	public void setLabels(List<String>labels)
	{
		componentPanel.removeAll();
        for(int i=0;i<labels.size();i++)
        {	
		   componentPanel.addText(labels.get(i), 0,i,false);
		   componentPanel.addCheckBox(labels.get(i),1,i,false);
		}
        
        componentPanel.validate();
        componentPanel.repaint();
        componentPanel.addPanel();
        
		
		this.panel = new JPanel();
		this.panel.removeAll();
		this.panel.setLayout(new BorderLayout());
		this.panel.add(componentPanel,BorderLayout.NORTH);
		
		this.panel.validate();
		this.panel.repaint();
		add(this.panel,BorderLayout.WEST);
		this.validate();
		this.repaint();
				
	}
		
}
