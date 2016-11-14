package fr.enssat.regnaultnantel.geoquest.model;

public class Beacon {

    private Hint hint;
    private boolean reached = false;

    public Hint getHint() {
        return hint;
    }

    public void setHint(Hint hint) {
        this.hint = hint;
    }

    public boolean isReached() {
        return reached;
    }

    public void setReached(boolean reached) {
        this.reached = reached;
    }
}
