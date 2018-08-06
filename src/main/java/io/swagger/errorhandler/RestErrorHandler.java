package io.swagger.errorhandler;

import io.swagger.utils.MessageUtils;
import io.swagger.common.dto.DetailedMessage;
import io.swagger.common.dto.ErrorInfo;
import io.swagger.exception.AppRuntimeException;
import io.swagger.exception.InvalidDataException;
import io.swagger.exception.NoDataFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the global error handler for all REST based microservices
 * written using Spring REST framework.
 *
 */
@RestControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private HttpServletRequest httpRequest;

    /**
     * This method handles all the cases where a given object fails the simple JSR-303 
     * validation as annotated in the class definition using JSR-303 annotations like @NotBlank
     * (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleMethodArgumentNotValid(org.springframework.web.bind.MethodArgumentNotValidException, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        logger.error(MessageUtils.printStackTrace(ex));
        ErrorInfo errorInfo = new ErrorInfo();
        List<DetailedMessage> detailedMessages = new ArrayList<>();

        errorInfo.setDetailedMessages(detailedMessages);
        if (ex.getBindingResult() != null && ex.getBindingResult().getAllErrors() != null) {
	        ex.getBindingResult().getAllErrors().forEach(objectError -> {
	        	DetailedMessage detailedMessage = new DetailedMessage();
	            detailedMessage.setMessage(objectError.getDefaultMessage());
	            detailedMessage.setObject(objectError.getObjectName());
	            if(objectError instanceof FieldError){
	                detailedMessage.setField(((FieldError)objectError).getField());
	            }
	            detailedMessages.add(detailedMessage);
	        });
        }

        errorInfo.setHttpStatusCode(status.toString());
        errorInfo.setHttpStatusMessage(status.getReasonPhrase());
        //checkAndPopulateRequestURL(errorInfo);
        return new ResponseEntity(errorInfo, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ErrorInfo errorInfo = populateErrorInfo(ex);
        //checkAndPopulateRequestURL(errorInfo);
        errorInfo.setHttpStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorInfo.setHttpStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<Object>(
                errorInfo, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    //
    
//    /**
//     * This method handles all InValid data cases. They can be input data validation failures or busines rules
//     * validation failures. And converts them into HTTP_404 Bad_request message highlighting the actual object.field
//     * that caused the validation rule failure.
//     * @param ex
//     * @param request
//     * @return ResponseEntity
//     */
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Object> handleException(final InvalidDataException ex) {
        logger.error(MessageUtils.printStackTrace(ex));
        ErrorInfo errorInfo = populateErrorInfo(ex);
        //checkAndPopulateRequestURL(errorInfo);
        errorInfo.setHttpStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorInfo.setHttpStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        return new ResponseEntity(errorInfo, HttpStatus.BAD_REQUEST);
    }

//    /**
//     * This method is used by called APIs to signal that there's no data available for a given parameter value.
//     * An HTTP_204 status code is sent to the calling client.
//     * @param ex
//     * @param request
//     * @return ResponseEntity
//     */
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleException(final NoDataFoundException ex) {
        logger.error(MessageUtils.printStackTrace(ex));
        ErrorInfo errorInfo = populateErrorInfo(ex);
        //checkAndPopulateRequestURL(errorInfo);
        errorInfo.setHttpStatusCode(HttpStatus.NO_CONTENT.toString());
        errorInfo.setHttpStatusMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
        //
        return new ResponseEntity(errorInfo, HttpStatus.NO_CONTENT);
    }
    
//    /**
//     * This method handles the case when GET based requestParameter validation fails.
//     * @param ex
//     * @param request
//     * @return ResponseEntity
//     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleException(final ConstraintViolationException ex) {
    	ErrorInfo errorInfo = new ErrorInfo();
    	List<DetailedMessage> msg = new ArrayList<>();
    	if (ex.getConstraintViolations() != null) {
	    	ex.getConstraintViolations().forEach(message -> {
	    		DetailedMessage detailedMessage = new DetailedMessage();
	        	detailedMessage.setMessage(message.getMessage());
	        	msg.add(detailedMessage);
	    	});
    	}
    	errorInfo.setDetailedMessages(msg);
    	return new ResponseEntity(errorInfo, HttpStatus.BAD_REQUEST);
    }
    
    
    /**
     * This method handles all runtimeExceptions like Host-Not-Reachable or Network-down etc.
     * @param ex
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleException(final Throwable ex, WebRequest request) {
        logger.error(MessageUtils.printStackTrace(ex));
        ErrorInfo errorInfo = new ErrorInfo();

        List<DetailedMessage> detailedMessages = new ArrayList<>();
        errorInfo.setDetailedMessages(detailedMessages);
        DetailedMessage detailedMessage = new DetailedMessage();
        //checkAndPopulateRequestURL(errorInfo);
        errorInfo.getDetailedMessages().add(detailedMessage);
        errorInfo.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errorInfo.setHttpStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(ex.getMessage());
        strBuilder.append("---");
        strBuilder.append(MessageUtils.printStackTrace(ex));
	        detailedMessage.setMessage(strBuilder.toString());

        return new ResponseEntity(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
//    private void checkAndPopulateRequestURL(ErrorInfo errorInfo) {
//    	RequestedResource rqResource = (RequestedResource)httpRequest.getAttribute(CommonConstants.REQUESTED_RESOURCE);
//
//        if(rqResource!=null && StringUtils.isNotBlank(rqResource.getRequestUrl())){
//            errorInfo.setUrl(rqResource.getRequestUri());
//        }
//    }
    
    private ErrorInfo populateErrorInfo(final AppRuntimeException ex) {
        logger.error(MessageUtils.printStackTrace(ex));
        ErrorInfo errorInfo = new ErrorInfo();
        List<DetailedMessage> detailedMessages = new ArrayList<>();
        if (ex.getErrorMap() != null) {
	        ex.getErrorMap().forEach((key,value) -> {
	        	DetailedMessage detailedMessage = new DetailedMessage();
	            detailedMessage.setMessage(value);
	            detailedMessage.setObject(key);
	            detailedMessages.add(detailedMessage);
	        });
        }
        errorInfo.setDetailedMessages(detailedMessages);
        return errorInfo;
    }

    private ErrorInfo populateErrorInfo(final MethodArgumentTypeMismatchException ex) {
        logger.error(MessageUtils.printStackTrace(ex));
        ErrorInfo errorInfo = new ErrorInfo();
        List<DetailedMessage> detailedMessages = new ArrayList<>();
        DetailedMessage detailedMessage = new DetailedMessage();
        detailedMessage.setMessage(ex.getMessage());
        detailedMessage.setObject(ex.getName());
        detailedMessages.add(detailedMessage);

        errorInfo.setDetailedMessages(detailedMessages);
        return errorInfo;
    }
}