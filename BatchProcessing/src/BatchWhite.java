/*
 * To convert all selected images to grayscale, make a new copy and save it to disk 
 */

import edu.duke.*;
import java.io.*;

public class BatchWhite {
	
	public void makeWhiteCopies(){
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			ImageResource image = new ImageResource(f);
			ImageResource grayImage = makeWhite(image);
			String iName = image.getFileName();
			String newName = "white-" + iName;
			grayImage.setFileName(f.getParentFile() + "/" +newName);
			grayImage.draw();
			grayImage.save();
		}
	}
	private ImageResource makeWhite(ImageResource image){
		ImageResource im = new ImageResource(image.getWidth(), image.getHeight());
		for(Pixel p : im.pixels()){
			int X = p.getX();
			int Y = p.getY();
			Pixel origPix = image.getPixel(X, Y);
			if(origPix.getGreen() >= 200){
				p.setRed(255);
				p.setGreen(255);
				p.setBlue(255);
			}else{
				p.setRed(origPix.getRed());
				p.setGreen(origPix.getGreen());
				p.setBlue(origPix.getBlue());
			}
		}
		return im;
	}
	public static void main(String[] args) {
		BatchWhite bg = new BatchWhite();
		bg.makeWhiteCopies();
	}
}