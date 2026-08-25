package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBag
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
import com.example.ui.theme.*

@Composable
fun MejunjeHeader(
    activeTab: String,
    onTabSelected: (String) -> Unit,
    cartCount: Int,
    favoritesCount: Int,
    onSearchClick: () -> Unit,
    onCartClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navTabs = listOf("TIENDA", "AROMAS", "COLECCIONES", "REGALOS", "ATELIER")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MejunjeWarmWhite)
            .border(width = 0.8.dp, color = MejunjeBorder.copy(alpha = 0.6f))
            .statusBarsPadding()
    ) {
        // Top Brand & Actions Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Search Icon
            IconButton(
                onClick = onSearchClick,
                modifier = Modifier
                    .size(40.dp)
                    .testTag("search_button")
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Buscar aroma o ingrediente",
                    tint = MejunjeCharcoal,
                    modifier = Modifier.size(22.dp)
                )
            }

            // Brand Title & City Subtitle
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onTabSelected("TIENDA") }
            ) {
                Text(
                    text = "MEJUNJE",
                    fontFamily = TypewriterFamily,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp,
                    color = MejunjeCharcoal
                )
                Text(
                    text = "BUENOS AIRES · ATELIER OLFATIVO",
                    fontFamily = TypewriterFamily,
                    fontSize = 8.5.sp,
                    letterSpacing = 1.6.sp,
                    color = MejunjeTextSecondary
                )
            }

            // Right Actions: Favorites & Cart
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Favorites
                IconButton(
                    onClick = onFavoritesClick,
                    modifier = Modifier
                        .size(40.dp)
                        .testTag("favorites_button")
                ) {
                    BadgedBox(
                        badge = {
                            if (favoritesCount > 0) {
                                Badge(
                                    containerColor = MejunjeTerracotta,
                                    contentColor = MejunjeWarmWhite
                                ) {
                                    Text(text = "$favoritesCount", fontSize = 9.sp, fontFamily = TypewriterFamily)
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (favoritesCount > 0) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favoritos",
                            tint = if (favoritesCount > 0) MejunjeTerracotta else MejunjeCharcoal,
                            modifier = Modifier.size(21.dp)
                        )
                    }
                }

                // Cart
                IconButton(
                    onClick = onCartClick,
                    modifier = Modifier
                        .size(40.dp)
                        .testTag("cart_button")
                ) {
                    BadgedBox(
                        badge = {
                            if (cartCount > 0) {
                                Badge(
                                    containerColor = MejunjeAmber,
                                    contentColor = MejunjeCharcoal
                                ) {
                                    Text(
                                        text = "$cartCount",
                                        fontSize = 9.5.sp,
                                        fontFamily = TypewriterFamily,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingBag,
                            contentDescription = "Carrito de compras",
                            tint = MejunjeCharcoal,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        }

        // Horizontal Navigation Tabs (Pills)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            navTabs.forEach { tab ->
                val isSelected = activeTab == tab
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            if (isSelected) MejunjeCharcoal else Color.Transparent
                        )
                        .border(
                            width = 0.8.dp,
                            color = if (isSelected) MejunjeCharcoal else MejunjeBorder,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable { onTabSelected(tab) }
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = tab,
                        fontFamily = TypewriterFamily,
                        fontSize = 11.5.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        letterSpacing = 1.2.sp,
                        color = if (isSelected) MejunjeWarmWhite else MejunjeTextSecondary
                    )
                }
            }
        }
    }
}
