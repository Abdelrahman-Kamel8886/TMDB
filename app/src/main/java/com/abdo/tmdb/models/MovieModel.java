package com.abdo.tmdb.models;

import java.util.List;


public class MovieModel {



    private int page;
    private List<ResultsDTO> results;
    private int totalPages;
    private int totalResults;

    public int getPage() {
        return page;
    }

    public List<ResultsDTO> getResults() {
        return results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public static class ResultsDTO {
        private Boolean adult;
        private String backdrop_path;
        private List<Integer> genre_ids;
        private int id;
        private String original_language;
        private String original_title;
        private String overview;
        private Double popularity;
        private String poster_path;
        private String release_date;
        private String title;
        private Boolean video;
        private Double vote_average;
        private int vote_count;

        public Boolean getAdult() {
            return adult;
        }

        public String getBackdropPath() {
            return backdrop_path;
        }

        public List<Integer> getGenreIds() {
            return genre_ids;
        }

        public int getId() {
            return id;
        }

        public String getOriginalLanguage() {
            return original_language;
        }

        public String getOriginalTitle() {
            return original_title;
        }

        public String getOverview() {
            return overview;
        }

        public Double getPopularity() {
            return popularity;
        }

        public String getPosterPath() {
            return poster_path;
        }

        public String getReleaseDate() {
            return release_date;
        }

        public String getTitle() {
            return title;
        }

        public Boolean getVideo() {
            return video;
        }

        public Double getVoteAverage() {
            return vote_average;
        }

        public int getVoteCount() {
            return vote_count;
        }


    }
}
