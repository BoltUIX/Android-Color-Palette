package com.boltuix.colorpalette

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.boltuix.colorpalette.databinding.FragmentColorPaletteBinding
import com.google.android.material.imageview.ShapeableImageView


class ColorPaletteFragment : Fragment() {
    private var _binding: FragmentColorPaletteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    var mutedColor: Int = androidx.appcompat.R.attr.colorPrimary
    private val defaultColor: Int get() = ContextCompat.getColor(
        requireContext(),
        R.color.colorPrimary
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentColorPaletteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val bitmap = BitmapFactory.decodeResource(
            resources,
            R.drawable.sample4
        )
        Palette.from(bitmap).generate { palette ->

            mutedColor = palette!!.getMutedColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorPrimary
                )
            )
            binding.collapsingToolbar.setContentScrimColor(
                mutedColor
                )
            binding.collapsingToolbar.setStatusBarScrimColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorSecondaryTextDefaultMaterialLight
                )
            )
        }




        val palette = PaletteUtils.createPaletteSync(bitmap)
        view.findViewById<ImageView>(R.id.color_extraction_target_image_view).apply {
            setImageBitmap(bitmap)
        }
        setVibrantPalette(view, palette)
        setMutedPalette(view, palette)




    }

    private fun setMutedPalette(view: View, palette: Palette) {
        view.findViewById<ShapeableImageView>(R.id.muted_image_view).apply {
            val color = palette.getMutedColor(defaultColor)
            setBackgroundColor(color)

            val psVibrant: Palette.Swatch = palette.vibrantSwatch!!
            val color1: Int = psVibrant.rgb
            val population: Int = psVibrant.population
            val hsl: FloatArray = psVibrant.hsl
            val bodyTextColor: Int = psVibrant.bodyTextColor
            val titleTextColor: Int = psVibrant.titleTextColor

            Log.d("color1","psVibrant:"+psVibrant)
            Log.d("color1","color1:"+color1)
            Log.d("color1","population:"+population)
            Log.d("color1","hsl:"+hsl)
            Log.d("color1","bodyTextColor:"+bodyTextColor)
            Log.d("color1","titleTextColor:"+titleTextColor)



            /* val psVibrant: Swatch = palette.vibrantSwatch!!
             val color1: Int = psVibrant.rgb
             val population: Int = psVibrant.population
             val hsl: FloatArray = psVibrant.hsl
             val bodyTextColor: Int = psVibrant.bodyTextColor
             val titleTextColor: Int = psVibrant.titleTextColor

             color_vibrant.text= bodyTextColor.toString()*/
        }
        view.findViewById<ShapeableImageView>(R.id.light_muted_image_view).apply {
            val color = palette.getLightMutedColor(defaultColor)
            setBackgroundColor(color)
        }
        view.findViewById<ShapeableImageView>(R.id.dark_muted_image_view).apply {
            val color = palette.getDarkMutedColor(defaultColor)
            setBackgroundColor(color)
        }
    }

    private fun setVibrantPalette(view: View, palette: Palette) {
        view.findViewById<ShapeableImageView>(R.id.vibrant_image_view).apply {
            val color = palette.getVibrantColor(defaultColor)
            setBackgroundColor(color)
        }
        view.findViewById<ShapeableImageView>(R.id.light_vibrant_image_view).apply {
            val color = palette.getLightVibrantColor(defaultColor)
            setBackgroundColor(color)
        }
        view.findViewById<ShapeableImageView>(R.id.dark_vibrant_image_view).apply {
            val color = palette.getDarkVibrantColor(defaultColor)
            setBackgroundColor(color)
        }
    }

    private fun getBitmap(@DrawableRes id: Int) =
        BitmapFactory.decodeResource(requireContext().resources, id)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}