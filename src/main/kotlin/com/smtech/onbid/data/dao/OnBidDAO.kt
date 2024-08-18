package com.smtech.onbid.data.dao

import com.smtech.onbid.data.dto.OnBidMapDTO
import com.smtech.onbid.data.entity.OnBid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface OnBidDAO {
    fun saveOnBid(onBid: OnBid): OnBid
    fun findDetail(onBid: OnBid): OnBidMapDTO?
    fun findAlls(page: PageRequest): Page<OnBid>?
    /** 목록조회 */
    fun findOnBidLists(searchTerm: String?, limit: Int, offset: Int): List<OnBidMapDTO>
    fun countOnBidWithDetails(searchTerm: String?): Long
}