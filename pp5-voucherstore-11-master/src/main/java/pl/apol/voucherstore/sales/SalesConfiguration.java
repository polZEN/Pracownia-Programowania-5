package pl.apol.voucherstore.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.apol.payu.http.JavaHttpPayUApiClient;
import pl.apol.payu.PayU;
import pl.apol.payu.PayUCredentials;
import pl.apol.voucherstore.productcatalog.ProductCatalogFacade;
import pl.apol.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.apol.voucherstore.sales.offer.OfferMaker;
import pl.apol.voucherstore.sales.ordering.ReservationRepository;
import pl.apol.voucherstore.sales.payment.PayUPaymentGateway;
import pl.apol.voucherstore.sales.payment.PaymentGateway;
import pl.apol.voucherstore.sales.product.ProductCatalogProductDetailsProvider;
import pl.apol.voucherstore.sales.product.ProductDetailsProvider;

@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade salesFacade(ProductCatalogFacade productCatalogFacade, OfferMaker offerMaker, PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        return new SalesFacade(
                productCatalogFacade,
                new InMemoryBasketStorage(),
                () -> "customer_1",
                (productId) -> true,
                offerMaker,
                paymentGateway,
                reservationRepository);
    }

    @Bean
    PaymentGateway payUPaymentGateway() {
        return new PayUPaymentGateway(new PayU(
                PayUCredentials.productionOfEnv(),
                new JavaHttpPayUApiClient()
        ));
    }

    @Bean
    OfferMaker offerMaker(ProductDetailsProvider productDetailsProvider) {
        return new OfferMaker(productDetailsProvider);
    }

    @Bean
    ProductDetailsProvider productDetailsProvider(ProductCatalogFacade productCatalogFacade) {
        return new ProductCatalogProductDetailsProvider(productCatalogFacade);
    }
}
