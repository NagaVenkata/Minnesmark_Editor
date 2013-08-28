package mmAccordionMenu;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
public class MmAccordionItemPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel itemPanel,menuPanel,buttonPanel; 
	
	
	public MmAccordionItemPanel()
	{
		
		this.itemPanel = new JPanel();
		this.menuPanel = new JPanel();
		this.buttonPanel = new JPanel();
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.itemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(this.itemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addContainerGap())
        );
		
		
		this.setOpaque(false);
	    menuPanel.setLayout(new GridLayout(0,1,5,0));
		itemPanel.setLayout(new BorderLayout());
		//itemPanel.setPreferredSize(new Dimension(275,250));
		buttonPanel.setLayout(new GridLayout(0,2,0,2));
		//buttonPanel.setPreferredSize(new Dimension(100,50));
	}
	
	public void addMenuItem(JComponent component)
	{
		
		menuPanel.add(component);
	}
	
	public void addButtonPanel(JComponent component)
	{
		buttonPanel.add(component);
	}
	
	
	public void addMenuItems(JComponent component)
	{
		
		menuPanel.add(component);
	}
	
	public void addPanel()
	{
		itemPanel.add(menuPanel);
		itemPanel.add(buttonPanel,BorderLayout.SOUTH);
	}
	
	public void addItemPanel()
	{
		itemPanel.add(buttonPanel,BorderLayout.SOUTH);
	}
	
	public void addMenuItem(MmAccordionItem item)
	{
		itemPanel.add(item);
	}
	
	public JPanel getItemPanel()
	{
		return itemPanel;
	}

}
