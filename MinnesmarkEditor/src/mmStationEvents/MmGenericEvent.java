package mmStationEvents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MmGenericEvent {
	
	private String type,eventName;
	
	JSONObject events,actions;
	
	
	JSONArray action = new JSONArray();
	
	public MmGenericEvent()
	{
		events = new JSONObject();
		actions = new JSONObject();
	}
	
	public MmGenericEvent(String name)
	{
		this.eventName = name; 
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
		
	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	
	public String getEventName()
	{
		return this.eventName;
	}
	
		
	public void makeJSONObject()
	{
		try {
			events.put("name", this.eventName);
			events.put("type", "generic");
						
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addActions(String nextEventName)
	{
		action.put(nextEventName);
	}

	public void JSONActions()
	{
		
		try 
		{
			actions.put("application-launched", action);
			events.put("actions",actions);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	
	public JSONObject getAudioEvent()
	{
		return events;
	}

}
