package com.smtech.onbid.data.dao

import com.smtech.onbid.entity.OnBid

interface OnBidDAO {
    fun saveOnBid(onBid: OnBid): OnBid
}