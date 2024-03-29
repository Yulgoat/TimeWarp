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

import org.junit.Assert;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.model.ErrorDTO;
import org.openapitools.client.model.NewPasswordDTO;
import org.openapitools.client.model.UserDTO;

import okhttp3.OkHttpClient;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
  @Disabled
  public void userForgotpwdPostTest() throws ApiException {
    NewPasswordDTO newPasswordDTO = null;
    authenticationApi.userForgotpwdPost(newPasswordDTO);
    // TODO: test validations
  }

  /**
   * @throws ApiException if the Api call fails
   */
  @Test
  public void userSigninPostTest() throws ApiException {

    // Signing in with invalid credentials should fail with UNAUTHORIZED
    UserDTO userDTO = new UserDTO().username("user").password("invalid");
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

    authenticationApi.userSignoutPost();
  }

  /**
   * @throws ApiException if the Api call fails
   */
  @Test
  public void userSignoutPostTest() throws ApiException {
    UserDTO userDTO = new UserDTO().username("testSignout").email("testSignout").password("testSignout");

    // Delete the test account if exists
    authenticationApi.userSigninPost(new UserDTO().username("admin").email("admin").password("admin"));
    try {
      administrationApi.userUsernameDelete("testSignout");
    }
    catch (ApiException e) {
      Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
    }
    authenticationApi.userSignoutPost();

    // Sign in
    authenticationApi.userSignupPost(userDTO);
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
    authenticationApi.userSigninPost(new UserDTO().username("admin").email("admin").password("admin"));
    try {
      administrationApi.userUsernameDelete("test");
    }
    catch (ApiException e) {
      Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
    }
    authenticationApi.userSignoutPost();

    // Signing up a new account should work
    UserDTO testUser = new UserDTO().username("test").email("test").password("test");
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

    authenticationApi.userSignoutPost();
  }

  @Test
  public void userSignupSigninTest() throws ApiException {
    // Delete the test account if exists
    authenticationApi.userSigninPost(new UserDTO().username("admin").email("admin").password("admin"));
    try {
      administrationApi.userUsernameDelete("testSuSiUsername");
    }
    catch (ApiException e) {
      Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
    }
    authenticationApi.userSignoutPost();

    // Signing up a new account should work
    UserDTO testUser = new UserDTO().username("testSuSiUsername").email("testSuSiEmail").password("testSuSiPwd");
    authenticationApi.userSignupPost(testUser);

    // Signing up twice the same account should fail with CONFLICT
    try {
      authenticationApi.userSignupPost(testUser);
      Assertions.fail();
    }
    catch (ApiException e) {
      Assertions.assertEquals(HttpStatus.SC_CONFLICT, e.getCode());
    }

    // Signing in with valid credential should work
    authenticationApi.userSigninPost(testUser);

    // Signing in again should fail with CONFLICT
    try {
      authenticationApi.userSigninPost(testUser);
      Assertions.fail();
    }
    catch (ApiException e) {
      Assertions.assertEquals(HttpStatus.SC_CONFLICT, e.getCode());
    }

    UserDTO testUserEmail = new UserDTO().username("test2").email("testSuSiEmail").password("test");
    try {
      authenticationApi.userSignupPost(testUserEmail);
      Assertions.fail();
    }
    catch (ApiException e) {
      Assertions.assertEquals(HttpStatus.SC_CONFLICT, e.getCode());
    }

    authenticationApi.userSignoutPost();
  }

  /**
   * @throws ApiException if the Api call fails
   */
  @Test
  public void userDelete() throws ApiException {
    //Signout if a user altready Signin
    try {
      authenticationApi.userSignoutPost();
    }
    catch (ApiException e) {
      Assertions.assertEquals(403, e.getCode());
    }

    // Delete the tests account if they  exists
    authenticationApi.userSigninPost(new UserDTO().username("admin").email("admin").password("admin"));
    try {
      administrationApi.userUsernameDelete("testUserDelete1");
    }
    catch (ApiException e) {
      Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
    }
    try {
      administrationApi.userUsernameDelete("testUserDelete2");
    }
    catch (ApiException e) {
      Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
    }
    try {
      administrationApi.userUsernameDelete("testUserDelete3");
    }
    catch (ApiException e) {
      Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
    }
    authenticationApi.userSignoutPost();


    UserDTO testUserDelete1 = new UserDTO().username("testUserDelete1").email("testUserDelete").password("testUserDelete");
    UserDTO testUserDelete2 = new UserDTO().username("testUserDelete2").email("testUserDelete").password("testUserDelete");
    UserDTO testUserDelete3 = new UserDTO().username("testUserDelete3").email("testUserDelete").password("testUserDelete");
    authenticationApi.userSignupPost(testUserDelete1);

    //Should fail because testUserDelete2 have an email which already exist
    try {
      authenticationApi.userSignupPost(testUserDelete2);
      Assertions.fail();
    }
    catch (ApiException e) {
      Assertions.assertEquals(HttpStatus.SC_CONFLICT, e.getCode());
    }

    //Delete testUserDelete1
    authenticationApi.userSigninPost(new UserDTO().username("admin").email("admin").password("admin"));
    try {
      administrationApi.userUsernameDelete("testUserDelete1");
    }
    catch (ApiException e) {
      Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
    }
    authenticationApi.userSignoutPost();

    //Shoudl Work because testUserDelete1 doesn't exist anymore
    authenticationApi.userSignupPost(testUserDelete3);
  }

  @Test
  public void currentUser() throws ApiException {
    //Signout if a user altready Signin
    try {
      authenticationApi.userSignoutPost();
    }
    catch (ApiException e) {
      Assertions.assertEquals(403, e.getCode());
    }

    // Delete the test account if exists
    authenticationApi.userSigninPost(new UserDTO().username("admin").email("admin").password("admin"));
    try {
      administrationApi.userUsernameDelete("testCurrentUser");
    }
    catch (ApiException e) {
      Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, e.getCode());
    }
    authenticationApi.userSignoutPost();

    //Create User
    UserDTO testCurrentUserDTO = new UserDTO().username("testCurrentUser").email("testCurrentUser").password("testCurrentUser");
    UserDTO testRetourCurrentUser = new UserDTO().username("testCurrentUser@timewarp").email("").password("");

    authenticationApi.userSignupPost(testCurrentUserDTO);
    authenticationApi.userSigninPost(testCurrentUserDTO);

    //Should Return the current User, so testCurrentUserDTO Username and no email and no password
    UserDTO retour = authenticationApi.userCurrentuserPost();
    Assertions.assertEquals(retour, testRetourCurrentUser);
  }
}
