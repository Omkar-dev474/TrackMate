package com.example.user_service.Service;

import java.util.Collections;
import java.util.List;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import jakarta.ws.rs.core.Response;
import com.example.user_service.dto.RequestUserDto;

@Service
public class KeycloakService {

    private final Keycloak keycloak;
 private static final String REALM = "TrackMate";
    public KeycloakService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public String createUser(RequestUserDto dto) {
      UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setRealmRoles(List.of(
        "ROLE_USERS",
        "ROLE_PARTNER"
));

        CredentialRepresentation credential =
                new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(dto.getPassword());
        credential.setTemporary(false);

        user.setCredentials(Collections.singletonList(credential));

        Response response = keycloak.realm(REALM)
                .users()
                .create(user);

        if (response.getStatus() != 201) {

    String error = response.readEntity(String.class);

    System.out.println("Keycloak Error: " + error);

    throw new RuntimeException(
            "Failed to create user in Keycloak. Status: "
                    + response.getStatus()
                    + " Error: " + error);
}
        System.out.println("Response Status = " + response.getStatus());
       String userId = CreatedResponseUtil.getCreatedId(response);
       return userId;

    }
}
