package com.gmail.gibboinlondon.crawlerincremental.crawler;

/**
 * Represents any of a very broad variety of problems that may occur such as policy violations (the file is too big)
 * and technical ones (the connection broke)
 *
 * In practice a little nuance over retry policies might be required (particularly around 503s) but that is beyond my
 * chosen scope.
 *
 * @author Simon
 */
public class FetchingException extends Exception {



}
