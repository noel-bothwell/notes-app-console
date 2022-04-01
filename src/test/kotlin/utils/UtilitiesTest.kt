package utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import utils.CategoryValidation.categories
import utils.CategoryValidation.isValidCategory
import utils.Validation.validRange


class UtilitiesTest {
    @Nested
    inner class validRange {
        @Test
        fun validRangeWorksWithPositiveTestNumbers() {
            Assertions.assertTrue(validRange(1, 1, 1))
            Assertions.assertTrue(validRange(1, 1, 2))
            Assertions.assertTrue(validRange(1, 0, 1))
            Assertions.assertTrue(validRange(1, 0, 2))
            Assertions.assertTrue(validRange(-1, -2, -1))
        }

        @Test
        fun validRangeWorksWithNegativeTestNumbers() {
            Assertions.assertFalse(validRange(1, 0, 0))
            Assertions.assertFalse(validRange(1, 1, 0))
            Assertions.assertFalse(validRange(1, 2, 1))
            Assertions.assertFalse(validRange(-1, -1, -2))
        }
    }

    @Nested
    inner class isValidCategory {
        @Test
        fun categoriesReturnsFullCategoriesSet() {
            Assertions.assertEquals(5, categories.size)
            Assertions.assertTrue(categories.contains("Home"))
            Assertions.assertTrue(categories.contains("College"))
            Assertions.assertTrue(categories.contains("Work"))
            Assertions.assertTrue(categories.contains("Gym"))
            Assertions.assertTrue(categories.contains("Food"))
            Assertions.assertFalse(categories.contains(""))
        }

        @Test
        fun isValidCategoryTrueWhenCategoryExists() {
            Assertions.assertTrue(isValidCategory("Home"))
            Assertions.assertTrue(isValidCategory("home"))
            Assertions.assertTrue(isValidCategory("HOME"))
            Assertions.assertTrue(isValidCategory("COLLEGE"))
            Assertions.assertTrue(isValidCategory("college"))
            Assertions.assertTrue(isValidCategory("College"))
            Assertions.assertTrue(isValidCategory("WORK"))
            Assertions.assertTrue(isValidCategory("work"))
            Assertions.assertTrue(isValidCategory("Work"))
            Assertions.assertTrue(isValidCategory("FOOD"))
            Assertions.assertTrue(isValidCategory("food"))
            Assertions.assertTrue(isValidCategory("Food"))
            Assertions.assertTrue(isValidCategory("GYM"))
            Assertions.assertTrue(isValidCategory("gym"))
            Assertions.assertTrue(isValidCategory("Gym"))
        }

        @Test
        fun isValidCategoryFalseWhenCategoryDoesNotExist() {
            Assertions.assertFalse(isValidCategory("Hom"))
            Assertions.assertFalse(isValidCategory("colllege"))
            Assertions.assertFalse(isValidCategory("wok"))
            Assertions.assertFalse(isValidCategory("gm"))
            Assertions.assertFalse(isValidCategory("foo"))
        }

    }
    }
