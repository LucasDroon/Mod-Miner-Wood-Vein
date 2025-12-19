package com.lucas.minerar;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootWorldContext;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Minerar implements ModInitializer {
	public static final String MOD_ID = "minerar";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public Set<BlockPos> findVein(World world, Block targetBlock, BlockPos pos){

		Queue<BlockPos> q = new LinkedList<>();
		q.add(pos);

		Set<BlockPos> visited = new HashSet<>();
		visited.add(pos);

		while (!q.isEmpty()){
			BlockPos currentPos = q.poll();

			for(Direction direction : Direction.values()){
				BlockPos neighborPos = currentPos.offset(direction);
				if (world.getBlockState(neighborPos).getBlock() == targetBlock && !visited.contains(neighborPos)){


					q.add(neighborPos);
					visited.add(neighborPos);
				}
			}
		}
		return visited;
	}

	public void dropVein(World world, PlayerEntity player, BlockPos pos, BlockState state){
			List<ItemStack> drops = state.getDroppedStacks(new LootWorldContext.Builder((ServerWorld) world).add(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos)).add(LootContextParameters.TOOL, player.getMainHandStack()));

			for (ItemStack drop : drops) {
				var entity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, drop);
				world.spawnEntity(entity);

			}
		}

	public void breakVein(World world, BlockPos pos){
			world.breakBlock(pos, false);
		}

	@Override
	public void onInitialize() {
		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
		if (world.isClient() || !player.canHarvest(state) || player.isCreative()){
			return true;
		}

		boolean isOre = state.isIn(TagKey.of(RegistryKeys.BLOCK, Identifier.of("c", "ores")));
		boolean isWood = state.isIn(BlockTags.LOGS);

		//Vein miner para minerios
		if (isOre) {

			Set<BlockPos> visited = findVein(world, state.getBlock(), pos);
			visited.remove(pos);

			for (BlockPos currentlyOre : visited) {
				dropVein(world, player, pos, state);
				breakVein(world, currentlyOre);
			}

			LOGGER.info("(BEFORE) Quebrei o minério: " + state.getBlock().getName().getString());
		}
		else {
			LOGGER.info("DEBUG: Não é minério, é: " + state.getBlock().getName().getString());
		}

		//Vein miner para madeiras (só funfa com machado de ouro).
		if (isWood){
			Set<BlockPos> visited = findVein(world, state.getBlock(), pos);
			visited.remove(pos);

			if (player.getMainHandStack().getItem() == Items.GOLDEN_AXE){
				for (BlockPos currentlyWood : visited){
					dropVein(world, player, pos, state);
					breakVein(world, currentlyWood);
				}
				LOGGER.info("(BEFORE) Quebrei a madeira: " + state.getBlock().getName().getString());
			}
			else {
				LOGGER.info("DEBUG: Sem um machado de ouro, mod desativado!");
			}
		}


		// IMPORTANTE: Retorno 'true' para autorizar a quebra.
		//Se retornássemos 'false', o bloco seria indestrutível!
		return true;
	});


	}
}