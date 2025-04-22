package cn.luz.mml;

import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import java.lang.annotation.Annotation;
import java.util.function.Function;

/**
 * 工具类
 *
 * @author Luz
 * @since 2025-04-22 周二 上午11:19
 */
public class ClassUtil {
    /**
     * 获取类所在的包名
     *
     * @param classQualifiedName 类的全限定名
     * @return 上一级包名
     */
    public static String getPackageName(String classQualifiedName) {
        if (strIsEmpty(classQualifiedName)) {
            return classQualifiedName; // 处理空字符串或 null
        }
        String packageName = classQualifiedName;
        int lastDot = classQualifiedName.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = classQualifiedName.substring(0, lastDot);
        }
        return packageName;
    }

    /**
     * 根据某个全限定名获取项目根包名，根包名肢解依据是第三个点之前的字符，标准为com.example.yourmod
     *
     * @param classQualifiedName 某个倒霉蛋的全限定名
     * @return 根包名
     */
    public static String getRootPackageName(String classQualifiedName) {
        if (strIsEmpty(classQualifiedName)) {
            return classQualifiedName; // 处理空字符串或 null
        }

        int dotCount = 0;
        int thirdDotIndex = -1;

        // 遍历字符串，找到第三个点的位置
        for (int i = 0; i < classQualifiedName.length(); i++) {
            if (classQualifiedName.charAt(i) == '.') {
                dotCount++;
                if (dotCount == 3) {
                    thirdDotIndex = i;
                    break;
                }
            }
        }

        // 根据点的数量返回结果
        if (thirdDotIndex != -1) {
            return classQualifiedName.substring(0, thirdDotIndex);
        } else {
            return getPackageName(classQualifiedName);
        }
    }

    /**
     * 通过全限定名获取类名
     *
     * @param qualifiedName 全限定名
     * @return 类名
     */
    public static String getSimpleName(String qualifiedName) {
        return qualifiedName.substring(qualifiedName.lastIndexOf('.') + 1).replaceAll("\\$", ".");
    }

    /**
     * 替换字符串里最后出现的元素
     *
     * @param str         原字符串
     * @param regex       要被替换的元素，正则特殊符号需要转义
     * @param replacement 替代的元素
     * @return 替换后的字符串
     */
    public static String replaceLast(String str, String regex, String replacement) {
        return str.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
    }

    /**
     * 从注解中获取还未初始化的类名
     *
     * @param annotation 注解
     * @param sup        注解getter
     * @return 类全限定名
     */
    public static <T extends Annotation> String getClassQualifierNameFromAnnotation(T annotation, Function<T, String> sup) {
        try {
            return sup.apply(annotation);
        } catch (MirroredTypeException e) {
            TypeMirror typeMirror = e.getTypeMirror();
            return typeMirror.toString();
        }
    }


    /**
     * 大、小驼峰命名法转下划线命名法
     *
     * @param str 大、小驼峰命名法字符串
     * @return 下划线命名法字符串
     * @author Luz
     * @since 2024/7/24 下午5:00
     **/
    public static String toSnakeCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        // 使用正则表达式按照大写字符分割后再拼接
        return String.join("_", str.split("(?=[A-Z])")).toLowerCase();
    }

    /**
     * 字符串判空
     *
     * @param str 字符串
     * @return 是否为空字符串
     */
    public static boolean strIsEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
