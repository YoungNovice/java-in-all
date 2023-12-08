package com.example.ownertest.dm.pipelline.pip;


import com.example.ownertest.dm.pipelline.context.PipelineContext;
import com.example.ownertest.dm.pipelline.value.PipelineValue;

public interface Pipeline {

    /**
     * 执行
     *
     * @return .
     */
    boolean invoke(PipelineContext pipelineContext);

    /**
     * 添加值
     *
     * @param pipelineValue .
     * @return .
     */
    boolean addValue(PipelineValue pipelineValue);


    /**
     * 移除值
     *
     * @param pipelineValue .
     * @return .
     */
    boolean removeValue(PipelineValue pipelineValue);
}

