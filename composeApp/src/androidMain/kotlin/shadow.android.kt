import android.graphics.BlurMaskFilter
import androidx.compose.ui.graphics.Paint

internal actual fun setMaskFilter(paint: Paint, blurRadius: Float) {
    val nativePaint = paint.asFrameworkPaint()
    nativePaint.setMaskFilter(BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL))
}