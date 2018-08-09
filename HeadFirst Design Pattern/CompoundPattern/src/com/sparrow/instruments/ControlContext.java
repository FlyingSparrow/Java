/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */


/**
 * The interface for the JavaSound tabs to open and close audio resources.
 */
package com.sparrow.instruments;

public interface ControlContext {

    void open();

    void close();
}
