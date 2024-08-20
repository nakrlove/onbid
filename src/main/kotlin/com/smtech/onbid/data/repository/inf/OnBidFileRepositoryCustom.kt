package com.smtech.onbid.data.repository.inf

import com.smtech.onbid.data.dto.OnBidFileDTO

interface OnBidFileRepositoryCustom {
    /** 등록된 파일 카테고리목록 조회 */
    fun getFileCategory(bididx: Int): List<OnBidFileDTO>
}