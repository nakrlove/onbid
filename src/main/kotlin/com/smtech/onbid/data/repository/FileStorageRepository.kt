package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.OnBidFile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FileStorageRepository : JpaRepository<OnBidFile, Int> {
   override fun findById(id: Int): Optional<OnBidFile>
}