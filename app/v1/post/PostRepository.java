package v1.post;

import v1.post.entity.PostData;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface PostRepository {

    CompletionStage<Stream<PostData>> list();

    CompletionStage<PostData> create(PostData postData);

    CompletionStage<Optional<PostData>> get(Long id);

    CompletionStage<Optional<String>> update(Long id, PostData postData);
}

