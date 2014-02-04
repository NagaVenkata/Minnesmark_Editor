package mmStationEvents;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MmMarkerUnDetectEvent {
	
	private String type,eventName;
    JSONObject eventType,attributes,actions;
    boolean detectMarker;
    
    JSONArray action = new JSONArray();
	
	public MmMarkerUnDetectEvent()
	{
		eventType = new JSONObject();
		attributes = new JSONObject();
		actions = new JSONObject();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void makeJSONObject()
	{
		
		try {
			  
			   eventType.put("name","MarkerUnDetect");
			   eventType.put("type","markerUnDetect");
			    
			   attributes.put("detectMarker", false);
			   
			   eventType.put("attributes", attributes);
			   
			   actions.put("marker-undetected", action);
               eventType.put("actions", actions);
			  
			   
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	
	public JSONObject getMarkerUnDetectEvent()
	{
		return eventType;
	}
	

}

