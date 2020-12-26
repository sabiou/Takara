package dev.farouk.takara.ui.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import dev.farouk.takara.R

/**
 * Created by Farouk on 19/12/2020.
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        view.load(imageUrl) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    } else {
        view.load(R.drawable.ic_baseline_account_circle_24) {
            transformations(CircleCropTransformation())
        }
    }
}