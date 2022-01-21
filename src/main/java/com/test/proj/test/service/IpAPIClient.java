package com.test.proj.test.service;

import com.test.proj.test.model.IPAddressInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class IpAPIClient {

    public static final String IP_API_BASE = "http://ip-api.com/json";
    public static final String IP_API = "/%s?fields=status,message,country,city,query";
    private WebClient client;

    public IPAddressInfo getIPAddressInfo(String ipAddress) {
        client = WebClient.builder()
                .baseUrl(IP_API_BASE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return client.get()
                .uri(String.format(IP_API, ipAddress))
                .retrieve()
                .bodyToFlux(IPAddressInfo.class).blockFirst();
    }
}
