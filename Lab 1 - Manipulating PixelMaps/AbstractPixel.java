package TP01_1;
/**
 * Classe abstraite de pixel
 * @author Vlad Drelciuc et Maxym Lamothe
 * @date : 2019-05-16
 */

public abstract class AbstractPixel 
{
	public abstract BWPixel toBWPixel();
	public abstract GrayPixel toGrayPixel();
	public abstract ColorPixel toColorPixel();
	public abstract TransparentPixel toTransparentPixel();
	
	public abstract AbstractPixel Negative();
	public abstract void setAlpha(int alpha);
	
	public abstract String toString();
}
