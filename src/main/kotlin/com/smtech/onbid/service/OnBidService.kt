package com.smtech.onbid.service

import com.smtech.onbid.data.dto.OnBidDTO
import com.smtech.onbid.entity.OnBid
import com.smtech.onbid.entity.OnBidDays

interface OnBidService {
    fun saveOnBid(onBid: OnBidDTO, file: ByteArray?, additionalFiles: List<ByteArray>?):  OnBid

}