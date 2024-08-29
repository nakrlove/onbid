package com.smtech.onbid.data.repository.inf

import com.smtech.onbid.data.dto.OnBidDayDTO
import com.smtech.onbid.data.dto.OnBidMapDTO

interface OnBidRepositoryCustom {
    /** 목록조회 */
    fun findOnBidLists(searchTerm: Int?, limit: Int, offset: Int): List<OnBidMapDTO>
    fun findOnBidCount(searchTerm: Int?): Long

    fun onBidDetails(bididx: Int):OnBidMapDTO

    fun findDaysQuery(bididx: Int): List<OnBidDayDTO>
}