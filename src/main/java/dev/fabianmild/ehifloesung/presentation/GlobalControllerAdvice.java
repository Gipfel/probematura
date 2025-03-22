package dev.fabianmild.ehifloesung.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ProblemDetail> handleException(RuntimeException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        System.out.printf("An %s error occurred: %s", e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity.badRequest().body(problemDetail);
    }

}
