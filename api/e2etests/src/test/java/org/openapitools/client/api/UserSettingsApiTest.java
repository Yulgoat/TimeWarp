/*
 * CPOO Server API
 * This is a prototype of CPOO Project's front/back API. 
 *
 * The version of the OpenAPI document: 0.0.1
 * Contact: contact@mightycode.fr
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import okhttp3.OkHttpClient;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for UserSettingsApi
 */
@Disabled
public class UserSettingsApiTest {

    private final UserSettingsApi UserSettingsapi = new UserSettingsApi();

    private final AuthenticationApi authenticationApi = new AuthenticationApi();

    private final AdministrationApi administrationApi = new AdministrationApi();


    @BeforeEach
    public void init() throws ApiException {

        // Simulate the behavior of a web browser by remembering cookies set by the server
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.cookieJar(new MyCookieJar()).build();
        ApiClient apiClient = new ApiClient(okHttpClient);
        authenticationApi.setApiClient(apiClient);
        administrationApi.setApiClient(apiClient);
        UserSettingsapi.setApiClient(apiClient);
    }


    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userAccountChangeppPatchTest() throws ApiException {
        String body = null;
        UserSettingsapi.userAccountChangeppPatch(body);
        // TODO: test validations
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userAccountChgusernamePatchTest() throws ApiException {
        String body = null;
        UserSettingsapi.userAccountChgusernamePatch(body);
        // TODO: test validations
    }

    /**
     * Change user theme
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userChangeThemePatchTest() throws ApiException {
        UserChangeThemePatchRequest userChangeThemePatchRequest = null;
        UserSettingsapi.userChangeThemePatch(userChangeThemePatchRequest);
        // TODO: test validations
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userChangepwdPatchTest() throws ApiException {
        //Signout if a user altready Signin
        try{
            authenticationApi.userSignoutPost();
        }
        catch (ApiException e) {
            Assertions.assertEquals(403, e.getCode());
        }

        // Delete the test account if exists
        authenticationApi.userSigninPost(new UserDTO().username("admin").email("admin").password("admin"));
        try {
            administrationApi.userUsernameDelete("testchangepwd");
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
        }
        authenticationApi.userSignoutPost();


        UserDTO user = new UserDTO().username("testchangepwd").email("TestChangePassword").password("Pwd1");
        authenticationApi.userSignupPost(user);

        //SignIn with First Password should work
        authenticationApi.userSigninPost(user);

        //Change Password
        ChangePasswordDTO cpDto = new ChangePasswordDTO().oldPassword(user.getPassword()).newPassword("Pwd2");
        UserSettingsapi.userChangepwdPatch(cpDto);
        authenticationApi.userSignoutPost();

        //SignIn with ancient password should fail
        try {
            authenticationApi.userSigninPost(user);
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_UNAUTHORIZED, e.getCode());
        }

        //SignIn with new password should work
        authenticationApi.userSigninPost(new UserDTO().username("testchangepwd").email("TestChangePassword").password("Pwd2"));

        //ChangePassword with incorrect old password should fail
        try {
            ChangePasswordDTO cpDto2 = new ChangePasswordDTO().oldPassword("Hello").newPassword("Pwd3");
            UserSettingsapi.userChangepwdPatch(cpDto2);
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_UNAUTHORIZED, e.getCode());
        }
        authenticationApi.userSignoutPost();
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userDisconnectPostTest() throws ApiException {
        UserSettingsapi.userDisconnectPost();
        // TODO: test validations
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userLanguagePatchTest() throws ApiException {
        String body = null;
        UserSettingsapi.userLanguagePatch(body);
        // TODO: test validations
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userNotificationsPatchTest() throws ApiException {
        NotificationsDTO notificationsDTO = null;
        UserSettingsapi.userNotificationsPatch(notificationsDTO);
        // TODO: test validations
    }

    /**
     * Get user settings information
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userSettingsGetTest() throws ApiException {
        UserSettingsDTO response = UserSettingsapi.userSettingsGet();
        // TODO: test validations
    }

}