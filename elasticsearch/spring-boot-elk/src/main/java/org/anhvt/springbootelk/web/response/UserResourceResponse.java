package org.anhvt.springbootelk.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Represents response body when {@link org.anhvt.springbootelk.domain.User} resource has to be returned
 * Necessary for proper Swagger documentation.
 *
 * @author trunganhvu
 */
@AllArgsConstructor
@Getter
public class UserResourceResponse implements Serializable {

    private static final long serialVersionUID = -8761235292937715094L;

    @Schema(description = "Unique user identifier", required = true)
    private Long id;

    @Schema(description = "User's first name", required = true)
    private String firstName;

    @Schema(description = "User's last name", required = true)
    private String lastName;

    @Schema(description = "Country where user's living", required = true)
    private String country;
}
