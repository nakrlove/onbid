package com.smtech.onbid.data.dto

/**
 * 카테고리 DTO
 */
data class CategoryDTO(  var idx: Int? = null
                       , var content: String? = null
                       , var user:String? = null
                       , var regdate:String? = null
                       , var bididx: Int? = null
                       , var page: Int = 0
                       , var size: Int = 10
)
