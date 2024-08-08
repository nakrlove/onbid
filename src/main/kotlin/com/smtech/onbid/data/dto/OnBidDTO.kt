package com.smtech.onbid.data.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.Length

data class OnBidDTO(

        @field:NotNull(message = "정확한 주소를 입력해주세요!")
        @field:Size(min = 1, message = "주소는 최소 1자 이상이어야 합니다.")
        val addr1: String

      , var addr2:String
      , @field:NotNull(message = "데이터가 누락되었습니다.")
        @field:Size(min = 1, message = "데이터는 최소 1자 이상이어야 합니다.")
        val bankruptcyPhone:String

      , @field:NotNull
        val bankruptcyName: String
)