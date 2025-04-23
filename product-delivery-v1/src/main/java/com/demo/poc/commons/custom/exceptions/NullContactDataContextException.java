package com.demo.poc.commons.custom.exceptions;

import com.demo.poc.commons.core.errors.exceptions.GenericException;
import lombok.Getter;

@Getter
public class NullContactDataContextException extends GenericException {

    public NullContactDataContextException() {
        super(ErrorDictionary.NULL_CONTACT_DATA_CONTEXT.getMessage(), ErrorDictionary.parse(NullContactDataContextException.class));
    }
}
