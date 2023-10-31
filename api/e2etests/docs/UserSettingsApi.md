# UserSettingsApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**userAccountChangeppPatch**](UserSettingsApi.md#userAccountChangeppPatch) | **PATCH** /user/account/changepp |  |
| [**userAccountChgusernamePatch**](UserSettingsApi.md#userAccountChgusernamePatch) | **PATCH** /user/account/chgusername |  |
| [**userChangeThemePatch**](UserSettingsApi.md#userChangeThemePatch) | **PATCH** /user/change-theme | Change user theme |
| [**userChangepwdPatch**](UserSettingsApi.md#userChangepwdPatch) | **PATCH** /user/changepwd |  |
| [**userDisconnectPost**](UserSettingsApi.md#userDisconnectPost) | **POST** /user/disconnect |  |
| [**userLanguagePatch**](UserSettingsApi.md#userLanguagePatch) | **PATCH** /user/language |  |
| [**userNotificationsPatch**](UserSettingsApi.md#userNotificationsPatch) | **PATCH** /user/notifications |  |
| [**userSettingsGet**](UserSettingsApi.md#userSettingsGet) | **GET** /user/settings | Get user settings information |


<a id="userAccountChangeppPatch"></a>
# **userAccountChangeppPatch**
> userAccountChangeppPatch(userAccountChangeppPatchRequest)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserSettingsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    UserSettingsApi apiInstance = new UserSettingsApi(defaultClient);
    UserAccountChangeppPatchRequest userAccountChangeppPatchRequest = new UserAccountChangeppPatchRequest(); // UserAccountChangeppPatchRequest | 
    try {
      apiInstance.userAccountChangeppPatch(userAccountChangeppPatchRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserSettingsApi#userAccountChangeppPatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userAccountChangeppPatchRequest** | [**UserAccountChangeppPatchRequest**](UserAccountChangeppPatchRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |
| **0** | Error |  -  |

<a id="userAccountChgusernamePatch"></a>
# **userAccountChgusernamePatch**
> userAccountChgusernamePatch(userAccountChgusernamePatchRequest)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserSettingsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    UserSettingsApi apiInstance = new UserSettingsApi(defaultClient);
    UserAccountChgusernamePatchRequest userAccountChgusernamePatchRequest = new UserAccountChgusernamePatchRequest(); // UserAccountChgusernamePatchRequest | 
    try {
      apiInstance.userAccountChgusernamePatch(userAccountChgusernamePatchRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserSettingsApi#userAccountChgusernamePatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userAccountChgusernamePatchRequest** | [**UserAccountChgusernamePatchRequest**](UserAccountChgusernamePatchRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |
| **0** | Error |  -  |

<a id="userChangeThemePatch"></a>
# **userChangeThemePatch**
> userChangeThemePatch(userChangeThemePatchRequest)

Change user theme

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserSettingsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    UserSettingsApi apiInstance = new UserSettingsApi(defaultClient);
    UserChangeThemePatchRequest userChangeThemePatchRequest = new UserChangeThemePatchRequest(); // UserChangeThemePatchRequest | 
    try {
      apiInstance.userChangeThemePatch(userChangeThemePatchRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserSettingsApi#userChangeThemePatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userChangeThemePatchRequest** | [**UserChangeThemePatchRequest**](UserChangeThemePatchRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Theme changed successfully |  -  |
| **0** | Error |  -  |

<a id="userChangepwdPatch"></a>
# **userChangepwdPatch**
> userChangepwdPatch(changePasswordDTO)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserSettingsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    UserSettingsApi apiInstance = new UserSettingsApi(defaultClient);
    ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO(); // ChangePasswordDTO | 
    try {
      apiInstance.userChangepwdPatch(changePasswordDTO);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserSettingsApi#userChangepwdPatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **changePasswordDTO** | [**ChangePasswordDTO**](ChangePasswordDTO.md)|  | |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |
| **0** | Error |  -  |

<a id="userDisconnectPost"></a>
# **userDisconnectPost**
> userDisconnectPost()



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserSettingsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    UserSettingsApi apiInstance = new UserSettingsApi(defaultClient);
    try {
      apiInstance.userDisconnectPost();
    } catch (ApiException e) {
      System.err.println("Exception when calling UserSettingsApi#userDisconnectPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |
| **0** | Error |  -  |

<a id="userLanguagePatch"></a>
# **userLanguagePatch**
> userLanguagePatch(userLanguagePatchRequest)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserSettingsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    UserSettingsApi apiInstance = new UserSettingsApi(defaultClient);
    UserLanguagePatchRequest userLanguagePatchRequest = new UserLanguagePatchRequest(); // UserLanguagePatchRequest | 
    try {
      apiInstance.userLanguagePatch(userLanguagePatchRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserSettingsApi#userLanguagePatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userLanguagePatchRequest** | [**UserLanguagePatchRequest**](UserLanguagePatchRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |
| **0** | Error |  -  |

<a id="userNotificationsPatch"></a>
# **userNotificationsPatch**
> userNotificationsPatch(notificationsDTO)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserSettingsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    UserSettingsApi apiInstance = new UserSettingsApi(defaultClient);
    NotificationsDTO notificationsDTO = new NotificationsDTO(); // NotificationsDTO | 
    try {
      apiInstance.userNotificationsPatch(notificationsDTO);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserSettingsApi#userNotificationsPatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **notificationsDTO** | [**NotificationsDTO**](NotificationsDTO.md)|  | |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |
| **0** | Error |  -  |

<a id="userSettingsGet"></a>
# **userSettingsGet**
> UserSettingsDTO userSettingsGet()

Get user settings information

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserSettingsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    UserSettingsApi apiInstance = new UserSettingsApi(defaultClient);
    try {
      UserSettingsDTO result = apiInstance.userSettingsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserSettingsApi#userSettingsGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**UserSettingsDTO**](UserSettingsDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response |  -  |
| **0** | Error |  -  |

