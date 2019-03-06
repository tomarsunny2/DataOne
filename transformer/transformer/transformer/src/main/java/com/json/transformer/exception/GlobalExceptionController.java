package com.json.transformer.exception;


import com.json.transformer.model.ErrorBean;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;

/*
* Global Exception class will handle all exception thrown by application. 
* To DO : handle all type of exceptions
*/

@ControllerAdvice
public class GlobalExceptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);

	@ExceptionHandler(value=HttpMessageNotReadableException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorBean handleResourceAccessException(HttpServletResponse httpResponse, HttpMessageNotReadableException exp){
		LOGGER.error("Error occurred: {}",exp.getMessage(),exp);
		ErrorBean errorBean = new ErrorBean();
                errorBean.setErrorCode("BAD_REQUEST");
		errorBean.setUserMessage("Requested JSON not valid");
		httpResponse.setContentType("application/json");
		return errorBean;
	}
        
        @ExceptionHandler(value=HttpRequestMethodNotSupportedException.class)
        @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public @ResponseBody ErrorBean handleOfferNotFoundException(HttpServletResponse httpResponse, HttpRequestMethodNotSupportedException exp){
		LOGGER.error("Error occurred: {}",exp.getMessage(),exp);
		ErrorBean errorBean = new ErrorBean();
                errorBean.setErrorCode("METHOD_NOT_ALLOWED");
		errorBean.setUserMessage("The request method does not support.");
		httpResponse.setContentType("application/json");
		return errorBean;
	}
        @ExceptionHandler(value=NoHandlerFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorBean handleApplicationNotFoundException(HttpServletResponse httpResponse, NoHandlerFoundException exp){
		LOGGER.error("Error occurred: {}",exp.getMessage(),exp);
		ErrorBean errorBean = new ErrorBean();
                errorBean.setErrorCode("NOT_FOUND");
		errorBean.setUserMessage("resource not found");
		httpResponse.setContentType("application/json");
		return errorBean;
	}
        
        @ExceptionHandler(value=Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorBean handleResourceAccessException(HttpServletResponse httpResponse, Exception exp){
		LOGGER.error("Error occurred: {}",exp.getMessage(),exp);
		final ErrorBean errorBean = new ErrorBean();
                errorBean.setErrorCode("INTERNAL_SERVER_ERROR");
		errorBean.setUserMessage("Error while performing user operation");
		httpResponse.setContentType("application/json");
		return errorBean;
	}

  
}
