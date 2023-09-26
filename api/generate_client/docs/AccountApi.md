# AccountApi

All URIs are relative to *http://localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**userAccountChangeppPatch**](AccountApi.md#userAccountChangeppPatch) | **PATCH** /user/account/changepp | 
[**userAccountChgusernamePatch**](AccountApi.md#userAccountChgusernamePatch) | **PATCH** /user/account/chgusername | 
[**userDisconnectPost**](AccountApi.md#userDisconnectPost) | **POST** /user/disconnect | 

<a name="userAccountChangeppPatch"></a>
# **userAccountChangeppPatch**
> userAccountChangeppPatch(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AccountApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

AccountApi apiInstance = new AccountApi();
AccountChangeppBody body = new AccountChangeppBody(); // AccountChangeppBody | 
try {
    apiInstance.userAccountChangeppPatch(body);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#userAccountChangeppPatch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**AccountChangeppBody**](AccountChangeppBody.md)|  |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="userAccountChgusernamePatch"></a>
# **userAccountChgusernamePatch**
> userAccountChgusernamePatch(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AccountApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

AccountApi apiInstance = new AccountApi();
AccountChgusernameBody body = new AccountChgusernameBody(); // AccountChgusernameBody | 
try {
    apiInstance.userAccountChgusernamePatch(body);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#userAccountChgusernamePatch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**AccountChgusernameBody**](AccountChgusernameBody.md)|  |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="userDisconnectPost"></a>
# **userDisconnectPost**
> userDisconnectPost()



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AccountApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

AccountApi apiInstance = new AccountApi();
try {
    apiInstance.userDisconnectPost();
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#userDisconnectPost");
    e.printStackTrace();
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

