package arg.jorge.mainquest.controllers.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import arg.jorge.mainquest.controllers.JugadorAPI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import arg.jorge.mainquest.AbstractApiTest;
import arg.jorge.mainquest.domain.Jugador;
import arg.jorge.mainquest.dtos.JugadorDTO;
import arg.jorge.mainquest.services.JugadorServices;

public class JugadorAPIRestTest extends AbstractApiTest {
    private static final String NOMBRE = "jugadorTestApi";
    private static final String EMAIL = "jugador-test-api@test.com";

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JugadorServices servicio;

    @BeforeEach
    void crearJugadorDePrueba() {
        this.servicio.registrar(NOMBRE, "clave123", EMAIL);
    }

    @AfterEach
    void borrarJugadorDePrueba() {
        Jugador jugador = this.servicio.obtenerPorEmail(EMAIL);
        if (jugador != null) {
            this.servicio.eliminar(jugador.getId());
        }
    }

    @Test
    public void listarJugadoresDevuelveElJugadorRegistrado() {
        URI uri = URI.create(LOCAL_SERVER + ":" + puerto() + JugadorAPI.LIST_JUGADORES_URL);

        HttpRequest request = HttpRequest
                .newBuilder(uri)
                .GET()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            assertEquals(200, response.statusCode());

            List<JugadorDTO> jugadores = mapper
                    .readerForListOf(JugadorDTO.class)
                    .readValue(response.body());

            assertFalse(jugadores.isEmpty());
            for (JugadorDTO dto : jugadores) {
                assertNotNull(dto.getNombre());
                assertTrue(dto.getNivel() >= 1);
            }
            assertTrue(jugadores.stream().anyMatch(j -> NOMBRE.equals(j.getNombre())));
        } catch (Exception e) {
            fail("No se pudo llamar a la API: " + e.getMessage());
        }
    }
}