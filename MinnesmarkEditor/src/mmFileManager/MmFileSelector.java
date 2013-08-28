package mmFileManager;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MmFileSelector extends JFileChooser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String fileName;
	
	int option;
	
	public MmFileSelector()
	{
		super();
	}
	
		
	public void showOpenFileDialog()
	{
		
		option = showOpenDialog(this);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & GIF Images", "jpg", "gif");
		//setFileFilter(filter);
		    
		//FileFilter fileFilter = new MmFileFilter();
		addChoosableFileFilter(filter);
		setFileFilter(filter);
	    //this.setFileSelectionMode(JFileChooser.FILES_ONLY);
		setAcceptAllFileFilterUsed(false);
		
	
		
	}
	
	public void showSaveFileDialog()
	{	
		option = showSaveDialog(this);
		addChoosableFileFilter(new MmFileFilter());
		
	}
	
	public int getOption()
	{
		return option;
	}
	
	
	
	public String getFileName()
	{
		return fileName;
	}
	
	

}
