package arg.jorge.mainquest.forms;

import arg.jorge.mainquest.domain.ValidationUtils;
import org.springframework.web.multipart.MultipartFile;

public class ProfileForm extends JugadorAdminForm {

    private Long idPicture;
    private MultipartFile file;

    public boolean tieneImagenCargada() {
        return this.file != null && this.file.getSize() > 0;
    }

    public boolean mostrarImagen() {
        return ValidationUtils.isValidID(this.idPicture);
    }

    public Long getIdPicture() { return idPicture; }
    public void setIdPicture(Long idPicture) { this.idPicture = idPicture; }

    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }
}