import java.math.BigDecimal;

import pl.rspective.voucherify.client.VoucherifyClient;
import pl.rspective.voucherify.client.VoucherifyUtils;
import pl.rspective.voucherify.client.model.Customer;
import pl.rspective.voucherify.client.model.VoucherRedemptionContext;
import pl.rspective.voucherify.client.model.VoucherRedemptionResult;

public class VoucherifyExampleApp {

    public static void main(String[] args) {
        VoucherifyClient client = new VoucherifyClient.Builder()
                .setAppId("c70a6f00-cf91-4756-9df5-47628850002b")
                .setAppToken("3266b9f8-e246-4f79-bdf0-833929b1380c")
                .build();
        try {
            VoucherRedemptionResult result = client.vouchers().redeem("Testing7fjWdr", new VoucherRedemptionContext(
                    new Customer.Builder()
                          .setId("alice.morgan")
                          .setName("Alice Morgan")
                          .setEmail("alice@morgan.com")
                          .setDescription("")
                          .addMetadata("locale", "en-GB")
                          .addMetadata("shoeSize", 5)
                          .addMetadata("favouriteBrands", new String[]{"Armani", "L'Autre Chose", "Vicini"})
                          .build()));
            
            BigDecimal initialPrice = new BigDecimal("29.99");
            BigDecimal priceAfterDiscount = VoucherifyUtils.calculatePrice(initialPrice, result.getVoucher(), null);
            System.out.println("Initial price:       " + initialPrice);
            System.out.println("Price after discout: " + priceAfterDiscount);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
