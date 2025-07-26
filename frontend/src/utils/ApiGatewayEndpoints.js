const authBaseUrl = "http://localhost:9090/api/v1/auth";
const usersBaseUrl = "http://localhost:9090/api/v1/users";

const endpoints = {
    /**
     * Authentication related endpoints
     */
    login: `${authBaseUrl}/login`,
    signup: `${authBaseUrl}/signup`,
    logout: `${authBaseUrl}/logout`,
    refreshToken: `${authBaseUrl}/token/refresh`,
    /**
     * User Profile related endpoints
     */
    getProfile: `${usersBaseUrl}/profile`,
    createProfile: `${usersBaseUrl}/profile`,
    updateProfile: `${usersBaseUrl}/profile`,
};

export { endpoints as ApiGatewayEndpoint };