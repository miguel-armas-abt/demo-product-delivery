package com.demo.poc.commons.custom.states.dto.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ContextRequest implements Serializable {

    @NotEmpty
    @Pattern(regexp = "^(CONTACT_DATA|AVAILABLE_DATES|RESERVATION|RETRY)$")
    private String previousState;

    @NotEmpty
    private String ciphered;
}
