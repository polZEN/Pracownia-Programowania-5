package pl.apol.voucherstore.sales;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.apol.voucherstore.sales.ordering.ClientData;
import pl.apol.voucherstore.sales.ordering.Reservation;
import pl.apol.voucherstore.sales.ordering.ReservationItem;
import pl.apol.voucherstore.sales.ordering.ReservationRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryTest {
    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void itAllowToStoreReservationWithItems() {
        var reservation = new Reservation(
                UUID.randomUUID().toString(),
                new ClientData("Adrian", "Pol", "adrpol@pol.pl"),
                Arrays.asList(
                        new ReservationItem("123", "name", BigDecimal.TEN, 1),
                        new ReservationItem("124", "name", BigDecimal.TEN, 1)
                ),
                BigDecimal.TEN
        );

        reservationRepository.save(reservation);

        var loaded = reservationRepository.loadById(reservation.getId());

        assertThat(loaded).isPresent();
        assertThat(loaded.get().getId()).isEqualTo(reservation.getId());
    }
}
