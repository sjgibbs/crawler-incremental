package com.gmail.gibboinlondon.crawlerincremental;

import org.junit.Ignore;
import org.junit.Test;


import static org.junit.Assert.fail;

/**
 * Unit test for simple Crawler.
 */
public class CrawlerTest
{

	@Test
	public void shouldReportDiscoveredLinksToSiteMap() {
		fail();
	}

    @Test
    public void shouldCrawlToOutboundLinks()
    {
        fail();
    }

    @Test
	public void shouldNotCrawlToExternalLinks() {
		fail();
	}

	@Test
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

}
