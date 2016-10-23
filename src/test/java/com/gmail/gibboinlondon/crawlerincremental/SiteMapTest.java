package com.gmail.gibboinlondon.crawlerincremental;

import com.gmail.gibboinlondon.crawlerincremental.sitemap.Node;
import com.gmail.gibboinlondon.crawlerincremental.sitemap.Sitemap;
import org.junit.Test;

import java.net.URI;

import static com.gmail.gibboinlondon.crawlerincremental.Examples.*;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertThat;

/**
 * SiteMapTest
 *
 * @author Simon
 */
public class SiteMapTest {


	@Test
	public void shouldAddNodeForFetchedPage() {
		Sitemap sitemap = handleOneFetchedPage();

		assertThat(sitemap.nodes(),hasKey(EXAMPLE_URI));

	}

	@Test
	public void fetchedPageMustNotBeTheNullObejct() {
		Sitemap sitemap = handleOneFetchedPage();
		assertThat(sitemap.nodes().get(EXAMPLE_URI),not(is(Node.NOT_FETCHED)));
	}

	@Test
	public void shouldContainNullObjectForUnfetchedLinks() {
		Sitemap sitemap = handleOneFetchedPage();

		assertThat(sitemap.nodes().get(EXAMPLE_PAGE_A),is(Node.NOT_FETCHED));
		assertThat(sitemap.nodes().get(EXAMPLE_PAGE_B),is(Node.NOT_FETCHED));



	}

	@Test
	public void nullObjectMustBeReplacedWhenPageIsFetched() {
		Sitemap sitemap = handleOneFetchedPage();

		sitemap.addNodes(new FetchedPage(EXAMPLE_PAGE_B,asList(EXAMPLE_PAGE_C)));

		assertThat(sitemap.nodes().get(EXAMPLE_PAGE_B),not(is(Node.NOT_FETCHED)));
	}



	private Sitemap handleOneFetchedPage() {
		FetchedPage page = new FetchedPage(EXAMPLE_URI,EXAMPLE_LINKS);
		Sitemap sitemap = new Sitemap();

		sitemap.addNodes(page);
		return sitemap;
	}

}
