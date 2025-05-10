package com.demo.poc.commons.core.errors.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public enum ErrorOrigin {
    OWN, PARTNER, THIRD_PARTY
}
