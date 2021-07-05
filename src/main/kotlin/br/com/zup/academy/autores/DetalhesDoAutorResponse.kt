package br.com.zup.academy.autores

class DetalhesDoAutorResponse(autor: Autor) {
    val nome: String? = autor.nome
    val email: String? = autor.email
    val descricao: String? = autor.descricao
}
