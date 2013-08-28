package mmFileManager;

import java.io.File;

import javax.swing.filechooser.FileFilter;


public class MmFileFilter extends FileFilter {

	
	
	public boolean accept(File file)
	{
		if(file.isDirectory())
		{
			return true;
		}
		
		String extension = MmFileUtils.getExtension(file);
	    if (extension != null) {
	        if (extension.equals(MmFileUtils.tiff) ||
	            extension.equals(MmFileUtils.tif) ||
	            extension.equals(MmFileUtils.gif) ||
	            extension.equals(MmFileUtils.jpeg) ||
	            extension.equals(MmFileUtils.jpg) ||
	            extension.equals(MmFileUtils.png)) {
	                return true;
	        } else {
	            return false;
	        }
	    }

	    return false;
		
	}
	
	public String getDescription() {
        return "*.tiff *.tif *.gif *.jpeg *.jpg *.png";
    }
	
	
}
