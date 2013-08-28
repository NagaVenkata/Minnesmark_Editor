
public class MmMarkerEvents {
	
	private String markerName;
	private String modelName;
	
	public MmMarkerEvents(String markerName,String modelName)
	{
		this.markerName = markerName;
		this.modelName = modelName;
	}
	
	public void setMarkerName(String markerName)
	{
		this.markerName = markerName;
	}
	
	public void setModelName(String modelName)
	{
		this.modelName = modelName;
	}
	
	public String getMarkerName()
	{
		return this.markerName;
	}
	
	public String getModelName()
	{
		return this.modelName;
	}

}
