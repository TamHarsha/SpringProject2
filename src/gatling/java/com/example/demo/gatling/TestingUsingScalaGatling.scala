package com.example.demo.gatling;

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class TestingUsingScalaGatling extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8081").acceptHeader("application/json")

  val scn = scenario("Load Test using Scala")
    .exec(
      http("Get All USing Feign Client")
        .get("/api/admin/feignclient")
        .check(status.is(200))
    )
    .pause(5)

  setUp(
    scn.inject(
      atOnceUsers(1), // Start with 1 users at once
      rampUsers(5) during (10 seconds) // Gradually add 5 users over 10 seconds
    ).protocols(httpProtocol)
  )
}