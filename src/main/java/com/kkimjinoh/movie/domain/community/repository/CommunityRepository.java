package com.kkimjinoh.movie.domain.community.repository;

import com.kkimjinoh.movie.domain.community.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 커뮤니티 게시글 Repository
 */
@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity, Long> {
}