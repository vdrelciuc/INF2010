package TP01_1;
/**
 * Interface des operations sur PixelMap
 * @author Vlad Drelciuc et Maxym Lamothe
 * @date : 2019-05-16
 */

public interface ImageOperations 
{
	public void negate();
	public void convertToBWImage();
	public void convertToGrayImage();
	public void convertToColorImage();
	public void resize(int x, int y);
	public void insert(PixelMap pm, int row0, int col0);
	public void crop(int h, int w);
	public void translate(int colOffset, int rowOffset);
}
