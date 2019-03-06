
package com.json.transformer.util;

import com.json.transformer.model.DiscSpaceBean;
import com.json.transformer.model.StatusResponse;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 *
 * @author sgtomar
 */
public class TransformerUtil {

    public static long getMemoryUsages() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        long getTotalPhysicalMemorySize = 0;
        long getFreePhysicalMemorySize = 0;
        long perMemoryUsages = 0;

        for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getName().equals("getTotalPhysicalMemorySize")) {
                Object value;
                try {
                    value = method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    value = e;
                }
                getTotalPhysicalMemorySize = (Long) value;
            }
            if (method.getName().equals("getFreePhysicalMemorySize")) {
                Object value;
                try {
                    value = method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    value = e;
                }
                getFreePhysicalMemorySize = (Long) value;
            }
        }
        perMemoryUsages = (getTotalPhysicalMemorySize - getFreePhysicalMemorySize) * 100 / getTotalPhysicalMemorySize;
        return perMemoryUsages;
    }

    public static double getProcessCpuLoad() throws Exception {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
        AttributeList list = mbs.getAttributes(name, new String[]{"ProcessCpuLoad"});

        if (list.isEmpty()) {
            return Double.NaN;
        }

        Attribute att = (Attribute) list.get(0);
        Double value = (Double) att.getValue();

        // usually takes a couple of seconds before we get real values
        if (value == -1.0) {
            return Double.NaN;
        }

        return ((int) (value * 1000) / 10.0) * 10;

    }

    public static StatusResponse getSystemInfo() throws Exception {
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setMem_used_pct(TransformerUtil.getMemoryUsages());
        File[] drives = File.listRoots();
        if (drives != null && drives.length > 0) {
            List<DiscSpaceBean> discSpaceInfos = new ArrayList<DiscSpaceBean>();
            for (File aDrive : drives) {
                discSpaceInfos.add(new DiscSpaceBean(aDrive.toString(), String.valueOf(aDrive.getFreeSpace())));
            }
            statusResponse.setDisc_space_avail(discSpaceInfos);
        }
        statusResponse.setCpu_used_pct(TransformerUtil.getProcessCpuLoad());
        return statusResponse;
    }

}
