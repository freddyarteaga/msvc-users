package com.farteaga.arkam.msvc_users.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity //Entidad: Objeto persistente/Instancia DB
@Table(name = "users")
@Data //libreria que automatiza setters, getters, constructores, etc.
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AI
    private Long id;

    //NotNull -> No permite que sea nulo, cualquier tipo de objeto
    //NotEmpty -> Tamaño sea mayor a 0, " ", String, Colecciones, Arrays
    @NotBlank(message = "First name is mandatory") //String
    private String firstName;

    @Size(min = 2, max = 50) // String, Listas, Colecciones
    private String lastName;

    @Email
    private String email;

    @Column(columnDefinition = "TEXT") //name, length
    private String bio;

    @Past
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date birthdate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    //Asignan fechas automáticas de creación y actualización.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }



}