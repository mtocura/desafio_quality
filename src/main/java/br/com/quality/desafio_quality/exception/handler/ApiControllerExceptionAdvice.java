package br.com.quality.desafio_quality.exception.handler;

import br.com.quality.desafio_quality.dto.FieldExceptionDTO;
import br.com.quality.desafio_quality.utils.exception.FieldErrors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ApiControllerExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> argumentNotValidHandler(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();

        List<FieldError> fieldErrors = result.getFieldErrors();
        FieldExceptionDTO exceptions = new FieldExceptionDTO(FieldErrors.processFieldErrors(fieldErrors));

        return ResponseEntity.badRequest().body(exceptions);
    }
}
