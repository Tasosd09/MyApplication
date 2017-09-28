package com.example.tasos.myapplication.model;

import com.estimote.coresdk.recognition.utils.MacAddress;

/**
 * Created by tasos on 16-Sep-17.
 */

public class BeaconDAO {
    private int rssi;
    private String uniqueID;
    private MacAddress macAddress;

    public BeaconDAO(int rssi, String uniqueID, MacAddress macAddress) {
        this.rssi = rssi;
        this.uniqueID = uniqueID;
        this.macAddress = macAddress;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public MacAddress getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(MacAddress macAddress) {
        this.macAddress = macAddress;
    }
}
