package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Notifications
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-02T22:03:18.108Z")

public class Notifications   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("recipientId")
  private Long recipientId = null;

  @JsonProperty("sentTimestamp")
  private OffsetDateTime sentTimestamp = null;

  public Notifications id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Notifications recipientId(Long recipientId) {
    this.recipientId = recipientId;
    return this;
  }

  /**
   * Get recipientId
   * @return recipientId
  **/
  @ApiModelProperty(value = "")


  public Long getRecipientId() {
    return recipientId;
  }

  public void setRecipientId(Long recipientId) {
    this.recipientId = recipientId;
  }

  public Notifications sentTimestamp(OffsetDateTime sentTimestamp) {
    this.sentTimestamp = sentTimestamp;
    return this;
  }

  /**
   * Get sentTimestamp
   * @return sentTimestamp
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getSentTimestamp() {
    return sentTimestamp;
  }

  public void setSentTimestamp(OffsetDateTime sentTimestamp) {
    this.sentTimestamp = sentTimestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Notifications notifications = (Notifications) o;
    return Objects.equals(this.id, notifications.id) &&
        Objects.equals(this.recipientId, notifications.recipientId) &&
        Objects.equals(this.sentTimestamp, notifications.sentTimestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, recipientId, sentTimestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Notifications {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    recipientId: ").append(toIndentedString(recipientId)).append("\n");
    sb.append("    sentTimestamp: ").append(toIndentedString(sentTimestamp)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

