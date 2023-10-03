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
import org.junit.jupiter.api.Test;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.UserDTO;

/**
 * API tests for AuthenticationApi
 */
public class AuthenticationApiTest {

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
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userSigninPostTest() throws ApiException {

        // Signing in with invalid credentials should fail with UNAUTHORIZED
        UserDTO userDTO = new UserDTO().login("user").password("invalid");
        try {
            authenticationApi.userSigninPost(userDTO);
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_UNAUTHORIZED, e.getCode());
        }

        // Signing in with valid credential should work
        authenticationApi.userSigninPost(userDTO.password("user"));

        // Signing in again should fail with CONFLICT
        try {
            authenticationApi.userSigninPost(userDTO);
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_CONFLICT, e.getCode());
        }
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userSignoutPostTest() throws ApiException {

        // Sign in
        UserDTO userDTO = new UserDTO().login("user").password("user");
        authenticationApi.userSigninPost(userDTO);

        // Signing out while signed in should work
        authenticationApi.userSignoutPost();

        // Signing out again should fail with FORBIDDEN
        try {
            authenticationApi.userSignoutPost();
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_FORBIDDEN, e.getCode());
        }
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userSignupPostTest() throws ApiException {

        // Delete the test account if exists
        authenticationApi.userSigninPost(new UserDTO().login("admin").password("admin"));
        try {
            administrationApi.userLoginDelete("test");
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
        }
        authenticationApi.userSignoutPost();

        // Signing up a new account should work
        UserDTO testUser = new UserDTO().login("test").password("test");
        authenticationApi.userSignupPost(testUser);

        // Signing up twice the same account should fail with CONFLICT
        try {
            authenticationApi.userSignupPost(testUser);
            Assertions.fail();
        }
        catch (ApiException e) {
            Assertions.assertEquals(HttpStatus.SC_CONFLICT, e.getCode());
        }

        // Signing in with the new account should work
        authenticationApi.userSigninPost(testUser);
    }
}