package io.swagger.api;

import io.swagger.model.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.web.service.CategoryDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-02T22:03:18.108Z")

@Controller
public class CategoryApiController implements CategoryApi {

    private static final Logger log = LoggerFactory.getLogger(CategoryApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Autowired
    private CategoryDaoService categoryDaoService;

    @org.springframework.beans.factory.annotation.Autowired
    public CategoryApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addcategory(@ApiParam(value = "category object that needs to be added" ,required=true )  @Valid @RequestBody Category body) {
        String accept = request.getHeader("Accept");
        categoryDaoService.save(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deletecategory(@ApiParam(value = "category object that needs to be deleted" ,required=true )  @Valid @RequestBody Category body) {
        String accept = request.getHeader("Accept");
        categoryDaoService.deleteById(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updatecategory(@ApiParam(value = "Category object that needs to be added" ,required=true )  @Valid @RequestBody Category body) {
        String accept = request.getHeader("Accept");
        categoryDaoService.update(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
