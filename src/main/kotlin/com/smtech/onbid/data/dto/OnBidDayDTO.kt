package com.smtech.onbid.data.dto

//import com.fasterxml.jackson.annotation.JsonFormat

/**
 * 입찰일자/감정가/보증금
 */
data class OnBidDayDTO(
//    val sdate: String?, // 입찰 시작일
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 원하는 형식으로 변경
    val edate  : String?,
    val evalue : String?, //감정가
    val deposit: String?,//보증금
    val daysidx: Int?,
    val bididx: Int?,
    val onbid_status: String?, //입찰진행상태
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 원하는 형식으로 변경
    val regdate: String?,
    val name: String?,
    val bblig: Any?,
)
