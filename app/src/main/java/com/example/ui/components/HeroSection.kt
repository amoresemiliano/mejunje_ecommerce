package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun HeroSection(
    onExploreClick: () -> Unit,
    onFindScentClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MejunjeWarmWhite)
            .border(width = 1.dp, color = MejunjeBorder, shape = RoundedCornerShape(12.dp))
    ) {
        // Editorial Cover Atmospheric Canvas
        Canvas(modifier = Modifier.matchParentSize()) {
            val width = size.width
            val height = size.height

            // Soft diagonal light beam simulating sunlight entering through Buenos Aires high windows
            drawRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MejunjeAmber.copy(alpha = 0.12f),
                        MejunjeIvory.copy(alpha = 0.5f),
                        MejunjePaper.copy(alpha = 0.8f)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(width, height)
                )
            )

            // Warm amber circular watermark
            drawCircle(
                color = MejunjeAmber.copy(alpha = 0.08f),
                radius = width * 0.4f,
                center = Offset(width * 0.85f, height * 0.3f)
            )

            // Botanical shadow silhouette
            drawOval(
                color = MejunjeSage.copy(alpha = 0.15f),
                topLeft = Offset(width * 0.7f, height * 0.1f),
                size = Size(width * 0.25f, height * 0.6f)
            )
        }

        // Editorial Content Layout
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Editorial Issue / Atelier Tag
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .background(MejunjeWhite, RoundedCornerShape(4.dp))
                    .border(0.6.dp, MejunjeBorder, RoundedCornerShape(4.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "ATELIER BOTÁNICO",
                    fontFamily = TypewriterFamily,
                    fontSize = 9.sp,
                    letterSpacing = 1.8.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MejunjeDeepGreen
                )
                Text(
                    text = "·",
                    fontFamily = TypewriterFamily,
                    fontSize = 9.sp,
                    color = MejunjeAmber
                )
                Text(
                    text = "LOTE LIMITADO",
                    fontFamily = TypewriterFamily,
                    fontSize = 9.sp,
                    letterSpacing = 1.8.sp,
                    color = MejunjeTextSecondary
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Main Brand Title
            Text(
                text = "MEJUNJE",
                fontFamily = TypewriterFamily,
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 6.sp,
                color = MejunjeCharcoal,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Poetic Subcopy
            Text(
                text = "“historias de vida envueltas en perfume.”",
                fontFamily = EditorialSansFamily,
                fontSize = 15.5.sp,
                lineHeight = 22.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = MejunjeTextSecondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "mezcla · intención · botánica",
                fontFamily = TypewriterFamily,
                fontSize = 11.5.sp,
                letterSpacing = 2.sp,
                color = MejunjeAmber,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(22.dp))

            // Editorial Typewriter Note Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(MejunjeWhite)
                    .border(0.8.dp, MejunjeBorder, RoundedCornerShape(6.dp))
                    .padding(14.dp)
            ) {
                Text(
                    text = "No creamos fragancias genéricas. Diseñamos atmósferas que despiertan la memoria, hechas a mano en Buenos Aires con ceras vegetales, vidrio ámbar y aceites puros.",
                    fontFamily = EditorialSansFamily,
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    color = MejunjeCharcoal.copy(alpha = 0.85f),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // CTAs
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Primary CTA: DESCUBRIR MEJUNJES
                Button(
                    onClick = onExploreClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MejunjeCharcoal,
                        contentColor = MejunjeWarmWhite
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("hero_explore_button")
                ) {
                    Text(
                        text = "DESCUBRIR MEJUNJES",
                        fontFamily = TypewriterFamily,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.8.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }

                // Secondary CTA: ENCONTRAR MI AROMA (Quiz)
                OutlinedButton(
                    onClick = onFindScentClick,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MejunjeDeepGreen
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.linearGradient(listOf(MejunjeDeepGreen, MejunjeAmber))
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("hero_quiz_button")
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AutoAwesome,
                        contentDescription = null,
                        tint = MejunjeAmber,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "DESCUBRÍ TU AROMA (QUIZ)",
                        fontFamily = TypewriterFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.4.sp
                    )
                }
            }
        }
    }
}
