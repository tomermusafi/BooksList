package com.musafi.bookslist.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class MySignalManager {

    /**
     * Make a custom Toast by changing the background color and the text color
     * @param context The context of the current state of the application
     * @param text The alert text
     * @param backgroundColor the background color of the Toast
     * @param textColor The text color of the Toast
     */
    public static void makeMyToast(Context context, String text, int backgroundColor, int textColor){
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        View _view = toast.getView();
        _view.getBackground().setColorFilter(backgroundColor, PorterDuff.Mode.SRC_IN);
        TextView textView = _view.findViewById(android.R.id.message);
        textView.setTextColor(textColor);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}
