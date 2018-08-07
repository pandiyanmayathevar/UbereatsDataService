package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UberOrder
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-07T18:20:17.927Z")
@Entity
public class UberOrder   {
    @JsonProperty("id")
    @Id
    @GeneratedValue
    private Integer id ;

    @JsonProperty("dishId")
    @NotNull
    private Integer dishId ;

    @JsonProperty("customerId")
    @NotNull
    private Integer customerId ;

    @JsonProperty("processingTimestamp")
    @CreationTimestamp
    private java.sql.Timestamp processingTimestamp ;

    public UberOrder id(Integer id) {
        this.id = id;
        return this;
    }

    public UberOrder(){}

    public UberOrder(Integer id, Integer dishId, Integer customerId, java.sql.Timestamp processingTimestamp) {
        this.id = id;
        this.dishId = dishId;
        this.customerId = customerId;
        this.processingTimestamp = processingTimestamp;
    }

    /**
     * Get id
     * @return id
     **/
    @ApiModelProperty(value = "")


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UberOrder dishId(Integer dishId) {
        this.dishId = dishId;
        return this;
    }

    /**
     * Get dishId
     * @return dishId
     **/
    @ApiModelProperty(value = "")


    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public UberOrder customerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    /**
     * Get customerId
     * @return customerId
     **/
    @ApiModelProperty(value = "")


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public UberOrder processingTimestamp(java.sql.Timestamp processingTimestamp) {
        this.processingTimestamp = processingTimestamp;
        return this;
    }

//    /**
//     * Get processingTimestamp
//     * @return processingTimestamp
//     **/
//    @ApiModelProperty(value = "")

    @Valid

    public java.sql.Timestamp getProcessingTimestamp() {
        return processingTimestamp;
    }

    public void setProcessingTimestamp(java.sql.Timestamp processingTimestamp) {
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
        UberOrder UberOrder = (UberOrder) o;
        return Objects.equals(this.id, UberOrder.id) &&
                Objects.equals(this.dishId, UberOrder.dishId) &&
                Objects.equals(this.customerId, UberOrder.customerId) &&
                Objects.equals(this.processingTimestamp, UberOrder.processingTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dishId, customerId, processingTimestamp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UberOrder {\n");

        //sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    dishId: ").append(toIndentedString(dishId)).append("\n");
        sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
        //sb.append("    processingTimestamp: ").append(toIndentedString(processingTimestamp)).append("\n");
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

