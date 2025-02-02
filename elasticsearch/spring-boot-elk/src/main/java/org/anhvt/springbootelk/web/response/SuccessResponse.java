package org.anhvt.springbootelk.web.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.io.Serializable;

/**
 * Represents successful response when there is nothing to return
 * Necessary for proper Swagger documentation.
 *
 * @author trunganhvu
 */
@SuppressWarnings("unused")
@Getter
public class SuccessResponse implements Serializable {

    private static final long serialVersionUID = -355194637398843627L;

    @Schema(description = "Status of the response", allowableValues = "success", required = true)
    private final String status = "success";
}