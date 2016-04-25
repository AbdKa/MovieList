package com.ccsit.abdulkader.movielist.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abdul Kader on 4/23/2016.
 * This is the prototype of the getMovies response
 */
public class MoviesListResponse {
    @SerializedName("results")
    private List<Result> results = new ArrayList<Result>();

    /**
     *
     * @return
     * The results
     */
    public List<Result> getResults() {
        return results;
    }

    public class Result {
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("overview")
        private String overview;
        @SerializedName("release_date")
        private String releaseDate;
        @SerializedName("id")
        private int id;
        @SerializedName("title")
        private String title;
        @SerializedName("vote_average")
        private float voteAverage;

        /**
         *
         * @return
         * The posterPath
         */
        public String getPosterPath() {
            return posterPath;
        }

        /**
         *
         * @return
         * The overview
         */
        public String getOverview() {
            return overview;
        }

        /**
         *
         * @return
         * The releaseDate
         */
        public String getReleaseDate() {
            return releaseDate;
        }

        /**
         *
         * @return
         * The id
         */
        public int getId() {
            return id;
        }

        /**
         *
         * @return
         * The title
         */
        public String getTitle() {
            return title;
        }

        /**
         *
         * @return
         * The voteAverage
         */
        public float getVoteAverage() {
            return voteAverage;
        }
    }
}
