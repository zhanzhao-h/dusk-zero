package com.hy.dusk.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: ZhaoZhan
 * @date: 2022-12-30 16:26
 */
public interface PrivateIn {
    void normalInterfaceMethod();

    default void defaultMethod1(){

        Collectors.teeing()

    }
    private void privateMethod(){
        System.out.println("这是一个接口的私有方法");
    }
}
