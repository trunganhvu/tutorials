package org.anhvt.springbootelk.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Represents request body for Create User operation.
 * Necessary for proper Swagger documentation.
 *
 * @author trunganhvu
 */
@Getter
@Setter
@ToString
public class UserResourceRequest implements Serializable {

    private static final long serialVersionUID = 2657944775357946081L;

    @Schema(description = "User's first name", required = true)
    @NonNull
    private String firstName;

    @Schema(description = "User's last name", required = true)
    @NonNull
    private String lastName;

    @Schema(description = "Country where user's living", required = true)
    @NonNull
    private String country;

}