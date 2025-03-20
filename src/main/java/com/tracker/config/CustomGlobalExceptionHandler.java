package com.tracker.config;

import com.tracker.cat.CatTrackerException;
import com.tracker.cat.CatTrackerInternalException;
import com.tracker.common.presentation.model.TrackerErrorResModel;
import com.tracker.dog.DogTrackerException;
import com.tracker.dog.DogTrackerInternalException;
import com.tracker.entity.EntityException;
import com.tracker.search.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        TrackerErrorResModel resModel = new TrackerErrorResModel(Instant.now(), errors);
        return new ResponseEntity<>(resModel, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value
            = {EntityException.class})
    protected ResponseEntity<Object> handleGeneralException(
            Exception ex) {
        log.error(ex.getMessage(), ex);
        String errorMsg = "Internal Error Occurred while processing";
        TrackerErrorResModel resModel = new TrackerErrorResModel(Instant.now(), List.of(errorMsg));
        return new ResponseEntity<>(resModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value
            = {NoDataFoundException.class})
    protected ResponseEntity<Object> handleNoDataFoundException(
            NoDataFoundException ex) {
        log.error(ex.getMessage(), ex);
        TrackerErrorResModel resModel = new TrackerErrorResModel(Instant.now(), List.of(ex.getMessage()));
        return new ResponseEntity<>(resModel, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value
            = {CatTrackerException.class, DogTrackerException.class})
    protected ResponseEntity<Object> handleCatTrackerException(
            CatTrackerException ex) {
        log.error(ex.getMessage(), ex);
        TrackerErrorResModel resModel = new TrackerErrorResModel(Instant.now(), List.of(ex.getMessage()));
        return new ResponseEntity<>(resModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value
            = {CatTrackerInternalException.class, DogTrackerInternalException.class})
    protected ResponseEntity<Object> handleCatTrackerInternalException(
            CatTrackerInternalException ex) {
        log.error(ex.getMessage(), ex);
        TrackerErrorResModel resModel = new TrackerErrorResModel(Instant.now(), List.of(ex.getMessage()));
        return new ResponseEntity<>(resModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
