package br.com.meli.wave4.exceptions.advice;

import br.com.meli.wave4.exceptions.InvalidSectionException;
import br.com.meli.wave4.exceptions.StandardError;
import br.com.meli.wave4.exceptions.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class ApiExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError err = new ValidationError(System.currentTimeMillis(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error",
                "Erro de validacao na requisicao",
                request.getRequestURI());
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler(InvalidSectionException.class)
    public ResponseEntity<StandardError> invalidSection(InvalidSectionException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Invalid Section", "Setor inválido", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
