package com.smtech.onbid.data.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "category_tb")
data class Category(@Id
                    @GeneratedValue(strategy = GenerationType.IDENTITY)
                    @Column(name = "IDX")
                    val idx: Int? = null,
                    @Column(name = "CONTENT", columnDefinition = "VARCHAR")
                    var content: String?,

                    @Column(name = "USER", columnDefinition = "VARCHAR")
                    var user: String? = null,

//                    @Column(name = "REGDATE", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
                    @Column(name = "REGDATE")
                    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 원하는 형식으로 변경
                    var regdate: String?,

                    @Column(name = "BIDIDX")
                    var bididx: Int? ,
    ){
    // 기본 생성자
    constructor() : this(idx =null, content = null, user = null, regdate = LocalDateTime.now().toString(),bididx = null)
    constructor(content: String?, user: String?) : this(idx =null, content = content, user = user, regdate = LocalDateTime.now().toString(),bididx = null)
}
