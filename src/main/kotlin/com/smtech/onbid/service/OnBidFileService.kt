package com.smtech.onbid.service

import com.smtech.onbid.data.dto.OnBidFileDTO
import com.smtech.onbid.data.entity.OnBidFile
import java.io.InputStream
import java.util.*

interface OnBidFileService {
    fun getFileById(id: Int): Optional<OnBidFile>
    fun getFileInputStream(id: Int): InputStream?

    /** 등록된 파일카테고리 조회*/
    fun getFileCategory(bididx: Int): List<OnBidFileDTO>
}