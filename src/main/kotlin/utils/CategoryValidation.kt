package utils

object CategoryValidation {
    // NOTE: JvmStatic annotation means that the categories variable is static i.e. we can reference it through the class

    @JvmStatic
    val categories = setOf("Home", "College", "Gym", "Work", "Food")

    @JvmStatic
    fun isValidCategory(categoryToCheck: String?): Boolean {
        for (category in categories) {
            if (category.equals(categoryToCheck, ignoreCase = true)) {
                return true
            }
        }
        return false
    }
}