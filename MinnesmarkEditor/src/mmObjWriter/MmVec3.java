package mmObjWriter;

public class MmVec3 {
	
	float x,y,z;
	
	public MmVec3()
	{
		x=0;
		y=0;
		z=0;
	}
	
	public MmVec3(float x1,float y1,float z1)
	{
		x=x1;
		y=y1;
		z=z1;
	}
	
	public void set(float x1,float y1,float z1)
	{
		x=x1;
		y=y1;
		z=z1;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	

}
