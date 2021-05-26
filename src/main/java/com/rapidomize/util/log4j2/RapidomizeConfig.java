package com.rapidomize.util.log4j2;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.config.Property;

import java.io.Serializable;

/**
 * Interface to capture the appender configurations. This reduce the duplication in Appender implementation
 * To avoid method name conflict with other interfaces, this adapt the attribute style naming for methods.
 */
public interface RapidomizeConfig {
    String name();

    Layout<? extends Serializable> layout();

    Filter filter();

    boolean ignoreExceptions();

    Property[] properties();

    String appid();

    String token();
}
