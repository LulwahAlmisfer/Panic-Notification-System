package org.example.filterservice.client.models;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    private String id;
    private String value;

}