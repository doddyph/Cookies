package me.nitelab.cookies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import me.nitelab.cookies.R;
import me.nitelab.cookies.model.Movie;


/**
 * Created by dph on 29/07/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private static final String TAG = "MoviesAdapter";
    private List<Movie> movies;
    private OnItemCheckedListener itemCheckedListener;

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public MoviesAdapter(List<Movie> movies, OnItemCheckedListener itemCheckedListener) {
        this.movies = movies;
        this.itemCheckedListener = itemCheckedListener;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_list, parent, false);
        return new MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvGenre.setText(movie.getGenre());
        holder.tvYear.setText(movie.getYear());
        holder.cbMovie.setChecked(movie.isChecked());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void doCheckedAll(boolean checked) {
        Movie movie;
        for (int i = 0; i < movies.size(); i++) {
            movie = movies.get(i);
            if (checked != movie.isChecked()) {
                movie.setChecked(checked);
                movies.set(i, movie);
            }
        }
        notifyDataSetChanged();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView tvTitle;
        @BindView(R.id.genre)
        TextView tvGenre;
        @BindView(R.id.year)
        TextView tvYear;
        @BindView(R.id.movie_checkbox)
        CheckBox cbMovie;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnCheckedChanged(R.id.movie_checkbox)
        public void onCheckedChange(CheckBox checkBox, boolean checked) {
            int index = getLayoutPosition();
            Movie movie = movies.get(index);
            if (checked != movie.isChecked()) {
                movie.setChecked(checked);
                movies.set(index, movie);
                if (itemCheckedListener != null) {
                    if (checked) itemCheckedListener.onItemChecked();
                    else itemCheckedListener.onItemUncheck();
                }
            }
        }
    }

    public interface OnItemCheckedListener {
        void onItemChecked();
        void onItemUncheck();
    }
}
