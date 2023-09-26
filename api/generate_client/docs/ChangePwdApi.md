# ChangePwdApi

All URIs are relative to *http://localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**userChangepwdPatch**](ChangePwdApi.md#userChangepwdPatch) | **PATCH** /user/changepwd | 

<a name="userChangepwdPatch"></a>
# **userChangepwdPatch**
> userChangepwdPatch(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ChangePwdApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

ChangePwdApi apiInstance = new ChangePwdApi();
ChangePasswordDTO body = new ChangePasswordDTO(); // ChangePasswordDTO | 
try {
    apiInstance.userChangepwdPatch(body);
} catch (ApiException e) {
    System.err.println("Exception when calling ChangePwdApi#userChangepwdPatch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ChangePasswordDTO**](ChangePasswordDTO.md)|  |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

