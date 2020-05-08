package in.sonakshi.moviemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.sonakshi.moviemanager.adapter.MovieAdapter;
import in.sonakshi.moviemanager.models.Movie;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_movie);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // 1. Layout Manager
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Movie> movies = new ArrayList< >();
        List < String > gs = new ArrayList <>();
        gs.add ("Thriller"); gs.add ("Comedy"); gs.add ("SciFi"); gs.add ("Romantic");
        for (int i = 0; i < 20; i++) {
            movies.add (new Movie (i, "Title" + i, "image" + i, 5 + i, 2010 + i, gs));
        }

        // 2. Adapter
        mAdapter = new MovieAdapter(movies);
        recyclerView.setAdapter(mAdapter);

    }
}
