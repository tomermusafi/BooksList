package com.musafi.bookslist.adapters;

import android.content.Context;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.musafi.bookslist.R;
import com.musafi.bookslist.models.Book;
import com.musafi.bookslist.utils.MyImageManager;
import com.musafi.bookslist.utils.MyTextManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Context context;
    private List<Book> books;
    private List<Book> AllBooks;
    private OnItemClickListener mItemClickListener;
    static private CharSequence cs;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
        this.AllBooks = new ArrayList<>(books);
        cs = "";
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final Book book = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.name.setText(book.getName());
            genericViewHolder.author.setText(book.getAuthor());
            genericViewHolder.rating.setRating(book.getRating()
            );
            int color = Color.rgb(
                    book.getPlaceholderColor().getRed(),
                    book.getPlaceholderColor().getGreen(),
                    book.getPlaceholderColor().getBlue());
            MyImageManager.loadImage(context, book.getCover(), color, genericViewHolder.cover);

            MyTextManager.highlightMatchText(context,genericViewHolder.name,genericViewHolder.author,cs.toString(), R.color.teal_200);


        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    private Book getItem(int position) {
        return books.get(position);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Book> filteredList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(AllBooks);
            }else{
                for(Book book : AllBooks){
                    if(book.getName().toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                            book.getAuthor().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(book);

                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            cs = charSequence;
            books.clear();
            books.addAll((Collection<?extends Book>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView author;
        private ShapeableImageView cover;
        private RatingBar rating;


        public ViewHolder(final View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.book_li_LBL_title);
            this.author = itemView.findViewById(R.id.book_li_LBL_body);
            this.cover = itemView.findViewById(R.id.book_li_IMG_url);
            this.rating = itemView.findViewById(R.id.book_li_RB_rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), getItem(getAdapterPosition()));
                }
            });
        }
    }


    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Book book);
    }
}
