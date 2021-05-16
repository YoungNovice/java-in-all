package com.yangxuan.nio.netty.code2;

import com.yangxuan.nio.netty.codec.StudentPOJO;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();
        if (dataType == MyDataInfo.MyMessage.DataType.studentType) {
            MyDataInfo.Student student = msg.getStudent();
            System.out.println("客户端发送的数据student id=" + student.getId() + "name=" + student.getName());
        } else if (dataType == MyDataInfo.MyMessage.DataType.workType) {
            MyDataInfo.Worker work = msg.getWork();
            System.out.println("客户端发送的数据work id=" + work.getAge() + "name=" + work.getName());
        } else {
            System.out.println("传输的数据类型不正确！！！");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端～", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
