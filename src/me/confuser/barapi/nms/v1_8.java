package me.confuser.barapi.nms;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.confuser.barapi.Util;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class v1_8 extends FakeDragon {
	private static int idCount = 1234;
	private int id;

	public v1_8(String name, Location loc) {
		super(name, loc);
		
		setMaxHealth(300);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Object getSpawnPacket() {
		Class<?> PacketPlayOutSpawnEntityLiving = Util.getCraftClass("PacketPlayOutSpawnEntityLiving");
		Object packet = null;
		
		try {			
			packet = PacketPlayOutSpawnEntityLiving.newInstance();
			
			Field a = Util.getField(PacketPlayOutSpawnEntityLiving, "a");
			a.setAccessible(true);
			
			id = v1_8.idCount++;
			a.set(packet, id);
			
			Field b = Util.getField(PacketPlayOutSpawnEntityLiving, "b");
			b.setAccessible(true);
			b.set(packet, (byte) EntityType.WITHER.getTypeId());
			
			Field c = Util.getField(PacketPlayOutSpawnEntityLiving, "c");
			c.setAccessible(true);
			c.set(packet, getXvel());

			Field d = Util.getField(PacketPlayOutSpawnEntityLiving, "d");
			d.setAccessible(true);
			d.set(packet, getYvel());
			
			Field e = Util.getField(PacketPlayOutSpawnEntityLiving, "e");
			e.setAccessible(true);
			e.set(packet, getZvel());
			
			Field f = Util.getField(PacketPlayOutSpawnEntityLiving, "f");
			f.setAccessible(true);
			f.set(packet, (byte) 0);
			
			Field g = Util.getField(PacketPlayOutSpawnEntityLiving, "g");
			g.setAccessible(true);
			g.set(packet, (byte) 0);
			
			Field h = Util.getField(PacketPlayOutSpawnEntityLiving, "h");
			h.setAccessible(true);
			h.set(packet, (byte) 0);
			
			Field i = Util.getField(PacketPlayOutSpawnEntityLiving, "i");
			i.setAccessible(true);
			i.set(packet, (byte) 0);
			
			Field j = Util.getField(PacketPlayOutSpawnEntityLiving, "j");
			j.setAccessible(true);
			j.set(packet, (byte) 0);
			
			Field k = Util.getField(PacketPlayOutSpawnEntityLiving, "k");
			k.setAccessible(true);
			k.set(packet, (byte) 0);
			
			Field l = Util.getField(PacketPlayOutSpawnEntityLiving, "l");
			l.setAccessible(true);
			l.set(packet, getWatcher());
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return packet;
	}

	@Override
	public Object getDestroyPacket() {
		Class<?> PacketPlayOutEntityDestroy = Util.getCraftClass("PacketPlayOutEntityDestroy");

		Object packet = null;
		try {
			packet = PacketPlayOutEntityDestroy.newInstance();
			Field a = PacketPlayOutEntityDestroy.getDeclaredField("a");
			a.setAccessible(true);
			a.set(packet, new int[] { id });
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return packet;
	}

	@Override
	public Object getMetaPacket(Object watcher) {
		Class<?> DataWatcher = Util.getCraftClass("DataWatcher");

		Class<?> PacketPlayOutEntityMetadata = Util.getCraftClass("PacketPlayOutEntityMetadata");

		Object packet = null;
		try {
			packet = PacketPlayOutEntityMetadata.getConstructor(new Class<?>[] { int.class, DataWatcher, boolean.class }).newInstance(id, watcher, true);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return packet;
	}

	@Override
	public Object getTeleportPacket(Location loc) {
		Class<?> PacketPlayOutEntityTeleport = Util.getCraftClass("PacketPlayOutEntityTeleport");
		Object packet = null;

		try {
			packet = PacketPlayOutEntityTeleport.getConstructor(new Class<?>[] { int.class, int.class, int.class, int.class, byte.class, byte.class, boolean.class }).newInstance(this.id, loc.getBlockX() * 32, loc.getBlockY() * 32, loc.getBlockZ() * 32, (byte) ((int) loc.getYaw() * 256 / 360), (byte) ((int) loc.getPitch() * 256 / 360), false);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return packet;
	}

	@Override
	public Object getWatcher() {
		Class<?> Entity = Util.getCraftClass("Entity");
		Class<?> DataWatcher = Util.getCraftClass("DataWatcher");

		Object watcher = null;
		try {
			watcher = DataWatcher.getConstructor(new Class<?>[] { Entity }).newInstance((Object) null);
			Method a = Util.getMethod(DataWatcher, "a", new Class<?>[] { int.class, Object.class });
			
			a.invoke(watcher, 0, (byte) 0x20);
			a.invoke(watcher, 2, name);
			a.invoke(watcher, 3, (byte) 1);
			a.invoke(watcher, 6, health);
			a.invoke(watcher, 8, (byte) 0);
			a.invoke(watcher, 10, name);
			a.invoke(watcher, 17, (int) 0);
			a.invoke(watcher, 18, (int) 0);
			a.invoke(watcher, 19, (int) 0);
			a.invoke(watcher, 20, (int) 881);

			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return watcher;
	}
}
