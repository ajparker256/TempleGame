package gui;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class GuiTexture {
	
	private int texture;
	private Vector2f position;
	private Vector2f scale;
	private Vector3f color;
	public GuiTexture(int texture, Vector2f position, Vector2f scale) {
		this.texture = texture;
		this.position = position;
		this.scale = scale;
		this.color=new Vector3f(1.0f,1.0f,1.0f);
	}
	public int getTexture() {
		return texture;
	}
	public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f pos) {
		position = pos;
	}
	public Vector2f getScale() {
		return scale;
	}
	public void setScale(Vector2f newScale) {
		scale = newScale;
	}
	public Vector3f getColor() {
		return this.color;
	}
	

}
