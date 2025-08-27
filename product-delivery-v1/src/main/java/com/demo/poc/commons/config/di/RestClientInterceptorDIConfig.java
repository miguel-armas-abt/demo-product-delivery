//package com.demo.poc.commons.config.di;
//
//import com.demo.poc.commons.core.interceptor.restclient.RestClientRequestInterceptor;
//import com.demo.poc.commons.core.interceptor.restclient.RestClientResponseInterceptor;
//import com.demo.poc.commons.core.logging.ThreadContextInjector;
//import com.demo.poc.commons.core.properties.ConfigurationBaseProperties;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.enterprise.inject.Produces;
//
//@ApplicationScoped
//public class RestClientInterceptorDIConfig {
//
//  @Produces
//  public RestClientRequestInterceptor restClientRequestInterceptor(ConfigurationBaseProperties properties,
//                                                                   ThreadContextInjector threadContextInjector) {
//    return new RestClientRequestInterceptor(properties, threadContextInjector);
//  }
//
//  @Produces
//  public RestClientResponseInterceptor restClientResponseInterceptor(ConfigurationBaseProperties properties,
//                                                                    ThreadContextInjector threadContextInjector) {
//    return new RestClientResponseInterceptor(properties, threadContextInjector);
//  }
//}
