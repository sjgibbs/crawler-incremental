package com.gmail.gibboinlondon.crawlerincremental;

import java.net.URI;

/**
 * Fetches pages. Delivers greatly summarised impressions of those pages to the crawler.
 *
 * @author Simon
 */
public interface Fetcher {

	FetchedPage fetch(URI pageUri) throws FetchingException;

}
