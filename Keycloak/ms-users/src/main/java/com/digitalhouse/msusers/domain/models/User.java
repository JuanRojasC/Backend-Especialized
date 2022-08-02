package com.digitalhouse.msusers.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String username;

    private String email;

    private String firstName;

    private List<Bill> bills;


    public static User from(UserRepresentation userRepresentation) {
        return new User(
                userRepresentation.getId(),
                userRepresentation.getUsername(),
                userRepresentation.getEmail(),
                userRepresentation.getFirstName(),
                List.of()
        );
    }

}
