# ForgotPwdApi

All URIs are relative to *http://localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**userForgotpwdPost**](ForgotPwdApi.md#userForgotpwdPost) | **POST** /user/forgotpwd | 

<a name="userForgotpwdPost"></a>
# **userForgotpwdPost**
> userForgotpwdPost(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.ForgotPwdApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

ForgotPwdApi apiInstance = new ForgotPwdApi();
NewPasswordDTO body = new NewPasswordDTO(); // NewPasswordDTO | 
try {
    apiInstance.userForgotpwdPost(body);
} catch (ApiException e) {
    System.err.println("Exception when calling ForgotPwdApi#userForgotpwdPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**NewPasswordDTO**](NewPasswordDTO.md)|  |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

