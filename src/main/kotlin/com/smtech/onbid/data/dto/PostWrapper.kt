package com.smtech.onbid.data.dto

import com.smtech.onbid.data.entity.Post


data class PostWrapper(var count: Long = 0 , var post: List<Post>? = null)