package cn.h2uwei.rpc.service.impl;

import cn.h2uwei.rpc.service.EchoService;

/**
 * EchoService implement class
 *
 * @author h.mongo
 * @date 2023/7/19 17:09
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String msg) {
        return "echo>: " + msg;
    }
}
