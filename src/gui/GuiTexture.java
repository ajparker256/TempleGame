package gui;

import org.lwjgl.util.vector.Vector2f;

public class GuiTexture {
	
	private int texture;
	private Vector2f position;
	private Vector2f scale;
	public GuiTexture(int texture, Vector2f position, Vector2f scale) {
		this.texture = texture;
		this.position = position;
		this.scale = scale;
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
	

}
