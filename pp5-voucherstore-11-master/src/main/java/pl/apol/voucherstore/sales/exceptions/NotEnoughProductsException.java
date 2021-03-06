package pl.apol.voucherstore.sales.exceptions;

public class NotEnoughProductsException extends IllegalStateException {
    public NotEnoughProductsException() {
        super("Brak wystarczającej ilości dostępnych produktów");
    }
}
