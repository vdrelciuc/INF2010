package TP01_1;

/**
 * Classe de pixel en tons de gris
 * @author Vlad Drelciuc et Maxym Lamothe
 * @date : 2019-05-16
 */

public class GrayPixel extends AbstractPixel {
	int pixel; // donnee du pixel

	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	GrayPixel() {
		this.pixel = 255;
	}

	/**
	 * Constructeur par parametre
	 * 
	 * @param pixel : valeur du pixel
	 */
	GrayPixel(int pixel) {
		this.pixel = pixel;

	}

	/**
	 * Renvoie la valeur du pixel
	 */
	public int getPixel() {
		return pixel;
	}

	/**
	 * Assigne une valeur au pixel
	 * 
	 * @param pixel: valeur a assigner
	 */
	public void setPixel(int pixel) {
		this.pixel = pixel;
	}

	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel() {
		if (pixel <= 127)
			return new BWPixel(false);
		else
			return new BWPixel(true);
	}

	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel() {
		return new GrayPixel(pixel);

	}

	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel() {
		int[] rgb = new int[3];
		rgb[0] = rgb[1] = rgb[2] = pixel;
		ColorPixel cp = new ColorPixel(rgb);
		return cp;
	}

	public TransparentPixel toTransparentPixel() {
		int[] rgb = new int[4];
		rgb[0] = rgb[1] = rgb[2] = pixel;
		rgb[3] = 255;
		TransparentPixel tp = new TransparentPixel(rgb);
		return tp;
	}

	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public AbstractPixel Negative() {
		return new GrayPixel(255 - this.pixel);
	}

	public void setAlpha(int alpha) {
		// ne fait rien
	}

	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier (avec un espace
	 * supplémentaire en fin)s
	 */
	public String toString() {
		return ((Integer) (pixel)).toString() + " ";
	}
}
