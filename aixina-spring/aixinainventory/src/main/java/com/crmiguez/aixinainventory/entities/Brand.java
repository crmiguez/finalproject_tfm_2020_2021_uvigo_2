package com.crmiguez.aixinainventory.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "brand")
@Table
public class Brand implements Serializable {

    @Id
    @Size(max = 50)
    @Column(name = "brandId")
    private String brandId;

    @Column(name = "brandName")
    private String brandName;

    @Column(name = "brandAddress")
    private String brandAddress;

    @Column(name = "brandPhone")
    private String brandPhone;

    @Column(name = "brandEmail")
    private String brandEmail;

    @Column(name = "brandWebSite")
    private String brandWebSite;

    public Brand() {
    }

    public Brand(@Size(max = 50) String brandId, String brandName, String brandAddress, String brandPhone, String brandWebSite) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandAddress = brandAddress;
        this.brandPhone = brandPhone;
        this.brandWebSite = brandWebSite;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandAddress() {
        return brandAddress;
    }

    public void setBrandAddress(String brandAddress) {
        this.brandAddress = brandAddress;
    }

    public String getBrandPhone() {
        return brandPhone;
    }

    public void setBrandPhone(String brandPhone) {
        this.brandPhone = brandPhone;
    }

    public String getBrandEmail() {
        return brandEmail;
    }

    public void setBrandEmail(String brandEmail) {
        this.brandEmail = brandEmail;
    }

    public String getBrandWebSite() {
        return brandWebSite;
    }

    public void setBrandWebSite(String brandWebSite) {
        this.brandWebSite = brandWebSite;
    }
}
