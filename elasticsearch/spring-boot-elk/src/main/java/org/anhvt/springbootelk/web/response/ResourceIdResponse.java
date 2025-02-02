package org.anhvt.springbootelk.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Specific Response which is used for the cases when only resource identifier has to be present in the response.
 * Necessary for proper Swagger documentation.
 *
 * @author trunganhvu
 */
@SuppressWarnings("unused")
@AllArgsConstructor
@Getter
public class ResourceIdResponse implements Serializable {

    private static final long serialVersionUID = -7692197744600484558L;

    @Schema(description = "Resource identifier", required = true)
    private Long id;
}
