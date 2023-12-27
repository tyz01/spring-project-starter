package org.example.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;

import org.example.spring.database.repository.UserRepository;
import org.example.spring.dto.PersonalInfo;
import org.example.spring.dto.UserFilter;
import org.example.spring.entity.Role;
import org.example.spring.entity.User;
import org.example.spring.integration.annotation.IT;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {
    private final UserRepository userRepository;

    @Test
    void checkBatch() {
        var users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
        System.out.println();
    }

    @Test
    void checkJdbcTemplate() {
        var users = userRepository.findAllByCompanyIdAndRole(1L, Role.USER);
        assertThat(users).hasSize(1);
        System.out.println();
    }

    @Test
        //@Commit
    void checkAuditing() {
        var ivan = userRepository.findById(1L).get();
        ivan.setBirthDate(ivan.getBirthDate().plusYears(1L));
        userRepository.flush();
    }

    @Test
    void checkCustomImplementation() {
        UserFilter userFilter = new UserFilter(
                null, "ov", LocalDate.now()
        );
        var users = userRepository.findAllByFilter(userFilter);
        assertThat(users).hasSize(4);
    }

    @Test
    void checkProjections() {
        var users = userRepository.findAllByCompanyId(1L);
        assertThat(users).hasSize(2);
    }

    @Test
    void checkPageable() {
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        var sliceResult = userRepository.findAllBy(pageable);
        sliceResult.forEach(user -> System.out.println(user.getId()));
        assertThat(sliceResult).hasSize(2);

        while (sliceResult.hasNext()) {
            sliceResult = userRepository.findAllBy(sliceResult.nextPageable());
            sliceResult.forEach(user -> System.out.println(user.getId()));
        }
    }

    @Test
    void checkSort() {
        var sortBy = Sort.sort(User.class);
        sortBy.by(User::getFirstname).descending().and(sortBy.by(User::getLastname));
        //var sortByIdDescending = Sort.by("firstName").descending().and(Sort.by("lastName"));
        // var sortByIdDescending = Sort.by("firstName").descending().
        //        and(Sort.by("lastName"));
        var allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortBy);
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFirstTop() {
        var topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkUpdates() {
        var ivan = userRepository.getById(1L);
        assertSame(Role.ADMIN, ivan.getRole());

        var resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        ivan.getUsername();

        var theSameIvan = userRepository.getById(1L);
        assertSame(Role.ADMIN, ivan.getRole());

    }

    @Test
    void checkQueries() {
        var users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
    }
}