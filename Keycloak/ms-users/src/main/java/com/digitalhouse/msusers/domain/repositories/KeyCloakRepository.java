package com.digitalhouse.msusers.domain.repositories;

import com.digitalhouse.msusers.domain.models.User;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class KeyCloakRepository implements UserRepository{

    @Autowired
    private Keycloak keycloak;

    @Value("${dh.keycloak.realm}")
    private String realm;

    @Override
    public User findUser(String id) {
        UserResource userResource = keycloak.realm(realm).users().get(id);
        UserRepresentation user = userResource.toRepresentation();
        return User.from(user);
    }
}
