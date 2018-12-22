package br.com.redcode.easyvalidation

fun isAllValid(vararg objects: Any?) = isAnyNullOrEmpty(objects).not()

fun isAnyNullOrEmpty(objects: Array<*>): Boolean {
    if (isNullOrEmpty(objects).not()) {
        objects.forEach { obj ->
            if (isNullOrEmpty(obj)) {
                return true
            }
        }
        return false
    }
    return true
}

fun isNullOrEmpty(obj: Any?): Boolean {
    if (obj != null) {
        return when (obj) {
            is String -> obj.isEmpty()
            is List<*> -> obj.isEmpty()
            is Map<*, *> -> obj.isEmpty()
            else -> false
        }
    }

    return true
}