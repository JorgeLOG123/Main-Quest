package arg.jorge.mainquest.domain;

public class ValidationUtils {

    public static boolean isEmpty(String texto){
        return texto == null || texto.isBlank();
    }

    public static boolean ISNegative(long numero){
        return numero < 0;
    }

    public static boolean isValidID(Long id){
        return id != null && id.longValue() > 0;
    }


}
