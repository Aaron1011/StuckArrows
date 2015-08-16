package com.aaron1011.stuckarrows;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClientProxy {

    private Field renderPlayerMap;
    private Field layers;

    public ClientProxy() {
        try {
            this.renderPlayerMap = RenderManager.class.getDeclaredField("field_178636_l");
            this.renderPlayerMap.setAccessible(true);

            this.layers = RendererLivingEntity.class.getDeclaredField("field_177097_h");
            this.layers.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("This shouldn't happen!", e);
        }
    }

    public void replaceLayerArrow() {
        try {
            Map<String, RenderPlayer> map = (Map) renderPlayerMap.get(Minecraft.getMinecraft().getRenderManager());
            for (RenderPlayer renderPlayer: map.values()) {
                List<LayerRenderer> renderers = (List) this.layers.get(renderPlayer);
                Iterator<LayerRenderer> iterator = renderers.iterator();

                boolean found = false;

                while (iterator.hasNext()) {
                    LayerRenderer renderer = iterator.next();
                    if (renderer instanceof LayerArrow) {
                        iterator.remove();
                        System.out.println("FOUND IT!");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Oh noes!");
                }

                renderers.add(new CustomLayerArrow(renderPlayer));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Something bad happened!", e);
        }
    }

}
