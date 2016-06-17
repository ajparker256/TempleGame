package buttons;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;

public class Button {
	//This is a list of the top left corners of hitboxes
		private Vector2f hitBoxesTL;
		
		//This is a list of the Bottom Right corners of the hitboxes
		private Vector2f hitBoxesBR;
		
		public Button(Vector2f TopLeft, Vector2f BottomRight) {
			hitBoxesTL = TopLeft;
			hitBoxesBR = BottomRight;
		}
		
		public void setTL(Vector2f tl) {
			hitBoxesTL = tl;
		}
		
		public void setBR(Vector2f br) {
			hitBoxesBR = br;
		}
				
		public Vector2f getTL() {
			return hitBoxesTL;
		}
		
		public Vector2f getBR() {
			return hitBoxesBR;
		}
		
		//Returns true if the mouseX and mouseY are within the confines of the button
		public boolean isClicked(int x, int y) {
			return (x>hitBoxesTL.x && x<hitBoxesBR.x && y>hitBoxesTL.y && y<hitBoxesBR.y);
		}
}