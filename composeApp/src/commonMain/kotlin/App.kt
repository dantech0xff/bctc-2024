import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

const val VND_50_000: Int = 50000
const val VND_100_000: Int = 100000
const val VND_500_000: Int = 500000

fun convertMoney(money: Int): String {
    return (money / 1000).toString() + "k"
}

@Composable
fun App() {
    MaterialTheme {

        var dices by remember {
            mutableStateOf(arrayOf(GambleType.NONE, GambleType.NONE, GambleType.NONE))
        }

        var money by remember {
            mutableStateOf(100000)
        }

        var currentMoneySelected by remember {
            mutableStateOf(0)
        }

        Column(
            Modifier.fillMaxWidth().safeDrawingPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Vốn: ${convertMoney(money)}",
                modifier = Modifier.wrapContentSize().padding(12.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal
            )

            Row(
                modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.wrapContentSize()
                        .background(
                            Color.Red.copy(alpha = if (currentMoneySelected == VND_50_000) 1f else 0.5f),
                            RoundedCornerShape(8.dp)
                        )
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            if (money >= VND_50_000) {
                                currentMoneySelected = VND_50_000
                            }
                        }
                        .padding(8.dp)
                ) {
                    Text(
                        convertMoney(VND_50_000),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier.wrapContentSize()
                        .background(
                            Color.Red.copy(alpha = if (currentMoneySelected == VND_100_000) 1f else 0.5f),
                            RoundedCornerShape(8.dp)
                        )
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            if (money >= VND_100_000) {
                                currentMoneySelected = VND_100_000
                            }
                        }.padding(8.dp)
                ) {
                    Text(
                        convertMoney(VND_100_000),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier.wrapContentSize()
                        .background(
                            Color.Red.copy(alpha = if (currentMoneySelected == VND_500_000) 1f else 0.5f),
                            RoundedCornerShape(8.dp)
                        )
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            if (money >= VND_500_000) {
                                currentMoneySelected = VND_500_000
                            }
                        }.padding(8.dp)
                ) {
                    Text(
                        convertMoney(VND_500_000),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(Color.Red.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            money += VND_500_000
                        }
                ) {
                    Text(
                        "+",
                        modifier = Modifier.align(Alignment.Center),
                        fontWeight = FontWeight.Bold,
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                        color = Color.Yellow
                    )
                }
            }

            LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = Modifier.fillMaxWidth()) {
                item {
                    GambleCell(GambleType.BAU) {

                    }
                }
                item {
                    GambleCell(GambleType.CUA) {

                    }
                }
                item {
                    GambleCell(GambleType.TOM) {

                    }
                }
                item {
                    GambleCell(GambleType.CA) {

                    }
                }
                item {
                    GambleCell(GambleType.GA) {

                    }
                }
                item {
                    GambleCell(GambleType.NAI) {

                    }
                }
            }


            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                MiniGambleCell(dices[0])
                MiniGambleCell(dices[1])
                MiniGambleCell(dices[2])
            }

            Box(
                modifier = Modifier.fillMaxWidth().height(100.dp).padding(4.dp)
                    .background(Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {

                    }
            ) {
                Text(
                    "Xóc!", modifier = Modifier.wrapContentSize().align(Alignment.Center),
                    fontSize = 32.sp, fontWeight = FontWeight.Black
                )
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun GambleCell(gambleType: GambleType, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
    ) {
        Image(
            painter = painterResource(
                when (gambleType) {
                    GambleType.BAU -> "bau.png"
                    GambleType.CUA -> "cua.png"
                    GambleType.TOM -> "tom.png"
                    GambleType.CA -> "ca.png"
                    GambleType.GA -> "ga.png"
                    GambleType.NAI -> "nai.png"
                    else -> "none.jpeg"
                }
            ),
            contentDescription = "Compose Logo",
            modifier = Modifier.align(Alignment.Center)
                .size(120.dp)
                .background(
                Color.LightGray.copy(alpha = 0.5f),
                RoundedCornerShape(16.dp)
            ).clip(RoundedCornerShape(16.dp))
                .clickable {
                    onClick()
                },
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MiniGambleCell(gambleType: GambleType) {
    Box(
        modifier = Modifier.size(42.dp)
            .background(
                Color.LightGray.copy(alpha = 0.5f),
                RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(
                when (gambleType) {
                    GambleType.BAU -> "bau.png"
                    GambleType.CUA -> "cua.png"
                    GambleType.TOM -> "tom.png"
                    GambleType.CA -> "ca.png"
                    GambleType.GA -> "ga.png"
                    GambleType.NAI -> "nai.png"
                    else -> "none.jpeg"
                }
            ),
            contentDescription = "Compose Logo",
            modifier = Modifier.wrapContentSize()
        )
    }
}

enum class GambleType(val value: Int) {
    NONE(-1),
    BAU(0),
    CUA(1),
    TOM(2),
    CA(3),
    GA(4),
    NAI(5)
}