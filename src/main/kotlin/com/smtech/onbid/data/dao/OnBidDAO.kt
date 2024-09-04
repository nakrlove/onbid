package com.smtech.onbid.data.dao

import com.smtech.onbid.data.dto.OnBidMapDTO
import com.smtech.onbid.data.entity.OnBid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.util.*

interface OnBidDAO {

//    fun findById(bididx:Int): Optional<OnBid>
    fun saveOnBid(onBid: OnBid): OnBid
    fun findDetail(onBid: OnBid): OnBidMapDTO?
    fun findAlls(page: PageRequest): Page<OnBid>?
    /** 목록조회 */
    fun findOnBidLists(searchTerm: Int?, limit: Int, offset: Int): List<OnBidMapDTO>
    fun findOnBidCount(searchTerm: Int?): Long
}