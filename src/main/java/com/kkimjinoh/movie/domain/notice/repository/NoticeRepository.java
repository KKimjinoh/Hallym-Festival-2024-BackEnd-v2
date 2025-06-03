package com.kkimjinoh.movie.domain.notice.repository;

import com.kkimjinoh.movie.domain.notice.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 공지사항 Repository
 */
@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
}