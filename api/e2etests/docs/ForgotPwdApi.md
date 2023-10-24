# ForgotPwdApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**userForgotpwdPost**](ForgotPwdApi.md#userForgotpwdPost) | **POST** /user/forgotpwd |  |


<a id="userForgotpwdPost"></a>
# **userForgotpwdPost**
> userForgotpwdPost(newPasswordDTO)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ForgotPwdApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    ForgotPwdApi apiInstance = new ForgotPwdApi(defaultClient);
    NewPasswordDTO newPasswordDTO = new NewPasswordDTO(); // NewPasswordDTO | 
    try {
      apiInstance.userForgotpwdPost(newPasswordDTO);
    } catch (ApiException e) {
      System.err.println("Exception when calling ForgotPwdApi#userForgotpwdPost");
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
| **newPasswordDTO** | [**NewPasswordDTO**](NewPasswordDTO.md)|  | |

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
