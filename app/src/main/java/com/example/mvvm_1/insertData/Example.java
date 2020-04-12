package com.example.mvvm_1.insertData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    
        @SerializedName("product_offset")
        @Expose
        private Integer product_offset;  
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("products")
        @Expose
        private List<Product> products = null;

    public Integer getProduct_offset() {
        return product_offset;
    }

    public void setProduct_offset(Integer product_offset) {
        this.product_offset = product_offset;
    }

    public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

    public class Product {

        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("last_date")
        @Expose
        private String lastDate;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("product_image")
        @Expose
        private String productImage;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getLastDate() {
            return lastDate;
        }

        public void setLastDate(String lastDate) {
            this.lastDate = lastDate;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

    }
}
