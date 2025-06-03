package com.kkimjinoh.movie.domain.lostitem.repository;

import com.kkimjinoh.movie.domain.lostitem.entity.LostItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 분실물 Repository
 */
@Repository
public interface LostItemRepository extends JpaRepository<LostItemEntity, Long> {
}