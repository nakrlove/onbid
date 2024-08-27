package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

//@Repository
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
            select  building_mgmt_no
                  , concat(concat_ws(' ',city_kr,country_kr,town_kr,dong,concat(gibun_no,'-',gibun_subno))
                  , ' '
                  , CASE 
				    WHEN building_nm IS NOT NULL AND building_nm <> '' THEN concat('(', building_nm, ')')
				    ELSE ''
                     END) AS addr2 
			      , concat_ws(' ',city_kr,country_kr,road_kr,concat(building_no,'-',building_subno)
                  ,' ' 
                  , CASE 
				    WHEN building_nm IS NOT NULL AND building_nm <> '' THEN concat('(',legal_nm_kr,',', building_nm, ')')
                    ELSE ''
				     END ) AS addr1
         from zipcode
		where concat_ws(' ',city_kr,country_kr,legal_nm_kr,concat(gibun_no,'-',gibun_subno)) like %:search% 
        limit :totalPage  , 10
            
    """, nativeQuery = true
    )
    fun findBySigunguLikeDolomyeongLikeBeobjeongdongmyeong(
        @Param(value = "search") search: String,
        @Param(value = "totalPage") totalPage: Long
    ): List<Post>

    //         SELECT count(*)
//         FROM  post p
//        WHERE (
//                    CONCAT_WS(' ', p.sigungu, p.beobjeongdong, CONCAT(p.jibeonbonbeon, '-', p.jibeonbubeon)) LIKE %:search%
//                 or CONCAT_WS(' ', p.sigungu, p.dolomyeong, CONCAT(p.jibeonbonbeon, '-', p.jibeonbubeon))     LIKE %:search%
//                 )
//

    @Query(""" 
        select count(*)
         from zipcode
		where concat_ws(' ',city_kr,country_kr,legal_nm_kr,concat(gibun_no,'-',gibun_subno)) like %:search%         
                 
    """, nativeQuery = true
    )
    fun findByCount(  @Param(value = "search") search: String): Long

}