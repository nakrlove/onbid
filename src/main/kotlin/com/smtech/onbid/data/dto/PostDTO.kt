package com.smtech.onbid.data.dto

import jakarta.validation.constraints.NotNull

data class PostDTO(
        @field:NotNull
        val addr1: String
      , val addr2: String?
      , val addr3: String?
      , val totalPage: Long? = 1
      , val reload: Int? = 0)
