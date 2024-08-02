package com.smtech.onbid.handler

import com.smtech.onbid.entity.Post

interface PostDataHandler {
    fun findPosts(addr1: String,totalPage: Long): List<Post>
    fun findByCount(addr1: String): Long
}