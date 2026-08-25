package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.CardGiftcard
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MejunjeCatalog
import com.example.model.OlfactoryFamily
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.ui.components.*
import com.example.ui.theme.*
import com.example.viewmodel.MejunjeViewModel

@Composable
fun HomeScreen(
    viewModel: MejunjeViewModel,
    modifier: Modifier = Modifier
) {
    val activeTab by viewModel.activeTab.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val selectedMood by viewModel.selectedMood.collectAsState()
    val selectedFamily by viewModel.selectedFamily.collectAsState()
    val cartItems by viewModel.cartItems.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val newsletterEmail by viewModel.newsletterEmail.collectAsState()
    val isNewsletterSubscribed by viewModel.newsletterSubscribed.collectAsState()

    // Modals state
    val selectedProduct by viewModel.selectedProduct.collectAsState()
    val isCartOpen by viewModel.isCartOpen.collectAsState()
    val isSearchOpen by viewModel.isSearchOpen.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isQuizOpen by viewModel.isQuizOpen.collectAsState()
    val quizStep by viewModel.quizStep.collectAsState()
    val quizResult by viewModel.quizResult.collectAsState()
    val isGiftExperienceOpen by viewModel.isGiftExperienceOpen.collectAsState()
    val giftOccasion by viewModel.giftOccasion.collectAsState()
    val giftBudget by viewModel.giftBudget.collectAsState()
    val giftRecipient by viewModel.giftRecipientType.collectAsState()
    val giftNote by viewModel.giftNote.collectAsState()
    val isCheckoutOpen by viewModel.isCheckoutOpen.collectAsState()
    val checkoutStep by viewModel.checkoutStep.collectAsState()
    val checkoutName by viewModel.checkoutName.collectAsState()
    val checkoutEmail by viewModel.checkoutEmail.collectAsState()
    val checkoutPhone by viewModel.checkoutPhone.collectAsState()
    val checkoutAddress by viewModel.checkoutAddress.collectAsState()
    val checkoutCity by viewModel.checkoutCity.collectAsState()
    val checkoutShippingMethod by viewModel.checkoutShippingMethod.collectAsState()
    val checkoutPaymentMethod by viewModel.checkoutPaymentMethod.collectAsState()
    val confirmedOrderNumber by viewModel.confirmedOrderNumber.collectAsState()
    val snackbarMessage by viewModel.snackbarMessage.collectAsState()

    val snackbarHostState = androidx.compose.runtime.remember { SnackbarHostState() }

    androidx.compose.runtime.LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearSnackbar()
        }
    }

    Scaffold(
        topBar = {
            MejunjeHeader(
                activeTab = activeTab,
                onTabSelected = { viewModel.setActiveTab(it) },
                cartCount = cartItems.sumOf { it.quantity },
                favoritesCount = favorites.size,
                onSearchClick = { viewModel.setSearchOpen(true) },
                onCartClick = { viewModel.setCartOpen(true) },
                onFavoritesClick = {
                    viewModel.showSnackbar("${favorites.size} aromas guardados en tu lista de deseos.")
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    containerColor = MejunjeCharcoal,
                    contentColor = MejunjeWarmWhite,
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = data.visuals.message,
                        fontFamily = TypewriterFamily,
                        fontSize = 11.5.sp
                    )
                }
            }
        },
        containerColor = MejunjeWarmWhite,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MejunjeWarmWhite)
        ) {
            when (activeTab) {
                "TIENDA" -> {
                    // Editorial Hero Cover
                    item {
                        HeroSection(
                            onExploreClick = { viewModel.selectCategory(ProductCategory.ALL) },
                            onFindScentClick = { viewModel.openQuiz() }
                        )
                    }

                    // Mood Selector ("¿QUÉ QUERÉS SENTIR?")
                    item {
                        MoodDiscoverySection(
                            selectedMood = selectedMood,
                            onMoodSelected = { viewModel.selectMood(it) }
                        )
                    }

                    // Filtered or Featured Products List
                    item {
                        val filteredList = MejunjeCatalog.products.filter { p ->
                            (selectedCategory == ProductCategory.ALL || p.category == selectedCategory) &&
                            (selectedMood == null || p.moodTags.contains(selectedMood)) &&
                            (selectedFamily == null || p.aromaticFamily == selectedFamily)
                        }

                        FeaturedProductsSection(
                            products = filteredList,
                            favorites = favorites,
                            onProductClick = { viewModel.selectProduct(it) },
                            onAddToCart = { viewModel.addToCart(it) },
                            onToggleFavorite = { viewModel.toggleFavorite(it) }
                        )
                    }

                    // Collections
                    item {
                        CollectionsSection(
                            onSelectCategory = { cat ->
                                viewModel.selectCategory(cat)
                                viewModel.setActiveTab("COLECCIONES")
                            }
                        )
                    }

                    // Bundles / Mejunjes Armados
                    item {
                        BundlesSection(
                            onAddBundle = { bundle -> viewModel.addBundleToCart(bundle) }
                        )
                    }

                    // Botanicals
                    item {
                        BotanicalsSection()
                    }

                    // Atelier Story & Values
                    item {
                        AtelierStorySection()
                    }

                    // Reviews, Newsletter & Footer
                    item {
                        ReviewsAndNewsletterSection(
                            email = newsletterEmail,
                            onEmailChange = { viewModel.setNewsletterEmail(it) },
                            onSubscribe = { viewModel.subscribeNewsletter() },
                            isSubscribed = isNewsletterSubscribed
                        )
                    }
                }

                "AROMAS" -> {
                    // Aromas / Olfactory Families View
                    item {
                        AromasCatalogHeader(
                            selectedFamily = selectedFamily,
                            onSelectFamily = { viewModel.selectFamily(it) }
                        )
                    }

                    item {
                        val familyProducts = if (selectedFamily == null) {
                            MejunjeCatalog.products
                        } else {
                            MejunjeCatalog.products.filter { it.aromaticFamily == selectedFamily }
                        }

                        FeaturedProductsSection(
                            products = familyProducts,
                            favorites = favorites,
                            onProductClick = { viewModel.selectProduct(it) },
                            onAddToCart = { viewModel.addToCart(it) },
                            onToggleFavorite = { viewModel.toggleFavorite(it) }
                        )
                    }

                    item {
                        BotanicalsSection()
                    }
                }

                "COLECCIONES" -> {
                    // Colecciones (Velas, Difusores, Sprays, Textiles, Sets)
                    item {
                        CategoryCatalogHeader(
                            selectedCategory = selectedCategory,
                            onSelectCategory = { viewModel.selectCategory(it) }
                        )
                    }

                    item {
                        val catProducts = if (selectedCategory == ProductCategory.ALL) {
                            MejunjeCatalog.products
                        } else {
                            MejunjeCatalog.products.filter { it.category == selectedCategory }
                        }

                        FeaturedProductsSection(
                            products = catProducts,
                            favorites = favorites,
                            onProductClick = { viewModel.selectProduct(it) },
                            onAddToCart = { viewModel.addToCart(it) },
                            onToggleFavorite = { viewModel.toggleFavorite(it) }
                        )
                    }

                    item {
                        BundlesSection(
                            onAddBundle = { bundle -> viewModel.addBundleToCart(bundle) }
                        )
                    }
                }

                "REGALOS" -> {
                    // Gifting Special Experience Hub
                    item {
                        GiftingBannerHero(
                            onOpenGiftExperience = { viewModel.openGiftExperience() }
                        )
                    }

                    item {
                        BundlesSection(
                            onAddBundle = { bundle -> viewModel.addBundleToCart(bundle) }
                        )
                    }

                    item {
                        val giftProducts = MejunjeCatalog.products.filter { it.moodTags.contains("Regalo") || it.category == ProductCategory.SETS }
                        FeaturedProductsSection(
                            products = giftProducts.ifEmpty { MejunjeCatalog.products.take(4) },
                            favorites = favorites,
                            onProductClick = { viewModel.selectProduct(it) },
                            onAddToCart = { viewModel.addToCart(it) },
                            onToggleFavorite = { viewModel.toggleFavorite(it) }
                        )
                    }
                }

                "ATELIER" -> {
                    // Atelier Story, Process & Reportage
                    item {
                        AtelierStorySection()
                    }

                    item {
                        BotanicalsSection()
                    }

                    item {
                        ReviewsAndNewsletterSection(
                            email = newsletterEmail,
                            onEmailChange = { viewModel.setNewsletterEmail(it) },
                            onSubscribe = { viewModel.subscribeNewsletter() },
                            isSubscribed = isNewsletterSubscribed
                        )
                    }
                }
            }
        }
    }

    // Modal: Product Detail PDP
    selectedProduct?.let { product ->
        ProductDetailModal(
            product = product,
            isFavorite = favorites.contains(product.id),
            onDismiss = { viewModel.selectProduct(null) },
            onAddToCart = { prod, qty, giftWrap, note ->
                viewModel.addToCart(prod, qty, giftWrap, note)
            },
            onToggleFavorite = { viewModel.toggleFavorite(product.id) },
            onSelectCompanionProduct = { comp -> viewModel.selectProduct(comp) }
        )
    }

    // Modal: Cart Drawer
    if (isCartOpen) {
        CartDrawerSheet(
            cartItems = cartItems,
            subtotal = viewModel.cartSubtotal,
            giftPackagingTotal = viewModel.cartGiftWrappingTotal,
            total = viewModel.cartTotal,
            onDismiss = { viewModel.setCartOpen(false) },
            onUpdateQuantity = { id, delta -> viewModel.updateCartItemQuantity(id, delta) },
            onRemoveItem = { id -> viewModel.removeFromCart(id) },
            onToggleGiftWrap = { id -> viewModel.toggleItemGiftWrap(id) },
            onCheckout = { viewModel.startCheckout() }
        )
    }

    // Modal: Search Overlay
    if (isSearchOpen) {
        SearchOverlayModal(
            query = searchQuery,
            onQueryChange = { viewModel.setSearchQuery(it) },
            onProductClick = { viewModel.selectProduct(it) },
            onDismiss = { viewModel.setSearchOpen(false) }
        )
    }

    // Modal: Quiz
    if (isQuizOpen) {
        QuizModal(
            step = quizStep,
            result = quizResult,
            onAnswer = { stepIdx, ans -> viewModel.answerQuizStep(stepIdx, ans) },
            onDismiss = { viewModel.closeQuiz() },
            onSelectProduct = { prod -> viewModel.selectProduct(prod) },
            onAddToCart = { prod -> viewModel.addToCart(prod) }
        )
    }

    // Modal: Gift Experience
    if (isGiftExperienceOpen) {
        GiftExperienceModal(
            selectedOccasion = giftOccasion,
            selectedBudget = giftBudget,
            selectedRecipient = giftRecipient,
            customNote = giftNote,
            onOccasionChange = { viewModel.setGiftOccasion(it) },
            onBudgetChange = { viewModel.setGiftBudget(it) },
            onRecipientChange = { viewModel.setGiftRecipientType(it) },
            onNoteChange = { viewModel.setGiftNote(it) },
            onAddGiftToCart = { prod -> viewModel.addGiftSelectionToCart(prod) },
            onDismiss = { viewModel.closeGiftExperience() }
        )
    }

    // Modal: Checkout
    if (isCheckoutOpen) {
        CheckoutModal(
            step = checkoutStep,
            name = checkoutName,
            email = checkoutEmail,
            phone = checkoutPhone,
            address = checkoutAddress,
            city = checkoutCity,
            shippingMethod = checkoutShippingMethod,
            paymentMethod = checkoutPaymentMethod,
            orderNumber = confirmedOrderNumber,
            total = viewModel.cartTotal,
            onStepChange = { viewModel.setCheckoutStep(it) },
            onDataChange = { n, e, p, a, c -> viewModel.updateCheckoutData(n, e, p, a, c) },
            onShippingChange = { viewModel.setShippingMethod(it) },
            onPaymentChange = { viewModel.setPaymentMethod(it) },
            onCompleteOrder = { viewModel.completeOrder() },
            onDismiss = { viewModel.closeCheckout() }
        )
    }
}

@Composable
private fun AromasCatalogHeader(
    selectedFamily: OlfactoryFamily?,
    onSelectFamily: (OlfactoryFamily?) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
        Text(
            text = "FAMILIAS OLFATIVAS",
            fontFamily = TypewriterFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            color = MejunjeCharcoal
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Explorá el repertorio botánico clasificado por sus acordes predominantes.",
            fontFamily = EditorialSansFamily,
            fontSize = 13.sp,
            color = MejunjeTextSecondary
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val allFamilies = listOf<OlfactoryFamily?>(null) + OlfactoryFamily.values().toList()
            allFamilies.forEach { fam ->
                val isSelected = fam == selectedFamily
                val label = fam?.label ?: "TODAS"
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) MejunjeCharcoal else MejunjeWhite)
                        .border(0.8.dp, if (isSelected) MejunjeCharcoal else MejunjeBorder, RoundedCornerShape(20.dp))
                        .clickable { onSelectFamily(fam) }
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = label.uppercase(),
                        fontFamily = TypewriterFamily,
                        fontSize = 11.sp,
                        color = if (isSelected) MejunjeWarmWhite else MejunjeCharcoal
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryCatalogHeader(
    selectedCategory: ProductCategory,
    onSelectCategory: (ProductCategory) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
        Text(
            text = "COLECCIONES",
            fontFamily = TypewriterFamily,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            color = MejunjeCharcoal
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Objetos de aroma fabricados a mano en Buenos Aires.",
            fontFamily = EditorialSansFamily,
            fontSize = 13.sp,
            color = MejunjeTextSecondary
        )

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ProductCategory.values().forEach { cat ->
                val isSelected = cat == selectedCategory
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) MejunjeCharcoal else MejunjeWhite)
                        .border(0.8.dp, if (isSelected) MejunjeCharcoal else MejunjeBorder, RoundedCornerShape(20.dp))
                        .clickable { onSelectCategory(cat) }
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = cat.displayName.uppercase(),
                        fontFamily = TypewriterFamily,
                        fontSize = 11.sp,
                        color = if (isSelected) MejunjeWarmWhite else MejunjeCharcoal
                    )
                }
            }
        }
    }
}

@Composable
private fun GiftingBannerHero(
    onOpenGiftExperience: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MejunjePaper)
            .border(1.dp, MejunjeBorder, RoundedCornerShape(10.dp))
            .padding(20.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Outlined.CardGiftcard,
                contentDescription = null,
                tint = MejunjeTerracotta,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "REGALAR MEJUNJE",
                fontFamily = TypewriterFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp,
                color = MejunjeCharcoal
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Un aroma es un recuerdo que dura para siempre. Enviamos tu regalo listo para abrir, con caja botánica, sellado en lacre y carta escrita en máquina de escribir.",
                fontFamily = EditorialSansFamily,
                fontSize = 13.sp,
                lineHeight = 19.sp,
                color = MejunjeTextSecondary,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onOpenGiftExperience,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MejunjeCharcoal,
                    contentColor = MejunjeWarmWhite
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.height(44.dp)
            ) {
                Text(
                    text = "PERSONALIZAR REGALO",
                    fontFamily = TypewriterFamily,
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.4.sp
                )
            }
        }
    }
}
