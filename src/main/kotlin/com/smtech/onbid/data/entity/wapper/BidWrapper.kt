package com.smtech.onbid.data.entity.wapper

import com.smtech.onbid.data.entity.OnBid
import org.springframework.data.domain.Page

data class BidWrapper(val count: Long, val onbid: Page<OnBid>?)
