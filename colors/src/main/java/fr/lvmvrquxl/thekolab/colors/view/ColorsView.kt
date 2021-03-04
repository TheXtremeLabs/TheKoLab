package fr.lvmvrquxl.thekolab.colors.view

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import fr.lvmvrquxl.thekolab.colors.databinding.ColorsActivityBinding
import fr.lvmvrquxl.thekolab.colors.view.content.ChangeColorsView
import fr.lvmvrquxl.thekolab.colors.view.content.ColorInfoView
import fr.lvmvrquxl.thekolab.colors.view.toolbar.ExitView
import fr.lvmvrquxl.thekolab.colors.view.toolbar.TitleView
import fr.lvmvrquxl.thekolab.shared.view.ActivityView
import fr.lvmvrquxl.thekolab.shared.view.LifecycleObserver
import fr.lvmvrquxl.thekolab.shared.view.LifecycleView

/**
 * Main view of the colors activity.
 *
 * @param activity Instance of the colors activity
 *
 * @since 1.0.0
 *
 * @see ActivityView
 * @see AppCompatActivity
 */
internal class ColorsView private constructor(private val activity: AppCompatActivity) :
    ActivityView<ColorsActivityBinding>(activity) {
    companion object {
        /**
         * Create an instance of the view.
         *
         * @param activity Instance of the colors activity
         *
         * @return New instance of the view
         *
         * @since 1.0.0
         *
         * @see ActivityView
         * @see AppCompatActivity
         */
        fun create(activity: AppCompatActivity): LifecycleObserver = ColorsView(activity)
    }

    override fun bindView() {
        super.viewBinding = ColorsActivityBinding.inflate(this.activity.layoutInflater)
    }

    override fun registerViews() {
        super.viewBinding?.colorsContent?.changeColors?.let { changeColors: MaterialButton ->
            val view: LifecycleView = ChangeColorsView.create(this.activity, changeColors)
            super.addView(view)
        }

        super.viewBinding?.colorsContent?.colorInfo?.let { colorInfo: MaterialTextView ->
            val view: LifecycleView = ColorInfoView.create(this.activity, colorInfo)
            super.addView(view)
        }

        super.viewBinding?.colorsToolbar?.exit?.let { exit: ShapeableImageView ->
            val view: LifecycleView = ExitView.create(this.activity, exit)
            super.addView(view)
        }

        super.viewBinding?.colorsToolbar?.title?.let { title: MaterialTextView ->
            val view: LifecycleView = TitleView.create(this.activity, title)
            super.addView(view)
        }
    }
}