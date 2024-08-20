package com.smtech.onbid.data.dto

/**
 * File정보 DTO
 */
data class OnBidFileDTO(
    val idx: Int? = null,
    val bididx: Int?,
    val code: String?,
    val filename: String?,
    val filetype: String?,
    val filesize: Int?,
    val filepath: String?,
    val file: ByteArray?,
    val codename: String,
)
