package tobinio.specialdeal.mixin;

import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import tobinio.specialdeal.SpecialOffer;

@Mixin (TradeOffer.class)
public class TradeOfferIsSpecialOffer implements SpecialOffer {

    @Unique
    private boolean isSpecialOffer;
    @Unique
    private int lifeTime;

    @Override
    public boolean getIsSpecialOffer() {
        return isSpecialOffer;
    }

    @Override
    public void setIsSpecialOffer(boolean isSpecialOffer) {
        this.isSpecialOffer = isSpecialOffer;
    }

    @Override
    public int getLifeTime() {
        return lifeTime;
    }

    @Override
    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
}
