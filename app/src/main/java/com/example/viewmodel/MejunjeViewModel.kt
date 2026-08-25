package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MejunjeViewModel : ViewModel() {

    // Active Navigation Tab: "TIENDA", "AROMAS", "COLECCIONES", "REGALOS", "ATELIER"
    private val _activeTab = MutableStateFlow("TIENDA")
    val activeTab: StateFlow<String> = _activeTab.asStateFlow()

    // Filter by Category
    private val _selectedCategory = MutableStateFlow(ProductCategory.ALL)
    val selectedCategory: StateFlow<ProductCategory> = _selectedCategory.asStateFlow()

    // Filter by Mood / Emotion
    private val _selectedMood = MutableStateFlow<String?>(null)
    val selectedMood: StateFlow<String?> = _selectedMood.asStateFlow()

    // Filter by Olfactory Family
    private val _selectedFamily = MutableStateFlow<OlfactoryFamily?>(null)
    val selectedFamily: StateFlow<OlfactoryFamily?> = _selectedFamily.asStateFlow()

    // Product Detail
    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

    // Cart State
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    private val _isCartOpen = MutableStateFlow(false)
    val isCartOpen: StateFlow<Boolean> = _isCartOpen.asStateFlow()

    // Search
    private val _isSearchOpen = MutableStateFlow(false)
    val isSearchOpen: StateFlow<Boolean> = _isSearchOpen.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Favorites
    private val _favorites = MutableStateFlow<Set<String>>(setOf("prod_vela_ambar_madera"))
    val favorites: StateFlow<Set<String>> = _favorites.asStateFlow()

    // "Descubrí tu aroma" Quiz State
    private val _isQuizOpen = MutableStateFlow(false)
    val isQuizOpen: StateFlow<Boolean> = _isQuizOpen.asStateFlow()

    private val _quizStep = MutableStateFlow(0)
    val quizStep: StateFlow<Int> = _quizStep.asStateFlow()

    private val _quizAnswers = MutableStateFlow<Map<Int, String>>(emptyMap())
    val quizAnswers: StateFlow<Map<Int, String>> = _quizAnswers.asStateFlow()

    private val _quizResult = MutableStateFlow<QuizResult?>(null)
    val quizResult: StateFlow<QuizResult?> = _quizResult.asStateFlow()

    // "Regalar Mejunje" Experience State
    private val _isGiftExperienceOpen = MutableStateFlow(false)
    val isGiftExperienceOpen: StateFlow<Boolean> = _isGiftExperienceOpen.asStateFlow()

    private val _giftOccasion = MutableStateFlow("Cumpleaños")
    val giftOccasion: StateFlow<String> = _giftOccasion.asStateFlow()

    private val _giftBudget = MutableStateFlow("Hasta $25.000")
    val giftBudget: StateFlow<String> = _giftBudget.asStateFlow()

    private val _giftRecipientType = MutableStateFlow("Amiga")
    val giftRecipientType: StateFlow<String> = _giftRecipientType.asStateFlow()

    private val _giftNote = MutableStateFlow("Que este aroma te recuerde siempre la pausa que te merecés. Con mucho cariño.")
    val giftNote: StateFlow<String> = _giftNote.asStateFlow()

    // Checkout Flow State
    private val _isCheckoutOpen = MutableStateFlow(false)
    val isCheckoutOpen: StateFlow<Boolean> = _isCheckoutOpen.asStateFlow()

    private val _checkoutStep = MutableStateFlow(1) // 1: Datos, 2: Entrega, 3: Pago, 4: Confirmación
    val checkoutStep: StateFlow<Int> = _checkoutStep.asStateFlow()

    private val _checkoutName = MutableStateFlow("Camila Martínez")
    val checkoutName: StateFlow<String> = _checkoutName.asStateFlow()

    private val _checkoutEmail = MutableStateFlow("camila@ejemplo.com.ar")
    val checkoutEmail: StateFlow<String> = _checkoutEmail.asStateFlow()

    private val _checkoutPhone = MutableStateFlow("+54 9 11 4589-2341")
    val checkoutPhone: StateFlow<String> = _checkoutPhone.asStateFlow()

    private val _checkoutAddress = MutableStateFlow("Gurruchaga 1824, Palermo")
    val checkoutAddress: StateFlow<String> = _checkoutAddress.asStateFlow()

    private val _checkoutCity = MutableStateFlow("Buenos Aires, CABA")
    val checkoutCity: StateFlow<String> = _checkoutCity.asStateFlow()

    private val _checkoutShippingMethod = MutableStateFlow("Envío Express Atelier CABA (Entrega en 24hs)")
    val checkoutShippingMethod: StateFlow<String> = _checkoutShippingMethod.asStateFlow()

    private val _checkoutPaymentMethod = MutableStateFlow("Transferencia Bancaria Atelier (-10% OFF)")
    val checkoutPaymentMethod: StateFlow<String> = _checkoutPaymentMethod.asStateFlow()

    private val _confirmedOrderNumber = MutableStateFlow("MJ-84920")
    val confirmedOrderNumber: StateFlow<String> = _confirmedOrderNumber.asStateFlow()

    // Newsletter State
    private val _newsletterEmail = MutableStateFlow("")
    val newsletterEmail: StateFlow<String> = _newsletterEmail.asStateFlow()

    private val _newsletterSubscribed = MutableStateFlow(false)
    val newsletterSubscribed: StateFlow<Boolean> = _newsletterSubscribed.asStateFlow()

    // Feedback Toast / SnackBar
    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    // Init with initial cart item for visual excitement
    init {
        val initialProduct = MejunjeCatalog.products.firstOrNull()
        if (initialProduct != null) {
            _cartItems.value = listOf(
                CartItem(
                    product = initialProduct,
                    quantity = 1,
                    isGiftWrapped = true,
                    giftNote = "Para que la casa huela a madera y lluvia."
                )
            )
        }
    }

    // Navigation & Tabs
    fun setActiveTab(tab: String) {
        _activeTab.value = tab
        if (tab == "COLECCIONES") {
            _selectedCategory.value = ProductCategory.ALL
            _selectedMood.value = null
        }
    }

    // Product Selection
    fun selectProduct(product: Product?) {
        _selectedProduct.value = product
    }

    // Categories and Filters
    fun selectCategory(category: ProductCategory) {
        _selectedCategory.value = category
        _selectedMood.value = null
        _selectedFamily.value = null
    }

    fun selectMood(mood: String?) {
        _selectedMood.value = mood
    }

    fun selectFamily(family: OlfactoryFamily?) {
        _selectedFamily.value = family
    }

    fun clearAllFilters() {
        _selectedCategory.value = ProductCategory.ALL
        _selectedMood.value = null
        _selectedFamily.value = null
        _searchQuery.value = ""
    }

    // Cart Actions
    fun addToCart(product: Product, quantity: Int = 1, giftWrapped: Boolean = false, note: String = "") {
        _cartItems.update { current ->
            val existingIndex = current.indexOfFirst { it.product.id == product.id }
            if (existingIndex >= 0) {
                current.mapIndexed { index, item ->
                    if (index == existingIndex) item.copy(quantity = item.quantity + quantity) else item
                }
            } else {
                current + CartItem(product = product, quantity = quantity, isGiftWrapped = giftWrapped, giftNote = note)
            }
        }
        showSnackbar("“${product.name}” se sumó a tu mejunje.")
    }

    fun addBundleToCart(bundle: BundleItem) {
        val matchingProduct = MejunjeCatalog.products.find { it.category == ProductCategory.SETS } 
            ?: MejunjeCatalog.products.first()
        addToCart(
            product = matchingProduct.copy(
                id = bundle.id,
                name = bundle.title,
                price = bundle.bundlePrice,
                shortStory = bundle.subtitle,
                poeticDescription = bundle.description,
                category = ProductCategory.SETS
            ),
            quantity = 1,
            giftWrapped = true,
            note = "Set especial: ${bundle.productsIncluded.joinToString(" + ")}"
        )
    }

    fun updateCartItemQuantity(productId: String, delta: Int) {
        _cartItems.update { current ->
            current.mapNotNull { item ->
                if (item.product.id == productId) {
                    val newQty = item.quantity + delta
                    if (newQty > 0) item.copy(quantity = newQty) else null
                } else item
            }
        }
    }

    fun toggleItemGiftWrap(productId: String) {
        _cartItems.update { current ->
            current.map { item ->
                if (item.product.id == productId) item.copy(isGiftWrapped = !item.isGiftWrapped) else item
            }
        }
    }

    fun removeFromCart(productId: String) {
        _cartItems.update { current -> current.filter { it.product.id != productId } }
    }

    fun setCartOpen(open: Boolean) {
        _isCartOpen.value = open
    }

    // Search Actions
    fun setSearchOpen(open: Boolean) {
        _isSearchOpen.value = open
        if (!open) _searchQuery.value = ""
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // Favorites
    fun toggleFavorite(productId: String) {
        _favorites.update { current ->
            if (current.contains(productId)) {
                current - productId
            } else {
                current + productId
            }
        }
    }

    // Quiz Actions
    fun openQuiz() {
        _isQuizOpen.value = true
        _quizStep.value = 0
        _quizAnswers.value = emptyMap()
        _quizResult.value = null
    }

    fun closeQuiz() {
        _isQuizOpen.value = false
    }

    fun answerQuizStep(stepIndex: Int, answer: String) {
        val updated = _quizAnswers.value.toMutableMap()
        updated[stepIndex] = answer
        _quizAnswers.value = updated

        if (stepIndex < 3) {
            _quizStep.value = stepIndex + 1
        } else {
            // Compute Result
            computeQuizResult(updated)
        }
    }

    private fun computeQuizResult(answers: Map<Int, String>) {
        val moodAnswer = answers[1] ?: "Calma"
        val familyAnswer = answers[2] ?: "Amaderado"

        val matchingProducts = MejunjeCatalog.products.filter { p ->
            p.aromaticFamily.label.contains(familyAnswer, ignoreCase = true) ||
            p.moodTags.any { it.contains(moodAnswer, ignoreCase = true) }
        }.take(3).ifEmpty { MejunjeCatalog.products.take(3) }

        _quizResult.value = QuizResult(
            profileName = "Tu Mejunje: $moodAnswer & Notas de $familyAnswer",
            profilePoem = "Buscás que los espacios respiren con vos. Un equilibrio de luz suave, notas que abrigan y texturas botánicas honestas.",
            recommendedProducts = matchingProducts,
            advice = "Te recomendamos encender la vela en la última hora de la tarde y complementar con el spray ambiental para generar capas olfativas duraderas."
        )
    }

    // Gifting
    fun openGiftExperience() {
        _isGiftExperienceOpen.value = true
    }

    fun closeGiftExperience() {
        _isGiftExperienceOpen.value = false
    }

    fun setGiftOccasion(occasion: String) { _giftOccasion.value = occasion }
    fun setGiftBudget(budget: String) { _giftBudget.value = budget }
    fun setGiftRecipientType(recipient: String) { _giftRecipientType.value = recipient }
    fun setGiftNote(note: String) { _giftNote.value = note }

    fun addGiftSelectionToCart(selectedProduct: Product) {
        addToCart(
            product = selectedProduct,
            quantity = 1,
            giftWrapped = true,
            note = "Regalo ${_giftOccasion.value} para ${_giftRecipientType.value}: “${_giftNote.value}”"
        )
        _isGiftExperienceOpen.value = false
        _isCartOpen.value = true
    }

    // Checkout Flow
    fun startCheckout() {
        _isCartOpen.value = false
        _checkoutStep.value = 1
        _isCheckoutOpen.value = true
    }

    fun closeCheckout() {
        _isCheckoutOpen.value = false
    }

    fun setCheckoutStep(step: Int) { _checkoutStep.value = step }
    fun updateCheckoutData(name: String, email: String, phone: String, address: String, city: String) {
        _checkoutName.value = name
        _checkoutEmail.value = email
        _checkoutPhone.value = phone
        _checkoutAddress.value = address
        _checkoutCity.value = city
    }
    fun setShippingMethod(method: String) { _checkoutShippingMethod.value = method }
    fun setPaymentMethod(method: String) { _checkoutPaymentMethod.value = method }

    fun completeOrder() {
        val randomOrder = "MJ-${(10000..99999).random()}"
        _confirmedOrderNumber.value = randomOrder
        _checkoutStep.value = 4
        _cartItems.value = emptyList() // clear cart
    }

    // Newsletter
    fun setNewsletterEmail(email: String) {
        _newsletterEmail.value = email
    }

    fun subscribeNewsletter() {
        if (_newsletterEmail.value.isNotBlank() && _newsletterEmail.value.contains("@")) {
            _newsletterSubscribed.value = true
            showSnackbar("Te damos la bienvenida a las Cartas del Atelier.")
        }
    }

    // SnackBar
    fun showSnackbar(msg: String) {
        _snackbarMessage.value = msg
    }

    fun clearSnackbar() {
        _snackbarMessage.value = null
    }

    // Subtotals calculations
    val cartSubtotal: Int
        get() = _cartItems.value.sumOf { it.product.price * it.quantity }

    val cartGiftWrappingTotal: Int
        get() = _cartItems.value.count { it.isGiftWrapped } * 1200 // $1.200 por packaging artesanal

    val cartTotal: Int
        get() = cartSubtotal + cartGiftWrappingTotal
}
