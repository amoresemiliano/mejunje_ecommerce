package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.CardGiftcard
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
import com.example.model.MejunjeCatalog
import com.example.model.Product
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiftExperienceModal(
    selectedOccasion: String,
    selectedBudget: String,
    selectedRecipient: String,
    customNote: String,
    onOccasionChange: (String) -> Unit,
    onBudgetChange: (String) -> Unit,
    onRecipientChange: (String) -> Unit,
    onNoteChange: (String) -> Unit,
    onAddGiftToCart: (Product) -> Unit,
    onDismiss: () -> Unit
) {
    val occasions = listOf("Cumpleaños", "Casa nueva", "Gracias", "Pareja", "Amiga", "Empresa")
    val budgets = listOf("Hasta $20.000", "Hasta $30.000", "Set Completo ($45.000+)")
    val recipients = listOf("Amiga", "Familia", "Pareja", "Compañero/a de trabajo", "Para mí")

    var selectedGiftProduct by remember { mutableStateOf(MejunjeCatalog.products.first()) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = MejunjeWarmWhite,
        scrimColor = Color.Black.copy(alpha = 0.55f),
        dragHandle = { BottomSheetDefaults.DragHandle(color = MejunjeBorder) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(imageVector = Icons.Outlined.CardGiftcard, contentDescription = null, tint = MejunjeTerracotta, modifier = Modifier.size(20.dp))
                    Text(
                        text = "REGALAR MEJUNJE",
                        fontFamily = TypewriterFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.6.sp,
                        color = MejunjeCharcoal
                    )
                }

                IconButton(onClick = onDismiss, modifier = Modifier.size(36.dp)) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar", tint = MejunjeCharcoal)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Elegí el motivo, nosotros preparamos el envoltorio botánico con lacre y escribimos tu dedicatoria en máquina de escribir.",
                fontFamily = EditorialSansFamily,
                fontSize = 13.sp,
                color = MejunjeTextSecondary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 1. OCASIÓN
            Text(
                text = "1. OCASIÓN",
                fontFamily = TypewriterFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp,
                color = MejunjeCharcoal
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                occasions.forEach { occ ->
                    val isSelected = occ == selectedOccasion
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isSelected) MejunjeCharcoal else MejunjeWhite)
                            .border(0.8.dp, if (isSelected) MejunjeCharcoal else MejunjeBorder, RoundedCornerShape(4.dp))
                            .clickable { onOccasionChange(occ) }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = occ,
                            fontFamily = TypewriterFamily,
                            fontSize = 11.sp,
                            color = if (isSelected) MejunjeWarmWhite else MejunjeCharcoal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. PRODUCTO A REGALAR
            Text(
                text = "2. ELEGÍ EL OBJETO AROMÁTICO",
                fontFamily = TypewriterFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp,
                color = MejunjeCharcoal
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                MejunjeCatalog.products.take(4).forEach { p ->
                    val isSelected = p.id == selectedGiftProduct.id
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isSelected) MejunjePaper else MejunjeWhite)
                            .border(if (isSelected) 1.2.dp else 0.8.dp, if (isSelected) MejunjeAmber else MejunjeBorder, RoundedCornerShape(6.dp))
                            .clickable { selectedGiftProduct = p }
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = p.name,
                                fontFamily = TypewriterFamily,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MejunjeCharcoal
                            )
                            Text(
                                text = "${p.aromaticFamily.label} · $${String.format("%,d", p.price).replace(',', '.')}",
                                fontFamily = EditorialSansFamily,
                                fontSize = 11.sp,
                                color = MejunjeTextSecondary
                            )
                        }

                        RadioButton(
                            selected = isSelected,
                            onClick = { selectedGiftProduct = p },
                            colors = RadioButtonDefaults.colors(selectedColor = MejunjeCharcoal)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. CARTA MANUSCRITA MECANOGRAFIADA
            Text(
                text = "3. TU DEDICATORIA MECANOGRAFIADA",
                fontFamily = TypewriterFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp,
                color = MejunjeCharcoal
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = customNote,
                onValueChange = onNoteChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .testTag("gift_letter_input"),
                placeholder = {
                    Text(
                        text = "Escribí tu mensaje aquí...",
                        fontFamily = TypewriterFamily,
                        fontSize = 12.sp,
                        color = MejunjeTextMuted
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MejunjeCharcoal,
                    unfocusedBorderColor = MejunjeBorder,
                    focusedContainerColor = MejunjeWhite,
                    unfocusedContainerColor = MejunjeWhite
                ),
                shape = RoundedCornerShape(4.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // CTA
            Button(
                onClick = { onAddGiftToCart(selectedGiftProduct) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MejunjeCharcoal,
                    contentColor = MejunjeWarmWhite
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("gift_add_to_cart_button")
            ) {
                Text(
                    text = "PREPARAR ESTE REGALO",
                    fontFamily = TypewriterFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.6.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
