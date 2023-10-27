package tobinio.specialdeal.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin (MerchantScreen.class)
public abstract class MerchantScreenMixin extends Screen {

    @Unique
    private static final Identifier SPECIAL_DEAL_TEXTURE = new Identifier("special-deal", "textures/gui/container/special-deal.png");

    protected MerchantScreenMixin(Text title) {
        super(title);
    }

    @Inject (method = "render", at = @At (value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/MerchantScreen;renderFirstBuyItem(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;II)V"))
    private void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci,
            @Local TradeOffer tradeOffer, @Local (ordinal = 2) int i, @Local (ordinal = 7) int n) {

        if (tradeOffer.getIsSpecialOffer()) {
            RenderSystem.enableBlend();
            context.drawTexture(SPECIAL_DEAL_TEXTURE, i + 5, n - 1, 0, 0, 88, 20);
            RenderSystem.disableBlend();
        }
    }
}
