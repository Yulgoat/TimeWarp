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

import org.openapitools.client.ApiException;
import org.openapitools.client.model.ChangePasswordDTO;
import org.openapitools.client.model.ErrorDTO;
import org.openapitools.client.model.NotificationsDTO;
import org.openapitools.client.model.UserChangeThemePatchRequest;
import org.openapitools.client.model.UserSettingsDTO;
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

    private final UserSettingsApi api = new UserSettingsApi();

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userAccountChangeppPatchTest() throws ApiException {
        String body = null;
        api.userAccountChangeppPatch(body);
        // TODO: test validations
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userAccountChgusernamePatchTest() throws ApiException {
        String body = null;
        api.userAccountChgusernamePatch(body);
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
        api.userChangeThemePatch(userChangeThemePatchRequest);
        // TODO: test validations
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userChangepwdPatchTest() throws ApiException {
        ChangePasswordDTO changePasswordDTO = null;
        api.userChangepwdPatch(changePasswordDTO);
        // TODO: test validations
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userDisconnectPostTest() throws ApiException {
        api.userDisconnectPost();
        // TODO: test validations
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userLanguagePatchTest() throws ApiException {
        String body = null;
        api.userLanguagePatch(body);
        // TODO: test validations
    }

    /**
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userNotificationsPatchTest() throws ApiException {
        NotificationsDTO notificationsDTO = null;
        api.userNotificationsPatch(notificationsDTO);
        // TODO: test validations
    }

    /**
     * Get user settings information
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userSettingsGetTest() throws ApiException {
        UserSettingsDTO response = api.userSettingsGet();
        // TODO: test validations
    }

}
