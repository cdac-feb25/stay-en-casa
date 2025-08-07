[33mcommit 2aad305f1e67ceb502b0635edc1c543c15c1c58e[m[33m ([m[1;36mHEAD -> [m[1;32mmain[m[33m, [m[1;31morigin/main[m[33m)[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Thu Aug 7 04:45:52 2025 +0530

    Frontend : Carousel add on property detail page

[33mcommit 9822b84c1eae63abddf63fb12cf081ecbe7220eb[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Thu Aug 7 02:39:13 2025 +0530

    Frontend : All Properties, PropertyDetail page working, getting all properties and storing in local storage (next fetch after 15 min)

[33mcommit 6d29d8b434cb8c64f674819e8e695c0de954069a[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Thu Aug 7 00:07:20 2025 +0530

    Frontend : add-property page working tested

[33mcommit 514618705c4db88ec84d97a00c11c0302d5a4f23[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Thu Aug 7 00:05:59 2025 +0530

    Gateway/Property Service : '/{propertyId}/images' endpoint add for seperately adding image urls to property data

[33mcommit 1c0dc7a3bb60ff1c35248a0e6c53088ce8237fe7[m
Merge: 64d7fff c9c6d5b
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Wed Aug 6 17:38:52 2025 +0530

    Merge conflict resolved : new endpoint add (Gateway) ('/properties/owner' - *JWT (fetch property by owner))

[33mcommit 64d7ffffb5b4d4b4bb4a788b520b0da2f3d59aeb[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Wed Aug 6 16:50:06 2025 +0530

    Misc docs : .gitignore update (uploading env files - backend/frontend), Flaws in project update, Error docs update, frontend asset update

[33mcommit 3c298a15b1db46e21d16314974b35d259ace6a82[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Wed Aug 6 16:47:57 2025 +0530

    Auth Service : minor change - /auth/{uid}/exists endpoint add , ApiAccessGateway Filter creation changed to explicit

[33mcommit 0e1ecce3beb6c06bc723fcd8353beb8022c5e72b[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Wed Aug 6 16:41:08 2025 +0530

    User Service : Some endpoint add for Property Service to check property id - done and tested

[33mcommit 879177c6e9919fb8ddff9d87c3cdefd6c0470844[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Wed Aug 6 16:39:50 2025 +0530

    Gateway Service : Integration with Property Service done and tested, new endpoint (check desc)
    
    1. GET / - public (fetching all properties)
    
    2. POST /search - public (search and filter properties)
    
    3. GET /{propertyId}/availability - public (checking availability of a property)
    
    4. POST / - *JWT (posting new property)
    
    5. GET /{propertyId} - *JWT (getting detail of particular property - only for owner)
    
    6. PUT /{propertyId} - *JWT (updating property post - only for owner)
    
    7. DELETE /{propertyId} - *JWT (deleting existing property post - only for owner)
    
    8. PATCH /{propertyId}/mark-as-sold - *JWT (mark property as sold - only for owner)
    
    9. PATCH /{propertyId}/mark-as-available - *JWT (mark property as AVAILABLE - only for owner)

[33mcommit 1499f0b73b9e1a5275514e0ee1cab65c47cf4a54[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Wed Aug 6 16:38:37 2025 +0530

    Property Service : Security ON, ApiGatewayAccessFilter ON, Integration with Gateway Service done and tested, some endpoints remove and some new add

[33mcommit c9c6d5b44c70bc16ccdd9114001af3cc6d204913[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Wed Aug 6 01:08:58 2025 +0530

    Make Changes in EditPropertyPage and propertyService final changes pending after testing

[33mcommit f88002c2a4165ef2bf9f2d48865d9edafbeff1fd[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Tue Aug 5 18:54:11 2025 +0530

    User Service : update-profile logic update, UserProfileController advice logic, ProfileException add

[33mcommit 0e0966ad04f6e4a581bd6985782143e821c4c222[m
Merge: 7faf05c 865a8de
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Tue Aug 5 18:04:08 2025 +0530

    Merge conflict

[33mcommit 7faf05c53c96b3d9dbca7239bfa359960edcefcd[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Tue Aug 5 17:59:30 2025 +0530

    Properties Pages Created and made changes in other pages accordingly
    
    AddPropertiesPage, EditPropertyPage, MypropertiesPage. Created API calls methods for adding, updating (All or Partial), Toggle availability, deleting property

[33mcommit 865a8dede58444c9cc48e3d2a71523e45b8520fb[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Tue Aug 5 00:28:14 2025 +0530

    Frontend : OTP based signup UI and logic implemented

[33mcommit c6412cafc389b3548a892122998eec4bbd71341c[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Tue Aug 5 00:25:15 2025 +0530

    Gateway Service : (Bug fixes) : OTP based signup and Refresh token was getting shared on signup (now resolved);

[33mcommit 30376eb8765518301c28a54c1f477f7d3ff39f63[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Tue Aug 5 00:21:47 2025 +0530

    Auth Service : OTP based signup implemented (/signup-otp endpoints for generating otp (shared via email with new template) and then proceeding with the sign up process)

[33mcommit 05b0ce5a19b3bd47871555e9cd0a4218110bfdfa[m
Merge: 1bac902 c8826b0
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Mon Aug 4 18:03:40 2025 +0530

    Merge branch 'main' of https://github.com/cdac-feb25/stay-en-casa

[33mcommit 1bac902e3bc2a4efd186f9f1a5c361039c57c205[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Mon Aug 4 18:03:38 2025 +0530

    Merge conflict in SupabaseHelper

[33mcommit c8826b0bf5c8e4e2791a67cad68872f3e38385d9[m
Merge: 1f24a6a bec0468
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Sun Aug 3 23:13:51 2025 +0530

    Merge conflict resolved in SupabaseHelper

[33mcommit 1f24a6a1e1de9911d4f388483fb449ad18379c97[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Sun Aug 3 23:09:56 2025 +0530

    Changes in booking service

[33mcommit bec0468483c15584beea5a5c0242c0c291706ed5[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sun Aug 3 22:22:55 2025 +0530

    Frontend : OTP based /forgot-password, /change-password feature add, UI and Logic done

[33mcommit 471cba77de8c0300f654cd4505cf53726c1dcb54[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sun Aug 3 22:20:18 2025 +0530

    Gateway Service : AuthService corresponding changes (/change-password changed to PUT method)

[33mcommit dddcd31aa1ce79e7991722c462a72b879b9b7747[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sun Aug 3 22:18:53 2025 +0530

    Auth Service : (minor update) Controller, EmailService, UserToken Entity
    
    Auth Controller : /change-password HttpMethod changed to PUT
    
    EmailService : email template update
    
    UserToken : timestamp updating on token update

[33mcommit 5e6e29104026e294a02bccd908c1da22ef3b1679[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sun Aug 3 15:04:07 2025 +0530

    Gateway Service : OTP based password changing feature add, removing Security Filter from '/forgot-password', '/change-password' endpoints

[33mcommit 2b4460336489dd0c26e16d1143a00286019fbd9b[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sun Aug 3 15:00:21 2025 +0530

    Auth Service : OTP based password change feature (OtpService / EmailService class) add using 'Resend' email service and custom domain 'StayEn.Casa' (check desc)
    
    1. /forgot-password endpoint add for sending OTP to user's email id and a copy of OTP is saved on database
    
    On successful email existence, sending an email to user's email id
    
    2. /change-password endpoint add for verifying, changing password to new password and sending success email to user's id
    
    After successful verification of OTP, the copy of OTP is deleted from database and an email of success is send to user's email id

[33mcommit 7510c8fd32d5c743c7cbb21b75c67b02022a6de7[m
Merge: 7a49d4c 7b36beb
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Sun Aug 3 11:48:21 2025 +0530

    Navbar changes

[33mcommit 7a49d4c36de63b2f6d41862fb44541d87ce0eb29[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Sun Aug 3 11:37:45 2025 +0530

    Navbar changes

[33mcommit 7b36bebcf45523ede17752df735dce16e7ed597b[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sun Aug 3 01:49:25 2025 +0530

    Frontend : Signup done and tested, minor changes in login, edit-profile, navigation

[33mcommit c1a1ab11c3d2a5d8ac36fc23cdb5cbe2c8ecaea5[m
Merge: f531f42 a9e27e3
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sun Aug 3 00:25:39 2025 +0530

    Merge Conflict resolved : App.jsx, NavBar.jsx

[33mcommit f531f427830989d596b54a5263d0ea4e41d747fd[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sun Aug 3 00:15:01 2025 +0530

    Frontend : Helper Class, [Login, EditProfile, Logout] (with UI and logic), Redirection Logic (if logged in, if profile missing, etc) (check desc)
    
    ApiCaller, AxiosHelper, SupabaseHelper, LocalStorageHelper, PayloadHelper, ApiActionHelper (for response data/error), AssetHelper, AppRoutes, UserContext, ApiEndpoints

[33mcommit a9e27e38a542d8eeda95b7745e13c0f92cd6749b[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Fri Aug 1 02:24:23 2025 +0530

    AllProperties Page created and propertyService file ccreated for API calls

[33mcommit 1db9037c4ee09b29b5b4f053d369d3c47cbef07c[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Wed Jul 30 17:14:42 2025 +0530

    Gateway-Service (major) : change in flow of token response given, jwt-filter token extraction (header / cookies), token in cookie (check desc), more Env variable add
    
    Previously : we were sending access-token, refresh-token in response body due to which we have to save the tokens explicitly inside browser localStorage which is considered as unsafe approach
    
    Currently : only access-token (15 min validity) is being sent in response body and refresh-token (24 hours validity) is being sent in responseCookie
    
    Now : we only need to save access-token inside browser localStorage / refresh-token is automatically save by browser in HttpOnly Cookie (DevTool - Application - Cookies)
    
    1. ResponseCookie config : HttpOnly, Domain as AppDomain (to allow all subdomain), Path as '/api/v1/auth/token/refresh' so that browser only send refresh-token while calling given refresh path
    
    2. ResponseCookie config : maxAge 31 days (though refresh-token expires in 24 hours avoiding browser auto clearing)
    
    3. ResponseCookie config : secure(false) for now to allow http for localhost, later to be changed to secure(true) https SSL
    
    4. ResponseCookie config : sameSite('Lax') allowing Cross-Site access, later to be changed to 'None' with secure(true), so that cookie token stored by api.stayen.casa is allowed to send by stayen.casa (cross-origin access)

[33mcommit c34ca8b4e2c898eb4aea1a8053b4ae76c6300035[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Wed Jul 30 16:53:03 2025 +0530

    docs update : flaws in project, error codes, StayEnCasa icons upload, user-schema update
    
    flaws : the flaws in project are listed and how to solve it also mentioned so that we can solve it in future with upcoming update, till then first priority is to make project in running condition

[33mcommit aa969e76dd4837f1dcf150d25b7decdd334af920[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Tue Jul 29 19:33:38 2025 +0530

    Gateway-Service (major) : RestTemplate - Downstream Service API call integrated (TESTED), RequestValidator add, All endpoints centralized in Endpoints class, EnvConstant created, ControllerAdvice add, Minor update in Security
    
    /login, /signup, /logout, /token/refresh, users/profile (GET, POST, PUT) - API integrated in respective controller and TESTED
    
    validator package - RequestValidator create to filter payload, header received in request (currently - header uid, payload uid comparison in create profile endpoint / trimming uid, email from update profile payload)
    
    Auth-Key added for accessing downstream services
    
    EnvConstant created for centralized access of environment variable

[33mcommit bddde5af8a3ac0f908e0a6c2cb8005b3ae63885c[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Tue Jul 29 19:15:25 2025 +0530

    Auth-Service (major) : API integrated with GatewayService, APIGatewayAccessFilter logic update, All endpoints centralized in Endpoints class, StatusCode update , Minor changes in API, Unnecessary DTO, JwtFilter deleted, SecurityConig shifted to config package, EnvConstant created
    
    /login, /signup, /logout, /token/refresh - API integrated with API-Gateway-Service and TESTED
    
    For restricting unauthorized access to User-Service, updated APIGatewayAccessFilter logic to check for Auth-Key (instead of Origin) in header and compare it with key present in application.properties
    
    All 400 (Bad-Request), 401 (Unauthorized) exception changed to 403 (Forbidden) due to conflict in RestTemplate trimming Response Body
    
    EnvConstant created for centralized environment variable access

[33mcommit cf068be9042e5b5d3912bbc61a58e0518f5f0b13[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Tue Jul 29 19:03:00 2025 +0530

    User-Service (major) : API integration with GatewayService, APIGatewayAccessFilter logic update, All endpoints centralized in Endpoints class
    
    Get, Create, Update profile API integrated with API-Gateway-Service and TESTED
    
    For restricting unauthorized access to User-Service, updated APIGatewayAccessFilter logic to check for Auth-Key (instead of Origin) in header and compare it with key present in application.properties

[33mcommit c653e1faf4a774e411f91223b5ac07b3ac1b26cd[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Mon Jul 28 02:45:18 2025 +0530

    Booking Service pages created
    
    BookingPage.jsx, MyBookingsPage.jsx, BookingDetailsPage.jsx and Update
    BookingPage.jsx created and functionality integration working

[33mcommit 43c607e33afa4819e1712136f45f577683602a45[m
Merge: e190d9e f199ca6
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Sun Jul 27 19:56:57 2025 +0530

    Modified Commit

[33mcommit e190d9e71ba54a1946b2887287a304e9a3e1b7e7[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Sun Jul 27 19:31:22 2025 +0530

    created Booking Page and connected it to the backend

[33mcommit f199ca64d1e609ead70f4241e81b4b52fbeb922c[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sat Jul 26 23:51:20 2025 +0530

    Frontend : Login/Signup page created (functionality pending) || Functionality integration started (check desc)
    
    AxiosHelper for static method for login, signup, logout, etc
    
    ApiGatewayEndpoints storing all the endpoints for quicker access
    
    LocalStorageHelper for storing and retrieving data (device_id, jwt_token) from browser persistent storage
    
    PayloadHelper for creating payload specific to each endpoints

[33mcommit 23bf1e6c92840476a58da684b5ec9d7977b0beb3[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Fri Jul 25 21:06:15 2025 +0530

    User-Service : ApiGatewayAccessFilter and UserContextFilter created (check desc) / fetch, create, update profile APIs done and tested
    
    ApiGatewayAccessFilter created so that no one can access this service's API other than allowed origins (currently API Gateway domain) but commented for testing, uncomment before deploying
    
    UserContextFilter created, to check required header tag (user-id, user-email, user-device-id) in each request, otherwise request will be rejected

[33mcommit b0251a44759f4b51739bd5b8824430d0b53c87a6[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Wed Jul 23 01:43:59 2025 +0530

    Modified Frontend folder, currently added booking pages in bookings folder in pages folder and also added bookingService file in services folder

[33mcommit 314a256fca32f20c18346ab5c0b8f05268e2d2b7[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Thu Jun 26 10:00:41 2025 +0530

    Fixes Bug while testing. Final testing is partially done and remaining in process. Search filters are working properly. Indexing in process for search API filters.

[33mcommit c3403fe7f5ee95178cf345123705de96f0b09f74[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Tue Jun 24 16:43:22 2025 +0530

    Necessary API implemented in Payment Service. Final Testing in Process

[33mcommit 190a062c4564828b64e693970fe35a27bae5960c[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Mon Jun 23 23:50:11 2025 +0530

    api-gateway-service added with JWT validation, parsing user info and dummy endpoints create (final testing PENDING)

[33mcommit f26937ed8b72d8067a036238ccc4cb381e3faa5f[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Mon Jun 23 17:44:29 2025 +0530

    Method for Unique ID generation for properties is completed

[33mcommit 1bd3adcea1c190543395445a1d82aff664fd31bb[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Sat Jun 21 00:37:10 2025 +0530

    All property DTO classes completed, service layer and controller API for insertingproperty details completed and meaningful unique id generation is in process

[33mcommit c97a5d6c9211d20b8a9911c3737b0cc4461f3d25[m
Merge: 46f82ce bf2935e
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Thu Jun 19 18:32:59 2025 +0530

    Merge branch 'main' of https://github.com/cdac-feb25/property-renting-selling

[33mcommit 46f82ceb24ce35b7a6e7e2895f7ffe828e435b4b[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Thu Jun 19 18:31:34 2025 +0530

    JWT-Auth-Filter & '/auth/token/refresh' update (check desc)
    
    Jwt Auth Filter updated, instead of manually skipping the allowed URL, now overriding shouldNotFilter() to skip whitelisted URL from JWT filteration
    
    If TEMP token is used, then rejecting the request immediately
    
    inside '/auth/token/refresh', if last-refresh-token (valid & unexpired) is used for refreshing, then logging out their session based on deviceID

[33mcommit bf2935e50bed5564801be8def7d006cc1730c565[m
Author: Utkarsh-1709 <utkarshmaurya1998@gmail.com>
Date:   Wed Jun 18 00:19:24 2025 +0530

    Created ER Diagram for Auth, Booking, Property and User Service. Created Property Service and DTO is in process

[33mcommit d791c593ed88e83167f48760b3ad1d6b3741a364[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Mon Jun 16 10:30:04 2025 +0530

    Auth Error code added (single AuthException class for handling multiple AuthError with enum), Constant removed

[33mcommit 435d9145e3100d2793784bf5cffb715e6137c6e2[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Mon Jun 16 00:08:42 2025 +0530

    /auth/token/refresh update, rotate and create new refresh token logic half done [pending : intercepting if lastRefreshToken and invalidate deviceToken]

[33mcommit 7837ef15f7d5bd3bf40353167ef83c3cf781cc19[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sun Jun 15 21:39:40 2025 +0530

    Major update :: /login, /register, /logout endpoint update | all endpoint secured via access_token | no refresh_token allowed | Token-Error-Code added | JWT-Utils update, check Desc
    
    above mentioned endpoints logic updated
    
    User data in MongoDB (createdAt, updatedAt timestamp) corrected
    
    Using refresh_token is disallowed and 'Invalid Token' error is returned
    
    Specific Error Code for specific token error is created
    
    TokenType information added in JWT

[33mcommit 861b161db0e59d3559d709c6b796e9d5f5a478fb[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sun Jun 15 19:09:09 2025 +0530

    Pom.xml, application.properties update / Schema update / AppConfig.java for Bean creation / Major DTO update
    
    Jackson helper for JAVA 8 DateTime API added
    
    All Schema update, _id : Uid, email added
    
    AppConfig.java component created for Bean creation all in one place
    
    Major update in DTO

[33mcommit 7d6fab51287bfe5d19e8e5058e99c0d43d28fa37[m
Author: The-3rio <craftmenlab@gmail.com>
Date:   Thu Jun 12 16:06:02 2025 +0530

    Booking Service api testing done without jwt

[33mcommit 243556aefe0431057c9d8005ef0cbb70bd15269b[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Wed Jun 11 01:08:26 2025 +0530

    Schema update / FlowChart add

[33mcommit d8adef3ccfeb81e13dac51f3c997fda3846babc1[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Wed Jun 11 01:07:35 2025 +0530

    JWT implement | Login/Register API complete, Logout half done (without expired jwt handling), check desc
    
    JWT Filter is intercepting but not handling exception correctly
    
    expired jwt not handled
    
    refresh token not handled
    
    All pending changes to be done in upcoming commit

[33mcommit 6d80507ee79003577af8727a9ef4caeca354c426[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sun Jun 8 23:46:38 2025 +0530

    Admin, UserToken Schema added / Auth-User Service FlowChart added

[33mcommit 1f1ad947b382e1ca397fecb82ce8346b9e13d773[m
Author: The-3rio <craftmenlab@gmail.com>
Date:   Sat Jun 7 18:10:23 2025 +0530

    booking-service / property-service created

[33mcommit 909fee7e9fb76d01c7fada44fcdbd77414bd2d77[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sat Jun 7 17:54:28 2025 +0530

    minor updates to application.properties

[33mcommit e8528f1029c15de839c1650e32ae4fbe79da9a77[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sat Jun 7 17:38:06 2025 +0530

    .gitignore file added in all service | Auth-Service / User-Service application.properties updated

[33mcommit 8900a16c6488d6313ad82603b7c3e9e0b113271d[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sat Jun 7 10:51:43 2025 +0530

    create Authentication-Service / User-Service initial project files

[33mcommit 7e7251244ae4d18116ed493a99a81f76b70d06cb[m
Author: The-3rio <craftmenlab@gmail.com>
Date:   Sun Jun 1 20:57:09 2025 +0530

    bookingschema added

[33mcommit 08b11e94116344dff77e73626478a1fb15c31a05[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Sat May 31 09:22:19 2025 +0530

    userCredential schema add / user, property schema update

[33mcommit 421eb06125e5f20009470a762e61019c287d6897[m
Author: The-3rio <craftmenlab@gmail.com>
Date:   Thu May 29 16:34:35 2025 +0530

    property-schema.json created

[33mcommit dc47ff9bf29c51089bb27196cf0c67783b65e20e[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Thu May 29 16:10:57 2025 +0530

    User-Schema.json created / Project Docs folder created

[33mcommit fd585554086bba443d5196468a256b58def8b8cb[m
Author: Utkarsh Maurya <69841168+Utkarsh-1709@users.noreply.github.com>
Date:   Thu May 29 00:01:26 2025 +0530

    Project Timeline
    
    Week Wise Project Timeline

[33mcommit 85822c79befcec39d813b56fdb984b47211f125a[m
Author: Utkarsh Maurya <69841168+Utkarsh-1709@users.noreply.github.com>
Date:   Wed May 28 23:56:42 2025 +0530

    Create Folder Structure_28May_Day1
    
    Folder Structure - Property Renting/Selling

[33mcommit 3b52e1bb9713d0b9f2f6ad3957ba109024001e3c[m
Author: Utkarsh Maurya <69841168+Utkarsh-1709@users.noreply.github.com>
Date:   Wed May 28 23:33:21 2025 +0530

    Update README.md

[33mcommit 970a78e34463839cf3e58688194f8a4bdab1f10e[m
Author: Utkarsh Maurya <69841168+Utkarsh-1709@users.noreply.github.com>
Date:   Wed May 28 23:32:22 2025 +0530

    Update README.md

[33mcommit 372b5b5421b3637e19e5769a0431f9d3842631da[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Mon May 26 12:52:18 2025 +0530

    Readme update, frontend .gitignore update

[33mcommit af8035d3c32ea64386a5ba4d59973c05f29e251f[m[33m ([m[1;31morigin/master[m[33m, [m[1;31morigin/HEAD[m[33m)[m
Author: aadarsh-mca <aadarsh.mca.lnctu.2022@gmail.com>
Date:   Mon May 26 10:34:45 2025 +0530

    Initial 'Frontend' commit;

[33mcommit e55110259e22aa0b7e947ded493857fb3cd19c49[m
Author: Aadarsh Yadav <114849585+aadarsh-mca@users.noreply.github.com>
Date:   Wed May 14 09:58:41 2025 +0530

    Initial commit
