package com.rapidomize.util.log4j2;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.status.StatusLogger;

@Plugin(name = "RapidomizeAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public class RapidomizeAppender extends AbstractAppender {
    private final Logger internal = StatusLogger.getLogger();

    private String appId;

    @PluginFactory
    public static RapidomizeAppender createAppender(
        @PluginAttribute("name") String name,
        @PluginAttribute("appId") String appId,
        @PluginElement("Layout") Layout<String> layout,
        @PluginElement("Filters") Filter filter) {

        return new RapidomizeAppender(name, appId, filter, layout);
    }

    @SuppressWarnings("deprecation")
    private RapidomizeAppender(String name, String appId, Filter filter, Layout<String> layout) {
        super(name, filter, layout);
        this.appId = appId;
        internal.info("Rapidomize constructed as " + name + ", appId = " + appId);
    }

    @Override
    public void append(LogEvent event) {
        internal.info("appending from " + this.getClass());
        System.out.println(getLayout().toSerializable(event));
    }
}
