package com.framework.login.controllers;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.hotelfs.login.controllers;
//
//import java.io.IOException;
//import java.io.PrintStream;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import javax.inject.Inject;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author Oswald1107
// */
//@WebFilter(filterName = "SessionFilter", urlPatterns = {"/app/*"})
//public class SessionFilter implements Filter {
//
//    private static final boolean debug = true;
//
//    // The filter configuration object we are associated with.  If
//    // this value is null, this filter instance is not currently
//    // configured.
//    private FilterConfig filterConfig = null;
//
//    @Inject
//    private SessionController sc;
//
//    public SessionFilter() {
//    }
//
//    /**
//     *
//     * @param request The servlet request we are processing
//     * @param response The servlet response we are creating
//     * @param chain The filter chain we are processing
//     *
//     * @exception IOException if an input/output error occurs
//     * @exception ServletException if a servlet error occurs
//     */
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//            FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest rq = (HttpServletRequest) request;
//        HttpServletResponse rs = (HttpServletResponse) response;
//        if (sc != null) {
//            if (sc.inicioSesion()) {
//                String url = rq.getRequestURL().toString();
//                if (sc.tienePermiso(url)) {
//                    chain.doFilter(request, response);
//                } else {
//                    rs.sendRedirect(rq.getContextPath() + "/faces/index.xhtml");
//                }
//            } else {
//                rs.sendRedirect(rq.getContextPath() + "/index.xhtml");
//            }
//        } else {
//            rs.sendRedirect(rq.getContextPath() + "/index.xhtml");
//        }
//    }
//
//    /**
//     * Return the filter configuration object for this filter.
//     *
//     * @return
//     */
//    public FilterConfig getFilterConfig() {
//        return (this.filterConfig);
//    }
//
//    /**
//     * Set the filter configuration object for this filter.
//     *
//     * @param filterConfig The filter configuration object
//     */
//    public void setFilterConfig(FilterConfig filterConfig) {
//        this.filterConfig = filterConfig;
//    }
//
//    /**
//     * Destroy method for this filter
//     */
//    @Override
//    public void destroy() {
//    }
//
//    /**
//     * Init method for this filter
//     *
//     * @param filterConfig
//     */
//    @Override
//    public void init(FilterConfig filterConfig) {
//        this.filterConfig = filterConfig;
//        if (filterConfig != null) {
//            if (debug) {
//                System.out.println("SessionFilter:Initializing filter");
//            }
//        }
//    }
//
//    /**
//     * Return a String representation of this object.
//     */
//    @Override
//    public String toString() {
//        if (filterConfig == null) {
//            return ("SessionFilter()");
//        }
//        StringBuffer sb = new StringBuffer("SessionFilter(");
//        sb.append(filterConfig);
//        sb.append(")");
//        return (sb.toString());
//    }
//
//}
