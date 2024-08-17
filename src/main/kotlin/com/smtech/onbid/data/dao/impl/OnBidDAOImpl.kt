package com.smtech.onbid.data.dao.impl

import com.smtech.onbid.data.dao.OnBidDAO
import com.smtech.onbid.data.dto.OnBidMapDTO
import com.smtech.onbid.data.repository.OnBidRepository
import com.smtech.onbid.data.entity.OnBid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class OnBidDAOImpl(@Autowired val onbidDRepository: OnBidRepository):OnBidDAO {

    val  LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun saveOnBid(onBid: OnBid): OnBid {
        onbidDRepository.save(onBid)
        return onBid
    }

    /** 상세보기 */
    override fun findDetail(onBid: OnBid): OnBidMapDTO? {
        return onBid.bididx?.let { onbidDRepository.onBidDetails(it) }
    }

//    @Transactional(readOnly = true)
    override fun findAlls(page: PageRequest): Page<OnBid>? {
//        return onbidDRepository.findAll(page)
        return null
    }

    override fun findOnBidWithDetails(searchTerm: String?, limit: Int, offset: Int): List<OnBidMapDTO> {
        return onbidDRepository.findOnBidWithDetails(searchTerm,limit,offset)
    }

    override fun countOnBidWithDetails(searchTerm: String?): Long {
        return onbidDRepository.countOnBidWithDetails(searchTerm)
    }
}