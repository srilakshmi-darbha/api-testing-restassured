package com.thoughtworks.qabootcamp.context;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private TestContext(){};
    Map<String, String> context = new HashMap();

    public void setProperty(String key, String value){
        context.put(key, value);
    }

    public String getProperty(String key){
        return context.get(key);
    }

    public void removeProperty(String key){
        context.remove(key);
    }

    public static TestContext getInstance(){
        return InnerTextContext.testContext;
    }

    private static class InnerTextContext {
        private static TestContext testContext = new TestContext();
    }

}
