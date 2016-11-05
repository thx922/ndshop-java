package com.bjike.ndshop.goods.boot;

import com.bjike.ndshop.dbs.jpa.boot.initializer.Components;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lgq on 16-10-13.
 */
@Component
public class MyComponents extends Components {

    @Override
    public String[] scanPackages() {
        return new String[]{"com.bjike.ndshop.goods"};
    }

}