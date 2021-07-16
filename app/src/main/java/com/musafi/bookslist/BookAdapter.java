package com.musafi.bookslist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private Context context;
    private List<Book> books;
    private List<Book> AllBooks;
    private OnItemClickListener mItemClickListener;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
        this.AllBooks = new ArrayList<>(books);
    }

    public void updateList(ArrayList<Book> states) {
        this.books = states;
        notifyDataSetChanged();

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
            Log.d("pttt","hhh: "+ book.getCover());
            int color = Color.rgb(book.getPlaceholderColor().getRed(),book.getPlaceholderColor().getGreen(),book.getPlaceholderColor().getBlue());
            Glide
                    .with(context)
                    .load(book.getCover())
                    .centerCrop()
                    .placeholder(new ColorDrawable(color))
                    .into(genericViewHolder.cover);

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
                    // TODO: 7/16/2021
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
            books.clear();
            books.addAll((Collection<?extends Book>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView name;
        private TextView author;
        private ImageView cover;


        public ViewHolder(final View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.book_li_LBL_title);
            this.author = itemView.findViewById(R.id.book_li_LBL_body);
            this.cover = itemView.findViewById(R.id.book_li_IMG_url);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), getItem(getAdapterPosition()));
                }
            });
        }
    }

    public void removeAt(int position) {
        books.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, books.size());
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Book book);
    }
}
