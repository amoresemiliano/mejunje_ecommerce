package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Product
import com.example.model.QuizResult
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizModal(
    step: Int,
    result: QuizResult?,
    onAnswer: (Int, String) -> Unit,
    onDismiss: () -> Unit,
    onSelectProduct: (Product) -> Unit,
    onAddToCart: (Product) -> Unit
) {
    val questions = listOf(
        "¿Qué ambiente querés perfumar?" to listOf(
            "Living & Salón" to "Espacio de encuentro, lectura y luz natural",
            "Dormitorio & Cama" to "Santuario de descanso, sábanas y noche",
            "Atelier & Estudio" to "Concentración, creatividad y aire limpio",
            "Entrada & Galería" to "La primera bienvenida al hogar"
        ),
        "¿Qué sensación buscás?" to listOf(
            "Calma & Desconexión" to "Bajar el ritmo y respirar hondo",
            "Refugio & Calidez" to "Madera tibia, mantas y silencio",
            "Energía & Claridad" to "Mañanas luminosas y optimismo",
            "Sensualidad & Misterio" to "Atmósfera íntima para la noche"
        ),
        "¿Qué familias aromáticas te atraen?" to listOf(
            "Amaderado" to "Cedro, sándalo, cortezas y ámbar",
            "Cítrico & Fresco" to "Bergamota, naranja amarga y té verde",
            "Verde & Herbal" to "Musgo, hojas de higo, lavanda y pinos",
            "Floral & Gourmand" to "Rosas silvestres, jazmín y vainilla bourbon"
        ),
        "¿Qué intensidad preferís?" to listOf(
            "Sutil & Etéreo" to "Una brisa delicada que no invade",
            "Equilibrado" to "Presencia armónica y constante",
            "Envolvente e Intenso" to "Impronta olfativa marcada y memorable"
        )
    )

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
                    Icon(imageVector = Icons.Outlined.AutoAwesome, contentDescription = null, tint = MejunjeAmber, modifier = Modifier.size(18.dp))
                    Text(
                        text = "DESCUBRÍ TU AROMA",
                        fontFamily = TypewriterFamily,
                        fontSize = 15.sp,
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

            if (result == null && step < questions.size) {
                val (questionTitle, options) = questions[step]

                // Step Progress Indicator
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    (0 until questions.size).forEach { idx ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(3.5.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(if (idx <= step) MejunjeCharcoal else MejunjeBorder)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "PASO ${step + 1} DE ${questions.size}",
                    fontFamily = TypewriterFamily,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MejunjeAmber,
                    letterSpacing = 1.4.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = questionTitle,
                    fontFamily = TypewriterFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MejunjeCharcoal,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    options.forEach { (label, desc) ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(6.dp))
                                .background(MejunjeWhite)
                                .border(0.8.dp, MejunjeBorder, RoundedCornerShape(6.dp))
                                .clickable { onAnswer(step, label) }
                                .padding(14.dp)
                                .testTag("quiz_option_${label.lowercase().take(10)}")
                        ) {
                            Column {
                                Text(
                                    text = label,
                                    fontFamily = TypewriterFamily,
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp,
                                    color = MejunjeCharcoal
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = desc,
                                    fontFamily = EditorialSansFamily,
                                    fontSize = 12.sp,
                                    color = MejunjeTextSecondary
                                )
                            }
                        }
                    }
                }
            } else if (result != null) {
                // Result Card
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(MejunjePaper)
                            .border(1.dp, MejunjeBorder, RoundedCornerShape(8.dp))
                            .padding(18.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "RESULTADO DE ALQUIMIA",
                                fontFamily = TypewriterFamily,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = MejunjeAmber,
                                letterSpacing = 1.8.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = result.profileName,
                                fontFamily = TypewriterFamily,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = MejunjeCharcoal,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "“${result.profilePoem}”",
                                fontFamily = EditorialSansFamily,
                                fontSize = 13.sp,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                color = MejunjeTextSecondary,
                                textAlign = TextAlign.Center,
                                lineHeight = 19.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = result.advice,
                                fontFamily = EditorialSansFamily,
                                fontSize = 12.sp,
                                color = MejunjeCharcoal,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "TUS MEJUNJES RECOMENDADOS",
                        fontFamily = TypewriterFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.4.sp,
                        color = MejunjeCharcoal
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        result.recommendedProducts.forEach { product ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(MejunjeWhite)
                                    .border(0.8.dp, MejunjeBorder, RoundedCornerShape(6.dp))
                                    .clickable {
                                        onSelectProduct(product)
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
                                        text = "${product.aromaticFamily.label} · $${String.format("%,d", product.price).replace(',', '.')}",
                                        fontFamily = EditorialSansFamily,
                                        fontSize = 11.sp,
                                        color = MejunjeTextSecondary
                                    )
                                }

                                Button(
                                    onClick = { onAddToCart(product) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MejunjeCharcoal,
                                        contentColor = MejunjeWarmWhite
                                    ),
                                    shape = RoundedCornerShape(4.dp),
                                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                                    modifier = Modifier.height(34.dp)
                                ) {
                                    Text(
                                        text = "SUMAR",
                                        fontFamily = TypewriterFamily,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
