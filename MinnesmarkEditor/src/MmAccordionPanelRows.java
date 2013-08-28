import java.awt.Component;
import java.util.*;


public class MmAccordionPanelRows {
	
	private int gridx,gridy,componentValue;
	Component component;
	boolean selected;
	//private List<MmAccordionPanelRows> rowsColumns = new ArrayList<MmAccordionPanelRows>();
	
	public MmAccordionPanelRows(Component component,int gridx,int gridy,int componentValue)
	{
		this.component = component;
		this.gridx=gridx;
		this.gridy=gridy;
		this.componentValue=componentValue;
		
	}
	
	public Component getComponent()
	{
		return this.component;
	}
	
	public int getGridy()
	{
		return this.gridy;
	}
	
	public int getComponentValue()
	{
		return this.componentValue;
	}
	
	public void setComponentSelected(boolean selected)
	{
		this.selected=selected;
	}
	
	public boolean getSelected()
	{
		return this.selected;
	}
	
	/*public List<MmAccordionPanelRows> getRowsColums()
	{
		//return this.rowsColumns;
	}*/
	

}
