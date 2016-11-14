package fr.enssat.regnaultnantel.geoquest.model;

public interface Initinerary {

    void getNextStep();
    void getLastStep();
    void getCurrentStep();

    Hint getCurrentClue();
}
