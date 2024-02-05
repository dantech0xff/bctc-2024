import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val paint = Paint()
@Stable
fun Modifier.shadow(
    color: Color = Color.Black,
    borderRadius: Dp = 4.dp,
    blurRadius: Dp = 4.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0.dp,
    modifier: Modifier = Modifier
) = then(
    modifier.drawBehind {
        drawIntoCanvas {
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.reset()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + offsetX.toPx() + spreadPixel)
            val bottomPixel = (this.size.height + offsetY.toPx() + spreadPixel)

            frameworkPaint.let { fwPaint ->
                fwPaint.isAntiAlias = true
                if (blurRadius > 0.dp) {
                    setMaskFilter(paint, blurRadius.toPx())
                }
                fwPaint.color = color.toArgb()
            }
            it.drawRoundRect(
                left = leftPixel, top = topPixel,
                right = rightPixel, bottom = bottomPixel,
                radiusX = borderRadius.toPx(), radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)

internal expect fun setMaskFilter(paint: Paint, blurRadius: Float)