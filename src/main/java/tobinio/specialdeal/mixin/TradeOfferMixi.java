package tobinio.specialdeal.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tobinio.specialdeal.SpecialOffer;

@Mixin (TradeOffer.class)
public class TradeOfferMixi {

    @Unique
    private final static String IS_SPECIAL_OFFER = "isSpecialOffer";
    private final static String LIFE_TIME = "lifeTime";

    @Inject (method = "<init>(Lnet/minecraft/nbt/NbtCompound;)V", at = @At (value = "TAIL"))
    void constructor(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains(IS_SPECIAL_OFFER)) {
            ((SpecialOffer) this).setIsSpecialOffer(nbt.getBoolean(IS_SPECIAL_OFFER));
        }

        if (nbt.contains(LIFE_TIME)) {
            ((SpecialOffer) this).setLifeTime(nbt.getInt(LIFE_TIME));
        }
    }

    @Inject (method = "toNbt", at = @At (value = "TAIL"))
    void toNbt(CallbackInfoReturnable<NbtCompound> cir, @Local NbtCompound nbtCompound) {
        nbtCompound.putBoolean(IS_SPECIAL_OFFER, ((SpecialOffer) this).getIsSpecialOffer());
        nbtCompound.putInt(LIFE_TIME, ((SpecialOffer) this).getLifeTime());
    }
}
