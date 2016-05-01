package com.ccsit.abdulkader.movielist.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Abdul Kader on 4/23/2016.
 * This interface contains the methods' definitions of the retrofit requests
 */
public interface MoviesApi {

    /**
     * This method used to get the list of movies from TheMovieDb
     * @param apiKey for authentication
     */
    @GET("movie/{sort}")
    Call<MoviesListResponse> getMovies(
            @Path("sort") String sort,
            @Query("api_key") String apiKey
    );
}
