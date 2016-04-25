package com.ccsit.abdulkader.movielist;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.ccsit.abdulkader.movielist.api.MoviesApi;
import com.ccsit.abdulkader.movielist.api.MoviesListResponse;
import com.ccsit.abdulkader.movielist.utils.ConnectionChecking;
import com.ccsit.abdulkader.movielist.utils.MovieAdapter;
import com.ccsit.abdulkader.movielist.utils.Values;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private MoviesApi api;
    private List<MoviesListResponse.Result> moviesList;
    private GridView gridView;

    private static int selected = 0;

    private static final String ORDINARY_TYPE = "discover";
    private static final String SORT_TYPE = "movie";

    private static final String ORDINARY_SORT = "movie";
    private static final String POPULAR_SORT = "popular";
    private static final String TOP_RATED_SORT = "top_rated";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.movies_grid);

        // Building the rest adapter for the API interface
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(MoviesApi.class);

        getMovieList(ORDINARY_TYPE, ORDINARY_SORT);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", moviesList.get(position).getTitle());
                bundle.putString("posterPath", moviesList.get(position).getPosterPath());
                bundle.putFloat("rate", moviesList.get(position).getVoteAverage());
                bundle.putString("releaseDate", moviesList.get(position).getReleaseDate());
                bundle.putString("overview", moviesList.get(position).getOverview());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort) {
            // custom dialog
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.sort_dialog);
            // Custom Android Alert Dialog Title
            dialog.setTitle("Sort by:");

            Button dialogButtonCancel = (Button) dialog.findViewById(R.id.customDialogCancel);
            Button dialogButtonOk = (Button) dialog.findViewById(R.id.customDialogOk);
            final RadioButton radioButtonPopularity = (RadioButton) dialog.findViewById(R.id.radio_popularity);
            final RadioButton radioButtonTopRated = (RadioButton) dialog.findViewById(R.id.radio_top_rated);

            if (selected == 1) {
                radioButtonPopularity.setChecked(true);
            } else if (selected == 2) {
                radioButtonTopRated.setChecked(true);
            }

            // Click cancel to dismiss android custom dialog box
            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            // Action for custom dialog ok button click
            dialogButtonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (radioButtonPopularity.isChecked()) {
                        selected = 1;
                        getMovieList(SORT_TYPE, POPULAR_SORT);
                    } else if (radioButtonTopRated.isChecked()) {
                        selected = 2;
                        getMovieList(SORT_TYPE, TOP_RATED_SORT);
                    }

                    dialog.dismiss();
                }
            });
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getMovieList(String type, String sortBy) {
        if (ConnectionChecking.isConnected(this)) {
                Call<MoviesListResponse> call = api.getMovies(type, sortBy, Values.API_KEY);
                call.enqueue(new Callback<MoviesListResponse>() {
                    @Override
                    public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {
                        moviesList = response.body().getResults();
                        gridView.setAdapter(new MovieAdapter(MainActivity.this, moviesList));
                    }

                    @Override
                    public void onFailure(Call<MoviesListResponse> call, Throwable t) {
                        Log.e("Failed ", t.toString());
                    }
                });
        } else {
            Toast.makeText(this, ConnectionChecking.NO_CONNECTION, Toast.LENGTH_LONG).show();
        }
    }
}
