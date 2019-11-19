package TP01_1;

import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM Implemente les methodes de
 * ImageOperations
 * @author Vlad Drelciuc et Maxym Lamothe
 * @date : 2019-05-16
 */

public class PixelMapPlus extends PixelMap implements ImageOperations {
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * 
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName) {
		super(fileName);
	}

	/**
	 * Constructeur copie
	 * 
	 * @param type  : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image) {
		super(image);
	}

	/**
	 * Constructeur copie (sert a changer de format)
	 * 
	 * @param type  : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image) {
		super(type, image);
	}

	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * 
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h    : hauteur (height) de l'image
	 * @param w    : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w) {
		super(type, h, w);
	}

	/**
	 * Genere le negatif d'une image
	 */
	public void negate() {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				imageData[i][j] = imageData[i][j].Negative();

	}

	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage() {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				imageData[i][j] = imageData[i][j].toBWPixel();
		imageType = imageType.BW;

	}

	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage() {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				imageData[i][j] = imageData[i][j].toGrayPixel();
		imageType = imageType.Gray;

	}

	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage() {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				imageData[i][j] = imageData[i][j].toColorPixel();
		imageType = imageType.Color;

	}

	public void convertToTransparentImage() {
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				imageData[i][j] = imageData[i][j].toTransparentPixel();
		imageType = imageType.Transparent;

	}

	/**
	 * Modifie la longueur et la largeur de l'image
	 * 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	/**
	 *
	 */
	public void resize(int w, int h) throws IllegalArgumentException {
		if (w < 0 || h < 0)
			throw new IllegalArgumentException();

		double factorH = (double) height / (double) h;
		double factorW = (double) width / (double) w;

		AbstractPixel[][] newImage = new AbstractPixel[h][w];
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				newImage[i][j] = imageData[(int) (i * factorH)][(int) (j * factorW)];

		imageData = newImage;
		height = h;
		width = w;

	}

	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void insert(PixelMap pm, int row0, int col0) {

		for (int i = 0; i < pm.height; i++)
			for (int j = 0; j < pm.width; j++)
				if ((i + row0 < height) && (j + col0 < width))
					imageData[i + row0][j + col0] = pm.imageData[i][j];
	}

	/**
	 * Decoupe l'image
	 */
	public void crop(int h, int w) {
		if (h > 0 && w > 0) {
			AbstractPixel[][] newImage = new AbstractPixel[h][w];

			for (int i = 0; i < h; i++)
				for (int j = 0; j < w; j++)
					newImage[i][j] = new BWPixel(true); // create a white picture

			for (int i = 0; i < h; i++)
				for (int j = 0; j < w; j++)
					if (i < height && j < width)
						newImage[i][j] = imageData[i][j];

			imageData = newImage;
			height = h;
			width = w;
		}

	}

	/**
	 * Effectue une translation de l'image
	 */
	public void translate(int rowOffset, int colOffset) {
		AbstractPixel[][] newImage = new AbstractPixel[height][width];

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				newImage[i][j] = new BWPixel(true); // create a white picture

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				if ((i + rowOffset < height && j + colOffset < width) && (i + rowOffset >= 0 && j + colOffset >= 0))
					newImage[i + rowOffset][j + colOffset] = imageData[i][j];

		imageData = newImage;
	}

	public void flip(char flipMod) {
		int rowOffset, colOffset;

		switch (flipMod) {
		case 'V':
			rowOffset = -height + 1;
			colOffset = 0;
			break;
		case 'H':
			rowOffset = 0;
			colOffset = -width + 1;
			break;
		default:
			rowOffset = -height + 1;
			colOffset = -width + 1;
			break;
		}

		AbstractPixel[][] newImage = new AbstractPixel[height][width];

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				newImage[i][j] = new BWPixel(true);// create a white picture

		int a = 0, b = 0;

		for (int i = rowOffset; i < height + rowOffset; i++) {
			for (int j = colOffset; j < width + colOffset; j++) {
				newImage[Math.abs(i)][Math.abs(j)] = imageData[a][b];
				b++;
			}
			b = 0;
			a++;
		}
		imageData = newImage;
	}

	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor
	 * 
	 * @param x          : colonne autour de laquelle le zoom sera effectue
	 * @param y          : rangee autour de laquelle le zoom sera effectue
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException {
		if (zoomFactor < 1.0)
			throw new IllegalArgumentException();

		int w = (int) Math.round(width / zoomFactor);
		int h = (int) Math.round(height / zoomFactor);

		int r = Math.max(0, y - h / 2);
		int c = Math.max(0, x - w / 2);

		if (r + h > height)
			r = height - h;
		if (c + w > width)
			c = width - w;

		double ratio = 1.0 / zoomFactor;

		AbstractPixel[][] oldImage = imageData;
		AllocateMemory(imageType, height, width);

		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				imageData[i][j] = oldImage[(int) (i * ratio) + r][(int) (j * ratio) + c];
			}
		}
	}

}
