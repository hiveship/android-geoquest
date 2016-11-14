package fr.enssat.regnaultnantel.geoquest.model;

public class Hint {

    private String message;
    private String imageName; // TODO: A voir comment sera géré l'affichage de l'image

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
