package com.ccsit.abdulkader.movielist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.ccsit.abdulkader.movielist.api.MoviesListResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Abdul Kader on 4/23/2016.
 * Adapt the movies posters in the grid view
 */
public class MovieAdapter extends ArrayAdapter<MoviesListResponse.Result> {

    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";
    private static final String IMAGE_SIZE = "w185";

    public MovieAdapter(Context context, List<MoviesListResponse.Result> moviesList) {
        super(context, 0, moviesList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MoviesListResponse.Result result = getItem(position);

        ViewHolder viewHolder;
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.movie_list_item, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.poster_image_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        Picasso.with(getContext()).load(BASE_IMAGE_URL + IMAGE_SIZE + result.getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);
        // Return the completed view to render on screen
        return convertView;
    }

    // View lookup cache
    private static class ViewHolder {
        ImageView imageView;
    }
}
