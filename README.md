== BarAPI ==
{{https://dl.dropboxusercontent.com/s/qg69hkk6dmpq8zd/shot_130901_201310.png?token_hash=AAGC7_1B9PDv79DWxGiM_1tWSabZL6OwhGfp8oUb39ZPVQ|}}

=== Server Owners ===
This plugin does nothing on its own. It is simply an API for other plugins to implement with.


=== Developers ===
Simply add BarAPI.jar to your project build path.

This plugin makes use of reflection and therefore "shouldn't" break on craftbukkit updates.

=== Limitations ===
The message can not be more than 64 characters. If it is more, BarAPI will automatically cut it to 64 characters to prevent the client from crashing. 

This is a client limitation and cannot be changed.

=== Examples ===
===== BarAPI.setMessage(Player player, String message)  =====

Set a message for the player. It will remain there until the player logs off or another plugin overrides it.



===== BarAPI.setMessage(Player player, String message, float percent) =====

Same as above except you can set the % of the health bar. 100 shows the entire health bar, 50 shows half the health bar and so on.



===== BarAPI.setMessage(final Player player, String message, int seconds) =====

Sets a timed message for the player. It will remain until the timer runs out. The health automatically reduces based on how long the timer is.



===== BarAPI.hasBar(Player player) =====

Pretty self explanatory, returns a boolean.



===== BarAPI.removeBar(Player player) =====

Also pretty self explanatory.



===== BarAPI.setHealth(Player player, float percent) =====

Allows you to modify the health of an existing bar. If the player has no bar, this does nothing.


=== Tutorials ===
[[http://youtu.be/Nb-tLbLrrmM|German]] - [[http://dev.bukkit.org/profiles/DerFeliix/|DerFeliix]]

=== Source ===
[[https://github.com/confuser/BarAPI/|GitHub]]

=== Maven ===
<<code html>>
<repositories>
	<repository>
		<id>confuser-repo</id>
		<url>http://ci.frostcast.net/plugin/repository/everything</url>
	</repository>
</repositories>

<dependencies>
	<dependency>
		<groupId>me.confuser</groupId>
		<artifactId>BarAPI</artifactId>
		<version>3.0</version>
	</dependency>
</dependencies>
<</code>>

=== Updater ===
This plugin contains an auto updater which is enabled by default. If you do not wish to automatically download new updates, edit BarAPI/config.yml and set autoUpdate to false.

=== To Dos ===
* Scroll messages if string is greater than 64 characters.

=== Plugins Using BarAPI ===
[[http://dev.bukkit.org/bukkit-plugins/welcomebar/|WelcomeBar]]

[[http://dev.bukkit.org/bukkit-plugins/parkour/|Parkour]]

[[http://dev.bukkit.org/bukkit-plugins/quicksupport/|QuickSupport]]

[[http://dev.bukkit.org/bukkit-plugins/bossbroadcast/|BossBroadcast]]

[[http://dev.bukkit.org/bukkit-plugins/bossmessage/|BossMessage]]

[[http://dev.bukkit.org/bukkit-plugins/pvpgames-automated/|PvPGames Automated]]

[[http://dev.bukkit.org/bukkit-plugins/bosseventscheduler/|BossEventScheduler]]

[[http://dev.bukkit.org/bukkit-plugins/infernal-mobs/|Infernal Mobs]]

[[http://dev.bukkit.org/bukkit-plugins/mythicmobs/|MythicMobs]]

[[http://dev.bukkit.org/bukkit-plugins/multikill/|MultiKill]]

[[http://dev.bukkit.org/bukkit-plugins/playerboss/|PlayerBoss]]

[[http://dev.bukkit.org/bukkit-plugins/bossads/|BossAds]]

[[http://dev.bukkit.org/bukkit-plugins/item-lore-stats/|Item Lore Stats]]

[[http://dev.bukkit.org/bukkit-plugins/bossbarpro/|BossBarPro]]

[[http://dev.bukkit.org/bukkit-plugins/viplobby/|VIPLobby]]

[[http://dev.bukkit.org/bukkit-plugins/battleofblocks/|Battle Of Blocks]]

[[http://dev.bukkit.org/bukkit-plugins/bossbroadcaster/|BossBroadcaster]]

[[http://dev.bukkit.org/bukkit-plugins/silkspawners/|SilkSpawners]]

[[http://dev.bukkit.org/bukkit-plugins/barjoin/|BarJoin]]

[[http://dev.bukkit.org/bukkit-plugins/join_messages/|JoinGlobalMessages]]

=== Metrics ===
To determine popularity and usage of BarAPI, plugin installs are automatically tracked by the Metrics plugin tracking system. Your Java version, OS, player count, server country location and plugin & server versions are collected. This is used to determine what environments are using the plugin to ensure full compatibility. This collection is anonymous. If you don't want this tracking, edit plugins/PluginMetrics/config.yml and set opt-out to true.
[[http://mcstats.org/plugin/BarAPI|{{http://api.mcstats.org/signature/BarAPI.png|}}]]
