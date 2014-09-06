import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import java.lang.management.ManagementFactory

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.TRACE

// TODO:encodingをつけるとエラーになる



def logFile = "/var/log/blc/tomcat/BIG0116_01/mobile/logs/mobile.log"
def logLevel = DEBUG


def osMXBean = ManagementFactory.getOperatingSystemMXBean()
if("${osMXBean.name}".contains("Mac")){
    logFile = "build/tomcat/logs/mobile.log"
    logLevel = TRACE
}

appender("FILE", RollingFileAppender) {
    //encoding = "UTF-8"
    file = logFile

    encoder(PatternLayoutEncoder) {
        pattern = "%d{yyyy/MM/dd HH:mm:ss.SSS} [%level] [%thread] [%logger{0}] %message [%logger] %n"
//        pattern = "%d{yyyy/MM/dd HH:mm:ss.SSS} %-5p [%t] %c:%M\\(%F:%L\\) %p %m%n"
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = logFile + ".%d{yyyy-MM-dd}"
    }
}

appender("STDOUT", ConsoleAppender) {
    //encoding = "UTF-8"
    encoder(PatternLayoutEncoder) {
        pattern = "%d{yyyy/MM/dd HH:mm:ss.SSS} [%level] [%thread] [%logger{0}] %message [%logger] %n"
//        pattern = "%d{yyyy/MM/dd HH:mm:ss.SSS} %-5p [%t] %c:%M\\(%F:%L\\) %p %m%n"
    }
}

root(logLevel, ["FILE", "STDOUT"])
