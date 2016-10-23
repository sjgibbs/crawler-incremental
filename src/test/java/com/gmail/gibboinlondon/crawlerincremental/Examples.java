package com.gmail.gibboinlondon.crawlerincremental;

import java.net.URI;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Shared example objects for tests
 *
 * @author Simon
 */
public class Examples {

	public static final URI EXAMPLE_URI = URI.create("http://example.com");
	public static final URI EXAMPLE_PAGE_A = EXAMPLE_URI.resolve("/a.html");
	public static final URI EXAMPLE_PAGE_B = EXAMPLE_URI.resolve("/b.html");
	public static final URI EXAMPLE_PAGE_C = EXAMPLE_URI.resolve("/c.html");

	public static final List<URI> EXAMPLE_LINKS = asList(EXAMPLE_PAGE_A, EXAMPLE_PAGE_B); // not C
}
