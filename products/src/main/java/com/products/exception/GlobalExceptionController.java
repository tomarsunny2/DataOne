package com.products.exception;

import com.products.model.ErrorBean;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
* Global Exception class will handle all exception thrown by application. 
* To DO : handle all type of exceptions
*/

@ControllerAdvice
public class GlobalExceptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);

	
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
