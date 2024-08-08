package com.smtech.onbid.data.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

/**
 * 코드 DTO
 */
data class AttachCodeDTO(
    @field:NotNull(message = "코드명을 입력하세요!") @field:Size(
        min = 1,
        message = "코드명을 최소 1자 이상이어야 합니다."
    ) val codename: String,
    @field:NotNull(message = "Page number is required") val page: Int = 1,
    @field:NotNull(message = "Page size is required") val size: Int = 10
)
