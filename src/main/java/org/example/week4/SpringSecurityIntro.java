package org.example.week4;


/**
 *  AuthenticationManager
 *
 *  user -> authenticate request
 *       -> filters
 *       -> UsernamePasswordAuthenticationFilter
 *          -> AuthenticationManager.authenticate(user input data)
 *              -> DAOAuthenticationProvider.authenticate(user input data)
 *                  -> get UserDetails by UserDetailsService
 *                      -> compare UserDetails vs user input data
 *       -> successful authentication handler
 *
 *  user -> request with jwt header
 *       -> jwt filter
 *          -> get jwt header , get jwt token
 *          -> verify jwt token
 *          -> save user details in security context(thread local)
 *
 *
 *
 */