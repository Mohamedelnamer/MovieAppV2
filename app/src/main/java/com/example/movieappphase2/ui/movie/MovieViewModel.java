package com.example.movieappphase2.ui.movie;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.movieappphase2.data.ApiRepository;
import com.example.movieappphase2.data.DatabaseRepository;
import com.example.movieappphase2.data.model.Moviedata;
import com.example.movieappphase2.ui.MainActivity;

import java.util.ArrayList;

public class MovieViewModel extends ViewModel {

    private ApiRepository apiRepository;
    private MutableLiveData<ArrayList<Moviedata>> movies;
    private ObservableField<Boolean> dataReady;


    public MovieViewModel() {
        apiRepository=new ApiRepository();
        movies=new MutableLiveData<>();
        dataReady=new ObservableField<>(false);
    }
    public void getMovies(String category,int page){

        apiRepository.getListMovies().observeForever(new Observer<ArrayList<Moviedata>>() {
            @Override
            public void onChanged(ArrayList<Moviedata> moviedata) {
                movies.setValue(moviedata);
                dataReady.set(true);

            }
        });
        apiRepository.getMovies(category,page);


    }


    public ObservableField<Boolean> getDataReady() {
        return dataReady;
    }

    public MutableLiveData<ArrayList<Moviedata>> getListMovies() {
        return movies;
    }

    public void setMovies(MutableLiveData<ArrayList<Moviedata>> movies) {
        this.movies = movies;
    }
}
