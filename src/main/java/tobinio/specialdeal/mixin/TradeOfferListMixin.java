package tobinio.specialdeal.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin (TradeOfferList.class)
public class TradeOfferListMixin {
    @Inject (method = "method_43715", at = @At (value = "TAIL"))
    private static void toPacket(PacketByteBuf buf2, TradeOffer offer, CallbackInfo ci) {
        buf2.writeBoolean(offer.getIsSpecialOffer());
        buf2.writeInt(offer.getLifeTime());
    }

    @Inject (method = "method_43716", at = @At (value = "TAIL"))
    private static void fromPacket(PacketByteBuf buf2, CallbackInfoReturnable<TradeOffer> cir,
            @Local TradeOffer tradeOffer) {
        tradeOffer.setIsSpecialOffer(buf2.readBoolean());
        tradeOffer.setLifeTime(buf2.readInt());
    }
}
