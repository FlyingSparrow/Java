package com.sparrow.command;

/**
 * @author wangjianchun
 * @create 2018/7/25
 */
public interface Command {

    void execute();

    void undo();

}
