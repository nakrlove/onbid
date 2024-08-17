package com.smtech.onbid.handler

import com.smtech.onbid.data.dto.OnBidMapDTO
import com.smtech.onbid.data.entity.OnBid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface OnBidDataHandler {
    fun saveOnBidEntity(onBid: OnBid): OnBid
    fun findDetail(onBid: OnBid): OnBidMapDTO?
    fun findAlls(page: PageRequest): Page<OnBid>?
    fun findOnBidWithDetails(searchTerm: String?, limit: Int, offset: Int): List<OnBidMapDTO>
    fun countOnBidWithDetails(searchTerm: String?): Long
}