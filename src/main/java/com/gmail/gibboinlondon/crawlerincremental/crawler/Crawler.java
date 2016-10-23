package com.gmail.gibboinlondon.crawlerincremental.crawler;

import com.gmail.gibboinlondon.crawlerincremental.FetchedPage;
import com.gmail.gibboinlondon.crawlerincremental.sitemap.Sitemap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Crawler
{
	private static final Logger LOGGER = LoggerFactory.getLogger(Crawler.class);
	private final Fetcher fetcher;
	private final Sitemap sitemap;
	private URI startPage;
	private List<URI> queue;

	public Crawler(Fetcher fetcher, Sitemap sitemap) {
		this.fetcher = fetcher;
		this.sitemap = sitemap;
		queue = new LinkedList<URI>();
	}

	public void crawlFrom(URI exampleUri) {
		startPage = exampleUri;
		queue.add(exampleUri);

		while(!queue.isEmpty()) {
			processCurrentItems();
		}

	}

	private void processCurrentItems()  {
		List<URI> currentItems = new ArrayList<URI>(queue);

		for(URI url : currentItems) {

			try {
				crawl(url);
			} catch (FetchingException e) {
				// throw this over the wall to "ops" - would need refinement of course
				LOGGER.warn("Failed to index {}. Resubmit needed", url);
			}

			queue.remove(url);
		}

	}

	private void crawl(URI uri) throws FetchingException {
		FetchedPage fetchedPage = fetcher.fetch(uri);
		if(fetchedPage==null) {
			throw new NullPointerException("fetchedPage was null. uri="+ uri.toString());
		}
		sitemap.addNodes(fetchedPage);

		List<URI> linksToCrawl = removeExternals(fetchedPage);

		queue.addAll(linksToCrawl);
	}

	private List<URI> removeExternals(FetchedPage fetchedPage) {
		List<URI> linksToCrawl = new ArrayList<URI>(fetchedPage.getOutboundLinks());
		Iterator<URI> it = linksToCrawl.iterator();
		while (it.hasNext()) {
			URI candidateUri = it.next();
			if(!candidateUri.getAuthority().equalsIgnoreCase(startPage.getAuthority())) {
				it.remove();
			}
		}
		return linksToCrawl;
	}
}
