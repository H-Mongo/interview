package cn.h2uwei.util;

import io.netty.util.CharsetUtil;

import java.util.Base64;

/**
 * base64 util
 *
 * @author h.mongo
 * @date 2023/7/20 14:16
 */
public class Base64Utils {

    private Base64Utils() {}

    public static byte[] base64Decode(String s) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(s);
    }

    public static byte[] base64Encode(String s) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encode(s.getBytes(CharsetUtil.UTF_8));
    }

}
