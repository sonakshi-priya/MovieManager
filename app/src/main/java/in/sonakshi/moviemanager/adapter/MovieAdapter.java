package in.sonakshi.moviemanager.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.sonakshi.moviemanager.R;
import in.sonakshi.moviemanager.models.Movie;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List < Movie > mMovies;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView movieImageView;
        private TextView nameTextView;
        private TextView ratingTextView;
        private TextView genreTextView;
        private TextView releaseYearTextView;


        public MovieViewHolder(View itemView) {
            super(itemView);

            movieImageView = itemView.findViewById(R.id.image_view_movie);
            nameTextView = itemView.findViewById(R.id.text_view_movie_name);
            ratingTextView = itemView.findViewById(R.id.text_view_movie_rating);
            genreTextView = itemView.findViewById(R.id.text_view_movie_genre);
            releaseYearTextView = itemView.findViewById(R.id.text_view_movie_releaseYear);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MovieAdapter(List< Movie > movies) {
        mMovies = movies;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View movieView = inflater.inflate(R.layout.movie_items, parent, false);

        // Return a new holder instance
        return new MovieViewHolder(movieView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Movie movie = mMovies.get(position);

        TextView nameTextView = holder.nameTextView;
        nameTextView.setText(movie.getTitle());
        TextView ratingTextView = holder.ratingTextView;
        ratingTextView.setText("Rating: " + String.valueOf(movie.getRating()));
        TextView genreTextView = holder.genreTextView;
        StringBuilder genreStringBuilder = new StringBuilder();
        int index = 0;
        for (String genre : movie.getGenre()) {
            if (index > 0) {
                genreStringBuilder.append (", ");
            }
            genreStringBuilder.append(genre);
            index++;
        }
        genreTextView.setText(genreStringBuilder.toString());
        TextView releaseYearTextView = holder.releaseYearTextView;
        releaseYearTextView.setText(String.valueOf(movie.getReleaseYear()));
        ImageView imageView = holder.movieImageView;
        String imageUrl = movie.getImage();

        Picasso.get()
                .load(Uri.parse(imageUrl))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.image_not_found)
                .into(imageView);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
