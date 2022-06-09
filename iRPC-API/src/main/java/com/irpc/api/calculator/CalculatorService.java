package com.irpc.api.calculator;

/**
 * 提供加法的服务
 */
public interface CalculatorService {
    // 添加一个泛型实现两个数字相加
    <T> T add(T a, T b);
}
