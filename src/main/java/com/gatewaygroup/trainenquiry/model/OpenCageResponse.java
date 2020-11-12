package com.gatewaygroup.trainenquiry.model;

import java.util.ArrayList;

public class OpenCageResponse {
    private String documentation;
    ArrayList< Object > licenses = new ArrayList <> ();
    Rate RateObject;
    ArrayList < Object > results = new ArrayList <> ();
    Status StatusObject;
    Stay_informed Stay_informedObject;
    private String thanks;
    Timestamp TimestampObject;
    private float total_results;


    public String getDocumentation() {
        return documentation;
    }

    public Rate getRate() {
        return RateObject;
    }

    public Status getStatus() {
        return StatusObject;
    }

    public Stay_informed getStay_informed() {
        return Stay_informedObject;
    }

    public String getThanks() {
        return thanks;
    }

    public Timestamp getTimestamp() {
        return TimestampObject;
    }

    public float getTotal_results() {
        return total_results;
    }


    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public void setRate(Rate rateObject) {
        this.RateObject = rateObject;
    }

    public void setStatus(Status statusObject) {
        this.StatusObject = statusObject;
    }

    public void setStay_informed(Stay_informed stay_informedObject) {
        this.Stay_informedObject = stay_informedObject;
    }

    public void setThanks(String thanks) {
        this.thanks = thanks;
    }

    public void setTimestamp(Timestamp timestampObject) {
        this.TimestampObject = timestampObject;
    }

    public void setTotal_results(float total_results) {
        this.total_results = total_results;
    }

    public ArrayList<Object> getLicenses() {
        return licenses;
    }

    public void setLicenses(ArrayList<Object> licenses) {
        this.licenses = licenses;
    }

    public ArrayList<Object> getResults() {
        return results;
    }

    public void setResults(ArrayList<Object> results) {
        this.results = results;
    }

    public static class Timestamp {
        private String created_http;
        private float created_unix;


        public String getCreated_http() {
            return created_http;
        }

        public float getCreated_unix() {
            return created_unix;
        }


        public void setCreated_http(String created_http) {
            this.created_http = created_http;
        }

        public void setCreated_unix(float created_unix) {
            this.created_unix = created_unix;
        }
    }
    public static class Stay_informed {
        private String blog;
        private String twitter;



        public String getBlog() {
            return blog;
        }

        public String getTwitter() {
            return twitter;
        }


        public void setBlog(String blog) {
            this.blog = blog;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }
    }
    public static class Status {
        private float code;
        private String message;



        public float getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }


        public void setCode(float code) {
            this.code = code;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    public static class Rate {
        private float limit;
        private float remaining;
        private float reset;


        public float getLimit() {
            return limit;
        }

        public float getRemaining() {
            return remaining;
        }

        public float getReset() {
            return reset;
        }


        public void setLimit(float limit) {
            this.limit = limit;
        }

        public void setRemaining(float remaining) {
            this.remaining = remaining;
        }

        public void setReset(float reset) {
            this.reset = reset;
        }
    }
}

