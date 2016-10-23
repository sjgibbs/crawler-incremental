
# Tradeoffs

> The Kobayashi Maru is a training exercise in the fictional Star Trek universe designed to test the character of 
> Starfleet Academy cadets in a no-win scenario. 
>
> The notional primary goal of the exercise is to rescue the civilian vessel Kobayashi Maru in a simulated battle with 
> the Klingons. The disabled ship is located in the Klingon Neutral Zone, and any Starfleet ship entering the zone would 
> cause an interstellar incident. The approaching cadet crew must decide whether to attempt rescue of the Kobayashi Maru 
> crew – endangering their own ship and lives – or leave the Kobayashi Maru to certain destruction. If the cadet chooses 
> to attempt rescue, the simulation is designed to guarantee that the ship is destroyed with the loss of all crew members.
>
> *Wikipedia Contributors*

Attempting to build a functional web crawler in 2 hours is a Kobayashi Maru scenario. All you can hope to achieve is
to demonstrate good character.

The primary goals here is, I believe, to demonstrate effective use of TDD (and nothing much more).

As such, there is zero to be gained demonstrating familiarity with HTTP and its clients libraries, and zero to be 
gained from showing a working web-crawler product. What will be handed in is a bunch of passing tests.

# Approach

The approach implied above is incremental. Not iterative. I naturally gravitate to iterative delivery and found the 
switch uncomfortable and artificial, but got over it. I did have a go at delivering iteratively first, and discarded it.

# Architecture

I'm delivering a one-class crawler with a fetcher modelled as a single class, but mocked in all tests. i.e. there is no 
real fetcher. An alternative I explored in some depth is the have a second fetcher component co-operating over HTTP and
I actually added basic crawling features to huluma.net (which I own).

Example API call: http://huluma.net/metadata/http://mailchimp.com/

My gut is that a second fetcher component is useful since grabbing HTML is wasteful of RAM (most of the HTML is 
discarded), and this would impact the economics of running a crawler for any of the usual reasons (e.g. calculating page 
rank). There may be yet more concerns (such as archiving fetched content) that make the economics even more compelling. 
The crawler itself should likely use a separate queue also.

Exploring this any further in 2 hours is not useful.