package GuiModule;

import processing.core.PApplet;

public class HappyFace extends PApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2798743036041888541L;

	public void setup()
	{
		size(500,500);
		background(150,150,150);
	}
	
	public void draw() 
	{	
		fill(255,255,0);
		ellipse(width/2,height/2,width-50,height-50);
		fill(0,0,0);
		ellipse(width/3,height/3,width/8,height/8);
		ellipse(2*width/3,height/3,width/8,height/8);
		noFill();
		arc(width/2,2*height/3,200,150,0,PI);
		
	}
}
