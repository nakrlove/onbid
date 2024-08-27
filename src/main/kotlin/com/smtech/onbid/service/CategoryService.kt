package com.smtech.onbid.service

import com.smtech.onbid.data.entity.Category
import java.util.*


interface CategoryService {
    fun cateGoryList(name:String?): List<Category>
    fun cateGroySave(category: Category):Category?
    fun cateGroyUpdate(category: Category):Category?
    fun cateGroyDelete(idx:Int):Boolean
}