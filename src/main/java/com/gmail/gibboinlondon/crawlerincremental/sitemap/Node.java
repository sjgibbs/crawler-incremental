package com.gmail.gibboinlondon.crawlerincremental.sitemap;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Node
 *
 * @author Simon
 */
public class Node {

	// We need a special-case to identify items we know about but only by URI.
	public static Node NOT_FETCHED = new Node(null);

	private final URI uri;


	public Node(URI uri) {
		this.uri = uri;
		outbound = new ArrayList<URI>(3);
	}

	private List<URI> outbound;

	public void addOutbound(URI uri) {
		this.outbound.add(uri);
	}

	public List<URI> getOutbound() {
		return outbound;
	}
}
