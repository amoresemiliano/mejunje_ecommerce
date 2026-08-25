package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.ui.theme.*

@Composable
fun FeaturedProductsSection(
    products: List<Product>,
    favorites: Set<String>,
    onProductClick: (Product) -> Unit,
    onAddToCart: (Product) -> Unit,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        // Section Title & Editorial Subtitle
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text(
                    text = "SELECCIÓN DEL ATELIER",
                    fontFamily = TypewriterFamily,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    color = MejunjeCharcoal
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Nuestras fórmulas y objetos aromáticos más queridos.",
                    fontFamily = EditorialSansFamily,
                    fontSize = 13.sp,
                    color = MejunjeTextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Hero Protagonist Product (First featured item)
        val heroProduct = products.firstOrNull { it.isFeatured } ?: products.firstOrNull()
        if (heroProduct != null) {
            HeroProductCard(
                product = heroProduct,
                isFavorite = favorites.contains(heroProduct.id),
                onProductClick = { onProductClick(heroProduct) },
                onAddToCart = { onAddToCart(heroProduct) },
                onToggleFavorite = { onToggleFavorite(heroProduct.id) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Secondary Products in 2-Column Editorial Grid
        val remainingProducts = products.filter { it.id != heroProduct?.id }
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            remainingProducts.chunked(2).forEach { rowPair ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowPair.forEach { product ->
                        Box(modifier = Modifier.weight(1f)) {
                            SecondaryProductCard(
                                product = product,
                                isFavorite = favorites.contains(product.id),
                                onProductClick = { onProductClick(product) },
                                onAddToCart = { onAddToCart(product) },
                                onToggleFavorite = { onToggleFavorite(product.id) }
                            )
                        }
                    }
                    if (rowPair.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun HeroProductCard(
    product: Product,
    isFavorite: Boolean,
    onProductClick: () -> Unit,
    onAddToCart: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, MejunjeBorder, RoundedCornerShape(10.dp))
            .clickable(onClick = onProductClick)
            .testTag("hero_product_card_${product.id}"),
        colors = CardDefaults.cardColors(containerColor = MejunjeWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Artwork with badge & favorite button overlay
            Box {
                ProductArtwork(product = product, height = 210.dp)

                // Badge top-left
                product.badge?.let { badgeText ->
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.TopStart)
                            .clip(RoundedCornerShape(3.dp))
                            .background(MejunjeCharcoal)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = badgeText,
                            fontFamily = TypewriterFamily,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp,
                            color = MejunjeWarmWhite
                        )
                    }
                }

                // Favorite button top-right
                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier
                        .padding(6.dp)
                        .align(Alignment.TopEnd)
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(MejunjeWhite.copy(alpha = 0.9f))
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorito",
                        tint = if (isFavorite) MejunjeTerracotta else MejunjeCharcoal,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Olfactory Family & Main Notes Tag
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = product.aromaticFamily.label.uppercase(),
                    fontFamily = TypewriterFamily,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = product.primaryColor,
                    letterSpacing = 1.2.sp
                )
                Text(
                    text = "·",
                    fontFamily = TypewriterFamily,
                    fontSize = 10.sp,
                    color = MejunjeTextMuted
                )
                Text(
                    text = product.mainNotes.joinToString(" · "),
                    fontFamily = TypewriterFamily,
                    fontSize = 10.5.sp,
                    color = MejunjeTextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Product Name
            Text(
                text = product.name,
                fontFamily = TypewriterFamily,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = MejunjeCharcoal,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Narrative Quote
            Text(
                text = "“${product.shortStory}”",
                fontFamily = EditorialSansFamily,
                fontSize = 13.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = MejunjeTextSecondary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Price & CTAs
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "$${String.format("%,d", product.price).replace(',', '.')}",
                        fontFamily = TypewriterFamily,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = MejunjeCharcoal
                    )
                    Text(
                        text = product.sizeVolume,
                        fontFamily = EditorialSansFamily,
                        fontSize = 11.sp,
                        color = MejunjeTextMuted
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = onProductClick,
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MejunjeCharcoal),
                        border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.linearGradient(listOf(MejunjeBorder, MejunjeBorder))),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.height(38.dp)
                    ) {
                        Text(
                            text = "VER MEJUNJE",
                            fontFamily = TypewriterFamily,
                            fontSize = 10.5.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.sp
                        )
                    }

                    Button(
                        onClick = onAddToCart,
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MejunjeCharcoal,
                            contentColor = MejunjeWarmWhite
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier
                            .height(38.dp)
                            .testTag("add_to_cart_${product.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingBag,
                            contentDescription = "Agregar al carrito",
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SecondaryProductCard(
    product: Product,
    isFavorite: Boolean,
    onProductClick: () -> Unit,
    onAddToCart: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(0.8.dp, MejunjeBorder, RoundedCornerShape(8.dp))
            .clickable(onClick = onProductClick)
            .testTag("product_card_${product.id}"),
        colors = CardDefaults.cardColors(containerColor = MejunjeWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            // Artwork
            Box {
                ProductArtwork(product = product, height = 140.dp)

                // Favorite Icon
                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(MejunjeWhite.copy(alpha = 0.85f))
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorito",
                        tint = if (isFavorite) MejunjeTerracotta else MejunjeCharcoal,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Family tag
            Text(
                text = product.aromaticFamily.label.uppercase(),
                fontFamily = TypewriterFamily,
                fontSize = 9.sp,
                color = product.primaryColor,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(3.dp))

            // Title
            Text(
                text = product.name,
                fontFamily = TypewriterFamily,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MejunjeCharcoal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Main notes
            Text(
                text = product.mainNotes.take(2).joinToString(" · "),
                fontFamily = EditorialSansFamily,
                fontSize = 11.5.sp,
                color = MejunjeTextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Price & Quick Add Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$${String.format("%,d", product.price).replace(',', '.')}",
                    fontFamily = TypewriterFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MejunjeCharcoal
                )

                IconButton(
                    onClick = onAddToCart,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(MejunjePaper)
                        .border(0.6.dp, MejunjeBorder, RoundedCornerShape(4.dp))
                        .testTag("quick_add_${product.id}")
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBag,
                        contentDescription = "Sumar al mejunje",
                        tint = MejunjeCharcoal,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }
        }
    }
}
