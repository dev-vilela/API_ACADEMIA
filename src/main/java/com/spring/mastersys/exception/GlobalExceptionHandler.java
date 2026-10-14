package com.spring.mastersys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
// Indica que esta classe irá interceptar exceções de todos os controllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Este método será executado quando ocorrer um erro de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarErroValidacao(MethodArgumentNotValidException ex) {

        // Obtém todos os erros de validação
        List<String> mensagens = ex.getBindingResult()

                // Pega apenas os erros dos campos
                .getFieldErrors()

                // Converte a lista para Stream para manipulação
                .stream()

                // Monta mensagens como:
                // nome: não pode estar vazio
                // email: formato inválido
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())

                // Converte novamente para lista
                .toList();

        // Cria o objeto de resposta de erro
        ErroResponse response = new ErroResponse(

                // Data e hora do erro
                LocalDateTime.now(),

                // Código HTTP 400
                HttpStatus.BAD_REQUEST.value(),

                // Mensagem geral
                "Erro de validação",

                // Lista com os erros encontrados
                mensagens
        );

        // Retorna HTTP 400 com o objeto de erro
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    // Trata exceções de regra de negócio criadas por você
    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroResponse> tratarRegraNegocio(RegraNegocioException ex){

        // Cria o objeto de resposta
        ErroResponse response = new ErroResponse(

                // Data e hora do erro
                LocalDateTime.now(),

                // Código HTTP 400
                HttpStatus.BAD_REQUEST.value(),

                // Tipo do erro
                "Erro de regra de negócio",

                // Mensagem da exceção
                List.of(ex.getMessage())
        );

        // Retorna HTTP 400 com a mensagem de erro
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
