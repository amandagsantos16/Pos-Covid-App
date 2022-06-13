package com.amanda.poscovid.api.modelo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class PostHorario : Serializable{
    var psicologoId : String = String()
    var horarios : List<HorarioDia> = listOf()
}

@JsonIgnoreProperties(ignoreUnknown = true)
class HorarioDia : Serializable{
    var diaDaSemana : Int = 0
    var horarios : List<String> = listOf()
}
