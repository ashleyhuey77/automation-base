package com.warnermedia.page.core.web;

import com.warnermedia.selenium.TestElement;

import java.util.ArrayList;
import java.util.List;

public class Fetch {

    public static TestElement element(Type type) {
        TestElement el = type.element();
        return el;
    }

    public static String name(Type type) {
        return type.toString();
    }

}
