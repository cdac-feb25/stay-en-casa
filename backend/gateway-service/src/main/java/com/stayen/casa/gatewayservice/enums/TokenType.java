package com.stayen.casa.gatewayservice.enums;

public enum TokenType {

    /**
     * <pre>
     * Access Token
     *
     * with 15 minutes validity
     * </pre>
     */
    ACCESS,

    /**
     * <pre>
     * Refresh Token
     *
     * with 24 hours validity
     * </pre>
     */
    REFRESH,

    /**
     * <pre>
     * Temporary Token
     *
     * with 1 milli-second validity
     * </pre>
     */
    TEMP;

}
