package com.smtech.onbid.data.repository.inf

import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.data.dto.OnBidMapDTO

interface OnBidRepositoryCustom {
    fun findOnBidWithDetails(searchTerm: String?, limit: Int, offset: Int): List<OnBidMapDTO>
    fun countOnBidWithDetails(searchTerm: String?): Long

    fun onBidDetails(bididx: Int):OnBidMapDTO

    fun findDaysQuery(bididx: Int): List<OnBidDayDTO>
}