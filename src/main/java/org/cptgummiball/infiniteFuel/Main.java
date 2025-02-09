package org.cptgummiball.infiniteFuel;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceStartSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Main extends JavaPlugin implements Listener {

    private String fuelName;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        fuelName = getConfig().getString("fuel-name", "Infinite Fuel");

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent event) {
        Furnace furnace = (Furnace) event.getBlock().getState();
        Block belowBlock = furnace.getBlock().getLocation().subtract(0, 1, 0).getBlock();

        if (belowBlock.getType() == Material.LAVA || belowBlock.getType() == Material.MAGMA_BLOCK) {
            event.setCancelled(true);
            updateFurnaceFuelSlot(furnace);
            startInfiniteSmelting(furnace);
        }
    }

    @EventHandler
    public void onFurnaceStart(FurnaceStartSmeltEvent event) {
        Furnace furnace = (Furnace) event.getBlock().getState();
        Block belowBlock = furnace.getBlock().getLocation().subtract(0, 1, 0).getBlock();

        if (belowBlock.getType() == Material.LAVA || belowBlock.getType() == Material.MAGMA_BLOCK) {
            updateFurnaceFuelSlot(furnace);
            startInfiniteSmelting(furnace);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() instanceof FurnaceInventory furnaceInventory) {
            Furnace furnace = (Furnace) furnaceInventory.getHolder();
            assert furnace != null;
            Block belowBlock = furnace.getBlock().getLocation().subtract(0, 1, 0).getBlock();

            if (belowBlock.getType() == Material.LAVA || belowBlock.getType() == Material.MAGMA_BLOCK) {
                // Prevent removing or adding fuel
                if (event.getSlot() == 1) {
                    event.setCancelled(true);
                }
            }
        }
    }

    private void startInfiniteSmelting(Furnace furnace) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (furnace.getInventory().getSmelting() == null) {
                    cancel();
                    return;
                }

                Block belowBlock = furnace.getBlock().getLocation().subtract(0, 1, 0).getBlock();
                if (belowBlock.getType() != Material.LAVA && belowBlock.getType() != Material.MAGMA_BLOCK) {
                    cancel();
                    return;
                }

                furnace.setBurnTime((short) 200);
                furnace.update();
            }
        }.runTaskTimer(this, 0L, 20L);
    }

    private void updateFurnaceFuelSlot(Furnace furnace) {
        FurnaceInventory inventory = furnace.getInventory();
        ItemStack fuelItem = new ItemStack(Material.MAGMA_BLOCK, 1);
        ItemMeta meta = fuelItem.getItemMeta();
        assert meta != null;
        meta.setDisplayName(fuelName);
        fuelItem.setItemMeta(meta);

        inventory.setFuel(fuelItem);
        furnace.update();
    }
}
