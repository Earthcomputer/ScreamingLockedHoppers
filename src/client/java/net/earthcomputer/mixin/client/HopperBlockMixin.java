package net.earthcomputer.mixin.client;

import net.earthcomputer.IHopperBlockEntity;
import net.earthcomputer.ScreamingLockedHoppers;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HopperBlock.class)
public abstract class HopperBlockMixin extends BlockWithEntity {
    protected HopperBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(Properties.ENABLED)) {
            return;
        }

        Direction cryDir = Direction.Type.HORIZONTAL.random(random);
        double cryOffset = random.nextDouble();
        double cryX = cryDir.getAxis() == Direction.Axis.X ? pos.getX() + 0.5 + cryDir.getOffsetX() * 0.5 : pos.getX() + cryOffset;
        double cryZ = cryDir.getAxis() == Direction.Axis.Z ? pos.getZ() + 0.5 + cryDir.getOffsetZ() * 0.5 : pos.getZ() + cryOffset;
        double cryY = pos.getY() + 0.6 + random.nextFloat() * 0.3;
        world.addParticle(ParticleTypes.DRIPPING_WATER, cryX, cryY, cryZ, 0, 0, 0);

        if (random.nextFloat() >= 0.5) {
            return;
        }

        if (world.getBlockEntity(pos) instanceof HopperBlockEntity hopperBE) {
            long timeSinceLastScream = world.getTime() - ((IHopperBlockEntity) hopperBE).screamingLockedHoppers$getLastScreamTime();
            if (timeSinceLastScream >= ScreamingLockedHoppers.MIN_TIME_BETWEEN_SCREAMS) {
                ((IHopperBlockEntity) hopperBE).screamingLockedHoppers$setLastScreamTime(world.getTime());
                world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, ScreamingLockedHoppers.SCREAMING_SOUND, SoundCategory.MASTER, 1, (float) random.nextTriangular(1, 0.2), true);
            }
        }
    }
}
