package org.example.filterservice.client.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    private Integer id;
    private String name;
    private String url;
    private String method;
    private String authorizationHeader;

}
