package com.smtech.onbid.data.repository

import com.smtech.onbid.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post,Long> {


//    /**
//     * 주소검색
//     */
//    @Query("""
//         SELECT p.id
//             , p.zipcode
//             , p.sido
//             , p.sido_eng
//             , p.sigungu
//             , p.sigungu_eng
//             , p.eubmyeon
//             , p.eubmyeon_eng
//             , p.dolomyeong_code
//             , p.dolomyeong
//             , p.dolomyeong_eng
//             , p.jiha
//             , p.geonmulbeonho_bonbeon
//             , p.geonmulbeonho_bubeon
//             , p.geonmulgwanlibeonho
//             , p.dalyang_baedalcheomyeong
//             , p.sigunguyong_geonmulmyeong
//             , p.beobjeongdong_code
//             , p.beobjeongdong
//             , p.limyeong
//             , p.haengjeongdongmyeong
//             , p.san
//             , p.jibeonbonbeon
//             , p.eubmyeondong_illyeonbeonho
//             , p.jibeonbubeon
//             , p.gu_upyeonbeonho
//             , p.upyeonbeonhoillyeonbeonho
//         FROM  post p
//         WHERE p.sigungu             LIKE %:sigungu%
//            OR p.dolomyeong          LIKE :dolomyeong%
//            OR p.beobjeongdong       LIKE :beobjeongdong%
//            limit :pageNum  , 10
//    """, nativeQuery = true
//    )
//    fun findBySigunguLikeDolomyeongLikeBeobjeongdongmyeong(
//        @Param(value = "sigungu") sigungu: String,
//        @Param(value = "dolomyeong") dolomyeong: String,
//        @Param(value = "beobjeongdong") beobjeongdong: String,
//        @Param(value = "pageNum") pageNum: Long
//    ): List<Post>


    /**
     * 주소검색
     */
    @Query(""" 
          SELECT p.id
                 , CONCAT_WS
                        (' ', p.sido, p.sigungu, p.dolomyeong,   
                          CASE WHEN p.geonmulbeonho_bonbeon IS NOT NULL 
                                AND p.geonmulbeonho_bubeon = '0' 
                               THEN p.geonmulbeonho_bonbeon
                               WHEN p.geonmulbeonho_bonbeon IS NOT NULL 
                                AND p.geonmulbeonho_bubeon IS NOT NULL 
                                AND p.geonmulbeonho_bonbeon <> '' 
                                AND p.geonmulbeonho_bubeon <> '' 
                               THEN CONCAT(p.geonmulbeonho_bonbeon, '-', p.geonmulbeonho_bubeon) 
                               ELSE NULL 
                           END
                      ,   CASE WHEN  p.beobjeongdong IS NOT NULL 
                                AND p.sigunguyong_geonmulmyeong IS NOT NULL 
                                AND p.beobjeongdong <> '' 
                                AND  p.sigunguyong_geonmulmyeong <> ''
                               THEN  CONCAT('(', p.beobjeongdong, ',', p.sigunguyong_geonmulmyeong, ')') 
                               ELSE NULL
                           END    
                        ) as addr1                
                , CONCAT_WS
                         (' ', p.sido, p.sigungu, p.beobjeongdong,      
                            CASE WHEN p.jibeonbubeon IS NOT NULL AND p.jibeonbubeon = '0'
								 THEN p.jibeonbonbeon
                                 WHEN p.jibeonbonbeon IS NOT NULL 
                                  AND p.jibeonbubeon IS NOT NULL 
                                  AND p.jibeonbonbeon <> '' 
                                  AND p.jibeonbubeon <> '' 
                                 THEN CONCAT(p.jibeonbonbeon, '-', p.jibeonbubeon) 
                                 ELSE NULL 
                             END
                         ) as addr2       
            FROM post p     
           WHERE ( 
                    CONCAT_WS(' ', p.sigungu, p.beobjeongdong, CONCAT(p.jibeonbonbeon, '-', p.jibeonbubeon)) LIKE %:search% 
                 or CONCAT_WS(' ', p.sigungu, p.dolomyeong, CONCAT(p.jibeonbonbeon, '-', p.jibeonbubeon))     LIKE %:search%
                 )
            limit :totalPage  , 10
    """, nativeQuery = true
    )
    fun findBySigunguLikeDolomyeongLikeBeobjeongdongmyeong(
        @Param(value = "search") search: String,
        @Param(value = "totalPage") totalPage: Long
    ): List<Post>


    @Query(""" 
         SELECT count(*) 
         FROM  post p
        WHERE ( 
                    CONCAT_WS(' ', p.sigungu, p.beobjeongdong, CONCAT(p.jibeonbonbeon, '-', p.jibeonbubeon)) LIKE %:search% 
                 or CONCAT_WS(' ', p.sigungu, p.dolomyeong, CONCAT(p.jibeonbonbeon, '-', p.jibeonbubeon))     LIKE %:search%
                 )
    """, nativeQuery = true
    )
    fun findByCount(  @Param(value = "search") search: String): Long

}