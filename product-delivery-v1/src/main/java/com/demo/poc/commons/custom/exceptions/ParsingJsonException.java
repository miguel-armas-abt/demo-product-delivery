package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class ParsingJsonException extends GenericException {

    public ParsingJsonException(String message) {
        super(message, ErrorDictionary.parse(ParsingJsonException.class));
    }
}
