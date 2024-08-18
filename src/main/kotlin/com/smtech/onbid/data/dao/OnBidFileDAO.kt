package com.smtech.onbid.data.dao

import com.smtech.onbid.data.entity.OnBidFile
import java.util.*

interface OnBidFileDAO {
    fun findById(id: Int): Optional<OnBidFile>
}