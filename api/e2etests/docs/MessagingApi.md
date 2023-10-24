# MessagingApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**discussionsCreatePost**](MessagingApi.md#discussionsCreatePost) | **POST** /discussions/create | Create a new discussion |
| [**discussionsDiscussionIdMessagesGet**](MessagingApi.md#discussionsDiscussionIdMessagesGet) | **GET** /discussions/{discussion_id}/messages | Get all messages in a conversation |
| [**discussionsDiscussionIdMessagesPost**](MessagingApi.md#discussionsDiscussionIdMessagesPost) | **POST** /discussions/{discussion_id}/messages | Send a message in a disccusion |
| [**discussionsUsernameGet**](MessagingApi.md#discussionsUsernameGet) | **GET** /discussions/{username} | Get a list of all discussions of a user |


<a id="discussionsCreatePost"></a>
# **discussionsCreatePost**
> discussionsCreatePost(discussionsCreatePostRequest)

Create a new discussion

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
    Integer discussionId = 56; // Integer | 
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
| **discussionId** | **Integer**|  | |

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

<a id="discussionsDiscussionIdMessagesPost"></a>
# **discussionsDiscussionIdMessagesPost**
> discussionsDiscussionIdMessagesPost(discussionId, messageDTO)

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
    Integer discussionId = 56; // Integer | 
    MessageDTO messageDTO = new MessageDTO(); // MessageDTO | 
    try {
      apiInstance.discussionsDiscussionIdMessagesPost(discussionId, messageDTO);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessagingApi#discussionsDiscussionIdMessagesPost");
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
| **discussionId** | **Integer**|  | |
| **messageDTO** | [**MessageDTO**](MessageDTO.md)|  | |

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

<a id="discussionsUsernameGet"></a>
# **discussionsUsernameGet**
> List&lt;DiscussionDTO&gt; discussionsUsernameGet(username)

Get a list of all discussions of a user

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
    String username = "username_example"; // String | 
    try {
      List<DiscussionDTO> result = apiInstance.discussionsUsernameGet(username);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MessagingApi#discussionsUsernameGet");
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
| **username** | **String**|  | |

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

