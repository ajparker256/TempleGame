package librarys;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import renderEngine.Loader;

public class StringLibrary {
	//Numbers
	public static GuiTexture[] nums;
	
	public static Vector2f size;
	
	public static Loader load;
	
	public static void init(Loader loader) {
		size = new Vector2f(.1f, .1f);
		load = loader;
		nums = new GuiTexture[10];
		TextureLibrary.space = loader.loadTexture("space");
		
		TextureLibrary.Num1= loader.loadTexture("Num1");
		TextureLibrary.Num2= loader.loadTexture("Num2");
		TextureLibrary.Num3= loader.loadTexture("Num3");
		TextureLibrary.Num4= loader.loadTexture("Num4");
		TextureLibrary.Num5= loader.loadTexture("Num5");
		TextureLibrary.Num6= loader.loadTexture("Num6");
		TextureLibrary.Num7= loader.loadTexture("Num7");
		TextureLibrary.Num8= loader.loadTexture("Num8");
		TextureLibrary.Num9= loader.loadTexture("Num9");
		TextureLibrary.Num0= loader.loadTexture("Num0");
		TextureLibrary.A = loader.loadTexture("A");
		TextureLibrary.B = loader.loadTexture("B");
		TextureLibrary.C = loader.loadTexture("C");
		TextureLibrary.D = loader.loadTexture("D");
		TextureLibrary.E = loader.loadTexture("E");
		TextureLibrary.F = loader.loadTexture("F");
		TextureLibrary.G = loader.loadTexture("G");
		TextureLibrary.H = loader.loadTexture("H");
		TextureLibrary.I = loader.loadTexture("I");
		TextureLibrary.J = loader.loadTexture("J");
		TextureLibrary.K = loader.loadTexture("K");
		TextureLibrary.L = loader.loadTexture("L");
		TextureLibrary.M = loader.loadTexture("M");
		TextureLibrary.N = loader.loadTexture("N");
		TextureLibrary.O = loader.loadTexture("O");
		TextureLibrary.P = loader.loadTexture("P");
		TextureLibrary.Q = loader.loadTexture("Q");
		TextureLibrary.R = loader.loadTexture("R");
		TextureLibrary.S = loader.loadTexture("S");
		TextureLibrary.T = loader.loadTexture("T");
		TextureLibrary.U = loader.loadTexture("U");
		TextureLibrary.V = loader.loadTexture("V");
		TextureLibrary.W = loader.loadTexture("W");
		TextureLibrary.X = loader.loadTexture("X");
		TextureLibrary.Y = loader.loadTexture("Y");
		TextureLibrary.Z = loader.loadTexture("Z");
		
		//Loads lowercase letters
		//TODO Make special sizes for thin letters so that they can be printed closer together. Also make a getWidth(char c) method.
		TextureLibrary.la = loader.loadTexture("la");
		TextureLibrary.lb = loader.loadTexture("lb");
		TextureLibrary.lc = loader.loadTexture("lc");
		TextureLibrary.ld = loader.loadTexture("ld");
		TextureLibrary.le = loader.loadTexture("le");
		TextureLibrary.lf = loader.loadTexture("lf");
		TextureLibrary.lg = loader.loadTexture("lg");
		TextureLibrary.lh = loader.loadTexture("lh");
		TextureLibrary.li = loader.loadTexture("li");
		TextureLibrary.lj = loader.loadTexture("lj");
		TextureLibrary.lk = loader.loadTexture("lk");
		TextureLibrary.ll = loader.loadTexture("ll");
		TextureLibrary.lm = loader.loadTexture("lm");
		TextureLibrary.ln = loader.loadTexture("ln");
		TextureLibrary.lo = loader.loadTexture("lo");
		TextureLibrary.lp = loader.loadTexture("lp");
		TextureLibrary.lq = loader.loadTexture("lq");
		TextureLibrary.lr = loader.loadTexture("lr");
		TextureLibrary.ls = loader.loadTexture("ls");
		TextureLibrary.lt = loader.loadTexture("lt");
		TextureLibrary.lu = loader.loadTexture("lu");
		TextureLibrary.lv = loader.loadTexture("lv");
		TextureLibrary.lw = loader.loadTexture("lw");
		TextureLibrary.lx = loader.loadTexture("lx");
		TextureLibrary.ly = loader.loadTexture("ly");
		TextureLibrary.lz = loader.loadTexture("lz");
		

	}
	
	public static ArrayList<GuiTexture> drawString(String s, Vector2f loc) {
		ArrayList<GuiTexture> string = new ArrayList<GuiTexture>();
		for(int i = 0; i<s.length(); i++) {
			char letter = s.charAt(i);
			string.add(new GuiTexture(getLetter(letter), new Vector2f(loc.x+i*size.x*8/5, loc.y), new Vector2f(size.x,size.y)));
		}
		return string;
	}
	
	//TODO make a getWidth(char c) method
	//This returns the width of the image based off of the character making it so that the text can be correctly positioned next to one another
	public static int getWidth(char c) {
		if(c == ' ') {
			return (int)size.x;
		}
		//Return number textures
		if(c == '0') {
			return (int)size.x;
			
		}
		if(c == '1') 
			return (int)size.x;
		if(c == '2') 
			return (int)size.x;;
		if(c == '3') 
			return (int)size.x;;
		if(c == '4') 
			return (int)size.x;;
		if(c == '5') 
			return (int)size.x;;
		if(c == '6') 
			return (int)size.x;;
		if(c == '7') 
			return (int)size.x;;
		if(c == '8') 
			return (int)size.x;;
		if(c == '9') 
			return (int)size.x;;
		
		
		//Return letter textures for Arial Black (Used font size 96 on 114 by 114 pixel boxes)
		if(c == 'A') 
			return (int)size.x;;
		if(c == 'B') 
			return (int)size.x;;
		if(c == 'C') 
			return (int)size.x;;
		if(c == 'D') 
			return (int)size.x;;
		if(c == 'E') 
			return (int)size.x;;
		if(c == 'F') 
			return (int)size.x;;
		if(c == 'G') 
			return (int)size.x;;
		if(c == 'H') 
			return (int)size.x;;
		if(c == 'I') 
			return (int)size.x;;
		if(c == 'J') 
			return (int)size.x;;
		if(c == 'K') 
			return (int)size.x;;
		if(c == 'L') 
			return (int)size.x;;
		if(c == 'M') 
			return (int)size.x;;
		if(c == 'N') 
			return (int)size.x;;
		if(c == 'O') 
			return (int)size.x;;
		if(c == 'P') 
			return (int)size.x;;
		if(c == 'Q') 
			return (int)size.x;;
		if(c == 'R') 
			return (int)size.x;;
		if(c == 'S') 
			return (int)size.x;;
		if(c == 'T') 
			return (int)size.x;;
		if(c == 'U') 
			return (int)size.x;;
		if(c == 'V') 
			return (int)size.x;;
		//Possibly make this one have more space than the others to accomodate its obscene breadth
		if(c == 'W') 
			return (int)size.x;;
		if(c == 'X') 
			return (int)size.x;;
		if(c == 'Y') 
			return (int)size.x;;
		if(c == 'Z') 
			return (int)size.x;;
		
		//Lower Case Letters
		if(c == 'a') 
			return (int)size.x;;
		if(c == 'b') 
			return (int)size.x;;
		if(c == 'c') 
			return (int)size.x;;
		if(c == 'd') 
			return (int)size.x;;
		if(c == 'e') 
			return (int)size.x;;
		if(c == 'f') 
			return (int)size.x;;
		if(c == 'g') 
			return (int)size.x;;
		if(c == 'h') 
			return (int)size.x;;
		if(c == 'i') 
			return (int)size.x;;
		if(c == 'j') 
			return (int)size.x;;
		if(c == 'k') 
			return (int)size.x;;
		if(c == 'l') 
			return (int)size.x;;
		if(c == 'm') 
			return (int)size.x;;
		if(c == 'n') 
			return (int)size.x;;
		if(c == 'o') 
			return (int)size.x;;
		if(c == 'p') 
			return (int)size.x;;
		if(c == 'q') 
			return (int)size.x;;
		if(c == 'r') 
			return (int)size.x;;
		if(c == 's') 
			return (int)size.x;;
		if(c == 't') 
			return (int)size.x;;
		if(c == 'u') 
			return (int)size.x;;
		if(c == 'v') 
			return (int)size.x;;
		if(c == 'w') 
			return (int)size.x;;
		if(c == 'x') 
			return (int)size.x;;
		if(c == 'y') 
			return (int)size.x;;
		if(c == 'z') 
			return (int)size.x;;
		
		
		return 0;
	}
	
	
	public static int getLetter(char c) {
		//Potential optimization: Order in terms of frequency to reduce lag time in this method.
		if(c == ' ') {
			return TextureLibrary.space;
		}
		//Return number textures
		if(c == '0') {
			return TextureLibrary.Num0;
			
		}
		if(c == '1') 
			return TextureLibrary.Num1;
		if(c == '2') 
			return TextureLibrary.Num2;
		if(c == '3') 
			return TextureLibrary.Num3;
		if(c == '4') 
			return TextureLibrary.Num4;
		if(c == '5') 
			return TextureLibrary.Num5;
		if(c == '6') 
			return TextureLibrary.Num6;
		if(c == '7') 
			return TextureLibrary.Num7;
		if(c == '8') 
			return TextureLibrary.Num8;
		if(c == '9') 
			return TextureLibrary.Num9;
		
		
		//Return letter textures for Arial Black (Used font size 96 on 114 by 114 pixel boxes)
		if(c == 'A') 
			return TextureLibrary.A;
		if(c == 'B') 
			return TextureLibrary.B;
		if(c == 'C') 
			return TextureLibrary.C;
		if(c == 'D') 
			return TextureLibrary.D;
		if(c == 'E') 
			return TextureLibrary.E;
		if(c == 'F') 
			return TextureLibrary.F;
		if(c == 'G') 
			return TextureLibrary.G;
		if(c == 'H') 
			return TextureLibrary.H;
		if(c == 'I') 
			return TextureLibrary.I;
		if(c == 'J') 
			return TextureLibrary.J;
		if(c == 'K') 
			return TextureLibrary.K;
		if(c == 'L') 
			return TextureLibrary.L;
		if(c == 'M') 
			return TextureLibrary.M;
		if(c == 'N') 
			return TextureLibrary.N;
		if(c == 'O') 
			return TextureLibrary.O;
		if(c == 'P') 
			return TextureLibrary.P;
		if(c == 'Q') 
			return TextureLibrary.Q;
		if(c == 'R') 
			return TextureLibrary.R;
		if(c == 'S') 
			return TextureLibrary.S;
		if(c == 'T') 
			return TextureLibrary.T;
		if(c == 'U') 
			return TextureLibrary.U;
		if(c == 'V') 
			return TextureLibrary.V;
		if(c == 'W') 
			return TextureLibrary.W;
		if(c == 'X') 
			return TextureLibrary.X;
		if(c == 'Y') 
			return TextureLibrary.Y;
		if(c == 'Z') 
			return TextureLibrary.Z;
		
		//Lower Case Letters
		if(c == 'a') 
			return TextureLibrary.la;
		if(c == 'b') 
			return TextureLibrary.lb;
		if(c == 'c') 
			return TextureLibrary.lc;
		if(c == 'd') 
			return TextureLibrary.ld;
		if(c == 'e') 
			return TextureLibrary.le;
		if(c == 'f') 
			return TextureLibrary.lf;
		if(c == 'g') 
			return TextureLibrary.lg;
		if(c == 'h') 
			return TextureLibrary.lh;
		if(c == 'i') 
			return TextureLibrary.li;
		if(c == 'j') 
			return TextureLibrary.lj;
		if(c == 'k') 
			return TextureLibrary.lk;
		if(c == 'l') 
			return TextureLibrary.ll;
		if(c == 'm') 
			return TextureLibrary.lm;
		if(c == 'n') 
			return TextureLibrary.ln;
		if(c == 'o') 
			return TextureLibrary.lo;
		if(c == 'p') 
			return TextureLibrary.lp;
		if(c == 'q') 
			return TextureLibrary.lq;
		if(c == 'r') 
			return TextureLibrary.lr;
		if(c == 's') 
			return TextureLibrary.ls;
		if(c == 't') 
			return TextureLibrary.lt;
		if(c == 'u') 
			return TextureLibrary.lu;
		if(c == 'v') 
			return TextureLibrary.lv;
		if(c == 'w') 
			return TextureLibrary.lw;
		if(c == 'x') 
			return TextureLibrary.lx;
		if(c == 'y') 
			return TextureLibrary.ly;
		if(c == 'z') 
			return TextureLibrary.lz;
		
		//Else return -1 showing error
		return -1;
	}
		
	
	public static void setSize(Vector2f s) {
		size = s;
	}
}
