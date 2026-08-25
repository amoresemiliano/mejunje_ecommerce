package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ProductCategory
import com.example.ui.theme.*

@Composable
fun CollectionsSection(
    onSelectCategory: (ProductCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val collectionItems = listOf(
        Triple(ProductCategory.VELAS, "VELAS BOTÁNICAS", "Cera de soja vertida a mano · Pabilos crepitantes de madera"),
        Triple(ProductCategory.DIFUSORES, "DIFUSORES AMBIENTALES", "Varillas de ratán con aceites puros de larga duración"),
        Triple(ProductCategory.HOME_SPRAYS, "HOME SPRAYS", "Brumas botánicas para despertar espacios al instante"),
        Triple(ProductCategory.TEXTILES, "PERFUMERÍA TEXTIL", "Fórmulas delicadas para linos, prendas y almohadas"),
        Triple(ProductCategory.SETS, "SETS & RITUALES", "Combinaciones armadas con beneficio especial")
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "COLECCIONES",
                fontFamily = TypewriterFamily,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = MejunjeCharcoal
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Explorá según el tipo de objeto aromático que acompaña tu espacio.",
                fontFamily = EditorialSansFamily,
                fontSize = 13.sp,
                color = MejunjeTextSecondary
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            collectionItems.forEach { (category, title, subtitle) ->
                CollectionCard(
                    category = category,
                    title = title,
                    subtitle = subtitle,
                    onClick = { onSelectCategory(category) }
                )
            }
        }
    }
}

@Composable
private fun CollectionCard(
    category: ProductCategory,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(220.dp)
            .height(140.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MejunjeWhite)
            .border(0.8.dp, MejunjeBorder, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .testTag("collection_${category.name.lowercase()}"),
        contentAlignment = Alignment.BottomStart
    ) {
        // Decorative canvas gradient background
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            val color = when (category) {
                ProductCategory.VELAS -> MejunjeAmber
                ProductCategory.DIFUSORES -> MejunjeSoftMustard
                ProductCategory.HOME_SPRAYS -> MejunjeSage
                ProductCategory.TEXTILES -> MejunjeSalmon
                ProductCategory.SETS -> MejunjeTerracotta
                else -> MejunjeDryGreen
            }

            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        color.copy(alpha = 0.08f),
                        color.copy(alpha = 0.22f)
                    )
                )
            )

            drawCircle(
                color = color.copy(alpha = 0.15f),
                radius = width * 0.4f,
                center = Offset(width * 0.85f, height * 0.2f)
            )
        }

        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = title,
                fontFamily = TypewriterFamily,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.4.sp,
                color = MejunjeCharcoal
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                fontFamily = EditorialSansFamily,
                fontSize = 11.5.sp,
                color = MejunjeTextSecondary,
                lineHeight = 15.sp,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "EXPLORAR →",
                fontFamily = TypewriterFamily,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.2.sp,
                color = MejunjeDeepGreen
            )
        }
    }
}
