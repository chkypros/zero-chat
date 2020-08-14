package com.github.chkypros.zerochat.entities

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class User(@JsonProperty("identifier") val identifier: String, @JsonProperty("ipAddr") val ipAddr: String) : Comparable<User>, Serializable {

    override fun compareTo(other: User): Int {
        return identifier.compareTo(other.identifier, true)
    }

}
