package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun AtelierStorySection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        // Main Story Card (Typewriter Paper Aesthetic)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(MejunjeWhite)
                .border(1.dp, MejunjeBorder, RoundedCornerShape(10.dp))
                .padding(22.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "NUESTRO MEJUNJE",
                    fontFamily = TypewriterFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 3.sp,
                    color = MejunjeCharcoal
                )

                Spacer(modifier = Modifier.height(10.dp))

                // The Brand Mantra
                Text(
                    text = "mezclamos · probamos · escribimos · olemos · corregimos · volvemos a mezclar",
                    fontFamily = TypewriterFamily,
                    fontSize = 11.5.sp,
                    letterSpacing = 1.2.sp,
                    color = MejunjeAmber,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "MEJUNJE nació en un departamento alto de Buenos Aires con la obsesión de capturar cómo huele la memoria. Usamos morteros de cerámica, notas manuscritas y vidrio ámbar que protege las resinas de la luz.",
                    fontFamily = EditorialSansFamily,
                    fontSize = 13.5.sp,
                    lineHeight = 21.sp,
                    color = MejunjeCharcoal,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Cada lote es pequeño e intencional. No producimos en masa: perfumamos objetos que van a compartir tu vida y tus momentos de calma.",
                    fontFamily = EditorialSansFamily,
                    fontSize = 13.5.sp,
                    lineHeight = 21.sp,
                    color = MejunjeTextSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Atelier Badges / Commitments
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    AtelierPill(label = "LOTES CHICOS")
                    AtelierPill(label = "CERA VEGETAL")
                    AtelierPill(label = "SIN QUÍMICOS NOCIVOS")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // "DENTRO DEL ATELIER" Visual Grid
        Column {
            Text(
                text = "DENTRO DEL ATELIER",
                fontFamily = TypewriterFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = MejunjeCharcoal,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AtelierReportageCard(
                    title = "01. LAS FÓRMULAS",
                    subtitle = "Gotas medidas a mano y maceradas por semanas en reposo oscuro.",
                    modifier = Modifier.weight(1f)
                )
                AtelierReportageCard(
                    title = "02. EL VERTIDO",
                    subtitle = "Cera de soja tibia sobre pabilos de madera natural sin tratar.",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun AtelierPill(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(3.dp))
            .background(MejunjePaper)
            .border(0.6.dp, MejunjeBorder, RoundedCornerShape(3.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontFamily = TypewriterFamily,
            fontSize = 9.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp,
            color = MejunjeDeepGreen
        )
    }
}

@Composable
private fun AtelierReportageCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(MejunjePaper)
            .border(0.8.dp, MejunjeBorder, RoundedCornerShape(6.dp))
            .padding(12.dp)
    ) {
        Column {
            Text(
                text = title,
                fontFamily = TypewriterFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp,
                color = MejunjeAmber
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                fontFamily = EditorialSansFamily,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                color = MejunjeCharcoal
            )
        }
    }
}
