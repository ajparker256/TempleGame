
	package gui;
	 
	import org.lwjgl.util.vector.Matrix4f;
	 
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import shaders.ShaderProgram;
	 
	public class GuiShader extends ShaderProgram{
	     
	    private static final String VERTEX_FILE = "src/gui/guiVertexShader.txt";
	    private static final String FRAGMENT_FILE = "src/gui/guiFragmentShader.txt";
	     
	    private int location_transformationMatrix;
	    private int location_colorShift;
	    private int location_degrees;
	 
	    public GuiShader() {
	        super(VERTEX_FILE, FRAGMENT_FILE);
	    }
	     
	    public void loadTransformation(Matrix4f matrix){
	        super.loadMatrix(location_transformationMatrix, matrix);
	    }
	    public void loadColorShift(Vector3f vector){
	        super.loadVector4(location_colorShift, new Vector4f(vector.x,vector.y,vector.z,1.0f));
	    }
	 
	    protected void getAllUniformLocations() {
	        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	        location_colorShift=super.getUniformLocation("colorShift");
	        location_degrees=super.getUniformLocation("degrees");
	    }
	 
	    protected void bindAttributes() {
	        super.bindAttribute(0, "position");
	    }

		public void loadDegrees(double degrees) {
			super.loadFloat(location_degrees,(float) degrees);
			
		}
	     
	     
	 
	}
