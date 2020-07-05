package com.borysov.agileengine.helpers;

import com.borysov.agileengine.models.AuthResponse;
import com.borysov.agileengine.models.Item;
import com.borysov.agileengine.properties.CommonProperties;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RequestHelper {

    public static AuthResponse getAuth() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String body = "{ \"apiKey\": \"23567b218376f79d9415\" }";

        HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);

        ResponseEntity<AuthResponse> exchange =
                restTemplate.exchange(CommonProperties.AUTH_URI, HttpMethod.POST, entity, AuthResponse.class);
        return exchange.getBody();
    }

    public static Map<Integer, List> getImages(String token) throws URISyntaxException {
        HashMap result = new HashMap();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        boolean hasMore = true;
        int page = 1;


        ResponseEntity<Object> exchange = null;

        while (hasMore) {
            exchange = restTemplate.exchange(CommonProperties.IMAGES_URI + "?page=" + page, HttpMethod.GET, entity, Object.class);

            if (exchange.getBody() != null) {
                Map body = (Map) exchange.getBody();

                result.put((Integer) body.get("page"), (List) body.get("pictures"));

                hasMore = (boolean) body.get("hasMore");
                page++;
            }
        }
        return result;
    }

    public static Item getImageInfo(String id, String token) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<Item> item =
                restTemplate.exchange(CommonProperties.IMAGES_URI + "/" + id, HttpMethod.GET, entity, Item.class);

        return item.getBody();
    }
}
