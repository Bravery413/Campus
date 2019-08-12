package com.gdufe.campus.utils;


import java.util.List;

public class BeanUtils {
    public static boolean copyProperties(Object dest, Object src) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, src);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static <T1, T2> void copyProperties(final List<T1> origs, final List<T2> dests, Class<T1> origsElementTpe, Class<T2> destElementTpe) {
        if (origs == null || dests == null) {
            return;
        }
        if (dests.size() != 0) {
            //防止目标对象被覆盖，要求必须长度为零
            throw new RuntimeException("目标对象存在值");
        }
        try {
            for (T1 orig : origs) {
                T2 t = destElementTpe.newInstance();
                dests.add(t);
                copyProperties(orig, t);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
