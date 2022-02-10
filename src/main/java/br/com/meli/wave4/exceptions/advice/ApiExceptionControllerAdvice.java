package br.com.meli.wave4.exceptions.advice;

import br.com.meli.wave4.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
@ControllerAdvice
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

    @ExceptionHandler(InvalidWarehouseException.class)
    public ResponseEntity<StandardError> invalidWarehouse(InvalidWarehouseException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Invalid Warehouse", "O armazem informado não existe!", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler(SectionNotMatchTypeProductException.class)
    public ResponseEntity<StandardError> sectionNotMatchTypeProduct(SectionNotMatchTypeProductException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Section Not Match Type Product Error", "Setor informado não corresponde ao tipo de armazenamento do lote de produtos informado", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler(RepresentativeNotCorrespondentException.class)
    public ResponseEntity<StandardError> representativeNotCorrespondent(RepresentativeNotCorrespondentException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Representative Not Correspondent Error", "Representante não pertence ao Armazém informado", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler(UnavailableSpaceException.class)
    public ResponseEntity<StandardError> unavailableSpace(UnavailableSpaceException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Unavailable Space Error", "Setor sem espaço disponível para armazenar o lote informado", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler(UnregisteredProductException.class)
    public ResponseEntity<StandardError> unregisteredProduct(UnregisteredProductException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Unregistered Product Error", "Produto não registrado em nome do vendedor", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Not Found Error", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler(DueDateLessThan3WeeksException.class)
    public ResponseEntity<StandardError> dueDateLessThan3WeeksException(DueDateLessThan3WeeksException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Due Date Less Than 3 Weeks Error", "O prazo de validade do produto é inferior a 3 semanas.", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler(ProductDoesNotBelongToTheSellerException.class)
    public ResponseEntity<StandardError> productDoesNotBelongToTheSellerException(ProductDoesNotBelongToTheSellerException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Product Does Not Belong To The Seller Error", "Um dos produtos informados não está registrado em nome do vendedor.", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<StandardError> insufficientStockException(InsufficientStockException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Insufficient Stock Error", "Não há estoque disponível.", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }


    @ExceptionHandler(BatchNotContainsProductException.class)
    public ResponseEntity<StandardError> batchNotContainsProductException(BatchNotContainsProductException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Batch Not Contains Product Error", "O Lote não pertence ao produto informado.", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(UnavailableDateException.class)
    public ResponseEntity<StandardError> unavailableDateException(UnavailableDateException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(), "Unavailable Date Error", "A data escolhida está indisponível.", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
