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
		
		public static Button makeButtonWithCenterLoc(Vector2f center, Vector2f halfSize) {
			Vector2f hitBoxesTL = new Vector2f(center.x - halfSize.x, center.y+halfSize.y);
			Vector2f hitBoxesBR = new Vector2f(center.x + halfSize.x, center.y-halfSize.y);
			return new Button(hitBoxesTL, hitBoxesBR);
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
		public boolean isClicked(float x, float y) {
			return (x>hitBoxesTL.x && x<hitBoxesBR.x && y<hitBoxesTL.y && y>hitBoxesBR.y);
		}
}