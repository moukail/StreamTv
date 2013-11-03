package nl.moukail.streamtv.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Ismail
 *Run everyday, beginning of day
 */
@Scheduled(cron = "0 0 0/5 * * ?")//Run once an hour, beginning of hour
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FiveHours { }
