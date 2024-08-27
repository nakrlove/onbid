package com.smtech.onbid.data.dao

import com.smtech.onbid.data.entity.Category
import java.util.*

interface CategoryDAO {
    fun cateGoryList(content:String?): List<Category>
    fun cateGroySave(category: Category): Category?
    fun cateGroyUpdate(category: Category): Category?
    fun cateGroyDelete(idx:Int):Boolean
}