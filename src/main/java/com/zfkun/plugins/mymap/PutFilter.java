package com.zfkun.plugins.mymap;

import com.janetfilter.core.commons.DebugInfo;
import com.janetfilter.core.enums.RuleType;
import com.janetfilter.core.models.FilterRule;
import com.janetfilter.core.plugin.PluginConfig;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PutFilter {
    private static Map<Object, Object> map;

    public static void setRules(PluginConfig config) {
        map = new HashMap();
        Iterator<FilterRule> var1 = config.getBySection("MyMap").iterator();

        while(var1.hasNext()) {
            FilterRule rule = (FilterRule)var1.next();
            if (rule.getType() == RuleType.EQUAL) {
                String[] sections = rule.getRule().split("->", 2);
                if (2 != sections.length) {
                    DebugInfo.output("Invalid record: " + rule + ", skipped.");
                } else {
                    map.put(sections[0], sections[1]);
                }
            }
        }

    }

    public static Object testPut(Object k, Object v) {
        if (null == k) {
            return v;
        } else {
            return map.containsKey(k) ? map.get(k) : v;
        }
    }
}
