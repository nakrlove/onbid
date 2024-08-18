package com.smtech.onbid.service

import com.smtech.onbid.data.entity.OnBidFile
import java.io.InputStream
import java.util.*

interface FileStorageService {
    fun getFileById(id: Int): Optional<OnBidFile>
    fun getFileInputStream(id: Int): InputStream?
}