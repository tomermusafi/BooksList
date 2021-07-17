package com.musafi.bookslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;

import android.os.Bundle;

import android.view.View;

import com.musafi.bookslist.adapters.BookAdapter;
import com.musafi.bookslist.models.Book;

import com.musafi.bookslist.utils.MyListManager;
import com.musafi.bookslist.utils.MySignalManager;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private ArrayList <Book> books;
    private RecyclerView books_RCV;
    private BookAdapter bookAdapter;
    SearchView searchView;
    private final String FILE_NAME = "books.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //handle night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //find views
        books_RCV = findViewById(R.id.main_RCV_books);
        searchView = findViewById(R.id.main_SV_search);

        //create books list
        books = MyListManager.createBooksList(this, FILE_NAME);
        //create the RecyclerView Adapter
        bookAdapter = new BookAdapter(this, books);
        //initial the RecyclerView
        MyListManager.initList(this,books_RCV,bookAdapter);

        bookAdapter.SetOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Book book) {
                MySignalManager.makeMyToast(MainActivity.this,
                        "The book "+ book.getName() + "is chosen at index " + position,
                        Color.WHITE, Color.BLACK);
            }
        });

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
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

}