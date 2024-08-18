package com.smtech.onbid.data.dao.impl

import com.smtech.onbid.data.dao.OnBidFileDAO
import com.smtech.onbid.data.entity.OnBidFile
import com.smtech.onbid.data.repository.FileStorageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class OnBidFileDAOImpl(@Autowired val fileStorageRepository: FileStorageRepository): OnBidFileDAO {

    override fun findById(id: Int): Optional<OnBidFile>{
        return fileStorageRepository.findById(id)
    }
}