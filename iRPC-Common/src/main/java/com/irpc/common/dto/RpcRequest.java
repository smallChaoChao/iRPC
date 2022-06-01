package com.irpc.common.dto;

import lombok.*;

import java.io.Serializable;

/**
 * RPC 请求数据传输对象 DTO(Data Transfer Object)
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 2159505111572912418L;
    private String interfaceName;
    private String methodName;
    private Object[] args;
    private Class<?>[] argsTypes;
}
