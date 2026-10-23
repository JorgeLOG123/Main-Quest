package arg.jorge.mainquest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AbstractApiTest {
    protected static final String LOCAL_SERVER = "http://localhost";

    @LocalServerPort
    private int puerto;

    protected int puerto() {
        return this.puerto;
    }
}