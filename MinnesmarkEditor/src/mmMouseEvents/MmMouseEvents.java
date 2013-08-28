package mmMouseEvents;
import javax.swing.*;


import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.*;



public class MmMouseEvents implements MouseListener {

	JLabel label;
	JFileChooser fileChooser;
	JPanel panel;
	int selectType=0;
	private Component componentSelected;
	//List<MmAccordionPanelRows> rows = new ArrayList<MmAccordionPanelRows>();
	
	public MmMouseEvents()
	{
		fileChooser = new JFileChooser();
		
	}
	
	public void addMouseEvent(JLabel label,JPanel panel,int selectType)
	{
		this.label = new JLabel();
		this.label = label;
		this.panel = panel;
		this.selectType=selectType;
		
	}
	
	
	public MmMouseEvents(JLabel label,int selectType)
	{
		this.label = new JLabel();
		this.label = label;
		this.selectType=selectType;		
	}
	
	public MmMouseEvents(JLabel label,JPanel panel,int selectType)
	{
		this.label = new JLabel();
		this.label = label; 
		fileChooser = new JFileChooser();
		this.panel = panel;
		this.selectType=selectType;
	}
	
	/*public MmMouseEvents(JLabel label,JPanel panel,int selectType,List<MmAccordionPanelRows> rows)
	{
		this.label = new JLabel();
		this.label = label; 
		this.panel = panel;
		this.selectType=selectType;
		this.rows = rows;
	}*/
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(this.selectType==1)
		{
			/*for(int i=0;i<rows.size();i++)
			{
				if(rows.get(i).getComponent().equals(this.label))
				{
					//this.label.setForeground(Color.BLUE);
					//this.rows.get(i).setComponentSelected(true);
					for(int j=0;j<rows.size();j++)
					{
						if(rows.get(i).getGridy()==rows.get(j).getGridy())
						{
							rows.get(j).getComponent().setForeground(Color.BLUE);
							this.rows.get(j).setComponentSelected(true);
						}
					}
				}
			}*/
			
		}
		
		if(this.selectType==2)
		{	
		   int val = fileChooser.showDialog(this.panel, "open");
	       if (val == JFileChooser.APPROVE_OPTION) {
	    	   File file = fileChooser.getSelectedFile();
	  	       this.label.setText(file.getName());
	       }
	       
	       
	    }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		this.label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//this.label.setForeground(Color.darkGray);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//this.label.setForeground(Color.black);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	public Component getComponent()
	{
		return this.componentSelected;
	}
	
	public void removeComponent()
	{
		
		/*for(int i=0;i<rows.size();i++)
		{
			if(rows.get(i).getSelected())
			{
				this.panel.remove(rows.get(i).getComponent());
			}
		}
		
		this.panel.validate();
		List<MmAccordionPanelRows> rowsColumns = new ArrayList<MmAccordionPanelRows>();
		
		rowsColumns = rows;
		
		/*for(int i=0;i<rowsColumns.size();i++)
		{
			for(int j=0;j<rows.size();j++)
			{	
			   if(rowsColumns.get(i).getSelected()==rowsColumns.get(j).getSelected())
			   {
				  rows.remove(j);
			
			   }
			}   
		}
		
		
		
		System.out.println(rows.size());*/
	}

}


/*FileChannel src = null,des=null;
try {
	  src = new FileInputStream(file).getChannel();
	  des = new FileOutputStream("/Users/Umapathi/Desktop/"+file.getName()).getChannel();
	  try {
		des.transferFrom(src, 0, src.size());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

finally
{
	try {
		src.close();
		des.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}*/