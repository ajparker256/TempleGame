package renderEngine;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import models.RawModel;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;
import textures.TextureData;
//Used to create a VAO out of the information within obj files extracted in OBJLoader
public class Loader {
	public static final float MIPMAP_AMOUNT=-1f;
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
//stores the VAOS positions of vertices, texture cordinates to apply the texture properly,
// and the normal vectors of faces on the object(used for lighting)
	public RawModel loadToVAO(float[] positions,float[] textureCords,float[] normals, int indices[]){
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0,3,positions);
		storeDataInAttributeList(1,2,textureCords);
		storeDataInAttributeList(2,3,normals);
		unbindVAO();
		return new RawModel(vaoID,indices.length);
	}
	public RawModel loadToVAO(float[] positions,int dimensions) {
		int vaoID =createVAO();
		this.storeDataInAttributeList(0, dimensions, positions);
		unbindVAO();
		return new RawModel(vaoID,positions.length/dimensions);
	}
	public RawModel loadToVao(float[] positions){
		int vaoID = createVAO();
		storeDataInAttributeList(0,2,positions);
		unbindVAO();
		return new RawModel(vaoID,positions.length/2);
	}
//Gives the texture an int id
	public int loadTexture(String fileName){
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG",new FileInputStream("res/"+fileName+".png"));
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D,GL11.GL_TEXTURE_MIN_FILTER,GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D,GL14.GL_TEXTURE_LOD_BIAS,MIPMAP_AMOUNT);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int textureID=texture.getTextureID();
		textures.add(textureID);
		return textureID;
		
	}
	
	//Creates the vao and returns an id of the vao as an int
	 private int createVAO(){
	 int vaoID = GL30.glGenVertexArrays();
	 GL30.glBindVertexArray(vaoID);
	 vaos.add(vaoID);
	 return vaoID;
	 }
	 //Stores data from obtained from the vao buffer into a list
	private void storeDataInAttributeList(int attributeNumber,int cSize, float[] data){
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER,buffer,GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber,cSize,GL11.GL_FLOAT,false,0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);
	}
	//has to be done or GPU gets clogged up
	private void unbindVAO(){
		 GL30.glBindVertexArray(0);
	}
	//Used to bind indices
	private void bindIndicesBuffer(int[] indices){
		int vboID= GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER,buffer,GL15.GL_STATIC_DRAW);
	}
	//used to create a buffer for the object
	private IntBuffer storeDataInIntBuffer(int[] data){
		IntBuffer buffer= BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
		
	}
	//same idea as above but for floats
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	private TextureData decodeTextureFile(String fileName){
		int width=0;
		int height =0;
		ByteBuffer buffer =null;
		try{
			FileInputStream in = new FileInputStream(fileName);
			PNGDecoder decoder = new PNGDecoder(in);
			width=decoder.getWidth();
			height = decoder.getHeight();
			buffer = ByteBuffer.allocateDirect(4*width*height);
			decoder.decode(buffer,width*4,Format.RGBA);
			buffer.flip();
			in.close();
			
		} catch(Exception e){
			e.printStackTrace();
			System.err.println(fileName+ "Failed");
			System.exit(-1);
		}
		return new TextureData(buffer,width,height);
	}
	public int loadCubeMap(String[] textureFiles){
		int texID = GL11.glGenTextures();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texID);
		
		for(int i=0;i<textureFiles.length;i++){
			TextureData data = decodeTextureFile("res/"+textureFiles[i]+".png");
			GL11.glTexImage2D( GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X+i,0,GL11.GL_RGBA,data.getWidth(),data.getHeight(),0,GL11.GL_RGBA,GL11.GL_UNSIGNED_BYTE,data.getBuffer());
		}
		//smoothens texture
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL13.GL_TEXTURE_CUBE_MAP, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		textures.add(texID);
		return texID;
	}
	
	//Just keeps GPU clean
	public void cleanUp(){
		for (int vao:vaos){
			GL30.glDeleteVertexArrays(vao);
		}
		for (int vbo:vbos){
			GL15.glDeleteBuffers(vbo);
		}
		for (int texture:textures){
			GL11.glDeleteTextures(texture);
		}
	}
}
