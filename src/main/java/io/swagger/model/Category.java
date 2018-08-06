package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Category
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-02T22:03:18.108Z")
@Entity
public class Category   {
  @JsonProperty("id")
  @Id
  @GeneratedValue
  private int id ;

  @JsonProperty("categoryName")
  @Size(min=3, message="Name should have atleast 3 characters")
  @ApiModelProperty(notes="Name should have atleast 3 characters")
  private String categoryName ;

  public Category id(int id) {
    this.id = id;
    return this;
  }


  public Category(){}
  public Category(int id, String categoryName) {
    this.id = id;
    this.categoryName = categoryName;
  }

  public Category( String categoryName) {
    this.categoryName = categoryName;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Category categoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }

  /**
   * Get categoryName
   * @return categoryName
  **/
  @ApiModelProperty(value = "")


  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Category category = (Category) o;
    return Objects.equals(this.id, category.id) &&
        Objects.equals(this.categoryName, category.categoryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, categoryName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Category {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    categoryName: ").append(toIndentedString(categoryName)).append("\n");
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

