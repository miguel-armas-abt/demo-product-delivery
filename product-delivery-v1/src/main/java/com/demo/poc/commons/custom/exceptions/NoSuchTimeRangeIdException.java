package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class NoSuchTimeRangeIdException extends GenericException {

    public NoSuchTimeRangeIdException(String timeRangeId) {
        super(ErrorDictionary.NO_SUCH_TIME_RANGE_ID.getMessage() + ": " + timeRangeId, ErrorDictionary.parse(NoSuchTimeRangeIdException.class));
    }
}
