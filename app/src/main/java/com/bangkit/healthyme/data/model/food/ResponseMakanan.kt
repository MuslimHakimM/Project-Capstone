package com.bangkit.healthyme.data.model.food

import com.google.gson.annotations.SerializedName

data class ResponseMakanan(

	@field:SerializedName("msg")
	val msg: String,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("response")
	val response: Response,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class MakananItem(

	@field:SerializedName("kalori")
	val kalori: String,

	@field:SerializedName("sodium")
	val sodium: String,

	@field:SerializedName("link_nutrisi")
	val linkNutrisi: String,

	@field:SerializedName("name_food")
	val nameFood: String,

	@field:SerializedName("protein")
	val protein: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("link_resep")
	val linkResep: String,

	@field:SerializedName("lemak")
	val lemak: String
)

data class Response(

	@field:SerializedName("totalItems")
	val totalItems: Int,

	@field:SerializedName("Makanan")
	val makanan: List<MakananItem>,

	@field:SerializedName("totalPages")
	val totalPages: Int,

	@field:SerializedName("currentPage")
	val currentPage: Int
)
