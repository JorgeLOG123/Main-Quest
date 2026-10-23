package arg.jorge.mainquest.exceptions;

public class MultipleJugadoresFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final String ERR_MSG = "Se detectó más de un jugador registrado con el email %s";

    public MultipleJugadoresFoundException(String email) {
        super(String.format(ERR_MSG, email));
    }
}