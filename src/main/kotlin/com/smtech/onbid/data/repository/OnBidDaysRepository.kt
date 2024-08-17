package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.OnBidDays
import org.springframework.data.jpa.repository.JpaRepository

interface OnBidDaysRepository : JpaRepository<OnBidDays, Int>
