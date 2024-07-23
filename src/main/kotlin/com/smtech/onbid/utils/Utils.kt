package com.smtech.onbid.utils

import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException

class Utils {

    companion object {
        @Throws(IOException::class)
        public fun saveFile(file: MultipartFile, uploadDir: File): File {
            val filePath = File(uploadDir, file.originalFilename ?: file.name)
            file.transferTo(filePath)
            return filePath
        }
    }
}