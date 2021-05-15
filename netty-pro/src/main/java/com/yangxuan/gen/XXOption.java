package com.yangxuan.gen;

import io.netty.channel.ChannelOption;
import io.netty.util.ConstantPool;

public class XXOption<T> extends AbsMyCons<XXOption<T>> {

    private static final MyConstantPool<XXOption<Object>> pool = new MyConstantPool<XXOption<Object>>() {
        protected XXOption<Object> newConstant(int id, String name) {
            return new XXOption(id, name);
        }
    };

    public static final XXOption<Boolean> SO_KEEPALIVE = valueOf("SO_KEEPALIVE");

    public static <T> XXOption<T> valueOf(String name) {
        return (XXOption) pool.valueOf(name);
    }

    protected XXOption(int id, String name) {
        super(id, name);
    }

}
