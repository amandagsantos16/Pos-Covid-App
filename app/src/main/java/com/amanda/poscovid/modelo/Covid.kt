package com.amanda.poscovid.modelo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
open class Covid : Serializable {
    var cases: String? = String()
    var deaths: String? = String()
    var suspects: String? = String()
    var refuses: String? = String()
}