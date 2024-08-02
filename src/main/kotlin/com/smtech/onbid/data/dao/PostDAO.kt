package com.smtech.onbid.data.dao

import com.smtech.onbid.entity.Post


interface PostDAO {

    fun findSigunguLikeDolomyongLikeBeobjeongdongmyeong(addr1: String,totalPage: Long): List<Post>
    fun findByCount(addr1: String): Long
}