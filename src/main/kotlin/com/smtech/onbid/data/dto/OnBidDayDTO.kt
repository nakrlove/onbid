package com.smtech.onbid.data.dto

/**
 * 입찰일자/감정가/보증금
 */
data class OnBidDayDTO(
    var sdate: String, /* 입찰시작일 */
    var edate: String,
    var evalue: String,
    var deposit: String,
    )
