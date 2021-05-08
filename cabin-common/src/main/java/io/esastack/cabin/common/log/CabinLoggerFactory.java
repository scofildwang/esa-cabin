/*
 * Copyright 2021 OPPO ESA Stack Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.esastack.cabin.common.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * For set cabin log level dynamic, use the System property
 */
public class CabinLoggerFactory {

    private static volatile Level level;

    public static void setLevel(final String level) {
        CabinLoggerFactory.level = Level.toLevel(level);
    }

    public static Logger getLogger(final Class<?> type) {
        final Logger logger = LoggerFactory.getLogger(type);
        if (level != null) {
            final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            final ch.qos.logback.classic.Logger vLogger = loggerContext.getLogger(type);
            if (vLogger != null) {
                vLogger.setLevel(level);
            }
        }
        return logger;
    }
}
