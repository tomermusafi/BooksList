package com.musafi.bookslist.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class MyJsonManager {

    /**
     * Get a Json from a json file in string format
     * @param context The context of the current state of the application
     * @param fileName The name of the json file
     * @return Return the json of the file in string format
     */
    public static String getJsonFromAssets(Context context, String fileName){
        String json;
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
