package fr.enssat.regnaultnantel.geoquest.model.geojson;

public class Point extends GeoJsonObject {

    private Coordinates coordinates;

    public Point() {
    }

    public Point(double longitude, double latitude) {
        coordinates = new Coordinates(longitude, latitude);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Point{" + "coordinates=" + coordinates + "} " + super.toString();
    }
}
