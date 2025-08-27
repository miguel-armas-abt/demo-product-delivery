package com.demo.poc.commons.config.di;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
    componentModel = "cdi",
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class MappingConfig {
}
