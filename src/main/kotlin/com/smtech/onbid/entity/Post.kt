package com.smtech.onbid.entity

import jakarta.persistence.*

//zipcode , sido, sido_eng, sigungu,  sigungu_eng , eubmyeon , eubmyeon_eng , dolomyeong_code , dolomyeong
// , dolomyeong_yeongmun , jihayeobu , geonmulbeonho_bonbeon , geonmulbeonho_bubeon , geonmulgwanlibeonho
// , dalyang_baedalcheomyeong ,  sigunguyong_geonmulmyeong  ,  beobjeongdong_code  ,  limyeong ,  haengjeongdongmyeong
// ,  beobjeongdongmyeong , san_yeobu  ,  jibeonbonbeon  ,  eubmyeondong_illyeonbeonho ,  jibeonbubeon ,  gu_upyeonbeonho
// ,  upyeonbeonhoillyeonbeonho
@Entity
@Table(name = "post")
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int? = null,

//    @Column(name = "zipcode")
//    val zipcode: String? = null,  /* 구역번호 */
//    @Column(name = "sido")
//    val sido: String? = null,  /* 시도 */
//
//    @Column(name = "sido_eng")
//    val sido_eng: String? = null, /* 시도영문 */
//    @Column(name = "sigungu")
//    val sigungu: String? = null,  /* 시군구 */
//    @Column(name = "sigungu_eng")
//    val sigungu_eng: String? = null,  /* 시군구영문 */
//    @Column(name = "eubmyeon")
//    val eubmyeon: String? = null,  /* 읍면 */
//    @Column(name = "eubmyeon_eng")
//    val eubmyeon_eng: String? = null,  /* 읍면영문 */
//    @Column(name = "dolomyeong_code")
//    val dolomyeong_code: String? = null,  /* 도로명코드 */
//    @Column(name = "dolomyeong")
//    val dolomyeong: String? = null,  /* 도로명 */
//    @Column(name = "dolomyeong_eng")
//    val dolomyeong_eng: String? = null,  /* 도로명영문 */
//    @Column(name = "jiha")
//    val jiha: String? = null,  /* 지하여부 */
//    @Column(name = "geonmulbeonho_bonbeon")
//    val geonmulbeonho_bonbeon: String? = null,  /* 건물번호본번 */
//    @Column(name = "geonmulbeonho_bubeon")
//    val geonmulbeonho_bubeon: String? = null,  /* 건물번호부번 */
//    @Column(name = "geonmulgwanlibeonho")
//    val geonmulgwanlibeonho: String? = null,  /* 건물관리번호 */
//    @Column(name = "dalyang_baedalcheomyeong")
//    val dalyang_baedalcheomyeong: String? = null,  /* 다량배달처명 */
//    @Column(name = "sigunguyong_geonmulmyeong")
//    val sigunguyong_geonmulmyeong: String? = null,  /* 시군구용건물명 */
//
//    @Column(name = "beobjeongdong_code")
//    val beobjeongdong_code: String? = null,  /* 법정동코드 */
//    @Column(name = "beobjeongdong")
//    val beobjeongdong: String? = null,  /* 법정동명 */
//    @Column(name = "limyeong")
//    val limyeong: String? = null,  /* 리명 */
//    @Column(name = "haengjeongdongmyeong")
//    val haengjeongdongmyeong: String? = null,  /* 행정동명 */
//    @Column(name = "san")
//    val san: String? = null,  /* 산여부 @*/
//    @Column(name = "jibeonbonbeon")
//    val jibeonbonbeon: String? = null,  /* 지번본번 */
//    @Column(name = "eubmyeondong_illyeonbeonho")
//    val eubmyeondong_illyeonbeonho: String? = null,  /* 읍면동일련번호 */
//    @Column(name = "jibeonbubeon")
//    val jibeonbubeon: String? = null,  /* 지번부번 */
//    @Column(name = "gu_upyeonbeonho")
//    val gu_upyeonbeonho: String? = null,  /* 구 우편번호 */
//    @Column(name = "upyeonbeonhoillyeonbeonho")
//    val upyeonbeonhoillyeonbeonho: String? = null,  /* 우편번호일련번호 */

    @Column(name = "addr1")
    val addr1: String? = null,  /* 우편번호1 */
    @Column(name = "addr2")
    val addr2: String? = null,  /* 우편번호2 */

)


