package site.connectdots.connectdotsprj.hotPlace.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import site.connectdots.connectdotsprj.hotPlace.entity.Hotplace;
import site.connectdots.connectdotsprj.hotPlace.entity.HotplaceLocation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class HotplaceRepositoryTest {

    @Autowired
    HotplaceRepository hotplaceRepository;

    @Test
    @DisplayName("데이터 1개 저장")
    void initialTest() {
        hotplaceRepository.save(
                Hotplace.builder()
                        .hotplaceContent("내용")
                        .hotplaceLocation(HotplaceLocation.강남구)
                        .memberIdx(1L)
                        .build()
        );
    }


    @Test
    @DisplayName("hotplace bulk data 50")
    void insertBulkTest() {

        HotplaceLocation[] hotplaceLocations = {
                HotplaceLocation.강남구, HotplaceLocation.강북구, HotplaceLocation.강동구, HotplaceLocation.강서구,
                HotplaceLocation.관악구, HotplaceLocation.광진구, HotplaceLocation.구로구, HotplaceLocation.금천구,
                HotplaceLocation.노원구, HotplaceLocation.도봉구, HotplaceLocation.동대문구, HotplaceLocation.동작구,
                HotplaceLocation.마포구, HotplaceLocation.서대문구, HotplaceLocation.서초구, HotplaceLocation.성동구,
                HotplaceLocation.성북구, HotplaceLocation.송파구, HotplaceLocation.양천구, HotplaceLocation.영등포구,
                HotplaceLocation.용산구, HotplaceLocation.은평구, HotplaceLocation.종로구, HotplaceLocation.중구,
                HotplaceLocation.중랑구
        };

        for (int i = 1; i <= 50; i++) {
            hotplaceRepository.save(
                    Hotplace.builder()
                            .hotplaceContent("내용" + i)
                            .hotplaceLocation(hotplaceLocations[i % 25])
                            .memberIdx((long) i)
                            .build()
            );
        }
    }

    @Test
    @DisplayName("전체 조회시 데이터가 50개여야만 한다.")
    void findAllTest()  {
        //given

        //when
        List<Hotplace> all = hotplaceRepository.findAll();

        //then
        assertEquals(50, all.size());
//        System.out.println(all);
        all.forEach(System.out::println);
    }



    @Test
    @DisplayName("3번째 게시물의 hotplace Location은 강서구여야만 한다.")
    void findByIdxTest() {
        //given
        Long hotplaceIdx = 3L;
        //when
        Hotplace hotplace = hotplaceRepository.findById(hotplaceIdx)
                .orElseThrow();
        //then
        assertEquals("강서구", hotplace.getHotplaceLocation().name());
    }





}