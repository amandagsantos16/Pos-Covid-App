package com.amanda.poscovid.modelo

import java.io.Serializable

class Noticia: Serializable {
    var titulo: String = String()
    var subtitulo: String = String()
    var corpo: String = String()
    var url: String = String()
}