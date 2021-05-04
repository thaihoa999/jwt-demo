package com.training.util;

import com.training.dto.response.MessageResponse;
import org.springframework.http.ResponseEntity;

/**
 * The type Rest utils.
 */
public class RestUtils {

    /**
     * Build bad request response entity.
     *
     * @param messageResponse the error response
     * @return the response entity
     */
    public static ResponseEntity<MessageResponse> buildBadRequest(MessageResponse messageResponse) {
        return ResponseEntity.badRequest().body(messageResponse);
    }

    /**
     * Build ok response entity.
     *
     * @param <T>   the type parameter
     * @param value the value of response
     * @return the response entity
     */
    public static <T> ResponseEntity<T> buildOk(T value) {
        return ResponseEntity.ok(value);
    }
}
