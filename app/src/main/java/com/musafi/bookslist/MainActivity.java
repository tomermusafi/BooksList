package com.musafi.bookslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList <Book> books;
    ArrayList <Book> all_books;
    RecyclerView books_RCV;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        books_RCV = findViewById(R.id.main_RCV_books);

        createBooksList();
        all_books = new ArrayList<>(books);
        bookAdapter = new BookAdapter(this, books);
        initList();

        bookAdapter.SetOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Book book) {
                Toast toast = Toast.makeText(MainActivity.this,"The book "+ book.getName() + "is chosen at index " + position , Toast.LENGTH_LONG);
//                View view1 = toast.getView();
//                view1.setBackgroundColor(Color.WHITE);
//
//                TextView text = (TextView) view1.findViewById(android.R.id.message);
//                /*Here you can do anything with above textview like text.setTextColor(Color.parseColor("#000000"));*/
//                text.setTextColor(Color.BLACK);
                toast.show();
            }
        });

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = findViewById(R.id.main_SV_search);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                bookAdapter.getFilter().filter(s);
                return false;
            }
        });

    }
    private  void initList() {
        books_RCV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        books_RCV.setLayoutManager(layoutManager);
        books_RCV.setAdapter(bookAdapter);
    }


    private void createBooksList() {
        try {
            JSONObject json = new JSONObject(getJsonFromAssets());
            JSONArray jsonArr = json.getJSONArray("data");
            books = new ArrayList<>();
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
    }
    private String getJsonFromAssets(){
        String json;
        try {
            InputStream inputStream = getAssets().open("books.json");
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