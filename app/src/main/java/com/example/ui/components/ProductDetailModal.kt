package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MejunjeCatalog
import com.example.model.Product
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailModal(
    product: Product,
    isFavorite: Boolean,
    onDismiss: () -> Unit,
    onAddToCart: (Product, Int, Boolean, String) -> Unit,
    onToggleFavorite: () -> Unit,
    onSelectCompanionProduct: (Product) -> Unit
) {
    var quantity by remember { mutableStateOf(1) }
    var isGiftWrap by remember { mutableStateOf(false) }
    var giftNote by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MejunjeWarmWhite,
        scrimColor = Color.Black.copy(alpha = 0.55f),
        dragHandle = {
            BottomSheetDefaults.DragHandle(color = MejunjeBorder)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            // Top Bar with Close and Favorite
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onDismiss, modifier = Modifier.size(36.dp)) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar", tint = MejunjeCharcoal)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onToggleFavorite, modifier = Modifier.size(36.dp)) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (isFavorite) MejunjeTerracotta else MejunjeCharcoal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Large Product Artwork Gallery
            ProductArtwork(product = product, height = 240.dp)

            Spacer(modifier = Modifier.height(18.dp))

            // Category & Family Tag
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .background(product.primaryColor.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = product.aromaticFamily.label.uppercase(),
                        fontFamily = TypewriterFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = product.primaryColor,
                        letterSpacing = 1.2.sp
                    )
                }

                Text(
                    text = product.category.displayName,
                    fontFamily = TypewriterFamily,
                    fontSize = 10.sp,
                    color = MejunjeTextSecondary,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Product Name
            Text(
                text = product.name,
                fontFamily = TypewriterFamily,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MejunjeCharcoal,
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Price & Size
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "$${String.format("%,d", product.price).replace(',', '.')}",
                    fontFamily = TypewriterFamily,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MejunjeCharcoal
                )
                Text(
                    text = "· ${product.sizeVolume}",
                    fontFamily = EditorialSansFamily,
                    fontSize = 13.sp,
                    color = MejunjeTextMuted
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Narrative Story
            Text(
                text = product.poeticDescription,
                fontFamily = EditorialSansFamily,
                fontSize = 14.sp,
                lineHeight = 22.sp,
                color = MejunjeCharcoal
            )

            Spacer(modifier = Modifier.height(16.dp))

            // "A QUÉ HUELE" Editorial Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(MejunjePaper)
                    .border(0.8.dp, MejunjeBorder, RoundedCornerShape(6.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Text(
                        text = "A QUÉ HUELE",
                        fontFamily = TypewriterFamily,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp,
                        color = MejunjeAmber
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "“${product.feelsLike}”",
                        fontFamily = EditorialSansFamily,
                        fontSize = 13.5.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        lineHeight = 20.sp,
                        color = MejunjeCharcoal
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // INTENSIDAD SCALE
            Text(
                text = "INTENSIDAD OLFATIVA",
                fontFamily = TypewriterFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp,
                color = MejunjeCharcoal
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Sutil",
                    fontFamily = TypewriterFamily,
                    fontSize = 10.sp,
                    color = MejunjeTextMuted
                )
                (1..5).forEach { dotIndex ->
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(
                                if (dotIndex <= product.intensity) MejunjeCharcoal else MejunjeBorder
                            )
                    )
                }
                Text(
                    text = "Intenso",
                    fontFamily = TypewriterFamily,
                    fontSize = 10.sp,
                    color = MejunjeTextMuted
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // PIRÁMIDE OLFATIVA
            Text(
                text = "PIRÁMIDE OLFATIVA",
                fontFamily = TypewriterFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.4.sp,
                color = MejunjeCharcoal
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(MejunjeWhite)
                    .border(0.8.dp, MejunjeBorder, RoundedCornerShape(6.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PyramidTierRow("SALIDA", product.pyramid.topNotes.joinToString(" · "))
                Divider(color = MejunjeBorder.copy(alpha = 0.5f), thickness = 0.6.dp)
                PyramidTierRow("CORAZÓN", product.pyramid.heartNotes.joinToString(" · "))
                Divider(color = MejunjeBorder.copy(alpha = 0.5f), thickness = 0.6.dp)
                PyramidTierRow("FONDO", product.pyramid.baseNotes.joinToString(" · "))
            }

            Spacer(modifier = Modifier.height(18.dp))

            // IDEAL PARA ESPACIOS
            Text(
                text = "IDEAL PARA:",
                fontFamily = TypewriterFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp,
                color = MejunjeCharcoal
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                product.idealForRooms.forEach { room ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(3.dp))
                            .background(MejunjePaper)
                            .border(0.6.dp, MejunjeBorder, RoundedCornerShape(3.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = room,
                            fontFamily = TypewriterFamily,
                            fontSize = 10.sp,
                            color = MejunjeCharcoal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // CROSS-SELL: PARA COMPLETAR EL RITUAL
            if (product.companionProductIds.isNotEmpty()) {
                val companions = MejunjeCatalog.products.filter { product.companionProductIds.contains(it.id) }
                if (companions.isNotEmpty()) {
                    Text(
                        text = "PARA COMPLETAR EL RITUAL",
                        fontFamily = TypewriterFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.4.sp,
                        color = MejunjeCharcoal
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        companions.forEach { comp ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(MejunjeWhite)
                                    .border(0.8.dp, MejunjeBorder, RoundedCornerShape(6.dp))
                                    .clickable { onSelectCompanionProduct(comp) }
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = comp.name,
                                        fontFamily = TypewriterFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MejunjeCharcoal
                                    )
                                    Text(
                                        text = comp.aromaticFamily.label + " · $" + String.format("%,d", comp.price).replace(',', '.'),
                                        fontFamily = EditorialSansFamily,
                                        fontSize = 11.sp,
                                        color = MejunjeTextSecondary
                                    )
                                }
                                Text(
                                    text = "VER →",
                                    fontFamily = TypewriterFamily,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MejunjeAmber
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Gift Packaging Option Checkbox
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(MejunjePaper)
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isGiftWrap,
                    onCheckedChange = { isGiftWrap = it },
                    colors = CheckboxDefaults.colors(checkedColor = MejunjeCharcoal)
                )
                Column {
                    Text(
                        text = "Envolver para regalo en papel y lacre (+ $1.200)",
                        fontFamily = TypewriterFamily,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = MejunjeCharcoal
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Sticky Bottom Add To Cart Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Quantity Selector
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(MejunjeWhite)
                        .border(0.8.dp, MejunjeBorder, RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { if (quantity > 1) quantity-- },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Text("-", fontFamily = TypewriterFamily, fontSize = 16.sp, color = MejunjeCharcoal)
                    }
                    Text(
                        text = "$quantity",
                        fontFamily = TypewriterFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MejunjeCharcoal,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                    IconButton(
                        onClick = { quantity++ },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Text("+", fontFamily = TypewriterFamily, fontSize = 16.sp, color = MejunjeCharcoal)
                    }
                }

                // CTA AGREGAR A MI MEJUNJE
                Button(
                    onClick = {
                        onAddToCart(product, quantity, isGiftWrap, giftNote)
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MejunjeCharcoal,
                        contentColor = MejunjeWarmWhite
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .testTag("pdp_add_to_cart_button")
                ) {
                    Icon(imageVector = Icons.Outlined.ShoppingBag, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "AGREGAR A MI MEJUNJE",
                        fontFamily = TypewriterFamily,
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.2.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PyramidTierRow(tierName: String, notes: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = tierName,
            fontFamily = TypewriterFamily,
            fontSize = 9.5.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            color = MejunjeTextMuted,
            modifier = Modifier.width(60.dp)
        )
        Text(
            text = notes,
            fontFamily = EditorialSansFamily,
            fontSize = 12.5.sp,
            color = MejunjeCharcoal
        )
    }
}
