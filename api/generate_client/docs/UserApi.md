# UserApi

All URIs are relative to *http://localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**userChangeThemePatch**](UserApi.md#userChangeThemePatch) | **PATCH** /user/change-theme | Change user theme
[**userSettingsGet**](UserApi.md#userSettingsGet) | **GET** /user/settings | Get user settings information

<a name="userChangeThemePatch"></a>
# **userChangeThemePatch**
> userChangeThemePatch(body)

Change user theme

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.UserApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

UserApi apiInstance = new UserApi();
UserChangethemeBody body = new UserChangethemeBody(); // UserChangethemeBody | 
try {
    apiInstance.userChangeThemePatch(body);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#userChangeThemePatch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UserChangethemeBody**](UserChangethemeBody.md)|  |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="userSettingsGet"></a>
# **userSettingsGet**
> UserSettingsDTO userSettingsGet()

Get user settings information

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.UserApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

UserApi apiInstance = new UserApi();
try {
    UserSettingsDTO result = apiInstance.userSettingsGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserApi#userSettingsGet");
    e.printStackTrace();
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

