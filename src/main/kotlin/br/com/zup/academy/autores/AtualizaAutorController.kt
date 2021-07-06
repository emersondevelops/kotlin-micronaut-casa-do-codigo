package br.com.zup.academy.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class AtualizaAutorController(val autorRepository: AutorRepository) {

    @Put("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long, descricao: String): HttpResponse<Any> {

        val possivelAutor = autorRepository.findById(id)
        if (possivelAutor.isEmpty) return HttpResponse.notFound()

        val autor = possivelAutor.get()
        autor.descricao = descricao

        return HttpResponse.ok(DetalhesDoAutorResponse(autor))
    }
}
