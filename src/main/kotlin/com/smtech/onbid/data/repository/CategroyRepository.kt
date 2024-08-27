package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.Category
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository

interface CategroyRepository: JpaRepository<Category, Int> {

    fun findByUserAndContent(userId:String,name:String):Optional<Category>
    fun findOptionalByUserAndContent(userId:String,name:String): Optional<Category>

    //    fun findByUserId(userId:String): List<Category>
    fun findByUser(userId:String): List<Category>
}