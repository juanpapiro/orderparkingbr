package br.com.fiap.orderparkingbr.controller.handler;

import br.com.fiap.orderparkingbr.dto.ErrorApi;
import br.com.fiap.orderparkingbr.dto.ErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerConfig {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> handleArgumentNotValid(MethodArgumentNotValidException e) {
        ErrorApi errorApi = new ErrorApi("Request com argumento(s) inválido(s)!");
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorApi.setError(new ErrorValidation(error.getField(),
                    messageSource.getMessage(error, LocaleContextHolder.getLocale())));
        });
        return ResponseEntity.badRequest().body(errorApi);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorApi> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ErrorApi(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleGlobalError(Exception e) {

        return ResponseEntity.internalServerError().build();
    }

}
