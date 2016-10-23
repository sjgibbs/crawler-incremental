package com.gmail.gibboinlondon.crawlerincremental;

import com.gmail.gibboinlondon.crawlerincremental.crawler.Crawler;
import com.gmail.gibboinlondon.crawlerincremental.crawler.Fetcher;
import com.gmail.gibboinlondon.crawlerincremental.crawler.FetchingException;
import com.gmail.gibboinlondon.crawlerincremental.sitemap.Sitemap;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.net.URI;
import java.util.Collections;
import java.util.List;

import static com.gmail.gibboinlondon.crawlerincremental.Examples.*;
import static java.util.Arrays.asList;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple Crawler.
 */
@RunWith(MockitoJUnitRunner.class)
public class CrawlerTest
{



	@Mock
	private Fetcher fetcher;
	private Sitemap sitemap;

	@Before
	public void setUp() {
		// stub the basics for a three page website, add more in tests as needed
		try {
			when(fetcher.fetch(EXAMPLE_URI)).thenReturn(new FetchedPage(EXAMPLE_URI,EXAMPLE_LINKS));
			when(fetcher.fetch(EXAMPLE_PAGE_A)).thenReturn(new FetchedPage(EXAMPLE_PAGE_A, noLinks()));
			when(fetcher.fetch(EXAMPLE_PAGE_B)).thenReturn(new FetchedPage(EXAMPLE_PAGE_B, noLinks()));
		} catch (FetchingException e) {
			throw new RuntimeException("Platform failure. Mockito threw an application checked exception during stubbing", e);
		}

		sitemap = new Sitemap();

	}



	@Test(timeout = 5000L)
	public void shouldReportDiscoveredLinksToSiteMap() {
		startCrawling();

		// We could have just verified a report was made on a mock sitemap, but it seemed it would be less clear.
		assertThat(sitemap.nodes(),hasKey(EXAMPLE_URI));
		assertThat(sitemap.nodes(),hasKey(EXAMPLE_PAGE_A));
		assertThat(sitemap.nodes(),hasKey(EXAMPLE_PAGE_B));
	}

	private void startCrawling() {
		Crawler crawler = new Crawler(fetcher, sitemap);
		crawler.crawlFrom(EXAMPLE_URI);
	}

	@Test(timeout = 5000L)
	public void shouldReportOutboundLinksFromSubsequentPagesToSiteMap() {
		fail();
	}

	@Test(timeout = 5000L)
    public void shouldCrawlToOutboundLinks() throws FetchingException {
        startCrawling();

		verify(fetcher).fetch(EXAMPLE_PAGE_A);
		verify(fetcher).fetch(EXAMPLE_PAGE_B);

    }

	@Test(timeout = 5000L)
	public void shouldCrawlToOutboundLinksFromSubsequentPages() throws FetchingException {

		when(fetcher.fetch(EXAMPLE_PAGE_B)).thenReturn(new FetchedPage(EXAMPLE_PAGE_B, asList(EXAMPLE_PAGE_C)));

		startCrawling();

		verify(fetcher).fetch(EXAMPLE_PAGE_C);

	}

	@Test(timeout = 5000L)
	public void shouldNotCrawlToExternalLinks() {
		fail();
	}

	@Test(timeout = 5000L)
	public void shouldNotStopIfThereIsAProblemGettingThePage() {
		fail();
	}

	@Test @Ignore
	public void shouldNotStopIfThePageIsABinaryImage() {
		fail("for test purposes, given the limited scope, there is little to distinguish this outcome from an exception.");
	}

	@Test @Ignore
	public void shouldNotWasteTimeIfPageIsRespondingVerySlowly() {
		fail("Implementing this test means going beyond the scope of the crawler and into the grabber");
	}

	@Test @Ignore
	public void shouldNotAttemptToDownloadVeryLargeFiles() {
		fail("Implementing this test means going beyond the scope of the crawler and into the grabber");
	}


	private List<URI> noLinks() {
		return Collections.<URI>emptyList();
	}
}
