package com.ma.pedidos.exceptions;

import com.ma.pedidos.model.dto.ErrorDTO;
import com.ma.pedidos.model.dto.ErroresDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

@ControllerAdvice
public class ExceptionsHandler {

    @ResponseBody
    @ExceptionHandler(ProductoNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorDTO nombreProvisorioSameToDefinitivoException(ProductoNotFoundException ex){
        return new ErrorDTO(ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> invalidParamsExceptionHandler(MethodArgumentNotValidException e){
        List<ErrorDTO> errorDTOList = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(m -> {
            ErrorDTO errorDTO = ErrorDTO.builder()
                    .error(m.getDefaultMessage())
                    .build();
            errorDTOList.add(errorDTO);
        });
        ErroresDTO erroresDTO = ErroresDTO.builder()
                .errores(errorDTOList)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroresDTO);
    }
}
