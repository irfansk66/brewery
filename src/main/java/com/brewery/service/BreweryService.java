package com.brewery.service;

import com.lib.brewery.models.Brewery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import java.util.List;



@Service
public class BreweryService {

    private static final Logger log = LoggerFactory.getLogger(BreweryService.class);

    private RestTemplate restTemplate;
    private Environment environment;

    @Autowired
    public BreweryService(RestTemplate restTemplate, Environment environment){
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    public Brewery getBrewery(String brewreyId) {
        String baseUrl = environment.getProperty("base.url");
        Brewery response = null;
        try {
            HttpEntity<String> entity = getStringHttpEntity();
            response = restTemplate.exchange(baseUrl+"/"+brewreyId, HttpMethod.GET,entity,Brewery.class).getBody();

        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        return response;
    }

    public List<Brewery> searchBrewery(String query) {
        String baseUrl = environment.getProperty("base.url");
        List<Brewery> response = null;
        try {
            HttpEntity<String> entity = getStringHttpEntity();

            ResponseEntity<Brewery[]> responseEntity = restTemplate.exchange(baseUrl+"/search?query="+query, HttpMethod.GET,entity,Brewery[].class);
            Brewery[] objects = responseEntity.getBody();
            return Arrays.asList(objects);

        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        return response;
    }


    public List<Brewery> getBreweryByState(String query) {
        String baseUrl = environment.getProperty("base.url");
        List<Brewery> response = null;
        try {
            HttpEntity<String> entity = getStringHttpEntity();

            ResponseEntity<Brewery[]> responseEntity = restTemplate.exchange(baseUrl+"?by_state="+query, HttpMethod.GET,entity,Brewery[].class);
            Brewery[] objects = responseEntity.getBody();
            return Arrays.asList(objects);

        } catch (Exception ex) {
            log.info(ex.getMessage());
        }
        return response;
    }

    private HttpEntity<String> getStringHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<String>("parameters", headers);
    }
}
