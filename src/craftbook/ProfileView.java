package craftbook;

import java.io.PrintStream;
import java.util.List;

public class ProfileView extends View {

	public ProfileView(User user, PrintStream ostream) {
		super(user, ostream);
	}
	
	@Override
	public String calculate() {
		StringBuilder result = new StringBuilder();
		
		List<Post> posts = targetUser.getPosts();
		
		// posts is sorted earliest last, so iterate backwards 
		for (int i = posts.size() - 1; i >= 0; --i) {
			Post post = posts.get(i);
			result.append(
				String.format(
					"%s (%s ago)",
					post.getText(),
					calcTimespan(post.getTimestamp(), getCurrentTime())
				)
			);
			
			if (i > 0)
				result.append('\n');
		}
		
		return result.toString();
	}
}
