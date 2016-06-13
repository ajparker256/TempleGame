package models;

import textures.ModelTexture;
//Used to easily store a model and a texture together
public class TexturedModel {
	
	private RawModel rawModel;
	private ModelTexture texture;
	
	
	public TexturedModel(RawModel model, ModelTexture texture){
		this.rawModel=model;
		this.texture=texture;
	}


	public RawModel getRawModel() {
		return rawModel;
	}


	public ModelTexture getTexture() {
		return texture;
	}

}
