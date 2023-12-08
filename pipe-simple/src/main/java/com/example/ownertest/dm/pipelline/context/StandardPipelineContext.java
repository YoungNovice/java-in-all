
package com.example.ownertest.dm.pipelline.context;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StandardPipelineContext implements PipelineContext {

    private Map<String, Object> contentMap = new ConcurrentHashMap<>();

    @Override
    public void set(String contextKey, Object contextValue) {
        contentMap.put(contextKey, contextValue);
    }

    @Override
    public Object get(String contextKey) {
        return contentMap.get(contextKey);
    }
}