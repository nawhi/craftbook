package craftbook;

import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;

public abstract class View {

	protected User targetUser;
	protected PrintStream outStream;
	
	public View(User user, PrintStream ostream) {
		targetUser = user;
		outStream = ostream;
	}
	
	public void show() {
		outStream.println(calculate());
	}
	
	public abstract String calculate();
	
	public static String calcTimespan(Instant a, Instant b) {
		Duration diff = Duration.between(a,b).abs();
		
		long secs = diff.getSeconds();
		if (secs < 1)
			return "moments";
		if (secs < 60)
			return String.format("%d second%s", secs, (secs==1 ? "" : "s"));
		
		long mins = diff.toMinutes();
		if (mins < 60)
			return String.format("%d minute%s", mins, (mins==1 ? "" : "s"));
		
		long hrs = diff.toHours();
		if (hrs < 24)
			return String.format("%d hour%s", hrs, (hrs==1 ? "" : "s"));
			
		long days = diff.toDays();
		if (days < 28)
			return String.format("%d day%s", days, (days==1 ? "" : "s"));
		
		return "over a month";
	}
	
	protected Instant getCurrentTime() {
		return Instant.now();
	}
}
