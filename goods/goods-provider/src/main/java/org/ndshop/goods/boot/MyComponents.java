package org.ndshop.goods.boot;

import org.ndshop.dbs.jpa.boot.initializer.Components;
import org.ndshop.user.common.entity.User;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgq on 16-10-13.
 */
@Component
public class MyComponents extends Components {


    @Override
    public String[] scanPackages() {
        return new String[]{"org.ndshop.goods","org.ndshop.user"};
    }

    public static void main(String[] args) {
        System.out.println( User.class.getPackage());
    }
    @Override
    public List<Cache> initCaches() {
        List<Cache> list = new ArrayList<>();
        ConcurrentMapCache cache =  new ConcurrentMapCache("goodsServiceCache");
        ConcurrentMapCache daoCache =  new ConcurrentMapCache("goodsDaoCache");
        list.add( cache );
        list.add( daoCache );
//        return Arrays.asList(cache);
        return list;
    }
//    public static String clazzForPackage(Class<?> clazz,int parent){
//
//
//    }

}
