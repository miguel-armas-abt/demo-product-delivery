package com.demo.service.entrypoint.contactdata.mapper;

import java.util.List;

import com.demo.commons.config.mapper.MappingConfig;
import com.demo.service.commons.states.context.contactdata.AddressContext;
import com.demo.service.commons.states.dto.response.ContextResponse;
import com.demo.service.entrypoint.contactdata.dto.response.ContactDataResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappingConfig.class)
public interface ContactDataResponseMapper {

  @Mapping(target = "addresses", source = "addresses")
  ContactDataResponseDto toResponse(ContextResponse context, List<AddressContext> addresses);
}
