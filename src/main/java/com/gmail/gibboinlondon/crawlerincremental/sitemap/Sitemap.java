package com.gmail.gibboinlondon.crawlerincremental.sitemap;

import com.gmail.gibboinlondon.crawlerincremental.FetchedPage;

import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Sitemap
 *
 * @author Simon
 */
public class Sitemap {
	private Map<URI, Node> nodesByUri;

	public Sitemap() {
		nodesByUri = new LinkedHashMap<URI, Node>();
	}

	public Map<URI,Node> nodes() {
		return Collections.unmodifiableMap(nodesByUri);
	}

	public void addNodes(FetchedPage fetchedPage) {
		Node node = new Node(fetchedPage.getFetchedUri());
		for(URI url : fetchedPage.getOutboundLinks()) {
			node.addOutbound(url);
			if(!nodesByUri.containsKey(url)) {
				nodesByUri.put(url,Node.NOT_FETCHED);
			}
		}
		nodesByUri.put(fetchedPage.getFetchedUri(), node);
	}
}
