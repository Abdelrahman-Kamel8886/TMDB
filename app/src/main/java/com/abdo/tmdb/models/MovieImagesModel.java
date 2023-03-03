package com.abdo.tmdb.models;

import java.util.List;

public class MovieImagesModel {


    private List<Backdrops> backdrops;
    private int id;
    private List<Logos> logos;
    private List<Posters> posters;

    public List<Backdrops> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Backdrops> backdrops) {
        this.backdrops = backdrops;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Logos> getLogos() {
        return logos;
    }

    public void setLogos(List<Logos> logos) {
        this.logos = logos;
    }

    public List<Posters> getPosters() {
        return posters;
    }

    public void setPosters(List<Posters> posters) {
        this.posters = posters;
    }

    public static class Backdrops {
        private double aspect_ratio;
        private int height;
        private Object iso_639_1;
        private String file_path;
        private double vote_average;
        private int vote_count;
        private int width;

        public double getAspect_ratio() {
            return aspect_ratio;
        }

        public void setAspect_ratio(double aspect_ratio) {
            this.aspect_ratio = aspect_ratio;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Object getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(Object iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }

    public static class Logos {
        private double aspect_ratio;
        private int height;
        private String iso_639_1;
        private String file_path;
        private double vote_average;
        private int vote_count;
        private int width;

        public double getAspect_ratio() {
            return aspect_ratio;
        }

        public void setAspect_ratio(double aspect_ratio) {
            this.aspect_ratio = aspect_ratio;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }

    public static class Posters {
        private double aspect_ratio;
        private int height;
        private String iso_639_1;
        private String file_path;
        private double vote_average;
        private int vote_count;
        private int width;

        public double getAspect_ratio() {
            return aspect_ratio;
        }

        public void setAspect_ratio(double aspect_ratio) {
            this.aspect_ratio = aspect_ratio;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
