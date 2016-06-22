package librarys;

import java.util.ArrayList;

import renderEngine.Loader;
import gui.GuiTexture;

public class TextureLibrary {
	public TextureLibrary(Loader loader){
		TextureLibrary.tiles=new ArrayList<Integer>();
		tiles.add(loader.loadTexture("tiles/Blank"));
		tiles.add(loader.loadTexture("tiles/Dirt"));
		
	}
//Space
public static int space;
public static ArrayList<Integer> tiles;
	
//Numbers stored here
public static int Num0;
public static int Num1;
public static int Num2;
public static int Num3;
public static int Num4;
public static int Num5;
public static int Num6;
public static int Num7;
public static int Num8;
public static int Num9;

//Capital letters stored
public static int A;
public static int B;
public static int C;
public static int D;
public static int E;
public static int F;
public static int G;
public static int H;
public static int I;
public static int J;
public static int K;
public static int L;
public static int M;
public static int N;
public static int O;
public static int P;
public static int Q;
public static int R;
public static int S;
public static int T;
public static int U;
public static int V;
public static int W;
public static int X;
public static int Y;
public static int Z;


//Lower Case letters
public static int la;
public static int lb;
public static int lc;
public static int ld;
public static int le;
public static int lf;
public static int lg;
public static int lh;
public static int li;
public static int lj;
public static int lk;
public static int ll;
public static int lm;
public static int ln;
public static int lo;
public static int lp;
public static int lq;
public static int lr;
public static int ls;
public static int lt;
public static int lu;
public static int lv;
public static int lw;
public static int lx;
public static int ly;
public static int lz;

//Punctuation
public static int period;
public static int exclamationPoint;
public static int questionMark;
public static int comma;

public static int getTile(int texture) {
	return tiles.get(texture);
}
}
