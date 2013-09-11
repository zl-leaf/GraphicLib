package net.hci.graphic.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

public class SobelUtils {
	/**
	 * Sobel算法
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Bitmap Sobel(Bitmap bitmap) {

		bitmap = CommenUtils.getSmallBitmap(bitmap, 480, 800);
		Bitmap temp = CommenUtils.toGrayscale(bitmap);
		int w = temp.getWidth();
		int h = temp.getHeight();

		int[] mmap = new int[w * h];
		double[] tmap = new double[w * h];
		int[] cmap = new int[w * h];

		temp.getPixels(mmap, 0, temp.getWidth(), 0, 0, temp.getWidth(),
				temp.getHeight());

		double max = Double.MIN_VALUE;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				tmap[j * w + i] = Math.sqrt(GX(i, j, temp) * GX(i, j, temp)
						+ GY(i, j, temp) * GY(i, j, temp));
				if (max < tmap[j * w + i]) {
					max = tmap[j * w + i];
				}
			}
		}

		double top = max * 0.07;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (tmap[j * w + i] > top) {
					cmap[j * w + i] = mmap[j * w + i];
				} else {
					cmap[j * w + i] = 0;
				}
			}
		}
		return Bitmap.createBitmap(cmap, temp.getWidth(), temp.getHeight(),
				Config.ARGB_8888);
	}

	/**
	 * 获取横向的
	 * 
	 * @param x 第x行
	 * @param y 第y列
	 * @param bitmap
	 * @return
	 */
	public static double GX(int x, int y, Bitmap bitmap) {
		double res = (-1) * getPixel(x - 1, y - 1, bitmap) + 1
				* getPixel(x + 1, y - 1, bitmap) + (-Math.sqrt(2))
				* getPixel(x - 1, y, bitmap) + Math.sqrt(2)
				* getPixel(x + 1, y, bitmap) + (-1)
				* getPixel(x - 1, y + 1, bitmap) + 1
				* getPixel(x + 1, y + 1, bitmap);
		return res;
	}

	/**
	 * 获取纵向的
	 * 
	 * @param x 第x行
	 * @param y 第y列
	 * @param bitmap
	 * @return
	 */
	public static double GY(int x, int y, Bitmap bitmap) {
		double res = 1 * getPixel(x - 1, y - 1, bitmap) + Math.sqrt(2)
				* getPixel(x, y - 1, bitmap) + 1
				* getPixel(x + 1, y - 1, bitmap) + (-1)
				* getPixel(x - 1, y + 1, bitmap) + (-Math.sqrt(2))
				* getPixel(x, y + 1, bitmap) + (-1)
				* getPixel(x + 1, y + 1, bitmap);
		return res;
	}

	/**
	 * 获取第x行第y列的色度
	 * @param x 第x行
	 * @param y 第y列
	 * @param bitmap
	 * @return
	 */
	public static double getPixel(int x, int y, Bitmap bitmap) {
		if (x < 0 || x >= bitmap.getWidth() || y < 0 || y >= bitmap.getHeight())
			return 0;
		return bitmap.getPixel(x, y);
	}
}
