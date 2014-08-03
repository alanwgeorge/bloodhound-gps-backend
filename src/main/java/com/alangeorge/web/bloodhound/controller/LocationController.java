package com.alangeorge.web.bloodhound.controller;

import com.alangeorge.web.bloodhound.model.Location;
import com.alangeorge.web.bloodhound.model.dao.LocationDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("UnusedDeclaration")
@Controller()
@RequestMapping(value = "location")
public class LocationController {

    @Autowired
    private LocationDao locationDao;
    /**
     * Json request for new geofence from device.  Takes current position and returns
     * a new geofence.  This most likely is call in response to the device exiting a
     * currently monitored geofence.
     * <p/>
     * To test from command line:<br/>
     * {@code
     * curl -H "Content-Type: application/json" -d '{"deviceId":"foobardeviceid","latitude":37.00,"longitude":-122.00, "accuracy":10}' http://localhost:8080/blood/location/update
     * }
     * <p/>
     * To test from javascript:<br/>
     * {@code
     * $.ajax({
     * type: "POST",
     *       url: "location/update",
     *       data: JSON.stringify({ deviceId: foobarDeviceId, latitude: 37.0, longitude: -122.0, accuracy: 10}),
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
    public @ResponseBody Map<String, String> update(@RequestBody Position currentPosition) {

        Location location = new Location();
        location.setDeviceId(currentPosition.getDeviceId());
        location.setLatitude(currentPosition.getLatitude());
        location.setLongitude(currentPosition.getLongitude());
        location.setAccuracy(currentPosition.getAccuracy());

        location = locationDao.createLocation(location);

        Map<String, String> result = new HashMap<String, String>();

        if (location != null) {
            result.put("result", "success");
        } else {
            result.put("result", "failed");
        }

        return result;
    }
}
