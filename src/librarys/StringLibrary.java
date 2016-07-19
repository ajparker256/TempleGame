package librarys;

import java.util.ArrayList;
import java.util.Scanner;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;


import gui.GuiTexture;
import renderEngine.Loader;

public class StringLibrary {
	//Numbers
	public static GuiTexture[] nums;
	
	public static Vector2f size;
	
	public static Loader load;
	
	public static float currentLength;
	
	public static float spacing;
	
	public static void init() {
		size = new Vector2f(.2f, .4f);
	}
	
	//This returns a list of instructions to draw letters in the shape of the string.
	public static ArrayList<GuiTexture> drawString(String s, Vector2f loc) {
		ArrayList<GuiTexture> string = new ArrayList<GuiTexture>();
		float tempHeight = 0;
		currentLength = 0;
		for(int i = 0; i<s.length(); i++) {
			char letter = s.charAt(i);
			if(i!=s.length()-1) {
				char nextChar = s.charAt(i+1);
				if(nextChar == 'w' || nextChar == 'm') {
					spacing = size.x*8/9;
				} else if(nextChar == 'l' || nextChar == 'j' || nextChar == 'f' || nextChar == 'i') {
					spacing = size.x/4;
				} else {
					spacing = size.x/2;
				}
			}
			if(letter == 'g' || letter == 'q' || letter == 'p' || letter == 'y') {
				tempHeight = loc.y-size.y*2/5;
			} else {
				tempHeight = loc.y;
			}
			
			string.add(new GuiTexture(getLetter(letter), new Vector2f(loc.x+currentLength, tempHeight), new Vector2f(size.x,size.y)));
			
			currentLength += getWidth(letter)+spacing;
		}
		currentLength = 0;
		return string;
	}
	
	//This fits a string into a given length, wrapping the text
	public static ArrayList<GuiTexture> makeItFit(String s, Vector2f locationOfTopLeftCorner, float width) {
		Scanner scan = new Scanner(s);
		ArrayList<GuiTexture> words = new ArrayList<GuiTexture>();
		//This is used to control line breaks
		int totalHeight = 0;
		//This is used to control width
		float totalLength = 0;
		while(scan.hasNext()) {
			//This is the next word plus a space so that it fits well
			String nextWord = scan.next()+" ";
			//This is the width of the next word
			float wLength = getWidth(nextWord);
			//If the word does not exceed the given width, add it to the line
			if(totalLength+wLength<width) {
				totalLength+=wLength;
				//Otherwise move it to the next line
			} else {
				totalHeight++;
				totalLength = wLength;
			}
			//Add the word to the list of strings to draw at the given locations
			words.addAll(drawString(nextWord, new Vector2f(locationOfTopLeftCorner.x+totalLength-wLength, locationOfTopLeftCorner.y-totalHeight*size.y*2)));
		}
		//Restore the memory
		scan.close();
		//Return the list of letter images in the proper locations
		return words;
	}
	
	public static ArrayList<GuiTexture>  makeItFitC(String s, Vector2f locationOfTopLeftCorner, float width) {
		Scanner scan = new Scanner(s);
		int row = 0;
		float length = 0;
		int letterCount = 0;
		ArrayList<GuiTexture> string = new ArrayList<GuiTexture>();
		while(scan.hasNext()) {
			//This is used to subString the line for drawing it centered
				String nextWord = scan.next();
				float wordWidth = getWidth(nextWord);
				if(length+wordWidth>width) {
					string.addAll(drawString(s.substring(0, letterCount), new Vector2f(locationOfTopLeftCorner.x+(width-length+getWidth(' '))/2, locationOfTopLeftCorner.y-row*size.y*2)));
					s=s.substring(letterCount);
					if(length != 0)
						row++;
					length = wordWidth+getWidth(' ');
					letterCount = nextWord.length()+1;
				} else {
					length+=wordWidth+getWidth(' ');
					letterCount+=nextWord.length()+1;
				} 
		}
		string.addAll(drawString(s, new Vector2f(locationOfTopLeftCorner.x+(width-length+getWidth(' '))/2, locationOfTopLeftCorner.y-row*size.y*2)));
		scan.close();
		return string;
	}
	
	public static Vector2f getSize() {
		return size;
	}
	
	//This returns the width of a string, allowing centering of strings regardless of length
	public static float getWidth(String s) {
		currentLength = 0;
		for(int i = 0; i<s.length(); i++) {
			char letter = s.charAt(i);
			if(i!=s.length()-1) {
				char nextChar = s.charAt(i+1);
				if(nextChar == 'w' || nextChar == 'm') {
					spacing = size.x*8/9;
				} else if(nextChar == 'l' || nextChar == 'j' || nextChar == 'f' || nextChar == 'i') {
					spacing = size.x/4;
				} else {
					spacing = size.x/2;
				}
			}
			currentLength += getWidth(letter)+spacing;
		}
		return currentLength;
	}
	
	//This returns the width of the image based off of the character making it so that the text can be correctly positioned next to one another
	public static float getWidth(char c) {
		if(c == ' ') {
			return size.x/4;
		}
		//Return number textures
		if(c == '0') {
			return size.x;
			
		}
		if(c == '1') 
			return size.x;
		if(c == '2') 
			return size.x;
		if(c == '3') 
			return size.x;
		if(c == '4') 
			return size.x;
		if(c == '5') 
			return size.x;
		if(c == '6') 
			return size.x;
		if(c == '7') 
			return size.x;
		if(c == '8') 
			return size.x;
		if(c == '9') 
			return size.x;
		
		
		//Return letter textures for Arial Black (Used font size 96 on 114 by 114 pixel boxes)
		if(c == 'A') 
			return size.x*8/9;
		if(c == 'B') 
			return size.x*8/9;
		if(c == 'C') 
			return size.x*8/9;
		if(c == 'D') 
			return size.x*8/9;
		if(c == 'E') 
			return size.x*8/9;
		if(c == 'F') 
			return size.x*8/9;
		if(c == 'G') 
			return size.x*8/9;
		if(c == 'H') 
			return size.x*8/9;
		if(c == 'I') 
			return size.x*8/9;
		if(c == 'J') 
			return size.x*8/9;
		if(c == 'K') 
			return size.x*8/9;
		if(c == 'L') 
			return size.x*8/9;
		if(c == 'M') 
			return size.x*11/10;
		if(c == 'N') 
			return size.x*8/9;
		if(c == 'O') 
			return size.x*8/9;
		if(c == 'P') 
			return size.x*8/9;
		if(c == 'Q') 
			return size.x*8/9;
		if(c == 'R') 
			return size.x*8/9;
		if(c == 'S') 
			return size.x*8/9;
		if(c == 'T') 
			return size.x*8/9;
		if(c == 'U') 
			return size.x*8/9;
		if(c == 'V') 
			return size.x*8/9;
		//Possibly make this one have more space than the others to accomodate its obscene breadth
		if(c == 'W') 
			return size.x;
		if(c == 'X') 
			return size.x*8/9;
		if(c == 'Y') 
			return size.x*8/9;
		if(c == 'Z') 
			return size.x*8/9;
		
		//Lower Case Letters (113 is the width of the images)
		if(c == 'a') 
			return size.x*75/113;
		if(c == 'b') 
			return size.x*85/113;
		if(c == 'c') 
			return size.x*90/113;
		if(c == 'd') 
			return size.x*80/113;
		if(c == 'e') 
			return size.x*80/113;
		if(c == 'f') 
			return size.x*70/113;
		if(c == 'g') 
			return size.x*85/113;
		if(c == 'h') 
			return size.x*90/113;
		if(c == 'i') 
			return size.x*35/113;
		if(c == 'j') 
			return size.x*50/113;
		if(c == 'k') 
			return size.x*65/113;
		if(c == 'l') 
			return size.x*45/113;
		if(c == 'm') 
			return size.x;
		if(c == 'n') 
			return size.x*75/113;
		if(c == 'o') 
			return size.x*80/113;
		if(c == 'p') 
			return size.x*90/113;
		if(c == 'q') 
			return size.x*70/113;
		if(c == 'r') 
			return size.x*45/113;
		if(c == 's') 
			return size.x*85/113;
		if(c == 't') 
			return size.x*55/113;
		if(c == 'u') 
			return size.x*80/113;
		if(c == 'v') 
			return size.x*75/113;
		if(c == 'w') 
			return size.x*120/113;
		if(c == 'x') 
			return size.x*90/113;
		if(c == 'y') 
			return size.x*65/113;
		if(c == 'z') 
			return size.x*75/113;
		
		//Punctuation Width
		if(c == '.')
			return size.x*40/113;
		if(c == '!') 
			return size.x*40/113;
		if(c == '?') 
			return size.x*70/113;
		if(c == ',') 
			return size.x*40/113;
		
		
		return 0;
	}
	
	
	public static int getLetter(char c) {
		//Potential optimization: Order in terms of frequency to reduce lag time in this method.
		if(c == ' ') {
			return GuiLibrary.space;
		}
		//Return number textures
		if(c == '0') {
			return GuiLibrary.Num0;
			
		}
		if(c == '1') 
			return GuiLibrary.Num1;
		if(c == '2') 
			return GuiLibrary.Num2;
		if(c == '3') 
			return GuiLibrary.Num3;
		if(c == '4') 
			return GuiLibrary.Num4;
		if(c == '5') 
			return GuiLibrary.Num5;
		if(c == '6') 
			return GuiLibrary.Num6;
		if(c == '7') 
			return GuiLibrary.Num7;
		if(c == '8') 
			return GuiLibrary.Num8;
		if(c == '9') 
			return GuiLibrary.Num9;
		
		
		//Return letter textures for Arial Black (Used font size 96 on 114 by 114 pixel boxes)
		if(c == 'A') 
			return GuiLibrary.A;
		if(c == 'B') 
			return GuiLibrary.B;
		if(c == 'C') 
			return GuiLibrary.C;
		if(c == 'D') 
			return GuiLibrary.D;
		if(c == 'E') 
			return GuiLibrary.E;
		if(c == 'F') 
			return GuiLibrary.F;
		if(c == 'G') 
			return GuiLibrary.G;
		if(c == 'H') 
			return GuiLibrary.H;
		if(c == 'I') 
			return GuiLibrary.I;
		if(c == 'J') 
			return GuiLibrary.J;
		if(c == 'K') 
			return GuiLibrary.K;
		if(c == 'L') 
			return GuiLibrary.L;
		if(c == 'M') 
			return GuiLibrary.M;
		if(c == 'N') 
			return GuiLibrary.N;
		if(c == 'O') 
			return GuiLibrary.O;
		if(c == 'P') 
			return GuiLibrary.P;
		if(c == 'Q') 
			return GuiLibrary.Q;
		if(c == 'R') 
			return GuiLibrary.R;
		if(c == 'S') 
			return GuiLibrary.S;
		if(c == 'T') 
			return GuiLibrary.T;
		if(c == 'U') 
			return GuiLibrary.U;
		if(c == 'V') 
			return GuiLibrary.V;
		if(c == 'W') 
			return GuiLibrary.W;
		if(c == 'X') 
			return GuiLibrary.X;
		if(c == 'Y') 
			return GuiLibrary.Y;
		if(c == 'Z') 
			return GuiLibrary.Z;
		
		//Lower Case Letters
		if(c == 'a') 
			return GuiLibrary.la;
		if(c == 'b') 
			return GuiLibrary.lb;
		if(c == 'c') 
			return GuiLibrary.lc;
		if(c == 'd') 
			return GuiLibrary.ld;
		if(c == 'e') 
			return GuiLibrary.le;
		if(c == 'f') 
			return GuiLibrary.lf;
		if(c == 'g') 
			return GuiLibrary.lg;
		if(c == 'h') 
			return GuiLibrary.lh;
		if(c == 'i') 
			return GuiLibrary.li;
		if(c == 'j') 
			return GuiLibrary.lj;
		if(c == 'k') 
			return GuiLibrary.lk;
		if(c == 'l') 
			return GuiLibrary.ll;
		if(c == 'm') 
			return GuiLibrary.lm;
		if(c == 'n') 
			return GuiLibrary.ln;
		if(c == 'o') 
			return GuiLibrary.lo;
		if(c == 'p') 
			return GuiLibrary.lp;
		if(c == 'q') 
			return GuiLibrary.lq;
		if(c == 'r') 
			return GuiLibrary.lr;
		if(c == 's') 
			return GuiLibrary.ls;
		if(c == 't') 
			return GuiLibrary.lt;
		if(c == 'u') 
			return GuiLibrary.lu;
		if(c == 'v') 
			return GuiLibrary.lv;
		if(c == 'w') 
			return GuiLibrary.lw;
		if(c == 'x') 
			return GuiLibrary.lx;
		if(c == 'y') 
			return GuiLibrary.ly;
		if(c == 'z') 
			return GuiLibrary.lz;
		
		if(c == '.') 
			return GuiLibrary.period;
		if(c == '!')
			return GuiLibrary.exclamationPoint;
		if(c == '?') 
			return GuiLibrary.questionMark;
		if(c == ',') 
			return GuiLibrary.comma;
		
		//Else return -1 showing error
		return -1;
	}
		
	
	public static void setSize(Vector2f s) {
		size = s;
	}
}
