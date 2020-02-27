package com.example.dell.beekeeping.Models;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.print.PrinterId;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Image_Model {
    private String caption;
    private String image;

    private static final int PREFERRED_WIIDTH = 250;
    private static final int PREFERRED_HEIGHT = 250;
    public static final int col_id = 0;
    public static final int col_caption = 1;
    public static final int col_image= 2;

    public Image_Model(Cursor cursor){
        this.caption = cursor.getColumnName(col_caption);
        this.image = cursor.getColumnName(col_image);
    }

    public Image_Model (String caption, Bitmap image) {
        this.caption = caption;
        this.image = BitmapToString(ImageResize(image));
    }

    public String getCaption () {
        return caption;
    }

    public Bitmap getImage () {
        return StringToBitmap(this.image);
    }

    public String getImageAsString(){
        return this.image;
    }

    private String BitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b,Base64.DEFAULT);
    }

    private Bitmap StringToBitmap(String string ){
        Bitmap bitmap = null;
        try {
           byte[] encodeByte = Base64.decode(string,Base64.DEFAULT);
           bitmap = BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);

        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }

    private Bitmap ImageResize(Bitmap bitmap){

        int width = bitmap.getWidth();
        int Height = bitmap.getHeight();
        float scaleWidth = PREFERRED_WIIDTH / width;
        float scaleHeight =PREFERRED_HEIGHT / Height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmapResize = Bitmap.createBitmap(bitmap,0,0,width,Height,matrix,false);
        bitmapResize.recycle();

        return  bitmapResize ;
    }
}
