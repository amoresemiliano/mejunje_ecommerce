package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.BundleItem
import com.example.model.MejunjeCatalog
import com.example.ui.theme.*

@Composable
fun BundlesSection(
    onAddBundle: (BundleItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        // Title
        Column {
            Text(
                text = "MEJUNJES ARMADOS",
                fontFamily = TypewriterFamily,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = MejunjeCharcoal
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Combinaciones sugeridas por nuestro atelier para rituales completos y regalos con intención.",
                fontFamily = EditorialSansFamily,
                fontSize = 13.sp,
                color = MejunjeTextSecondary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            MejunjeCatalog.bundles.forEach { bundle ->
                BundleCard(bundle = bundle, onAddBundle = { onAddBundle(bundle) })
            }
        }
    }
}

@Composable
private fun BundleCard(
    bundle: BundleItem,
    onAddBundle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, MejunjeBorder, RoundedCornerShape(10.dp))
            .testTag("bundle_card_${bundle.id}"),
        colors = CardDefaults.cardColors(containerColor = MejunjeWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row: Tag & Discount
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .background(bundle.accentColor.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = bundle.tag,
                        fontFamily = TypewriterFamily,
                        fontSize = 9.5.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp,
                        color = bundle.accentColor
                    )
                }

                Text(
                    text = "${bundle.discountPercent}% OFF BENEFICIO",
                    fontFamily = TypewriterFamily,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MejunjeDeepGreen
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Title
            Text(
                text = bundle.title,
                fontFamily = TypewriterFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp,
                color = MejunjeCharcoal
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = bundle.subtitle,
                fontFamily = EditorialSansFamily,
                fontSize = 12.5.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = MejunjeTextSecondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Products Included Box (Typewriter Paper style)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(MejunjePaper)
                    .border(0.6.dp, MejunjeBorder, RoundedCornerShape(4.dp))
                    .padding(10.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text(
                        text = "CONTIENE:",
                        fontFamily = TypewriterFamily,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = MejunjeTextMuted,
                        letterSpacing = 1.sp
                    )
                    bundle.productsIncluded.forEach { itemText ->
                        Text(
                            text = "• $itemText",
                            fontFamily = TypewriterFamily,
                            fontSize = 11.sp,
                            color = MejunjeCharcoal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Pricing & Add to Cart
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "$${String.format("%,d", bundle.bundlePrice).replace(',', '.')}",
                            fontFamily = TypewriterFamily,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = MejunjeCharcoal
                        )
                        Text(
                            text = "$${String.format("%,d", bundle.originalPrice).replace(',', '.')}",
                            fontFamily = TypewriterFamily,
                            fontSize = 12.sp,
                            color = MejunjeTextMuted,
                            style = androidx.compose.ui.text.TextStyle(textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough)
                        )
                    }
                    Text(
                        text = "Incluye caja de regalo atelier",
                        fontFamily = EditorialSansFamily,
                        fontSize = 11.sp,
                        color = MejunjeTextSecondary
                    )
                }

                Button(
                    onClick = onAddBundle,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MejunjeCharcoal,
                        contentColor = MejunjeWarmWhite
                    ),
                    shape = RoundedCornerShape(4.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                    modifier = Modifier.testTag("add_bundle_${bundle.id}")
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBag,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "AGREGAR SET",
                        fontFamily = TypewriterFamily,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp
                    )
                }
            }
        }
    }
}
