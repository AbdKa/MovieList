package com.ccsit.abdulkader.movielist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccsit.abdulkader.movielist.utils.Values;
import com.squareup.picasso.Picasso;

/**
 * Created by Abdul Kader on 4/25/2016.
 * This is the activity_details activity after the user selection
 */
public class DetailsActivity extends AppCompatActivity {
    private TextView titleTv, rateTv, releaseDateTv, overviewTv;
    private ImageView poster;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        
        titleTv = (TextView) findViewById(R.id.movie_title_tv);
        rateTv = (TextView) findViewById(R.id.rate_tv);
        releaseDateTv = (TextView) findViewById(R.id.release_date_tv);
        overviewTv = (TextView) findViewById(R.id.overview_tv);
        poster = (ImageView) findViewById(R.id.poster_img);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle bundle = getIntent().getExtras();
        titleTv.setText(bundle.getString("title"));
        rateTv.setText(String.valueOf(bundle.getFloat("rate")));
        releaseDateTv.setText(bundle.getString("releaseDate"));
        overviewTv.setText(bundle.getString("overview"));
        Picasso.with(this)
                .load(Values.BASE_IMAGE_URL + Values.POSTER_SIZE_DETAILS + bundle.getString("posterPath"))
                .placeholder(R.drawable.placeholder)
                .into(poster);
    }
}
