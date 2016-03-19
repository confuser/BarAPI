package me.confuser.barapi;

import me.confuser.barapi.nms.FakeDragon;
import me.confuser.barapi.nms.v1_8Fake;
import me.confuser.barapi.nms.v1_9;
import net.gravitydevelopment.updater.Updater;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Allows plugins to safely set a health bar message.
 *
 * @author James Mortemore
 */

public class BarAPI extends JavaPlugin implements Listener {

  private static HashMap<UUID, FakeDragon> players = new HashMap<UUID, FakeDragon>();
  private static HashMap<UUID, Integer> timers = new HashMap<UUID, Integer>();

  private static BarAPI plugin;

  private static boolean useSpigotHack = false;

  public static boolean useSpigotHack() {
    return useSpigotHack;
  }

  /**
   * Set a message for all players.<br>
   * It will remain there until the player logs off or another plugin overrides it.<br>
   * This method will show a full health bar and will cancel any running timers.
   *
   * @param message The message shown.<br>
   *                Due to limitations in Minecraft this message cannot be longer than 64 characters.<br>
   *                It will be cut to that size automatically.
   *
   * @see BarAPI#setMessage(player, message)
   */
  @Deprecated
  public static void setMessage(String message) {
    for (Player player : Bukkit.getOnlinePlayers()) {
      setMessage(player, message);
    }
  }

  /**
   * Set a message for the given player.<br>
   * It will remain there until the player logs off or another plugin overrides it.<br>
   * This method will show a full health bar and will cancel any running timers.
   *
   * @param player  The player who should see the given message.
   * @param message The message shown to the player.<br>
   *                Due to limitations in Minecraft this message cannot be longer than 64 characters.<br>
   *                It will be cut to that size automatically.
   */
  @Deprecated
  public static void setMessage(Player player, String message) {
    if (hasBar(player))
      removeBar(player);
    FakeDragon dragon = getDragon(player, message);

    dragon.name = cleanMessage(message);
    dragon.health = dragon.getMaxHealth();

    cancelTimer(player);

    sendDragon(dragon, player);
  }

  /**
   * Set a message for all players.<br>
   * It will remain there for each player until the player logs off or another plugin overrides it.<br>
   * This method will show a health bar using the given percentage value and will cancel any running timers.
   *
   * @param message The message shown to the player.<br>
   *                Due to limitations in Minecraft this message cannot be longer than 64 characters.<br>
   *                It will be cut to that size automatically.
   * @param percent The percentage of the health bar filled.<br>
   *                This value must be between 0F (inclusive) and 100F (inclusive).
   *
   * @throws IllegalArgumentException If the percentage is not within valid bounds.
   * @see BarAPI#setMessage(player, message, percent)
   */
  @Deprecated
  public static void setMessage(String message, float percent) {
    for (Player player : Bukkit.getOnlinePlayers()) {
      setMessage(player, message, percent);
    }
  }

  /**
   * Set a message for the given player.<br>
   * It will remain there until the player logs off or another plugin overrides it.<br>
   * This method will show a health bar using the given percentage value and will cancel any running timers.
   *
   * @param player  The player who should see the given message.
   * @param message The message shown to the player.<br>
   *                Due to limitations in Minecraft this message cannot be longer than 64 characters.<br>
   *                It will be cut to that size automatically.
   * @param percent The percentage of the health bar filled.<br>
   *                This value must be between 0F (inclusive) and 100F (inclusive).
   *
   * @throws IllegalArgumentException If the percentage is not within valid bounds.
   */
  @Deprecated
  public static void setMessage(Player player, String message, float percent) {
    Validate.isTrue(0F <= percent && percent <= 100F, "Percent must be between 0F and 100F, but was: ", percent);

    if (hasBar(player))
      removeBar(player);

    FakeDragon dragon = getDragon(player, message);

    dragon.name = cleanMessage(message);
    dragon.health = (percent / 100f) * dragon.getMaxHealth();

    cancelTimer(player);

    sendDragon(dragon, player);
  }

  /**
   * Set a message for all players.<br>
   * It will remain there for each player until the player logs off or another plugin overrides it.<br>
   * This method will use the health bar as a decreasing timer, all previously started timers will be cancelled.<br>
   * The timer starts with a full bar.<br>
   * The health bar will be removed automatically if it hits zero.
   *
   * @param message The message shown.<br>
   *                Due to limitations in Minecraft this message cannot be longer than 64 characters.<br>
   *                It will be cut to that size automatically.
   * @param seconds The amount of seconds displayed by the timer.<br>
   *                Supports values above 1 (inclusive).
   *
   * @throws IllegalArgumentException If seconds is zero or below.
   * @see BarAPI#setMessage(player, message, seconds)
   */
  @Deprecated
  public static void setMessage(String message, int seconds) {
    for (Player player : Bukkit.getOnlinePlayers()) {
      setMessage(player, message, seconds);
    }
  }

  /**
   * Set a message for the given player.<br>
   * It will remain there until the player logs off or another plugin overrides it.<br>
   * This method will use the health bar as a decreasing timer, all previously started timers will be cancelled.<br>
   * The timer starts with a full bar.<br>
   * The health bar will be removed automatically if it hits zero.
   *
   * @param player  The player who should see the given timer/message.
   * @param message The message shown to the player.<br>
   *                Due to limitations in Minecraft this message cannot be longer than 64 characters.<br>
   *                It will be cut to that size automatically.
   * @param seconds The amount of seconds displayed by the timer.<br>
   *                Supports values above 1 (inclusive).
   *
   * @throws IllegalArgumentException If seconds is zero or below.
   */
  @Deprecated
  public static void setMessage(final Player player, String message, int seconds) {
    Validate.isTrue(seconds > 0, "Seconds must be above 1 but was: ", seconds);

    if (hasBar(player))
      removeBar(player);

    FakeDragon dragon = getDragon(player, message);

    dragon.name = cleanMessage(message);
    dragon.health = dragon.getMaxHealth();

    final float dragonHealthMinus = dragon.getMaxHealth() / seconds;

    cancelTimer(player);

    timers.put(player.getUniqueId(), Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {

      @Override
      public void run() {
        FakeDragon drag = getDragon(player, "");
        drag.health -= dragonHealthMinus;

        if (drag.health <= 1) {
          removeBar(player);
          cancelTimer(player);
        } else {
          sendDragon(drag, player);
        }
      }

    }, 20L, 20L).getTaskId());

    sendDragon(dragon, player);
  }

  /**
   * Checks whether the given player has a bar.
   *
   * @param player The player who should be checked.
   *
   * @return True, if the player has a bar, False otherwise.
   */
  @Deprecated
  public static boolean hasBar(Player player) {
    return players.get(player.getUniqueId()) != null;
  }

  /**
   * Removes the bar from the given player.<br>
   * If the player has no bar, this method does nothing.
   *
   * @param player The player whose bar should be removed.
   */
  @Deprecated
  public static void removeBar(Player player) {
    if (!hasBar(player))
      return;

    FakeDragon dragon = getDragon(player, "");

    if (dragon instanceof v1_9) {
      ((v1_9) dragon).getBar().removePlayer(player);
    } else {
      Util.sendPacket(player, getDragon(player, "").getDestroyPacket());
    }

    players.remove(player.getUniqueId());

    cancelTimer(player);
  }

  /**
   * Modifies the health of an existing bar.<br>
   * If the player has no bar, this method does nothing.
   *
   * @param player  The player whose bar should be modified.
   * @param percent The percentage of the health bar filled.<br>
   *                This value must be between 0F and 100F (inclusive).
   */
  @Deprecated
  public static void setHealth(Player player, float percent) {
    if (!hasBar(player))
      return;

    FakeDragon dragon = getDragon(player, "");
    dragon.health = (percent / 100f) * dragon.getMaxHealth();

    cancelTimer(player);

    if (percent == 0) {
      removeBar(player);
    } else {
      sendDragon(dragon, player);
    }
  }

  /**
   * Get the health of an existing bar.
   *
   * @param player The player whose bar's health should be returned.
   *
   * @return The current absolute health of the bar.<br>
   * If the player has no bar, this method returns -1.
   */
  @Deprecated
  public static float getHealth(Player player) {
    if (!hasBar(player))
      return -1;

    return getDragon(player, "").health;
  }

  /**
   * Get the message of an existing bar.
   *
   * @param player The player whose bar's message should be returned.
   *
   * @return The current message displayed to the player.<br>
   * If the player has no bar, this method returns an empty string.
   */
  @Deprecated
  public static String getMessage(Player player) {
    if (!hasBar(player))
      return "";

    return getDragon(player, "").name;
  }

  private static String cleanMessage(String message) {
    if (message.length() > 64)
      message = message.substring(0, 63);

    return message;
  }

  private static void cancelTimer(Player player) {
    Integer timerID = timers.remove(player.getUniqueId());

    if (timerID != null) {
      Bukkit.getScheduler().cancelTask(timerID);
    }
  }

  private static void sendDragon(FakeDragon dragon, Player player) {
    if (dragon instanceof v1_9) {
      BossBar bar = ((v1_9) dragon).getBar();

      bar.addPlayer(player);
      bar.setProgress(dragon.health / dragon.getMaxHealth());
    } else {
      Util.sendPacket(player, dragon.getMetaPacket(dragon.getWatcher()));
      Util.sendPacket(player, dragon.getTeleportPacket(getDragonLocation(player.getLocation())));
    }
  }

  private static FakeDragon getDragon(Player player, String message) {
    if (hasBar(player)) {
      return players.get(player.getUniqueId());
    } else
      return addDragon(player, cleanMessage(message));
  }

  private static FakeDragon addDragon(Player player, String message) {
    FakeDragon dragon = Util.newDragon(message, getDragonLocation(player.getLocation()));

    if (dragon instanceof v1_9) {
      BossBar bar = ((v1_9) dragon).getBar();

      bar.addPlayer(player);
    } else {
      Util.sendPacket(player, dragon.getSpawnPacket());
    }

    players.put(player.getUniqueId(), dragon);

    return dragon;
  }

  private static FakeDragon addDragon(Player player, Location loc, String message) {
    FakeDragon dragon = Util.newDragon(message, getDragonLocation(loc));

    if (dragon instanceof v1_9) {
      BossBar bar = ((v1_9) dragon).getBar();

      bar.addPlayer(player);
    } else {
      Util.sendPacket(player, dragon.getSpawnPacket());
    }

    players.put(player.getUniqueId(), dragon);

    return dragon;
  }

  private static Location getDragonLocation(Location loc) {
    if (Util.isBelowGround) {
      loc.subtract(0, 300, 0);
      return loc;
    }

    float pitch = loc.getPitch();

    if (pitch >= 55) {
      loc.add(0, -300, 0);
    } else if (pitch <= -55) {
      loc.add(0, 300, 0);
    } else {
      loc = loc.getBlock().getRelative(getDirection(loc), plugin.getServer().getViewDistance() * 16).getLocation();
    }

    return loc;
  }

  private static BlockFace getDirection(Location loc) {
    float dir = Math.round(loc.getYaw() / 90);
    if (dir == -4 || dir == 0 || dir == 4)
      return BlockFace.SOUTH;
    if (dir == -1 || dir == 3)
      return BlockFace.EAST;
    if (dir == -2 || dir == 2)
      return BlockFace.NORTH;
    if (dir == -3 || dir == 1)
      return BlockFace.WEST;
    return null;
  }

  public void onEnable() {
    getConfig().options().copyDefaults(true);
    saveConfig();

    if (getConfig().getBoolean("autoUpdate")) {
      new Updater(this, 64876, getFile(), Updater.UpdateType.DEFAULT, false);
    }

    try {
      MetricsLite metrics = new MetricsLite(this);
      metrics.start();
    } catch (IOException e) {
      // Failed to submit the stats :-(
    }

    plugin = this;

    useSpigotHack = getConfig().getBoolean("useSpigotHack", false);

    if (!useSpigotHack) {
      if (v1_8Fake.isUsable()) {
        useSpigotHack = true;
        Util.detectVersion();
        getLogger().info("Detected spigot hack, enabling fake 1.8");
      }
    }

    getServer().getPluginManager().registerEvents(this, this);

    getLogger().info("Loaded");

    if (useSpigotHack) {
      getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

        public void run() {
          for (UUID uuid : players.keySet()) {
            Player p = Bukkit.getPlayer(uuid);
            Util.sendPacket(p, players.get(uuid).getTeleportPacket(getDragonLocation(p.getLocation())));
          }
        }
      }, 0L, 5L);
    }

    // TestMode
    if (getConfig().getBoolean("testMode")) {
      plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {

        @Override
        public void run() {
          for (Player player : plugin.getServer().getOnlinePlayers()) {
            BarAPI.setMessage(player, ChatColor.AQUA + "Testing BarAPI Testing BarAPI Testing BarAPI Testing BarAPI Testing BarAPI Testing BarAPI Testing BarAPI", 10);
          }
        }

      }, 30L, 300L);
    }
  }

  public void onDisable() {
    for (Player player : plugin.getServer().getOnlinePlayers()) {
      quit(player);
    }

    players.clear();

    for (int timerID : timers.values()) {
      Bukkit.getScheduler().cancelTask(timerID);
    }

    timers.clear();
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void PlayerLoggout(PlayerQuitEvent event) {
    quit(event.getPlayer());
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onPlayerKick(PlayerKickEvent event) {
    quit(event.getPlayer());
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onPlayerTeleport(final PlayerTeleportEvent event) {
    handleTeleport(event.getPlayer(), event.getTo().clone());
  }

  @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
  public void onPlayerTeleport(final PlayerRespawnEvent event) {
    handleTeleport(event.getPlayer(), event.getRespawnLocation().clone());
  }

  private void handleTeleport(final Player player, final Location loc) {

    if (!hasBar(player))
      return;

    final FakeDragon oldDragon = getDragon(player, "");

    if (oldDragon instanceof v1_9) return;

    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

      @Override
      public void run() {
        // Check if the player still has a dragon after the two ticks! ;)
        if (!hasBar(player))
          return;

        float health = oldDragon.health;
        String message = oldDragon.name;

        Util.sendPacket(player, getDragon(player, "").getDestroyPacket());

        players.remove(player.getUniqueId());

        FakeDragon dragon = addDragon(player, loc, message);
        dragon.health = health;

        sendDragon(dragon, player);
      }

    }, 2L);
  }

  private void quit(Player player) {
    removeBar(player);
  }
}
