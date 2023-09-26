# LanguageApi

All URIs are relative to *http://localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**userLanguagePatch**](LanguageApi.md#userLanguagePatch) | **PATCH** /user/language | 

<a name="userLanguagePatch"></a>
# **userLanguagePatch**
> userLanguagePatch(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.LanguageApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

LanguageApi apiInstance = new LanguageApi();
UserLanguageBody body = new UserLanguageBody(); // UserLanguageBody | 
try {
    apiInstance.userLanguagePatch(body);
} catch (ApiException e) {
    System.err.println("Exception when calling LanguageApi#userLanguagePatch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UserLanguageBody**](UserLanguageBody.md)|  |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

