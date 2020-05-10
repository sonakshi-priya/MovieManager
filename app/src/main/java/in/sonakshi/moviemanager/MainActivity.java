package in.sonakshi.moviemanager;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.sonakshi.moviemanager.adapter.MovieAdapter;
import in.sonakshi.moviemanager.models.Movie;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Movie> mMovies;

    private final String API_URL = "http://www.mocky.io/v2/5d4d3c3f330000d43f3376b0";
    private Gson gson;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mMovies = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.recycler_view_movie);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        // recyclerView.setHasFixedSize(true);

        // 1. Layout Manager
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // 2. Adapter
        mAdapter = new MovieAdapter(mMovies);
        recyclerView.setAdapter(mAdapter);

        // SwipeRefreshLayout
        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(true);

                // Fetching data from server
                loadData();
            }
        });

    }

    private void loadData() {
        // Showing refresh animation before making http call
        mSwipeRefreshLayout.setRefreshing(true);

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(API_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Log.v("JSON OBJECT", jsonObject.toString());
                        String title = jsonObject.getString("title");
                        String image = jsonObject.getString("image");
                        double rating = jsonObject.getDouble("rating");
                        int releaseYear = jsonObject.getInt("releaseYear");
                        JSONArray genreJSONArray = jsonObject.getJSONArray("genre");
                        List < String > genre = new ArrayList <>();
                        for (int j = 0; j < genreJSONArray.length(); j++) {
                            genre.add (genreJSONArray.getString(j));
                        }
                        Movie movie = new Movie (title, image, (float) rating, releaseYear,genre);
                        mMovies.add(movie);
                        mAdapter.notifyItemInserted(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                // Stopping swipe refresh
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Stopping swipe refresh
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Something went wrong.Try Again!!", Toast.LENGTH_SHORT).show();
            }
        });
        mRequestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onRefresh() {
        loadData();
    }
}
