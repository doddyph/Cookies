package me.nitelab.cookies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import me.nitelab.cookies.adapter.MoviesAdapter;
import me.nitelab.cookies.model.Movie;

public class MainActivity extends AppCompatActivity
        implements MoviesAdapter.OnItemCheckedListener {
    private static final String TAG = "MainActivity";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.checked_all)
    CheckBox cbCheckedAll;

    private List<Movie> movies = new ArrayList<>();
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        moviesAdapter = new MoviesAdapter(movies, this);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(moviesAdapter);

        prepareData();
    }

    private void prepareData() {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movies.add(movie);
        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movies.add(movie);
        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movies.add(movie);
        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        movies.add(movie);
        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015");
        movies.add(movie);
        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        movies.add(movie);
        movie = new Movie("Up", "Animation", "2009");
        movies.add(movie);
        movie = new Movie("Star Trek", "Science Fiction", "2009");
        movies.add(movie);
        movie = new Movie("The LEGO Movie", "Animation", "2014");
        movies.add(movie);
        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        movies.add(movie);
        movie = new Movie("Aliens", "Science Fiction", "1986");
        movies.add(movie);
        movie = new Movie("Chicken Run", "Animation", "2000");
        movies.add(movie);
        movie = new Movie("Back to the Future", "Science Fiction", "1985");
        movies.add(movie);
        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movies.add(movie);
        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
        movies.add(movie);
        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movies.add(movie);
        moviesAdapter.notifyDataSetChanged();
    }

    @OnCheckedChanged(R.id.checked_all)
    public void onCheckedAll(CheckBox checkBox, boolean checked) {
        moviesAdapter.doCheckedAll(checked);
    }

    @OnClick(R.id.button_ok)
    public void onOK() {
        List<Movie> movies = moviesAdapter.getMovies();
        StringBuilder builder = new StringBuilder();
        for (Movie movie : movies) {
            if (movie.isChecked()) builder.append(movie.getTitle()).append("\n");
        }
        String txtToast = "";
        if (builder.length() > 0) {
            int lastIdx = builder.lastIndexOf("\n");
            builder.deleteCharAt(lastIdx);
            txtToast = builder.toString();
        }
        if (!txtToast.isEmpty()) Toast.makeText(this, txtToast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemChecked() {
        int counter = 0;
        List<Movie> movies = moviesAdapter.getMovies();
        for (Movie movie : movies) if (movie.isChecked()) counter++;
        if (counter == movies.size()) cbCheckedAll.setChecked(true);
    }

    @Override
    public void onItemUncheck() {
        List<Movie> movies = moviesAdapter.getMovies();
        int counter = 0;
        for (Movie movie : movies) if (movie.isChecked()) counter++;
        if (counter == 0) cbCheckedAll.setChecked(false);
    }
}
