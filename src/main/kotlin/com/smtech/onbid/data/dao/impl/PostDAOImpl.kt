package com.smtech.onbid.data.dao.impl

import com.smtech.onbid.data.dao.PostDAO
import com.smtech.onbid.data.repository.PostRepository
import com.smtech.onbid.entity.Post
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostDAOImpl(@Autowired val postRepository: PostRepository): PostDAO {

    override fun findSigunguLikeDolomyongLikeBeobjeongdongmyeong(search: String,totalPage: Long): List<Post> {
        return postRepository.findBySigunguLikeDolomyeongLikeBeobjeongdongmyeong(search,totalPage)
    }

    override fun findByCount(addr1: String): Long = postRepository.findByCount(addr1)

}