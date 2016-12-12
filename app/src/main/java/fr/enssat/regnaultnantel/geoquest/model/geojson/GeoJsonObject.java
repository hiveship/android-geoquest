package fr.enssat.regnaultnantel.geoquest.model.geojson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import java.io.Serializable;

@JsonTypeInfo(property = "type", use = Id.NAME)
@JsonSubTypes({@Type(Feature.class), @Type(FeatureCollection.class), @Type(Point.class)})
@JsonInclude(Include.NON_NULL)
public class GeoJsonObject implements Serializable {

    @Override
    public String toString() {
        return "GeoJsonObject{}";
    }
}
