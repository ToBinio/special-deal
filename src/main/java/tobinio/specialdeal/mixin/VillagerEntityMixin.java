package tobinio.specialdeal.mixin;

import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tobinio.specialdeal.SpecialDeal;

@Mixin (VillagerEntity.class)
public abstract class VillagerEntityMixin {
    @Inject (method = "prepareOffersFor", at = @At (value = "TAIL"))
    private void prepareOffersFor(PlayerEntity player, CallbackInfo ci) {
        MerchantEntity merchantEntity = (MerchantEntity) (Object) this;

        TradeOffer tradeOffer = new TradeOffer(Items.ANDESITE.getDefaultStack(), Items.DIORITE.getDefaultStack(), 10, 4, 1);
        tradeOffer.setIsSpecialOffer(true);
        tradeOffer.setLifeTime(100);

        merchantEntity.getOffers()
                .add(0, tradeOffer);
    }

    @Inject (method = "tick", at = @At (value = "TAIL"))
    private void tick(CallbackInfo ci) {
        MerchantEntity merchantEntity = (MerchantEntity) (Object) this;

        for (int i = merchantEntity.getOffers().size() - 1; i >= 0; i--) {
            TradeOffer tradeOffer = merchantEntity.getOffers().get(i);

            if (!tradeOffer.getIsSpecialOffer())
                continue;

            if (tradeOffer.getLifeTime() <= 1) {
                merchantEntity.getOffers().remove(i);
                continue;
            }

            tradeOffer.setLifeTime(tradeOffer.getLifeTime() - 1);
        }

        for (TradeOffer offer : merchantEntity.getOffers()) {
            offer.setLifeTime(offer.getLifeTime() - 1);
        }
    }
}
