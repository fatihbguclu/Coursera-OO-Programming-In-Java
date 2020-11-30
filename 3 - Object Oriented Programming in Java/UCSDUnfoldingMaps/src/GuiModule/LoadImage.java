package GuiModule;

import processing.core.PApplet;
import processing.core.PImage;

public class LoadImage extends PApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PImage img;
	public void setup() {
		size(500,500);
		background(250);
		stroke(0);
		img = loadImage("palmTrees.jpg","jpg");
		img.resize(0, height);
		image(img,0,0);
	}
	
	public void draw() {
		int[] color = sunColorSec(second());
		fill(color[0],color[1],color[2]);
		ellipse(width/4,height/5,width/5,width/5);
	}
	
	public int[] sunColorSec(float second) {
		int[] rgb = new int[3];
		
		float diff30 = Math.abs(30-second);
		float ratio = diff30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		//System.out.println("R" + rgb[0] + " G" + rgb[1] + " B" + rgb[2]);
		return rgb;
		
	}
}
