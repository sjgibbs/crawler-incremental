package com.gmail.gibboinlondon.crawlerincremental;

import java.net.URI;
import java.util.List;

/**
 * FetchedPage
 *
 * @author Simon
 */
public class FetchedPage {

	private URI fetchedUri;
	private List<URI> outboundLinks;

	public FetchedPage(URI fetchedUri, List<URI> outboundLinks) {
		this.fetchedUri = fetchedUri;
		this.outboundLinks = outboundLinks;
	}

	public URI getFetchedUri() {
		return fetchedUri;
	}

	public List<URI> getOutboundLinks() {
		return outboundLinks;
	}
}
