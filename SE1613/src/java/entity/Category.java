package entity;

/**
 *
 * @author kazaf
 */
public class Category {

    private int categoryId; //auto number
    private String categoryName, description, picture;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String description, String picture) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.picture = picture;
    }

    public Category(String categoryName, String description, String picture) {
        this.categoryName = categoryName;
        this.description = description;
        this.picture = picture;
    }

    public Category(int categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }
    
    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryId=" + categoryId + ", categoryName=" + categoryName + ", description=" + description + ", picture=" + picture + '}';
    }

    
}
