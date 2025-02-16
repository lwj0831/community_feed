package org.fastcampus.community_feed.post.application.interfaces;

import org.fastcampus.community_feed.post.domain.Post;

import java.util.Optional;

public interface PostRepository {
    Post save(Post pos);
    Optional<Post> findById(Long id);
}
