package org.example.week4;


/**
 *
 * ASCII -> 0 - 127
 *  128 ~ ..
 *
 * 1. authentication : user login / identity verification
 * 2. authorization : verify roles / authorities
 *    OAuth2.0
 *    JWT
 *      header.payload.signature
 *      header: algo
 *      payload: user info, token expiration time, roles..
 *      signature: encrypted(header.payload)
 *
 *      encode(header.payload.encrypt(header.payload))
 *    rest api header
 *          key: Authorization value: "Bearer XXXXX"
 *
 * 3. HTTPS : http + ssl / tls
 *
 *      Application layer  [http, websocket]
 *      Persistent layer
 *      Session layer      session
 *      Transport layer    [tcp header][http header][data]
 *      Network layer      [ip header][tcp header][http header][data]
 *      Data link layer    ethernet
 *      Physical layer     cable
 * 4. CORS
 *           preFlight request
 *     domainA  ->  domainB (white list)
 *             <- accessible list
 *     domainA (header: list) ->  domainB
 *
 * 5. CSRF
 *      <input type=hidden>?
 *      <button>
 * 6. DDOS
 *      cloud front : CND
 *
 *      user -> edge location -> server
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Filter
 *  request  ->  filter  ->   filter  ->  filter   -> DispatcherServlet(/*) -> handler mapping -> controller
 *
 *          preFilter logic
 *          doChain()
 *          postFilter logic
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Deadline: June 24th
 *  part1:
 *      1. create a new spring security project
 *      2. jwt utility
 *      3. jwt filter
 *      4. UserDetails Impl
 *      5. UserDetailsService Impl
 *      6. Spring Security Configuration
 *      7. @PreAuthorize("hasRole[]")
 *      8. use cache (hardcode user information)
 *
 *  Next Monday
 *      1. Spring Security
 *      2. Microservice In
 *
 *
 */