package com.demo.poc.commons.core.config;

import com.demo.poc.commons.core.interceptor.error.ConstraintErrorInterceptor;
import com.demo.poc.commons.core.interceptor.error.ErrorInterceptor;
import com.demo.poc.commons.core.logging.ThreadContextInjector;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(targets = {
    ThreadContextInjector.class,
    ErrorInterceptor.class,
    ConstraintErrorInterceptor.class})
public class BeanRegistration {
}
