package net.hci.graphic.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * 共用的工具
 * @author leaf
 *
 */
public class CommenUtils {
	/**
	 * 转化成灰度图
	 * @param bmpOriginal
	 * @return
	 */
	public static Bitmap toGrayscale(Bitmap bmpOriginal) {
		
		int width, height;
		height = bmpOriginal.getHeight();
		width = bmpOriginal.getWidth();

		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		Canvas c = new Canvas(bmpGrayscale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpOriginal, 0, 0, paint);
		return bmpGrayscale;
	}
	
	/**
	 * Bitmap压缩
	 * 
	 * @param bm
	 * @param height
	 * @param width
	 * @return
	 */
	public static Bitmap compress(final Bitmap bm, int reqWidth, int reqHeight) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		
		if (height > reqHeight || width > reqWidth) { 
			float scaleWidth = (float) reqWidth / width;
			float scaleHeight = (float) reqHeight / height;
			float scale = scaleWidth < scaleHeight ? scaleWidth : scaleHeight;

			Matrix matrix = new Matrix();
			matrix.postScale(scale, scale);
			Bitmap result = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
					bm.getHeight(), matrix, true);
			bm.recycle();
			return result;
		}
		return bm;
	}
}
