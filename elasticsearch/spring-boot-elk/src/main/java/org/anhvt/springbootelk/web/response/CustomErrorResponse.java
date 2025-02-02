package org.anhvt.springbootelk.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * {@link CustomErrorResponse} will be returned in case of custom error occurrence
 * Necessary for proper Swagger documentation.
 *
 * @author trunganhvu
 */
@SuppressWarnings("unused")
@AllArgsConstructor
@Getter
public class CustomErrorResponse implements Serializable {

    private static final long serialVersionUID = -7755563009111273632L;

    @Schema(description = "Custom Error code", required = true)
    private String errorCode;

    @Schema(description = "Custom Error message")
    private String errorMessage;

}
