package org.opendcs.testing.kiwi;

import java.util.HashMap;
import java.util.Map;

public class Product {
    public final long id;
    public final String name;

    private static final Map<Long,Product> products = new HashMap<>();

    private Product(String name) {
        this(-1, name);
    }

    private Product(long id, String name) {
        this.name = name;
        this.id = id;
    }

    public static Product of(long id, String name) {
        return products.computeIfAbsent(id, key -> new Product(id, name));
    }

    public static Product of(String name) {
        return products.values()
                .stream()
                .filter(p -> p.name.equals(name))
                .findFirst()
                .orElseGet(() -> new Product(name));
    }
}
