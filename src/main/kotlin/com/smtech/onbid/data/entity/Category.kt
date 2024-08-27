package com.smtech.onbid.data.entity

import jakarta.persistence.*
import org.springframework.beans.factory.annotation.Value
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

                    @Column(name = "REGDATE", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
                    var regdate: LocalDateTime = LocalDateTime.now(),
    ){
    // 기본 생성자
    constructor() : this(null, null, null, LocalDateTime.now())
}
