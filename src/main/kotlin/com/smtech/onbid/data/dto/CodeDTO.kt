package com.smtech.onbid.data.dto

import jakarta.validation.constraints.NotNull

/**
 * 코드 DTO
 */
data class CodeDTO(
//    @field:NotNull(message = "코드명을 입력하세요!") @field:Size(
//        min = 1,
//        message = "코드명을 최소 1자 이상이어야 합니다."
//    )

    var idx: Long = 0,
    val codename: String? = null,
    val code: String? = null,
    val subcode: String? = null,
    @field:NotNull(message = "Page number is required") val page: Int = 0,
    @field:NotNull(message = "Page size is required") val size: Int = 10
)
