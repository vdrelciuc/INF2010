package TP01_1;

/**
 * Classe de pixel transparent
 * @author Vlad Drelciuc et Maxym Lamothe
 * @date : 2019-05-16
 */

public class TransparentPixel extends AbstractPixel {
	public int[] rgba; // donnees de l'image

	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	TransparentPixel() {
		rgba = new int[4];
		rgba[0] = 255;
		rgba[1] = 255;
		rgba[2] = 255;
		rgba[3] = 255;
	}

	/**
	 * Assigne une valeur au pixel
	 * 
	 * @param rgba: valeurs a assigner
	 */
	TransparentPixel(int[] rgba) {
		this.rgba = new int[4];
		this.rgba[0] = rgba[0];
		this.rgba[1] = rgba[1];
		this.rgba[2] = rgba[2];
		this.rgba[3] = rgba[3];

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
		int[] rgb = new int[3];
		for (int i = 0; i < rgb.length; i++)
			rgb[i] = rgba[i];
		return new ColorPixel(rgb);
	}

	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public TransparentPixel Negative() {
		int[] tab = new int[4];
		for (int i = 0; i < rgba.length - 1; i++)
			tab[i] = 255 - rgba[i];
		tab[3] = rgba[3];
		return new TransparentPixel(tab);
	}

	public TransparentPixel toTransparentPixel() {
		return new TransparentPixel(this.rgba);

	}

	public void setAlpha(int alpha) {
		rgba[3] = alpha;
	}

	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier (avec un espace
	 * supplémentaire en fin)s
	 */
	public String toString() {
		return ((Integer) rgba[0]).toString() + " " + ((Integer) rgba[1]).toString() + " "
				+ ((Integer) rgba[2]).toString() + " " + ((Integer) rgba[3]).toString() + " ";
	}

	/**
	 * Renvoie la moyenne des valeurs RGBA du pixel
	 */
	private int mean() {
		int sum = 0;
		for (int i = 0; i < rgba.length; i++) {
			sum += rgba[i];
		}
		return sum / rgba.length;
	}
}
