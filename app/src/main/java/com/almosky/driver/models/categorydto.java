package com.almosky.driver.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class categorydto {


    @SerializedName("Detail")
    @Expose
    private ArrayList<Detail> detail = null;

    public ArrayList<Detail> getDetail() {
        return detail;
    }

    public void setDetail(ArrayList<Detail> detail) {
        this.detail = detail;
    }

    public class Detail {

        @SerializedName("items")
        @Expose
        private ArrayList<Item> items = null;
        @SerializedName("categoryId")
        @Expose
        private Integer categoryId;
        @SerializedName("categoryName")
        @Expose
        private String categoryName;

        @SerializedName("CategoryIcons")
        @Expose
        private String categoryIcons;

        public String getCategoryIcons() {
            return categoryIcons;
        }

        public void setCategoryIcons(String categoryIcons) {
            this.categoryIcons = categoryIcons;
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public void setItems(ArrayList<Item> items) {
            this.items = items;
        }

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public class Item {

            @SerializedName("item_name")
            @Expose
            private String itemName;
            @SerializedName("item_image")
            @Expose
            private String itemImage;
            @SerializedName("categoryId")
            @Expose
            private Integer categoryId;
            @SerializedName("itemId")
            @Expose
            private Integer itemId;
            @SerializedName("categoryName")
            @Expose
            private String categoryName;

            @SerializedName("drycleanpricenormal")
            @Expose
            private Integer drycleanpricenormal;



            @SerializedName("drycleanpricefast")
            @Expose
            private Integer drycleanpricefast;

            @SerializedName("washironpricenormal")
            @Expose
            private Integer washironpricenormal;

            @SerializedName("washironpricefast")
            @Expose
            private Integer washironpricefast;

            @SerializedName("ironingpricenormal")
            @Expose
            private Integer ironingpricenormal;

            @SerializedName("ironingpricefast")
            @Expose
            private Integer ironingpricefast;


            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public String getItemImage() {
                return itemImage;
            }

            public void setItemImage(String itemImage) {
                this.itemImage = itemImage;
            }

            public Integer getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Integer categoryId) {
                this.categoryId = categoryId;
            }

            public Integer getItemId() {
                return itemId;
            }

            public void setItemId(Integer itemId) {
                this.itemId = itemId;
            }

            public Integer getDrycleanpricenormal() {
                return drycleanpricenormal;
            }

            public void setDrycleanpricenormal(Integer drycleanpricenormal) {
                this.drycleanpricenormal = drycleanpricenormal;
            }

            public Integer getDrycleanpricefast() {
                return drycleanpricefast;
            }

            public void setDrycleanpricefast(Integer drycleanpricefast) {
                this.drycleanpricefast = drycleanpricefast;
            }

            public Integer getWashironpricenormal() {
                return washironpricenormal;
            }

            public void setWashironpricenormal(Integer washironpricenormal) {
                this.washironpricenormal = washironpricenormal;
            }

            public Integer getWashironpricefast() {
                return washironpricefast;
            }

            public void setWashironpricefast(Integer washironpricefast) {
                this.washironpricefast = washironpricefast;
            }

            public Integer getIroningpricenormal() {
                return ironingpricenormal;
            }

            public void setIroningpricenormal(Integer ironingpricenormal) {
                this.ironingpricenormal = ironingpricenormal;
            }

            public Integer getIroningpricefast() {
                return ironingpricefast;
            }

            public void setIroningpricefast(Integer ironingpricefast) {
                this.ironingpricefast = ironingpricefast;
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

        }

    }

}




