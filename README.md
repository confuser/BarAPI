
<p><a class="img-holder" href="https://dl.dropboxusercontent.com/s/qg69hkk6dmpq8zd/shot_130901_201310.png?token_hash=AAGC7_1B9PDv79DWxGiM_1tWSabZL6OwhGfp8oUb39ZPVQ" style="width: 610px;"><img src="https://dl.dropboxusercontent.com/s/qg69hkk6dmpq8zd/shot_130901_201310.png?token_hash=AAGC7_1B9PDv79DWxGiM_1tWSabZL6OwhGfp8oUb39ZPVQ" alt="" title="" style="width: 610px;"></a></p>
<h3 id="w-server-owners">Server Owners</h3>
<p>This plugin does nothing on its own. It is simply an API for other plugins to implement with.</p>
<h3 id="w-developers">Developers</h3>
<p>Simply add BarAPI.jar to your project build path.</p>
<p>This plugin makes use of reflection and therefore "shouldn't" break on craftbukkit updates.</p>
<h3 id="w-limitations">Limitations</h3>
<p>The message can not be more than 64 characters. If it is more, BarAPI will automatically cut it to 64 characters to prevent the client from crashing. </p>
<p>This is a client limitation and cannot be changed.</p>
<h3 id="w-examples">Examples</h3>
<h5 id="w-bar-api-set-message-player-player-string-message">BarAPI.setMessage(Player player, String message)</h5>
<p>Set a message for the player. It will remain there until the player logs off or another plugin overrides it.</p>
<h5 id="w-bar-api-set-message-player-player-string-message-float">BarAPI.setMessage(Player player, String message, float percent)</h5>
<p>Same as above except you can set the % of the health bar. 100 shows the entire health bar, 50 shows half the health bar and so on.</p>
<h5 id="w-bar-api-set-message-final-player-player-string-message">BarAPI.setMessage(final Player player, String message, int seconds)</h5>
<p>Sets a timed message for the player. It will remain until the timer runs out. The health automatically reduces based on how long the timer is.</p>
<h5 id="w-bar-api-has-bar-player-player">BarAPI.hasBar(Player player)</h5>
<p>Pretty self explanatory, returns a boolean.</p>
<h5 id="w-bar-api-remove-bar-player-player">BarAPI.removeBar(Player player)</h5>
<p>Also pretty self explanatory.</p>
<h5 id="w-bar-api-set-health-player-player-float-percent">BarAPI.setHealth(Player player, float percent)</h5>
<p>Allows you to modify the health of an existing bar. If the player has no bar, this does nothing.</p>
<h3 id="w-tutorials">Tutorials</h3>
<p><a href="http://youtu.be/Nb-tLbLrrmM">German</a> - <a href="http://dev.bukkit.org/profiles/DerFeliix/">DerFeliix</a></p>
<h3 id="w-source">Source</h3>
<p><a href="https://github.com/confuser/BarAPI/">GitHub</a></p>
<h3 id="w-maven">Maven</h3>
<div class="code-wrapper" data-lexer="html"><div class="markup-code"><pre><span class="nt">&lt;repositories&gt;</span>
	<span class="nt">&lt;repository&gt;</span>
		<span class="nt">&lt;id&gt;</span>confuser-repo<span class="nt">&lt;/id&gt;</span>
		<span class="nt">&lt;url&gt;</span>http://ci.frostcast.net/plugin/repository/everything<span class="nt">&lt;/url&gt;</span>
	<span class="nt">&lt;/repository&gt;</span>
<span class="nt">&lt;/repositories&gt;</span>

<span class="nt">&lt;dependencies&gt;</span>
	<span class="nt">&lt;dependency&gt;</span>
		<span class="nt">&lt;groupId&gt;</span>me.confuser<span class="nt">&lt;/groupId&gt;</span>
		<span class="nt">&lt;artifactId&gt;</span>BarAPI<span class="nt">&lt;/artifactId&gt;</span>
		<span class="nt">&lt;version&gt;</span>3.0<span class="nt">&lt;/version&gt;</span>
	<span class="nt">&lt;/dependency&gt;</span>
<span class="nt">&lt;/dependencies&gt;</span>
</pre></div>
</div><h3 id="w-updater">Updater</h3>
<p>This plugin contains an auto updater which is enabled by default. If you do not wish to automatically download new updates, edit BarAPI/config.yml and set autoUpdate to false.</p>
<h3 id="w-to-dos">To Dos</h3>
<ul><li>Scroll messages if string is greater than 64 characters.
</li></ul>
<h3 id="w-plugins-using-bar-api">Plugins Using BarAPI</h3>
<p><a href="http://dev.bukkit.org/bukkit-plugins/welcomebar/">WelcomeBar</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/parkour/">Parkour</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/quicksupport/">QuickSupport</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/bossbroadcast/">BossBroadcast</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/bossmessage/">BossMessage</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/pvpgames-automated/">PvPGames Automated</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/bosseventscheduler/">BossEventScheduler</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/infernal-mobs/">Infernal Mobs</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/mythicmobs/">MythicMobs</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/multikill/">MultiKill</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/playerboss/">PlayerBoss</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/bossads/">BossAds</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/item-lore-stats/">Item Lore Stats</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/bossbarpro/">BossBarPro</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/viplobby/">VIPLobby</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/battleofblocks/">Battle Of Blocks</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/bossbroadcaster/">BossBroadcaster</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/silkspawners/">SilkSpawners</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/barjoin/">BarJoin</a></p>
<p><a href="http://dev.bukkit.org/bukkit-plugins/join_messages/">JoinGlobalMessages</a></p>
<h3 id="w-metrics">Metrics</h3>
<p>To determine popularity and usage of BarAPI, plugin installs are automatically tracked by the Metrics plugin tracking system. Your Java version, OS, player count, server country location and plugin &amp; server versions are collected. This is used to determine what environments are using the plugin to ensure full compatibility. This collection is anonymous. If you don't want this tracking, edit plugins/PluginMetrics/config.yml and set opt-out to true.
<a href="http://mcstats.org/plugin/BarAPI"><img src="http://api.mcstats.org/signature/BarAPI.png" alt="" title=""></a></p>
