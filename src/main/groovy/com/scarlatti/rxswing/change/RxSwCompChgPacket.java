package com.scarlatti.rxswing.change;

import java.util.ArrayList;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 9/8/2018
 */
public class RxSwCompChgPacket {
    private List<Runnable> changesForSelf = new ArrayList<>();
    private List<RxSwCompChgPacket> changesForChildren = new ArrayList<>();

    public List<Runnable> getChangesForSelf() {
        return changesForSelf;
    }

    public List<RxSwCompChgPacket> getChangesForChildren() {
        return changesForChildren;
    }
}
