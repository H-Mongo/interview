package cn.h2uwei.rpc.protocol;

import cn.h2uwei.rpc.model.Invocation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * rpc 协议
 *
 * @author h.mongo
 * @date 2023/7/20 14:05
 */
public class RpcProtocol {

    private static final String RPC_PROTOCOL = "rpc://";
    private static final Pattern RPC_SCHEMA_PATTERN = Pattern.compile("^(\\s*" + RPC_PROTOCOL + ")(?<action>[0-9a-zA-Z]+)\\?(?<invoker>.+)$");

    public static void main(String[] args) {
//        parse("    rpc://abc");
        praseInvocation("    rpc://invoke?class=abc&method=say&parameter=java.lang.String");
//        parse("    rpc://abc?");
    }


    public static Invocation praseInvocation(String str) {
        Matcher matcher = RPC_SCHEMA_PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("不符合rpc协议格式！");
        }

        String action = matcher.group("action");
        if (!"invoke".equals(action)) {
            throw new IllegalArgumentException("操作不支持！当前仅仅支持invoke操作！当前操作：" + action);
        }
        String invoker = matcher.group("invoker");
        String[] split = invoker.split("&");
        Map<String, String> map = new HashMap<>(split.length);
        for (String s : split) {
            String[] pair = s.split("=");
            map.put(pair[0], pair[1]);
        }
        System.out.println(map);
        Invocation invocation = new Invocation();
        invocation.setClassName(map.get("class"));
        invocation.setMethodName(map.get("method"));
        invocation.setParams(new Object[]{map.get("parameter")});
        return invocation;
    }


}
