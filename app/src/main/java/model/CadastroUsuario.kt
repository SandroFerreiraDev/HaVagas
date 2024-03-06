package model

data class CadastroUsuario(
    var nomeCompleto: String = "",
    var email: String = "",
    var receberEmails: Boolean = false,
    var telefone: String = "",
    var tipoTelefone: TipoTelefone = TipoTelefone.RESIDENCIAL,
    var telefoneCelular: String = "",
    var sexo: Sexo = Sexo.OUTRO,
    var dataNascimento: String = "",
    var formacao: Formacao = Formacao.FUNDAMENTAL,
    var anoFormatura: Int? = null,
    var anoConclusao: Int? = null,
    var instituicao: String? = null,
    var tituloMonografia: String? = null,
    var orientador: String? = null,
    var vagasInteresse: String = ""
)

enum class TipoTelefone {
    RESIDENCIAL, COMERCIAL
}

enum class Sexo {
    MASCULINO, FEMININO, OUTRO
}

enum class Formacao {
    FUNDAMENTAL, MEDIO, GRADUACAO, ESPECIALIZACAO, MESTRADO, DOUTORADO
}
