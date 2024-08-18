package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.OnBid
import com.smtech.onbid.data.repository.inf.OnBidRepositoryCustom
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository


interface OnBidRepository : CrudRepository<OnBid, Int>, OnBidRepositoryCustom {
    /** 코드명 검색 */
    fun findByAddr1Like(addr1: String, pageable: Pageable): Page<OnBid>

}