package entities;

import org.lwjgl.util.vector.Vector3f;
//Where the view of the environment comes from
public class Camera {
	private static final int distance = 0;
	private Vector3f position; 
	private float pitch;
	private float yaw;
	private float roll;
	private float yOffset;
	private float zOffset;
	
//Moves the camera from keyboard, for testing only
	public Camera(){
		this.position =  new Vector3f(0,0,0);
	}


	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	public void setRoll(float roll) {
		this.roll = roll;
	}
	public Vector3f getPosition(){
		return position;
	}
	
	public float getPitch(){
		return pitch;
	}
	
	public float getYaw(){
		return yaw;
	}
	
	public float getRoll(){
		return roll;
	}
	public void increasePosition(float dx,float dy,float dz){
		this.position.x+=dx;
		this.position.y+=dy;
		this.position.z+=dz;
	}
	public Vector3f getRotation(){
		return new Vector3f(this.pitch,this.yaw,this.roll);
	}

	
}
