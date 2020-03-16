package com.fabian.myphonebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContactExceptionHandler {

    @ExceptionHandler(value = ContactNotFoundException.class)
        public ResponseEntity<Object> exception(ContactNotFoundException exception)
        {
            return new ResponseEntity<>("Contact not found", HttpStatus.NOT_FOUND);
        }


}
