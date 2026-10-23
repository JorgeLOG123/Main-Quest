package arg.jorge.mainquest.session;

import arg.jorge.mainquest.domain.ValidationUtils;

public class InfoSession {

    private String email;
    private Integer puntos;




    public InfoSession(String email){
        this.email = email;
        this.puntos = 0;
    }

    public boolean isValidEmail() {
        return !ValidationUtils.isEmpty(this.email);
    }



    public String getEmail() {
        return email;
    }

    public Integer getPuntos() {
        return puntos;
    }
}
