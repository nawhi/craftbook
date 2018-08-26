package craftbook;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

/**
 * Class which produces a text-based view of an
 * aggregated list of a user's profile, and all
 * the profiles of other users followed by it.
 * 
 *  Example:
 * 
 *  Charlie - I'm in New York today! Anyone want to have a coffee? (2 seconds ago)
 *  Bob - Good game though. (1 minute ago)
 *  Bob - Damn! We lost! (2 minutes ago)
 *  Alice - I love the weather today (5 minutes ago)
 * 
 * @author nick
 *
 */
public class WallView extends View {

	public WallView(User user, PrintStream ostream) {
		super(user, ostream);
	}

	@Override
	public String calculate() {
		StringBuilder result = new StringBuilder();
		List<Post> posts = makePostList();
		Collections.sort(posts);
		
		// Iterate backwards to present most recent first
		for (int i = posts.size() - 1; i >= 0; --i) {
			Post post = posts.get(i);
			result.append(
				String.format(
					"%s - %s (%s ago)", 
					post.getAuthor().getHandle(),
					post.getText(),
					calcTimespan(getCurrentTime(), post.getTimestamp())
				)
			);
			if (i > 0)
				result.append('\n');
		}
		return result.toString();
	}
	
	private List<Post> makePostList() {
		List<Post> posts = targetUser.getPosts();
		for (User u: targetUser.getFollowedUsers())
			posts.addAll(u.getPosts());
		return posts;
	}

}
