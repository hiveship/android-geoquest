package fr.enssat.regnaultnantel.geoquest.model.geojson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Geometry<T> extends GeoJsonObject {

    private List<T> coordinates = new ArrayList<>(); // Need to keep order

    public Geometry<T> add(T elements) {
        coordinates.add(elements);
        return this;
    }

    public List<T> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LinkedList<T> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Geometry{" + "coordinates=" + coordinates + "} " + super.toString();
    }
}
