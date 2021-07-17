package com.musafi.bookslist.utils;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.musafi.bookslist.models.Book;
import com.musafi.bookslist.models.MyRBG;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class MyListManager {

    /**
     * Create the Books list
     * @param context The context of the current state of the application
     * @param fileName The name of the Json file
     * @return ArrayList of Books
     */
    public static ArrayList<Book> createBooksList(Context context, String fileName) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(Objects.requireNonNull(MyJsonManager.getJsonFromAssets(context, fileName)));
            JSONArray jsonArr = json.getJSONArray("data");
            for (int i = 0; i < jsonArr.length(); i ++){
                JSONObject book = jsonArr.getJSONObject(i);
                books.add(new Book(book.getString("title"),
                        book.getString("body"), (float) book.getDouble("rating"),
                        new MyRBG(book.getJSONObject("placeholderColor").getInt("red"),
                                book.getJSONObject("placeholderColor").getInt("green"),
                                book.getJSONObject("placeholderColor").getInt("blue")),
                        book.getString("url")));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Initial the RecyclerView
     * @param context The context of the current state of the application
     * @param RCV The target RecyclerView
     * @param adapter The adapter of the RecyclerView
     */
    public static void initList(Context context, RecyclerView RCV, RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        RCV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        RCV.setLayoutManager(layoutManager);
        RCV.setAdapter(adapter);
    }
}
