package com.bs.assignment.exception;

import com.bs.assignment.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private ErrorAttributes errorAttributes;

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Map<String, Object> handleCustomExceptions(WebRequest request,
                                                      HttpServletResponse response, ServiceException ex) {
        response.setStatus(ex.getResponseStatus().value());
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(request, false);
        errorAttributes.put("status", ex.getResponseStatus().value());
        errorAttributes.put("error", ex.getResponseStatus().getReasonPhrase());
        errorAttributes.put("message", ex.getMessage());
        return errorAttributes;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handleException(WebRequest request, Exception ex) {
        LOGGER.error("Error occurred servicing the request", ex);
        Map<String, Object> result = this.errorAttributes.getErrorAttributes(request, false);
        result.put("status", INTERNAL_SERVER_ERROR.value());
        result.put("error", "Internal Server Error");
        result.put("message", "An internal server error occurred");
        return result;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = METHOD_NOT_ALLOWED)
    @ResponseBody
    public Map<String, Object> handleHttpRequestMethodNotSupportedException(WebRequest request,
                                                                            HttpRequestMethodNotSupportedException ex) {
        Map<String, Object> result = this.errorAttributes.getErrorAttributes(request, false);
        result.put("status", METHOD_NOT_ALLOWED.value());
        result.put("error", "Method Not Allowed");
        result.put("message", ex.getMessage());
        return result;
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public Map<String, Object> handleHttpMediaTypeNotSupportedException(WebRequest request,
                                                                        HttpMediaTypeNotSupportedException ex) {
        Map<String, Object> result = this.errorAttributes.getErrorAttributes(request, false);
        result.put("status", UNSUPPORTED_MEDIA_TYPE.value());
        result.put("error", "Unsupported media type.");
        result.put("message", ex.getMessage());
        return result;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleHttpMessageNotReadableException(WebRequest request,
                                                                     HttpMessageNotReadableException ex) {
        return badRequest(request, "Request contains field type error(s)");
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleHttpArgumentNotReadableException(WebRequest request,
                                                                      MethodArgumentTypeMismatchException ex) {
        String message = format("'%s' property contains invalid value", ex.getName());
        return badRequest(request, message);
    }

    @ExceptionHandler(InvalidPropertyException.class)
    @ResponseStatus(value = BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> handleHttpArgumentNotReadableException(WebRequest request,
                                                                      InvalidPropertyException ex) {
        String message = format("'%s' property contains invalid value", ex.getPropertyName());
        return badRequest(request, message);
    }

    private Map<String, Object> badRequest(WebRequest request, String message) {
        Map<String, Object> result = this.errorAttributes.getErrorAttributes(request, false);
        result.put("status", BAD_REQUEST.value());
        result.put("error", "Bad Request");
        result.put("message", message);
        return result;
    }
}
