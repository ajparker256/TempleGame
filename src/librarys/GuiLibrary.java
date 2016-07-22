package librarys;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import renderEngine.Loader;

public class GuiLibrary {
public static GuiTexture button1;
public static GuiTexture gun;
public static GuiTexture crosshair;
public static int bulletTexture;
public static int background;
public static int shop;
public static int nextWave;
public static int health;

//Explorer's basic images for standing and walking
public static int explorerStanding;
public static int explorerWalkingL;
public static int explorerWalkingR;
public static int explorerStanding1;
public static int explorerWalkingL1;
public static int explorerWalkingR1;

public static int minerStanding;
public static int minerWalkingL;
public static int minerWalkingR;
public static int minerStanding1;
public static int minerWalkingL1;
public static int minerWalkingR1;



//Dart Trap Arrow Animation
public static int arrow1;
public static int arrow2;
public static int arrow3;
public static int arrow4;
public static int arrow5;
public static int arrow6;
public static int arrow7;
public static int arrow8;
public static int arrow9;
public static int arrow10;

//Images for the shop
public static int frame;
public static int upArrow;
public static int downArrow;

public static int idol1;
public static int idolOnBlank;

public static int ladder;
public static int ladderTop;

public static int desertBackdrop;

public static int treasureOpen;
public static int treasureClosed;

public static int rock1;
public static int rock2;
public static int rock3;
public static int rock4;
public static int rock5;
public static int rock6;
public static int rock7;

public static int flame1;
public static int flame2;
public static int flame3;
public static int flame4;

//Space
public static int space;
	
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

public static int blank;
public static int dirt0;

public static int arrowTrap1;
public static int arrowTrap2;
public static int arrowTrap3;
public static int arrowTrap4;
public static int arrowVolley;

public static int backgroundDraft1;
	
public static int commonBackground;
public static int rareBackground;
public static int legendaryBackground;

public static void init(Loader loader) {
		GuiLibrary.explorerStanding = loader.loadTexture("Explorers/Basic/BasicExplorer");
		GuiLibrary.explorerWalkingL = loader.loadTexture("Explorers/Basic/BasicExplorer Walking1");
		GuiLibrary.explorerWalkingR = loader.loadTexture("Explorers/Basic/BasicExplorer Walking2");

		
		GuiLibrary.minerStanding = loader.loadTexture("Explorers/Miner/Miner");
		GuiLibrary.minerWalkingL = loader.loadTexture("Explorers/Miner/MinerWalking1");
		GuiLibrary.minerWalkingR = loader.loadTexture("Explorers/Miner/MinerWalking2");

		
		GuiLibrary.arrow1 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows1");
		GuiLibrary.arrow2 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows2");
		GuiLibrary.arrow3 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows3");
		GuiLibrary.arrow4 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows4");
		GuiLibrary.arrow5 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows5");
		GuiLibrary.arrow6 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows6");
		GuiLibrary.arrow7 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows7");
		GuiLibrary.arrow8 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows8");
		GuiLibrary.arrow9 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows9");
		GuiLibrary.arrow10 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows10");
		
		GuiLibrary.frame = loader.loadTexture("Frame");
		GuiLibrary.upArrow = loader.loadTexture("UpArrow");
		GuiLibrary.downArrow = loader.loadTexture("DownArrow");
		
		GuiLibrary.idol1 = loader.loadTexture("Idol");
		GuiLibrary.idolOnBlank = loader.loadTexture("/Tiles/IdolOnBlank");

		GuiLibrary.ladder = loader.loadTexture("/Tiles/ladder");
		GuiLibrary.ladderTop = loader.loadTexture("/Tiles/ladderTop");
		
		GuiLibrary.treasureOpen = loader.loadTexture("tiles/treasureOpen");
		GuiLibrary.treasureClosed = loader.loadTexture("/Tiles/treasureClosed");
	
		GuiLibrary.rock1 = loader.loadTexture("tiles/Dirt3");
		GuiLibrary.rock2 = loader.loadTexture("tiles/Dirt4");
		GuiLibrary.rock3 = loader.loadTexture("tiles/Dirt5");
		GuiLibrary.rock4 = loader.loadTexture("tiles/Dirt6");
		GuiLibrary.rock5 = loader.loadTexture("tiles/Dirt7");
		GuiLibrary.rock6 = loader.loadTexture("tiles/Dirt8");
		GuiLibrary.rock7 = loader.loadTexture("tiles/Dirt9");
		
		GuiLibrary.flame1 = loader.loadTexture("Flames/Fire1");
		GuiLibrary.flame2 = loader.loadTexture("Flames/Fire2");
		GuiLibrary.flame3 = loader.loadTexture("Flames/Fire3");
		GuiLibrary.flame4 = loader.loadTexture("Flames/Fire4");
		
		GuiLibrary.space = loader.loadTexture("letters/space");
		
		GuiLibrary.Num1= loader.loadTexture("letters/Num1");
		GuiLibrary.Num2= loader.loadTexture("letters/Num2");
		GuiLibrary.Num3= loader.loadTexture("letters/Num3");
		GuiLibrary.Num4= loader.loadTexture("letters/Num4");
		GuiLibrary.Num5= loader.loadTexture("letters/Num5");
		GuiLibrary.Num6= loader.loadTexture("letters/Num6");
		GuiLibrary.Num7= loader.loadTexture("letters/Num7");
		GuiLibrary.Num8= loader.loadTexture("letters/Num8");
		GuiLibrary.Num9= loader.loadTexture("letters/Num9");
		GuiLibrary.Num0= loader.loadTexture("letters/Num0");
		GuiLibrary.A = loader.loadTexture("letters/A");
		GuiLibrary.B = loader.loadTexture("letters/B");
		GuiLibrary.C = loader.loadTexture("letters/C");
		GuiLibrary.D = loader.loadTexture("letters/D");
		GuiLibrary.E = loader.loadTexture("letters/E");
		GuiLibrary.F = loader.loadTexture("letters/F");
		GuiLibrary.G = loader.loadTexture("letters/G");
		GuiLibrary.H = loader.loadTexture("letters/H");
		GuiLibrary.I = loader.loadTexture("letters/I");
		GuiLibrary.J = loader.loadTexture("letters/J");
		GuiLibrary.K = loader.loadTexture("letters/K");
		GuiLibrary.L = loader.loadTexture("letters/L");
		GuiLibrary.M = loader.loadTexture("letters/M");
		GuiLibrary.N = loader.loadTexture("letters/N");
		GuiLibrary.O = loader.loadTexture("letters/O");
		GuiLibrary.P = loader.loadTexture("letters/P");
		GuiLibrary.Q = loader.loadTexture("letters/Q");
		GuiLibrary.R = loader.loadTexture("letters/R");
		GuiLibrary.S = loader.loadTexture("letters/S");
		GuiLibrary.T = loader.loadTexture("letters/T");
		GuiLibrary.U = loader.loadTexture("letters/U");
		GuiLibrary.V = loader.loadTexture("letters/V");
		GuiLibrary.W = loader.loadTexture("letters/W");
		GuiLibrary.X = loader.loadTexture("letters/X");
		GuiLibrary.Y = loader.loadTexture("letters/Y");
		GuiLibrary.Z = loader.loadTexture("letters/Z");
		
		//Loads lowercase letters
		//TODO Make special sizes for thin letters so that they can be printed closer together. Also make a getWidth(char c) method.
		GuiLibrary.la = loader.loadTexture("letters/la");
		GuiLibrary.lb = loader.loadTexture("letters/lb");
		GuiLibrary.lc = loader.loadTexture("letters/lc");
		GuiLibrary.ld = loader.loadTexture("letters/ld");
		GuiLibrary.le = loader.loadTexture("letters/le");
		GuiLibrary.lf = loader.loadTexture("letters/lf");
		GuiLibrary.lg = loader.loadTexture("letters/lg");
		GuiLibrary.lh = loader.loadTexture("letters/lh");
		GuiLibrary.li = loader.loadTexture("letters/li");
		GuiLibrary.lj = loader.loadTexture("letters/lj");
		GuiLibrary.lk = loader.loadTexture("letters/lk");
		GuiLibrary.ll = loader.loadTexture("letters/ll");
		GuiLibrary.lm = loader.loadTexture("letters/lm");
		GuiLibrary.ln = loader.loadTexture("letters/ln");
		GuiLibrary.lo = loader.loadTexture("letters/lo");
		GuiLibrary.lp = loader.loadTexture("letters/lp");
		GuiLibrary.lq = loader.loadTexture("letters/lq");
		GuiLibrary.lr = loader.loadTexture("letters/lr");
		GuiLibrary.ls = loader.loadTexture("letters/ls");
		GuiLibrary.lt = loader.loadTexture("letters/lt");
		GuiLibrary.lu = loader.loadTexture("letters/lu");
		GuiLibrary.lv = loader.loadTexture("letters/lv");
		GuiLibrary.lw = loader.loadTexture("letters/lw");
		GuiLibrary.lx = loader.loadTexture("letters/lx");
		GuiLibrary.ly = loader.loadTexture("letters/ly");
		GuiLibrary.lz = loader.loadTexture("letters/lz");
		
		//This loads the punctuation
		GuiLibrary.period = loader.loadTexture("letters/period");
		GuiLibrary.exclamationPoint = loader.loadTexture("letters/exclamationPoint");
		GuiLibrary.questionMark = loader.loadTexture("letters/questionMark");
		GuiLibrary.comma = loader.loadTexture("letters/comma");

		GuiLibrary.blank = loader.loadTexture("tiles/Blank");
		GuiLibrary.dirt0 = loader.loadTexture("tiles/Dirt2");
		
		GuiLibrary.arrowTrap1 = loader.loadTexture("CrossBow1");
		GuiLibrary.arrowTrap2 = loader.loadTexture("CrossBow2");
		GuiLibrary.arrowTrap3 = loader.loadTexture("CrossBow3");
		GuiLibrary.arrowTrap4 = loader.loadTexture("CrossBow4");
		GuiLibrary.arrowVolley = loader.loadTexture("arrows");		
		
		GuiLibrary.backgroundDraft1 = loader.loadTexture("Title Screen/BackgroundDraft1");
		
		GuiLibrary.desertBackdrop = loader.loadTexture("DesertBackdrop");
	
		//GuiLibrary.commonBackground = loader.loadTexture("");
		//GuiLibrary.rareBackground = loader.loadTexture("");
		//GuiLibrary.legendaryBackground = loader.loadTexture("");
	}
	

}
