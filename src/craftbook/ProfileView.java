package craftbook;

import java.io.PrintStream;
import java.util.List;

/**
 * Class that produces a text-based view of a user's
 * profile. 
 * This takes the form of a list of posts, most recent
 * first, and detail of how long ago the posts were
 * created.
 * 
 *  Example:
 *  
 *  Good game though. (1 minute ago)
 *  Damn! We lost! (2 minutes ago)
 *  
 * @author nick
 *
 */
public class ProfileView extends View {

	public ProfileView(User user) {
		super(user);
	}
	
	public ProfileView(User user, PrintStream ostream) {
		super(user, ostream);
	}
	
	@Override
	public String calculate() {
		StringBuilder result = new StringBuilder();
		
		// Assumed to be pre-sorted (see getPosts description)
		List<Post> posts = targetUser.getPosts();
		
		// Iterate backwards (most recent first)
		for (int i = posts.size() - 1; i >= 0; --i) {
			Post post = posts.get(i);
			result.append(
				String.format(
					"%s (%s ago)",
					post.getMessage(),
					calcTimespan(post.getTimestamp(), getCurrentTime())
				)
			);
			
			if (i > 0)
				result.append('\n');
		}
		
		return result.toString();
	}
}
