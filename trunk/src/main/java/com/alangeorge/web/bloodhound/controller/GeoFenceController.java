package com.alangeorge.web.bloodhound.controller;

import com.alangeorge.web.bloodhound.model.GeoFence;
import com.alangeorge.web.bloodhound.model.dao.GeoFenceDao;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings("UnusedDeclaration")
@Controller()
@RequestMapping(value = "geofence")
public class GeoFenceController {
    private static final int DEFAULT_RADIUS = 500; //meters

    @Autowired
    private GeoFenceDao geoFenceDao;
    /**
     * Json request for new geofence from device.  Takes current position and returns
     * a new geofence.  This most likely is call in response to the device exiting a
     * currently monitored geofence.
     * <p/>
     * To test from command line:<br/>
     * {@code
     * curl -H "Content-Type: application/json" -d '{"deviceId":"foobardeviceid","latitude":37.00,"longitude":-122.00}' http://localhost:8080/blood/geofence/update
     * }
     *<p/>
     * To test from javascript:<br/>
     * {@code
     * $.ajax({
     * type: "POST",
     *       url: "geofence/update",
     *       data: JSON.stringify({ deviceId: foobarDeviceId, latitude: 37.0, longitude: -122.0, }),
     *  contentType: 'application/json',
     *  success: function(data) {
     *       if(data.status == 'OK') alert('Success');
     *       else alert('Failed : ' + data.status + ', ' + data.errorMessage);
     *  }
     *  });
     * }
     *
     * @param currentPosition device current position
     * @return new geofence for the device to monitor
     */
    @SuppressWarnings("JavaDoc")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public @ResponseBody
    GeoFence update(@RequestBody Position currentPosition) {

        GeoFence geoFence = new GeoFence();
        geoFence.setDeviceId(currentPosition.deviceId);
        geoFence.setLatitude(currentPosition.getLatitude());
        geoFence.setLongitude(currentPosition.getLongitude());
        geoFence.setRadius(DEFAULT_RADIUS);

        geoFenceDao.createGeoFence(geoFence);

        return geoFence;
    }

    @SuppressWarnings("UnusedDeclaration")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Position {
        private String deviceId;
        private float latitude;
        private float longitude;


        public Position() {  }

        public Position(float latitude, float longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public float getLatitude() {
            return latitude;
        }

        public void setLatitude(float latitude) {
            this.latitude = latitude;
        }

        public float getLongitude() {
            return longitude;
        }

        public void setLongitude(float longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "deviceId='" + deviceId + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    '}';
        }
    }
}
