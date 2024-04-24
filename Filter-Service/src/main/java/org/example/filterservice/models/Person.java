package org.example.filterservice.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    private String id;
    private String name;
    private String reason;
    private String test;

}
