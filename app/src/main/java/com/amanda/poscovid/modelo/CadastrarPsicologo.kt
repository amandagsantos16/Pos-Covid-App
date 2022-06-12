package com.amanda.poscovid.modelo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
public class CadastrarPsicologo {
    var nome: String? = String()
    var dataNascimento: String? = String()
    var crp: String? = String()
    var resumo: String? = String()
    var especializacoes: String? = String()
}