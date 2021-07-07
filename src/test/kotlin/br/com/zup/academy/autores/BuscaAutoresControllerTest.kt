package br.com.zup.academy.autores

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaAutoresControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setup() {
        val enderecoResponse = EnderecoResponse("Rua das Laranjeiras", "Rio de Janeiro", "RJ")
        val endereco = Endereco(enderecoResponse, "28", "22240-000")
        autor = Autor("Emerson Bezerra", "emerson.bezerra@zup.com.br", "Participante da OT#5",
            endereco)

        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.delete(autor)
    }

    @Test
    internal fun `Deve retornar os detalhes de um autor`() {

        val response = client.toBlocking()
            .exchange("/autores?email=${autor.email}", DetalhesDoAutorResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.email, response.body()!!.email)
        assertEquals(autor.descricao, response.body()!!.descricao)
    }
}
