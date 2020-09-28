package co.com.reto.horas_extra;

import co.com.reto.horas_extra.controller.ReporteController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class HorasExtraApplicationTests {


    private ReporteController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
