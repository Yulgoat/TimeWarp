/*
 * CPOO Server API
 * This is a prototype of CPOO Project's front/back API. 
 *
 * The version of the OpenAPI document: 0.0.1
 * Contact: contact@mightycode.fr
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openapitools.client.JSON;

/**
 * UnreadMessageDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class UnreadMessageDTO {
  public static final String SERIALIZED_NAME_DISCUSSION_ID = "discussionId";
  @SerializedName(SERIALIZED_NAME_DISCUSSION_ID)
  private UUID discussionId;

  public static final String SERIALIZED_NAME_UNREAD_MESSAGE = "unreadMessage";
  @SerializedName(SERIALIZED_NAME_UNREAD_MESSAGE)
  private Boolean unreadMessage;

  public UnreadMessageDTO() {
  }

  public UnreadMessageDTO discussionId(UUID discussionId) {
    
    this.discussionId = discussionId;
    return this;
  }

   /**
   * Get discussionId
   * @return discussionId
  **/
  @javax.annotation.Nonnull
  public UUID getDiscussionId() {
    return discussionId;
  }


  public void setDiscussionId(UUID discussionId) {
    this.discussionId = discussionId;
  }


  public UnreadMessageDTO unreadMessage(Boolean unreadMessage) {
    
    this.unreadMessage = unreadMessage;
    return this;
  }

   /**
   * Get unreadMessage
   * @return unreadMessage
  **/
  @javax.annotation.Nullable
  public Boolean getUnreadMessage() {
    return unreadMessage;
  }


  public void setUnreadMessage(Boolean unreadMessage) {
    this.unreadMessage = unreadMessage;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnreadMessageDTO unreadMessageDTO = (UnreadMessageDTO) o;
    return Objects.equals(this.discussionId, unreadMessageDTO.discussionId) &&
        Objects.equals(this.unreadMessage, unreadMessageDTO.unreadMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(discussionId, unreadMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnreadMessageDTO {\n");
    sb.append("    discussionId: ").append(toIndentedString(discussionId)).append("\n");
    sb.append("    unreadMessage: ").append(toIndentedString(unreadMessage)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("discussionId");
    openapiFields.add("unreadMessage");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("discussionId");
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to UnreadMessageDTO
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!UnreadMessageDTO.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in UnreadMessageDTO is not found in the empty JSON string", UnreadMessageDTO.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!UnreadMessageDTO.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `UnreadMessageDTO` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : UnreadMessageDTO.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("discussionId").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `discussionId` to be a primitive type in the JSON string but got `%s`", jsonObj.get("discussionId").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!UnreadMessageDTO.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'UnreadMessageDTO' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<UnreadMessageDTO> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(UnreadMessageDTO.class));

       return (TypeAdapter<T>) new TypeAdapter<UnreadMessageDTO>() {
           @Override
           public void write(JsonWriter out, UnreadMessageDTO value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public UnreadMessageDTO read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of UnreadMessageDTO given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of UnreadMessageDTO
  * @throws IOException if the JSON string is invalid with respect to UnreadMessageDTO
  */
  public static UnreadMessageDTO fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, UnreadMessageDTO.class);
  }

 /**
  * Convert an instance of UnreadMessageDTO to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

