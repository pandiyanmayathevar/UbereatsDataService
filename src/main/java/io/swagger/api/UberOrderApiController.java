package io.swagger.api;

import io.swagger.model.UberOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.web.service.UberOrderDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-02T22:03:18.108Z")

@Controller
public class UberOrderApiController implements UberOrderApi {

    private static final Logger log = LoggerFactory.getLogger(UberOrderApiController.class);

    private final ObjectMapper objectMapper;

    @Autowired
    private UberOrderDaoService UberOrderDaoService;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UberOrderApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addUberOrder(@ApiParam(value = "UberOrder object that needs to be added" ,required=true )  @Valid @RequestBody UberOrder body) {
        String accept = request.getHeader("Accept");
        UberOrderDaoService.save(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
