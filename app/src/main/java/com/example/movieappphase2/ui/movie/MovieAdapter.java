package com.example.movieappphase2.ui.movie;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieappphase2.utils.ItemClickListener;
import com.example.movieappphase2.data.model.Moviedata;
import com.example.movieappphase2.databinding.FilmcardBinding;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemViewHolder> {

    private ArrayList<Moviedata> movies;
    ItemAdapterListener itemAdapterListener;

    public void setItemAdapterListener(ItemAdapterListener itemAdapterListener) {
        this.itemAdapterListener = itemAdapterListener;
    }

    public MovieAdapter() {
        movies=new ArrayList<>();
    }

    public void clearItems(){

        movies.clear();
    }
    public void addItems(ArrayList<Moviedata> items){
        if(items!=null){
            this.movies.addAll(items);
            notifyDataSetChanged();}

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(getItemCount()>0){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FilmcardBinding filmcardBinding = FilmcardBinding.inflate(layoutInflater, parent, false);
        return new ItemViewHolder(filmcardBinding);}
        else{

            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if(movies.size()>0){
            holder.onBindItem(movies.get(position));}
    }

    @Override
    public int getItemCount() {
        return movies != null && movies.size() > 0 ? movies.size() : 1;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements MovieItemViewModel.ItemViewModelListener  {

        FilmcardBinding itemBinding;

        public ItemViewHolder(@NonNull FilmcardBinding itemBinding) {
            super(itemBinding.getRoot());


            this.itemBinding=itemBinding;
        }
        public void onBindItem(Moviedata item) {
            // set Data to variable to set each specific Item
            itemBinding.setMoveitem(new MovieItemViewModel(item,this,itemBinding.filmposter));

            itemBinding.executePendingBindings();
        }


        @Override
        public void onItemClick(Moviedata item, ImageView poster) {
            if(item!=null)
                itemAdapterListener.onItemClick(item,poster);
        }
    }
    public interface ItemAdapterListener extends ItemClickListener {}



}
