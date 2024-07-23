package com.smtech.onbid.data.repository

import com.smtech.onbid.entity.OnBid
import com.smtech.onbid.entity.OnBidDays
import org.springframework.data.jpa.repository.JpaRepository

interface OnBidDaysRepository : JpaRepository<OnBidDays, Int>
