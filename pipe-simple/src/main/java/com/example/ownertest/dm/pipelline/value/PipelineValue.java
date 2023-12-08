package com.example.ownertest.dm.pipelline.value;


import com.example.ownertest.dm.pipelline.context.PipelineContext;


public interface PipelineValue {

    /**
     * 节点执行
     *
     * @param pipelineContext
     * @return
     */
    boolean execute(PipelineContext pipelineContext);

}

