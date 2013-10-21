package nl.moukail.streamtv.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.scheduling.annotation.Scheduled;

@Scheduled(cron = "0 0 * * * 0")//Run once a week, midnight on Sunday
@Target(value=ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Weekly {}