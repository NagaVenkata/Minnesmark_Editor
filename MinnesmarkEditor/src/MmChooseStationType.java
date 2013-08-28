import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.*;



public class MmChooseStationType extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JLabel> labels;
	private ArrayList<JRadioButton> buttons;
	JRadioButton option;
	JLabel label;
	ButtonGroup grp;
	
	public MmChooseStationType()
	{
		super();
		labels = new ArrayList<JLabel>();
		buttons = new ArrayList<JRadioButton>();
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
 		JLabel label = new JLabel("Vilken sorts punkt är det?");
 		constraints.fill = GridBagConstraints.HORIZONTAL;
 		constraints.gridx=0;
 		constraints.gridy=0;
 		
		panel.add(label,constraints);
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
 		constraints.gridx=0;
 		constraints.gridy=1;
 		
		option = new JRadioButton("Starten");
		panel.add(option,constraints);
		buttons.add(option);
		
				
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
 		constraints.gridx=0;
 		constraints.gridy=2;
 		
		option = new JRadioButton("Sväng");
		panel.add(option,constraints);
		buttons.add(option);
		
				
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
 		constraints.gridx=0;
 		constraints.gridy=3;
 		
		option = new JRadioButton("Station");
		panel.add(option,constraints);
		buttons.add(option);
		
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
 		constraints.gridx=0;
 		constraints.gridy=4;
 		
		option = new JRadioButton("Målet");
		panel.add(option,constraints);
		buttons.add(option);
		
		grp = new ButtonGroup();
		
		for(int i=0;i<buttons.size();i++)
			grp.add(buttons.get(i));
		
		add(panel);
		
	}
	
	public String getselectedButton()
	{
		String str="";
		
		for(int i=0;i<buttons.size();i++)
		{
			if(buttons.get(i).isSelected())
			{
				str= buttons.get(i).getText();
				break;
			}
		}
		
		return str;
	}
	
	

}
