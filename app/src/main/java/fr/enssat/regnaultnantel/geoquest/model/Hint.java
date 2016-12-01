package fr.enssat.regnaultnantel.geoquest.model;

public class Hint {

    private String message;
    private String image; // TODO: A voir comment sera géré l'affichage de l'image

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageName() {
        return image;
    }

    public void setImageName(String image) {
        this.image = image;
    }
}
