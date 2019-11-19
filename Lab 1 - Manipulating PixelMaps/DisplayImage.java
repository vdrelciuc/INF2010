package TP01_1;
 /**
 * Canevas pour contenir l'image a afficher
 * @author Vlad Drelciuc et Maxym Lamothe
 * @date : 2019-05-16
 */
 
import java.awt.*;

public class DisplayImage extends Canvas
{
	static final long serialVersionUID = 0;
	Image  image;
	
	public DisplayImage(Image img) {
		this.image = img;
		setSize(img.getWidth(this), img.getHeight(this));
	}
	
	public void paint(Graphics gr) {
		gr.drawImage(image, 0, 0, this);
	}	
}