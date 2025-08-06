const appDomain = import.meta.env.VITE_APP_DOMAIN;

const paths = {
    authUrl: '/api/v1/auth',
    authTokenUrl: '/api/v1/auth/token',
    usersUrl: '/api/v1/users',
    propertyUrl: '/api/v1/properties',
}

const Endpoints = {
    /**
     * Auth related urls
     */
    login: `${appDomain}${paths.authUrl}/login`,
    signupOtp: `${appDomain}${paths.authUrl}/signup-otp`,
    signup: `${appDomain}${paths.authUrl}/signup`,
    logout: `${appDomain}${paths.authUrl}/logout`,
    forgotPassword: `${appDomain}${paths.authUrl}/forgot-password`,
    changePassword: `${appDomain}${paths.authUrl}/change-password`,

    /**
     * Auth-Token related urls
     */
    refreshToken: `${appDomain}${paths.authTokenUrl}/refresh`,

    /**
     * User-Token related urls
     * 
     * Diff on the Method used
     * 
     * GET : fetching profile
     * POST : creating new profile
     * PUT : updating existing profile
     */
    profile: `${appDomain}${paths.usersUrl}/profile`,

    /**
     * Property related urls
     */
    propertyBase: `${appDomain}${paths.propertyUrl}`,
    getAllProperty: `${appDomain}${paths.propertyUrl}`,
    postProperty: `${appDomain}${paths.propertyUrl}`,
    // updatePropertyImages: `${appDomain}/${propertyUrl}/${propertyId}/images`,
    imagesEnd: `/images`,
}

export default Endpoints;