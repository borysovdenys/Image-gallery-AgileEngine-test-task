package com.borysov.agileengine.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException {

    private static final String ERROR_MSG = "Entity %s <%s> not found.";

    private final Class clazz;
    private final Object id;

    @Override
    public String getMessage() {
        return String.format(ERROR_MSG, getClazz().getSimpleName(), getId());
    }
}
