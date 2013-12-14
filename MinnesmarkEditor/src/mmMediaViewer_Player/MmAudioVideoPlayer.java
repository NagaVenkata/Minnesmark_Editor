package mmMediaViewer_Player;


import java.io.*;

import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;

import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.*;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

import java.net.*;




public class MmAudioVideoPlayer {
	
	String audio_video_filename; 
	
	Player player=null;
	
	URL url = null;
	AudioInputStream aiffAudioStream = null; 
	Clip aiffPlayer = null;
	
	EmbeddedMediaPlayerComponent mediaPlayerComponent = null;
	
	JDialog mediaDialog = null;
	
	public MmAudioVideoPlayer()
	{
		
	}
	
	public MmAudioVideoPlayer(String fileName)
	{
		audio_video_filename = fileName;
		
	}
	
	public void setAudioFile(String fileName)
	{
		audio_video_filename = fileName;
	}
	
	public void audioPlay(JFrame window)
	{
		if(mediaDialog==null)		
		   mediaDialog = new JDialog(window);
		
		mediaDialog.setTitle("Media Player");

		if(mediaPlayerComponent==null)
		   mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

		mediaDialog.setContentPane(mediaPlayerComponent);

		mediaDialog.setLocation(100, 100);
		mediaDialog.setSize(100, 100);
		mediaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		mediaDialog.setVisible(true);

        mediaPlayerComponent.getMediaPlayer().playMedia(audio_video_filename);

        
		
	}
	
	
	public void videoPlay(JFrame window)
	{
		try
		{
		    if(mediaDialog==null)		
			    mediaDialog = new JDialog(window);
		
		    mediaDialog.setTitle("Media Player");
		    
		    

		   if(mediaPlayerComponent==null)
		      mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		   
		  
		   
		   //System.out.println("component "+mediaPlayerComponent);

		   mediaDialog.setContentPane(mediaPlayerComponent);

		   mediaDialog.setLocation(100, 100);
		   mediaDialog.setSize(400, 400);
		   mediaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 	   mediaDialog.setVisible(true);
		
		

          mediaPlayerComponent.getMediaPlayer().playMedia(audio_video_filename);
	   }   
       catch(Exception e)
       {
    	   JOptionPane.showMessageDialog(null, e);
       }
        

        
		
	}
	
	/*public void palyMP3AudioClip()
	{
		
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);
		PlugInManager.addPlugIn(
				"com.sun.media.codec.audio.mp3.JavaDecoder",
				new Format[]{input1, input2},
				new Format[]{output},
				PlugInManager.CODEC
			);
		
		try{
			  
			  player = Manager.createPlayer(new MediaLocator(new File(audio_video_filename).toURI().toURL()));
			  player.start();
			  
		}
		catch(Exception e)
		{
			
		}
	}*/
	
	public void stopMP3AudioClip()
	{
		mediaPlayerComponent.getMediaPlayer().stop();
		
		mediaDialog.setVisible(false);
	}
	
	/*public void playAiffAudioClip()
	{
		
		try
		{
		   	
		   aiffAudioStream = AudioSystem.getAudioInputStream(new File(audio_video_filename));
		   aiffPlayer = AudioSystem.getClip();
		   aiffPlayer.open(aiffAudioStream);
		   aiffPlayer.start();
		   
		}
		catch(Exception e)
		{
			System.out.println("exception "+e);
		}
	}
	
	public void stopAiffAudioClip()
	{
		aiffPlayer.stop();
	}*/
	
	public void  audioStop()
	{
        mediaPlayerComponent.getMediaPlayer().stop();
		
		mediaDialog.setVisible(false);
		
	}
	
	

}
