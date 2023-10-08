package com.example.observationdemo

import io.micrometer.observation.annotation.Observed
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*


@Service
internal class MyUserService {
    private val random: Random = Random()

    // Example of using an annotation to observe methods
    // <user.name> will be used as a metric name
    // <getting-user-name> will be used as a span  name
    // <userType=userType2> will be set as a tag for both metric & span
    @Observed(name = "user.name", contextualName = "getting-user-name", lowCardinalityKeyValues = ["userType", "userType2"])
    fun userName(userId: String?): String {
        log.info("Getting user name for user with id <{}>", userId)
        try {
            Thread.sleep(random.nextLong(200L)) // simulates latency
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
        return "foo"
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(MyUserService::class.java)
    }
}