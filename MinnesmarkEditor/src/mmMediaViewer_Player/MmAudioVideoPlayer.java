package mmMediaViewer_Player;


import java.io.*;

import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;

import javax.sound.sampled.*;
import javax.swing.JOptionPane;

import java.net.*;




public class MmAudioVideoPlayer {
	
	String audio_video_filename;
	
	Player player=null;
	
	URL url = null;
	AudioInputStream aiffAudioStream = null; 
	Clip aiffPlayer = null;
	
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
	
	public void audioPlay()
	{
		
		
		if(audio_video_filename.contains(".mp3"))
		{
			palyMP3AudioClip();
		}
		else
			playAiffAudioClip();
		
	}
	
	public void palyMP3AudioClip()
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
	}
	
	public void stopMP3AudioClip()
	{
		player.stop();
	}
	
	public void playAiffAudioClip()
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
	}
	
	public void  audioStop()
	{
		if(audio_video_filename.contains(".mp3"))
			stopMP3AudioClip();
		else
			stopAiffAudioClip();
	}
	
	

}
