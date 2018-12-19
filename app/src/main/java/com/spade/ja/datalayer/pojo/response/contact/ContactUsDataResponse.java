package com.spade.ja.datalayer.pojo.response.contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spade.ja.datalayer.pojo.BaseModel;
import com.spade.ja.datalayer.pojo.response.eventinner.SocialMedium;

import java.util.List;

public class ContactUsDataResponse extends BaseModel {


    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<ContactData> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ContactData> getData() {
        return data;
    }

    public void setData(List<ContactData> data) {
        this.data = data;
    }

    public class ContactData {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("telephone")
        @Expose
        private String telephone;
        @SerializedName("website")
        @Expose
        private String website;
        @SerializedName("social_platforms")
        @Expose
        private List<SocialMedium> socialPlatforms = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public List<SocialMedium> getSocialPlatforms() {
            return socialPlatforms;
        }

        public void setSocialPlatforms(List<SocialMedium> socialPlatforms) {
            this.socialPlatforms = socialPlatforms;
        }
    }
}
