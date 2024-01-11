# AdministrationApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**userUsernameDelete**](AdministrationApi.md#userUsernameDelete) | **DELETE** /user/{username} | Delete a user account |


<a id="userUsernameDelete"></a>
# **userUsernameDelete**
> userUsernameDelete(username)

Delete a user account

Delete the account of the user matching the given username. Only the administrator can use this endpoint.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AdministrationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    AdministrationApi apiInstance = new AdministrationApi(defaultClient);
    String username = "username_example"; // String | username of the user account to delete.
    try {
      apiInstance.userUsernameDelete(username);
    } catch (ApiException e) {
      System.err.println("Exception when calling AdministrationApi#userUsernameDelete");
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
| **username** | **String**| username of the user account to delete. | |

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
| **404** | Not found: User account not found. |  -  |
| **0** | Error |  -  |

