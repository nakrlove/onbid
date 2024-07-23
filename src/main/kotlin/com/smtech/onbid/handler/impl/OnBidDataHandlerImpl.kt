package com.smtech.onbid.handler.impl

import com.smtech.onbid.data.dao.OnBidDAO
import com.smtech.onbid.entity.OnBid
import com.smtech.onbid.handler.OnBidDataHandler
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Transactional
class OnBidDataHandlerImpl(@Autowired val onBidDAO: OnBidDAO):OnBidDataHandler {
    override fun saveOnBidEntify(onBid: OnBid): OnBid {
//        TODO("Not yet implemented")
        onBidDAO.saveOnBid(onBid)
        return onBid
    }
}