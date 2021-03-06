package io.swagger.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xml.internal.utils.URI;
import io.swagger.model.Restaurant;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.web.service.CategoryDaoService;
import io.swagger.web.service.RestaurantDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-02T22:03:18.108Z")

@Controller
public class StoreApiController implements StoreApi {

    private static final Logger log = LoggerFactory.getLogger(StoreApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private RestaurantDaoService restaurantDaoService ;

    @Autowired
    private CategoryDaoService categoryDaoService;

    @org.springframework.beans.factory.annotation.Autowired
    public StoreApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addrestaurant(@ApiParam(value = "Restaurant object that needs to be added" ,required=true )  @Valid @RequestBody Restaurant body) {
        String accept = request.getHeader("Accept");
        if (categoryDaoService.validCategoryId(body.getCategoryId()))
        {
        restaurantDaoService.save(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
        }else{return new ResponseEntity<Void>(HttpStatus.valueOf(404));}

    }

    public ResponseEntity<Void> deleteRestaurant(@ApiParam(value = "Restaurant object that needs to be deleted" ,required=true )  @Valid @RequestBody Restaurant body) {
        String accept = request.getHeader("Accept");
        restaurantDaoService.deleteById(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List> getRestaurant() {
        String accept = request.getHeader("Accept");
        // if (accept != null && accept.contains("application/json")) {
         List<Restaurant> list =  restaurantDaoService.findAll();

        ResponseEntity<List> responseEntity = new ResponseEntity<List>(list,HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<List> getRestaurantWithCategory(@ApiParam(value = "CategoryName",required=true) @PathVariable("categoryName") String categoryName) {
        String accept = request.getHeader("Accept");

        int categoryId = 0;

        try {
            categoryId = categoryDaoService.findByCategoryName(categoryName);
            if (categoryId != 0) {
                List<Restaurant> list = restaurantDaoService.findByCategoryId(categoryId);
                ResponseEntity<List> responseEntity = new ResponseEntity<List>(list, HttpStatus.OK);
                return responseEntity;
            }
        } catch (Exception e){
            log.error(e.getMessage()) ;
        }

        return new ResponseEntity<List>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List> getRestaurantWithlocation(@NotNull @ApiParam(value = "filterByLocation", required = true) @Valid @RequestParam(value = "filterByLocation", required = true) String filterByLocation) {
        String accept = request.getHeader("Accept");
        List<Restaurant> list =  restaurantDaoService.findByPostalCode(filterByLocation);
        ResponseEntity<List> responseEntity = new ResponseEntity<List>(list,HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<Void> updateRestaurant (@ApiParam(value = "Restaurant object that needs to be added" ,required=true )  @Valid @RequestBody Restaurant body) {
        String accept = request.getHeader("Accept");

        if (categoryDaoService.validCategoryId(body.getCategoryId()))
        {
            restaurantDaoService.update(body);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else {
                return new ResponseEntity<Void>(HttpStatus.valueOf(404));
            }

    }

}
