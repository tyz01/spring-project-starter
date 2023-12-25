package service;

import lombok.RequiredArgsConstructor;
import org.example.Main;
import org.example.spring.configuration.DataBaseProperties;
import org.example.spring.dto.CompanyReadDto;
import org.example.spring.integration.annotation.IT;
import org.example.spring.service.CompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = Main.class, inheritInitializers = false)
public class CompanyServiceIT {

    private static final Long COMPANY_ID = 1L;

    private final CompanyService companyService;
    private final DataBaseProperties dataBaseProperties;


    @Test
    void findById() {
        var actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        var expectedResult = new CompanyReadDto(COMPANY_ID);
        //actualResult.isPresent(actual -> assertEquals(expectedResult, actual));
    }
}
