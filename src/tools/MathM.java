package tools;

import java.util.ArrayList;


import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import gui.GuiTexture;
import librarys.GuiLibrary;

public class MathM {
//Math used to create a Matrix of the rotations and positions of an object after it moves an amount
	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz,float scale){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rx), new Vector3f(1,0,0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rz), new Vector3f(0,0,1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale,scale,scale),matrix,matrix);
		return matrix;
		
	}
//Math used to create a Matrix of the object as it is seen by the camera
	public static Matrix4f createViewMatrix(Camera camera){
		Matrix4f viewMatrix= new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float)Math.toRadians(camera.getPitch()),new Vector3f (1,0,0), viewMatrix,viewMatrix);
		Matrix4f.rotate((float)Math.toRadians(camera.getYaw()),new Vector3f(0,1,0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float)Math.toRadians(camera.getRoll()),new Vector3f(0,0,1), viewMatrix, viewMatrix);
		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);	
		return viewMatrix;
	}
	public static float clamp(float input,int min,int max){
		if(input>max){
			input=max;
		}
		if(input<min){
			input=min;
		}
		return input;
	}
	public static int clamp(int input,int min,int max){
		if(input>max){
			input=max;
		}
		if(input<min){
			input=min;
		}
		return input;
	}
	public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
		return matrix;
	}
	public static ArrayList<GuiTexture> printNumber (int number,Vector2f location,float size){
		ArrayList<GuiTexture> list= new ArrayList<GuiTexture>();
		if(number%10==0){
			list.add(new GuiTexture(GuiLibrary.Num0, new Vector2f(location.x+size*3,location.y), new Vector2f(size,size)));
		}else 	if(number%10==1){
			list.add(new GuiTexture(GuiLibrary.Num1, new Vector2f(location.x+size*3,location.y), new Vector2f(size,size)));
		}else 	if(number%10==2){
		
			list.add(new GuiTexture(GuiLibrary.Num2, new Vector2f(location.x+size*3,location.y), new Vector2f(size,size)));
		}else 	if(number%10==3){
			list.add(new GuiTexture(GuiLibrary.Num3, new Vector2f(location.x+size*3,location.y), new Vector2f(size,size)));
		}else 	if(number%10==4){
			list.add(new GuiTexture(GuiLibrary.Num4, new Vector2f(location.x+size*3,location.y), new Vector2f(size,size)));
		}else 	if(number%10==5){
			list.add(new GuiTexture(GuiLibrary.Num5, new Vector2f(location.x+size*3,location.y), new Vector2f(size,size)));
		}else 	if(number%10==6){
			list.add(new GuiTexture(GuiLibrary.Num6, new Vector2f(location.x+size*3,location.y), new Vector2f(size,size)));
		}else 	if(number%10==7){
			list.add(new GuiTexture(GuiLibrary.Num7, new Vector2f(location.x+size*3,location.y), new Vector2f(size,size)));
		}else 	if(number%10==8){
			list.add(new GuiTexture(GuiLibrary.Num8, new Vector2f(location.x+size*3,location.y), new Vector2f(size,size)));
		}else 	if(number%10==9){
			list.add(new GuiTexture(GuiLibrary.Num9, new Vector2f(location.x+size*3,location.y), new Vector2f(size,size)));
		}
		if((number%100)-number%10==0){
				list.add(new GuiTexture(GuiLibrary.Num0, new Vector2f(location.x+size*2,location.y), new Vector2f(size,size)));
			}else 	if((number%100)-number%10==10){
				list.add(new GuiTexture(GuiLibrary.Num1, new Vector2f(location.x+size*2,location.y), new Vector2f(size,size)));
			}else 	if((number%100)-number%10==20){
			
				list.add(new GuiTexture(GuiLibrary.Num2, new Vector2f(location.x+size*2,location.y), new Vector2f(size,size)));
			}else 	if((number%100)-number%10==30){
				list.add(new GuiTexture(GuiLibrary.Num3, new Vector2f(location.x+size*2,location.y), new Vector2f(size,size)));
			}else 	if((number%100)-number%10==40){
				list.add(new GuiTexture(GuiLibrary.Num4, new Vector2f(location.x+size*2,location.y), new Vector2f(size,size)));
			}else 	if((number%100)-number%10==50){
				list.add(new GuiTexture(GuiLibrary.Num5, new Vector2f(location.x+size*2,location.y), new Vector2f(size,size)));
			}else 	if((number%100)-number%10==60){
				list.add(new GuiTexture(GuiLibrary.Num6, new Vector2f(location.x+size*2,location.y), new Vector2f(size,size)));
			}else 	if((number%100)-number%10==70){
				list.add(new GuiTexture(GuiLibrary.Num7, new Vector2f(location.x+size*2,location.y), new Vector2f(size,size)));
			}else 	if((number%100)-number%10==80){
				list.add(new GuiTexture(GuiLibrary.Num8, new Vector2f(location.x+size*2,location.y), new Vector2f(size,size)));
			}else 	if((number%100)-number%10==90){
				list.add(new GuiTexture(GuiLibrary.Num9, new Vector2f(location.x+size*2,location.y), new Vector2f(size,size)));
			}
			if((number%1000)-number%100==0){
				list.add(new GuiTexture(GuiLibrary.Num0, new Vector2f(location.x+size,location.y), new Vector2f(size,size)));
			}else 	if((number%1000)-number%100==100){
				list.add(new GuiTexture(GuiLibrary.Num1, new Vector2f(location.x+size,location.y), new Vector2f(size,size)));
			}else 	if((number%1000)-number%100==200){
				list.add(new GuiTexture(GuiLibrary.Num2, new Vector2f(location.x+size,location.y), new Vector2f(size,size)));
			}else 	if((number%1000)-number%100==300){
				list.add(new GuiTexture(GuiLibrary.Num3, new Vector2f(location.x+size,location.y), new Vector2f(size,size)));
			}else 	if((number%1000)-number%100==400){
				list.add(new GuiTexture(GuiLibrary.Num4, new Vector2f(location.x+size,location.y), new Vector2f(size,size)));
			}else 	if((number%1000)-number%100==500){
				list.add(new GuiTexture(GuiLibrary.Num5, new Vector2f(location.x+size,location.y), new Vector2f(size,size)));
			}else 	if((number%1000)-number%100==600){
				list.add(new GuiTexture(GuiLibrary.Num6, new Vector2f(location.x+size,location.y), new Vector2f(size,size)));
			}else 	if((number%1000)-number%100==700){
				list.add(new GuiTexture(GuiLibrary.Num7, new Vector2f(location.x+size,location.y), new Vector2f(size,size)));
			}else 	if((number%1000)-number%100==800){
				list.add(new GuiTexture(GuiLibrary.Num8, new Vector2f(location.x+size,location.y), new Vector2f(size,size)));
			}else 	if((number%1000)-number%100==900){
				list.add(new GuiTexture(GuiLibrary.Num9, new Vector2f(location.x+size,location.y), new Vector2f(size,size)));
			}

			if((number%10000)-number%100-number%10==0){
				list.add(new GuiTexture(GuiLibrary.Num0, new Vector2f(location.x,location.y), new Vector2f(size,size)));
			}else 	if((number%10000)-number%1000==1000){
				list.add(new GuiTexture(GuiLibrary.Num1, new Vector2f(location.x,location.y), new Vector2f(size,size)));
			}else 	if((number%10000)-number%1000==2000){
				list.add(new GuiTexture(GuiLibrary.Num2, new Vector2f(location.x,location.y), new Vector2f(size,size)));
			}else 	if((number%10000)-number%1000==3000){
				list.add(new GuiTexture(GuiLibrary.Num3, new Vector2f(location.x,location.y), new Vector2f(size,size)));
			}else 	if((number%10000)-number%1000==4000){
				list.add(new GuiTexture(GuiLibrary.Num4, new Vector2f(location.x,location.y), new Vector2f(size,size)));
			}else 	if((number%10000)-number%1000==5000){
				list.add(new GuiTexture(GuiLibrary.Num5, new Vector2f(location.x,location.y), new Vector2f(size,size)));
			}else 	if((number%10000)-number%1000==6000){
				list.add(new GuiTexture(GuiLibrary.Num6, new Vector2f(location.x,location.y), new Vector2f(size,size)));
			}else 	if((number%10000)-number%1000==7000){
				list.add(new GuiTexture(GuiLibrary.Num7, new Vector2f(location.x,location.y), new Vector2f(size,size)));
			}else 	if((number%10000)-number%1000==8000){
				list.add(new GuiTexture(GuiLibrary.Num8, new Vector2f(location.x,location.y), new Vector2f(size,size)));
			}else 	if((number%10000)-number%1000==9000){
				list.add(new GuiTexture(GuiLibrary.Num9, new Vector2f(location.x,location.y), new Vector2f(size,size)));
			}
		
		return list;
	}

	
	
}