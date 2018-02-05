
package com.spade.ja.datalayer.pojo.response.login;

import com.spade.ja.datalayer.pojo.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User extends BaseModel{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("birth_date")
    @Expose
    private Object birthDate;
    @SerializedName("forget_code")
    @Expose
    private String forgetCode;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("profile_image")
    @Expose
    private Object profileImage;
    @SerializedName("is_admin")
    @Expose
    private Integer isAdmin;
    @SerializedName("is_blocked")
    @Expose
    private Integer isBlocked;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Object birthDate) {
        this.birthDate = birthDate;
    }

    public String getForgetCode() {
        return forgetCode;
    }

    public void setForgetCode(String forgetCode) {
        this.forgetCode = forgetCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Object profileImage) {
        this.profileImage = profileImage;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Integer isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
