package com.kk.pds.writeAheadLogs;

import java.util.HashMap;
import java.util.Map;

public class SimpleKeyValueStore {
    private final Map<String, String> store;

    public SimpleKeyValueStore(int capacity) {
        this.store = new HashMap<>(capacity);
    }

    public void put(String key, String value) {
        store.put(key, value);
    }
    public String get(String key) {
        return store.get(key);
    }
}
