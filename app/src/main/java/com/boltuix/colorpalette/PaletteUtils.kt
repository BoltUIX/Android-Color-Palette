package com.boltuix.colorpalette

import android.graphics.Bitmap
import androidx.palette.graphics.Palette

//https://developer.android.com/develop/ui/views/graphics/palette-colors
object PaletteUtils {
    fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()
}