package com.ma.pedidos.exceptions;

import com.ma.pedidos.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionsHandler {

    @ResponseBody
    @ExceptionHandler(ProductoNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorDTO nombreProvisorioSameToDefinitivoException(ProductoNotFoundException ex){
        return new ErrorDTO(ex.getMessage());
    }
}
