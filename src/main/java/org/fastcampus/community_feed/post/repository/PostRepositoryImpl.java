package org.fastcampus.community_feed.post.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.community_feed.post.application.interfaces.PostRepository;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.repository.entity.post.PostEntity;
import org.fastcampus.community_feed.post.repository.jpa.JpaPostRepository;
import org.fastcampus.community_feed.post.repository.post_queue.UserPostQueueCommandRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;

    @Override
    @Transactional //레포지토리 단이든, 서비스 단이든 조회 이외의 기능은 transaction내에서 실행되어야함
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post); //새로 생성된 postEntity라서 이전에 조회해서 영속성컨텍스트 영역에 있는 postEntity객체와는 동일한 필드정보를 가질 뿐 다른 객체 => save시 persist말고 merge(조회쿼리한번더날림)로 동작함
        if(postEntity.getId()!=null){//Id가 있는 PostEntity즉 DB에 저장되었고 조회로 불러온 PostEntity의 경우 save내 merge방지를 위해 save쓰지않고 바로 update쿼리 사용
            jpaPostRepository.updatePostEntity(postEntity);
            return postEntity.toPost();
        }//처음 등록하는 postEntity일 경우
        postEntity = jpaPostRepository.save(postEntity); //조회쿼리 두번 날아가는 해결책=> 1.같은 트랜잭션내라면 내용같을때 동일성을 보장함/2.update 쿼리 만들어 사용
        userPostQueueCommandRepository.publishPost(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        return postEntity.toPost(); //PostEntity가 영속성컨텍스트에 남아있지 Post객체는 영속성컨텍스트와 관련이 없음/ ID채워지나 확인 필요!!
    }


}
