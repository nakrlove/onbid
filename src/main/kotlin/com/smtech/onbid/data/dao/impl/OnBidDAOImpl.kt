package com.smtech.onbid.data.dao.impl

import com.smtech.onbid.data.dao.OnBidDAO
import com.smtech.onbid.data.repository.OnBidRepository
import com.smtech.onbid.entity.OnBid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OnBidDAOImpl(@Autowired val onbidDRepository: OnBidRepository):OnBidDAO {
    val  LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun saveOnBid(onBid: OnBid): OnBid {
//        return OnBid();
        onbidDRepository.save(onBid)
        return onBid
    }
}