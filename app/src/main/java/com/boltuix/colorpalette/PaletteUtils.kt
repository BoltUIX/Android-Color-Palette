package com.boltuix.colorpalette

import android.graphics.Bitmap
import androidx.palette.graphics.Palette


object PaletteUtils {

    fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

}