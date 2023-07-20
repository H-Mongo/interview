package cn.h2uwei.rpc.model;

import lombok.Data;

import java.io.Serializable;

/**
 * invocation model
 *
 * @author h.mongo
 * @date 2023/7/19 17:10
 */
@Data
public class Invocation implements Serializable {
    private String className;
    private String methodName;
    private Class<?>[] methodParamType;
    private Object[] params;

}
