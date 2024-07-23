package com.smtech.onbid.service

import com.smtech.onbid.data.dto.OnBidDTO
import com.smtech.onbid.entity.OnBid
import org.springframework.web.multipart.MultipartFile
import java.io.File

interface OnBidService {
    fun saveOnBid(onBid: OnBidDTO, file: MultipartFile?, additionalFiles: List<MultipartFile>?):  OnBid
    fun saveOnBid(onBid: OnBidDTO, file: File?, additionalFiles: List<File>?):  OnBid

}