package net.scarletontv.saxophone.compat;

import eu.midnightdust.lib.config.MidnightConfig;

public class SaxophoneConfig extends MidnightConfig {
    private static final String config = "config";

    @Entry(category = config)
    public static int monolithTicks = 80;
}
