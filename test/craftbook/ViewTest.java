package craftbook;

import static org.junit.Assert.*;

import java.time.Instant;

import org.junit.Test;

public class ViewTest {
	
	/*
	 * Constants used in boundary testing of calcTimespan
	 */
	public final long ONE_SECOND_MS = 1000;
	public final long ONE_MINUTE_MS = 60 * 1000;

	public final long ONE_HOUR_S = 60 * 60;
	public final long ONE_HOUR_MS = ONE_HOUR_S * 1000;

	public final long ONE_DAY_S = ONE_HOUR_S * 24;
	public final long ONE_DAY_MS = ONE_DAY_S * 1000;

	public final long FOUR_WEEKS_S = ONE_DAY_S * 28;
	public final long FOUR_WEEKS_MS = FOUR_WEEKS_S * 1000;

	public final long ONE_YEAR_S = ONE_DAY_S * 365;
	public final long ONE_YEAR_MS = ONE_YEAR_S * 1000;

	public final Instant E = Instant.EPOCH;

	/*
	 * under 1 second      : moments
	 * 1s-59s              : X seconds
	 * 1m00s - 59m59s      : X minutes
	 * 1h00m00s - 23h59m59s: X hours
	 * 1d-27d              : X days
	 * 28d+                : over a month
	 */
	@Test
	public void calcTimespanOrderShouldBeIrrelevant() {
		Instant i1 = Instant.ofEpochSecond(9525);
		Instant i2 = Instant.ofEpochSecond(9515);
		assertEquals("10 seconds", View.calcTimespan(i1, i2));
		assertEquals("10 seconds", View.calcTimespan(i2, i1));
	}
	
	@Test
	public void calcTimespanMoments() {
		assertEquals("moments", View.calcTimespan(E, E));
		assertEquals("moments", View.calcTimespan(E, Instant.ofEpochMilli(ONE_SECOND_MS - 1)));
	}
	
	@Test
	public void calcTimespanSeconds() {
		assertEquals("1 second", View.calcTimespan(E, Instant.ofEpochSecond(1)));
		assertEquals("59 seconds", View.calcTimespan(E, Instant.ofEpochMilli(ONE_MINUTE_MS - 1)));
	}
	
	@Test
	public void calcTimespanMinutes() {
		assertEquals("1 minute", View.calcTimespan(E, Instant.ofEpochSecond(60)));
		assertEquals("59 minutes", View.calcTimespan(E, Instant.ofEpochMilli(ONE_HOUR_MS - 1)));
	}
	
	@Test
	public void calcTimespanHours() {
		assertEquals("1 hour", View.calcTimespan(E, Instant.ofEpochSecond(ONE_HOUR_S)));
		assertEquals("23 hours", View.calcTimespan(E, Instant.ofEpochMilli(ONE_DAY_MS - 1)));
	}
	
	@Test
	public void calcTimespanDays() {
		assertEquals("1 day", View.calcTimespan(E, Instant.ofEpochSecond(ONE_DAY_S)));
		assertEquals("27 days", View.calcTimespan(E, Instant.ofEpochMilli(FOUR_WEEKS_MS - 1)));
	}
	
	@Test
	public void calcTimespanMonth() {
		assertEquals("over a month", View.calcTimespan(E, Instant.ofEpochSecond(FOUR_WEEKS_S)));
	}
	
}
