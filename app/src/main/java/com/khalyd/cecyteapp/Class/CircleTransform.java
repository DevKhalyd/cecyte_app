package com.khalyd.cecyteapp.Class;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;


public class CircleTransform extends BitmapTransformation {

    //TODO Esta clase sirve para poner la foto de forma circular
    //Segun entiendo
    //TODO = QHE HACER (SEGUN EL TRADUCTOR)


    //El constuctor se genera a partir de los errores si se solucionan se crea u know
    public CircleTransform (Context context) {
        super(context);
    }


    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool,toTransform);
    }

    private static Bitmap circleCrop(BitmapPool pool, Bitmap source){
        if (source == null) return null;

        int size = Math.min(source.getWidth(),source.getHeight());
        int x =(source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        // TODO this could be acquired from the pool too

        //Solo se db de poner lo q te pide

        Bitmap squared = Bitmap.createBitmap(source,x,y,size,size);

        //Cuando dice q vas a poner, el primero es el tipo y el segundo su name

        Bitmap result = pool.get(size,size, Bitmap.Config.ARGB_8888);
        if (result == null){
            result = Bitmap.createBitmap(size,size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared,BitmapShader.TileMode.CLAMP,BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;  //Float se usa para otros objetos y no para poner numeros enteros o sea q tal vez esto sea un numero con decimal
        canvas.drawCircle(r,r,r,paint);
        return result;

        //Hasta que se dibujo el circulo
    }


    @Override
    public String getId() {
        return getClass().getName();
    }
}
