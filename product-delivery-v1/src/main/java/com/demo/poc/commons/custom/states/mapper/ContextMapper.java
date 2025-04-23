package com.demo.poc.commons.custom.states.mapper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.demo.poc.commons.core.config.MappingConfig;
import com.demo.poc.commons.custom.exceptions.ParsingJsonException;
import com.demo.poc.commons.custom.states.context.Context;
import com.demo.poc.commons.custom.states.context.ProductDeliveryContext;
import com.demo.poc.commons.custom.states.dto.response.ContextResponse;
import com.demo.poc.commons.custom.states.State;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MappingConfig.class, imports = State.class)
public interface ContextMapper {

  @Mapping(target = "nextState", expression = "java(context.getCurrentState().name())")
  @Mapping(target = "ciphered", source = "context", qualifiedByName = "mapCiphered")
  ContextResponse toResponse(Context context);

  @Named("mapCiphered")
  static String mapCiphered(Context context) {
    ObjectMapper objectMapper = new ObjectMapper();
    String contextJson;

    try {
      contextJson = objectMapper.writeValueAsString(context);
    }
    catch (JsonProcessingException exception) {
      throw new ParsingJsonException(exception.getMessage());
    }
    return new String(Base64.getEncoder().encode(contextJson.getBytes(StandardCharsets.UTF_8)));
  }

  default ProductDeliveryContext toContext(String ciphered) {
    String contextJson = new String(Base64.getDecoder().decode(ciphered));
    ObjectMapper objectMapper = new ObjectMapper();
    ProductDeliveryContext context;

    try {
      context = objectMapper.readValue(contextJson, ProductDeliveryContext.class);
    }
    catch (JsonProcessingException exception) {
      throw new ParsingJsonException(exception.getMessage());
    }
    return context;
  }
}
