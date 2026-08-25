package com.example.model

import com.example.ui.theme.*

object MejunjeCatalog {

    val products = listOf(
        Product(
            id = "prod_vela_ambar_madera",
            name = "Vela Botánica Ámbar & Madera",
            category = ProductCategory.VELAS,
            aromaticFamily = OlfactoryFamily.AMADERADO,
            mainNotes = listOf("ámbar", "cedro", "vainilla ahumada"),
            price = 18500,
            sizeVolume = "250g · 50hs de combustión limpia",
            shortStory = "un living de madera después de la lluvia.",
            poeticDescription = "Inspirada en las tardes porteñas de otoño donde el tiempo se detiene entre libros y madera tibia. Vertida en vaso de vidrio caramelo con pabilo de madera que crepita suavemente.",
            feelsLike = "madera tibia, papel viejo y una ventana abierta después de una tormenta de verano.",
            intensity = 4,
            idealForRooms = listOf("Living", "Biblioteca", "Dormitorio"),
            pyramid = OlfactoryPyramid(
                topNotes = listOf("Bergamota italiana", "Cáscara de naranja amarga"),
                heartNotes = listOf("Cedro del Atlas", "Cardamomo", "Jazmín salvaje"),
                baseNotes = listOf("Ámbar fósil", "Vainilla de Madagascar", "Resina de benjuí")
            ),
            moodTags = listOf("Refugio", "Calma", "Para leer", "Para una noche lenta"),
            companionProductIds = listOf("prod_spray_bosque", "prod_difusor_sandalo_higo"),
            isFeatured = true,
            isBestseller = true,
            primaryColor = MejunjeAmber,
            secondaryColor = MejunjeDeepGreen,
            badge = "MÁS ELEGIDO"
        ),
        Product(
            id = "prod_vela_lavanda_manzanilla",
            name = "Vela Lavanda & Manzanilla",
            category = ProductCategory.VELAS,
            aromaticFamily = OlfactoryFamily.VERDE,
            mainNotes = listOf("lavanda silvestre", "flores de manzanilla", "hoja de higuera"),
            price = 17800,
            sizeVolume = "250g · 50hs de combustión limpia",
            shortStory = "un respiro hondo para soltar el día.",
            poeticDescription = "Lavanda recolectada en campos serranos con infusión calmante de manzanilla dulce. La compañía perfecta para la mesa de luz antes del descanso.",
            feelsLike = "sábanas limpias de lino, sol tibio de las cinco de la tarde y silencio.",
            intensity = 2,
            idealForRooms = listOf("Dormitorio", "Espacio de Meditación", "Baño"),
            pyramid = OlfactoryPyramid(
                topNotes = listOf("Lavanda provenzal", "Salvia clara"),
                heartNotes = listOf("Manzanilla dulce", "Neroli"),
                baseNotes = listOf("Haba tonka", "Almizcle blanco")
            ),
            moodTags = listOf("Calma", "Para bajar un cambio", "Para una noche lenta"),
            companionProductIds = listOf("prod_spray_neroli_lino", "prod_textil_rose"),
            isFeatured = false,
            isBestseller = true,
            primaryColor = MejunjeSage,
            secondaryColor = MejunjeOlive,
            badge = "RITUAL NOCTURNO"
        ),
        Product(
            id = "prod_vela_cedro_vainilla",
            name = "Vela Cedro & Vainilla",
            category = ProductCategory.VELAS,
            aromaticFamily = OlfactoryFamily.GOURMAND,
            mainNotes = listOf("cedro añejo", "vainilla bourbon", "pizca de tabaco rubio"),
            price = 18500,
            sizeVolume = "250g · 50hs de combustión limpia",
            shortStory = "la calidez de una charla que no tiene prisa.",
            poeticDescription = "Un equilibrio sublime entre la sobriedad terrosa del cedro y la dulzura aterciopelada de la vainilla bourbon. Notas que abrigan sin empalagar.",
            feelsLike = "taza de café humeante en un rincón de San Telmo, bufanda de lana y calma.",
            intensity = 3,
            idealForRooms = listOf("Living", "Escritorio", "Atelier"),
            pyramid = OlfactoryPyramid(
                topNotes = listOf("Cáscara de nuez moscada", "Flor de azahar"),
                heartNotes = listOf("Cedro virginiano", "Tabaco rubio macerado"),
                baseNotes = listOf("Vainilla bourbon", "Pachulí ligero")
            ),
            moodTags = listOf("Nostalgia", "Sensualidad", "Para leer"),
            companionProductIds = listOf("prod_difusor_sandalo_higo", "prod_blend_humo"),
            isFeatured = true,
            isBestseller = false,
            primaryColor = MejunjeTerracotta,
            secondaryColor = MejunjeAmber,
            badge = "EDICIÓN ATELIER"
        ),
        Product(
            id = "prod_difusor_bergamota_te",
            name = "Difusor Bergamota & Té Verde",
            category = ProductCategory.DIFUSORES,
            aromaticFamily = OlfactoryFamily.CITRICO,
            mainNotes = listOf("bergamota calabresa", "té verde matcha", "menta japonesa"),
            price = 21000,
            sizeVolume = "200ml · 8 varillas de ratán natural",
            shortStory = "luz de mañana entrando por los ventanales.",
            poeticDescription = "Difusión constante y luminosa que purifica el aire y despierta las ideas. Las varillas de fibra vegetal porosa elevan el espíritu con frescura chispeante.",
            feelsLike = "brisa fresca que mueve cortinas blancas, té servido en cuenco de cerámica.",
            intensity = 3,
            idealForRooms = listOf("Atelier", "Cocina", "Entrada", "Oficina"),
            pyramid = OlfactoryPyramid(
                topNotes = listOf("Bergamota prensada en frío", "Pomelo rosado"),
                heartNotes = listOf("Té verde", "Jazmín sambac", "Coriandro"),
                baseNotes = listOf("Vetiver fresco", "Madera flotante")
            ),
            moodTags = listOf("Energía", "Concentración", "Para trabajar", "Para perfumar la casa"),
            companionProductIds = listOf("prod_spray_bosque", "prod_textil_eucalipto"),
            isFeatured = true,
            isBestseller = true,
            primaryColor = MejunjeSoftMustard,
            secondaryColor = MejunjeSage,
            badge = "ESENCIAL"
        ),
        Product(
            id = "prod_difusor_sandalo_higo",
            name = "Difusor Sándalo & Higo",
            category = ProductCategory.DIFUSORES,
            aromaticFamily = OlfactoryFamily.AMADERADO,
            mainNotes = listOf("sándalo de Mysore", "higo maduro", "hoja de laurel"),
            price = 22500,
            sizeVolume = "200ml · 8 varillas de ratán natural",
            shortStory = "un jardín escondido entre casonas antiguas.",
            poeticDescription = "La exuberancia frutal y lechosa del higo se funde en la nobleza cremosa del sándalo. Un aroma profundo que transforma cualquier ambiente en un refugio íntimo.",
            feelsLike = "tarde dorada bajo una higuera frondosa, piel tibia y silencio cómplice.",
            intensity = 4,
            idealForRooms = listOf("Living", "Dormitorio Principal", "Hall"),
            pyramid = OlfactoryPyramid(
                topNotes = listOf("Higo verde", "Mandarina amarga"),
                heartNotes = listOf("Hojas de higuera", "Iris florentino"),
                baseNotes = listOf("Sándalo puro", "Cedro seco", "Almizcle")
            ),
            moodTags = listOf("Sensualidad", "Refugio", "Para recibir gente"),
            companionProductIds = listOf("prod_vela_ambar_madera", "prod_textil_rose"),
            isFeatured = false,
            isBestseller = true,
            primaryColor = MejunjeDryGreen,
            secondaryColor = MejunjeClayRed
        ),
        Product(
            id = "prod_spray_bosque",
            name = "Home Spray Bosque Húmedo",
            category = ProductCategory.HOME_SPRAYS,
            aromaticFamily = OlfactoryFamily.VERDE,
            mainNotes = listOf("musgo de roble", "pino silvestre", "tierra mojada"),
            price = 14900,
            sizeVolume = "250ml · Gatillo pulverizador de bruma fina",
            shortStory = "caminar descalzo sobre el rocío matinal.",
            poeticDescription = "Un golpe de aire fresco y botánico instantáneo. Formulado a base de hidrolatos puros de coníferas y extractos botánicos que oxigenan los espacios.",
            feelsLike = "caminar entre pinos bajo la lluvia, corteza mojada y aire frío en los pulmones.",
            intensity = 4,
            idealForRooms = listOf("Living", "Baño", "Pasillos", "Galería"),
            pyramid = OlfactoryPyramid(
                topNotes = listOf("Agujas de pino", "Eucalipto medicinal"),
                heartNotes = listOf("Musgo de roble", "Salvia silvestre"),
                baseNotes = listOf("Tierra húmeda (Petricor)", "Ciprés")
            ),
            moodTags = listOf("Frescura", "Concentración", "Para perfumar la casa"),
            companionProductIds = listOf("prod_difusor_bergamota_te", "prod_vela_ambar_madera"),
            isFeatured = true,
            isBestseller = true,
            primaryColor = MejunjeDeepGreen,
            secondaryColor = MejunjeSage
        ),
        Product(
            id = "prod_spray_neroli_lino",
            name = "Home Spray Neroli & Lino",
            category = ProductCategory.HOME_SPRAYS,
            aromaticFamily = OlfactoryFamily.FLORAL,
            mainNotes = listOf("flor de azahar", "neroli fresco", "lino crudo"),
            price = 14900,
            sizeVolume = "250ml · Gatillo pulverizador de bruma fina",
            shortStory = "la caricia del sol en las sábanas de media tarde.",
            poeticDescription = "Fragancia ligera, etérea y reconfortante. Ideal para rociar en cortinas, almohadones y espacios antes de recibir visitas o al comenzar el día.",
            feelsLike = "ropa tendida al sol primaveral, flores blancas abriéndose al alba.",
            intensity = 2,
            idealForRooms = listOf("Dormitorio", "Living", "Espacio de Niños"),
            pyramid = OlfactoryPyramid(
                topNotes = listOf("Neroli de Túnez", "Limón amarillo"),
                heartNotes = listOf("Flor de azahar", "Peonía blanca"),
                baseNotes = listOf("Almizcle vegetal", "Cedro blanco")
            ),
            moodTags = listOf("Calma", "Frescura", "Para recibir gente"),
            companionProductIds = listOf("prod_vela_lavanda_manzanilla", "prod_textil_rose"),
            isFeatured = false,
            isBestseller = false,
            primaryColor = MejunjeSalmon,
            secondaryColor = MejunjeSoftMustard
        ),
        Product(
            id = "prod_textil_rose",
            name = "Perfume Textil Velvet Rose",
            category = ProductCategory.TEXTILES,
            aromaticFamily = OlfactoryFamily.FLORAL,
            mainNotes = listOf("rosa de mayo", "madera de guayaco", "pimienta rosa"),
            price = 15800,
            sizeVolume = "250ml · Fórmula que no mancha linos ni sedas",
            shortStory = "un poema escrito en el forro de un abrigo antiguo.",
            poeticDescription = "Una interpretación contemporánea y bohemia de la rosa: no empolvada, sino húmeda, espinosa y envuelta en maderas oscuras y especias.",
            feelsLike = "abrazar a alguien con perfume inolvidable, pétalos rojos entre páginas de libros.",
            intensity = 3,
            idealForRooms = listOf("Vestidor", "Dormitorio", "Placares"),
            pyramid = OlfactoryPyramid(
                topNotes = listOf("Pimienta rosa", "Grosella negra"),
                heartNotes = listOf("Rosa damascena", "Geranio"),
                baseNotes = listOf("Guayaco", "Ámbar gris sintético", "Incienso")
            ),
            moodTags = listOf("Sensualidad", "Nostalgia", "Para una noche lenta"),
            companionProductIds = listOf("prod_vela_cedro_vainilla", "prod_difusor_sandalo_higo"),
            isFeatured = true,
            isBestseller = false,
            primaryColor = MejunjeClayRed,
            secondaryColor = MejunjeTerracotta
        ),
        Product(
            id = "prod_textil_eucalipto",
            name = "Perfume Textil Eucalipto & Salvia",
            category = ProductCategory.TEXTILES,
            aromaticFamily = OlfactoryFamily.VERDE,
            mainNotes = listOf("eucalipto medicinal", "salvia blanca", "romero de huerta"),
            price = 15800,
            sizeVolume = "250ml · Fórmula que no mancha linos ni sedas",
            shortStory = "claridad mental y frescura para tus prendas.",
            poeticDescription = "Diseñado para rociar en tapizados, frazadas, bufandas y almohadas. Deja una estela herbácea que renueva y despeja la energía del espacio.",
            feelsLike = "manos frotando hojas de hierbas frescas, aire puro de montaña.",
            intensity = 3,
            idealForRooms = listOf("Dormitorio", "Studio", "Living"),
            pyramid = OlfactoryPyramid(
                topNotes = listOf("Eucalipto globulus", "Menta silvestre"),
                heartNotes = listOf("Salvia officinale", "Tomillo limonero"),
                baseNotes = listOf("Pino marítimo", "Cedro")
            ),
            moodTags = listOf("Concentración", "Energía", "Para trabajar"),
            companionProductIds = listOf("prod_difusor_bergamota_te", "prod_spray_bosque"),
            isFeatured = false,
            isBestseller = false,
            primaryColor = MejunjeSage,
            secondaryColor = MejunjeDeepGreen
        ),
        Product(
            id = "prod_blend_humo",
            name = "Blend Botánico Resina & Humo",
            category = ProductCategory.SETS,
            aromaticFamily = OlfactoryFamily.ESPECIADO,
            mainNotes = listOf("copal mexicano", "palo santo ético", "incienso de omán"),
            price = 19200,
            sizeVolume = "Gotero de vidrio ámbar 30ml + Piedra difusora",
            shortStory = "el ritual sagrado de sahumar las intenciones.",
            poeticDescription = "Blend concentrado de aceites resinosos puros para usar en difusores eléctricos, cuencos de agua caliente o sobre piedras volcánicas porosas.",
            feelsLike = "humo blanco ascendiendo en espiral, mística de atelier y recogimiento.",
            intensity = 5,
            idealForRooms = listOf("Atelier", "Espacio de Yoga", "Living"),
            pyramid = OlfactoryPyramid(
                topNotes = listOf("Palo santo", "Cilantro"),
                heartNotes = listOf("Copal puro", "Mirra"),
                baseNotes = listOf("Incienso negro", "Ládano")
            ),
            moodTags = listOf("Refugio", "Calma", "Nostalgia"),
            companionProductIds = listOf("prod_vela_cedro_vainilla", "prod_spray_bosque"),
            isFeatured = false,
            isBestseller = false,
            primaryColor = MejunjeTerracotta,
            secondaryColor = MejunjeCharcoal,
            badge = "ALQUIMIA PURA"
        )
    )

    val bundles = listOf(
        BundleItem(
            id = "bundle_noche",
            title = "RITUAL DE NOCHE",
            subtitle = "Para preparar el cuerpo y la mente antes de soñar",
            productsIncluded = listOf(
                "Vela Lavanda & Manzanilla (250g)",
                "Home Spray Neroli & Lino (250ml)",
                "Caja de fósforos botánicos MEJUNJE"
            ),
            originalPrice = 32700,
            bundlePrice = 28500,
            discountPercent = 13,
            tag = "SET RECOMENDADO",
            description = "Un dúo sereno pensado para transformar el dormitorio en un santuario nocturno. Apagá las pantallas, rociá las sábanas y encendé la mecha.",
            accentColor = MejunjeSage
        ),
        BundleItem(
            id = "bundle_casa_lenta",
            title = "SET CASA LENTA",
            subtitle = "La atmósfera completa para toda la casa",
            productsIncluded = listOf(
                "Difusor Bergamota & Té Verde (200ml)",
                "Vela Botánica Ámbar & Madera (250g)",
                "Home Spray Bosque Húmedo (250ml)",
                "Bolsa artesanal de lino crudo"
            ),
            originalPrice = 54400,
            bundlePrice = 46900,
            discountPercent = 14,
            tag = "EXPERIENCIA COMPLETA",
            description = "Nuestra trinidad de aromas para crear capas olfativas: frescura en la entrada, refugio en el living y bruma botánica cuando lo necesites.",
            accentColor = MejunjeAmber
        ),
        BundleItem(
            id = "bundle_regalo_atelier",
            title = "SET REGALO ATELIER",
            subtitle = "El obsequio perfecto listo para emocionar",
            productsIncluded = listOf(
                "Vela Cedro & Vainilla a elección (250g)",
                "Fósforos extra largos con raspador",
                "Tarjeta manuscrita mecanografiada",
                "Envoltorio botánico con lacre sellado a mano"
            ),
            originalPrice = 25000,
            bundlePrice = 21900,
            discountPercent = 12,
            tag = "LISTO PARA REGALAR",
            description = "Elegí el mensaje y nosotros lo escribimos en nuestra máquina de escribir vintage sobre papel de algodón artesanal.",
            accentColor = MejunjeTerracotta
        )
    )

    val botanicals = listOf(
        BotanicalIngredient(
            id = "ing_bergamota",
            name = "BERGAMOTA",
            originNote = "Cáscara cítrica prensada en frío de Calabria",
            family = OlfactoryFamily.CITRICO,
            description = "Chispeante, luminosa y sofisticada. Levanta el ánimo al instante sin caer en la acidez simple.",
            benefits = "Claridad mental, optimismo y disipación de la fatiga.",
            matchingProducts = listOf("prod_difusor_bergamota_te", "prod_vela_ambar_madera")
        ),
        BotanicalIngredient(
            id = "ing_cedro",
            name = "CEDRO",
            originNote = "Corteza destilada al vapor de la cordillera del Atlas",
            family = OlfactoryFamily.AMADERADO,
            description = "Noble, seco y profundamente reconfortante. El aroma de los muebles antiguos y las casas con historia.",
            benefits = "Arraigo, calma interior y concentración prolongada.",
            matchingProducts = listOf("prod_vela_ambar_madera", "prod_vela_cedro_vainilla")
        ),
        BotanicalIngredient(
            id = "ing_lavanda",
            name = "LAVANDA",
            originNote = "Flores silvestres de altura recolectadas al atardecer",
            family = OlfactoryFamily.VERDE,
            description = "Herbal, limpia y balsámica. Muy lejos de la lavanda artificial de limpieza; es la flor real en el campo.",
            benefits = "Baja las pulsaciones, calma la ansiedad y favorece el sueño profundo.",
            matchingProducts = listOf("prod_vela_lavanda_manzanilla")
        ),
        BotanicalIngredient(
            id = "ing_ambar",
            name = "ÁMBAR",
            originNote = "Acorde de resinas fósiles, benjuí y ládano",
            family = OlfactoryFamily.AMADERADO,
            description = "Dorado, cálido, meloso y envolvente. La nota que fija los recuerdos en la memoria olfativa.",
            benefits = "Sensación de abrigo, sensualidad y calidez hogareña.",
            matchingProducts = listOf("prod_vela_ambar_madera", "prod_textil_rose")
        ),
        BotanicalIngredient(
            id = "ing_vainilla",
            name = "VAINILLA",
            originNote = "Vainas maduras curadas al sol de Madagascar",
            family = OlfactoryFamily.GOURMAND,
            description = "Ahumada, licorosa y aterciopelada. Nunca empalagosa: aporta la redondez de un recuerdo de infancia.",
            benefits = "Confort emocional, alivio del estrés y calidez.",
            matchingProducts = listOf("prod_vela_cedro_vainilla", "prod_vela_ambar_madera")
        ),
        BotanicalIngredient(
            id = "ing_sandalo",
            name = "SÁNDALO",
            originNote = "Duramen maduro de madera sagrada",
            family = OlfactoryFamily.AMADERADO,
            description = "Cremoso, suavemente dulce y místico. Crea atmósferas de recogimiento y elegancia atemporal.",
            benefits = "Apertura de la creatividad y relajación muscular.",
            matchingProducts = listOf("prod_difusor_sandalo_higo")
        ),
        BotanicalIngredient(
            id = "ing_jazmin",
            name = "JAZMÍN",
            originNote = "Pétalos nocturnos cosechados antes del amanecer",
            family = OlfactoryFamily.FLORAL,
            description = "Embriagador, verde y sensual. El olor de las noches de verano en los patios de Buenos Aires.",
            benefits = "Despierta la sensualidad, combate la apatía y eleva la emoción.",
            matchingProducts = listOf("prod_difusor_bergamota_te", "prod_vela_ambar_madera")
        ),
        BotanicalIngredient(
            id = "ing_eucalipto",
            name = "EUCALIPTO",
            originNote = "Hojas cerosas de eucalipto azul medicinal",
            family = OlfactoryFamily.VERDE,
            description = "Alcanforado, fresco y expansivo. Abre los pulmones y renueva el aire estancado.",
            benefits = "Respiración libre, energía renovada y purificación del ambiente.",
            matchingProducts = listOf("prod_spray_bosque", "prod_textil_eucalipto")
        )
    )

    val moodCategories = listOf(
        MoodFilter(
            id = "mood_calma",
            title = "CALMA",
            subtitle = "Para soltar el ritmo del día",
            quote = "Un silencio hondo donde todo vuelve a su centro.",
            color = MejunjeSage
        ),
        MoodFilter(
            id = "mood_refugio",
            title = "REFUGIO",
            subtitle = "El abrazo de tu propia casa",
            quote = "Paredes de madera, té caliente y lluvia afuera.",
            color = MejunjeAmber
        ),
        MoodFilter(
            id = "mood_energia",
            title = "ENERGÍA",
            subtitle = "Despertar las mañanas con luz",
            quote = "Chispas de sol que ponen las ideas en marcha.",
            color = MejunjeSoftMustard
        ),
        MoodFilter(
            id = "mood_frescura",
            title = "FRESCURA",
            subtitle = "Bruma limpia y ventanas abiertas",
            quote = "El aire puro de un bosque que respira después del agua.",
            color = MejunjeOlive
        ),
        MoodFilter(
            id = "mood_sensualidad",
            title = "SENSUALIDAD",
            subtitle = "Misterio, cercanía y noches lentas",
            quote = "Piel tibia, pétalos oscuros y una luz baja.",
            color = MejunjeTerracotta
        ),
        MoodFilter(
            id = "mood_concentracion",
            title = "CONCENTRACIÓN",
            subtitle = "Para el atelier y el trabajo creativo",
            quote = "Espacio despejado para escribir, crear y pensar.",
            color = MejunjeDeepGreen
        ),
        MoodFilter(
            id = "mood_nostalgia",
            title = "NOSTALGIA",
            subtitle = "Memorias vivas de otros tiempos",
            quote = "Papeles viejos, cartas guardadas y perfume que regresa.",
            color = MejunjeClayRed
        )
    )

    val moodMomentBlocks = listOf(
        "PARA BAJAR UN CAMBIO" to listOf("prod_vela_lavanda_manzanilla", "prod_spray_neroli_lino"),
        "PARA RECIBIR GENTE" to listOf("prod_difusor_sandalo_higo", "prod_spray_neroli_lino"),
        "PARA LEER" to listOf("prod_vela_ambar_madera", "prod_vela_cedro_vainilla"),
        "PARA TRABAJAR" to listOf("prod_difusor_bergamota_te", "prod_textil_eucalipto"),
        "PARA UNA NOCHE LENTA" to listOf("prod_textil_rose", "prod_vela_cedro_vainilla"),
        "PARA REGALAR" to listOf("prod_vela_ambar_madera", "bundle_regalo_atelier"),
        "PARA PERFUMAR LA CASA" to listOf("prod_spray_bosque", "prod_difusor_bergamota_te")
    )

    val editorialReviews = listOf(
        Triple(
            "“Mi casa huele al lugar exacto donde quiero quedarme para siempre.”",
            "Lucía M.",
            "Palermo · Cliente desde 2023"
        ),
        Triple(
            "“La vela de Ámbar y Madera con pabilo de madera tiene una presencia que no encontré en ninguna otra marca de Buenos Aires.”",
            "Esteban R.",
            "Colegiales · Arquitecto"
        ),
        Triple(
            "“El home spray de Bosque Húmedo transformó mi estudio de trabajo. Es como abrir una ventana a la Patagonia.”",
            "Camila V.",
            "San Telmo · Ilustradora"
        )
    )

    val ugcMoments = listOf(
        "Mesa de noche de madera y lino con Vela Lavanda encendida.",
        "Atelier de pintura con Difusor Bergamota y pinceles.",
        "Biblioteca con libros antiguos, café y Vela Ámbar & Madera.",
        "Ventana soleada con Home Spray Neroli & Lino."
    )
}
