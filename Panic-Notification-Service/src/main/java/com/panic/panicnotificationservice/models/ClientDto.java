package com.panic.panicnotificationservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * DTO for {@link com.panic.panicnotificationservice.models.Client}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto implements Serializable {
    String name;
    String url;
    String method;
    String authorizationHeader;
}