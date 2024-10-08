package com.smtech.onbid.data.dto

data class OnBidMapDTO(

    val bididx: Int?,
    val addr1: String?,
    val addr2: String?,
    val it_type: String?,
    val ld_area: String?,
    val ld_area_memo: String?,
    val ld_area_pyeong: Double?,
    val build_area: String?,
    val build_area_memo: String?,
    val build_area_pyeong: Double?,
    val rd_addr: String?,
    val streeaddr2: String?,
    val bruptcy_admin_name: String?,
    val bruptcy_admin_phone: String?,
    val renter: String?,
    val estatetype: String?,
    val disposal_type: String?,
    val note: String?,
    val land_classification: String?,
    val progress_status: String?,
//    val sdate: String?,
    val edate: String?,
    val evalue: String?,
    val deposit: String?,
    val onbid_status: String?,
    val status: String?,
    val land_classification_name: String?,
    val national_land_planning_use_laws: String?,
    val other_laws: String?,
    val enforcement_decree: String?,
    val idx: Int?,
    val debtor: String?,
    val pnu:String?, //필지번호
    val sale_notice_id:String?, //매각공고번호
)
