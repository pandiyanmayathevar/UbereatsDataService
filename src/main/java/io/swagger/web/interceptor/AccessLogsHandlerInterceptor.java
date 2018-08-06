package io.swagger.web.interceptor;

import com.google.gson.Gson;
import io.swagger.common.constants.CommonConstants;
import io.swagger.common.dto.RequestMetadata;
import io.swagger.common.dto.RequestedResource;
import io.swagger.exception.InvalidDataException;
import io.swagger.api.CategoryApiController;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * The accessLogsInterceptor for all microservices. It retrieves the request-metadata
 * from the requestHeaders and persists it in logs. In near future the support for persisting the logs
 * into a persistent store will be turned on as well. 
 */
@Component
@Slf4j
public class AccessLogsHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(CategoryApiController.class);
    private static final String REQUEST_URL_STR_CONSTANT= "Request URL::";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("entered preHandle");
        
//        RequestedResource rqResource = new RequestedResource();
//        rqResource.setRequestUri(request.getRequestURI());
//        String requestUrl = StringUtils.isNotBlank(request.getRequestURL()) ? request.getRequestURL().toString() : "";
//        rqResource.setRequestUrl(requestUrl);
//        request.setAttribute(CommonConstants.REQUESTED_RESOURCE, rqResource);
////
//        if (StringUtils.isNotBlank(request.getHeader(CommonConstants.REQUEST_METADATA_HEADER))) {
//            String requestMetaDataJsonStr = request.getHeader(CommonConstants.REQUEST_METADATA_HEADER);
//            processClientMetaData(requestMetaDataJsonStr, request);
//        } else {
//            InvalidDataException invalidDataException = new InvalidDataException();
//            invalidDataException.getErrorMap().put(CommonConstants.REQUEST_METADATA_HEADER,
//                "client_metadata request header cannot be missing or have blank value");
//            throw invalidDataException;
//        }
        // log the basic perf metrics
        logEntryTimings(request);
        //
        log.debug("exited preHandle");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("entered afterCompletion");
        // basic perf metrics logs
        logExitTimings(request);
        //
        log.debug("exited afterCompletion");
    }

    private void processClientMetaData(String clientMetaDataHeader, HttpServletRequest request) {
        deserializeClientMetadata(clientMetaDataHeader, request);
        RequestMetadata clientMetaData = (RequestMetadata) request.getAttribute(CommonConstants.REQUEST_METADATA);
        log.debug("logging clientMetaData: " + clientMetaData);
    }

    private void deserializeClientMetadata(String clientMetaDataJsonStr, HttpServletRequest request) {
        com.google.gson.Gson gson = new Gson();


        //
        RequestMetadata requestMetadata;
        try {
            requestMetadata = gson.fromJson(clientMetaDataJsonStr, RequestMetadata.class);
            request.setAttribute("request-metadata",requestMetadata);
        } catch (Exception e) {
        	log.error(e.getMessage());
            InvalidDataException invalidDataException = new InvalidDataException(e);
            invalidDataException.getErrorMap().put(CommonConstants.REQUEST_METADATA_HEADER,
                "Error while parsing the request_metadata headerr=" + clientMetaDataJsonStr);
            throw invalidDataException;
        }
        validateClientMetadata(requestMetadata);
        request.setAttribute(CommonConstants.REQUEST_METADATA, requestMetadata);
    }

    private void validateClientMetadata(RequestMetadata requestMetadata) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<RequestMetadata>> constraintViolations = validator.validate(requestMetadata);
        if (constraintViolations != null && !constraintViolations.isEmpty()) {
            InvalidDataException invalidDataException = new InvalidDataException();
            for (ConstraintViolation<RequestMetadata> constraintViolation : constraintViolations) {
                String propertyPath = constraintViolation.getPropertyPath().toString();
                String message = constraintViolation.getMessage();
                invalidDataException.getErrorMap().put(propertyPath, message);
            }
            throw invalidDataException;
        }
    }

    private void logExitTimings(HttpServletRequest request) {
        long startTime = (Long) request.getAttribute("startTime");
        log.info(REQUEST_URL_STR_CONSTANT + request.getRequestURL().toString() + ":: End Time=" + System.currentTimeMillis());
        log.info(REQUEST_URL_STR_CONSTANT + request.getRequestURL().toString() + ":: Time Taken=" + (System.currentTimeMillis() - startTime));
    }

    private void logEntryTimings(HttpServletRequest request) {
        long startTime = System.currentTimeMillis();
        log.info(REQUEST_URL_STR_CONSTANT + request.getRequestURL().toString() + ":: Start Time=" + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);
    }
}