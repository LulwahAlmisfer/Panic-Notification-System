package org.example.filterservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {

    private Integer personId;
    private String personName;
    private String personReason;
    private Integer clientId;
    private String clientName;
    private String clientUrl;
    private String clientMethod;
    private String clientAuthHeader;

}
