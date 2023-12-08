package com.example.ownertest.dm.pipelline;


import com.example.ownertest.dm.pipelline.context.PipelineContext;
import com.example.ownertest.dm.pipelline.context.StandardPipelineContext;
import com.example.ownertest.dm.pipelline.pip.Pipeline;
import com.example.ownertest.dm.pipelline.pip.StandardPipeline;
import com.example.ownertest.dm.pipelline.value.ForTestValue;
import com.example.ownertest.dm.pipelline.value.GraySwitchValue;
import com.example.ownertest.dm.pipelline.value.PipelineValue;

/**
 * 入口类
 *
 * pipeline
 * 适用场景: 当你的数据流需要经过很多同等逻辑处理时，可以考虑使用此套路，便于后续扩展
 *
 * 管道(Pipeline)----用于串联阀门的管道通路
 * 阀门(PipelineValue)----用于每一个节点处理实际业务诉求
 * 管道上下文(PipelineContext)----用于管道上下文中数据的扭转
 */
public class PipelineClient {

    public static void main(String[] args) {
        // 管道初始化
        Pipeline pipeline = new StandardPipeline();
        // value扩展
        PipelineValue pipelineValue = new GraySwitchValue();
        PipelineValue pipelineValue2 = new ForTestValue();
        pipeline.addValue(pipelineValue);
        pipeline.addValue(pipelineValue2);
        // 上下文
        PipelineContext pipelineContext = new StandardPipelineContext();
        // 调用管道
        pipeline.invoke(pipelineContext);
    }

}