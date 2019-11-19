package TP01_1;

/**
 * Classe de pixel en couleurs
 * @author Vlad Drelciuc et Maxym Lamothe
 * @date : 2019-05-16
 */

public class ColorPixel extends AbstractPixel {
	public int[] rgb; // donnees de l'image

	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	ColorPixel() {
		rgb = new int[3];
		rgb[0] = 255;
		rgb[1] = 255;
		rgb[2] = 255;
	}

	/**
	 * Assigne une valeur au pixel
	 * 
	 * @param rgb: valeurs a assigner
	 */
	ColorPixel(int[] rgb) {
		this.rgb = new int[3];
		this.rgb[0] = rgb[0];
		this.rgb[1] = rgb[1];
		this.rgb[2] = rgb[2];
	}

	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel() {
		if (mean() <= 127)
			return new BWPixel(false);
		else
			return new BWPixel(true);

	}

	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel() {
		return new GrayPixel(mean());
	}

	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel() {
		return new ColorPixel(this.rgb);

	}

	public TransparentPixel toTransparentPixel() {
		int[] tab = new int[4];
		for (int i = 0; i < rgb.length; i++)
			tab[i] = rgb[i];
		tab[3] = 255;
		TransparentPixel tp = new TransparentPixel(tab);
		return tp;
	}

	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public AbstractPixel Negative() {
		int[] tab = new int[3];
		for (int i = 0; i < rgb.length; i++)
			tab[i] = 255 - rgb[i];
		return new ColorPixel(tab);
	}

	public void setAlpha(int alpha) {
		// ne fait rien
	}

	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier (avec un espace
	 * supplémentaire en fin)s
	 */
	public String toString() {
		return ((Integer) rgb[0]).toString() + " " + ((Integer) rgb[1]).toString() + " " + ((Integer) rgb[2]).toString()
				+ " ";
	}

	/**
	 * Renvoie la moyenne des valeurs RGB du pixel
	 */
	private int mean() {
		int sum = 0;
		for (int i = 0; i < rgb.length; i++) {
			sum += rgb[i];
		}
		return sum / rgb.length;
	}
}