package me.pioula111.craftingandstats.lockedChests;

import java.util.UUID;

public class OpenerInfo {
    private String uuid;
    private String owner;
    public OpenerInfo(String uuid, String owner) {
        this.uuid = uuid;
        this.owner = owner;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }
}
