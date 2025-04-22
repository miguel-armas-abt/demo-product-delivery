package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class NotFoundAvailableDatesException extends GenericException {

    public NotFoundAvailableDatesException() {
        super(ErrorDictionary.NOT_FOUND_AVAILABLE_DATES.getMessage(), ErrorDictionary.parse(NotFoundAvailableDatesException.class));
    }
}
