# AuthenticationApi

All URIs are relative to *http://localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**userDeletePost**](AuthenticationApi.md#userDeletePost) | **POST** /user/delete | 
[**userSigninPost**](AuthenticationApi.md#userSigninPost) | **POST** /user/signin | 
[**userSignoutPost**](AuthenticationApi.md#userSignoutPost) | **POST** /user/signout | 
[**userSignupPost**](AuthenticationApi.md#userSignupPost) | **POST** /user/signup | 

<a name="userDeletePost"></a>
# **userDeletePost**
> userDeletePost()



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthenticationApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

AuthenticationApi apiInstance = new AuthenticationApi();
try {
    apiInstance.userDeletePost();
} catch (ApiException e) {
    System.err.println("Exception when calling AuthenticationApi#userDeletePost");
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

<a name="userSigninPost"></a>
# **userSigninPost**
> userSigninPost(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthenticationApi;


AuthenticationApi apiInstance = new AuthenticationApi();
UserDTO body = new UserDTO(); // UserDTO | 
try {
    apiInstance.userSigninPost(body);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthenticationApi#userSigninPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UserDTO**](UserDTO.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="userSignoutPost"></a>
# **userSignoutPost**
> userSignoutPost()



### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.AuthenticationApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

AuthenticationApi apiInstance = new AuthenticationApi();
try {
    apiInstance.userSignoutPost();
} catch (ApiException e) {
    System.err.println("Exception when calling AuthenticationApi#userSignoutPost");
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

<a name="userSignupPost"></a>
# **userSignupPost**
> userSignupPost(body)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthenticationApi;


AuthenticationApi apiInstance = new AuthenticationApi();
UserDTO body = new UserDTO(); // UserDTO | 
try {
    apiInstance.userSignupPost(body);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthenticationApi#userSignupPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**UserDTO**](UserDTO.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

