package com.brewery.controller;

import com.brewery.service.BreweryService;
import com.lib.brewery.api.BreweryApi;
import com.lib.brewery.models.Brewery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
public class BreweryController implements BreweryApi {

    private BreweryService breweryService;

    @Autowired
    public BreweryController(BreweryService breweryService){
        this.breweryService = breweryService;
    }

    @Override
    public ResponseEntity<Brewery> getBrewery(@NotNull @Valid String breweryId) {
        Brewery brewery =  breweryService.getBrewery(breweryId);
        if(brewery == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Brewery>(brewery,HttpStatus.OK );
    }

    @Override
    public ResponseEntity<List<Brewery>> searchBrewery(@NotNull @Valid String query) {
        List<Brewery> result = breweryService.searchBrewery(query);
        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @Override
    public ResponseEntity<List<Brewery>> searchBreweryByState(@NotNull @Valid String query) {
        List<Brewery> result = breweryService.getBreweryByState(query);
        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }


}
