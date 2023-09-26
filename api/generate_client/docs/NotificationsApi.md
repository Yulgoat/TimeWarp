# NotificationsApi

All URIs are relative to *http://localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**userNotificationsPatch**](NotificationsApi.md#userNotificationsPatch) | **PATCH** /user/notifications | 

<a name="userNotificationsPatch"></a>
# **userNotificationsPatch**
> userNotificationsPatch(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.NotificationsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

NotificationsApi apiInstance = new NotificationsApi();
NotificationsDTO body = new NotificationsDTO(); // NotificationsDTO | 
try {
    apiInstance.userNotificationsPatch(body);
} catch (ApiException e) {
    System.err.println("Exception when calling NotificationsApi#userNotificationsPatch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NotificationsDTO**](NotificationsDTO.md)|  |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

