package com.oganbelema.agromall.farmer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class FarmerResponse(
    val `data`: Data,
    val status: String
)

data class Data(
    val farmers: List<Farmer>,
    val imageBaseUrl: String,
    val totalRec: Int
)

@Entity(tableName = "farmer")
data class Farmer(
    val address: String,
    val bvn: String,
    val city: String,
    val dob: String,
    @SerializedName("email_address")
    @ColumnInfo(name = "email_address")
    val emailAddress: String,
    @SerializedName("expiry_date")
    @ColumnInfo(name = "expiry_date")
    val expiryDate: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("farmer_id")
    @ColumnInfo(name = "farmer_id")
    val farmerId: String,
    val fingerprint: String,
    @SerializedName("first_name")
    @ColumnInfo(name = "first_name")
    var firstName: String,
    val gender: String,
    @SerializedName("id_image")
    @ColumnInfo(name = "id_image")
    val idImage: String,
    @SerializedName("id_no")
    @ColumnInfo(name = "id_no")
    val idNo: String,
    @SerializedName("id_type")
    @ColumnInfo(name = "id_type")
    val idType: String,
    @SerializedName("issue_date")
    @ColumnInfo(name = "issue_date")
    val issueDate: String,
    val lga: String,
    @SerializedName("marital_status")
    @ColumnInfo(name = "marital_status")
    val maritalStatus: String,
    @SerializedName("middle_name")
    @ColumnInfo(name = "middle_name")
    val middleName: String,
    @SerializedName("mobile_no")
    @ColumnInfo(name = "mobile_no")
    val mobileNo: String,
    val nationality: String,
    val occupation: String,
    @SerializedName("passport_photo")
    @ColumnInfo(name = "passport_photo")
    val passportPhoto: String,
    @SerializedName("reg_no")
    @ColumnInfo(name = "reg_no")
    val regNo: String,
    @SerializedName("spouse_name")
    @ColumnInfo(name = "spouse_name")
    val spouseName: String,
    val state: String,
    var surname: String
)