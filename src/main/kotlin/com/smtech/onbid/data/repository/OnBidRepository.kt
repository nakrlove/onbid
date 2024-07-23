package com.smtech.onbid.data.repository

import com.smtech.onbid.data.dto.OnBidDTO
import com.smtech.onbid.entity.OnBid
import org.springframework.data.jpa.repository.JpaRepository

interface OnBidRepository : JpaRepository<OnBid, Int>