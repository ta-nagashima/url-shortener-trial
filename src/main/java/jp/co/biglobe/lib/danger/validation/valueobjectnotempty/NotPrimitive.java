package jp.co.biglobe.lib.danger.validation.valueobjectnotempty;

import java.util.Arrays;
import java.util.List;

class NotPrimitive {

    private NotPrimitive() {
    }

    static void verify(Class propertyType) {

        List<String> primitiveList = Arrays.asList("boolean", "byte", "char",
                "short", "int", "long", "float", "double");

        if (primitiveList.contains(propertyType.getName())) {
            throw new UnsupportedOperationException("Not Use Primitive Type.");
        }
    }
}
