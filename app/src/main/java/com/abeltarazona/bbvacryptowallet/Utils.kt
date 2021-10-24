package com.abeltarazona.bbvacryptowallet

/**
 * Created by AbelTarazona on 21/10/2021
 */

/**
 * Abel
 * Cesar
 * Franklyn
 * Franco
 * Jhonatan
 */
fun getListUsers() = listOf(
    74840159,
    74840119,
    74840129,
    74840139,
    74840149
)

const val BITCOIN_VALUE = 61207.23
const val ETHEREUM_VALUE = 4038.27
const val CARDANO_VALUE = 2.16
const val DOLAR_SOLES = 3.97
const val BIT_SOL = 240299.50
const val imageIcon = "https://s2.coinmarketcap.com/static/img/coins/64x64/"

fun getBitcoinBBVA() = BITCOIN_VALUE * DOLAR_SOLES
fun getEthereumBBVA() = ETHEREUM_VALUE * DOLAR_SOLES
fun getCardanoValue() = CARDANO_VALUE * DOLAR_SOLES

fun getListVariation() = listOf(1.2, -2.9, 5.4, 3.2, -9.2)

data class Crypto(
    val icon: String,
    val name: String,
    val nameLarge: String,
    val price: Double,
    val priceStr: String,
    val variation: Double,
    val isBBVAToken: Boolean = true
)

fun getCryptoMarketList() = listOf(
    Crypto(
        "${imageIcon}1.png", "BBTC", "BITCOIN",
        getBitcoinBBVA(),
        String.format("%.2f", getBitcoinBBVA()), getListVariation().random()
    ),
    Crypto(
        "${imageIcon}1027.png",
        "BETH", "ETHEREUM",
        getEthereumBBVA(),
        String.format("%.2f", getEthereumBBVA()),
        getListVariation().random()
    ),
    Crypto(
        "https://i.imgur.com/qfJwCEp.png",
        "BBVA \nCoin", "BBVACOIN",
        3.95,
        String.format("%.2f", 3.95),
        getListVariation().random()
    ),
    Crypto(
        "${imageIcon}2010.png",
        "ADA", "ADA",
        getCardanoValue(),
        String.format("%.2f", getCardanoValue()),
        getListVariation().random()
    ),
    Crypto(
        "https://i.imgur.com/Ff9AIBr.png",
        "COD", "CODITEC",
        2.56,
        String.format("%.2f", 2.56),
        getListVariation().random()
    ),
)


// META SCREEN
data class Meta(
    val icon: Int,
    val title: String,
    val selected: Boolean = false
)

fun getMetasList() = listOf(
    Meta(R.drawable.open_book, "Estudios en el extranjero"),
    Meta(R.drawable.travel, "Viaje por el mundo"),
    Meta(R.drawable.car, "Carro del año"),
    Meta(R.drawable.house, "Dulce hogar"),
    Meta(R.drawable.analytics, "Nuevo negocio"),
    Meta(R.drawable.piggy_bank, "Ahorro para el futuro"),
)

// BENEFICIOS SCREEN

data class Collect(
    val tokens: Int,
    val description: String
)

fun getCollectList() = listOf(
    Collect(400, "Por abrir tu cuenta"),
    Collect(1, "Por cada S/ 3.95 de inversión"),
)

data class Canje(
    val tokens: Int,
    val name: String,
    val image: String,
)

fun getCanjeList() = listOf(
    Canje(10000, "Ford Bronco", "http://assets.stickpng.com/images/580b585b2edbce24c47b2c67.png"),
    Canje(40000, "Latam", "https://logodownload.org/wp-content/uploads/2016/11/latam-logo-15.png"),
    Canje(300, "KFC", "http://assets.stickpng.com/images/58429977a6515b1e0ad75ade.png"),
    Canje(
        500,
        "McDonald",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/160px-McDonald%27s_Golden_Arches.svg.png"
    ),
)