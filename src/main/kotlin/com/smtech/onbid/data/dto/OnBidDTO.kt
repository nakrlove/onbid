package com.smtech.onbid.data.dto

data class OnBidDTO(

//        @field:NotNull(message = "정확한 주소를 입력해주세요!")
//        @field:Size(min = 1, message = "주소는 최소 1자 이상이어야 합니다.")
        var bididx: Int?
      , var addr1: String?
      , var addr2:String?    /* 상세 주소 */
      , var rd_addr: String? /* 도로명 주소 */

//       @field:NotNull(message = "데이터가 누락되었습니다.")
//        @field:Size(min = 1, message = "데이터는 최소 1자 이상이어야 합니다.")
       , var bruptcy_admin_phone:String? /* 파산관제인전화번호 */

//      , @field:NotNull
       , var bruptcy_admin_name: String?  /* 파산관제인명 */

      , var note: String?           /* 유의사항 주소 */
      , var memo: List<MemoDTO>?    /* 메모 주소 */
      , var renter:String?          /* 임차여부 */
      , var ld_area: String?        /* 토지 */
      , var ld_area_memo: String?   /* 토지면적 메모*/
      , var build_area: String? /* 건축물 */
      , var build_area_memo: String? /* 건축물 메모*/
      , var estateType:String?  /* 부동산종류 */
      , var disposal_type:String?
      , var land_classification: String? = null /* 지목 */
      , var progress_status: String? = null  /* 진행상태 */
      , var onbid_status   : String? = null /* 입찰진행상태 */
      , val debtor         : String? /* 채무자명 */
      , val national_land_planning_use_laws: String?
      , val other_laws     : String?
      , val enforcement_decree: String?
      , val idx: Int? /* 관심종목 */
      , val pnu: String? /* 필지번호 */
      , var page: Int = 0
      , var size: Int = 10
)