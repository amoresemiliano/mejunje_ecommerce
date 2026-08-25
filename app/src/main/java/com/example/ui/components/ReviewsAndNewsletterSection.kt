package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MejunjeCatalog
import com.example.ui.theme.*

@Composable
fun ReviewsAndNewsletterSection(
    email: String,
    onEmailChange: (String) -> Unit,
    onSubscribe: () -> Unit,
    isSubscribed: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        // Section: LO QUE CUENTAN (Reviews)
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "LO QUE CUENTAN",
                fontFamily = TypewriterFamily,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = MejunjeCharcoal
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Historias de hogares que huelen a MEJUNJE.",
                fontFamily = EditorialSansFamily,
                fontSize = 13.sp,
                color = MejunjeTextSecondary
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Reviews Carousel
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MejunjeCatalog.editorialReviews.forEach { (quote, author, location) ->
                Box(
                    modifier = Modifier
                        .width(260.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MejunjeWhite)
                        .border(0.8.dp, MejunjeBorder, RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = quote,
                            fontFamily = EditorialSansFamily,
                            fontSize = 13.5.sp,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                            lineHeight = 20.sp,
                            color = MejunjeCharcoal
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = author.uppercase(),
                            fontFamily = TypewriterFamily,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            color = MejunjeAmber
                        )
                        Text(
                            text = location,
                            fontFamily = TypewriterFamily,
                            fontSize = 9.5.sp,
                            color = MejunjeTextMuted
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Section: CARTAS DEL ATELIER (Newsletter)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MejunjePaper)
                .border(1.dp, MejunjeBorder, RoundedCornerShape(10.dp))
                .padding(22.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "CARTAS DEL ATELIER",
                    fontFamily = TypewriterFamily,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 3.sp,
                    color = MejunjeCharcoal
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "aromas · pruebas · historias y novedades de MEJUNJE",
                    fontFamily = EditorialSansFamily,
                    fontSize = 13.5.sp,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    color = MejunjeTextSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(14.dp))

                if (isSubscribed) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MejunjeLightGreen, RoundedCornerShape(4.dp))
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "✓ ¡Listo! Ya estás en la lista de nuestras cartas.",
                            fontFamily = TypewriterFamily,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MejunjeDeepGreen
                        )
                    }
                } else {
                    OutlinedTextField(
                        value = email,
                        onValueChange = onEmailChange,
                        placeholder = {
                            Text(
                                text = "tu@correo.com",
                                fontFamily = TypewriterFamily,
                                fontSize = 12.sp,
                                color = MejunjeTextMuted
                            )
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
                            .testTag("newsletter_email_input")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = onSubscribe,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MejunjeCharcoal,
                            contentColor = MejunjeWarmWhite
                        ),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .testTag("newsletter_subscribe_button")
                    ) {
                        Text(
                            text = "QUIERO RECIBIRLAS",
                            fontFamily = TypewriterFamily,
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 1.6.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Minimalist Footer
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MEJUNJE",
                fontFamily = TypewriterFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 4.sp,
                color = MejunjeCharcoal
            )
            Text(
                text = "Buenos Aires · Argentina",
                fontFamily = TypewriterFamily,
                fontSize = 10.sp,
                color = MejunjeTextMuted,
                letterSpacing = 1.2.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Tienda · Atelier · Aromas · Regalos · Envíos",
                fontFamily = TypewriterFamily,
                fontSize = 10.5.sp,
                color = MejunjeTextSecondary,
                letterSpacing = 0.8.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "“mezcla. intención. aroma.”",
                fontFamily = EditorialSansFamily,
                fontSize = 11.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = MejunjeAmber
            )
        }
    }
}
