package com.irpc.common.dto;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class RpcResponse<T> implements Serializable {
    private static final long serialVersionUID = -5301696584807090225L;
    /**
     * 返回执行的结果
     */
    private T result;
}
