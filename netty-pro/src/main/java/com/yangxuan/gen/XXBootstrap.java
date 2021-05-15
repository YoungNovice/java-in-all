package com.yangxuan.gen;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;

import java.util.LinkedHashMap;
import java.util.Map;

public class XXBootstrap {

    private final Map<XXOption<?>, Object> childOptions = new LinkedHashMap<>();

    public <T> XXBootstrap childOption(XXOption<T> childOption, T value) {
        if (childOption == null) {
            throw new NullPointerException("childOption");
        } else {
            if (value == null) {
                synchronized(this.childOptions) {
                    this.childOptions.remove(childOption);
                }
            } else {
                synchronized(this.childOptions) {
                    this.childOptions.put(childOption, value);
                }
            }
            return this;
        }
    }

    public static void main(String[] args) {
        XXBootstrap bootstrap = new XXBootstrap();
        // bootstrap.childOptions(XXOption.SO_KEEPALIVE, true);
    }
}
