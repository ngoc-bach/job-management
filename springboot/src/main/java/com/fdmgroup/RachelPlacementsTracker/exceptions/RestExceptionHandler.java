package com.fdmgroup.RachelPlacementsTracker.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.fdmgroup.RachelPlacementsTracker.exceptions.NotFoundException;
import com.fdmgroup.RachelPlacementsTracker.exceptions.AccessDeniedException;
import com.fdmgroup.RachelPlacementsTracker.exceptions.MethodNotAllowedException;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class RestExceptionHandler {
	/* Have to use these next lines of code for every exception you create */

	// The exception we want to handle
	@ExceptionHandler(NotFoundException.class)
	// The HTTP status you want associated - eg. 404 in this case
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse handleNotFound(NotFoundException e) {
		return new ApiErrorResponse(e.getMessage());
	}

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrorResponse handleAccessDenied(AccessDeniedException e) {
        return new ApiErrorResponse(e.getMessage());
    }
    
    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ApiErrorResponse handle(MethodNotAllowedException e) {
        return new ApiErrorResponse(e.getMessage());
    }
}
