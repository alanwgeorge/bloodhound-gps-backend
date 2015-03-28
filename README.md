# bloodhound-gps-backend

Backend for:
* https://github.com/alanwgeorge/bloodhound-gps
* https://github.com/alanwgeorge/geofence-poc

## UI
### Get Device List
http://hostname:port/blood/device/list

(note: devices have to be manually inserted into the device table for the moment, see db create script for an example) 

### Device Location History
http://hostname:port/blood/device/{id}/{pageSize}/{page}

Devices are identified by the Google Cloud Messaging (GCM) Id from the device.

## Restful Services

### Update Device Location
http://hostname:port/blood/location/update

Request JSON Example:

`{"deviceId":"APA91bFZKhfx3Qzysjs3kjfksdfdZbvKmYLwfHczGi2EceGYzaK0wXFK6GEjSdgHFgTTCYQzIHPHAFiNdJ1E6ObEDxgUmhcuYJZzZCxaqKuJnh7d51CMPb_D8r8FFXXBrhTvebACEnKteGEPU-kzKP8CnPD37GLxuGHqPlwHVesELle5gXjzABL4fA","accuracy":929.0,"latitude":37.4594623,"longitude":-122.2151411}`

Devices are identified by the Google Cloud Messaging (GCM) Id from the device.

### GeoFence

#### New GeoFence
http://hostname:port/blood/geofence/create

JSON Request Example:

`{"createTime":1427584095352,"deviceId":"APA91bfdfsfdju1PAoCiUDKzbReI0lqCkkTmazF322uPMWijXh-4GeQGUVvzgNstaH9loYNW96Ca5ekdkjffdD2ITVBeQIQJSolcGov39H1Df54LdkSYwgysjLOHpYA1Zz5TEAVliui9ZRtZtyd8xNWk973OFluFtvO2pQ","latitude":37.759459,"longitude":-122.4150939}`

JSON Response Example:

`{"id":1,"deviceId":"APA91bfdfsfdju1PAoCiUDKzbReI0lqCkkTmazF322uPMWijXh-4GeQGUVvzgNstaH9loYNW96Ca5ekdkjffdD2ITVBeQIQJSolcGov39H1Df54LdkSYwgysjLOHpYA1Zz5TEAVliui9ZRtZtyd8xNWk973OFluFtvO2pQ","latitude":37.75944,"longitude":-122.41512,"radius":250,"createTime":1427584345394,"enterTime":null,"dwellTime":null,"exitTime":null,"version":0}`

#### GeoFence Update Enter Time
http://hostname:port/blood/geofence/updateEnterTime

JSON Request Example:

`{"dateTime":14275843493455,"id":1}`

JSON Response Example:

`{"id":1,"deviceId":"APA91bGwLYMju1PAoCiUDKzbReI0lqCkkTmazF322uPMWijXh-4GeQGUVvzgNstaH9loYNW96Ca5eY24pD2ITVBeQIQJSolcGov39H1Df54LdkSYwgysjLOHpYA1Zz5TEAVliui9ZRtZtyd8xNWk973OFluFtvO2pQ","latitude":37.75944,"longitude":-122.41512,"radius":250,"createTime":1427584345000,"enterTime":1427584349000,"dwellTime":null,"exitTime":null,"version":1}`

#### GeoFence Update Dwell Time
http://hostname:port/blood/geofence/updateDwellTime

JSON Request Example:

`{"dateTime":1427584410255,"id":1}`

JSON Response Example:

`{"id":1,"deviceId":"APA91bGwLYMju1PAoCiUDKzbReI0lqCkkTmazF322uPMWijXh-4GeQGUVvzgNstaH9loYNW96Ca5eY24pD2ITVBeQIQJSolcGov39H1Df54LdkSYwgysjLOHpYA1Zz5TEAVliui9ZRtZtyd8xNWk973OFluFtvO2pQ","latitude":37.75944,"longitude":-122.41512,"radius":250,"createTime":1427584345000,"enterTime":1427584349000,"dwellTime":1427584410000,"exitTime":null,"version":2}`

#### GeoFence Update Exit Time
http://hostname:port/blood/geofence/updateExitTime

JSON Request Example:

`{"dateTime":1427590010255,"id":1}`

JSON Response Example:

`{"id":1,"deviceId":"APA91bGwLYMju1PAoCiUDKzbReI0lqCkkTmazF322uPMWijXh-4GeQGUVvzgNstaH9loYNW96Ca5eY24pD2ITVBeQIQJSolcGov39H1Df54LdkSYwgysjLOHpYA1Zz5TEAVliui9ZRtZtyd8xNWk973OFluFtvO2pQ","latitude":37.75944,"longitude":-122.41512,"radius":250,"createTime":1427584345000,"enterTime":1427584349000,"dwellTime":1427584410000,"exitTime":1427590010000,"version":3}`
