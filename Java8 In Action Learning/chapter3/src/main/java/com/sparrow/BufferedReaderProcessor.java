package com.sparrow;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author wangjianchun
 * @create 2018/2/10
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

    String process(BufferedReader br) throws IOException;

}
