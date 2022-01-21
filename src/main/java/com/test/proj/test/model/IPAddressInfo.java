package com.test.proj.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * http://ip-api.com/json/{query}?fields=status,country,city,query
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IPAddressInfo {

    private String status;
    private String country;
    private String city;
    private String message;

    private String query;

    public IPAddressInfo() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "IPAddressInfo{" +
                "status='" + status + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", message='" + message + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
