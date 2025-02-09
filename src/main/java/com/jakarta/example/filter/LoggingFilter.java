package com.jakarta.example.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Override
    public void filter(ContainerRequestContext requestContext) {
        try {
            // Log request details using the logger
            logger.info("Received request from: method={} uri={}", requestContext.getMethod(), requestContext.getUriInfo().getPath());
        } catch (Exception e) {
            logger.error("Error in request logging", e);
        }
    }
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        try {
            // Log response details using the logger
            logger.info("Response: status={}", responseContext.getStatus());
        } catch (Exception e) {
            logger.error("Error in response logging", e);
        }
    }
}
