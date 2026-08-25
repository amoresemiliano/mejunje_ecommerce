package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.Person
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
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutModal(
    step: Int,
    name: String,
    email: String,
    phone: String,
    address: String,
    city: String,
    shippingMethod: String,
    paymentMethod: String,
    orderNumber: String,
    total: Int,
    onStepChange: (Int) -> Unit,
    onDataChange: (String, String, String, String, String) -> Unit,
    onShippingChange: (String) -> Unit,
    onPaymentChange: (String) -> Unit,
    onCompleteOrder: () -> Unit,
    onDismiss: () -> Unit
) {
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
                Text(
                    text = "FINALIZAR PEDIDO",
                    fontFamily = TypewriterFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.6.sp,
                    color = MejunjeCharcoal
                )

                IconButton(onClick = onDismiss, modifier = Modifier.size(36.dp)) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar", tint = MejunjeCharcoal)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Stepper Navigation
            if (step < 4) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    (1..3).forEach { s ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(3.5.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(if (s <= step) MejunjeCharcoal else MejunjeBorder)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))
            }

            when (step) {
                1 -> {
                    // PASO 1: DATOS PERSONALES
                    Text(
                        text = "1. DATOS PERSONALES",
                        fontFamily = TypewriterFamily,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp,
                        color = MejunjeAmber
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = name,
                        onValueChange = { onDataChange(it, email, phone, address, city) },
                        label = { Text("Nombre y Apellido", fontFamily = TypewriterFamily, fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("checkout_name_input")
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { onDataChange(name, it, phone, address, city) },
                        label = { Text("Correo Electrónico", fontFamily = TypewriterFamily, fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("checkout_email_input")
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = phone,
                        onValueChange = { onDataChange(name, email, it, address, city) },
                        label = { Text("Teléfono / WhatsApp", fontFamily = TypewriterFamily, fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("checkout_phone_input")
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { onStepChange(2) },
                        colors = ButtonDefaults.buttonColors(containerColor = MejunjeCharcoal, contentColor = MejunjeWarmWhite),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.fillMaxWidth().height(46.dp).testTag("checkout_continue_to_shipping")
                    ) {
                        Text("CONTINUAR A ENTREGA →", fontFamily = TypewriterFamily, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                2 -> {
                    // PASO 2: ENTREGA
                    Text(
                        text = "2. DIRECCIÓN DE ENTREGA",
                        fontFamily = TypewriterFamily,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp,
                        color = MejunjeAmber
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = address,
                        onValueChange = { onDataChange(name, email, phone, it, city) },
                        label = { Text("Calle, número y piso", fontFamily = TypewriterFamily, fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("checkout_address_input")
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = city,
                        onValueChange = { onDataChange(name, email, phone, address, it) },
                        label = { Text("Ciudad / Código Postal", fontFamily = TypewriterFamily, fontSize = 12.sp) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("checkout_city_input")
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "OPCIÓN DE ENVÍO:",
                        fontFamily = TypewriterFamily,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MejunjeCharcoal
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    val shippingOptions = listOf(
                        "Envío Express Atelier CABA (24hs)" to "Gratis en compras superiores",
                        "Envío a Todo el País (Andreani / Correo)" to "$2.800"
                    )

                    shippingOptions.forEach { (optionTitle, priceNote) ->
                        val isSelected = shippingMethod.contains(optionTitle.take(15))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (isSelected) MejunjePaper else MejunjeWhite)
                                .border(0.8.dp, if (isSelected) MejunjeCharcoal else MejunjeBorder, RoundedCornerShape(4.dp))
                                .clickable { onShippingChange(optionTitle) }
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(text = optionTitle, fontFamily = TypewriterFamily, fontSize = 11.5.sp, color = MejunjeCharcoal)
                                Text(text = priceNote, fontFamily = EditorialSansFamily, fontSize = 11.sp, color = MejunjeTextMuted)
                            }
                            RadioButton(selected = isSelected, onClick = { onShippingChange(optionTitle) })
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(
                            onClick = { onStepChange(1) },
                            modifier = Modifier.weight(1f).height(46.dp)
                        ) {
                            Text("← VOLVER", fontFamily = TypewriterFamily, fontSize = 11.sp)
                        }

                        Button(
                            onClick = { onStepChange(3) },
                            colors = ButtonDefaults.buttonColors(containerColor = MejunjeCharcoal, contentColor = MejunjeWarmWhite),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.weight(1f).height(46.dp).testTag("checkout_continue_to_payment")
                        ) {
                            Text("CONTINUAR A PAGO", fontFamily = TypewriterFamily, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                3 -> {
                    // PASO 3: MÉTODO DE PAGO
                    Text(
                        text = "3. MÉTODO DE PAGO",
                        fontFamily = TypewriterFamily,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp,
                        color = MejunjeAmber
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    val paymentOptions = listOf(
                        "Transferencia Bancaria Atelier (-10% OFF)" to "Te enviamos los datos bancarios y recibo oficial",
                        "Tarjetas de Crédito / Débito (3 Cuotas)" to "A través de pasarela segura"
                    )

                    paymentOptions.forEach { (option, desc) ->
                        val isSelected = paymentMethod.contains(option.take(15))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (isSelected) MejunjePaper else MejunjeWhite)
                                .border(0.8.dp, if (isSelected) MejunjeCharcoal else MejunjeBorder, RoundedCornerShape(4.dp))
                                .clickable { onPaymentChange(option) }
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = option, fontFamily = TypewriterFamily, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MejunjeCharcoal)
                                Text(text = desc, fontFamily = EditorialSansFamily, fontSize = 11.sp, color = MejunjeTextSecondary)
                            }
                            RadioButton(selected = isSelected, onClick = { onPaymentChange(option) })
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Total Row
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MejunjePaper, RoundedCornerShape(4.dp))
                            .padding(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "TOTAL DE LA ORDEN:", fontFamily = TypewriterFamily, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MejunjeCharcoal)
                            Text(text = "$${String.format("%,d", total).replace(',', '.')}", fontFamily = TypewriterFamily, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MejunjeCharcoal)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(
                            onClick = { onStepChange(2) },
                            modifier = Modifier.weight(1f).height(46.dp)
                        ) {
                            Text("← VOLVER", fontFamily = TypewriterFamily, fontSize = 11.sp)
                        }

                        Button(
                            onClick = onCompleteOrder,
                            colors = ButtonDefaults.buttonColors(containerColor = MejunjeDeepGreen, contentColor = MejunjeWarmWhite),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.weight(1f).height(46.dp).testTag("checkout_confirm_order_button")
                        ) {
                            Text("CONFIRMAR PEDIDO ✓", fontFamily = TypewriterFamily, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                4 -> {
                    // PASO 4: CONFIRMACIÓN POÉTICA
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(25.dp))
                                .background(MejunjeDeepGreen),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = MejunjeWarmWhite)
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "¡TU MEJUNJE ESTÁ EN CAMINO!",
                            fontFamily = TypewriterFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp,
                            color = MejunjeCharcoal,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "ORDEN: $orderNumber",
                            fontFamily = TypewriterFamily,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = MejunjeAmber
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(6.dp))
                                .background(MejunjePaper)
                                .border(0.8.dp, MejunjeBorder, RoundedCornerShape(6.dp))
                                .padding(14.dp)
                        ) {
                            Text(
                                text = "En el atelier ya estamos preparando tu paquete con papel de algodón, pabilos cuidados y sellado de lacre. Enviamos la confirmación a $email.",
                                fontFamily = EditorialSansFamily,
                                fontSize = 13.sp,
                                lineHeight = 19.sp,
                                color = MejunjeCharcoal,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = onDismiss,
                            colors = ButtonDefaults.buttonColors(containerColor = MejunjeCharcoal, contentColor = MejunjeWarmWhite),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.fillMaxWidth().height(46.dp).testTag("checkout_finish_button")
                        ) {
                            Text("VOLVER A LA TIENDA", fontFamily = TypewriterFamily, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
