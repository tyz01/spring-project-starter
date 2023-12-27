package org.example.spring.database.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.example.spring.database.querydsl.QPredicates;
import org.example.spring.dto.UserFilter;
import org.example.spring.entity.QUser;
import org.example.spring.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static org.example.spring.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

//    @Override
//    public List<User> findAllByFilter(UserFilter userFilter) {
//        var cb = entityManager.getCriteriaBuilder();
//        var criteria = cb.createQuery(User.class);
//        var user = criteria.from(User.class);
//        criteria.select(user);
//        List<Predicate> predicates = new ArrayList<>();
//        if (userFilter.firstname() != null) {
//            predicates.add(cb.like(user.get("firstname"), userFilter.firstname()));
//        }
//        if (userFilter.lastname() != null) {
//            predicates.add(cb.like(user.get("lastname"), userFilter.lastname()));
//        }
//        if (userFilter.birthDate() != null) {
//            predicates.add(cb.lessThan(user.get("birthDate"), userFilter.birthDate()));
//        }
//        criteria.where(predicates.toArray(Predicate[]::new));
//        return entityManager.createQuery(criteria).getResultList();
//    }

    public List<User> findAllByFilter(UserFilter userFilter) {
        var predicate = QPredicates.builder()
                .add(userFilter.firstname(), user.firstname::containsIgnoreCase)
                .add(userFilter.lastname(), user.lastname::containsIgnoreCase)
                .add(userFilter.birthDate(), user.birthDate::before)
                .build();
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }
}
