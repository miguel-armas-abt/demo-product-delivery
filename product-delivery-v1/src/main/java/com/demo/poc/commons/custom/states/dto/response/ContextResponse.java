package com.demo.poc.commons.custom.states.dto.response;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ContextResponse implements Serializable {

    @NotEmpty
    @Pattern(regexp = "^(AVAILABLE_DATES|RESERVATION|RETRY|COORDINATION)$")
    private String nextState;

    @NotEmpty
    private String ciphered;
}
