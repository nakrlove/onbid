package com.smtech.onbid.handler.impl

import com.smtech.onbid.data.dao.OnBidDAO
import com.smtech.onbid.data.dto.OnBidMapDTO
import com.smtech.onbid.data.entity.OnBid
import com.smtech.onbid.handler.OnBidDataHandler
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
@Transactional
class OnBidDataHandlerImpl(@Autowired val onBidDAO: OnBidDAO):OnBidDataHandler {

    override fun saveOnBidEntity(onBid: OnBid): OnBid {
//        TODO("Not yet implemented")
        onBidDAO.saveOnBid(onBid)
        return onBid
    }

    override fun findDetail(onBid: OnBid): OnBidMapDTO? {
        return onBidDAO.findDetail(onBid)
    }


    override fun findAlls(page: PageRequest): Page<OnBid>? {
        return onBidDAO.findAlls(page)
    }

    override fun findOnBidLists(searchTerm: String?, limit: Int, offset: Int): List<OnBidMapDTO> {
        return onBidDAO.findOnBidLists(searchTerm,limit,offset)
    }

    override fun countOnBidWithDetails(searchTerm: String?): Long {
        return onBidDAO.countOnBidWithDetails(searchTerm)
    }
}