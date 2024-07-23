package com.smtech.onbid.handler

import com.smtech.onbid.data.dao.OnBidDAO
import com.smtech.onbid.entity.OnBid

interface OnBidDataHandler {
    fun saveOnBidEntify(onBid: OnBid):OnBid
}