package com.example.ownertest.dm.pipelline.context;

public interface PipelineContext {

    String FOR_TEST = "forTest";

    void set(String contextKey, Object contextValue);

    Object get(String contextKey);

}