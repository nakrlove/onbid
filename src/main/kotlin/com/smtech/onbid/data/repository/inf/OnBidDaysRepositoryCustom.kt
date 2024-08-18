package com.smtech.onbid.data.repository.inf

import com.smtech.onbid.data.dto.OnBidDayDTO

interface OnBidDaysRepositoryCustom {
    fun findBidDaysQuery(bididx: Int?): List<OnBidDayDTO>
}