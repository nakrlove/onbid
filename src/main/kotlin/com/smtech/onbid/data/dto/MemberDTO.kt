package com.smtech.onbid.data.dto

data class MemberDTO(var name: String? = null, var email: String? = null , var organization: String? = null) {
    override fun toString() =  "{ ${name} : $email : $organization }"
}
