package com.example.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.*

enum class ProductCategory(val displayName: String, val tagline: String) {
    ALL("TODOS", "La colección completa del atelier"),
    VELAS("VELAS", "Cera de soja botánica vertida a mano"),
    DIFUSORES("DIFUSORES", "Varillas de ratán con aceites puros"),
    HOME_SPRAYS("HOME SPRAYS", "Brumas ambientales de impacto inmediato"),
    TEXTILES("TEXTILES", "Perfumería fina para linos y prendas"),
    SETS("SETS & BUNDLES", "Rituales completos con beneficio de atelier")
}

enum class OlfactoryFamily(val label: String, val description: String, val badgeColor: Color) {
    AMADERADO("Amaderado", "Maderas nobles, cortezas secas y resinas", MejunjeAmber),
    CITRICO("Cítrico", "Frescura vibrante de frutos al sol", MejunjeSoftMustard),
    FLORAL("Floral", "Pétalos silvestres y botánica nocturna", MejunjeSalmon),
    VERDE("Verde & Herbal", "Hierbas húmedas, tallos y bosque", MejunjeSage),
    ESPECIADO("Especiado", "Semillas tostadas, canelas y pimientas", MejunjeTerracotta),
    GOURMAND("Gourmand", "Vainillas maduras, haba tonka y miel", MejunjeAmber),
    FRESCO("Fresco & Acuático", "Lino recién lavado y bruma limpia", MejunjeOlive)
}

data class OlfactoryPyramid(
    val topNotes: List<String>,    // Salida (notas altas)
    val heartNotes: List<String>,  // Corazón (cuerpo del aroma)
    val baseNotes: List<String>    // Fondo (fijación y memoria)
)

data class Product(
    val id: String,
    val name: String,
    val category: ProductCategory,
    val aromaticFamily: OlfactoryFamily,
    val mainNotes: List<String>,
    val price: Int, // En Pesos Argentinos (ARS)
    val sizeVolume: String, // e.g. "250g · 50hs de quemado"
    val shortStory: String, // e.g. "un living de madera después de la lluvia."
    val poeticDescription: String, // Editorial copy
    val feelsLike: String, // "A qué huele": madera tibia, papel viejo y una ventana abierta
    val intensity: Int, // 1 to 5 scale
    val idealForRooms: List<String>, // Living, Dormitorio, Atelier, Baño, Entrada
    val pyramid: OlfactoryPyramid,
    val moodTags: List<String>, // Calma, Refugio, Noche Lenta, Concentración
    val companionProductIds: List<String> = emptyList(), // Cross-sell: "Para completar el ritual"
    val isFeatured: Boolean = false,
    val isBestseller: Boolean = false,
    val primaryColor: Color = MejunjeAmber,
    val secondaryColor: Color = MejunjeDeepGreen,
    val badge: String? = null
)

data class BundleItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val productsIncluded: List<String>,
    val originalPrice: Int,
    val bundlePrice: Int,
    val discountPercent: Int,
    val tag: String,
    val description: String,
    val accentColor: Color = MejunjeTerracotta
)

data class BotanicalIngredient(
    val id: String,
    val name: String,
    val originNote: String,
    val family: OlfactoryFamily,
    val description: String,
    val benefits: String,
    val matchingProducts: List<String>
)

data class MoodFilter(
    val id: String,
    val title: String,
    val subtitle: String,
    val quote: String,
    val color: Color
)

data class CartItem(
    val product: Product,
    val quantity: Int = 1,
    val isGiftWrapped: Boolean = false,
    val giftNote: String = ""
)

data class QuizStep(
    val questionNumber: Int,
    val title: String,
    val subtitle: String,
    val options: List<QuizOption>
)

data class QuizOption(
    val id: String,
    val label: String,
    val description: String,
    val matchingCategory: ProductCategory? = null,
    val matchingFamily: OlfactoryFamily? = null,
    val matchingMood: String? = null
)

data class QuizResult(
    val profileName: String,
    val profilePoem: String,
    val recommendedProducts: List<Product>,
    val advice: String
)
