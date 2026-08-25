package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.ui.theme.*

/**
 * Editorial, atmospheric custom vector & canvas illustration for MEJUNJE products.
 * Evokes warm amber glass, botanical wax, botanical sprigs, natural daylight and typewriter labels.
 */
@Composable
fun ProductArtwork(
    product: Product,
    modifier: Modifier = Modifier,
    height: Dp = 220.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(8.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MejunjePaper,
                        MejunjeIvory,
                        MejunjeBeige.copy(alpha = 0.6f)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val heightPx = size.height

            // Soft radial ambient light glow
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        product.primaryColor.copy(alpha = 0.25f),
                        Color.Transparent
                    ),
                    center = Offset(width * 0.5f, heightPx * 0.45f),
                    radius = width * 0.55f
                )
            )

            // Warm surface table shadow line
            val tableY = heightPx * 0.76f
            drawLine(
                color = MejunjeBorder.copy(alpha = 0.8f),
                start = Offset(width * 0.12f, tableY),
                end = Offset(width * 0.88f, tableY),
                strokeWidth = 1.5f
            )

            // Draw Botanical leaves / sprigs in background
            val leafPath = Path().apply {
                moveTo(width * 0.68f, tableY)
                quadraticTo(width * 0.82f, heightPx * 0.45f, width * 0.74f, heightPx * 0.28f)
                quadraticTo(width * 0.86f, heightPx * 0.38f, width * 0.68f, tableY)
            }
            drawPath(
                path = leafPath,
                color = MejunjeSage.copy(alpha = 0.45f)
            )

            // Draw Product Silhouette according to category
            when (product.category) {
                ProductCategory.VELAS -> {
                    // Amber Glass Jar
                    val jarWidth = width * 0.38f
                    val jarHeight = heightPx * 0.42f
                    val jarLeft = (width - jarWidth) / 2f
                    val jarTop = tableY - jarHeight

                    // Soft Jar Drop Shadow
                    drawOval(
                        color = MejunjeCharcoal.copy(alpha = 0.12f),
                        topLeft = Offset(jarLeft - 8f, tableY - 6f),
                        size = Size(jarWidth + 16f, 14f)
                    )

                    // Amber glass body
                    drawRoundRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MejunjeAmber.copy(alpha = 0.85f),
                                Color(0xFF9E6B28)
                            )
                        ),
                        topLeft = Offset(jarLeft, jarTop),
                        size = Size(jarWidth, jarHeight),
                        cornerRadius = CornerRadius(10f, 10f)
                    )

                    // Glass highlight line
                    drawLine(
                        color = Color.White.copy(alpha = 0.45f),
                        start = Offset(jarLeft + 8f, jarTop + 10f),
                        end = Offset(jarLeft + 8f, jarTop + jarHeight - 12f),
                        strokeWidth = 3f
                    )

                    // Black metal / amber lid line
                    drawRoundRect(
                        color = MejunjeCharcoal,
                        topLeft = Offset(jarLeft - 4f, jarTop - 6f),
                        size = Size(jarWidth + 8f, 10f),
                        cornerRadius = CornerRadius(4f, 4f)
                    )

                    // Flickering flame / candle wick
                    drawLine(
                        color = MejunjeCharcoal,
                        start = Offset(width * 0.5f, jarTop - 6f),
                        end = Offset(width * 0.5f, jarTop - 18f),
                        strokeWidth = 2.5f
                    )
                    // Flame
                    val flamePath = Path().apply {
                        moveTo(width * 0.5f, jarTop - 18f)
                        quadraticTo(width * 0.53f, jarTop - 26f, width * 0.5f, jarTop - 34f)
                        quadraticTo(width * 0.47f, jarTop - 26f, width * 0.5f, jarTop - 18f)
                    }
                    drawPath(flamePath, color = MejunjeAmber)
                }

                ProductCategory.DIFUSORES -> {
                    // Reed Diffuser Glass Bottle
                    val bottleWidth = width * 0.32f
                    val bottleHeight = heightPx * 0.36f
                    val bottleLeft = (width - bottleWidth) / 2f
                    val bottleTop = tableY - bottleHeight

                    // 5 Reeds / Rattan sticks radiating upwards
                    val stickColor = MejunjeSand
                    val stickTop = bottleTop - heightPx * 0.32f
                    drawLine(color = stickColor, start = Offset(width * 0.5f, bottleTop + 10f), end = Offset(width * 0.36f, stickTop), strokeWidth = 3f)
                    drawLine(color = stickColor, start = Offset(width * 0.5f, bottleTop + 10f), end = Offset(width * 0.44f, stickTop - 10f), strokeWidth = 3f)
                    drawLine(color = stickColor, start = Offset(width * 0.5f, bottleTop + 10f), end = Offset(width * 0.5f, stickTop - 15f), strokeWidth = 3f)
                    drawLine(color = stickColor, start = Offset(width * 0.5f, bottleTop + 10f), end = Offset(width * 0.57f, stickTop - 8f), strokeWidth = 3f)
                    drawLine(color = stickColor, start = Offset(width * 0.5f, bottleTop + 10f), end = Offset(width * 0.65f, stickTop + 5f), strokeWidth = 3f)

                    // Bottle body
                    drawRoundRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MejunjeWarmWhite.copy(alpha = 0.95f),
                                MejunjeAmber.copy(alpha = 0.45f)
                            )
                        ),
                        topLeft = Offset(bottleLeft, bottleTop),
                        size = Size(bottleWidth, bottleHeight),
                        cornerRadius = CornerRadius(14f, 14f)
                    )
                    drawRoundRect(
                        color = MejunjeBorder,
                        topLeft = Offset(bottleLeft, bottleTop),
                        size = Size(bottleWidth, bottleHeight),
                        cornerRadius = CornerRadius(14f, 14f),
                        style = Stroke(width = 2f)
                    )
                }

                ProductCategory.HOME_SPRAYS, ProductCategory.TEXTILES -> {
                    // Spray Bottle with Trigger
                    val bottleWidth = width * 0.28f
                    val bottleHeight = heightPx * 0.40f
                    val bottleLeft = (width - bottleWidth) / 2f
                    val bottleTop = tableY - bottleHeight

                    // Amber apothecary cylinder
                    drawRoundRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MejunjeAmber.copy(alpha = 0.85f),
                                Color(0xFF8A5B20)
                            )
                        ),
                        topLeft = Offset(bottleLeft, bottleTop),
                        size = Size(bottleWidth, bottleHeight),
                        cornerRadius = CornerRadius(8f, 8f)
                    )

                    // Black Spray Trigger Head
                    drawRoundRect(
                        color = MejunjeCharcoal,
                        topLeft = Offset(bottleLeft + 4f, bottleTop - 20f),
                        size = Size(bottleWidth - 8f, 20f),
                        cornerRadius = CornerRadius(3f, 3f)
                    )
                    // Trigger nozzle
                    drawLine(
                        color = MejunjeCharcoal,
                        start = Offset(bottleLeft + 6f, bottleTop - 12f),
                        end = Offset(bottleLeft - 14f, bottleTop - 8f),
                        strokeWidth = 5f
                    )
                    // Mist dots
                    drawCircle(color = product.primaryColor.copy(alpha = 0.6f), radius = 2.5f, center = Offset(bottleLeft - 24f, bottleTop - 14f))
                    drawCircle(color = product.primaryColor.copy(alpha = 0.4f), radius = 3.5f, center = Offset(bottleLeft - 34f, bottleTop - 8f))
                    drawCircle(color = product.primaryColor.copy(alpha = 0.3f), radius = 2f, center = Offset(bottleLeft - 40f, bottleTop - 22f))
                }

                ProductCategory.SETS, ProductCategory.ALL -> {
                    // Set Bundle: Candle + Spray Duo
                    val leftJar = width * 0.28f
                    val rightBottle = width * 0.54f

                    // Candle
                    drawRoundRect(
                        brush = Brush.verticalGradient(listOf(MejunjeAmber, Color(0xFF9E6B28))),
                        topLeft = Offset(leftJar, tableY - heightPx * 0.32f),
                        size = Size(width * 0.26f, heightPx * 0.32f),
                        cornerRadius = CornerRadius(8f, 8f)
                    )
                    // Spray
                    drawRoundRect(
                        brush = Brush.verticalGradient(listOf(MejunjeDeepGreen, Color(0xFF3B4234))),
                        topLeft = Offset(rightBottle, tableY - heightPx * 0.42f),
                        size = Size(width * 0.20f, heightPx * 0.42f),
                        cornerRadius = CornerRadius(6f, 6f)
                    )
                }
            }
        }

        // Center Typewriter Label Mockup on Product
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 20.dp)
                .widthIn(min = 90.dp, max = 130.dp)
                .background(MejunjeWarmWhite, RoundedCornerShape(2.dp))
                .border(0.8.dp, MejunjeCharcoal.copy(alpha = 0.5f), RoundedCornerShape(2.dp))
                .padding(horizontal = 6.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "MEJUNJE",
                    fontFamily = TypewriterFamily,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp,
                    color = MejunjeCharcoal
                )
                Text(
                    text = product.mainNotes.firstOrNull()?.uppercase() ?: "AROMA",
                    fontFamily = TypewriterFamily,
                    fontSize = 6.5.sp,
                    color = MejunjeTextSecondary,
                    letterSpacing = 0.8.sp
                )
            }
        }
    }
}
