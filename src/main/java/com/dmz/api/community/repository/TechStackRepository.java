// package com.dmz.api.community.repository;
//
// import java.util.List;
//
// import org.springframework.data.jpa.repository.JpaRepository;
//
// import com.dmz.api.community.domain.Community;
// import com.dmz.api.community.domain.TechStack;
//
// /**
//  * packageName    : com.dmz.api.community.repository
//  * fileName       : TechStackRepository
//  * author         : MinKyu Park
//  * date           : 2023-12-30
//  * description    :
//  * ===========================================================
//  * DATE              AUTHOR             NOTE
//  * -----------------------------------------------------------
//  * 2023-12-30        MinKyu Park       최초 생성
//  */
// public interface TechStackRepository extends JpaRepository<TechStack, Long> {
// 	List<TechStack> findByCommunity(Community community);
//
// }