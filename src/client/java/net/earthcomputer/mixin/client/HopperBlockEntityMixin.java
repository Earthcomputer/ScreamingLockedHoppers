package net.earthcomputer.mixin.client;

import net.earthcomputer.IHopperBlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin implements IHopperBlockEntity {
    @Unique
    private long lastScreamTime;

    @Override
    public long screamingLockedHoppers$getLastScreamTime() {
        return lastScreamTime;
    }

    @Override
    public void screamingLockedHoppers$setLastScreamTime(long lastScreamTime) {
        this.lastScreamTime = lastScreamTime;
    }
}
