package com.example.ui.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MejunjeCatalog
import com.example.model.MoodFilter
import com.example.ui.theme.*

@Composable
fun MoodDiscoverySection(
    selectedMood: String?,
    onMoodSelected: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // Section Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "¿QUÉ QUERÉS SENTIR?",
                fontFamily = TypewriterFamily,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = MejunjeCharcoal
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Elegí una emoción para explorar los aromas que acompañan tu estado de ánimo.",
                fontFamily = EditorialSansFamily,
                fontSize = 13.sp,
                color = MejunjeTextSecondary
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Horizontal Carousel of Mood Cards
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // "Todos" Reset Card
            MoodCardItem(
                title = "TODAS",
                subtitle = "Ver catálogo completo",
                quote = "La sinfonía completa de mezclas de nuestro atelier.",
                accentColor = MejunjeCharcoal,
                isSelected = selectedMood == null,
                onClick = { onMoodSelected(null) }
            )

            // Mood Items
            MejunjeCatalog.moodCategories.forEach { mood ->
                MoodCardItem(
                    title = mood.title,
                    subtitle = mood.subtitle,
                    quote = mood.quote,
                    accentColor = mood.color,
                    isSelected = selectedMood == mood.title,
                    onClick = {
                        if (selectedMood == mood.title) onMoodSelected(null) else onMoodSelected(mood.title)
                    }
                )
            }
        }
    }
}

@Composable
private fun MoodCardItem(
    title: String,
    subtitle: String,
    quote: String,
    accentColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(170.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) MejunjePaper else MejunjeWhite)
            .border(
                width = if (isSelected) 1.5.dp else 0.8.dp,
                color = if (isSelected) accentColor else MejunjeBorder,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(14.dp)
            .testTag("mood_card_${title.lowercase()}")
    ) {
        Column {
            // Top Accent Pill / Tag
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(3.dp))
                    .background(accentColor.copy(alpha = if (isSelected) 0.25f else 0.15f))
                    .padding(horizontal = 7.dp, vertical = 3.dp)
            ) {
                Text(
                    text = title,
                    fontFamily = TypewriterFamily,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp,
                    color = if (accentColor == MejunjeCharcoal) MejunjeCharcoal else accentColor
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = subtitle,
                fontFamily = TypewriterFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = MejunjeCharcoal,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "“$quote”",
                fontFamily = EditorialSansFamily,
                fontSize = 11.5.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = MejunjeTextSecondary,
                lineHeight = 15.sp,
                maxLines = 3
            )
        }
    }
}
