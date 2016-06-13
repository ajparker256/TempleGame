package models;
//Used to store where a model is located as well as vertex count
public class RawModel {
private int vaoID;
private int vertexCount;


public RawModel(int vaoID,int vertexCount){
	this.vaoID=vaoID;
	this.vertexCount=vertexCount;	
}


public int getVaoID() {
	return vaoID;
}
public int getVertexCount(){
	return this.vertexCount;
}


}