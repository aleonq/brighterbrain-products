package com.mindworks.shoppingraga.datastore.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taru on 10/15/2017.
 */
@Entity(tableName = "products")
public class Product implements Parcelable {

    @Ignore
    public Product(String name, String description, String regularPrice, String salePrice, String productPhotoUri) {
        this.name = name;
        this.description = description;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
        this.productPhotoUri = productPhotoUri;
    }

    public Product(long id, String name, String description, String regularPrice, String salePrice, String productPhotoUri) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
        this.productPhotoUri = productPhotoUri;
    }

    @SerializedName("_id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private long id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("description")
    @ColumnInfo(name = "description")
    private String description;

    @SerializedName("regularPrice")
    @ColumnInfo(name = "regularPrice")
    private String regularPrice;

    @SerializedName("salePrice")
    @ColumnInfo(name = "salePrice")
    private String salePrice;

    @SerializedName("productPhotoUri")
    @ColumnInfo(name = "productPhotoUri")
    private String productPhotoUri;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getProductPhotoUri() {
        return productPhotoUri;
    }

    public void setProductPhotoUri(String productPhotoUri) {
        this.productPhotoUri = productPhotoUri;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", regularPrice=" + regularPrice +
                ", salePrice=" + salePrice +
                ", productPhotoUri='" + productPhotoUri + '\'' +
                '}';
    }

    protected Product(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
        regularPrice = in.readString();
        salePrice = in.readString();
        productPhotoUri = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(regularPrice);
        parcel.writeString(salePrice);
        parcel.writeString(productPhotoUri);
    }
}