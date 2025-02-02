package org.anhvt.springbootelk.exception;

/**
 * This exception should be thrown in all cases when a resource cannot be found
 *
 * @author trunganhvu
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Instantiates a new {@link ResourceNotFoundException}.
     *
     * @param message the message
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }
}