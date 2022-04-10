package com.amanda.poscovid.modelo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
open class Covid : Serializable {
    var cases: Long? = 0
    var deaths: Long? = 0
    var suspects: Long? = 0
    var refuses: Long? = 0
}