package com.scarlatti.rxswing.change;

import com.scarlatti.rxswing.component.ntv.RxJLabel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.scarlatti.rxswing.component.ntv.RxJLabel.TEXT_PROPERTY;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 8/26/2018
 */
public class RxJLabelChgMger implements RxNtvChgMger {
    private RxJLabel master;
    private RxJLabel other;

    public static RxJLabelChgMger getInstance(RxJLabel master, RxJLabel other) {
        return new RxJLabelChgMger(master, other);
    }

    private RxJLabelChgMger(RxJLabel master, RxJLabel other) {
        this.master = master;
        this.other = other;
    }

    // limitation: using "master" right now, because we aren't replacing components at all...
    public List<Runnable> pleaseCreateChgPkt() {

        List<Runnable> chgPkt = new ArrayList<>();
        Map<String, Object> crntMap = master.getData();
        Map<String, Object> otherMap = other.getData();

        // tmp limitation only compares existing properties...
        for (String key : crntMap.keySet()) {
            if (otherMap.containsKey(key)) {
                if (!crntMap.get(key).equals(otherMap.get(key))) {
                    switch (key) {
                        case TEXT_PROPERTY:
                            chgPkt.add(() -> {
                                master.setText(String.valueOf(otherMap.get(key)));
                                master.getParent().invalidate();
                            });
                            break;
                    }
                }
            }
        }

        return chgPkt;
    }
}
