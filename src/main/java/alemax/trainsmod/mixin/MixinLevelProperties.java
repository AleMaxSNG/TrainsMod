package alemax.trainsmod.mixin;

import alemax.trainsmod.global.trackmarker.TrackMarkerHandler;
import alemax.trainsmod.global.trackmarker.TrackMarkerInstances;
import com.mojang.datafixers.DataFixer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelProperties.class)
public class MixinLevelProperties {

    /*
    @Inject(at = @At("RETURN"), method = "<init>")
    private void onConstructed(CallbackInfo info) {
        TrackMarkerInstances.SERVER_HANDLER = new TrackMarkerHandler(0);
        System.out.println("Created new Handler");
    }
     */



    @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/nbt/CompoundTag;Lcom/mojang/datafixers/DataFixer;ILnet/minecraft/nbt/CompoundTag;)V")
    private void onConstructed(CompoundTag compoundTag_1, DataFixer dataFixer_1, int int_1, CompoundTag compoundTag_2, CallbackInfo info) {
        TrackMarkerInstances.HANDLER = new TrackMarkerHandler(compoundTag_1.getInt("trackMarkerCount"));
        System.out.println("Created new Handler with count of " + TrackMarkerInstances.HANDLER.count);
    }





    @Inject(at = @At("RETURN"), method = "updateProperties")
    private void updateProperties(CompoundTag compoundTag_1, CompoundTag compoundTag_2, CallbackInfo info) {
        compoundTag_1.putInt("trackMarkerCount" , TrackMarkerInstances.HANDLER.count);
        System.out.println("Saved Count of: " + TrackMarkerInstances.HANDLER.count);
    }


}