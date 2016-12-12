package fr.enssat.regnaultnantel.geoquest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.enssat.regnaultnantel.geoquest.model.geojson.Feature;
import fr.enssat.regnaultnantel.geoquest.model.geojson.FeatureCollection;
import fr.enssat.regnaultnantel.geoquest.model.geojson.Point;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class ParserTest {

    @Test
    public void testUnmarshallValidGeoJSON() throws Exception {
        try {
            InputStream source = getClass().getResourceAsStream("/example1.json");

            FeatureCollection features = new ObjectMapper().readValue(source, FeatureCollection.class);
            Assert.assertEquals(features.getFeatures().size(), 1);

            Feature first = features.getFeatures().get(0);
            Assert.assertNotNull(first.getProperties());
            Assert.assertEquals(first.getProperties().get("name"), "Stade de Bel Air");
            Assert.assertEquals(first.getProperties().get("indice"), "Un espace sportif après une longue montée depuis le centre de Lannion !");

            Assert.assertTrue(first.getGeometry() instanceof Point);
            Point point = (Point) first.getGeometry();
            Assert.assertEquals(point.getCoordinates().getLatitude(), 48.72391072616036, 0.000001);
            Assert.assertEquals(point.getCoordinates().getLongitude(), -3.4769153594970703, 0.000001);

        } catch (Exception e) {
            // Should not happen
            e.printStackTrace();
            Assert.fail();
        }
    }

    /**
     * Valid JSON but not following GeoJson specification
     */
    @Test
    public void testUnmarshallUnvalidGeoJson() throws Exception {
        //TODO: Add test
    }


    /**
     * Unvalid JSON format
     */
    @Test
    public void testUnmarshallUnvalidJson() {
        //TODO: Add test
    }
}