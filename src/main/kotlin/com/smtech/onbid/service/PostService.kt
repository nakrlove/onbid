package com.smtech.onbid.service

import com.smtech.onbid.entity.Post

interface PostService {
    fun findPosts(addr1: String,totalPage: Long): List<Post>
    fun findByCount(addr1: String): Long
}