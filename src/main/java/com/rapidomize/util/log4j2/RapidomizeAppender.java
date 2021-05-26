package com.rapidomize.util.log4j2;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.status.StatusLogger;

import java.io.Serializable;


/**
 * Sends log events to Rapidomize cloud.
 */
@Plugin(name = "RapidomizeAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE, printObject = true)
public class RapidomizeAppender extends AbstractAppender {
    // NOTE: Use literal values for 'category' and 'elementType' to support older versions of log4j2

    public static class Builder<B extends Builder<B>> extends AbstractAppender.Builder<B>
        implements org.apache.logging.log4j.core.util.Builder<RapidomizeAppender>, RapidomizeConfig {

        /**
         * Instantiate a new Rapidomize Appender
         *
         * @return A new RapidomizeAppender object
         */
        @Override
        public RapidomizeAppender build() {
            return new RapidomizeAppender(this);
        }

        @PluginBuilderAttribute("appid")
        @Required(message = "RapidomizeAppender: no application id is provided")
        private String appid;

        /**
         * Id of the Rapidomize Application to which this appender should send events.
         * This is required for every appender
         *
         * @return Rapidomize ICApp id
         */
        @Override
        public String appid() {
            return this.appid;
        }

        public B setAppid(String appid) {
            this.appid = appid;
            return asBuilder();
        }

        @PluginBuilderAttribute("token")
        @Required(message = "RapidomizeAppender: no authorization token is provided")
        private String token;

        /**
         * The authorization token for the application.
         * This is required for evey appender
         *
         * @return Rapidomize authorization token
         */
        @Override
        public String token() {
            return this.token;
        }

        public B setToken(String token) {
            this.token = token;
            return asBuilder();
        }

        /* Following configurations are common to all Log4j plugins */

        @Override
        public String name() {
            return getName();
        }

        @Override
        public Filter filter() {
            return getFilter();
        }

        @Override
        public Layout<? extends Serializable> layout() {
            return getLayout();
        }

        @Override
        public boolean ignoreExceptions() {
            return isIgnoreExceptions();
        }

        @Override
        public Property[] properties() {
            return getPropertyArray();
        }

        @Override
        public String toString() {
            return "RapidomizeConfig{" +
                "appid=" + appid() +
                ", token=" + token() +
                "}";
        }
    }

    // status logger for internal log messages from appender.
    private final Logger LOGGER = StatusLogger.getLogger();

    private RapidomizeConfig config;
    private RapidomizeManager manager;

    protected RapidomizeAppender(RapidomizeConfig config) {
        super(config.name(), config.filter(), config.layout(), config.ignoreExceptions(), config.properties());
        LOGGER.info(config);
        this.manager = new RapidomizeManager();
    }

    @PluginBuilderFactory
    public static <B extends Builder<B>> B newBuilder() {
        return new Builder<B>().asBuilder();
    }

    @Override
    public void append(LogEvent event) {
        System.out.println(getLayout().toSerializable(event));
    }


}
