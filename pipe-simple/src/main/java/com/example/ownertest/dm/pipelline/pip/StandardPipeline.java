package com.example.ownertest.dm.pipelline.pip;


import com.example.ownertest.dm.pipelline.context.PipelineContext;
import com.example.ownertest.dm.pipelline.value.PipelineValue;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class StandardPipeline implements Pipeline {

    private List<PipelineValue> pipelineValueList = Lists.newArrayList();

    @Override
    public boolean invoke(PipelineContext pipelineContext) {
        boolean isResult = true;
        for (PipelineValue pipelineValue : pipelineValueList) {
            try {
                isResult = pipelineValue.execute(pipelineContext);
                if (!isResult) {
                    // log.error("{},exec is wrong", pipelineValue.getClass().getSimpleName());
                }
            } catch (Exception e) {
                // log.error(e.getMessage(), e);
            }
        }
        return isResult;
    }

    @Override
    public boolean addValue(PipelineValue pipelineValue) {
        if (pipelineValueList.contains(pipelineValue)) {
            return true;
        }
        return pipelineValueList.add(pipelineValue);
    }

    @Override
    public boolean removeValue(PipelineValue pipelineValue) {
        return pipelineValueList.remove(pipelineValue);
    }
}