package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CardGiftcard
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CartItem
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartDrawerSheet(
    cartItems: List<CartItem>,
    subtotal: Int,
    giftPackagingTotal: Int,
    total: Int,
    onDismiss: () -> Unit,
    onUpdateQuantity: (String, Int) -> Unit,
    onRemoveItem: (String) -> Unit,
    onToggleGiftWrap: (String) -> Unit,
    onCheckout: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MejunjeWarmWhite,
        scrimColor = Color.Black.copy(alpha = 0.55f),
        dragHandle = { BottomSheetDefaults.DragHandle(color = MejunjeBorder) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "TU MEJUNJE",
                        fontFamily = TypewriterFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        color = MejunjeCharcoal
                    )
                    Text(
                        text = "“Tu mejunje está tomando forma.”",
                        fontFamily = EditorialSansFamily,
                        fontSize = 12.5.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = MejunjeTextSecondary
                    )
                }

                IconButton(onClick = onDismiss, modifier = Modifier.size(36.dp)) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar", tint = MejunjeCharcoal)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            if (cartItems.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(MejunjePaper, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Tu mejunje está vacío.",
                            fontFamily = TypewriterFamily,
                            fontSize = 14.sp,
                            color = MejunjeCharcoal
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Explorá el catálogo para sumar tus aromas.",
                            fontFamily = EditorialSansFamily,
                            fontSize = 12.sp,
                            color = MejunjeTextMuted
                        )
                    }
                }
            } else {
                // Cart Items List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                        .heightIn(max = 320.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(cartItems, key = { it.product.id }) { item ->
                        CartItemRow(
                            item = item,
                            onQuantityChange = { delta -> onUpdateQuantity(item.product.id, delta) },
                            onRemove = { onRemoveItem(item.product.id) },
                            onToggleGiftWrap = { onToggleGiftWrap(item.product.id) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Price Breakdown Card
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(MejunjePaper)
                        .border(0.8.dp, MejunjeBorder, RoundedCornerShape(6.dp))
                        .padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Subtotal aromas", fontFamily = TypewriterFamily, fontSize = 12.sp, color = MejunjeTextSecondary)
                        Text(text = "$${String.format("%,d", subtotal).replace(',', '.')}", fontFamily = TypewriterFamily, fontSize = 12.sp, color = MejunjeCharcoal)
                    }

                    if (giftPackagingTotal > 0) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Envoltorio atelier con lacre", fontFamily = TypewriterFamily, fontSize = 12.sp, color = MejunjeAmber)
                            Text(text = "+ $${String.format("%,d", giftPackagingTotal).replace(',', '.')}", fontFamily = TypewriterFamily, fontSize = 12.sp, color = MejunjeAmber)
                        }
                    }

                    Divider(color = MejunjeBorder.copy(alpha = 0.6f), thickness = 0.8.dp, modifier = Modifier.padding(vertical = 4.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "TOTAL ESTIMADO", fontFamily = TypewriterFamily, fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp, color = MejunjeCharcoal)
                        Text(text = "$${String.format("%,d", total).replace(',', '.')}", fontFamily = TypewriterFamily, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = MejunjeCharcoal)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Checkout Button
                Button(
                    onClick = onCheckout,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MejunjeCharcoal,
                        contentColor = MejunjeWarmWhite
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("cart_proceed_checkout_button")
                ) {
                    Text(
                        text = "CONTINUAR COMPRA",
                        fontFamily = TypewriterFamily,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.6.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CartItemRow(
    item: CartItem,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit,
    onToggleGiftWrap: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .border(0.6.dp, MejunjeBorder, RoundedCornerShape(6.dp)),
        colors = CardDefaults.cardColors(containerColor = MejunjeWhite),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Mini Artwork Thumbnail
            ProductArtwork(product = item.product, height = 65.dp, modifier = Modifier.width(55.dp))

            // Details
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.product.name,
                    fontFamily = TypewriterFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MejunjeCharcoal,
                    maxLines = 1
                )
                Text(
                    text = "$${String.format("%,d", item.product.price).replace(',', '.')}",
                    fontFamily = TypewriterFamily,
                    fontSize = 11.5.sp,
                    color = MejunjeTextSecondary
                )

                if (item.isGiftWrapped) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(imageVector = Icons.Outlined.CardGiftcard, contentDescription = null, tint = MejunjeAmber, modifier = Modifier.size(12.dp))
                        Text(text = "Para regalo", fontFamily = TypewriterFamily, fontSize = 9.sp, color = MejunjeAmber)
                    }
                }
            }

            // Quantity buttons
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .background(MejunjePaper)
                    .padding(horizontal = 4.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onQuantityChange(-1) }, modifier = Modifier.size(24.dp)) {
                    Text("-", fontFamily = TypewriterFamily, fontSize = 14.sp, color = MejunjeCharcoal)
                }
                Text(
                    text = "${item.quantity}",
                    fontFamily = TypewriterFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MejunjeCharcoal,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                IconButton(onClick = { onQuantityChange(1) }, modifier = Modifier.size(24.dp)) {
                    Text("+", fontFamily = TypewriterFamily, fontSize = 14.sp, color = MejunjeCharcoal)
                }
            }

            // Delete
            IconButton(onClick = onRemove, modifier = Modifier.size(28.dp)) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar", tint = MejunjeTextMuted, modifier = Modifier.size(16.dp))
            }
        }
    }
}
