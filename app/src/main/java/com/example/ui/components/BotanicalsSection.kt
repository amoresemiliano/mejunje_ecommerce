package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.model.BotanicalIngredient
import com.example.model.MejunjeCatalog
import com.example.ui.theme.*

@Composable
fun BotanicalsSection(
    modifier: Modifier = Modifier
) {
    var selectedBotanical by remember { mutableStateOf(MejunjeCatalog.botanicals.first()) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "LO QUE HAY ADENTRO",
                fontFamily = TypewriterFamily,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = MejunjeCharcoal
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Botánica pura, resinas nobles y aceites esenciales que componen nuestras fórmulas.",
                fontFamily = EditorialSansFamily,
                fontSize = 13.sp,
                color = MejunjeTextSecondary
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Ingredients Pills Carousel
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MejunjeCatalog.botanicals.forEach { botanical ->
                val isSelected = botanical.id == selectedBotanical.id
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) MejunjeDeepGreen else MejunjeWhite)
                        .border(
                            width = 0.8.dp,
                            color = if (isSelected) MejunjeDeepGreen else MejunjeBorder,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable { selectedBotanical = botanical }
                        .padding(horizontal = 14.dp, vertical = 7.dp)
                        .testTag("botanical_pill_${botanical.name.lowercase()}")
                ) {
                    Text(
                        text = botanical.name,
                        fontFamily = TypewriterFamily,
                        fontSize = 11.5.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                        letterSpacing = 1.2.sp,
                        color = if (isSelected) MejunjeWarmWhite else MejunjeCharcoal
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Selected Botanical Focus Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MejunjePaper)
                .border(0.8.dp, MejunjeBorder, RoundedCornerShape(8.dp))
                .padding(18.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedBotanical.name,
                        fontFamily = TypewriterFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        color = MejunjeCharcoal
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(selectedBotanical.family.badgeColor.copy(alpha = 0.2f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = selectedBotanical.family.label.uppercase(),
                            fontFamily = TypewriterFamily,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MejunjeCharcoal,
                            letterSpacing = 1.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = selectedBotanical.originNote,
                    fontFamily = EditorialSansFamily,
                    fontSize = 12.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    color = MejunjeAmber
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = selectedBotanical.description,
                    fontFamily = EditorialSansFamily,
                    fontSize = 13.5.sp,
                    lineHeight = 20.sp,
                    color = MejunjeCharcoal
                )

                Spacer(modifier = Modifier.height(12.dp))

                Divider(color = MejunjeBorder.copy(alpha = 0.5f), thickness = 0.8.dp)

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "INTENCIÓN Y BENEFICIO: ",
                        fontFamily = TypewriterFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MejunjeDeepGreen,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = selectedBotanical.benefits,
                        fontFamily = EditorialSansFamily,
                        fontSize = 12.5.sp,
                        color = MejunjeTextSecondary
                    )
                }
            }
        }
    }
}
