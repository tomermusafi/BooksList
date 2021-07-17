package com.musafi.bookslist.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MyImageManager {

    /**
     * Load an image into an imageView
     * @param context The context of the current state of the application
     * @param url The URL of the image
     * @param placeholderColor The color of the placeholder
     * @param imageView The target imageView
     */
    public static void loadImage(Context context, String url, int placeholderColor, ImageView imageView){
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(new ColorDrawable(placeholderColor))
                .into(imageView);
    }
}
