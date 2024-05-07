package com.panic.panicnotificationservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "url", nullable = false, length = Integer.MAX_VALUE)
    private String url;

    @Column(name = "method", nullable = false, length = Integer.MAX_VALUE)
    private String method;

    @Column(name = "authorization_header", length = Integer.MAX_VALUE)
    private String authorizationHeader;

    private String apiKey = String.valueOf(UUID.randomUUID());

    @ManyToMany
    @JoinTable(name = "client_notification",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id"))
    private Set<Notification> notifications = new LinkedHashSet<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "client_person",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> person = new LinkedHashSet<>();

}