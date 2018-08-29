package com.scarlatti.rxswing.inspect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 */
public class InspectedRxComponent {
    private String type;
    private String implType;
    private String id;
    private Map<String, Object> data;
    private List<InspectedRxComponent> children = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<InspectedRxComponent> getChildren() {
        return children;
    }

    public void setChildren(List<InspectedRxComponent> children) {
        this.children = children;
    }

    public String getImplType() {
        return implType;
    }

    public void setImplType(String implType) {
        this.implType = implType;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
