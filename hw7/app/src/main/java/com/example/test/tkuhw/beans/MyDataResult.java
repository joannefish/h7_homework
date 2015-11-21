package com.example.test.tkuhw.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class MyDataResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


    public static class Result implements Serializable {
        private static final long serialVersionUID = 1L;

        private String offset;

        @SerializedName("limit")
        private String limitation;

        private String count;

        private String sort;

        private List<ResultItem> results;

        public List<ResultItem> getResults() {
            return results;
        }

        public void setResults(List<ResultItem> results) {
            this.results = results;
        }
    }

    public static class ResultItem implements Serializable {
        private static final long serialVersionUID = 1L;

        private String _id;
        private String ParkName;
        private String AdministrativeArea;
        private String YearBuilt;
        private String Location;
        private String Image;
        private String Introduction;

        public String get_id() {
            return _id;
        }

        public String getParkName() {
            return ParkName;
        }

        public String getAdministrativeArea() {
            return AdministrativeArea;
        }

        public String getYearBuilt(String yearBuilt) {
            return YearBuilt;
        }

        public String getLocation() {
            return Location;
        }

        public String getImage() {
            return Image;
        }

        public String getIntroduction() {
            return Introduction;
        }
    }
}