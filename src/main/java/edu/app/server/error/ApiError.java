package edu.app.server.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

/**
 * Sirve para enviar respuestas de los errores que se producen al realizar un petición del usuario.
 */
@Data
public class ApiError {
    /**
     * Es el estado de la petición que se hizo.
     */
    private HttpStatus status;
    /**
     * Es el mensajed de error que se envia.
     */
    private String message;
    /**
     * Son los errores que se registran del trace de errores.
     */
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        this.errors = Collections.singletonList(error);
    }
}