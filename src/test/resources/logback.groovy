import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender

import static ch.qos.logback.classic.Level.ERROR

// TODO:encodingをつけるとエラーになる

appender("FILE", RollingFileAppender) {
    //encoding = "UTF-8"
    file = "build/tomcat/logs/test.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%d{yyyy/MM/dd HH:mm:ss.SSS} %-5p [%t] %c:%M\\(%F:%L\\) %p %m%n"
    }
}

appender("STDOUT", ConsoleAppender) {
    //encoding = "UTF-8"
    encoder(PatternLayoutEncoder) {
        pattern = "%d{yyyy/MM/dd HH:mm:ss.SSS} %-5p [%t] %c:%M\\(%F:%L\\) %p %m%n"
    }
}

root(ERROR, ["FILE", "STDOUT"])