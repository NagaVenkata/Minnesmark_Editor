package mmFileManager;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileType;

import net.sf.vfsjfilechooser.filechooser.AbstractVFSFileFilter;

public class MmFileFilter extends AbstractVFSFileFilter {

	String extension;
	
	@Override
	public boolean accept(FileObject file) {
		// TODO Auto-generated method stub
		
		try
		{
		   if (file.getType()==FileType.FOLDER) {
	            return true;
	       }
		}
		catch(Exception e)
		{
			
		}
		
		extension = MmFileUtils.getExtension(file);
		
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

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		
		
		return new String("*.jpg,*.bmp *.jpeg *.tif *.tiff *.obj *.osg *.mp3 *.m4a *.m4a *.patt");
	}

	
	
	
	/*public boolean accept(File file)
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
    }*/
	
	
}
