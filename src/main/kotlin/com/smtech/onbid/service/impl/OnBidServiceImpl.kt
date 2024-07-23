package com.smtech.onbid.service.impl

import com.smtech.onbid.data.dto.OnBidDTO
import com.smtech.onbid.entity.OnBid
import com.smtech.onbid.handler.OnBidDataHandler
import com.smtech.onbid.service.OnBidService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OnBidServiceImpl(@Autowired val onBidHandler: OnBidDataHandler): OnBidService{

    override fun saveOnBid(onBidDTO: OnBidDTO, file: ByteArray?, additionalFiles: List<ByteArray>?): OnBid {
        //onBid.regdate = LocalDateTime.now() // Set the current date/time
        val onbid  = OnBid(addr1 = onBidDTO.addr1,addr2 = onBidDTO.addr2, bankruptcyName = onBidDTO.bankruptcyName, bankruptcyphone = onBidDTO.bankruptcyPhone)
        return onBidHandler.saveOnBidEntify(onbid)
    }
}