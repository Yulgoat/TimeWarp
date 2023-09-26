# MessagingApi

All URIs are relative to *http://localhost:8080/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**discussionsCreatePost**](MessagingApi.md#discussionsCreatePost) | **POST** /discussions/create | Create a new discussion
[**discussionsDiscussionIdMessagesGet**](MessagingApi.md#discussionsDiscussionIdMessagesGet) | **GET** /discussions/{discussion_id}/messages | Get all messages in a conversation
[**discussionsDiscussionIdMessagesPost**](MessagingApi.md#discussionsDiscussionIdMessagesPost) | **POST** /discussions/{discussion_id}/messages | Send a message in a disccusion
[**discussionsGet**](MessagingApi.md#discussionsGet) | **GET** /discussions | Get a list of all discussions

<a name="discussionsCreatePost"></a>
# **discussionsCreatePost**
> discussionsCreatePost(body)

Create a new discussion

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.MessagingApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

MessagingApi apiInstance = new MessagingApi();
DiscussionsCreateBody body = new DiscussionsCreateBody(); // DiscussionsCreateBody | 
try {
    apiInstance.discussionsCreatePost(body);
} catch (ApiException e) {
    System.err.println("Exception when calling MessagingApi#discussionsCreatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**DiscussionsCreateBody**](DiscussionsCreateBody.md)|  |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="discussionsDiscussionIdMessagesGet"></a>
# **discussionsDiscussionIdMessagesGet**
> List&lt;MessageDTO&gt; discussionsDiscussionIdMessagesGet(discussionId)

Get all messages in a conversation

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.MessagingApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

MessagingApi apiInstance = new MessagingApi();
Integer discussionId = 56; // Integer | 
try {
    List<MessageDTO> result = apiInstance.discussionsDiscussionIdMessagesGet(discussionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling MessagingApi#discussionsDiscussionIdMessagesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **discussionId** | **Integer**|  |

### Return type

[**List&lt;MessageDTO&gt;**](MessageDTO.md)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="discussionsDiscussionIdMessagesPost"></a>
# **discussionsDiscussionIdMessagesPost**
> discussionsDiscussionIdMessagesPost(body, discussionId)

Send a message in a disccusion

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.MessagingApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

MessagingApi apiInstance = new MessagingApi();
MessageDTO body = new MessageDTO(); // MessageDTO | 
Integer discussionId = 56; // Integer | 
try {
    apiInstance.discussionsDiscussionIdMessagesPost(body, discussionId);
} catch (ApiException e) {
    System.err.println("Exception when calling MessagingApi#discussionsDiscussionIdMessagesPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**MessageDTO**](MessageDTO.md)|  |
 **discussionId** | **Integer**|  |

### Return type

null (empty response body)

### Authorization

[CookieAuth](../README.md#CookieAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="discussionsGet"></a>
# **discussionsGet**
> List&lt;DiscussionDTO&gt; discussionsGet()

Get a list of all discussions

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.MessagingApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: CookieAuth
ApiKeyAuth CookieAuth = (ApiKeyAuth) defaultClient.getAuthentication("CookieAuth");
CookieAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//CookieAuth.setApiKeyPrefix("Token");

MessagingApi apiInstance = new MessagingApi();
try {
    List<DiscussionDTO> result = apiInstance.discussionsGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling MessagingApi#discussionsGet");
    e.printStackTrace();
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

