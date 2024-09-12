package com.smtech.onbid.data.repository

import com.smtech.onbid.data.entity.Codes


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CodeRepository: JpaRepository<Codes, Long> {
    /** 코드명 검색 */
//    @Query("SELECT c.idx,c.code,c.scode,c.name FROM Codes c WHERE c.name like :name%")
    fun findByNameContaining(name: String, pageable: Pageable): Page<Codes>
    /** SUB_CODE로 조회*/
    fun findByScode(subcode: String): List<Codes>

    @Query("SELECT COUNT(c) FROM Codes c WHERE c.name like :name%")
    fun findNameCountQuery(name: String):Long

    /** 파일첨부 */
    @Query("""
        WITH duplication AS (
             select code,scode,name 
               from code_tb 
              where scode <> '000'
        )
        select ctb.idx,ctb.code,ctb.scode,ctb.name 
        from code_tb ctb inner join duplication d on d.scode <> ctb.code
        where ctb.scode = '043' /* 파일첨부용도 */
        --  and ctb.code in ('001','002','004','041')
        group by ctb.idx,ctb.code,ctb.scode,ctb.name
    """, nativeQuery = true)
    fun findCodeQuery(): List<Codes>

    /**
     * 코드조회 (지목/면적)
     */
    @Query("""
        WITH duplication AS (
             select code,scode,name 
               from code_tb 
              where scode <> '000'
        )
        select ctb.idx,ctb.code,ctb.scode,ctb.name 
        from code_tb ctb inner join duplication d on d.scode <> ctb.code
        where ctb.scode = :scode
        group by ctb.idx,ctb.code,ctb.scode,ctb.name
        order by ctb.idx asc
    """, nativeQuery = true)
    fun findCodeQuery(@Param(value = "scode") scode: String): List<Codes>

    /** 코드명 검색 */
//    override fun findAll(pageable: Pageable): Page<Codes>

    @Query("SELECT MAX(c.code) FROM Codes c")
    fun findMaxCode(): String?


}