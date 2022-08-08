package com.home.servicebus.model;

import com.microsoft.azure.servicebus.management.AccessRights;

import java.util.List;

public class AccessPolicy {
    private String name;
    private List<AccessRights> accessRights;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccessRights> getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(List<AccessRights> accessRights) {
        this.accessRights = accessRights;
    }
}
