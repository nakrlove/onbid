package com.smtech.onbid.data.dto

/**
 * 입찰일자/감정가/보증금
 */
data class OnBidDayDTO(
    val sdate: String?, // 입찰 시작일
    val edate: String?,
    val evalue: String?,
    val deposit: String?,
    val daysidx: Int?,
    val bididx: Int?,
    val onbid_status: String?, // 입찰 진행 상태
    val regdate: String?,
    val name: String?
)
