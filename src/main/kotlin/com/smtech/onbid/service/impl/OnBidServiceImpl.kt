package com.smtech.onbid.service.impl

import com.smtech.onbid.data.dto.OnBidDTO
import com.smtech.onbid.entity.OnBid
import com.smtech.onbid.entity.OnBidFile
import com.smtech.onbid.handler.OnBidDataHandler
import com.smtech.onbid.service.OnBidService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class OnBidServiceImpl(@Autowired val onBidHandler: OnBidDataHandler): OnBidService{

    @Transactional
    override fun saveOnBid(onBidDTO: OnBidDTO, fileBytes: MultipartFile?, additionalFilesBytes: List<MultipartFile>?): OnBid {
        //onBid.regdate = LocalDateTime.now() // Set the current date/time

        println(" saveOnBid =============== 1")
        val onbid  = OnBid( addr1 = onBidDTO.addr1
                          , addr2 = onBidDTO.addr2
                          , bankruptcyName = onBidDTO.bankruptcyName
                          , bankruptcyphone = onBidDTO.bankruptcyPhone)

        val mainFile = fileBytes?.let {
            OnBidFile(
                fileName = it.originalFilename ?: "main_file",
                fileSize = it.size,
                file     = it.bytes,
                onBidFiles    = onbid
            )
        }
        

        if (mainFile != null) {
            onbid.onBidFiles?.add(mainFile)
        }

        additionalFilesBytes?.forEach {  additionalFile ->
            val onBidFile = OnBidFile(
                fileName = additionalFile.originalFilename ?: "additional_file_${additionalFilesBytes.indexOf(additionalFile)}",
                fileSize = additionalFile.size,
                file = additionalFile.bytes,
                onBidFiles = onbid
            )
            onbid.onBidFiles?.add(onBidFile)
        }
//        onBidRepository.save(onBid)
        return onBidHandler.saveOnBidEntity(onbid)
    }

    override fun saveOnBid(onBidDTO: OnBidDTO, file: File?, additionalFiles: List<File>?): OnBid {
        println(" saveOnBid =============== 2")
        //onBid.regdate = LocalDateTime.now() // Set the current date/time
        val onbid  = OnBid(addr1 = onBidDTO.addr1,addr2 = onBidDTO.addr2, bankruptcyName = onBidDTO.bankruptcyName, bankruptcyphone = onBidDTO.bankruptcyPhone)
        return onBidHandler.saveOnBidEntity(onbid)
    }
}

