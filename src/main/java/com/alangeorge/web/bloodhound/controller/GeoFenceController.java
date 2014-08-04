package com.alangeorge.web.bloodhound.controller;

import com.alangeorge.web.bloodhound.model.GeoFence;
import com.alangeorge.web.bloodhound.model.dao.GeoFenceDao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@SuppressWarnings("UnusedDeclaration")
@Controller()
@RequestMapping(value = "geofence")
public class GeoFenceController {
    private static final Logger log = Logger.getLogger(GeoFenceController.class);
    private static final int DEFAULT_RADIUS = 250; //meters

    @Autowired
    private GeoFenceDao geoFenceDao;

    /**
     * Json request for new geofence from device.  Takes current position and returns
     * a new geofence.  This most likely is call in response to the device exiting a
     * currently monitored geofence.
     * <p/>
     * To test from command line:<br/>
     * {@code
     * curl -H "Content-Type: application/json" -d '{"deviceId":"foobardeviceid","latitude":37.00,"longitude":-122.00, "createTime":1407169928405}' http://localhost:8080/blood/geofence/create
     * }
     * <p/>
     * To test from javascript:<br/>
     * {@code
     * $.ajax({
     * type: "POST",
     *       url: "geofence/create",
     *       data: JSON.stringify({ deviceId: foobarDeviceId, latitude: 37.0, longitude: -122.0, createTime: 1407169928405}),
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
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public @ResponseBody GeoFence create(@RequestBody Position currentPosition) {

        GeoFence geoFence = new GeoFence();
        geoFence.setDeviceId(currentPosition.getDeviceId());
        geoFence.setLatitude(currentPosition.getLatitude());
        geoFence.setLongitude(currentPosition.getLongitude());
        geoFence.setCreateTime(currentPosition.getCreateTime());
        geoFence.setRadius(DEFAULT_RADIUS);

        geoFenceDao.createGeoFence(geoFence);

        return geoFence;
    }

    /**
     * Json request for updating geofence exit time from device.
     *
     * <p/>
     * To test from command line:<br/>
     * {@code
     * curl -H "Content-Type: application/json" -d '{"id":1, "dateTime":1407169928405}' http://localhost:8080/blood/geofence/updateExitTime
     * }
     * <p/>
     * To test from javascript:<br/>
     * {@code
     * $.ajax({
     * type: "POST",
     *       url: "geofence/updateExitTime",
     *       data: JSON.stringify({ id: 1, dateTime: 1407169928405}),
     *  contentType: 'application/json',
     *  success: function(data) {
     *       if(data.status == 'OK') alert('Success');
     *       else alert('Failed : ' + data.status + ', ' + data.errorMessage);
     *  }
     *  });
     * }
     *
     * @param updateDateTimeRequest device current position
     * @return new geofence for the device to monitor
     */
    @SuppressWarnings("JavaDoc")
    @RequestMapping(value = "updateExitTime", method = RequestMethod.POST)
    public @ResponseBody GeoFence updateExitTime(@RequestBody UpdateDateTimeRequest updateDateTimeRequest) {
        GeoFence geoFence = geoFenceDao.findById(updateDateTimeRequest.getId());

        log.debug("updating exit time : " + updateDateTimeRequest.getDateTime().getTime());

        geoFence.setExitTime(updateDateTimeRequest.getDateTime());

        return geoFenceDao.update(geoFence);
    }

    /**
     * Json request for updating geofence enter time from device.
     *
     * <p/>
     * To test from command line:<br/>
     * {@code
     * curl -H "Content-Type: application/json" -d '{"id":1, "dateTime":1407169928405}' http://localhost:8080/blood/geofence/updateEnterTime
     * }
     * <p/>
     * To test from javascript:<br/>
     * {@code
     * $.ajax({
     * type: "POST",
     *       url: "geofence/updateEnterTime",
     *       data: JSON.stringify({ id: 1, dateTime: 1407169928405}),
     *  contentType: 'application/json',
     *  success: function(data) {
     *       if(data.status == 'OK') alert('Success');
     *       else alert('Failed : ' + data.status + ', ' + data.errorMessage);
     *  }
     *  });
     * }
     *
     * @param updateDateTimeRequest device current position
     * @return new geofence for the device to monitor
     */
    @SuppressWarnings("JavaDoc")
    @RequestMapping(value = "updateEnterTime", method = RequestMethod.POST)
    public @ResponseBody GeoFence updateEnterTime(@RequestBody UpdateDateTimeRequest updateDateTimeRequest) {
        GeoFence geoFence = geoFenceDao.findById(updateDateTimeRequest.getId());

        log.debug("updating enter time : " + updateDateTimeRequest.getDateTime().getTime());

        geoFence.setEnterTime(updateDateTimeRequest.getDateTime());

        GeoFence result = geoFenceDao.update(geoFence);

        result = geoFenceDao.findById(result.getId());

        log.debug("enter time after update : " + result.getEnterTime().getTime());

        return result;
    }

    /**
     * Json request for updating geofence dwell time from device.
     *
     * <p/>
     * To test from command line:<br/>
     * {@code
     * curl -H "Content-Type: application/json" -d '{"id":1, "dateTime":1407169928405}' http://localhost:8080/blood/geofence/updateDwellTime
     * }
     * <p/>
     * To test from javascript:<br/>
     * {@code
     * $.ajax({
     * type: "POST",
     *       url: "geofence/updateDwellTime",
     *       data: JSON.stringify({ id: 1, dateTime: 1407169928405}),
     *  contentType: 'application/json',
     *  success: function(data) {
     *       if(data.status == 'OK') alert('Success');
     *       else alert('Failed : ' + data.status + ', ' + data.errorMessage);
     *  }
     *  });
     * }
     *
     * @param updateDateTimeRequest device current position
     * @return new geofence for the device to monitor
     */
    @SuppressWarnings("JavaDoc")
    @RequestMapping(value = "updateDwellTime", method = RequestMethod.POST)
    public @ResponseBody GeoFence updateDwellTime(@RequestBody UpdateDateTimeRequest updateDateTimeRequest) {
        GeoFence geoFence = geoFenceDao.findById(updateDateTimeRequest.getId());

        log.debug("updating dwell time : " + updateDateTimeRequest.getDateTime().getTime());

        geoFence.setDwellTime(updateDateTimeRequest.getDateTime());

        GeoFence result = geoFenceDao.update(geoFence);

        result = geoFenceDao.findById(result.getId());

        log.debug("dwell time after update : " + result.getEnterTime().getTime());

        return result;
    }

    public static class UpdateDateTimeRequest {
        private long id;
        private Date dateTime;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Date getDateTime() {
            return dateTime;
        }

        public void setDateTime(Date dateTime) {
            this.dateTime = dateTime;
        }

        @Override
        public String toString() {
            return "UpdateDateTimeRequest{" +
                    "id=" + id +
                    ", dateTime=" + dateTime +
                    '}';
        }
    }
}
