package fr.enssat.regnaultnantel.geoquest.model.geojson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Feature extends GeoJsonObject {

    @JsonProperty(access = Access.WRITE_ONLY)
    private UUID uuid = UUID.randomUUID();
    @JsonInclude(Include.NON_NULL)
    private Map<String, Object> properties = new HashMap<>();
    @JsonInclude(Include.ALWAYS)
    private GeoJsonObject geometry;

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public GeoJsonObject getGeometry() {
        return geometry;
    }

    public void setGeometry(GeoJsonObject geometry) {
        this.geometry = geometry;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Feature{properties=" + properties + ", geometry=" + geometry + ", uuid='" + uuid + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feature feature = (Feature) o;
        return feature.getUuid().equals(getUuid());
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        result = 31 * result + (geometry != null ? geometry.hashCode() : 0);
        return result;
    }
}
