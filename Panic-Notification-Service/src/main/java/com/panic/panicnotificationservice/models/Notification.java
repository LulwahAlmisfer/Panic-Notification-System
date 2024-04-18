package com.panic.panicnotificationservice.models;

import com.panic.panicnotificationservice.models.Client;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "value", nullable = false, length = Integer.MAX_VALUE)
    private String value;

    @ManyToMany(mappedBy = "notifications")
    private Set<Client> clients = new LinkedHashSet<>();

}