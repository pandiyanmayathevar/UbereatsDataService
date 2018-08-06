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
 * Order
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-02T22:03:18.108Z")

public class Order   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("dishId")
  private Long dishId = null;

  @JsonProperty("customerId")
  private Long customerId = null;

  @JsonProperty("processingTimestamp")
  private OffsetDateTime processingTimestamp = null;

  public Order id(Long id) {
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

  public Order dishId(Long dishId) {
    this.dishId = dishId;
    return this;
  }

  /**
   * Get dishId
   * @return dishId
  **/
  @ApiModelProperty(value = "")


  public Long getDishId() {
    return dishId;
  }

  public void setDishId(Long dishId) {
    this.dishId = dishId;
  }

  public Order customerId(Long customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
  **/
  @ApiModelProperty(value = "")


  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Order processingTimestamp(OffsetDateTime processingTimestamp) {
    this.processingTimestamp = processingTimestamp;
    return this;
  }

  /**
   * Get processingTimestamp
   * @return processingTimestamp
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getProcessingTimestamp() {
    return processingTimestamp;
  }

  public void setProcessingTimestamp(OffsetDateTime processingTimestamp) {
    this.processingTimestamp = processingTimestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(this.id, order.id) &&
        Objects.equals(this.dishId, order.dishId) &&
        Objects.equals(this.customerId, order.customerId) &&
        Objects.equals(this.processingTimestamp, order.processingTimestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dishId, customerId, processingTimestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Order {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dishId: ").append(toIndentedString(dishId)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    processingTimestamp: ").append(toIndentedString(processingTimestamp)).append("\n");
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

