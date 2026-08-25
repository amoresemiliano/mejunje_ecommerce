package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
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
import com.example.model.MejunjeCatalog
import com.example.model.Product
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchOverlayModal(
    query: String,
    onQueryChange: (String) -> Unit,
    onProductClick: (Product) -> Unit,
    onDismiss: () -> Unit
) {
    val results = if (query.isBlank()) {
        emptyList()
    } else {
        MejunjeCatalog.products.filter { p ->
            p.name.contains(query, ignoreCase = true) ||
            p.aromaticFamily.label.contains(query, ignoreCase = true) ||
            p.mainNotes.any { it.contains(query, ignoreCase = true) } ||
            p.moodTags.any { it.contains(query, ignoreCase = true) } ||
            p.poeticDescription.contains(query, ignoreCase = true)
        }
    }

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
            // Search Input Row
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                placeholder = {
                    Text(
                        text = "Buscar por aroma, madera, emoción o nota...",
                        fontFamily = TypewriterFamily,
                        fontSize = 12.5.sp,
                        color = MejunjeTextMuted
                    )
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null, tint = MejunjeCharcoal)
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { onQueryChange("") }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Limpiar", tint = MejunjeCharcoal)
                        }
                    }
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MejunjeCharcoal,
                    unfocusedBorderColor = MejunjeBorder,
                    focusedContainerColor = MejunjeWhite,
                    unfocusedContainerColor = MejunjeWhite
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("search_input_field")
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Quick suggestion tags if query is empty
            if (query.isBlank()) {
                Text(
                    text = "BÚSQUEDAS POPULARES",
                    fontFamily = TypewriterFamily,
                    fontSize = 10.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = MejunjeTextMuted,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                val suggestions = listOf("Cedro & Humo", "Bergamota", "Velas", "Calma", "Lluvia", "Jazmín")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    suggestions.forEach { tag ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(3.dp))
                                .background(MejunjePaper)
                                .border(0.6.dp, MejunjeBorder, RoundedCornerShape(3.dp))
                                .clickable { onQueryChange(tag) }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(text = tag, fontFamily = TypewriterFamily, fontSize = 10.5.sp, color = MejunjeCharcoal)
                        }
                    }
                }
            } else {
                Text(
                    text = "RESULTADOS (${results.size})",
                    fontFamily = TypewriterFamily,
                    fontSize = 10.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = MejunjeTextMuted,
                    letterSpacing = 1.2.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (results.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No encontramos un aroma con esa nota.",
                            fontFamily = TypewriterFamily,
                            fontSize = 12.sp,
                            color = MejunjeTextMuted
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 340.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(results, key = { it.id }) { product ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(MejunjeWhite)
                                    .border(0.8.dp, MejunjeBorder, RoundedCornerShape(6.dp))
                                    .clickable {
                                        onProductClick(product)
                                        onDismiss()
                                    }
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = product.name,
                                        fontFamily = TypewriterFamily,
                                        fontSize = 12.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = MejunjeCharcoal
                                    )
                                    Text(
                                        text = "${product.aromaticFamily.label} · ${product.mainNotes.joinToString(" · ")}",
                                        fontFamily = EditorialSansFamily,
                                        fontSize = 11.sp,
                                        color = MejunjeTextSecondary
                                    )
                                }
                                Text(
                                    text = "$${String.format("%,d", product.price).replace(',', '.')}",
                                    fontFamily = TypewriterFamily,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MejunjeCharcoal
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
