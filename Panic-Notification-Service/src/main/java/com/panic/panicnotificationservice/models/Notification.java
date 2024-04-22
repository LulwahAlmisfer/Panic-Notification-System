package com.panic.panicnotificationservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "value", nullable = false, length = Integer.MAX_VALUE)
    private String value;

    @ManyToMany(mappedBy = "notifications")
    @JsonIgnore
    private Set<Client> clients = new LinkedHashSet<>();

}