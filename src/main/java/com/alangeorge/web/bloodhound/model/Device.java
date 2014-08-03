package com.alangeorge.web.bloodhound.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@SuppressWarnings("UnusedDeclaration")
@Entity
@Table(name = "device")
@NamedQueries({
        @NamedQuery(name="Device.findAll", query = "select d from Device d"),
        @NamedQuery(name="Device.findById", query = "select d from Device d where deviceId = :deviceId")
})
public class Device {
    @Id // @Id indicates that this it a unique primary key
    @Column(name = "device_id", length = 255, unique = false, nullable = false)
    private String deviceId;

    @Column(name = "name", length = 255, unique = false, nullable = false)
    private String name;

    @OneToMany(mappedBy = "deviceId", fetch = FetchType.EAGER)
    private Collection<Location> locations;

    @Version
    private int version;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Collection<Location> getLocations() {
        return locations;
    }

    public void setLocations(Collection<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId='" + deviceId + '\'' +
                ", name='" + name + '\'' +
                ", version=" + version +
                '}';
    }
}
