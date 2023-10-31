# MessagingApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**discussionsCreatePost**](MessagingApi.md#discussionsCreatePost) | **POST** /discussions/create | Create a new discussion with a user |
| [**discussionsDiscussionIdMessagesGet**](MessagingApi.md#discussionsDiscussionIdMessagesGet) | **GET** /discussions/{discussion_id}/messages | Get all messages in a conversation |
| [**discussionsGet**](MessagingApi.md#discussionsGet) | **GET** /discussions | Get a list of all discussions of the current user |
| [**discussionsMessageGet**](MessagingApi.md#discussionsMessageGet) | **GET** /discussions/message | Receive a message |
| [**discussionsMessagePost**](MessagingApi.md#discussionsMessagePost) | **POST** /discussions/message | Send a message in a disccusion |


<a id="discussionsCreatePost"></a>
# **discussionsCreatePost**
> discussionsCreatePost(discussionsCreatePostRequest)

Create a new discussion with a user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessagingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessagingApi apiInstance = new MessagingApi(defaultClient);
    DiscussionsCreatePostRequest discussionsCreatePostRequest = new DiscussionsCreatePostRequest(); // DiscussionsCreatePostRequest | 
    try {
      apiInstance.discussionsCreatePost(discussionsCreatePostRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessagingApi#discussionsCreatePost");
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
| **discussionsCreatePostRequest** | [**DiscussionsCreatePostRequest**](DiscussionsCreatePostRequest.md)|  | |

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
| **200** | Conversation created successfully |  -  |
| **0** | Error |  -  |

<a id="discussionsDiscussionIdMessagesGet"></a>
# **discussionsDiscussionIdMessagesGet**
> List&lt;MessageDTO&gt; discussionsDiscussionIdMessagesGet(discussionId)

Get all messages in a conversation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessagingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessagingApi apiInstance = new MessagingApi(defaultClient);
    UUID discussionId = UUID.randomUUID(); // UUID | 
    try {
      List<MessageDTO> result = apiInstance.discussionsDiscussionIdMessagesGet(discussionId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessagingApi#discussionsDiscussionIdMessagesGet");
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
| **discussionId** | **UUID**|  | |

### Return type

[**List&lt;MessageDTO&gt;**](MessageDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response |  -  |

<a id="discussionsGet"></a>
# **discussionsGet**
> List&lt;DiscussionDTO&gt; discussionsGet()

Get a list of all discussions of the current user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessagingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessagingApi apiInstance = new MessagingApi(defaultClient);
    try {
      List<DiscussionDTO> result = apiInstance.discussionsGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessagingApi#discussionsGet");
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

[**List&lt;DiscussionDTO&gt;**](DiscussionDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful response |  -  |

<a id="discussionsMessageGet"></a>
# **discussionsMessageGet**
> List&lt;MessageDTO&gt; discussionsMessageGet()

Receive a message

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessagingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessagingApi apiInstance = new MessagingApi(defaultClient);
    try {
      List<MessageDTO> result = apiInstance.discussionsMessageGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessagingApi#discussionsMessageGet");
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

[**List&lt;MessageDTO&gt;**](MessageDTO.md)

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

<a id="discussionsMessagePost"></a>
# **discussionsMessagePost**
> discussionsMessagePost(postMessageDTO)

Send a message in a disccusion

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MessagingApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: CookieAuth
    ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
    CookieAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //CookieAuth.setApiKeyPrefix("Token");

    MessagingApi apiInstance = new MessagingApi(defaultClient);
    PostMessageDTO postMessageDTO = new PostMessageDTO(); // PostMessageDTO | 
    try {
      apiInstance.discussionsMessagePost(postMessageDTO);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessagingApi#discussionsMessagePost");
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
| **postMessageDTO** | [**PostMessageDTO**](PostMessageDTO.md)|  | |

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
| **200** | Message sent successfully |  -  |
| **0** | Error |  -  |

