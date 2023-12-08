
package com.example.ownertest.dm.pipelline.value;


import com.example.ownertest.dm.pipelline.context.PipelineContext;

public class GraySwitchValue extends AbstractPipelineValue {
    @Override
    public boolean doExec(PipelineContext pipelineContext) {
        pipelineContext.set(PipelineContext.FOR_TEST, true);
        return true;
    }

}
